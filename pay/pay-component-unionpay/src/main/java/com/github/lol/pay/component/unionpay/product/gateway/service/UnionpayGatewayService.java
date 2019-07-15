package com.github.lol.pay.component.unionpay.product.gateway.service;

import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.model.*;

/**
 * unionpay gateway client
 *
 * @author: jifuwei
 * @create: 2019-07-15 14:20
 **/
public class UnionpayGatewayService extends AbstractUnionpayProductService implements IUnionGatewayClient {

    public UnionpayGatewayService(UnionpayConfig config) {
        setConfig(config);
    }

    @Override
    public ConsumeSyncResp consume(ConsumeReq consumeReq) {
        return null;
    }

    @Override
    public CancelConsumeSyncResp cancelConsume(CancelConsumeReq cancelConsumeReq) {
        return null;
    }

    @Override
    public BackConsumeSyncResp backConsume(BackConsumeReq backConsumeReq) {
        return null;
    }

    @Override
    public TransactionStatusQuerySyncResp transactionStatusQuery(TransactionStatusQueryReq transactionStatusQueryReq) {
        return null;
    }

    @Override
    public EncryptInfoUpdateSyncResp encryptInfoUpdate(EncryptInfoUpdateReq encryptInfoUpdateReq) {
        return null;
    }

    @Override
    public PreAuthSyncResp preAuth(PreAuthReq preAuthReq) {
        return null;
    }

    @Override
    public CancelPreAuthSyncResp cancelPreAuth(CancelPreAuthReq cancelPreAuthReq) {
        return null;
    }

    @Override
    public CompletePreAuthSyncResp completePreAuth(CompletePreAuthReq completePreAuthReq) {
        return null;
    }

    @Override
    public CancelCompletedPreAuthSyncResp cancelCompletedPreAuth(CancelCompletedPreAuthReq cancelCompletedPreAuthReq) {
        return null;
    }

    @Override
    public FileTransferSyncResp fileTransfer(FileTransferReq fileTransferReq) {
        return null;
    }
}
