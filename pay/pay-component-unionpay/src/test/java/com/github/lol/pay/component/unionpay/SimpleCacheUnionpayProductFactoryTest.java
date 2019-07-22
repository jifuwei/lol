package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.client.IUnionpayGatewayClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
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
        IUnionpayGatewayClient unionGatewayClient1 = (IUnionpayGatewayClient) factory.produce(UnionpayProductEnum.GATEWAY.name());
        IUnionpayGatewayClient unionGatewayClient2 = (IUnionpayGatewayClient) factory.produce(UnionpayProductEnum.GATEWAY.name());
        assertEquals(unionGatewayClient1, unionGatewayClient2);
    }
}
