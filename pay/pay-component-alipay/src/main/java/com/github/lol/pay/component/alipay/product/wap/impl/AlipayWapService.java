package com.github.lol.pay.component.alipay.product.wap.impl;

import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.client.IAlipayWapClient;
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
public class AlipayWapService extends AbstractAlipayProductService implements IAlipayWapClient {

    public AlipayWapService(@NonNull AlipayConfig config) {
        init(config);
    }

    public static AlipayWapService of(@NonNull AlipayConfig config) {
        return new AlipayWapService(config);
    }


    @Override
    public String wapPay(WapPayBizContentReq wapPayBizContentReq) {
        return this.getAlipayCoreService()
                .wapPay(pkgAlipayCoreReq("wapPay", wapPayBizContentReq));
    }

    @Override
    public AlipayTradeQueryResponse query(@NonNull QueryBizContentReq queryBizContentReq) {
        return this.getAlipayCoreService()
                .query(pkgAlipayCoreReq("query", queryBizContentReq));
    }

    @Override
    public AlipayTradeRefundResponse refund(@NonNull RefundBizContentReq refundBizContentReq) {
        return this.getAlipayCoreService()
                .refund(pkgAlipayCoreReq("refund", refundBizContentReq));
    }

    @Override
    public AlipayTradeFastpayRefundQueryResponse fastpayRefundQuery(
            @NonNull FastpayRefundQueryBizContentReq fastpayRefundQueryBizContentReq) {
        return this.getAlipayCoreService()
                .fastpayRefundQuery(pkgAlipayCoreReq("fastpayRefundQuery",
                        fastpayRefundQueryBizContentReq));
    }

    @Override
    public AlipayTradeCloseResponse close(@NonNull CloseBizContentReq closeBizContentReq) {
        return this.getAlipayCoreService()
                .close(pkgAlipayCoreReq("close", closeBizContentReq));
    }

    @Override
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            @NonNull BillDownloadUrlQueryBizContentReq billDownloadUrlQueryBizContentReq) {
        return this.getAlipayCoreService()
                .billDownloadurlQuery(pkgAlipayCoreReq("billDownloadurlQuery",
                        billDownloadUrlQueryBizContentReq));
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
