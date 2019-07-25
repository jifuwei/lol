package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.gateway.impl.UnionpayGatewayService;
import com.github.lol.pay.component.unionpay.product.qrcode.impl.UnionpayQRCodeService;
import com.github.lol.pay.core.CacheProductFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * unionpay product provider
 *
 * @author: jifuwei
 * @create: 2019-07-15 15:07
 **/
@Slf4j
public class CacheUnionpayProductFactory extends CacheProductFactory {

    private final static String DEFAULT_COMPONENT_NAME = "UnionpayComponent";

    private UnionpayConfig config;

    public CacheUnionpayProductFactory(@NonNull UnionpayConfig config) {
        this.config = config;
    }

    @Override
    protected Object buildClass(@NonNull String productName) {
        switch (productName) {
            case "GATEWAY":
                return UnionpayGatewayService.of(config);
            case "QR_CODE":
                return UnionpayQRCodeService.of(config);
            default:
                throw new RuntimeException(String.format("[Unionpay Product]: %s not support", productName));
        }
    }

    @Override
    protected String validateProductName(String productName) {
        return UnionpayProductEnum.valueOf(productName).name();
    }

    @Override
    protected String getComponentName() {
        return DEFAULT_COMPONENT_NAME;
    }
}
