package com.github.lol.pay.component.alipay.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * alipay core request
 *
 * @author: jifuwei
 * @create: 2019-07-26 09:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlipayCoreReq {
    private String productId;
    private String methodName;
    private String bizContent;
}
