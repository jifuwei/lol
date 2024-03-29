package com.github.lol.pay.component.unionpay.product.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * cancel completed pre auth sync response data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=278&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
public class GatewayCancelCompletedPreAuthSyncResp implements Serializable {
    private static final long serialVersionUID = 8590746144208612163L;

    private String queryId;
    private String signature;
    private String signMethod;
    private String respCode;
    private String respMsg;
    private String signPubKeyCert;
    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String reqReserved;
    private String merId;
    private String orderId;
    private String origQryId;
    private String reserved;
}
