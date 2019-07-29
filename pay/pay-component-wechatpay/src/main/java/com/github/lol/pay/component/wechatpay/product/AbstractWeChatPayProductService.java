package com.github.lol.pay.component.wechatpay.product;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.github.lol.pay.component.wechatpay.WeChatPayConfig;
import com.github.lol.pay.component.wechatpay.internal.WXPay;
import com.github.lol.pay.component.wechatpay.internal.WXPayConfig;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.SneakyThrows;

/**
 * abstract WeChatPay gateway service
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:18
 **/
@Getter
@Setter
public abstract class AbstractWeChatPayProductService {

    private WXPayConfig wxPayConfig;
    private WXPay wxPay;
    private SerializeConfig serializeConfig;

    /**
     * define product name
     *
     * @return
     */
    protected abstract String productName();

    /**
     * define product id
     *
     * @return
     */
    protected abstract String productId();

    @SneakyThrows
    protected void init(@NonNull WeChatPayConfig config) {
        this.wxPayConfig = config;
        wxPay = new WXPay(wxPayConfig, config.getNotifyUrl(), config.getAutoReport(), config.getUseSandbox());

        // config要做singleton处理，要不然会存在性能问题
        serializeConfig = new SerializeConfig();
        // 驼峰转下划线
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }
}
