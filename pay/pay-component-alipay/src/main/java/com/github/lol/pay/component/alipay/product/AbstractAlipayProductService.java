package com.github.lol.pay.component.alipay.product;

import com.github.lol.pay.component.alipay.AlipayConfig;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * abstract unionpay gateway service
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:18
 **/
@Getter
@Setter
public abstract class AbstractAlipayProductService {

    private AlipayCoreService alipayCoreService;

    /**
     * define product name
     *
     * @return
     */
    protected abstract String productName();

    /**
     * define product id
     *
     * @return
     */
    protected abstract String productId();

    protected void init(@NonNull AlipayConfig config) {
        alipayCoreService = new AlipayCoreService(config);
    }
}
