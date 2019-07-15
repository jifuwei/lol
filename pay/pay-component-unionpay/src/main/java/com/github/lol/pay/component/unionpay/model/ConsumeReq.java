package com.github.lol.pay.component.unionpay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * consume request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=275&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:30
 **/
@Data
@NoArgsConstructor
public class ConsumeReq implements Serializable {
    private static final long serialVersionUID = -3234138678009888793L;

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
    private String issInsCode;
    private String instalTransInfo;
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
    private String accSplitData;
    private String riskRateInfo;
    private String ctrlRule;
    private String defaultPayType;
    private String reqReserved;
    private String frontFailUrl;
    private String supPayType;
    private String payTimeout;
    private String termId;
    private String userMac;
}
