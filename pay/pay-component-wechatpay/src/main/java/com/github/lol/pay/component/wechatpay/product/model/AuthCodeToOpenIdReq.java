package com.github.lol.pay.component.wechatpay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * auth code to open id query request data
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthCodeToOpenIdReq {
    @NotEmpty
    private String appid;
    @NotEmpty
    private String mchId;
    @NotEmpty
    private String authCode;
    @NotEmpty
    private String nonceStr;
    @NotEmpty
    private String sign;
}
