package com.github.lol.pay.component.unionpay.product;

import com.github.lol.lib.util.ReflectUtil;
import com.github.lol.lib.util.SerializeUtil;
import com.github.lol.lib.util.http.HttpNetUtil;
import com.github.lol.pay.component.unionpay.UnionpayCertHolder;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.UnionpaySignHolder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;

/**
 * abstract unionpay gateway service
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:18
 **/
@Getter
@Setter
public abstract class AbstractUnionpayProductService {

    private UnionpayConfig config;
    private UnionpaySignHolder signService;
    private UnionpayCertHolder certService;

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

    protected void init(@NonNull UnionpayConfig config) {
        // 1.配置
        this.setConfig(config);
        // 2.认证配置
        this.setCertService(UnionpayCertHolder.of(config));
        // 3.签名配置
        this.setSignService(UnionpaySignHolder.of(config, this.getCertService()));
    }

    /**
     * post to server
     *
     * @param reqObject
     * @param url
     * @param returnClazz
     * @param <T>
     * @return
     */
    protected <T> T post(@NonNull Object reqObject, @NonNull String url,
                         @NonNull Class<T> returnClazz) {
        return send(reqObject, url, HttpNetUtil.HTTP_METHOD_POST, returnClazz);
    }

    /**
     * form builder
     *
     * @param reqObject
     * @param url
     * @return
     */
    protected FormReq form(@NonNull Object reqObject, @NonNull String url) {
        ReflectUtil.validateNotNullField(reqObject);

        Map<String, String> dataMap = SerializeUtil.objectToMapNullRemove(reqObject,
                String.class, String.class);

        this.getSignService().sign(dataMap, this.getConfig().getEncoding());

        return FormReq.builder()
                .actionUrl(url).encoding(this.getConfig().getEncoding()).inputDataMap(dataMap)
                .build();
    }

    /**
     * send data to server
     *
     * @param reqObject
     * @param url
     * @param reqMethod
     * @param returnClazz
     * @param <T>
     * @return
     */
    private <T> T send(@NonNull Object reqObject, @NonNull String url,
                       @NonNull String reqMethod,
                       @NonNull Class<T> returnClazz) {
        // 0.数据校验
        ReflectUtil.validateNotNullField(reqObject);

        // 1.序列化
        Map<String, Object> dataMap = SerializeUtil.objectToMapNullRemove(reqObject,
                String.class, Object.class);

        // 2.签名，注意-会在map中增加签名字段
        this.getSignService().sign(dataMap, this.getConfig().getEncoding());

        // 3.正式请求
        Map<String, String> respMap = this.buildNetUtil(url, reqMethod).post(dataMap);

        // 4.验签
        this.getSignService().validate(respMap, this.getConfig().getEncoding());

        // 5.序列化
        return SerializeUtil.mapToObject(respMap, returnClazz);
    }

    /**
     * create net util
     *
     * @param url
     * @param reqMethod
     * @return
     */
    private HttpNetUtil buildNetUtil(@NonNull String url, @NonNull String reqMethod) {
        return new HttpNetUtil(url, reqMethod, config.getEncoding(),
                config.getConnectTimeout(), config.getReadTimeout(), false, config.getVerifyCertEnable());
    }
}
