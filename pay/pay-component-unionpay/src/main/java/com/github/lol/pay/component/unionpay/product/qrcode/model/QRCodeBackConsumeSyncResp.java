package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code back consume sync response
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeBackConsumeSyncResp implements Serializable {
    private static final long serialVersionUID = -4811834593343367605L;

    private String queryId;
    private String signature;
    private String signMethod;
    private String respCode;
    private String respMsg;
    private String accInsCode;
    private String signPubKeyCert;
    private String origQryId;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String reqReserved;
    private String merId;
    private String orderId;
    private String reserved;
}
