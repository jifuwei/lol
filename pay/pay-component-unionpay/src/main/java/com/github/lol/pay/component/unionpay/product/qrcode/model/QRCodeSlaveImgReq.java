package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code slave image request
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeSlaveImgReq implements Serializable {
    private static final long serialVersionUID = -5720621872143917889L;

    private String qrNo;
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
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String accInsCode;
    private String certId;
    private String reserved;
    private String accSplitData;
    private String riskRateInfo;
    private String reqReserved;
    private String termId;
    private String termInfo;
}
