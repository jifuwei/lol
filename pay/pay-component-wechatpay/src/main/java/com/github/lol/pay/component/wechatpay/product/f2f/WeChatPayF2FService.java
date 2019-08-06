package com.github.lol.pay.component.wechatpay.product.f2f;

import com.github.lol.pay.component.wechatpay.IWeChatPayF2FClient;
import com.github.lol.pay.component.wechatpay.WeChatPayConfig;
import com.github.lol.pay.component.wechatpay.constant.WeChatPayProductEnum;
import com.github.lol.pay.component.wechatpay.product.AbstractWeChatPayProductService;
import com.github.lol.pay.component.wechatpay.product.model.*;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * alipay F2F service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
@Slf4j
public class WeChatPayF2FService extends AbstractWeChatPayProductService implements IWeChatPayF2FClient {

    public WeChatPayF2FService(@NonNull WeChatPayConfig config) {
        init(config);
    }

    public static WeChatPayF2FService of(@NonNull WeChatPayConfig config) {
        return new WeChatPayF2FService(config);
    }

    @Override
    @SneakyThrows
    public Map<String, String> microPay(MicroPayReq microPayReq) {
        return this.getWxPay().microPay(serializeObject2Map(microPayReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> orderQuery(OrderQueryReq orderQueryReq) {
        return this.getWxPay().orderQuery(serializeObject2Map(orderQueryReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> reverse(ReverseReq reverseReq) {
        return this.getWxPay().reverse(serializeObject2Map(reverseReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> refund(RefundReq refundReq) {
        return this.getWxPay().refund(serializeObject2Map(refundReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> refundQuery(RefundQueryReq refundQueryReq) {
        return this.getWxPay().refundQuery(serializeObject2Map(refundQueryReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> downloadBill(DownloadBillReq downloadBillReq) {
        return this.getWxPay().downloadBill(serializeObject2Map(downloadBillReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> downloadFundFlow(DownloadFundFlowReq downloadBillReq) {
        return this.getWxPay().downloadFundFlow(serializeObject2Map(downloadBillReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> report(ReportReq reportReq) {
        return this.getWxPay().report(serializeObject2Map(reportReq));
    }

    @Override
    @SneakyThrows
    public Map<String, String> authCodeToOpenId(AuthCodeToOpenIdReq authCodeToOpenIdReq) {
        return this.getWxPay().authCodeToOpenid(serializeObject2Map(authCodeToOpenIdReq));
    }

    @Override
    public Map<String, String> batchQueryComment(BatchQueryCommentReq batchQueryCommentReq) {
        return null; // TODO 暂不支持
    }

    @Override
    protected String productName() {
        return WeChatPayProductEnum.F2F.getDesc();
    }

    @Override
    protected String productId() {
        return WeChatPayProductEnum.F2F.name();
    }
}
