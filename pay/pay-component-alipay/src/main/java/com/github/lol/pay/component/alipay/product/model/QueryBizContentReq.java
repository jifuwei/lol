package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * query alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/alipay.trade.query
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryBizContentReq implements Serializable {
    private static final long serialVersionUID = -5963927903376882366L;

    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String tradeNo;
    private String orgPid;
}
