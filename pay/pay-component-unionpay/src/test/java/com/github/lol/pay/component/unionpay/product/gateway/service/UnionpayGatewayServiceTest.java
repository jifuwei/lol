package com.github.lol.pay.component.unionpay.product.gateway.service;

import com.github.lol.pay.component.unionpay.IUnionPayProductFactory;
import com.github.lol.pay.component.unionpay.SimpleCacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.product.common.model.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.model.ConsumeReq;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class UnionpayGatewayServiceTest {

    private UnionpayConfig config = new UnionpayConfig();

    private IUnionPayProductFactory unionPayProductFactory = SimpleCacheUnionpayProductFactory.getInstance(config);

    @Test
    public void consume() {
        ConsumeReq consumeReq = ConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("01")
                .txnSubType("01")
                .bizType("000201")
                .channelType("07")
                .merId("777290058171299")
                .accessType("0")
                .orderId("jfw123456711")
                .txnTime("20190711170412")
                .currencyCode("156")
                .txnAmt("10000000")
                .riskRateInfo("{commodityName=测试商品名称}")
                .frontUrl("http://localhost:8080/ACPSample_B2C/frontRcvResponse")
                .backUrl("http://222.222.222.222:8080/ACPSample_B2C/backRcvResponse")
                .payTimeout(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000))
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        FormReq formReq = gatewayClient.consume(consumeReq);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }
}
