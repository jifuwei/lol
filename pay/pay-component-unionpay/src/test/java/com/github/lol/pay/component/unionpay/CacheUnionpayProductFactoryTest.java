package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.client.IUnionpayGatewayClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CacheUnionpayProductFactoryTest {

    @Test
    public void produce() {
        CacheUnionpayProductFactory factory = new CacheUnionpayProductFactory(new UnionpayConfig());
        IUnionpayGatewayClient unionGatewayClient1 = (IUnionpayGatewayClient) factory.produce(UnionpayProductEnum.GATEWAY.name());
        IUnionpayGatewayClient unionGatewayClient2 = (IUnionpayGatewayClient) factory.produce(UnionpayProductEnum.GATEWAY.name());
        assertEquals(unionGatewayClient1, unionGatewayClient2);
    }
}
