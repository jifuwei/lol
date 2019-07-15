package com.github.lol.pay.component.unionpay.product;

import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * abstract unionpay gateway service
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:18
 **/
public abstract class AbstractUnionpayProductService {

    @Getter
    @Setter
    private UnionpayConfig config;
}
