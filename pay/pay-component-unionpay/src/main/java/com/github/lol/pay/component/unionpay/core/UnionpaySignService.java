package com.github.lol.pay.component.unionpay.core;

import com.github.lol.pay.component.unionpay.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.*;

/**
 * sign util
 *
 * @author: jifuwei
 * @create: 2019-07-12 11:55
 **/
@Slf4j
public class UnionpaySignService {

    private UnionpayConfig config;
    private CertificateService certService;

    public UnionpaySignService(UnionpayConfig config, CertificateService certService) {
        this.config = config;
        this.certService = certService;
    }

    public static UnionpaySignService of(UnionpayConfig config, CertificateService certService) {
        return new UnionpaySignService(config, certService);
    }

    /**
     * sign for unionpay request data, use default encoding
     *
     * @param params
     */
    public void sign(Map<String, String> params) {
        sign(params, null);
    }

    /**
     * sign for unionpay request data
     *
     * @param params
     * @param encoding
     */
    public void sign(Map<String, String> params, String encoding) {
        Validate.notNull(params, "sign params can't null");
        encoding = StringUtils.isEmpty(encoding) ? UTF_8_ENCODING : encoding;
        params.forEach((k, v) -> log.debug("==> param [key]: {} [value]: {}", k, v));
        log.debug("==> encoding [params]: {}", encoding);

        String signMethod = params.get(PARMA_SIGN_METHOD);
        String version = params.get(PARMA_VERSION);

        Validate.notEmpty(signMethod, "signMethod can't empty");
        Validate.notEmpty(version, "version can't empty");
        if (!VERSION_1_0_0.equals(version) && !VERSION_5_0_1.equals(version) && StringUtils.isEmpty(signMethod)) {
            throw new RuntimeException("illegal version: " + version);
        }

        EncryptHandle.execute(params, encoding, config, certService);
    }


    /**
     * diff encrypt method collection
     */
    interface EncryptHandle {
        Logger LOGGER = LoggerFactory.getLogger(EncryptHandle.class);

        /**
         * execute handler
         *
         * @param params
         * @param encoding
         * @param config
         * @param certService
         */
        static void execute(Map<String, String> params, String encoding, UnionpayConfig config, CertificateService certService) {
            String signMethod = params.get(PARMA_SIGN_METHOD);

            EncryptHandle targetHandler;
            switch (signMethod) {
                case SIGN_METHOD_RSA:
                    targetHandler = RSA;
                    break;
                case SIGN_METHOD_SHA256:
                    targetHandler = SHA256;
                    break;
                case SIGN_METHOD_SM3:
                    targetHandler = SM3;
                    break;
                default:
                    throw new RuntimeException("encrypt handler can't find");
            }

            targetHandler.handle(params, encoding, config, certService);
        }

        /**
         * sign data & verify cert
         *
         * @param params
         * @param encoding
         * @param config
         * @param certService
         */
        void handle(Map<String, String> params, String encoding, UnionpayConfig config, CertificateService certService);

        /**
         * RSA encrypt handler
         * api version must VERSION_5_0_1 or VERSION_1_0_0
         */
        EncryptHandle RSA = (params, encoding, config, certService) -> {

            BiFunction<Map<String, String>, String, byte[]> signDigestBiFunction = (p, e) -> {

                try {
                    String stringData = coverMap2String(p);
                    LOGGER.debug("==> RSA EncryptHandle [stringData]: {}", stringData);
                    String version = p.get(PARMA_VERSION);

                    byte[] signDigest;
                    switch (version) {
                        case VERSION_5_1_0:
                            signDigest = SecurityUtil.sha256X16(stringData, e).getBytes(e);
                            break;
                        case VERSION_5_0_0:
                        case VERSION_1_0_0:
                        case VERSION_5_0_1:
                            signDigest = SecurityUtil.sha1X16(stringData, e).getBytes(e);
                            break;
                        default:
                            throw new RuntimeException("illegal version: " + version);
                    }

                    return signDigest;
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e1) {
                    throw new RuntimeException("RSA EncryptHandle error", e1);
                }

            };

            byte[] signDigest = signDigestBiFunction.apply(params, encoding);
            LOGGER.debug("==> RSA EncryptHandle [signDigest]: {}", new String(signDigest));

            BiFunction<byte[], Map<String, String>, String> signSeqFunction = (s, p) -> {

                String version = p.get(PARMA_VERSION);

                byte[] byteSign;
                switch (version) {
                    case VERSION_5_1_0:
                        byteSign = SecurityUtil.base64Encode(SecurityUtil
                                .signBySoftSHA256(certService.getSignCertPrivateKey(), s));
                        break;
                    case VERSION_5_0_0:
                    case VERSION_1_0_0:
                    case VERSION_5_0_1:
                        byteSign = SecurityUtil.base64Encode(SecurityUtil
                                .signBySoftSHA1(certService.getSignCertPrivateKey(), s));
                        break;
                    default:
                        throw new RuntimeException("illegal version: " + version);
                }

                return new String(byteSign);
            };


            String stringSign = signSeqFunction.apply(signDigest, params);
            LOGGER.debug("==> RSA EncryptHandle [stringSign]: {}", stringSign);

            params.put(PARMA_CERT_ID, certService.getSignCertId());
            params.put(PARAM_SIGNATURE, stringSign);
        };

        /**
         * SHA-256 encrypt handler
         */
        EncryptHandle SHA256 = (params, encoding, config, certService) -> {

            try {
                String secureKey = Optional.ofNullable(config.getSecureKey())
                        .orElseThrow(() -> new RuntimeException("secureKey can't empty"));
                String stringData = coverMap2String(params);
                LOGGER.debug("==> SHA256 EncryptHandle [stringData]: {}", stringData);
                LOGGER.debug("==> SHA256 EncryptHandle [secureKey]: {}", secureKey);

                String strBeforeSha256 = stringData +
                        AMPERSAND + SecurityUtil.sha256X16(secureKey, encoding);
                String strAfterSha256 = SecurityUtil.sha256X16(strBeforeSha256, encoding);
                LOGGER.debug("==> SHA256 EncryptHandle [strAfterSha256]: {}", strAfterSha256);

                params.put(PARAM_SIGNATURE, strAfterSha256);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA256 EncryptHandle error", e);
            }

        };

        /**
         * SM3 encrypt handler
         */
        EncryptHandle SM3 = (params, encoding, config, certService) -> {

            try {
                String secureKey = Optional.ofNullable(config.getSecureKey())
                        .orElseThrow(() -> new RuntimeException("secureKey can't empty"));
                String stringData = coverMap2String(params);
                LOGGER.debug("==> SM3 EncryptHandle [stringData]: {}", stringData);
                LOGGER.debug("==> SM3 EncryptHandle [secureKey]: {}", secureKey);

                String strBeforeSM3 = stringData + AMPERSAND + SecurityUtil.sm3X16(secureKey, encoding);
                String strAfterSM3 = SecurityUtil.sm3X16(strBeforeSM3, encoding);
                LOGGER.debug("==> SM3 EncryptHandle [strAfterSM3]: {}", strAfterSM3);

                params.put(PARAM_SIGNATURE, strAfterSM3);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("SM3 EncryptHandle error", e);
            }

        };

        /**
         * convert map to k=v&k=v seq order by key's ASCII num ASC
         *
         * @param params
         * @return
         */
        static String coverMap2String(Map<String, String> params) {
            if (Objects.isNull(params) || params.size() < 1) {
                return null;
            }

            return new TreeMap<>(params).entrySet()
                    .stream()
                    .filter(e -> !PARAM_SIGNATURE.equals(e.getKey().trim()))
                    .map(e -> e.getKey() + EQUAL + e.getValue())
                    .collect(Collectors.joining(AMPERSAND));
        }

    }
}
