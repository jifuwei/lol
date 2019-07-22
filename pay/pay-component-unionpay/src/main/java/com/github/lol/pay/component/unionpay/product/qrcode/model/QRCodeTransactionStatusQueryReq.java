package com.github.lol.pay.component.unionpay.product.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code transaction status query request
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeTransactionStatusQueryReq implements Serializable {
    private static final long serialVersionUID = -7155356061092450921L;

    private String version;
    private String encoding;
    private String bizType;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String merId;
    private String queryId;
    private String txnTime;
    private String accInsCode;
    private String orderId;
    private String certId;
    private String reserved;
    private String ctrlRule;
}
