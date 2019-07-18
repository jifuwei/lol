package com.github.lol.pay.component.unionpay.product;

import com.github.lol.lib.util.http.HttpNetUtil;
import com.github.lol.pay.component.unionpay.core.CertificateService;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.core.UnionpaySignService;
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
public abstract class AbstractUnionpayProductService {

    private UnionpayConfig config;
    private UnionpaySignService signService;
    private CertificateService certService;

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

    /**
     * create net util
     *
     * @param url
     * @param reqMethod
     * @return
     */
    protected HttpNetUtil buildNetUtil(@NonNull String url, @NonNull String reqMethod) {
        return new HttpNetUtil(url, reqMethod, config.getEncoding(),
                config.getConnectTimeout(), config.getReadTimeout(), false, config.getVerifyCert());
    }
}
