package com.github.lol.pay.component.wechatpay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * refund request data
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundReq {
    @NotEmpty
    private String appid;
    @NotEmpty
    private String mchId;
    private String transactionId;
    private String outTradeNo;
    @NotEmpty
    private String nonceStr;
    @NotEmpty
    private String sign;
    private String signType;
    @NotEmpty
    private String totalFee;
    @NotEmpty
    private String refundFee;
    private String refundFeeType;
    private String refundDesc;
    private String refundAccount;
    private String notifyUrl;
}
