package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code slave image async response
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeSlaveImgAsyncResp implements Serializable {
    private static final long serialVersionUID = 7089737894419585565L;

    private String qrNo;
    private String queryId;
    private String traceTime;
    private String signature;
    private String signMethod;
    private String traceNo;
    private String respCode;
    private String respMsg;
    private String exchangeDate;
    private String issInsNo;
    private String accInsCode;
    private String signPubKeyCert;
    private String settleCurrencyCode;
    private String exchangeRate;
    private String settleAmt;
    private String settleDate;
    private String accNo;
    private String payType;
    private String payCardType;
    private String payCardIssueName;
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
