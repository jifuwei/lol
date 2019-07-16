package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.service.UnionpayGatewayService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleCacheUnionpayProductFactoryTest {

    @Test
    public void getInstance() {
        SimpleCacheUnionpayProductFactory first = SimpleCacheUnionpayProductFactory.getInstance(new UnionpayConfig());
        SimpleCacheUnionpayProductFactory second = SimpleCacheUnionpayProductFactory.getInstance(new UnionpayConfig());
        assertEquals(first, second);
    }

    @Test
    public void produce() {
        SimpleCacheUnionpayProductFactory factory = SimpleCacheUnionpayProductFactory.getInstance(new UnionpayConfig());
        IUnionGatewayClient unionGatewayClient1 = factory.produce(UnionpayGatewayService.class);
        IUnionGatewayClient unionGatewayClient2 = factory.produce(UnionpayGatewayService.class);
        assertEquals(unionGatewayClient1, unionGatewayClient2);
    }
}
