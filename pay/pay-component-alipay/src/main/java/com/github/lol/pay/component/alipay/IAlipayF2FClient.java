package com.github.lol.pay.component.alipay;

import com.alipay.api.request.*;
import com.alipay.api.response.*;

/**
 * alipay F2F client
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:40
 **/
public interface IAlipayF2FClient {

    /**
     * 统一收单交易支付接口（条码支付）通过调用该接口创建支付宝交易订单
     *
     * @param alipayTradePayRequest
     * @return
     */
    AlipayTradePayResponse pay(AlipayTradePayRequest alipayTradePayRequest);

    /**
     * 统一收单线下交易查询 通过调用该接口查询订单的状态
     *
     * @param alipayTradeQueryRequest
     * @return
     */
    AlipayTradeQueryResponse query(AlipayTradeQueryRequest alipayTradeQueryRequest);

    /**
     * 统一收单交易撤销接口 通过调用该接口撤销订单
     *
     * @param alipayTradeCancelRequest
     * @return
     */
    AlipayTradeCancelResponse cancel(AlipayTradeCancelRequest alipayTradeCancelRequest);

    /**
     * 统一收单交易创建接口 创建支付宝交易订单
     *
     * @param alipayTradeCreateRequest
     * @return
     */
    AlipayTradeCreateResponse create(AlipayTradeCreateRequest alipayTradeCreateRequest);

    /**
     * 统一收单交易退款接口 支持部分和全部退款
     *
     * @param alipayTradeRefundRequest
     * @return
     */
    AlipayTradeRefundResponse refund(AlipayTradeRefundRequest alipayTradeRefundRequest);

    /**
     * 统一收单线下交易预创建（扫码支付） 创建支付宝交易订单（扫码）
     *
     * @param alipayTradePrecreateRequest
     * @return
     */
    AlipayTradePrecreateResponse preCreate(AlipayTradePrecreateRequest alipayTradePrecreateRequest);

    /**
     * 统一收单交易关闭接口 交易关闭接口
     *
     * @param alipayTradeCloseRequest
     * @return
     */
    AlipayTradeCloseResponse close(AlipayTradeCloseRequest alipayTradeCloseRequest);

    /**
     * 查询对账单下载地址接口 查询并下载对账单
     *
     * @param billDownloadurlQueryRequest
     * @return
     */
    AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            AlipayDataDataserviceBillDownloadurlQueryRequest billDownloadurlQueryRequest);

    /**
     * 交易保障接口 交易保障接口
     *
     * @param monitorHeartbeatSynRequest
     * @return
     */
    MonitorHeartbeatSynResponse monitorHeartbeatSyn(MonitorHeartbeatSynRequest monitorHeartbeatSynRequest);
}
