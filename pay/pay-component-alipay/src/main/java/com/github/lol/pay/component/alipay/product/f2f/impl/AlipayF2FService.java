package com.github.lol.pay.component.alipay.product.f2f.impl;

import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.client.IAlipayF2FClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.AbstractAlipayProductService;
import com.github.lol.pay.component.alipay.product.f2f.model.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * alipay F2F service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
@Slf4j
public class AlipayF2FService extends AbstractAlipayProductService implements IAlipayF2FClient {

    public AlipayF2FService(@NonNull AlipayConfig config) {
        init(config);
    }

    public static AlipayF2FService of(@NonNull AlipayConfig config) {
        return new AlipayF2FService(config);
    }

    @Override
    public AlipayTradePayResponse pay(@NonNull PayBizContentReq payBizContentReq) {
        return this.getAlipayCoreService().pay(payBizContentReq);
    }

    @Override
    public AlipayTradeQueryResponse query(@NonNull QueryBizContentReq queryBizContentReq) {
        return this.getAlipayCoreService().query(queryBizContentReq);
    }

    @Override
    public AlipayTradeCancelResponse cancel(@NonNull CancelBizContentReq cancelBizContentReq) {
        return this.getAlipayCoreService().cancel(cancelBizContentReq);
    }

    @Override
    public AlipayTradeCreateResponse create(@NonNull CreateBizContentReq createBizContentReq) {
        return this.getAlipayCoreService().create(createBizContentReq);
    }

    @Override
    public AlipayTradeRefundResponse refund(@NonNull RefundBizContentReq refundBizContentReq) {
        return this.getAlipayCoreService().refund(refundBizContentReq);
    }

    @Override
    public AlipayTradePrecreateResponse preCreate(@NonNull PreCreateBizContentReq preCreateBizContentReq) {
        return this.getAlipayCoreService().preCreate(preCreateBizContentReq);
    }

    @Override
    public AlipayTradeCloseResponse close(@NonNull CloseBizContentReq closeBizContentReq) {
        return this.getAlipayCoreService().close(closeBizContentReq);
    }

    @Override
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            @NonNull BillDownloadUrlQueryBizContentReq billDownloadUrlQueryBizContentReq) {
        return this.getAlipayCoreService().billDownloadurlQuery(billDownloadUrlQueryBizContentReq);
    }

    @Override
    public MonitorHeartbeatSynResponse monitorHeartbeatSyn(
            @NonNull MonitorHeartbeatSynBizContentReq monitorHeartbeatSynBizContentReq) {
        return this.getAlipayCoreService().monitorHeartbeatSyn(monitorHeartbeatSynBizContentReq);
    }

    @Override
    protected String productName() {
        return AlipayProductEnum.F2F.getDesc();
    }

    @Override
    protected String productId() {
        return AlipayProductEnum.F2F.name();
    }
}
