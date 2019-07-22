package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code cancel consume
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeCancelConsumeReq implements Serializable {
    private static final long serialVersionUID = -7118276573073189637L;

    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String backUrl;
    private String txnAmt;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    private String orderId;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String accInsCode;
    private String origQryId;
    private String origOrderId;
    private String origTxnTime;
    private String certId;
    private String txnType;
    private String reserved;
    private String reqReserved;
    private String termId;
}
