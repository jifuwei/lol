package com.github.lol.pay.component.unionpay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * consume sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=275&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:37
 **/
@Data
@NoArgsConstructor
public class ConsumeSyncResp implements Serializable {
    private static final long serialVersionUID = -8802896994977697706L;

    private String queryId;
    private String signature;
    private String signMethod;
    private String respCode;
    private String respMsg;
    private String signPubKeyCert;
    private String tn;
    private String accNo;
    private String payType;
    private String payCardType;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String currencyCode;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String reqReserved;
    private String merId;
    private String orderId;
    private String reserved;
}
