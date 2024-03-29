package com.github.lol.pay.component.unionpay.product.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * pre auth sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=278&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
public class GatewayPreAuthSyncResp implements Serializable {
    private static final long serialVersionUID = 713730126177810597L;

    private String queryId;
    private String signature;
    private String signMethod;
    private String respCode;
    private String respMsg;
    private String signPubKeyCert;
    private String acqInsCode;
    private String tn;
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
    private String reserved;
}
