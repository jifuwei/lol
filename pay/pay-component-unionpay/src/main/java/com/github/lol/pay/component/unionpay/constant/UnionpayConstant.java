package com.github.lol.pay.component.unionpay.constant;

/**
 * unionpay constant
 *
 * @author: jifuwei
 * @create: 2019-07-12 13:45
 **/
public class UnionpayConstant {

    /**
     * api params
     */
    public final static String PARMA_SIGN_METHOD = "signMethod";
    public final static String PARMA_VERSION = "version";
    public final static String PARMA_CERT_ID = "certId";
    public final static String PARAM_SIGNATURE = "signature";

    /**
     * api version
     */
    public final static String VERSION_1_0_0 = "1.0.0";
    public final static String VERSION_5_0_0 = "5.0.0";
    public final static String VERSION_5_0_1 = "5.0.1";
    public final static String VERSION_5_1_0 = "5.1.0";

    /**
     * sign method type
     */
    public final static String SIGN_METHOD_RSA = "01";
    public final static String SIGN_METHOD_SHA256 = "11";
    public final static String SIGN_METHOD_SM3 = "12";

    /**
     * member
     */
    public final static String EQUAL = "=";
    public final static String AMPERSAND = "&";

    /**
     * encoding
     */
    public final static String UTF_8_ENCODING = "UTF-8";

    /**
     * certificate config
     */
    public final static String DEFAULT_PROVIDER = "BC";
    public final static String DEFAULT_CERT_TYPE = "X.509";
    public final static String DEFAULT_ALGORITHM = "RSA";
}
