package com.github.lol.pay.component.unionpay.product.gateway.impl;

import com.github.lol.pay.component.unionpay.CacheUnionpayProductFactory;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import com.github.lol.pay.component.unionpay.UnionpayGlobalConfig;
import com.github.lol.pay.component.unionpay.client.IUnionpayGatewayClient;
import com.github.lol.pay.component.unionpay.constant.UnionpayConstant;
import com.github.lol.pay.component.unionpay.constant.UnionpayProductEnum;
import com.github.lol.pay.component.unionpay.product.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.model.*;
import com.github.lol.pay.component.unionpay.util.PackUtil;
import com.github.pay.component.core.IUnionPayProductFactory;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class UnionpayGatewayServiceTest {

    private UnionpayConfig config = UnionpayGlobalConfig.init();

    private IUnionpayGatewayClient gatewayClient;

    @Before
    public void before() {
        IUnionPayProductFactory unionPayProductFactory = new CacheUnionpayProductFactory(config);
        gatewayClient = (IUnionpayGatewayClient) unionPayProductFactory.produce(UnionpayProductEnum.GATEWAY.name());
    }

    @Test
    public void consume() {
        GatewayConsumeReq gatewayConsumeReq = GatewayConsumeReq.of(config)
                .orderId("jfw123456799")
                .txnTime("20190722100412")
                .txnAmt("10000000")
                .frontUrl("http://www.lol.com/gateway/frontback")
                .backUrl("http://www.lol.com/gateway/callback")
                .payTimeout(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000))
                .build();

        FormReq formReq = gatewayClient.consume(gatewayConsumeReq);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }

    @Test
    public void cancelConsume() {
        GatewayCancelConsumeReq req = GatewayCancelConsumeReq.of(config)
                .orderId("cjfw123456712")
                .txnTime("20190716170412")
                .txnAmt("10000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("561907111704129975038")
                .build();

        GatewayCancelConsumeSyncResp syncResp = gatewayClient.cancelConsume(req);

        assertNotNull(syncResp);
    }

    @Test
    public void backConsume() {
        GatewayBackConsumeReq req = GatewayBackConsumeReq.of(config)
                .orderId("bjfw123456712")
                .txnTime("20190716170412")
                .txnAmt("10000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("041907111704129836258")
                .build();

        GatewayBackConsumeSyncResp syncResp = gatewayClient.backConsume(req);

        assertNotNull(syncResp);
    }

    @Test
    public void transactionStatusQuery() {

        /**
         * orderId & txnTime 必须待查询交易单完全一致
         */
        GatewayTransactionStatusQueryReq req = GatewayTransactionStatusQueryReq.of(config)
                .orderId("jfw123456714")
                .txnTime("20190719170412")
                .build();

        GatewayTransactionStatusQuerySyncResp syncResp = gatewayClient.transactionStatusQuery(req);

        assertNotNull(syncResp);
    }

    @Test
    public void encryptInfoUpdate() {
        GatewayEncryptInfoUpdateReq req = GatewayEncryptInfoUpdateReq.of(config)
                .orderId("ejfw123456789")
                .txnTime("20190719170412")
                .build();

        GatewayEncryptInfoUpdateSyncResp syncResp = gatewayClient.encryptInfoUpdate(req);

        assertNotNull(syncResp);
    }

    @Test
    public void preAuth() {
        GatewayPreAuthReq req = GatewayPreAuthReq.of(config)
                .orderId("pjfw123456710")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .frontUrl("http://www.lol.com/gateway/frontback")
                .backUrl("http://www.lol.com/gateway/callback")
                .payTimeout(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000))
                .accType("01")
                .build();

        FormReq formReq = gatewayClient.preAuth(req);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }

    @Test
    public void cancelPreAuth() {
        GatewayCancelPreAuthReq req = GatewayCancelPreAuthReq.of(config)
                .orderId("ejfw123456789")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("421907191704120442528")
                .build();

        GatewayCancelPreAuthSyncResp syncResp = gatewayClient.cancelPreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void completePreAuth() {
        GatewayCompletePreAuthReq req = GatewayCompletePreAuthReq.of(config)
                .orderId("cpjfw123456710")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("711907191704128948768")
                .build();

        GatewayCompletePreAuthSyncResp syncResp = gatewayClient.completePreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void cancelCompletedPreAuth() {
        GatewayCancelCompletedPreAuthReq req = GatewayCancelCompletedPreAuthReq.of(config)
                .orderId("ccpjfw123456789")
                .txnTime("20190719170412")
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("711907191704128948768")
                .build();

        GatewayCancelCompletedPreAuthSyncResp syncResp = gatewayClient.cancelCompletedPreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void fileTransfer() {
        GatewayFileTransferReq req = GatewayFileTransferReq.of(config)
                .txnTime("20190719170412")
                .settleDate("0119")
                .build();

        GatewayFileTransferSyncResp syncResp = gatewayClient.fileTransfer(req);

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
