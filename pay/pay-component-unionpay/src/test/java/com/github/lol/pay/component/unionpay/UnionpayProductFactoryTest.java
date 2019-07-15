package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionpayProductFactoryTest {

    @Test
    public void getInstance() {
        SimpleCacheUnionpayProductFactory first = SimpleCacheUnionpayProductFactory.getInstance(new UnionpayConfig());
        SimpleCacheUnionpayProductFactory second = SimpleCacheUnionpayProductFactory.getInstance(new UnionpayConfig());
        assertEquals(first, second);
    }

    @Test
    public void produce() {
        SimpleCacheUnionpayProductFactory factory = SimpleCacheUnionpayProductFactory.getInstance(new UnionpayConfig());
        IUnionGatewayClient unionGatewayClient1 = factory.produce(IUnionGatewayClient.class);
        IUnionGatewayClient unionGatewayClient2 = factory.produce(IUnionGatewayClient.class);
        assertEquals(unionGatewayClient1, unionGatewayClient2);
    }
}
