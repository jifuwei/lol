package com.github.lol.pay.component.unionpay.product.qrcode.impl;

import com.github.lol.pay.component.unionpay.CacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.IUnionPayProductFactory;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.client.IUnionpayQRCodeClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.qrcode.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UnionpayQRCodeServiceTest {

    private UnionpayConfig config = new UnionpayConfig();

    private IUnionpayQRCodeClient qrCodeClient;

    @Before
    public void before() {
        IUnionPayProductFactory unionPayProductFactory = new CacheUnionpayProductFactory(config);
        qrCodeClient = (IUnionpayQRCodeClient) unionPayProductFactory.produce(UnionpayProductEnum.QR_CODE.name());
    }

    @Test
    public void cancelConsume() {
        QRCodeCancelConsumeReq req = QRCodeCancelConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("31")
                .txnSubType("00")
                .bizType("000000")
                .channelType("08")
                .merId(config.getMerId())
                .accessType("0")
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
        QRCodeBackConsumeReq req = QRCodeBackConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("04")
                .txnSubType("00")
                .bizType("000000")
                .channelType("08")
                .merId(config.getMerId())
                .accessType("0")
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
        QRCodeMasterImgReq req = QRCodeMasterImgReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("01")
                .txnSubType("07")
                .bizType("000000")
                .channelType("08")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("qrcode201907221021457qwer")
                .txnTime("20190722101256")
                .txnAmt("500000")
                .backUrl("http://www.lol.com/gateway/callback")
                .currencyCode("156")
                .build();

        QRCodeMasterImgSyncResp syncResp = qrCodeClient.masterImg(req);

        assertNotNull(syncResp);
    }

    @Test
    public void slaveImg() {
        QRCodeSlaveImgReq req = QRCodeSlaveImgReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("01")
                .txnSubType("06")
                .bizType("000000")
                .channelType("08")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("qrcode20190722102156qwer")
                .txnTime("20190722101256")
                .txnAmt("500000")
                .qrNo("C2B100001")
                .backUrl("http://www.lol.com/gateway/callback")
                .currencyCode("156")
                .build();

        QRCodeSlaveImgSyncResp syncResp = qrCodeClient.slaveImg(req);

        assertNotNull(syncResp);
    }

    @Test
    public void transactionStatusQuery() {
        QRCodeTransactionStatusQueryReq req = QRCodeTransactionStatusQueryReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("00")
                .txnSubType("00")
                .bizType("000000")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("qrcode20190722102156qwer")
                .txnTime("20190722101256")
                .build();

        QRCodeTransactionStatusQuerySyncResp syncResp = qrCodeClient.transactionStatusQuery(req);

        assertNotNull(syncResp);
    }

    @Test
    public void reversal() {
        QRCodeReversalReq req = QRCodeReversalReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("99")
                .txnSubType("01")
                .bizType("000000")
                .channelType("08")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("qrcode20190722102156qwer")
                .txnTime("20190722101256")
                .build();

        QRCodeReversalSyncResp syncResp = qrCodeClient.reversal(req);

        assertNotNull(syncResp);
    }

    @Test
    public void fileTransfer() {
        QRCodeFileTransferReq req = QRCodeFileTransferReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("76")
                .txnSubType("01")
                .bizType("000000")
                .merId(config.getMerId())
                .accessType("0")
                .settleDate("0722")
                .txnTime("20190722101256")
                .fileType("00")
                .build();

        QRCodeFileTransferSyncResp syncResp = qrCodeClient.fileTransfer(req);

        assertNotNull(syncResp);
    }
}
