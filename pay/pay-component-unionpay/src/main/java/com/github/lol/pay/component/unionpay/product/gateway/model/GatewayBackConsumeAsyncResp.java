package com.github.lol.pay.component.unionpay.product.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * back consume async response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=277&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
public class GatewayBackConsumeAsyncResp implements Serializable {
    private static final long serialVersionUID = -5444303160842941438L;

    private String queryId;
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
    private String customerInfo;
    private String accNo;
    private String payType;
    private String payCardType;
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
    private String origQryId;
    private String reserved;
}
