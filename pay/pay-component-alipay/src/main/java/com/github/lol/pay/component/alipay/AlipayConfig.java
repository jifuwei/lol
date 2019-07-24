package com.github.lol.pay.component.alipay;

import com.github.lol.lib.util.ReflectUtil;
import com.github.lol.lib.util.ValidUtil;
import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * alipay config
 *
 * @author: jifuwei
 * @create: 2019-07-24 11:27
 **/
@Data
@Builder
public class AlipayConfig {

    public final static String ENCODING_UTF_8 = "UTF-8";
    public final static String ENCODING_GBK = "GBK";

    public final static String SIGN_TYPE_RSA = "RSA";
    public final static String SIGN_TYPE_RSA2 = "RSA2";

    // 支付宝网关路由 固定
    @NotEmpty
    private String apiGatewayUrl;
    // 支付宝后台即创建应用后生成
    @NotEmpty
    private String appId;
    // 商户私钥 自生成
    @NotEmpty
    private String appPrivateKey;
    // 参数返回格式 固定 只支持json
    @NotEmpty
    private String paramFormat;
    // 请求和签名使用的字符串编码格式，支持GBK和UTF-8
    @NotEmpty
    private String encoding;
    // 支付宝公钥，由支付宝生成
    @NotEmpty
    private String alipayPublicKey;
    // 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
    @NotEmpty
    private String signType;

    public AlipayConfig(String apiGatewayUrl, String appId, String appPrivateKey, String paramFormat,
                        String encoding, String alipayPublicKey, String signType) {
        this.apiGatewayUrl = apiGatewayUrl;
        this.appId = appId;
        this.appPrivateKey = appPrivateKey;
        this.paramFormat = paramFormat;
        this.encoding = encoding;
        this.alipayPublicKey = alipayPublicKey;
        this.signType = signType;

        init();
    }

    private void init() {
        ReflectUtil.validateNotNullField(this);

        ValidUtil.notEmpty(apiGatewayUrl, "[AlipayConfig] apiGatewayUrl can't empty");
        ValidUtil.isTrue(apiGatewayUrl.startsWith("http"),
                "[AlipayConfig] illegal -> apiGatewayUrl: %s", apiGatewayUrl);

        ValidUtil.isTrue(ENCODING_UTF_8.equals(encoding) || ENCODING_GBK.equals(encoding),
                "[AlipayConfig] illegal -> encoding: %s", encoding);

        ValidUtil.isTrue(SIGN_TYPE_RSA.equals(signType) || SIGN_TYPE_RSA2.equals(signType),
                "[AlipayConfig] illegal -> signType: %s", signType);
    }
}
