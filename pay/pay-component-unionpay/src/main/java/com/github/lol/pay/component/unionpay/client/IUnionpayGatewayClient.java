package com.github.lol.pay.component.unionpay.client;

import com.github.lol.pay.component.unionpay.product.FormReq;
import com.github.lol.pay.component.unionpay.product.gateway.model.*;

/**
 * unionpay gateway client
 * <p>
 * product: 在线网关支付
 * docs reference: https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=275&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-15 14:21
 **/
public interface IUnionpayGatewayClient {

    /**
     * 消费是指境内外持卡人在境内外商户网站进行购物等消费时用银行卡结算的交易，
     * 经批准的消费额将即时 地反映到该持卡人的账户余额上。
     *
     * @param gatewayConsumeReq
     * @return
     */
    FormReq consume(GatewayConsumeReq gatewayConsumeReq);

    /**
     * 是指因人为原因而撤销已完成的消费，商户可以通过SDK向银联全渠道支付平台发起消费撤销交易，
     * 消费撤销必须是撤销CUPS当日当批的消费。发卡行批准的消费撤销金额将即时地反映到该持卡人的账户上。
     * 完成交易的过程不需要同持卡人交互，属于后台交易。
     *
     * @param gatewayCancelConsumeReq
     * @return
     */
    GatewayCancelConsumeSyncResp cancelConsume(GatewayCancelConsumeReq gatewayCancelConsumeReq);

    /**
     * 对于跨清算日或者当清算日的消费交易，商户可以通过调用SDK向银联全渠道支付平台发起退货交易，
     * 从而实现客户的退款需求，支持部分退货、多次退货。该交易参加资金清算,为后台交易。
     *
     * @param gatewayBackConsumeReq
     * @return
     */
    GatewayBackConsumeSyncResp backConsume(GatewayBackConsumeReq gatewayBackConsumeReq);

    /**
     * 对于未收到交易结果的联机交易，商户应向银联全渠道支付平台发起交易状态查询交易，
     * 查询交易结果。完成交易的过程不需要同持卡人交互，属于后台交易。
     * 交易查询类交易可由商户通过SDK向银联全渠道支付交易平台发起交易。
     * <p>
     * 注意：orderId & txnTime 必须与待查询交易单完全一致
     *
     * @param gatewayTransactionStatusQueryReq
     * @return
     */
    GatewayTransactionStatusQuerySyncResp transactionStatusQuery(GatewayTransactionStatusQueryReq gatewayTransactionStatusQueryReq);

    /**
     * 商户定期（1天1次）向银联全渠道系统发起获取加密公钥信息交易。在加密公钥证书更新期间，
     * 全渠道系统支持新老证书的共同使用，新老证书并行期为1个月。
     * 全渠道系统向商户返回最新的加密公钥证书，由商户服务器替换本地证书。
     *
     * @param gatewayEncryptInfoUpdateReq
     * @return
     */
    GatewayEncryptInfoUpdateSyncResp encryptInfoUpdate(GatewayEncryptInfoUpdateReq gatewayEncryptInfoUpdateReq);

    /**
     * 预授权交易用于受理方向持卡人的发卡方确认交易许可。
     * 受理方将预估的消费金额作为预授权金额，发送给持卡人的发卡方。
     *
     * @param gatewayPreAuthReq
     * @return
     */
    FormReq preAuth(GatewayPreAuthReq gatewayPreAuthReq);

    /**
     * 对已成功的POS预授权交易，在结算前使用预授权撤销交易，通知发卡方取消付款承诺。
     * 预授权撤销交易必须是对原始预授权交易或追加预授权交易最终承兑金额的全额撤销。
     *
     * @param gatewayCancelPreAuthReq
     * @return
     */
    GatewayCancelPreAuthSyncResp cancelPreAuth(GatewayCancelPreAuthReq gatewayCancelPreAuthReq);

    /**
     * 对已批准的预授权交易，用预授权完成做支付结算。
     *
     * @param gatewayCompletePreAuthReq
     * @return
     */
    GatewayCompletePreAuthSyncResp completePreAuth(GatewayCompletePreAuthReq gatewayCompletePreAuthReq);

    /**
     * 预授权完成撤销交易必须是对原始预授权完成交易的全额撤销。预授权完成撤销后的预授权仍然有效。
     *
     * @param gatewayCancelCompletedPreAuthReq
     * @return
     */
    GatewayCancelCompletedPreAuthSyncResp cancelCompletedPreAuth(GatewayCancelCompletedPreAuthReq gatewayCancelCompletedPreAuthReq);

    /**
     * 文件传输接口（对账文件下载）
     *
     * @param gatewayFileTransferReq
     * @return
     */
    GatewayFileTransferSyncResp fileTransfer(GatewayFileTransferReq gatewayFileTransferReq);
}
