package com.github.lol.pay.component.alipay.client;

import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.product.model.*;

/**
 * alipay F2F client
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:40
 **/
public interface IAlipayAppClient {

    /**
     * app支付接口
     * <p>
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param appPayBizContentReq
     * @return
     */
    AlipayTradeAppPayResponse appPay(AppPayBizContentReq appPayBizContentReq);

    /**
     * 统一收单线下交易查询
     * <p>
     * 该接口提供所有支付宝支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
     * 需要调用查询接口的情况： 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知； 调用支付接口后，
     * 返回系统错误或未知交易状态情况； 调用alipay.trade.pay，返回INPROCESS的状态； 调用alipay.trade.cancel之前，
     * 需确认支付状态；
     *
     * @param queryBizContentReq
     * @return
     */
    AlipayTradeQueryResponse query(QueryBizContentReq queryBizContentReq);

    /**
     * 统一收单交易退款接口
     * <p>
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 支付宝将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     * 交易超过约定时间（签约时设置的可退款时间）的订单无法进行退款 支付宝退款支持单笔交易分多次退款，
     * 多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。
     * 总退款金额不能超过用户实际支付金额
     *
     * @param refundBizContentReq
     * @return
     */
    AlipayTradeRefundResponse refund(RefundBizContentReq refundBizContentReq);

    /**
     * 统一收单交易退款查询
     * <p>
     * 商户可使用该接口查询自已通过alipay.trade.refund或alipay.trade.refund.apply提交的退款请求是否执行成功。
     * 该接口的返回码10000，仅代表本次查询操作成功，不代表退款成功。如果该接口返回了查询数据，
     * 且refund_status为空或为REFUND_SUCCESS，则代表退款成功，如果没有查询到则代表未退款成功，
     * 可以调用退款接口进行重试。重试时请务必保证退款请求号一致。
     *
     * @return
     */
    AlipayTradeFastpayRefundQueryResponse fastpayRefundQuery(
            FastpayRefundQueryBizContentReq fastpayRefundQueryBizContentReq);

    /**
     * 统一收单交易关闭接口
     * <p>
     * 用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     *
     * @param closeBizContentReq
     * @return
     */
    AlipayTradeCloseResponse close(CloseBizContentReq closeBizContentReq);

    /**
     * 查询对账单下载地址接口
     * <p>
     * 为方便商户快速查账，支持商户通过本接口获取商户离线账单下载地址
     *
     * @param billDownloadUrlQueryBizContentReq
     * @return
     */
    AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(
            BillDownloadUrlQueryBizContentReq billDownloadUrlQueryBizContentReq);
}
