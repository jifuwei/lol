package com.github.lol.pay.component.wechatpay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * micro pay request data
 *
 * @author: jifuwei
 * @create: 2019-07-29 10:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MicroPayReq {
    @NotEmpty
    private String appid;
    @NotEmpty
    private String mchId;
    private String deviceInfo;
    @NotEmpty
    private String nonceStr;
    @NotEmpty
    private String sign;
    private String signType;
    @NotEmpty
    private String body;
    private String detail;
    private String attach;
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String totalFee;
    private String feeType;
    @NotEmpty
    private String spbillCreateIp;
    private String goodsTag;
    private String limitPay;
    private String timeStart;
    private String timeExpire;
    private String receipt;
    @NotEmpty
    private String authCode;
    private String sceneInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SceneInfo {
        private String id;
        private String name;
        private String areaCode;
        private String address;
    }
}
