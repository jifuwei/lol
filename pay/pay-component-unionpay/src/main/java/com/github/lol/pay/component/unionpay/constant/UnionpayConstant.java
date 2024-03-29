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
    public final static String PARAM_SIGN_METHOD = "signMethod";
    public final static String PARAM_VERSION = "version";
    public final static String PARAM_CERT_ID = "certId";
    public final static String PARAM_SIGNATURE = "signature";
    public static final String PARAM_SIGN_PUB_KEY_CERT = "signPubKeyCert";
    public static final String PARAM_MER_ID = "merId";
    public static final String PARAM_ACCESS_TYPE = "accessType";
    public static final String PARAM_DOMAIN = "domain";
    public static final String PARAM_FILE_DOWNLOAD_URL = "fileDownLoadUrl";
    public static final String PARAM_SIGN_CERT_PATH = "signCertPath";
    public static final String PARAM_SIGN_CERT_PWD = "signCertPwd";
    public static final String PARAM_SIGN_CERT_TYPE = "signCertType";
    public static final String PARAM_MIDDLE_CERT_PATH = "middleCertPath";
    public static final String PARAM_ROOT_CERT_PATH = "rootCertPath";
    public static final String PARAM_ENCRYPT_CERT_PATH = "encryptCertPath";

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
     * currency code
     */
    public final static String DEFAULT_CURRENCY_CODE = "156";

    /**
     * certificate config
     */
    public final static String DEFAULT_PROVIDER = "BC";
    public final static String DEFAULT_CERT_TYPE = "X.509";
    public final static String DEFAULT_ALGORITHM = "RSA";
    public final static String DEFAULT_CHARSET_NAME = "ISO-8859-1";
    public static final String UNIONPAY_CNNAME = "中国银联股份有限公司";

    /**
     * date time format
     */
    public final static String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * all api url
     */
    public class Api {

        // 前台交易请求地址
        public final static String URL_FRONT_TRANSFER = "/gateway/api/frontTransReq.do";
        // 后台交易请求地址(无卡交易配置该地址)
        public final static String URL_BACK_TRANSFER_WITHOUT_CARD = "/gateway/api/backTransReq.do";
        // 单笔查询请求地址
        public final static String URL_QUERY_SINGLE = "/gateway/api/queryTrans.do";

    }
}
