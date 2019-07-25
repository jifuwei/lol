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
import com.github.lol.pay.core.IProductFactory;
import com.github.lol.pay.core.SimpleOrderIdGenerator;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.DEFAULT_DATE_TIME_FORMAT;
import static org.junit.Assert.assertNotNull;

/**
 * 在线网关支付-测试
 * <p>
 * 注意：
 * 如何集成产品测试权限？
 * 测试api返回无权限等错误信息时，测试请注意，应关注当前测试的是那款产品，然后前往【商户测试中心】-【我的产品】-【未测试】，
 * 在列表中选择需要的测试产品，点击【开始测试】，约等待20分钟后，再次尝试即可授权
 *
 * @author jifuwei
 * @create: 2019-07-12 13:45
 */
public class UnionpayGatewayServiceTest {

    private UnionpayConfig config = UnionpayGlobalConfig.init();

    private IUnionpayGatewayClient gatewayClient;

    @Before
    public void before() {
        IProductFactory unionPayProductFactory = new CacheUnionpayProductFactory(config);
        gatewayClient = (IUnionpayGatewayClient) unionPayProductFactory.produce(UnionpayProductEnum.GATEWAY.name());
    }

    /**
     * orderId = GATEWAY20190723165305uFt1
     * txnTime = 20190723165305
     * <p>
     * 这个两个测试参数后续会用到，测试时建议手动保存
     */
    @Test
    public void consume() {
        GatewayConsumeReq gatewayConsumeReq = GatewayConsumeReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("10000000")
                .frontUrl("http://www.lol.com/gateway/frontback")
                .backUrl("http://www.lol.com/gateway/callback")
                .payTimeout(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(new Date().getTime() + 15 * 60 * 1000))
                .build();

        FormReq formReq = gatewayClient.consume(gatewayConsumeReq);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }

    /**
     * txnAmt 必须与原始订单相同，否则会报错
     * origQryId 原始订单查询id, 可通过状态接口查询得到
     */
    @Test
    public void cancelConsume() {
        GatewayCancelConsumeReq req = GatewayCancelConsumeReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("10000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("461907231653058181258")
                .build();

        GatewayCancelConsumeSyncResp syncResp = gatewayClient.cancelConsume(req);

        assertNotNull(syncResp);
    }

    /**
     * txnAmt 必须与原始订单相同，否则会报错
     * origQryId 原始订单查询id, 可通过状态接口查询得到
     */
    @Test
    public void backConsume() {
        GatewayBackConsumeReq req = GatewayBackConsumeReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("10000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("461907231653058181258")
                .build();

        GatewayBackConsumeSyncResp syncResp = gatewayClient.backConsume(req);

        assertNotNull(syncResp);
    }

    /**
     * orderId & txnTime 必须与被查询交易单完全一致
     */
    @Test
    public void transactionStatusQuery() {
        GatewayTransactionStatusQueryReq req = GatewayTransactionStatusQueryReq.of(config)
                .orderId("GATEWAY20190723170401rbi6")
                .txnTime("20190723170401")
                .build();

        GatewayTransactionStatusQuerySyncResp syncResp = gatewayClient.transactionStatusQuery(req);

        assertNotNull(syncResp);
    }

    @Test
    public void encryptInfoUpdate() {
        GatewayEncryptInfoUpdateReq req = GatewayEncryptInfoUpdateReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .build();

        GatewayEncryptInfoUpdateSyncResp syncResp = gatewayClient.encryptInfoUpdate(req);

        assertNotNull(syncResp);
    }

    /**
     * orderId = GATEWAY20190723170401rbi6
     * txnTime = 20190723170401
     */
    @Test
    public void preAuth() {
        GatewayPreAuthReq req = GatewayPreAuthReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("5000000")
                .frontUrl("http://www.lol.com/gateway/frontback")
                .backUrl("http://www.lol.com/gateway/callback")
                .payTimeout(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(new Date().getTime() + 15 * 60 * 1000))
                .accType("01")
                .build();

        FormReq formReq = gatewayClient.preAuth(req);
        System.out.println("html: " + formReq.buildAutoFormHtml());

        assertNotNull(formReq);
    }

    @Test
    public void cancelPreAuth() {
        GatewayCancelPreAuthReq req = GatewayCancelPreAuthReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("531907231704018461268")
                .build();

        GatewayCancelPreAuthSyncResp syncResp = gatewayClient.cancelPreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void completePreAuth() {
        GatewayCompletePreAuthReq req = GatewayCompletePreAuthReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("531907231704018461268")
                .build();

        GatewayCompletePreAuthSyncResp syncResp = gatewayClient.completePreAuth(req);

        assertNotNull(syncResp);
    }

    @Test
    public void cancelCompletedPreAuth() {
        GatewayCancelCompletedPreAuthReq req = GatewayCancelCompletedPreAuthReq.of(config)
                .orderId(SimpleOrderIdGenerator.get(UnionpayProductEnum.GATEWAY.name()))
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
                .txnAmt("5000000")
                .backUrl("http://www.lol.com/gateway/callback")
                .origQryId("531907231704018461268")
                .build();

        GatewayCancelCompletedPreAuthSyncResp syncResp = gatewayClient.cancelCompletedPreAuth(req);

        assertNotNull(syncResp);
    }

    /**
     * 测试环境，申请的测试商户是没办法拉取文件的，建议商户号换成银联测试共享商户号：
     * 商户号- 700000000000001
     * 拉取日期 - 0119
     */
    @Test
    public void fileTransfer() {
        GatewayFileTransferReq req = GatewayFileTransferReq.of(config)
                .txnTime(DateTime.now().toString(DEFAULT_DATE_TIME_FORMAT))
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
