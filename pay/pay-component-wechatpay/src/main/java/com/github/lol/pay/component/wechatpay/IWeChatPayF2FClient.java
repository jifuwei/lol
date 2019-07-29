package com.github.lol.pay.component.wechatpay;

import com.github.lol.pay.component.wechatpay.product.model.MicroPayReq;

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
    Map<String, String> microPay(MicroPayReq microPayReq);
}
