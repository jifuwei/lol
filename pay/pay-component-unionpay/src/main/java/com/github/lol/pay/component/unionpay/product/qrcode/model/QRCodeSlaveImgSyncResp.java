package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code slave image sync response
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeSlaveImgSyncResp implements Serializable {
    private static final long serialVersionUID = 1348132248103178079L;

    private String queryId;
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
    private String currencyCode;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String reqReserved;
    private String merId;
    private String orderId;
    private String reserved;
}
