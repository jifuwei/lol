package com.github.lol.pay.component.unionpay;

/**
 * unionpay product factory
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:04
 **/
public interface IUnionPayProductFactory {

    /**
     * produce a product
     *
     * @param productName
     * @return
     */
    Object produce(String productName);
}
