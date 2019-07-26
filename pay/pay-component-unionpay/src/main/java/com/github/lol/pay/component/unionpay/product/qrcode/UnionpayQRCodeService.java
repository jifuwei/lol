package com.github.lol.pay.component.unionpay.product.qrcode;

import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.client.IUnionpayQRCodeClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.AbstractUnionpayProductService;
import com.github.lol.pay.component.unionpay.product.qrcode.model.*;
import lombok.NonNull;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.Api.*;

/**
 * unionpay qr code service
 *
 * @author: jifuwei
 * @create: 2019-07-21 14:49
 **/
public class UnionpayQRCodeService extends AbstractUnionpayProductService
        implements IUnionpayQRCodeClient {

    public UnionpayQRCodeService(@NonNull UnionpayConfig config) {
        init(config);
    }

    public static UnionpayQRCodeService of(@NonNull UnionpayConfig config) {
        return new UnionpayQRCodeService(config);
    }

    @Override
    public QRCodeCancelConsumeSyncResp cancelConsume(@NonNull QRCodeCancelConsumeReq cancelConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_BACK_TRANSFER_WITHOUT_CARD;
        return post(cancelConsumeReq, reqUrl, QRCodeCancelConsumeSyncResp.class);
    }

    @Override
    public QRCodeBackConsumeSyncResp backConsume(@NonNull QRCodeBackConsumeReq backConsumeReq) {
        String reqUrl = this.getConfig().getDomain() + URL_BACK_TRANSFER_WITHOUT_CARD;
        return post(backConsumeReq, reqUrl, QRCodeBackConsumeSyncResp.class);
    }

    @Override
    public QRCodeMasterImgSyncResp masterImg(@NonNull QRCodeMasterImgReq masterImgReq) {
        String reqUrl = this.getConfig().getDomain() + URL_BACK_TRANSFER_WITHOUT_CARD;
        return post(masterImgReq, reqUrl, QRCodeMasterImgSyncResp.class);
    }

    @Override
    public QRCodeSlaveImgSyncResp slaveImg(@NonNull QRCodeSlaveImgReq slaveImgReq) {
        String reqUrl = this.getConfig().getDomain() + URL_BACK_TRANSFER_WITHOUT_CARD;
        return post(slaveImgReq, reqUrl, QRCodeSlaveImgSyncResp.class);
    }

    @Override
    public QRCodeTransactionStatusQuerySyncResp transactionStatusQuery(
            @NonNull QRCodeTransactionStatusQueryReq transactionStatusQueryReq) {
        String reqUrl = this.getConfig().getDomain() + URL_QUERY_SINGLE;
        return post(transactionStatusQueryReq, reqUrl, QRCodeTransactionStatusQuerySyncResp.class);
    }

    @Override
    public QRCodeReversalSyncResp reversal(@NonNull QRCodeReversalReq reversalReq) {
        String reqUrl = this.getConfig().getDomain() + URL_BACK_TRANSFER_WITHOUT_CARD;
        return post(reversalReq, reqUrl, QRCodeReversalSyncResp.class);
    }

    @Override
    public QRCodeFileTransferSyncResp fileTransfer(@NonNull QRCodeFileTransferReq fileTransferReq) {
        // 对账地址和主api不一致
        String reqUrl = this.getConfig().getFileDownLoadUrl();
        return post(fileTransferReq, reqUrl, QRCodeFileTransferSyncResp.class);
    }

    @Override
    protected String productName() {
        return UnionpayProductEnum.QR_CODE.getDesc();
    }

    @Override
    protected String productId() {
        return UnionpayProductEnum.QR_CODE.name();
    }
}
