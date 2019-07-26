package com.github.lol.pay.component.alipay.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.product.model.AlipayCoreReq;
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

    private SerializeConfig serializeConfig;
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
        // 1.序列化对象
        // config要做singleton处理，要不然会存在性能问题
        serializeConfig = new SerializeConfig();
        // 驼峰转下划线
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;

        // 2.alipay核心api
        alipayCoreService = new AlipayCoreService(config);
    }

    protected AlipayCoreReq pkgAlipayCoreReq(String methodName, Object bizContentSource) {
        return AlipayCoreReq.builder()
                .productId(productId())
                .methodName(methodName)
                .bizContent(JSON.toJSONString(bizContentSource, serializeConfig))
                .build();
    }
}
