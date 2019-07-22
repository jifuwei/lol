package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code transaction status query sync resp
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeTransactionStatusQuerySyncResp implements Serializable {
    private static final long serialVersionUID = 5627837575744460628L;

    private String traceTime;
    private String txnType;
    private String txnSubType;
    private String signature;
    private String signMethod;
    private String settleCurrencyCode;
    private String settleAmt;
    private String settleDate;
    private String traceNo;
    private String respCode;
    private String respMsg;
    private String queryId;
    private String txnTime;
    private String exchangeDate;
    private String issInsNo;
    private String currencyCode;
    private String txnAmt;
    private String accInsCode;
    private String signPubKeyCert;
    private String exchangeRate;
    private String orderId;
    private String origOrderId;
    private String origTxnTime;
    private String origRespCode;
    private String origRespMsg;
    private String accNo;
    private String payType;
    private String payCardType;
    private String version;
    private String encoding;
    private String bizType;
    private String accessType;
    private String merId;
    private String reserved;
    private String reqReserved;
}
