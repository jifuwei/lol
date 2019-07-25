package com.github.lol.pay.component.alipay.product;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
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
    private SerializeConfig serializeConfig;

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
        // 1.alipay client init
        alipayClient = new DefaultAlipayClient(config.getApiGatewayUrl(), config.getAppId(),
                config.getAppPrivateKey(), config.getParamFormat(), config.getEncoding(),
                config.getAlipayPublicKey(), config.getSignType());

        // 2.序列化对象
        // config要做singleton处理，要不然会存在性能问题
        serializeConfig = new SerializeConfig();
        // 驼峰转下划线
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }
}
