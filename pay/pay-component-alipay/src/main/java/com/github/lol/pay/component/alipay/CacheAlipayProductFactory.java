package com.github.lol.pay.component.alipay;

import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.app.AlipayAppService;
import com.github.lol.pay.component.alipay.product.f2f.AlipayF2FService;
import com.github.lol.pay.component.alipay.product.pc.AlipayPcService;
import com.github.lol.pay.component.alipay.product.wap.AlipayWapService;
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
public class CacheAlipayProductFactory extends CacheProductFactory {

    private final static String DEFAULT_COMPONENT_NAME = "AlipayComponent";

    private AlipayConfig config;

    public CacheAlipayProductFactory(@NonNull AlipayConfig config) {
        this.config = config;
    }

    @Override
    protected Object buildClass(String productName) {
        switch (productName) {
            case "F2F":
                return AlipayF2FService.of(config);
            case "APP":
                return AlipayAppService.of(config);
            case "WAP":
                return AlipayWapService.of(config);
            case "PC":
                return AlipayPcService.of(config);
            default:
                throw new RuntimeException(String.format("[CacheAlipayProductFactory]: %s not support",
                        productName));
        }
    }

    @Override
    protected String validateProductName(String productName) {
        return AlipayProductEnum.valueOf(productName).name();
    }

    @Override
    protected String getComponentName() {
        return DEFAULT_COMPONENT_NAME;
    }
}
