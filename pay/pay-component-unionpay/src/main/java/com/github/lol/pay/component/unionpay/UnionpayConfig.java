package com.github.lol.pay.component.unionpay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.*;

/**
 * unionpay config
 *
 * @author: jifuwei
 * @create: 2019-07-12 15:06
 **/
@Data
@AllArgsConstructor
@Builder
public class UnionpayConfig {

    /**
     * api请求超时设置
     */
    private Integer connectTimeout;

    /**
     * api请求读取设置
     */
    private Integer readTimeout;

    /**
     * api请求是否校验证书
     * 建议生产环境校验
     */
    private Boolean verifyCert;

    /**
     * 编码
     */
    private String encoding;

    /**
     * 版本
     */
    private String version;

    /**
     * 商户ID
     */
    private String merId;

    /**
     * 接入类型
     */
    private String accessType;

    /**
     * 交易币种
     */
    private String currencyCode;

    /**
     * api domain
     */
    private String domain;

    /**
     * 对账文件地址
     */
    private String fileDownLoadUrl;

    /**
     * 签名方法
     */
    private String signMethod;

    /**
     * 签名证书路径.
     */
    private String signCertPath;

    /**
     * 签名证书密码.
     */
    private String signCertPwd;

    /**
     * 签名证书类型.
     */
    private String signCertType;

    /**
     * 中级证书路径
     */
    private String middleCertPath;

    /**
     * 根证书路径
     */
    private String rootCertPath;

    /**
     * 加密公钥证书路径.
     */
    private String encryptCertPath;

    /**
     * 磁道加密公钥模数.
     */
    private String encryptTrackKeyModulus;

    /**
     * 磁道加密公钥指数.
     */
    private String encryptTrackKeyExponent;

    /**
     * 验证签名公钥证书目录.
     */
    private String validateCertDir;

    /**
     * 安全密钥(SHA256和SM3计算时使用)
     */
    private String secureKey;

    /**
     * 是否验证验签证书CN，除了false都验
     */
    private boolean ifValidateCNName = true;

    /**
     * url管理
     */
    private Map<String, String> urlMap;

    // TODO: only for test
    public UnionpayConfig() {
//        this.connectTimeout = 30000;
//        this.readTimeout = 30000;
//        this.verifyCert = false;
        this.encoding = UTF_8_ENCODING;
        this.version = VERSION_5_1_0;
        this.domain = "https://gateway.test.95516.com";
        this.merId = "777290058171299";
        this.accessType = "0";
        this.currencyCode = "156";
        this.signMethod = SIGN_METHOD_RSA;
        this.signCertPath = "/Users/jifuwei/acp_test_sign.pfx";
        this.signCertPwd = "000000";
        this.signCertType = "PKCS12";
        this.middleCertPath = "/Users/jifuwei/acp_test_middle.cer";
        this.rootCertPath = "/Users/jifuwei/acp_test_root.cer";
        this.encryptCertPath = "/Users/jifuwei/acp_test_enc.cer";
        this.encryptTrackKeyModulus = null;
        this.encryptTrackKeyExponent = null;
        this.validateCertDir = null;
        this.secureKey = null;
        // 测试环境只能是false 生产环境必须是true
        this.ifValidateCNName = false;
        this.fileDownLoadUrl = "https://filedownload.test.95516.com/";
    }
}
