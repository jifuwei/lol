package com.github.lol.pay.component.unionpay.product.qrcode.impl;

import com.github.lol.pay.component.unionpay.CacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.UnionpayGlobalConfig;
import com.github.lol.pay.component.unionpay.client.IUnionpayQRCodeClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.qrcode.model.*;
import com.github.pay.component.core.IUnionPayProductFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UnionpayQRCodeServiceTest {

    private UnionpayConfig config = UnionpayGlobalConfig.init();

    private IUnionpayQRCodeClient qrCodeClient;

    @Before
    public void before() {
        IUnionPayProductFactory unionPayProductFactory = new CacheUnionpayProductFactory(config);
        qrCodeClient = (IUnionpayQRCodeClient) unionPayProductFactory.produce(UnionpayProductEnum.QR_CODE.name());
    }

    @Test
    public void cancelConsume() {
        QRCodeCancelConsumeReq req = QRCodeCancelConsumeReq.of(config)
                .orderId("qrcodeqjfw123456")
                .txnTime("20190722170412")
                .txnAmt("500000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("qrcode201907221021457qwer")
                .origOrderId("qrcode201907221021457qwer")
                .origTxnTime("20190722101256")
                .build();

        QRCodeCancelConsumeSyncResp syncResp = qrCodeClient.cancelConsume(req);

        assertNotNull(syncResp);
    }

    @Test
    public void backConsume() {
        QRCodeBackConsumeReq req = QRCodeBackConsumeReq.of(config)
                .orderId("qrcodebjfw123456")
                .txnTime("20190722170412")
                .txnAmt("100000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("1256489524")
                .origOrderId("1256489524")
                .origTxnTime("20190719170412")
                .build();

        QRCodeBackConsumeSyncResp syncResp = qrCodeClient.backConsume(req);

        assertNotNull(syncResp);
    }

    /**
     * 请求结果:	成功
     * respCode	00
     * acqCode	01022900
     * payeeInfo	e2lkPTc3NzI5MDA1ODE3MTI5OSZtZXJDYXRDb2RlPTU4MTImbmFtZT3mtYvor5XomZrmi5/llYbmiLc3NzcyOTAwNTgxNzEyOTkmdGVybUlkPTAxMDgwMjA5fQ
     * currencyCode	156
     * paymentValidTime	86362
     * orderType	10
     * qrCodeType	0
     * issCode	90880019
     * txnAmt	500000
     * txnNo	7919072254646293076120
     * version	1.0.0
     * invoiceSt	0
     * orderNo	qrcode201907221021457qwer
     * certId	69026276696
     * reqType	0120000903
     * respMsg	成功[0000000]
     */
    @Test
    public void masterImg() {
        QRCodeMasterImgReq req = QRCodeMasterImgReq.of(config)
                .orderId("qrcode201907221021457qwer")
                .txnTime("20190722101256")
                .txnAmt("500000")
                .backUrl("http://www.lol.com/gateway/callback")
                .build();

        QRCodeMasterImgSyncResp syncResp = qrCodeClient.masterImg(req);

        assertNotNull(syncResp);
    }

    @Test
    public void slaveImg() {
        QRCodeSlaveImgReq req = QRCodeSlaveImgReq.of(config)
                .orderId("qrcode20190722102156qwer")
                .txnTime("20190722101256")
                .txnAmt("500000")
                .qrNo("C2B100001")
                .backUrl("http://www.lol.com/gateway/callback")
                .build();

        QRCodeSlaveImgSyncResp syncResp = qrCodeClient.slaveImg(req);

        assertNotNull(syncResp);
    }

    @Test
    public void transactionStatusQuery() {
        QRCodeTransactionStatusQueryReq req = QRCodeTransactionStatusQueryReq.of(config)
                .orderId("qrcode20190722102156qwer")
                .txnTime("20190722101256")
                .build();

        QRCodeTransactionStatusQuerySyncResp syncResp = qrCodeClient.transactionStatusQuery(req);

        assertNotNull(syncResp);
    }

    @Test
    public void reversal() {
        QRCodeReversalReq req = QRCodeReversalReq.of(config)
                .orderId("qrcode20190722102156qwer")
                .txnTime("20190722101256")
                .build();

        QRCodeReversalSyncResp syncResp = qrCodeClient.reversal(req);

        assertNotNull(syncResp);
    }

    @Test
    public void fileTransfer() {
        QRCodeFileTransferReq req = QRCodeFileTransferReq.of(config)
                .settleDate("0722")
                .txnTime("20190722101256")
                .build();

        QRCodeFileTransferSyncResp syncResp = qrCodeClient.fileTransfer(req);

        assertNotNull(syncResp);
    }
}
