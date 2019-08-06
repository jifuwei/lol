package com.github.lol.pay.component.wechatpay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * download fund flow request data
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DownloadFundFlowReq {
    @NotEmpty
    private String appid;
    @NotEmpty
    private String mchId;
    @NotEmpty
    private String nonceStr;
    @NotEmpty
    private String sign;
    private String signType;
    @NotEmpty
    private String billDate;
    @NotEmpty
    private String accountType;
    private String tarType;
}
