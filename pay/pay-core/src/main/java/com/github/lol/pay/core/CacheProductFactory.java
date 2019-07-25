package com.github.lol.pay.core;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * cache product factory
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:03
 **/
@Slf4j
public abstract class CacheProductFactory implements IProductFactory {

    /**
     * cache map
     */
    private final ConcurrentMap<String, Object> productMap = new ConcurrentHashMap<>();

    /**
     * check need cache
     */
    private Boolean classCacheEnabled = true;

    public void setClassCacheEnabled(@NonNull Boolean classCacheEnabled) {
        this.classCacheEnabled = classCacheEnabled;
    }

    public Boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }


    protected abstract Object buildClass(String productName);

    protected abstract String validateProductName(String productName);

    protected abstract String getComponentName();

    /**
     * produce a cached product instance
     * <p>
     * 注意：cacheKey 必须保证唯一
     *
     * @param productName 支持产品枚举，请到相应的支付模块获取 **ProductEnum.class
     * @return
     */
    @Override
    public Object produce(String productName) {
        productName = validateProductName(productName);
        String componentName = getComponentName();

        if (!classCacheEnabled) {
            return buildClass(productName);
        }

        String cacheKey = componentName + "_" + productName;
        Object cached = productMap.get(cacheKey);
        if (!Objects.isNull(cached)) {
            log.debug("==> CacheProductFactory cached [component]: {} [product]: {}", componentName, productName);
            return cached;
        }

        cached = buildClass(productName);
        productMap.put(cacheKey, cached);
        log.debug("==> CacheProductFactory first init [component]: {} [product]: {} [cacheKey]: {}",
                componentName, productName, cacheKey);


        return cached;
    }
}
