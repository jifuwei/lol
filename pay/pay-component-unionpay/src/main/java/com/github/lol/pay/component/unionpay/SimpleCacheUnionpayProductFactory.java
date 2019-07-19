package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.gateway.impl.UnionpayGatewayService;
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
public class SimpleCacheUnionpayProductFactory implements IUnionPayProductFactory {

    /**
     * check need cache
     */
    private Boolean classCacheEnabled = true;

    /**
     * cache map
     */
    private final ConcurrentMap<Class<?>, Object> productMap = new ConcurrentHashMap<>();

    private UnionpayConfig config;

    private SimpleCacheUnionpayProductFactory setConfig(@NonNull UnionpayConfig config) {
        this.config = config;
        return this;
    }

    /**
     * instance of UnionpayProductFactory holder
     */
    private static class SimpleCacheUnionpayProductFactoryHolder {
        private static SimpleCacheUnionpayProductFactory instance = new SimpleCacheUnionpayProductFactory();
    }

    /**
     * get UnionpayProductFactory.class instance
     *
     * @param config
     * @return
     */
    public static SimpleCacheUnionpayProductFactory getInstance(@NonNull UnionpayConfig config) {
        return SimpleCacheUnionpayProductFactoryHolder.instance.setConfig(config);
    }

    @Override
    public void setClassCacheEnabled(@NonNull Boolean classCacheEnabled) {
        this.classCacheEnabled = classCacheEnabled;
    }

    @Override
    public Boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }

    /**
     * product a product
     *
     * @param clazz 支持的产品实现类，请在com.github.lol.pay.component.unionpay.product下寻找对应的产品
     * @param <T>
     * @return
     */
    @Override
    public <T extends AbstractUnionpayProductService> T produce(@NonNull Class<T> clazz) {
        if (classCacheEnabled) {
            Object cached = productMap.get(clazz);
            if (!Objects.isNull(cached)) {
                log.debug("==> UnionpayProductFactory cached [Class]: {}", clazz.getName());
                return refreshCustomerConfig((T) cached);
            }

            cached = buildClass(clazz);
            productMap.put(clazz, cached);
            log.debug("==> UnionpayProductFactory first init [Class]: {}", clazz.getName());


            return (T) cached;
        } else {
            return refreshCustomerConfig((T) buildClass(clazz));
        }
    }

    /**
     * refresh Customer Config
     *
     * @param cached
     * @param <T>
     * @return
     */
    private <T extends AbstractUnionpayProductService> T refreshCustomerConfig(@NonNull T cached) {
        cached.setConfig(config);
        return cached;
    }

    private <T> Object buildClass(@NonNull Class<T> clazz) {
        if (UnionpayGatewayService.class.equals(clazz)) {
            return UnionpayGatewayService.of(config);
        } else {
            throw new RuntimeException(String.format("[Class]: %s not support now", clazz.getName()));
        }
    }
}
