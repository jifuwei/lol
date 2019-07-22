package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code reversal request
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeReversalReq implements Serializable {
    private static final long serialVersionUID = 2252202685850661829L;

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
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String accInsCode;
    private String certId;
    private String reserved;
    private String reqReserved;
    private String termId;
}
