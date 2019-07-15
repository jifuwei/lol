package com.github.lol.pay.component.unionpay;

import lombok.Data;

/**
 * unionpay config
 *
 * @author: jifuwei
 * @create: 2019-07-12 15:06
 **/
@Data
public class UnionpayConfig {

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
}
