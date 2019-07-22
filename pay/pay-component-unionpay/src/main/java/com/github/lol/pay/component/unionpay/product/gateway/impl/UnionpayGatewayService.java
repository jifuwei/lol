package com.github.lol.pay.component.unionpay.product.gateway.impl;

import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.client.IUnionpayGatewayClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.FormReq;
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
        implements IUnionpayGatewayClient {

    public UnionpayGatewayService(@NonNull UnionpayConfig config) {
        init(config);
    }

    public static UnionpayGatewayService of(@NonNull UnionpayConfig config) {
        return new UnionpayGatewayService(config);
    }

    @Override
    public FormReq consume(@NonNull GatewayConsumeReq gatewayConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CONSUME;
        return form(gatewayConsumeReq, reqUrl);
    }

    @Override
    public GatewayCancelConsumeSyncResp cancelConsume(@NonNull GatewayCancelConsumeReq gatewayCancelConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_CONSUME;
        return post(gatewayCancelConsumeReq, reqUrl, GatewayCancelConsumeSyncResp.class);
    }

    @Override
    public GatewayBackConsumeSyncResp backConsume(@NonNull GatewayBackConsumeReq gatewayBackConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_BACK_CONSUME;
        return post(gatewayBackConsumeReq, reqUrl, GatewayBackConsumeSyncResp.class);
    }

    @Override
    public GatewayTransactionStatusQuerySyncResp transactionStatusQuery(
            @NonNull GatewayTransactionStatusQueryReq gatewayTransactionStatusQueryReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_TRANSACTION_STATUS_QUERY;
        return post(gatewayTransactionStatusQueryReq, reqUrl, GatewayTransactionStatusQuerySyncResp.class);
    }

    @Override
    public GatewayEncryptInfoUpdateSyncResp encryptInfoUpdate(
            @NonNull GatewayEncryptInfoUpdateReq gatewayEncryptInfoUpdateReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_ENCRYPT_INFO_UPDATE;
        return post(gatewayEncryptInfoUpdateReq, reqUrl, GatewayEncryptInfoUpdateSyncResp.class);
    }

    @Override
    public FormReq preAuth(@NonNull GatewayPreAuthReq gatewayPreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_PRE_AUTH;
        return form(gatewayPreAuthReq, reqUrl);
    }

    @Override
    public GatewayCancelPreAuthSyncResp cancelPreAuth(@NonNull GatewayCancelPreAuthReq gatewayCancelPreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_PRE_AUTH;
        return post(gatewayCancelPreAuthReq, reqUrl, GatewayCancelPreAuthSyncResp.class);
    }

    @Override
    public GatewayCompletePreAuthSyncResp completePreAuth(@NonNull GatewayCompletePreAuthReq gatewayCompletePreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_COMPLETE_PRE_AUTH;
        return post(gatewayCompletePreAuthReq, reqUrl, GatewayCompletePreAuthSyncResp.class);
    }

    @Override
    public GatewayCancelCompletedPreAuthSyncResp cancelCompletedPreAuth(
            @NonNull GatewayCancelCompletedPreAuthReq gatewayCancelCompletedPreAuthReq) {
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_COMPLETE_PRE_AUTH;
        return post(gatewayCancelCompletedPreAuthReq, reqUrl, GatewayCancelCompletedPreAuthSyncResp.class);
    }

    @Override
    public GatewayFileTransferSyncResp fileTransfer(@NonNull GatewayFileTransferReq gatewayFileTransferReq) {
        // 对账地址和主api不一致
        String reqUrl = this.getConfig().getFileDownLoadUrl();
        return post(gatewayFileTransferReq, reqUrl, GatewayFileTransferSyncResp.class);
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
