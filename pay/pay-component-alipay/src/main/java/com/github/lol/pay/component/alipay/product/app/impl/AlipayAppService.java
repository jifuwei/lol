package com.github.lol.pay.component.alipay.product.app.impl;

import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.client.IAlipayAppClient;
import com.github.lol.pay.component.alipay.constant.AlipayProductEnum;
import com.github.lol.pay.component.alipay.product.AbstractAlipayProductService;
import com.github.lol.pay.component.alipay.product.model.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * alipay APP service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
@Slf4j
public class AlipayAppService extends AbstractAlipayProductService implements IAlipayAppClient {

    public AlipayAppService(@NonNull AlipayConfig config) {
        init(config);
    }

    public static AlipayAppService of(@NonNull AlipayConfig config) {
        return new AlipayAppService(config);
    }


    @Override
    public AlipayTradeAppPayResponse appPay(@NonNull AppPayBizContentReq appPayBizContentReq) {
        return this.getAlipayCoreService().appPay(appPayBizContentReq);
    }

    @Override
    public AlipayTradeQueryResponse query(@NonNull QueryBizContentReq queryBizContentReq) {
        return this.getAlipayCoreService().query(queryBizContentReq);
    }

    @Override
    public AlipayTradeRefundResponse refund(@NonNull RefundBizContentReq refundBizContentReq) {
        return this.getAlipayCoreService().refund(refundBizContentReq);
    }

    @Override
    public AlipayTradeFastpayRefundQueryResponse fastpayRefundQuery(
            @NonNull FastpayRefundQueryBizContentReq fastpayRefundQueryBizContentReq) {
        return this.getAlipayCoreService().fastpayRefundQuery(fastpayRefundQueryBizContentReq);
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
    protected String productName() {
        return AlipayProductEnum.APP.getDesc();
    }

    @Override
    protected String productId() {
        return AlipayProductEnum.APP.name();
    }
}
