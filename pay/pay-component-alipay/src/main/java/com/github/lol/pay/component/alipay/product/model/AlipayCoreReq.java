package com.github.lol.pay.component.alipay.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class AlipayCoreReq implements Serializable {
    private static final long serialVersionUID = 3159060941030466661L;
    
    private String productId;
    private String methodName;
    private String bizContent;
}
