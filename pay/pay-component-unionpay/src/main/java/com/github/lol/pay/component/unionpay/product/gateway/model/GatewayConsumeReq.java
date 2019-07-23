package com.github.lol.pay.component.unionpay.product.gateway.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * consume request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=275&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayConsumeReq implements Serializable {
    private static final long serialVersionUID = -3234138678009888793L;

    private String version;
    private String encoding;
    private String bizType;
    @NotEmpty
    private String txnTime;
    @NotEmpty
    private String backUrl;
    private String currencyCode;
    @NotEmpty
    private String txnAmt;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    @NotEmpty
    private String orderId;
    private String orderDesc;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String issInsCode;
    private String instalTransInfo;
    private String encryptCertId;
    @NotEmpty
    private String frontUrl;
    private String customerInfo;
    private String cardTransData;
    private String accountPayChannel;
    private String accNo;
    private String accType;
    private String certId;
    private String reserved;
    private String customerIp;
    private String orderTimeout;
    private String accSplitData;
    private String riskRateInfo;
    private String ctrlRule;
    private String defaultPayType;
    private String reqReserved;
    private String frontFailUrl;
    private String supPayType;
    @NotEmpty
    private String payTimeout;
    private String termId;
    private String userMac;

    public static GatewayConsumeReqBuilder of(UnionpayConfig config) {
        return GatewayConsumeReq.builder()
                /**
                 * 商户接入参数
                 */
                .merId(config.getMerId())
                .accessType(config.getAccessType())
                .currencyCode(config.getCurrencyCode())

                /**
                 * 产品固定参数
                 */
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("01")
                .txnSubType("01")
                .bizType("000201")
                .channelType("07");
    }
}
