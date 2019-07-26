package com.github.lol.pay.component.alipay.product.app.impl;

import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.CacheAlipayProductFactory;
import com.github.lol.pay.component.alipay.client.IAlipayAppClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.model.*;
import com.github.lol.pay.core.IProductFactory;
import com.github.lol.pay.core.SimpleOrderIdGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.github.lol.pay.component.alipay.AlipayConfig.ENCODING_UTF_8;
import static com.github.lol.pay.component.alipay.AlipayConfig.SIGN_TYPE_RSA2;
import static com.github.lol.pay.component.alipay.product.model.BillDownloadUrlQueryBizContentReq.BILL_TYPE_TRADE;

public class AlipayAppServiceTest {

    private AlipayConfig config = new AlipayConfig("https://openapi.alipay.com/gateway.do",
            "APP_ID", "APP_PRIVATE_KEY", "json", ENCODING_UTF_8,
            "ALIPAY_PUBLIC_KEY", SIGN_TYPE_RSA2, new HashMap<>(), new HashMap<>());

    private IAlipayAppClient appClient;

    @Before
    public void before() {
        IProductFactory alipayPayProductFactory = new CacheAlipayProductFactory(config);
        appClient = (IAlipayAppClient) alipayPayProductFactory.produce(AlipayProductEnum.APP.name());
    }

    @Test
    public void appPay() {
        AppPayBizContentReq req = AppPayBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.APP.name()))
                .subject("Iphone XS Max 128G")
                .storeId("NJ_001")
                .totalAmount("12388.23")
                .timeoutExpress("2m")
                .timeExpire("2019-08-31 10:05")
                .storeId("NJ_001")
                .build();

        appClient.appPay(req);
    }

    @Test
    public void query() {
        QueryBizContentReq req = QueryBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.APP.name()))
                .tradeNo("2014112611001004680073956707")
                .build();

        appClient.query(req);
    }

    @Test
    public void refund() {
        RefundBizContentReq req = RefundBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.APP.name()))
                .tradeNo("2014112611001004680073956707")
                .storeId("NJ_001")
                .refundAmount("12388.23")
                .operatorId("zhangsan")
                .build();

        appClient.refund(req);
    }

    @Test
    public void fastpayRefundQuery() {
        FastpayRefundQueryBizContentReq req = FastpayRefundQueryBizContentReq.builder()
                .outTradeNo("2014112611001004680073956707")
                .tradeNo("2014112611001004680073956707")
                .outRequestNo(SimpleOrderIdGenerator.get(AlipayProductEnum.APP.name()))
                .build();

        appClient.fastpayRefundQuery(req);
    }

    @Test
    public void close() {
        CloseBizContentReq req = CloseBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.APP.name()))
                .tradeNo("2014112611001004680073956707")
                .operatorId("zhangsan")
                .build();

        appClient.close(req);
    }

    @Test
    public void billDownloadurlQuery() {
        BillDownloadUrlQueryBizContentReq req = BillDownloadUrlQueryBizContentReq.builder()
                .billType(BILL_TYPE_TRADE)
                .billDate("2019-07-25")
                .build();

        appClient.billDownloadurlQuery(req);
    }
}
