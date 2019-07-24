package com.github.lol.pay.component.alipay.product;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
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

    private AlipayClient alipayClient;

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
        // alipay client init
        alipayClient = new DefaultAlipayClient(config.getApiGatewayUrl(), config.getAppId(),
                config.getAppPrivateKey(), config.getParamFormat(), config.getEncoding(),
                config.getAlipayPublicKey(), config.getSignType());
    }
}
