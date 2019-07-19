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
    public static final String PARAM_SIGN_PUB_KEY_CERT = "signPubKeyCert";

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
    public final static String DEFAULT_CHARSET_NAME = "ISO-8859-1";
    public static final String UNIONPAY_CNNAME = "中国银联股份有限公司";

    /**
     * all api url
     */
    public class Api {
        /**
         * product: online gateway pay
         */
        public final static String URL_GATEWAY_CONSUME = "/gateway/api/frontTransReq.do";
        public final static String URL_GATEWAY_CANCEL_CONSUME = "/gateway/api/backTransReq.do";
        public final static String URL_GATEWAY_BACK_CONSUME = "/gateway/api/backTransReq.do";
        public final static String URL_GATEWAY_TRANSACTION_STATUS_QUERY = "/gateway/api/queryTrans.do";
        public final static String URL_GATEWAY_ENCRYPT_INFO_UPDATE = "/gateway/api/backTransReq.do";
        public final static String URL_GATEWAY_PRE_AUTH = "/gateway/api/frontTransReq.do";
        public final static String URL_GATEWAY_CANCEL_PRE_AUTH = "/gateway/api/backTransReq.do";
        public final static String URL_GATEWAY_COMPLETE_PRE_AUTH = "/gateway/api/backTransReq.do";
        public final static String URL_GATEWAY_CANCEL_COMPLETE_PRE_AUTH = "/gateway/api/backTransReq.do";
    }
}
