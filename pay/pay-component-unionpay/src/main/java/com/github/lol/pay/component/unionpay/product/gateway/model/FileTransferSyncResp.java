package com.github.lol.pay.component.unionpay.product.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * file transfer sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=277&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
public class FileTransferSyncResp implements Serializable {
    private static final long serialVersionUID = -8000803386398120972L;

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
