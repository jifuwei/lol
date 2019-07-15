package com.github.lol.pay.component.unionpay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * consume async response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=275&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:37
 **/
@Data
@NoArgsConstructor
public class ConsumeAsyncResp implements Serializable {
    private static final long serialVersionUID = -3336543213325006102L;

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
    private String signPubKeyCert;
    private String exchangeRate;
    private String accNo;
    private String payType;
    private String payCardNo;
    private String payCardType;
    private String payCardIssueName;
    private String version;
    private String bindId;
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
    private String accSplitData;
}
