package com.github.pay.component.unionpay.model;

/**
 * file transfer sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=277&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
public class FileTransferSyncResp {
    private String fileContent;
    private String signature;
    private String signMethod;
    private String fileName;
    private String respCode;
    private String respMsg;
    private String certId;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String settleDate;
    private String reqReserved;
    private String merId;
    private String fileType;
}
