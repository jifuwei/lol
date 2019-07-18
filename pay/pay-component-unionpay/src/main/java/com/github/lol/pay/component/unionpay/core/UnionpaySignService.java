package com.github.lol.pay.component.unionpay.core;

import com.github.lol.lib.util.SerializeUtil;
import com.github.lol.lib.util.StrUtil;
import com.github.lol.lib.util.ValidUtil;
import com.github.lol.pay.component.unionpay.util.SecurityUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

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

    public UnionpaySignService(@NonNull UnionpayConfig config, @NonNull CertificateService certService) {
        this.config = config;
        this.certService = certService;
    }

    public static UnionpaySignService of(@NonNull UnionpayConfig config,
                                         @NonNull CertificateService certService) {
        return new UnionpaySignService(config, certService);
    }

    /**
     * sign for unionpay request data, use default encoding
     *
     * @param params
     */
    public void sign(@NonNull Map<String, String> params) {
        sign(params, null);
    }

    /**
     * sign for unionpay request data
     *
     * @param params
     * @param encoding
     */
    public void sign(@NonNull Map params, String encoding) {
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;
        params.forEach((k, v) -> log.debug("==> param [key]: {} [value]: {}", k, v));
        log.debug("==> encoding [params]: {}", encoding);

        String signMethod = String.valueOf(params.get(PARMA_SIGN_METHOD));
        String version = String.valueOf(params.get(PARMA_VERSION));

        ValidUtil.notEmpty(signMethod, "signMethod can't empty");
        ValidUtil.notEmpty(version, "version can't empty");
        if (!VERSION_1_0_0.equals(version) && !VERSION_5_0_1.equals(version)
                && StrUtil.isEmpty(signMethod)) {
            throw new RuntimeException("illegal version: " + version);
        }

        EncryptHandle.execute(params, encoding, config, certService);
    }

    /**
     * verify resp params
     *
     * @param respMap
     * @param encoding
     */
    public void validate(@NonNull Map<String, String> respMap, String encoding) {
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;

        VerifyHandle.execute(respMap, encoding, config, certService);
    }


    /**
     * diff encrypt method collection
     */
    @FunctionalInterface
    interface EncryptHandle {
        Logger LOGGER = LoggerFactory.getLogger(EncryptHandle.class);

        /**
         * RSA encrypt handler
         */
        EncryptHandle RSA = (params, encoding, config, certService) -> {

            // certId should sign
            params.put(PARMA_CERT_ID, certService.getSignCertId());
            BiFunction<Map<String, String>, String, byte[]> signDigestBiFunction = (p, e) -> {

                try {
                    String stringData = SerializeUtil.map2KVStr(p, new HashSet<String>() {{
                        add(PARAM_SIGNATURE);
                    }});
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

            params.put(PARAM_SIGNATURE, stringSign);
        };

        /**
         * SHA-256 encrypt handler
         */
        EncryptHandle SHA256 = (params, encoding, config, certService) -> {

            try {
                String secureKey = Optional.ofNullable(config.getSecureKey())
                        .orElseThrow(() -> new RuntimeException("secureKey can't empty"));
                String stringData = SerializeUtil.map2KVStr(params, new HashSet<String>() {{
                    add(PARAM_SIGNATURE);
                }});
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
                String stringData = SerializeUtil.map2KVStr(params, new HashSet<String>() {{
                    add(PARAM_SIGNATURE);
                }});
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
         * execute handler
         *
         * @param params
         * @param encoding
         * @param config
         * @param certService
         */
        static void execute(@NonNull Map params, String encoding,
                            @NonNull UnionpayConfig config,
                            @NonNull CertificateService certService) {
            String signMethod = String.valueOf(params.get(PARMA_SIGN_METHOD));

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
        void handle(Map params, String encoding, UnionpayConfig config, CertificateService certService);

    }

    /**
     * verify handler collection
     */
    @FunctionalInterface
    interface VerifyHandle {
        Logger LOGGER = LoggerFactory.getLogger(VerifyHandle.class);

        /**
         * RSA verify handler
         */
        VerifyHandle RSA = (params, encoding, config, certService) -> {

            // 1.从返回报文获取公钥
            String certStr = Optional.of(params.get(PARAM_SIGN_PUB_KEY_CERT))
                    .orElseThrow(() -> new RuntimeException("resp data can't find " +
                            PARAM_SIGN_PUB_KEY_CERT))
                    .toString();
            log.debug("==> RSA VerifyHandle [certStr]: {}", certStr);

            X509Certificate x509Cert = certService.genCertificateByStr(certStr);
            if (Objects.isNull(x509Cert)) {
                throw new RuntimeException("convert signPubKeyCert failed");
            }

            // 2.验证证书链，是否来自银联
            if (!certService.verifyCertificate(x509Cert)) {
                log.error("==> RSA VerifyHandle verify certificate failed, info: {}", certStr);
                throw new RuntimeException("verify certificate failed");
            }

            // 3.验签
            String respSignSeq = Optional.of(params.get(PARAM_SIGNATURE))
                    .orElseThrow(() -> new RuntimeException("resp data can't find " +
                            PARAM_SIGNATURE))
                    .toString();
            log.debug("==> RSA VerifyHandle [respSignSeq]: {}", respSignSeq);
            String targetSignSeq = SerializeUtil.map2KVStr(params, new HashSet<String>() {{
                add(PARAM_SIGNATURE);
            }});
            log.debug("==> RSA VerifyHandle [targetSignSeq]: {}", targetSignSeq);

            try {

                boolean res = SecurityUtil.validateSignBySoftSHA256(x509Cert.getPublicKey(),
                        SecurityUtil.base64Decode(respSignSeq.getBytes(encoding)),
                        SecurityUtil.sha256X16(targetSignSeq, encoding).getBytes(encoding));
                if (!res) {
                    throw new RuntimeException("RSA VerifyHandle final verify failed");
                }

            } catch (Exception e) {
                throw new RuntimeException("RSA VerifyHandle final verify error", e);
            }

            log.debug("==> RSA VerifyHandle [verify success]");

        };

        /**
         * SHA-256 verify handler
         */
        VerifyHandle SHA256 = (params, encoding, config, certService) -> {

            String respSignSeq = Optional.of(params.get(PARAM_SIGNATURE))
                    .orElseThrow(() -> new RuntimeException("resp data can't find " +
                            PARAM_SIGNATURE))
                    .toString();
            log.debug("==> SHA256 VerifyHandle [respSignSeq]: {}", respSignSeq);

            try {

                String secureKey = Optional.ofNullable(config.getSecureKey())
                        .orElseThrow(() -> new RuntimeException("secureKey can't empty"));
                String targetSignSeq = SerializeUtil.map2KVStr(params, new HashSet<String>() {{
                    add(PARAM_SIGNATURE);
                }});
                LOGGER.debug("==> SHA256 VerifyHandle [targetSignSeq]: {}", targetSignSeq);
                LOGGER.debug("==> SHA256 VerifyHandle [secureKey]: {}", secureKey);

                String strBeforeSha256 = targetSignSeq +
                        AMPERSAND + SecurityUtil.sha256X16(secureKey, encoding);
                String strAfterSha256 = SecurityUtil.sha256X16(strBeforeSha256, encoding);
                LOGGER.debug("==> SHA256 VerifyHandle [strAfterSha256]: {}", strAfterSha256);

                boolean res = respSignSeq.equalsIgnoreCase(strAfterSha256);
                if (!res) {
                    throw new RuntimeException("SHA256 VerifyHandle final verify failed");
                }

            } catch (Exception e) {
                throw new RuntimeException("SHA256 VerifyHandle final verify error", e);
            }

            log.debug("==> SHA256 VerifyHandle [verify success]");

        };

        /**
         * SM3 verify handler
         */
        VerifyHandle SM3 = (params, encoding, config, certService) -> {

            String respSignSeq = Optional.of(params.get(PARAM_SIGNATURE))
                    .orElseThrow(() -> new RuntimeException("resp data can't find " +
                            PARAM_SIGNATURE))
                    .toString();
            log.debug("==> SM3 VerifyHandle [respSignSeq]: {}", respSignSeq);

            try {

                String secureKey = Optional.ofNullable(config.getSecureKey())
                        .orElseThrow(() -> new RuntimeException("secureKey can't empty"));
                String targetSignSeq = SerializeUtil.map2KVStr(params, new HashSet<String>() {{
                    add(PARAM_SIGNATURE);
                }});
                LOGGER.debug("==> SM3 VerifyHandle [targetSignSeq]: {}", targetSignSeq);
                LOGGER.debug("==> SM3 VerifyHandle [secureKey]: {}", secureKey);

                String strBeforeSM3 = targetSignSeq + AMPERSAND +
                        SecurityUtil.sm3X16(secureKey, encoding);
                String strAfterSM3 = SecurityUtil.sm3X16(strBeforeSM3, encoding);
                LOGGER.debug("==> SM3 VerifyHandle [strAfterSM3]: {}", strAfterSM3);

                boolean res = respSignSeq.equalsIgnoreCase(strAfterSM3);
                if (!res) {
                    throw new RuntimeException("SM3 VerifyHandle final verify failed");
                }

            } catch (Exception e) {
                throw new RuntimeException("SM3 VerifyHandle final verify error", e);
            }

            log.debug("==> SM3 VerifyHandle [verify success]");

        };

        /**
         * execute handler
         *
         * @param params
         * @param encoding
         * @param config
         * @param certService
         */
        static void execute(@NonNull Map params, String encoding,
                            @NonNull UnionpayConfig config,
                            @NonNull CertificateService certService) {
            String signMethod = String.valueOf(params.get(PARMA_SIGN_METHOD));

            VerifyHandle targetHandler;
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
                    throw new RuntimeException("verify handler can't find");
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
        void handle(Map params, String encoding, UnionpayConfig config, CertificateService certService);

    }
}
