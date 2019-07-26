package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * cancel alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/alipay.trade.cancel
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelBizContentReq implements Serializable {
    private static final long serialVersionUID = -6657600314554329120L;

    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String tradeNo;
}
