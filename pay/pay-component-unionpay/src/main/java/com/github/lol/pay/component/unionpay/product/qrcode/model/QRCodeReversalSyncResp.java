package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code reversal sync response
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeReversalSyncResp implements Serializable {
    private static final long serialVersionUID = -7296464568004621732L;

    private String signature;
    private String signMethod;
    private String respCode;
    private String respMsg;
    private String accInsCode;
    private String signPubKeyCert;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String reqReserved;
    private String merId;
    private String orderId;
    private String reserved;
}
