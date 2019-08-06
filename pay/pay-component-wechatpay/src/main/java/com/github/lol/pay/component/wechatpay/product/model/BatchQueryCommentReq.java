package com.github.lol.pay.component.wechatpay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * order query request data
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchQueryCommentReq {
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
    private String beginTime;
    @NotEmpty
    private String endTime;
    @NotEmpty
    private String offset;
    private String limit;
}
