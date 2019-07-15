package com.github.lol.pay.component.unionpay.util;

import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.*;

/**
 * sign util
 *
 * @author: jifuwei
 * @create: 2019-07-12 11:55
 **/
@Slf4j
public class SignUtil {

    private UnionpayConfig config;

    public SignUtil(UnionpayConfig config) {
        this.config = config;
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
        Validate.isTrue(VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version),
                "illegal version: " + version);

        Optional.ofNullable(EncryptHandle.HANDLER_MAP.get(signMethod))
                .orElseThrow(() -> new RuntimeException("encrypt handler can't find"))
                .handle(params, encoding, config);
    }


    /**
     * diff encrypt method collection
     */
    interface EncryptHandle {
        Logger LOGGER = LoggerFactory.getLogger(EncryptHandle.class);

        /**
         * handler map
         */
        Map<String, EncryptHandle> HANDLER_MAP = new HashMap<String, EncryptHandle>() {{
            put(SIGN_METHOD_RSA, RSA);
            put(SIGN_METHOD_SHA256, SHA256);
            put(SIGN_METHOD_SM3, SM3);
        }};

        /**
         * sign data & verify cert
         *
         * @param params
         * @param encoding
         * @param config
         */
        void handle(Map<String, String> params, String encoding, UnionpayConfig config);

        /**
         * RSA encrypt handler
         * api version must VERSION_5_0_1 or VERSION_1_0_0
         */
        EncryptHandle RSA = (params, encoding, config) -> {

            try {
                String stringData = coverMap2String(params);
                LOGGER.debug("==> RSA EncryptHandle [stringData]: {}", stringData);
                byte[] signDigest = SecurityUtil.sha1X16(stringData, encoding);
                LOGGER.debug("==> RSA EncryptHandle [signDigest]: {}", new String(signDigest));

                byte[] byteSign = SecurityUtil.base64Encode(SecurityUtil
                        .signBySoft(CertificateUtil.getSignCertPrivateKey(), signDigest));
                String stringSign = new String(byteSign);
                LOGGER.debug("==> RSA EncryptHandle [stringSign]: {}", stringSign);

                params.put(PARMA_CERT_ID, CertificateUtil.getSignCertId());
                params.put(PARAM_SIGNATURE, stringSign);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                throw new RuntimeException("RSA EncryptHandle error", e);
            }
        };

        /**
         * SHA-256 encrypt handler
         */
        EncryptHandle SHA256 = (params, encoding, config) -> {

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
        EncryptHandle SM3 = (params, encoding, config) -> {

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
