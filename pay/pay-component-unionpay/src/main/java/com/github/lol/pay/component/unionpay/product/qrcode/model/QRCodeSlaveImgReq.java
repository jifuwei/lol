package com.github.lol.pay.component.unionpay.product.qrcode.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
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
    @NotEmpty
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

    public static QRCodeSlaveImgReqBuilder of(UnionpayConfig config) {
        return QRCodeSlaveImgReq.builder()
                /**
                 * 商户接入参数
                 */
                .merId(config.getMerId())
                .accessType(config.getAccessType())
                .currencyCode("156")

                /**
                 * 产品固定参数
                 */
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("01")
                .txnSubType("06")
                .bizType("000000")
                .channelType("08");
    }
}
