package com.github.pay.component.unionpay.model;

/**
 * encrypt info update request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=279&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:30
 **/
public class EncryptInfoUpdateReq {
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    private String orderId;
    private String certType;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String merAbbr;
    private String merCatCode;
    private String merName;
    private String acqInsCode;
    private String certId;
    private String reserved;
    private String reqReserved;
}
