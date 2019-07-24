package com.github.lol.pay.component.alipay.product.f2f.impl;

import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.IAlipayF2FClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.AbstractAlipayProductService;
import lombok.NonNull;
import lombok.SneakyThrows;

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
    @SneakyThrows
    public AlipayTradePayResponse pay(AlipayTradePayRequest alipayTradePayRequest) {
        return this.getAlipayClient().execute(alipayTradePayRequest);
    }

    @Override
    @SneakyThrows
    public AlipayTradeQueryResponse query(AlipayTradeQueryRequest alipayTradeQueryRequest) {
        return this.getAlipayClient().execute(alipayTradeQueryRequest);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCancelResponse cancel(AlipayTradeCancelRequest alipayTradeCancelRequest) {
        return this.getAlipayClient().execute(alipayTradeCancelRequest);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCreateResponse create(AlipayTradeCreateRequest alipayTradeCreateRequest) {
        return this.getAlipayClient().execute(alipayTradeCreateRequest);
    }

    @Override
    @SneakyThrows
    public AlipayTradeRefundResponse refund(AlipayTradeRefundRequest alipayTradeRefundRequest) {
        return this.getAlipayClient().execute(alipayTradeRefundRequest);
    }

    @Override
    @SneakyThrows
    public AlipayTradePrecreateResponse preCreate(AlipayTradePrecreateRequest alipayTradePrecreateRequest) {
        return this.getAlipayClient().execute(alipayTradePrecreateRequest);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCloseResponse close(AlipayTradeCloseRequest alipayTradeCloseRequest) {
        return this.getAlipayClient().execute(alipayTradeCloseRequest);
    }

    @Override
    @SneakyThrows
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            AlipayDataDataserviceBillDownloadurlQueryRequest billDownloadurlQueryRequest) {
        return this.getAlipayClient().execute(billDownloadurlQueryRequest);
    }

    @Override
    @SneakyThrows
    public MonitorHeartbeatSynResponse monitorHeartbeatSyn(MonitorHeartbeatSynRequest monitorHeartbeatSynRequest) {
        return this.getAlipayClient().execute(monitorHeartbeatSynRequest);
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
