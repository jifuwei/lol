package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * fastpay refund query trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api1/alipay.trade.app.pay
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FastpayRefundQueryBizContentReq {
    @NotEmpty
    private String tradeNo;
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String outRequestNo;
    private String orgPid;
}