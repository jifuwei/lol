package com.github.lol.pay.component.unionpay.product.gateway.service;

import com.github.lol.lib.util.SerializeUtil;
import com.github.lol.lib.util.http.HttpNetUtil;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.core.CertificateService;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.core.UnionpaySignService;
import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.common.model.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.model.*;
import org.apache.commons.lang3.Validate;

import java.util.Map;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.Api.URL_GATEWAY_CANCEL_CONSUME;
import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.Api.URL_GATEWAY_CONSUME;

/**
 * unionpay gateway client
 *
 * @author: jifuwei
 * @create: 2019-07-15 14:20
 **/
public class UnionpayGatewayService extends AbstractUnionpayProductService implements IUnionGatewayClient {

    public UnionpayGatewayService(UnionpayConfig config) {
        this.setConfig(config);
        this.setCertService(CertificateService.of(config));
        this.setSignService(UnionpaySignService.of(config, this.getCertService()));
    }

    public static UnionpayGatewayService of(UnionpayConfig config) {
        return new UnionpayGatewayService(config);
    }

    @Override
    public FormReq consume(ConsumeReq consumeReq) {
        Validate.notNull(consumeReq);

        Map<String, String> dataMap = convertData2Map(consumeReq);
        this.getSignService().sign(dataMap, this.getConfig().getEncoding());
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CONSUME;

        return FormReq.builder()
                .actionUrl(reqUrl).encoding(this.getConfig().getEncoding()).inputDataMap(dataMap)
                .build();
    }

    @Override
    public CancelConsumeSyncResp cancelConsume(CancelConsumeReq cancelConsumeReq) {

        Map<String, Object> dataMap = SerializeUtil.objectToMapNullRemove(cancelConsumeReq, String.class, Object.class);
        this.getSignService().sign(dataMap, this.getConfig().getEncoding());
        String reqUrl = this.getConfig().getDomain() + URL_GATEWAY_CANCEL_CONSUME;

        Map<String, String> respMap = this.buildNetUtil(reqUrl, HttpNetUtil.HTTP_METHOD_POST).post(dataMap);
        this.getSignService().validate(respMap, this.getConfig().getEncoding());

        return SerializeUtil.mapToObject(respMap, CancelConsumeSyncResp.class);
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

    @Override
    protected String productName() {
        return UnionpayProductEnum.GATEWAY.getDesc();
    }

    @Override
    protected String productId() {
        return UnionpayProductEnum.GATEWAY.name();
    }
}
