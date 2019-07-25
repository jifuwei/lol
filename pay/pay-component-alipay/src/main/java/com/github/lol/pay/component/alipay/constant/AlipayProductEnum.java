package com.github.lol.pay.component.alipay.constant;

import lombok.Getter;

/**
 * alipay product
 *
 * @author: jifuwei
 * @create: 2019-07-24 13:54
 **/
public enum AlipayProductEnum {

    F2F("当面付"),
    APP("APP支付");

    @Getter
    private String desc;

    AlipayProductEnum(String desc) {
        this.desc = desc;
    }
}
