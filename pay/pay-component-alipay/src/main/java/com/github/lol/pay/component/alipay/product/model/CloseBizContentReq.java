package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * close alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/alipay.trade.close
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseBizContentReq implements Serializable {
    private static final long serialVersionUID = 1713474734828142139L;

    @NotEmpty
    private String tradeNo;
    @NotEmpty
    private String outTradeNo;
    private String operatorId;
}