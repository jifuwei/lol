package com.github.lol.pay.component.alipay.product;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.github.lol.lib.util.ReflectUtil;
import com.github.lol.pay.component.alipay.AlipayConfig;
import com.github.lol.pay.component.alipay.product.model.AlipayCoreReq;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * alipay F2F service
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:36
 **/
@Slf4j
@Data
public class AlipayCoreService implements IAlipayCoreClient {

    private AlipayConfig config;
    private AlipayClient alipayClient;


    public AlipayCoreService(@NonNull AlipayConfig config) {
        init(config);
    }

    private void init(@NonNull AlipayConfig config) {
        // 1.alipay client init
        alipayClient = new DefaultAlipayClient(config.getApiGatewayUrl(), config.getAppId(),
                config.getAppPrivateKey(), config.getParamFormat(), config.getEncoding(),
                config.getAlipayPublicKey(), config.getSignType());
    }

    @Override
    @SneakyThrows
    public AlipayTradePayResponse pay(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradePayRequest req = new AlipayTradePayRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [pay] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeQueryResponse query(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeQueryRequest req = new AlipayTradeQueryRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [query] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCancelResponse cancel(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeCancelRequest req = new AlipayTradeCancelRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [cancel] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCreateResponse create(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeCreateRequest req = new AlipayTradeCreateRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [create] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeRefundResponse refund(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeRefundRequest req = new AlipayTradeRefundRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [refund] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradePrecreateResponse preCreate(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradePrecreateRequest req = new AlipayTradePrecreateRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [preCreate] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeCloseResponse close(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeCloseRequest req = new AlipayTradeCloseRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [close] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            @NonNull AlipayCoreReq alipayCoreReq) {
        AlipayDataDataserviceBillDownloadurlQueryRequest req = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [billDownloadurlQuery] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public MonitorHeartbeatSynResponse monitorHeartbeatSyn(
            @NonNull AlipayCoreReq alipayCoreReq) {
        MonitorHeartbeatSynRequest req = new MonitorHeartbeatSynRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [monitorHeartbeatSyn] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeAppPayResponse appPay(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [appPay] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public AlipayTradeFastpayRefundQueryResponse fastpayRefundQuery(
            @NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeFastpayRefundQueryRequest req = new AlipayTradeFastpayRefundQueryRequest();
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [alipayCoreReq] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().execute(req);
    }

    @Override
    @SneakyThrows
    public String wapPay(@NonNull AlipayCoreReq alipayCoreReq) {
        AlipayTradeWapPayRequest req = new AlipayTradeWapPayRequest();

        setUrl(req, alipayCoreReq);
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [wapPay] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().pageExecute(req).getBody();
    }

    @Override
    @SneakyThrows
    public String pagePay(AlipayCoreReq alipayCoreReq) {
        AlipayTradePagePayRequest req = new AlipayTradePagePayRequest();

        setUrl(req, alipayCoreReq);
        req.setBizContent(alipayCoreReq.getBizContent());
        log.debug("Alipay api [%s] [pagePay] req: {}", alipayCoreReq.getProductId(), req.getBizContent());

        return this.getAlipayClient().pageExecute(req).getBody();
    }

    private void setUrl(Object req, AlipayCoreReq alipayCoreReq) {
        String frontUrl = getConfigUrl("frontUrl", alipayCoreReq.getProductId(), alipayCoreReq.getMethodName());
        String backUrl = getConfigUrl("backUrl", alipayCoreReq.getProductId(), alipayCoreReq.getMethodName());

        ReflectUtil.invokeDeclaredMethod(req, "setReturnUrl", new Class[]{String.class}, new Object[]{frontUrl});
        ReflectUtil.invokeDeclaredMethod(req, "setNotifyUrl", new Class[]{String.class}, new Object[]{backUrl});
    }

    private String getConfigUrl(@NonNull String type, @NonNull String productId, @NonNull String method) {
        String cacheKey = String.format("%s.%s.%s", productId, method, type);

        Map<String, String> urlMap = "frontUrl".equalsIgnoreCase(type) ?
                config.getFrontUrlMap() : config.getBackUrlMap();

        if (Objects.isNull(urlMap) || Objects.isNull(urlMap.get(cacheKey))) {
            log.warn("[AlipayConfig] urlMap key: {} not exist", cacheKey);
        }

        return urlMap.get(cacheKey);
    }
}
