package com.github.lol.pay.component.wechatpay.constant;

import lombok.Getter;

/**
 * alipay product
 *
 * @author: jifuwei
 * @create: 2019-07-24 13:54
 **/
public enum WeChatPayProductEnum {

    F2F("付款码支付");

    @Getter
    private String desc;

    WeChatPayProductEnum(String desc) {
        this.desc = desc;
    }
}
