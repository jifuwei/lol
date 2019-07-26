package com.github.lol.pay.component.alipay.product;

import com.alipay.api.response.*;
import com.github.lol.pay.component.alipay.product.model.AlipayCoreReq;

/**
 * alipay core client
 *
 * @author: jifuwei
 * @create: 2019-07-24 14:40
 **/
public interface IAlipayCoreClient {

    /**
     * 统一收单交易支付接口（条码支付）
     * <p>
     * 收银员使用扫码设备读取用户手机支付宝“付款码”/声波获取设备（如麦克风）读取用户手机支付宝的声波信息后，
     * 将二维码或条码信息/声波信息通过本接口上送至支付宝发起支付。
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradePayResponse pay(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单线下交易查询
     * <p>
     * 该接口提供所有支付宝支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
     * 需要调用查询接口的情况： 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知； 调用支付接口后，
     * 返回系统错误或未知交易状态情况； 调用alipay.trade.pay，返回INPROCESS的状态； 调用alipay.trade.cancel之前，
     * 需确认支付状态；
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeQueryResponse query(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单交易撤销接口
     * <p>
     * 支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，支付宝系统会将此订单关闭；
     * 如果用户支付成功，支付宝系统会将此订单资金退还给用户。 注意：只有发生支付系统超时或者支付结果未知时可调用撤销，
     * 其他正常支付的单如需实现相同功能请调用申请退款API。提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeCancelResponse cancel(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单交易创建接口
     * <p>
     * 商户通过该接口进行交易的创建下单
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeCreateResponse create(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单交易退款接口
     * <p>
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 支付宝将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     * 交易超过约定时间（签约时设置的可退款时间）的订单无法进行退款 支付宝退款支持单笔交易分多次退款，
     * 多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。
     * 总退款金额不能超过用户实际支付金额
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeRefundResponse refund(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单线下交易预创建（扫码支付）
     * <p>
     * 收银员通过收银台或商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradePrecreateResponse preCreate(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单交易关闭接口
     * <p>
     * 用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeCloseResponse close(AlipayCoreReq alipayCoreReq);

    /**
     * 查询对账单下载地址接口
     * <p>
     * 为方便商户快速查账，支持商户通过本接口获取商户离线账单下载地址
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadurlQuery(AlipayCoreReq alipayCoreReq);

    /**
     * 交易保障接口
     * <p>
     * 支付宝将大数据和监控开放给商户/ISV。为了保障商户收银效果，要求每30分钟（或小于30分钟）从终端同步支付宝交易性能和异常信息。
     * 支付宝将该数据和支付宝内数据有机整合为商户/ISV提供实时监控能力，为线下收银保障护航。
     *
     * @param alipayCoreReq
     * @return
     */
    MonitorHeartbeatSynResponse monitorHeartbeatSyn(AlipayCoreReq alipayCoreReq);

    /**
     * app支付接口
     * <p>
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeAppPayResponse appPay(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单交易退款查询
     * <p>
     * 商户可使用该接口查询自已通过alipay.trade.refund或alipay.trade.refund.apply提交的退款请求是否执行成功。
     * 该接口的返回码10000，仅代表本次查询操作成功，不代表退款成功。如果该接口返回了查询数据，
     * 且refund_status为空或为REFUND_SUCCESS，则代表退款成功，如果没有查询到则代表未退款成功，
     * 可以调用退款接口进行重试。重试时请务必保证退款请求号一致。
     *
     * @param alipayCoreReq
     * @return
     */
    AlipayTradeFastpayRefundQueryResponse fastpayRefundQuery(AlipayCoreReq alipayCoreReq);

    /**
     * 手机网站支付
     * <p>
     * 外部商户创建订单并支付
     *
     * @param alipayCoreReq
     * @return
     */
    String wapPay(AlipayCoreReq alipayCoreReq);

    /**
     * 统一收单下单并支付页面接口
     * <p>
     * PC场景下单并支付
     *
     * @param alipayCoreReq
     * @return
     */
    String pagePay(AlipayCoreReq alipayCoreReq);
}
