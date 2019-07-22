package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code file transfer sync response
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeFileTransferSyncResp implements Serializable {
    private static final long serialVersionUID = -1300393632174573580L;

    private String fileContent;
    private String signature;
    private String signMethod;
    private String fileName;
    private String respCode;
    private String respMsg;
    private String accInsCode;
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
