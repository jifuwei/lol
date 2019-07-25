package com.github.lol.pay.component.alipay.product.wap.impl;

import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.CacheAlipayProductFactory;
import com.github.lol.pay.component.alipay.client.IAlipayWapClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.model.*;
import com.github.lol.pay.core.IProductFactory;
import com.github.lol.pay.core.SimpleOrderIdGenerator;
import org.junit.Before;
import org.junit.Test;

import static com.github.lol.pay.component.alipay.AlipayConfig.ENCODING_UTF_8;
import static com.github.lol.pay.component.alipay.AlipayConfig.SIGN_TYPE_RSA2;
import static com.github.lol.pay.component.alipay.product.model.BillDownloadUrlQueryBizContentReq.BILL_TYPE_TRADE;
import static com.github.lol.pay.component.alipay.product.model.WapPayBizContentReq.DEFAULT_PRODUCT_CODE;

public class AlipayWapServiceTest {

    private AlipayConfig config = new AlipayConfig("https://openapi.alipay.com/gateway.do",
            "APP_ID", "APP_PRIVATE_KEY", "json", ENCODING_UTF_8,
            "ALIPAY_PUBLIC_KEY", SIGN_TYPE_RSA2);

    private IAlipayWapClient wapClient;

    @Before
    public void before() {
        IProductFactory alipayPayProductFactory = new CacheAlipayProductFactory(config);
        wapClient = (IAlipayWapClient) alipayPayProductFactory.produce(AlipayProductEnum.WAP.name());
    }

    @Test
    public void wapPay() {
        WapPayBizContentReq req = WapPayBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.WAP.name()))
                .subject("Iphone XS Max 128G")
                .storeId("NJ_001")
                .totalAmount("12388.23")
                .timeoutExpress("2m")
                .timeExpire("2019-08-31 10:05")
                .storeId("NJ_001")
                .productCode(DEFAULT_PRODUCT_CODE)
                .build();

        wapClient.wapPay(req);
    }

    @Test
    public void query() {
        QueryBizContentReq req = QueryBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.WAP.name()))
                .tradeNo("2014112611001004680073956707")
                .build();

        wapClient.query(req);
    }

    @Test
    public void refund() {
        RefundBizContentReq req = RefundBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.WAP.name()))
                .tradeNo("2014112611001004680073956707")
                .storeId("NJ_001")
                .refundAmount("12388.23")
                .operatorId("zhangsan")
                .build();

        wapClient.refund(req);
    }

    @Test
    public void fastpayRefundQuery() {
        FastpayRefundQueryBizContentReq req = FastpayRefundQueryBizContentReq.builder()
                .outTradeNo("2014112611001004680073956707")
                .tradeNo("2014112611001004680073956707")
                .outRequestNo(SimpleOrderIdGenerator.get(AlipayProductEnum.WAP.name()))
                .build();

        wapClient.fastpayRefundQuery(req);
    }

    @Test
    public void close() {
        CloseBizContentReq req = CloseBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.WAP.name()))
                .tradeNo("2014112611001004680073956707")
                .operatorId("zhangsan")
                .build();

        wapClient.close(req);
    }

    @Test
    public void billDownloadurlQuery() {
        BillDownloadUrlQueryBizContentReq req = BillDownloadUrlQueryBizContentReq.builder()
                .billType(BILL_TYPE_TRADE)
                .billDate("2019-07-25")
                .build();

        wapClient.billDownloadurlQuery(req);
    }
}
