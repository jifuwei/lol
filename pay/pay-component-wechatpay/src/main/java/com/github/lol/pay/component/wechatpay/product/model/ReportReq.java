package com.github.lol.pay.component.wechatpay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * report request data
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportReq {
    @NotEmpty
    private String appid;
    @NotEmpty
    private String mchId;
    private String deviceInfo;
    @NotEmpty
    private String interfaceUrl;
    @NotEmpty
    private String nonceStr;
    @NotEmpty
    private String sign;
    @NotEmpty
    private String userIp;
    // !CDATA[ Trade.class.toJSONString ]
    @NotEmpty
    private String trades;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Trade {
        private String outTradeNo;
        private String beginTime;
        private String endTime;
        private String state;
        private String errMsg;
    }
}
