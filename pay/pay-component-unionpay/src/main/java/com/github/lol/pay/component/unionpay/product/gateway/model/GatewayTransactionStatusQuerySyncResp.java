package com.github.lol.pay.component.unionpay.product.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * transaction status query sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=278&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
public class GatewayTransactionStatusQuerySyncResp implements Serializable {
    private static final long serialVersionUID = -5662914041599077012L;

    private String queryId;
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
    private String bindId;
    private String exchangeDate;
    private String issuerIdentifyMode;
    private String currencyCode;
    private String txnAmt;
    private String signPubKeyCert;
    private String exchangeRate;
    private String cardTransData;
    private String origRespCode;
    private String origRespMsg;
    private String accNo;
    private String payType;
    private String payCardNo;
    private String payCardType;
    private String payCardIssueName;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String accessType;
    private String merId;
    private String orderId;
    private String reserved;
    private String reqReserved;
}
