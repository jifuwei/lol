package com.github.lol.pay.component.alipay.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.product.model.*;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * alipay F2F service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
@Slf4j
@Data
public class AlipayCoreService implements IAlipayCoreClient {

    private AlipayClient alipayClient;
    private SerializeConfig serializeConfig;

    public AlipayCoreService(@NonNull AlipayConfig config) {
        init(config);
    }

    private void init(@NonNull AlipayConfig config) {
        // 1.alipay client init
        alipayClient = new DefaultAlipayClient(config.getApiGatewayUrl(), config.getAppId(),
                config.getAppPrivateKey(), config.getParamFormat(), config.getEncoding(),
                config.getAlipayPublicKey(), config.getSignType());

        // 2.序列化对象
        // config要做singleton处理，要不然会存在性能问题
        serializeConfig = new SerializeConfig();
        // 驼峰转下划线
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    @Override
    @SneakyThrows
    public AlipayTradePayResponse pay(@NonNull PayBizContentReq payBizContentReq) {
        AlipayTradePayRequest req = new AlipayTradePayRequest();
        req.setBizContent(JSON.toJSONString(payBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [pay] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeQueryResponse query(@NonNull QueryBizContentReq queryBizContentReq) {
        AlipayTradeQueryRequest req = new AlipayTradeQueryRequest();
        req.setBizContent(JSON.toJSONString(queryBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [query] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCancelResponse cancel(@NonNull CancelBizContentReq cancelBizContentReq) {
        AlipayTradeCancelRequest req = new AlipayTradeCancelRequest();
        req.setBizContent(JSON.toJSONString(cancelBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [cancel] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCreateResponse create(@NonNull CreateBizContentReq createBizContentReq) {
        AlipayTradeCreateRequest req = new AlipayTradeCreateRequest();
        req.setBizContent(JSON.toJSONString(createBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [create] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeRefundResponse refund(@NonNull RefundBizContentReq refundBizContentReq) {
        AlipayTradeRefundRequest req = new AlipayTradeRefundRequest();
        req.setBizContent(JSON.toJSONString(refundBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [refund] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradePrecreateResponse preCreate(@NonNull PreCreateBizContentReq preCreateBizContentReq) {
        AlipayTradePrecreateRequest req = new AlipayTradePrecreateRequest();
        req.setBizContent(JSON.toJSONString(preCreateBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [preCreate] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCloseResponse close(@NonNull CloseBizContentReq closeBizContentReq) {
        AlipayTradeCloseRequest req = new AlipayTradeCloseRequest();
        req.setBizContent(JSON.toJSONString(closeBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [close] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            @NonNull BillDownloadUrlQueryBizContentReq billDownloadUrlQueryBizContentReq) {
        AlipayDataDataserviceBillDownloadurlQueryRequest req = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        req.setBizContent(JSON.toJSONString(billDownloadUrlQueryBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [billDownloadurlQuery] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public MonitorHeartbeatSynResponse monitorHeartbeatSyn(
            @NonNull MonitorHeartbeatSynBizContentReq monitorHeartbeatSynBizContentReq) {
        MonitorHeartbeatSynRequest req = new MonitorHeartbeatSynRequest();
        req.setBizContent(JSON.toJSONString(monitorHeartbeatSynBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [monitorHeartbeatSyn] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeAppPayResponse appPay(@NonNull AppPayBizContentReq appPayBizContentReq) {
        AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
        req.setBizContent(JSON.toJSONString(appPayBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [appPay] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeFastpayRefundQueryResponse fastpayRefundQuery(
            @NonNull FastpayRefundQueryBizContentReq fastpayRefundQueryBizContentReq) {
        AlipayTradeFastpayRefundQueryRequest req = new AlipayTradeFastpayRefundQueryRequest();
        req.setBizContent(JSON.toJSONString(fastpayRefundQueryBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [fastpayRefundQuery] req: {}", req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public String wapPay(WapPayBizContentReq wapPayBizContentReq) {
        AlipayTradeWapPayRequest req = new AlipayTradeWapPayRequest();
        req.setBizContent(JSON.toJSONString(wapPayBizContentReq, this.getSerializeConfig()));
        log.debug("Alipay api [wapPay] req: {}", req.getBizContent());

        return this.getAlipayClient().pageExecute(req).getBody();
    }
}
