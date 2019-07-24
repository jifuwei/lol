package com.github.lol.pay.component.alipay.product.f2f.impl;

import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.CacheAlipayProductFactory;
import com.github.lol.pay.component.alipay.IAlipayF2FClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.pay.component.core.IProductFactory;
import org.junit.Before;
import org.junit.Test;

public class AlipayF2FServiceTest {

    private AlipayConfig config = new AlipayConfig("https://openapi.alipay.com/gateway.do",
            "APP_ID", "APP_PRIVATE_KEY", "json", "CHARSET",
            "ALIPAY_PUBLIC_KEY", "RSA2");

    private IAlipayF2FClient f2FClient;

    @Before
    public void before() {
        IProductFactory alipayPayProductFactory = new CacheAlipayProductFactory(config);
        f2FClient = (IAlipayF2FClient) alipayPayProductFactory.produce(AlipayProductEnum.F2F.name());
    }

    @Test
    public void pay() {
    }

    @Test
    public void query() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void create() {
    }

    @Test
    public void refund() {
    }

    @Test
    public void preCreate() {
    }

    @Test
    public void close() {
    }

    @Test
    public void billDownloadurlQuery() {
    }

    @Test
    public void monitorHeartbeatSyn() {
    }
}
