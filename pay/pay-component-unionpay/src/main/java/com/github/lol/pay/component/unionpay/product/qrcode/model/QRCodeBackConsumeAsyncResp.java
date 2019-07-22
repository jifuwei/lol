package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code back consume async response
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeBackConsumeAsyncResp implements Serializable {
    private static final long serialVersionUID = 7499004313110050630L;

    private String queryId;
    private String currencyCode;
    private String traceTime;
    private String signature;
    private String signMethod;
    private String settleCurrencyCode;
    private String settleAmt;
    private String settleDate;
    private String traceNo;
    private String respCode;
    private String respMsg;
    private String exchangeDate;
    private String issInsNo;
    private String accInsCode;
    private String signPubKeyCert;
    private String exchangeRate;
    private String origQryId;
    private String origOrderId;
    private String origTxnTime;
    private String accNo;
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
