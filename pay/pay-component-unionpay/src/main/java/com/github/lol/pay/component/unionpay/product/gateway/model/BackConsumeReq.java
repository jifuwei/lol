package com.github.lol.pay.component.unionpay.product.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * back consume request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=277&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackConsumeReq implements Serializable {
    private static final long serialVersionUID = -8124473272010099548L;

    private String version;
    private String encoding;
    private String bizType;
    private String txnTime;
    private String backUrl;
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    private String orderId;
    private String acqInsCode;
    private String origQryId;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String certId;
    private String reserved;
    private String accSplitData;
    private String ctrlRule;
    private String reqReserved;
    private String termId;
}
