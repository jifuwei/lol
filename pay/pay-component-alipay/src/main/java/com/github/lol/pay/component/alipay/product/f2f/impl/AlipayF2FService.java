package com.github.lol.pay.component.alipay.product.f2f.impl;

import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.IAlipayF2FClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.AbstractAlipayProductService;
import com.github.lol.pay.component.alipay.product.f2f.model.*;
import lombok.NonNull;

/**
 * alipay F2F service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
public class AlipayF2FService extends AbstractAlipayProductService implements IAlipayF2FClient {

    public AlipayF2FService(@NonNull AlipayConfig config) {
        init(config);
    }

    public static AlipayF2FService of(@NonNull AlipayConfig config) {
        return new AlipayF2FService(config);
    }

    @Override
    public AlipayTradePayResponse pay(PayBizContentReq payBizContentReq) {
        return null;
    }

    @Override
    public AlipayTradeQueryResponse query(QueryBizContentReq queryBizContentReq) {
        return null;
    }

    @Override
    public AlipayTradeCancelResponse cancel(CancelBizContentReq cancelBizContentReq) {
        return null;
    }

    @Override
    public AlipayTradeCreateResponse create(CreateBizContentReq createBizContentReq) {
        return null;
    }

    @Override
    public AlipayTradeRefundResponse refund(RefundBizContentReq refundBizContentReq) {
        return null;
    }

    @Override
    public AlipayTradePrecreateResponse preCreate(PreCreateBizContentReq preCreateBizContentReq) {
        return null;
    }

    @Override
    public AlipayTradeCloseResponse close(CloseBizContentReq closeBizContentReq) {
        return null;
    }

    @Override
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(BillDownloadUrlQueryBizContentReq billDownloadUrlQueryBizContentReq) {
        return null;
    }

    @Override
    public MonitorHeartbeatSynResponse monitorHeartbeatSyn(MonitorHeartbeatSynBizContentReq monitorHeartbeatSynBizContentReq) {
        return null;
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
