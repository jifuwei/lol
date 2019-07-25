package com.github.lol.pay.component.unionpay.product.qrcode.impl;

import com.github.lol.pay.component.unionpay.CacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.UnionpayGlobalConfig;
import com.github.lol.pay.component.unionpay.client.IUnionpayQRCodeClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.qrcode.model.*;
import com.github.lol.pay.core.IProductFactory;
import com.github.lol.pay.core.SimpleOrderIdGenerator;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.DEFAULT_DATE_TIME_FORMAT;
import static org.junit.Assert.assertNotNull;

/**
 * 二维码支付-测试
 * <p>
 * 注意：
 * 如何集成产品测试权限？
 * 测试api返回无权限等错误信息时，测试请注意，应关注当前测试的是那款产品，然后前往【商户测试中心】-【我的产品】-【未测试】，
 * 在列表中选择需要的测试产品，点击【开始测试】，约等待20分钟后，再次尝试即可授权
 *
 * @author jifuwei
 * @create: 2019-07-12 13:45
 */
public class UnionpayQRCodeServiceTest {

    private UnionpayConfig config = UnionpayGlobalConfig.init();

    private IUnionpayQRCodeClient qrCodeClient;

    @Before
    public void before() {
        IProductFactory unionPayProductFactory = new CacheUnionpayProductFactory(config);
        qrCodeClient = (IUnionpayQRCodeClient) unionPayProductFactory.produce(UnionpayProductEnum.QR_CODE.name());
    }

    @Test
    public void cancelConsume() {
        QRCodeCancelConsumeReq req = QRCodeCancelConsumeReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.QR_CODE.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
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
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.QR_CODE.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
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
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.QR_CODE.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("500000")
                .backUrl("http://www.lol.com/gateway/callback")
                .build();

        QRCodeMasterImgSyncResp syncResp = qrCodeClient.masterImg(req);

        assertNotNull(syncResp);
    }

    @Test
    public void slaveImg() {
        QRCodeSlaveImgReq req = QRCodeSlaveImgReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.QR_CODE.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
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
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .build();

        QRCodeFileTransferSyncResp syncResp = qrCodeClient.fileTransfer(req);

        assertNotNull(syncResp);
    }
}
