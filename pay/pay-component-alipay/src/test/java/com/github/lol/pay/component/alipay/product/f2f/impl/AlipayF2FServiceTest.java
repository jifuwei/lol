package com.github.lol.pay.component.alipay.product.f2f.impl;

import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.CacheAlipayProductFactory;
import com.github.lol.pay.component.alipay.client.IAlipayF2FClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.model.*;
import com.github.lol.pay.core.IProductFactory;
import com.github.lol.pay.core.SimpleOrderIdGenerator;
import org.junit.Before;
import org.junit.Test;

import static com.github.lol.pay.component.alipay.AlipayConfig.ENCODING_UTF_8;
import static com.github.lol.pay.component.alipay.AlipayConfig.SIGN_TYPE_RSA2;
import static com.github.lol.pay.component.alipay.product.model.BillDownloadUrlQueryBizContentReq.BILL_TYPE_TRADE;
import static com.github.lol.pay.component.alipay.product.model.MonitorHeartbeatSynBizContentReq.PRODUCT_FP;
import static com.github.lol.pay.component.alipay.product.model.MonitorHeartbeatSynBizContentReq.TYPE_STORE;
import static com.github.lol.pay.component.alipay.product.model.PayBizContentReq.SCENE_BAR_CODE;

public class AlipayF2FServiceTest {

    private AlipayConfig config = new AlipayConfig("https://openapi.alipay.com/gateway.do",
            "APP_ID", "APP_PRIVATE_KEY", "json", ENCODING_UTF_8,
            "ALIPAY_PUBLIC_KEY", SIGN_TYPE_RSA2);

    private IAlipayF2FClient f2FClient;

    @Before
    public void before() {
        IProductFactory alipayPayProductFactory = new CacheAlipayProductFactory(config);
        f2FClient = (IAlipayF2FClient) alipayPayProductFactory.produce(AlipayProductEnum.F2F.name());
    }

    @Test
    public void pay() {
        PayBizContentReq req = PayBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .scene(SCENE_BAR_CODE)
                .authCode("28763443825664394")
                .subject("Iphone XS Max 128G")
                .storeId("NJ_001")
                .totalAmount("12388.23")
                .timeoutExpress("2m")
                .build();

        f2FClient.pay(req);
    }

    @Test
    public void query() {
        QueryBizContentReq req = QueryBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .tradeNo("2014112611001004680073956707")
                .build();

        f2FClient.query(req);
    }

    @Test
    public void cancel() {
        CancelBizContentReq req = CancelBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .tradeNo("2014112611001004680073956707")
                .build();

        f2FClient.cancel(req);
    }

    @Test
    public void create() {
        CreateBizContentReq req = CreateBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .subject("Iphone XS Max 128G")
                .storeId("NJ_001")
                .totalAmount("12388.23")
                .timeoutExpress("2m")
                .build();

        f2FClient.create(req);
    }

    @Test
    public void refund() {
        RefundBizContentReq req = RefundBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .tradeNo("2014112611001004680073956707")
                .storeId("NJ_001")
                .refundAmount("12388.23")
                .operatorId("zhangsan")
                .build();

        f2FClient.refund(req);
    }

    @Test
    public void preCreate() {
        PreCreateBizContentReq req = PreCreateBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .storeId("NJ_001")
                .totalAmount("12388.23")
                .timeoutExpress("2m")
                .operatorId("zhangsan")
                .build();

        f2FClient.preCreate(req);
    }

    @Test
    public void close() {
        CloseBizContentReq req = CloseBizContentReq.builder()
                .outTradeNo(SimpleOrderIdGenerator.get(AlipayProductEnum.F2F.name()))
                .tradeNo("2014112611001004680073956707")
                .operatorId("zhangsan")
                .build();

        f2FClient.close(req);
    }

    @Test
    public void billDownloadurlQuery() {
        BillDownloadUrlQueryBizContentReq req = BillDownloadUrlQueryBizContentReq.builder()
                .billType(BILL_TYPE_TRADE)
                .billDate("2019-07-25")
                .build();

        f2FClient.billDownloadurlQuery(req);
    }

    @Test
    public void monitorHeartbeatSyn() {
        MonitorHeartbeatSynBizContentReq req = MonitorHeartbeatSynBizContentReq.builder()
                .product(PRODUCT_FP)
                .type(TYPE_STORE)
                .equipmentId("1001023")
                .time("2019-01-22 16:46:02")
                .storeId("NJ_001")
                .storeId("NJ_001")
                .tradeInfo(new MonitorHeartbeatSynBizContentReq.TradeInfo())
                .build();

        f2FClient.monitorHeartbeatSyn(req);
    }
}
