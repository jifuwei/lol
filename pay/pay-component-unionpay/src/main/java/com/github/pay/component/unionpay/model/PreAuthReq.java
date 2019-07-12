package com.github.pay.component.unionpay.model;

/**
 * pre auth request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=280&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
public class PreAuthReq {
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String backUrl;
    private String currencyCode;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    private String orderId;
    private String orderDesc;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String encryptCertId;
    private String frontUrl;
    private String customerInfo;
    private String cardTransData;
    private String accountPayChannel;
    private String accNo;
    private String accType;
    private String certId;
    private String reserved;
    private String customerIp;
    private String orderTimeout;
    private String issInsCode;
    private String accSplitData;
    private String riskRateInfo;
    private String defaultPayType;
    private String reqReserved;
    private String frontFailUrl;
    private String supPayType;
    private String payTimeout;
    private String termId;
    private String userMac;
}
