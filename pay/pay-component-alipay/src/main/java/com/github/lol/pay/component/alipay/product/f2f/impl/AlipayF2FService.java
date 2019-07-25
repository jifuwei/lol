package com.github.lol.pay.component.alipay.product.f2f.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.client.IAlipayF2FClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.AbstractAlipayProductService;
import com.github.lol.pay.component.alipay.product.f2f.model.*;
import lombok.NonNull;
import lombok.SneakyThrows;
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
    @SneakyThrows
    public AlipayTradePayResponse pay(@NonNull PayBizContentReq payBizContentReq) {
        AlipayTradePayRequest req = new AlipayTradePayRequest();
        req.setBizContent(JSON.toJSONString(payBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [pay] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeQueryResponse query(@NonNull QueryBizContentReq queryBizContentReq) {
        AlipayTradeQueryRequest req = new AlipayTradeQueryRequest();
        req.setBizContent(JSON.toJSONString(queryBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [query] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCancelResponse cancel(@NonNull CancelBizContentReq cancelBizContentReq) {
        AlipayTradeCancelRequest req = new AlipayTradeCancelRequest();
        req.setBizContent(JSON.toJSONString(cancelBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [cancel] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCreateResponse create(@NonNull CreateBizContentReq createBizContentReq) {
        AlipayTradeCreateRequest req = new AlipayTradeCreateRequest();
        req.setBizContent(JSON.toJSONString(createBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [create] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeRefundResponse refund(@NonNull RefundBizContentReq refundBizContentReq) {
        AlipayTradeRefundRequest req = new AlipayTradeRefundRequest();
        req.setBizContent(JSON.toJSONString(refundBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [refund] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradePrecreateResponse preCreate(@NonNull PreCreateBizContentReq preCreateBizContentReq) {
        AlipayTradePrecreateRequest req = new AlipayTradePrecreateRequest();
        req.setBizContent(JSON.toJSONString(preCreateBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [preCreate] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCloseResponse close(@NonNull CloseBizContentReq closeBizContentReq) {
        AlipayTradeCloseRequest req = new AlipayTradeCloseRequest();
        req.setBizContent(JSON.toJSONString(closeBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [close] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            @NonNull BillDownloadUrlQueryBizContentReq billDownloadUrlQueryBizContentReq) {
        AlipayDataDataserviceBillDownloadurlQueryRequest req = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        req.setBizContent(JSON.toJSONString(billDownloadUrlQueryBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [billDownloadurlQuery] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public MonitorHeartbeatSynResponse monitorHeartbeatSyn(
            @NonNull MonitorHeartbeatSynBizContentReq monitorHeartbeatSynBizContentReq) {
        MonitorHeartbeatSynRequest req = new MonitorHeartbeatSynRequest();
        req.setBizContent(JSON.toJSONString(monitorHeartbeatSynBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [monitorHeartbeatSyn] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
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
