package com.github.pay.component.unionpay.model;

/**
 * encrypt info update sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=279&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:30
 **/
public class EncryptInfoUpdateSyncResp {
    private String signature;
    private String signMethod;
    private String respCode;
    private String respMsg;
    private String encryptPubKeyCert;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String reqReserved;
    private String merId;
    private String orderId;
    private String acqInsCode;
    private String certType;
    private String reserved;
    private String signPubKeyCert;
}
