package com.github.lol.pay.component.wechatpay.product.f2f;

import com.alibaba.fastjson.JSON;
import com.github.lol.pay.component.wechatpay.IWeChatPayF2FClient;
import com.github.lol.pay.component.wechatpay.WeChatPayConfig;
import com.github.lol.pay.component.wechatpay.constant.WeChatPayProductEnum;
import com.github.lol.pay.component.wechatpay.product.AbstractWeChatPayProductService;
import com.github.lol.pay.component.wechatpay.product.model.MicroPayReq;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * alipay F2F service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
@Slf4j
public class WeChatPayF2FService extends AbstractWeChatPayProductService implements IWeChatPayF2FClient {

    public WeChatPayF2FService(@NonNull WeChatPayConfig config) {
        init(config);
    }

    public static WeChatPayF2FService of(@NonNull WeChatPayConfig config) {
        return new WeChatPayF2FService(config);
    }

    @Override
    @SneakyThrows
    public Map<String, String> microPay(MicroPayReq microPayReq) {
        return this.getWxPay().microPay(JSON.parseObject(JSON.toJSONString(microPayReq, this.getSerializeConfig()), Map.class));
    }

    @Override
    protected String productName() {
        return WeChatPayProductEnum.F2F.getDesc();
    }

    @Override
    protected String productId() {
        return WeChatPayProductEnum.F2F.name();
    }
}
