package com.github.lol.pay.component.unionpay;

/**
 * unionpay product factory
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:04
 **/
public interface IUnionPayProductFactory {

    /**
     * check cache enable
     *
     * @return
     */
    Boolean isClassCacheEnabled();

    /**
     * set cache config
     *
     * @param classCacheEnabled
     */
    void setClassCacheEnabled(Boolean classCacheEnabled);

    /**
     * produce a product
     *
     * @param productName
     * @return
     */
    Object produce(String productName);
}
