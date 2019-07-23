package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.gateway.impl.UnionpayGatewayService;
import com.github.lol.pay.component.unionpay.product.qrcode.impl.UnionpayQRCodeService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * unionpay product provider
 *
 * @author: jifuwei
 * @create: 2019-07-15 15:07
 **/
@Slf4j
public class CacheUnionpayProductFactory implements IUnionPayProductFactory {

    /**
     * check need cache
     */
    private Boolean classCacheEnabled = true;

    /**
     * cache map
     */
    private final ConcurrentMap<String, Object> productMap = new ConcurrentHashMap<>();

    private UnionpayConfig config;

    public CacheUnionpayProductFactory(@NonNull UnionpayConfig config) {
        this.config = config;
    }

    public void setClassCacheEnabled(@NonNull Boolean classCacheEnabled) {
        this.classCacheEnabled = classCacheEnabled;
    }

    public Boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }

    /**
     * product a product
     *
     * @param productName 支持的产品 com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum
     * @return
     */
    @Override
    public Object produce(@NonNull String productName) {
        UnionpayProductEnum product = UnionpayProductEnum.valueOf(productName);

        if (!classCacheEnabled) {
            return buildClass(product);
        }

        Object cached = productMap.get(product.name());
        if (!Objects.isNull(cached)) {
            log.debug("==> UnionpayProductFactory cached [product]: {}", product.name());
            return cached;
        }

        cached = buildClass(product);
        productMap.put(product.name(), cached);
        log.debug("==> UnionpayProductFactory first init [product]: {}", product.name());


        return cached;
    }

    /**
     * create product impl
     *
     * @param product
     * @return
     */
    private Object buildClass(@NonNull UnionpayProductEnum product) {
        switch (product) {
            case GATEWAY:
                return UnionpayGatewayService.of(config);
            case QR_CODE:
                return UnionpayQRCodeService.of(config);
            default:
                throw new RuntimeException(String.format("[Unionpay Product]: %s not support", product.name()));
        }
    }
}
