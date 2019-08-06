package com.github.lol.pay.component.wechatpay;

import com.github.lol.pay.component.wechatpay.product.model.*;

import java.util.Map;

/**
 * WeChatPay product [F2F] interface
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:42
 **/
public interface IWeChatPayF2FClient {

    /**
     * 提交付款码支付
     * <p>
     * 收银员使用扫码设备读取微信用户付款码以后，二维码或条码信息会传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
     *
     * @param microPayReq
     * @return
     */
    Map microPay(MicroPayReq microPayReq);

    /**
     * 查询订单
     * <p>
     * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
     * 需要调用查询接口的情况：
     * <p>
     * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
     * ◆ 调用付款码支付API，返回USERPAYING的状态；
     * ◆ 调用关单或撤销接口API之前，需确认支付状态；
     *
     * @param orderQueryReq
     * @return
     */
    Map<String, String> orderQuery(OrderQueryReq orderQueryReq);

    /**
     * 撤销订单
     * <p>
     * 支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；
     * 如果用户支付成功，微信支付系统会将此订单资金退还给用户。
     * <p>
     * 注意：7天以内的交易单可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。
     * 提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。
     * <p>
     * 调用支付接口后请勿立即调用撤销订单API，建议支付后至少15s后再调用撤销订单接口。
     *
     * @param reverseReq
     * @return
     */
    Map<String, String> reverse(ReverseReq reverseReq);

    /**
     * 申请退款
     * <p>
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     * <p>
     * 注意：
     * <p>
     * 1、交易时间超过一年的订单无法提交退款
     * 2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。
     * 申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号
     * 3、请求频率限制：150qps，即每秒钟正常的申请退款请求次数不超过150次
     * 错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次
     * 4、每个支付订单的部分退款次数不能超过50次
     *
     * @param refundReq
     * @return
     */
    Map<String, String> refund(RefundReq refundReq);

    /**
     * 查询退款
     * <p>
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
     * 银行卡支付的退款3个工作日后重新查询退款状态。
     * <p>
     * 注意：如果单个支付订单部分退款次数超过20次请使用退款单号查询
     * <p>
     * 分页查询
     * 当一个订单部分退款超过10笔后，商户用微信订单号或商户订单号调退款查询API查询退款时，
     * 默认返回前10笔和total_refund_count（订单总退款次数）。商户需要查询同一订单下超过10笔的退款单时，
     * 可传入订单号及offset来查询，微信支付会返回offset及后面的10笔，以此类推。
     * 当商户传入的offset超过total_refund_count，则系统会返回报错PARAM_ERROR。
     * <p>
     * 举例：
     * <p>
     * 一笔订单下的退款单有36笔，当商户想查询第25笔时，可传入订单号及offset=24，
     * 微信支付平台会返回第25笔到第35笔的退款单信息，或商户可直接传入退款单号查询退款
     *
     * @param refundQueryReq
     * @return
     */
    Map<String, String> refundQuery(RefundQueryReq refundQueryReq);

    /**
     * 下载对账单
     * <p>
     * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
     * <p>
     * 注意：
     * <p>
     * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致；
     * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
     * 3、对账单中涉及金额的字段单位为“元”。
     * 4、对账单接口只能下载三个月以内的账单。
     * 5、对账单是以商户号纬度来生成的，如一个商户号与多个appid有绑定关系，则使用其中任何一个appid都可以请求下载对账单。
     * 对账单中的appid取自交易时候提交的appid，与请求下载对账单时使用的appid无关。
     *
     * @param downloadBillReq
     * @return
     */
    Map<String, String> downloadBill(DownloadBillReq downloadBillReq);

    /**
     * 下载资金账单
     * <p>
     * 商户可以通过该接口下载自2017年6月1日起 的历史资金流水账单。
     * <p>
     * 说明：
     * <p>
     * 1、资金账单中的数据反映的是商户微信账户资金变动情况；
     * 2、当日账单在次日上午9点开始生成，建议商户在上午10点以后获取；
     * 3、资金账单中涉及金额的字段单位为“元”。
     *
     * @param downloadBillReq
     * @return
     */
    Map<String, String> downloadFundFlow(DownloadFundFlowReq downloadBillReq);

    /**
     * 交易保障
     * <p>
     * 商户在调用微信支付提供的相关接口时，会得到微信支付返回的相关信息以及获得整个接口的响应时间。
     * 为提高整体的服务水平，协助商户一起提高服务质量，微信支付提供了相关接口调用耗时和返回信息的主动上报接口，
     * 微信支付可以根据商户侧上报的数据进一步优化网络部署，完善服务监控，和商户更好的协作为用户提供更好的业务体验。
     *
     * @param reportReq
     * @return
     */
    Map<String, String> report(ReportReq reportReq);

    /**
     * 授权码查询open id
     * <p>
     * 通过授权码查询公众号Openid，调用查询后，该授权码只能由此商户号发起扣款，直至授权码更新。
     *
     * @param authCodeToOpenIdReq
     * @return
     */
    Map<String, String> authCodeToOpenId(AuthCodeToOpenIdReq authCodeToOpenIdReq);

    /**
     * 拉取订单评价数据
     * <p>
     * 商户可以通过该接口拉取用户在微信支付交易记录中针对你的支付记录进行的评价内容。
     * 商户可结合商户系统逻辑对该内容数据进行存储、分析、展示、客服回访以及其他使用。如商户业务对评价内容有依赖，
     * 可主动引导用户进入微信支付交易记录进行评价。
     *
     * @param batchQueryCommentReq
     * @return
     */
    Map<String, String> batchQueryComment(BatchQueryCommentReq batchQueryCommentReq);
}
