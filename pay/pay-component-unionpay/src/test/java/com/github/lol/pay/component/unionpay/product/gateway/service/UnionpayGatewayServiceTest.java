package com.github.lol.pay.component.unionpay.product.gateway.service;

import com.github.lol.pay.component.unionpay.IUnionPayProductFactory;
import com.github.lol.pay.component.unionpay.SimpleCacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.product.common.model.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.model.CancelConsumeReq;
import com.github.lol.pay.component.unionpay.product.gateway.model.CancelConsumeSyncResp;
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
                .merId(config.getMerId())
                .accessType(config.getAccessType())
                .orderId("jfw123456712")
                .txnTime("20190711170412")
                .currencyCode(config.getCurrencyCode())
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

    @Test
    public void cancelConsume() {
        CancelConsumeReq req = CancelConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("31")
                .txnSubType("00")
                .bizType("000000")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("cjfw123456712")
                .txnTime("20190716170412")
                .txnAmt("10000000")
                .origQryId("561907111704129975038")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        CancelConsumeSyncResp syncResp = gatewayClient.cancelConsume(req);
        System.out.println("==> " + syncResp.toString());

        assertNotNull(syncResp);
    }
}
