package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.gateway.service.UnionpayGatewayService;
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
public class SimpleCacheUnionpayProductFactory implements IUnionPayProductFactory {
    /**
     * check need cache
     */
    private boolean classCacheEnabled = true;

    /**
     * cache map
     */
    private final ConcurrentMap<Class<?>, Object> productMap = new ConcurrentHashMap<>();

    private UnionpayConfig config;

    private SimpleCacheUnionpayProductFactory setConfig(UnionpayConfig config) {
        this.config = config;

        return this;
    }

    /**
     * instance of UnionpayProductFactory holder
     */
    private static class UnionpayProductFactoryHolder {
        private static SimpleCacheUnionpayProductFactory instance = new SimpleCacheUnionpayProductFactory();
    }

    /**
     * get UnionpayProductFactory.class instance
     *
     * @param config
     * @return
     */
    public static SimpleCacheUnionpayProductFactory getInstance(UnionpayConfig config) {
        return UnionpayProductFactoryHolder.instance.setConfig(config);
    }

    @Override
    public void setClassCacheEnabled(boolean classCacheEnabled) {
        this.classCacheEnabled = classCacheEnabled;
    }

    @Override
    public boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }

    @Override
    public <T extends AbstractUnionpayProductService> T produce(Class<T> clazz) {
        if (classCacheEnabled) {
            Object cached = productMap.get(clazz);
            if (!Objects.isNull(cached)) {
                log.debug("==> UnionpayProductFactory cached [Class]: {}", clazz.getName());
                return refreshCustomerConfig((T) cached);
            }

            cached = buildClass(clazz);
            productMap.put(clazz, cached);
            log.debug("==> UnionpayProductFactory first init [Class]: {}", clazz.getName());


            return refreshCustomerConfig((T) cached);
        } else {
            return refreshCustomerConfig((T) buildClass(clazz));
        }
    }

    private <T extends AbstractUnionpayProductService> T refreshCustomerConfig(T cached) {
        cached.setConfig(config);
        return cached;
    }

    private <T> Object buildClass(Class<T> clazz) {
        if (UnionpayGatewayService.class.equals(clazz)) {
            return UnionpayGatewayService.of(config);
        } else {
            throw new RuntimeException(String.format("[Class]: %s not support now", clazz.getName()));
        }
    }
}
