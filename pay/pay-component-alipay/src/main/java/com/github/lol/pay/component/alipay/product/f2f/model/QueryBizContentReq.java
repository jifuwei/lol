package com.github.lol.pay.component.alipay.product.f2f.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * query alipay trade biz content request
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryBizContentReq {
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String tradeNo;
    private String orgPid;
}
