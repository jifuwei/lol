package com.github.lol.pay.component.unionpay;

import com.github.lol.lib.util.ReflectUtil;
import com.github.lol.lib.util.StrUtil;
import com.github.lol.lib.util.ValidUtil;
import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.*;
import static sun.net.NetworkClient.DEFAULT_CONNECT_TIMEOUT;
import static sun.net.NetworkClient.DEFAULT_READ_TIMEOUT;

/**
 * unionpay config
 *
 * @author: jifuwei
 * @create: 2019-07-12 15:06
 **/
@Data
@Builder
public class UnionpayConfig {

    /**
     * 商户配置
     */
    // 商户ID
    @NotEmpty
    private String merId;
    // 接入类型: 0-商户直连接入, 1-收单机构接入, 2-平台商户接入
    @NotEmpty
    private String accessType;
    // 交易币种: 默认156 境内人民币
    private String currencyCode;

    /**
     * 安全配置
     */
    // api domain
    @NotEmpty
    private String domain;
    // 超时时间 默认 30000
    private Integer connectTimeout;
    // 读取超时时间 默认 30000
    private Integer readTimeout;
    // 是否校验证书 默认 false 生产环境建议校验
    private Boolean verifyCertEnable;

    // 编码 默认 UTF-8
    private String encoding;
    // 版本 默认 5.1.0
    private String version;
    // 对账文件地址
    @NotEmpty
    private String fileDownLoadUrl;
    // 签名方法 默认 01-RSA
    @NotEmpty
    private String signMethod;
    // 签名证书路径
    @NotEmpty
    private String signCertPath;
    // 签名证书密码
    @NotEmpty
    private String signCertPwd;
    // 签名证书类型
    @NotEmpty
    private String signCertType;
    // 中级证书路径
    @NotEmpty
    private String middleCertPath;
    // 根证书路径
    @NotEmpty
    private String rootCertPath;
    // 加密公钥证书路径
    @NotEmpty
    private String encryptCertPath;
    // 磁道加密公钥模数
    private String encryptTrackKeyModulus;
    // 磁道加密公钥指数
    private String encryptTrackKeyExponent;
    // 验证签名公钥证书目录
    private String validateCertDir;
    // 安全密钥(SHA256和SM3计算时使用)
    private String secureKey;
    // 是否验证验签证书CN 默认 false 生产建议打开
    private Boolean validateCNNameEnable;

    public UnionpayConfig(String merId, String accessType, String domain, String fileDownLoadUrl,
                          String signMethod, String signCertPath, String signCertPwd, String signCertType,
                          String middleCertPath, String rootCertPath, String encryptCertPath) {
        new UnionpayConfig(merId, accessType, null, domain,
                null, null, null, null, null,
                fileDownLoadUrl, signMethod, signCertPath, signCertPwd, signCertType, middleCertPath, rootCertPath,
                encryptCertPath, null, null,
                null, null, null);
    }

    public UnionpayConfig(String merId, String accessType, String currencyCode, String domain,
                          Integer connectTimeout, Integer readTimeout, Boolean verifyCertEnable, String encoding,
                          String version, String fileDownLoadUrl, String signMethod, String signCertPath,
                          String signCertPwd, String signCertType, String middleCertPath, String rootCertPath,
                          String encryptCertPath, String encryptTrackKeyModulus, String encryptTrackKeyExponent,
                          String validateCertDir, String secureKey, Boolean validateCNNameEnable) {
        this.merId = merId;
        this.accessType = accessType;
        this.currencyCode = currencyCode;
        this.domain = domain;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.verifyCertEnable = verifyCertEnable;
        this.encoding = encoding;
        this.version = version;
        this.fileDownLoadUrl = fileDownLoadUrl;
        this.signMethod = signMethod;
        this.signCertPath = signCertPath;
        this.signCertPwd = signCertPwd;
        this.signCertType = signCertType;
        this.middleCertPath = middleCertPath;
        this.rootCertPath = rootCertPath;
        this.encryptCertPath = encryptCertPath;
        this.encryptTrackKeyModulus = encryptTrackKeyModulus;
        this.encryptTrackKeyExponent = encryptTrackKeyExponent;
        this.validateCertDir = validateCertDir;
        this.secureKey = secureKey;
        this.validateCNNameEnable = validateCNNameEnable;

        init();
    }

    private void init() {
        ReflectUtil.validateNotNullField(this);

        ValidUtil.notEmpty(merId, "[UnionpayConfig] merId can't empty");
        ValidUtil.notEmpty(accessType, "[UnionpayConfig] accessType can't empty");
        currencyCode = StrUtil.isEmpty(currencyCode) ? DEFAULT_CURRENCY_CODE : currencyCode;

        ValidUtil.notEmpty(domain, "[UnionpayConfig] domain can't empty");
        ValidUtil.isTrue(domain.startsWith("http"), "[UnionpayConfig] illegal -> domain: %s", domain);

        connectTimeout = (Objects.isNull(connectTimeout) || connectTimeout <= 0) ?
                DEFAULT_CONNECT_TIMEOUT : connectTimeout;

        readTimeout = (Objects.isNull(readTimeout) || readTimeout <= 0) ?
                DEFAULT_READ_TIMEOUT : readTimeout;

        verifyCertEnable = Objects.isNull(verifyCertEnable) ? Boolean.FALSE : verifyCertEnable;
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;
        version = StrUtil.isEmpty(version) ? VERSION_5_1_0 : version;

        ValidUtil.notEmpty(fileDownLoadUrl, "[UnionpayConfig] fileDownLoadUrl can't empty");
        ValidUtil.isTrue(fileDownLoadUrl.startsWith("http"),
                "[UnionpayConfig] fileDownLoadUrl: %s illegal", fileDownLoadUrl);

        ValidUtil.notEmpty(signMethod, "[UnionpayConfig] signMethod can't empty");
        ValidUtil.isTrue(SIGN_METHOD_RSA.equals(signMethod)
                        || SIGN_METHOD_SHA256.equals(signMethod)
                        || SIGN_METHOD_SM3.equals(signMethod),
                "[UnionpayConfig] illegal param -> signMethod: %s", signMethod);

        ValidUtil.notEmpty(signCertPath, "[UnionpayConfig] signCertPath can't empty");
        ValidUtil.notEmpty(signCertPwd, "[UnionpayConfig] signCertPwd can't empty");
        ValidUtil.notEmpty(signCertType, "[UnionpayConfig] signCertType can't empty");
        ValidUtil.notEmpty(middleCertPath, "[UnionpayConfig] middleCertPath can't empty");
        ValidUtil.notEmpty(rootCertPath, "[UnionpayConfig] rootCertPath can't empty");
        ValidUtil.notEmpty(encryptCertPath, "[UnionpayConfig] encryptCertPath can't empty");

        if (SIGN_METHOD_SM3.equals(signMethod) || SIGN_METHOD_SHA256.equals(signMethod)) {
            ValidUtil.notEmpty(secureKey, "[UnionpayConfig] merId can't empty");
        }

        validateCNNameEnable = Objects.isNull(validateCNNameEnable) ? Boolean.FALSE : validateCNNameEnable;
    }
}
