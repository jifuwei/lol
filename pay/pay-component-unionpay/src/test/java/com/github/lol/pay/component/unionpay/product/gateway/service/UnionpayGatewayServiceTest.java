package com.github.lol.pay.component.unionpay.product.gateway.service;

import com.github.lol.pay.component.unionpay.IUnionPayProductFactory;
import com.github.lol.pay.component.unionpay.SimpleCacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.constant.UnionpayConstant;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.product.common.model.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.IUnionGatewayClient;
import com.github.lol.pay.component.unionpay.product.gateway.model.*;
import com.github.lol.pay.component.unionpay.util.PackUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class UnionpayGatewayServiceTest {

    private UnionpayConfig config = new UnionpayConfig();

    private IUnionPayProductFactory unionPayProductFactory = SimpleCacheUnionpayProductFactory.getInstance(config);

    @Test
    public void consume() {
        ConsumeReq consumeReq = ConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("01")
                .txnSubType("01")
                .bizType("000201")
                .channelType("07")
                .merId(config.getMerId())
                .accessType(config.getAccessType())
                .orderId("jfw123456714")
                .txnTime("20190719170412")
                .currencyCode(config.getCurrencyCode())
                .txnAmt("10000000")
                .riskRateInfo("{commodityName=测试商品名称}")
                .frontUrl("http://www.lol.com/gateway/frontback")
                .backUrl("http://www.lol.com/gateway/callback")
                .payTimeout(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000))
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        FormReq formReq = gatewayClient.consume(consumeReq);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }

    @Test
    public void cancelConsume() {
        CancelConsumeReq req = CancelConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("31")
                .txnSubType("00")
                .bizType("000000")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("cjfw123456712")
                .txnTime("20190716170412")
                .txnAmt("10000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("561907111704129975038")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        CancelConsumeSyncResp syncResp = gatewayClient.cancelConsume(req);

        assertNotNull(syncResp);
    }

    @Test
    public void backConsume() {
        BackConsumeReq req = BackConsumeReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("04")
                .txnSubType("00")
                .bizType("000000")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("bjfw123456712")
                .txnTime("20190716170412")
                .txnAmt("10000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("041907111704129836258")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        BackConsumeSyncResp syncResp = gatewayClient.backConsume(req);

        assertNotNull(syncResp);
    }

    @Test
    public void transactionStatusQuery() {

        /**
         * orderId & txnTime 必须待查询交易单完全一致
         */
        TransactionStatusQueryReq req = TransactionStatusQueryReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("00")
                .txnSubType("00")
                .bizType("000000")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("jfw123456714")
                .txnTime("20190719170412")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        TransactionStatusQuerySyncResp syncResp = gatewayClient.transactionStatusQuery(req);

        assertNotNull(syncResp);
    }

    @Test
    public void encryptInfoUpdate() {
        EncryptInfoUpdateReq req = EncryptInfoUpdateReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("95")
                .txnSubType("00")
                .bizType("000000")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .certType("01")
                .orderId("ejfw123456789")
                .txnTime("20190719170412")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        EncryptInfoUpdateSyncResp syncResp = gatewayClient.encryptInfoUpdate(req);

        assertNotNull(syncResp);
    }

    @Test
    public void preAuth() {
        PreAuthReq req = PreAuthReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("02")
                .txnSubType("01")
                .bizType("000201")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("pjfw123456710")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .currencyCode("156")
                .frontUrl("http://www.lol.com/gateway/frontback")
                .backUrl("http://www.lol.com/gateway/callback")
                .payTimeout(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000))
                .accType("01")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        FormReq formReq = gatewayClient.preAuth(req);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }

    @Test
    public void cancelPreAuth() {
        CancelPreAuthReq req = CancelPreAuthReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("32")
                .txnSubType("00")
                .bizType("000000")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("ejfw123456789")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("421907191704120442528")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        CancelPreAuthSyncResp syncResp = gatewayClient.cancelPreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void completePreAuth() {
        CompletePreAuthReq req = CompletePreAuthReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("03")
                .txnSubType("00")
                .bizType("000301")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("cpjfw123456710")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("711907191704128948768")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        CompletePreAuthSyncResp syncResp = gatewayClient.completePreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void cancelCompletedPreAuth() {
        CancelCompletedPreAuthReq req = CancelCompletedPreAuthReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("33")
                .txnSubType("00")
                .bizType("000000")
                .channelType("07")
                .merId(config.getMerId())
                .accessType("0")
                .orderId("ccpjfw123456789")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("711907191704128948768")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        CancelCompletedPreAuthSyncResp syncResp = gatewayClient.cancelCompletedPreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void fileTransfer() {
        FileTransferReq req = FileTransferReq.builder()
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("76")
                .txnSubType("01")
                .bizType("000000")
                .merId(config.getMerId())
                .accessType("0")
                .txnTime("20190719170412")
                .settleDate("0119")
                .fileType("00")
                .build();

        IUnionGatewayClient gatewayClient = unionPayProductFactory.produce(UnionpayGatewayService.class);
        FileTransferSyncResp syncResp = gatewayClient.fileTransfer(req);

        assertNotNull(syncResp);

        if (!"00".equals(syncResp.getRespCode())) {
            return;
        }

        // 1.解析并生成压缩文件
        String savePath = PackUtil.doCodeFileContent(syncResp,
                "/Users/lisi/unionfile", UnionpayConstant.UTF_8_ENCODING);

        // 2.落地压缩文件解压并解析
        List<String> fileList = PackUtil.unzip(savePath, "/Users/lisi/unionfile");

        // 3.解析ZM, ZME文件
        String fileContentDispaly = "获取到商户对账文件，并落地到" + savePath + ",并解压缩\n";
        for (String file : fileList) {
            if (file.contains("ZM_")) {
                List<Map> ZmDataList = PackUtil.parseZMFile(file);
                fileContentDispaly = fileContentDispaly + PackUtil.getFileContentTable(ZmDataList);
            } else if (file.contains("ZME_")) {
                List<Map> ZmDataList = PackUtil.parseZMEFile(file);
                fileContentDispaly = fileContentDispaly + PackUtil.getFileContentTable(ZmDataList);
            }
        }

        System.out.println("==> " + fileContentDispaly);
    }
}
