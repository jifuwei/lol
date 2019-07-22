package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code master image request
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeMasterImgReq implements Serializable {
    private static final long serialVersionUID = 249845241967941573L;

    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String backUrl;
    private String currencyCode;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    private String orderId;
    private String termInfo;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String accSplitData;
    private String accInsCode;
    private String certId;
    private String reserved;
    private String txnAmt;
    private String ctrlRule;
    private String reqReserved;
    private String payTimeout;
    private String termId;
}
