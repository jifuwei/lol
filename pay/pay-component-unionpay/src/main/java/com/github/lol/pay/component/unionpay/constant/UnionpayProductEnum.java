package com.github.lol.pay.component.unionpay.constant;

import lombok.Getter;

/**
 * unionpay product
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:48
 **/
public enum UnionpayProductEnum {

    GATEWAY("在线网关支付"),
    QR_CODE("二维码支付");

    @Getter
    private String desc;

    UnionpayProductEnum(String desc) {
        this.desc = desc;
    }
}
