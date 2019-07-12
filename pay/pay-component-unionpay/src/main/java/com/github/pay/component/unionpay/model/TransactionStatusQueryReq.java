package com.github.pay.component.unionpay.model;

/**
 * transaction status query request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=278&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
public class TransactionStatusQueryReq {
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String backUrl;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    private String orderId;
    private String acqInsCode;
    private String origQryId;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String certId;
    private String reserved;
    private String accSplitData;
    private String ctrlRule;
    private String reqReserved;
    private String termId;
}
