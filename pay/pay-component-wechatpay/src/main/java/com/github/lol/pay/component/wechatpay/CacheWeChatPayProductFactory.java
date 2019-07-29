package com.github.lol.pay.component.wechatpay;

import com.github.lol.pay.component.wechatpay.constant.WeChatPayProductEnum;
import com.github.lol.pay.core.CacheProductFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * cache alipay product factory
 *
 * @author: jifuwei
 * @create: 2019-07-24 11:21
 **/
@Slf4j
public class CacheWeChatPayProductFactory extends CacheProductFactory {

    private final static String DEFAULT_COMPONENT_NAME = "WeChatPayComponent";

    private WeChatPayConfig config;

    public CacheWeChatPayProductFactory(@NonNull WeChatPayConfig config) {
        this.config = config;
    }

    @Override
    protected Object buildClass(String productName) {
        switch (productName) {
            case "F2F":
                return null;
            default:
                throw new RuntimeException(String.format("[CacheWeChatPayProductFactory]: %s not support",
                        productName));
        }
    }

    @Override
    protected String validateProductName(String productName) {
        return WeChatPayProductEnum.valueOf(productName).name();
    }

    @Override
    protected String getComponentName() {
        return DEFAULT_COMPONENT_NAME;
    }
}
