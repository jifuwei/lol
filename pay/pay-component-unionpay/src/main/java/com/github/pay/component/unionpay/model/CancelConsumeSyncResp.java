package com.github.pay.component.unionpay.model;

/**
 * cancel consume sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=276&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
public class CancelConsumeSyncResp {
    private String queryId;
    private String signature;
    private String signMethod;
    private String signPubKeyCert;
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
    private String origQryId;
    private String reserved;
}
