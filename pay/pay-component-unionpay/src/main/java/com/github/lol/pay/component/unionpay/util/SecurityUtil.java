package com.github.lol.pay.component.unionpay.util;

import com.github.lol.lib.util.StrUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.digests.SM3Digest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.DEFAULT_PROVIDER;
import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.UTF_8_ENCODING;

/**
 * secure util
 *
 * @author: jifuwei
 * @create: 2019-07-12 16:57
 **/
public class SecurityUtil {

    /**
     * 算法常量： SHA1
     */
    private static final String ALGORITHM_SHA1 = "SHA-1";

    /**
     * 算法常量： SHA256
     */
    private static final String ALGORITHM_SHA256 = "SHA-256";

    /**
     * 算法常量：SHA1withRSA
     */
    private static final String BC_PROV_ALGORITHM_SHA1RSA = "SHA1withRSA";

    /**
     * 算法常量：SHA256withRSA
     */
    private static final String BC_PROV_ALGORITHM_SHA256RSA = "SHA256withRSA";


    /**
     * SHA-1 then to hex str
     *
     * @param data
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String sha1X16(@NonNull String data, String encoding)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;

        byte[] bytes = sha(data.getBytes(encoding), ALGORITHM_SHA1);
        return X16(bytes, encoding);
    }

    /**
     * SHA-256 then to hex str
     *
     * @param data
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String sha256X16(@NonNull String data, String encoding)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;

        byte[] bytes = sha(data.getBytes(encoding), ALGORITHM_SHA256);
        return X16(bytes, encoding);
    }

    /**
     * SM3 then to hex str
     *
     * @param data
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sm3X16(@NonNull String data, String encoding)
            throws UnsupportedEncodingException {
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;

        byte[] bytes = sm3(data.getBytes(encoding));
        return X16(bytes, encoding);
    }

    /**
     * sm3 encrypt
     *
     * @param data
     * @return
     */
    private static byte[] sm3(@NonNull byte[] data) {
        SM3Digest sm3 = new SM3Digest();
        sm3.update(data, 0, data.length);
        byte[] result = new byte[sm3.getDigestSize()];
        sm3.doFinal(result, 0);

        return result;
    }

    /**
     * sha encrypt by algorithm
     *
     * @param data
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] sha(@NonNull byte[] data, @NonNull String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.reset();
        md.update(data);

        return md.digest();
    }

    /**
     * to hex string
     *
     * @param bytes
     * @param encoding
     * @return
     */
    private static String X16(@NonNull byte[] bytes, String encoding) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                sb.append("0").append(Integer.toHexString(0xFF & b));
            } else {
                sb.append(Integer.toHexString(0xFF & b));
            }
        }

        return sb.toString();
    }

    public static byte[] signBySoftSHA1(@NonNull PrivateKey signCertPrivateKey,
                                        @NonNull byte[] signDigest) {
        return signBySoft(signCertPrivateKey, signDigest, BC_PROV_ALGORITHM_SHA1RSA);
    }

    public static byte[] signBySoftSHA256(@NonNull PrivateKey signCertPrivateKey,
                                          @NonNull byte[] signDigest) {
        return signBySoft(signCertPrivateKey, signDigest, BC_PROV_ALGORITHM_SHA256RSA);
    }

    public static boolean validateSignBySoftSHA256(@NonNull PublicKey publicKey,
                                                   @NonNull byte[] signData,
                                                   @NonNull byte[] srcData) throws Exception {
        Signature st = Signature.getInstance(BC_PROV_ALGORITHM_SHA256RSA, DEFAULT_PROVIDER);
        st.initVerify(publicKey);
        st.update(srcData);
        return st.verify(signData);
    }

    @SneakyThrows
    private static byte[] signBySoft(@NonNull PrivateKey signCertPrivateKey,
                                     @NonNull byte[] signDigest,
                                     @NonNull String algorithm) {
        Signature st = Signature.getInstance(algorithm, DEFAULT_PROVIDER);
        st.initSign(signCertPrivateKey);
        st.update(signDigest);
        return st.sign();
    }

    public static byte[] base64Encode(@NonNull byte[] signBySoft) {
        return Base64.encodeBase64(signBySoft);
    }

    public static byte[] base64Decode(@NonNull byte[] inputByte) throws IOException {
        return Base64.decodeBase64(inputByte);
    }
}
