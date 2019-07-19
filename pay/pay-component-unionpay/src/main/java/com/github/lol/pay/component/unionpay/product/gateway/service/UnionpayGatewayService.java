package com.github.lol.pay.component.unionpay.product.gateway.service;

import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.core.CertificateService;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.core.UnionpaySignService;
import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.common.model.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.model.*;
import lombok.NonNull;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.Api.*;

/**
 * unionpay gateway client
 *
 * @author: jifuwei
 * @create: 2019-07-15 14:20
 **/
public class UnionpayGatewayService extends AbstractUnionpayProductService
        implements IUnionGatewayClient {

    public UnionpayGatewayService(@NonNull UnionpayConfig config) {
        this.setConfig(config);
        this.setCertService(CertificateService.of(config));
        this.setSignService(UnionpaySignService.of(config, this.getCertService()));
    }

    public static UnionpayGatewayService of(@NonNull UnionpayConfig config) {
        return new UnionpayGatewayService(config);
    }

    @Override
    public FormReq consume(@NonNull ConsumeReq consumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CONSUME;
        return form(consumeReq, reqUrl);
    }

    @Override
    public CancelConsumeSyncResp cancelConsume(@NonNull CancelConsumeReq cancelConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_CONSUME;
        return post(cancelConsumeReq, reqUrl, CancelConsumeSyncResp.class);
    }

    @Override
    public BackConsumeSyncResp backConsume(@NonNull BackConsumeReq backConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_BACK_CONSUME;
        return post(backConsumeReq, reqUrl, BackConsumeSyncResp.class);
    }

    @Override
    public TransactionStatusQuerySyncResp transactionStatusQuery(
            @NonNull TransactionStatusQueryReq transactionStatusQueryReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_TRANSACTION_STATUS_QUERY;
        return post(transactionStatusQueryReq, reqUrl, TransactionStatusQuerySyncResp.class);
    }

    @Override
    public EncryptInfoUpdateSyncResp encryptInfoUpdate(
            @NonNull EncryptInfoUpdateReq encryptInfoUpdateReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_ENCRYPT_INFO_UPDATE;
        return post(encryptInfoUpdateReq, reqUrl, EncryptInfoUpdateSyncResp.class);
    }

    @Override
    public FormReq preAuth(@NonNull PreAuthReq preAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_PRE_AUTH;
        return form(preAuthReq, reqUrl);
    }

    @Override
    public CancelPreAuthSyncResp cancelPreAuth(@NonNull CancelPreAuthReq cancelPreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_PRE_AUTH;
        return post(cancelPreAuthReq, reqUrl, CancelPreAuthSyncResp.class);
    }

    @Override
    public CompletePreAuthSyncResp completePreAuth(@NonNull CompletePreAuthReq completePreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_COMPLETE_PRE_AUTH;
        return post(completePreAuthReq, reqUrl, CompletePreAuthSyncResp.class);
    }

    @Override
    public CancelCompletedPreAuthSyncResp cancelCompletedPreAuth(
            @NonNull CancelCompletedPreAuthReq cancelCompletedPreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_COMPLETE_PRE_AUTH;
        return post(cancelCompletedPreAuthReq, reqUrl, CancelCompletedPreAuthSyncResp.class);
    }

    @Override
    public FileTransferSyncResp fileTransfer(@NonNull FileTransferReq fileTransferReq) {
        // 对账地址和主api不一致
        String reqUrl = this.getConfig().getFileDownLoadUrl();
        return post(fileTransferReq, reqUrl, FileTransferSyncResp.class);
    }

    @Override
    protected String productName() {
        return UnionpayProductEnum.GATEWAY.getDesc();
    }

    @Override
    protected String productId() {
        return UnionpayProductEnum.GATEWAY.name();
    }
}
