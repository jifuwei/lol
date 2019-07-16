package com.github.lol.pay.component.unionpay.core;

import lombok.Data;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.*;

/**
 * unionpay config
 *
 * @author: jifuwei
 * @create: 2019-07-12 15:06
 **/
@Data
public class UnionpayConfig {

    /**
     * 编码
     */
    private String encoding;

    /**
     * 版本
     */
    private String version;

    /**
     * api domain
     */
    private String domain;

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

    public UnionpayConfig() {
        this.encoding = UTF_8_ENCODING;
        this.version = VERSION_5_1_0;
        this.domain = "https://gateway.test.95516.com";
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
    }
}
