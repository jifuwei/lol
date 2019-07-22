package com.github.lol.pay.component.unionpay.client;

import com.github.lol.pay.component.unionpay.product.qrcode.model.*;

/**
 * unionpay qr code client
 *
 * @author: jifuwei
 * @create: 2019-07-21 14:55
 **/
public interface IUnionpayQRCodeClient {

    /**
     * 在消费交易当天，由于持卡人或者商户的原因需要退款时，商户可以通过消费撤销接口将支付款退还给持卡人，
     * 银联将在收到消费撤销请求并且验证成功之后，按照消费撤销规则让发卡行按照原路退到持卡人支付卡上。
     * 注意：
     * 1.原交易收到明确成功应答的可以发起消费撤销；交易状态未明的需要发起冲正交易；
     * 2.撤销必须与原始消费在同一天（准确讲是前日23:00至本日23:00之间），原消费交易时间超过23:00的订单无法提交消费撤销请求；
     * 3.	需要提交原消费交易的商户订单号或者原始交易的流水号(具体地，原始消费交易的origQryId、origOrderId + origTxnTime二者必送其一)；
     * 4.消费撤销的金额必须等于原消费订单的金额；
     * 5.已发退货的交易不能发消费撤销；
     * 6.消费撤销退款到账是实时的。
     *
     * @param cancelConsumeReq
     * @return
     */
    QRCodeCancelConsumeSyncResp cancelConsume(QRCodeCancelConsumeReq cancelConsumeReq);

    /**
     * 在消费交易发生一段时间之内，由于持卡人或者商户的原因需要退款时，商户可以通过退货接口将支付款退还给持卡人，
     * 银联将在收到退货请求并且验证成功之后，按照退货规则让发卡行按照原路退到持卡人支付卡上。
     * 注意：
     * 1.	原交易收到明确成功应答的可以发起退货；交易状态未明的需要发起冲正交易；
     * 2.	原消费交易时间超过11个月的订单无法提交退货请求，超过11个月走差错平台处理；
     * 3.	支持单笔消费交易分支持部分退货、多次退货，多次退货需要提交原消费交易的商户订单号或者原始交易的流水号(具体地，
     * 原始消费交易的origQryId、origOrderId + origTxnTime二者必送其一)，并设置不同的退货订单号。申请退货的总金额不能超过原消费订单金额；
     * 4.	已发消费撤销且成功的的没法退货；同样地，已发退货的不能发消费撤销；
     * 5.	退款到账时间以银行为准，一般1-5个工作日。
     *
     * @param backConsumeReq
     * @return
     */
    QRCodeBackConsumeSyncResp backConsume(QRCodeBackConsumeReq backConsumeReq);

    /**
     * 商户申请二维码，同步应答二维码信息；供持卡人扫码支付；持卡人每次支付完成，银联通过后台地址发送商户通知。
     *
     * @param masterImgReq
     * @return
     */
    QRCodeMasterImgSyncResp masterImg(QRCodeMasterImgReq masterImgReq);

    /**
     * 持卡人被商户用扫描枪扫码，发起支付。银联收到消费请求后将立刻返回同步应答，然后继续处理消费交易，
     * 当消费交处理完毕后再通过消费结果通知将交易结果返回给商户系统。
     *
     * @param slaveImgReq
     * @return
     */
    QRCodeSlaveImgSyncResp slaveImg(QRCodeSlaveImgReq slaveImgReq);

    /**
     * 该接口提供所有银联订单的查询，包括支付、退货、消费撤销交易。商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
     *
     * @param transactionStatusQueryReq
     * @return
     */
    QRCodeTransactionStatusQuerySyncResp transactionStatusQuery(QRCodeTransactionStatusQueryReq transactionStatusQueryReq);

    /**
     * 冲正必须与原始消费在同一天（准确讲是昨日23:00至本日23:00之间）。 冲正交易，仅用于超时无应答等异常场景，
     * 只有发生支付系统超时或者支付结果未知时可调用冲正，其他正常支付的订单如果需要实现相通功能，请调用消费撤销或者退货。
     *
     * @param reversalReq
     * @return
     */
    QRCodeReversalSyncResp reversal(QRCodeReversalReq reversalReq);

    /**
     * 文件传输类交易接口
     *
     * @param fileTransferReq
     * @return
     */
    QRCodeFileTransferSyncResp fileTransfer(QRCodeFileTransferReq fileTransferReq);
}
