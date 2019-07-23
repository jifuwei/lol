package com.github.lol.pay.component.unionpay.product.qrcode.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code back consume
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeBackConsumeReq implements Serializable {
    private static final long serialVersionUID = 3629784889615009892L;

    private String version;
    private String bizType;
    @NotEmpty
    private String txnTime;
    @NotEmpty
    private String backUrl;
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
    @NotEmpty
    private String origQryId;
    @NotEmpty
    private String origOrderId;
    @NotEmpty
    private String origTxnTime;
    private String certId;
    private String encoding;
    private String reserved;
    private String accSplitData;
    private String reqReserved;
    private String termId;

    public static QRCodeBackConsumeReqBuilder of(UnionpayConfig config) {
        return QRCodeBackConsumeReq.builder()
                /**
                 * 商户接入参数
                 */
                .merId(config.getMerId())
                .accessType(config.getAccessType())

                /**
                 * 产品固定参数
                 */
                .version(config.getVersion())
                .encoding(config.getEncoding())
                .signMethod(config.getSignMethod())
                .txnType("04")
                .txnSubType("00")
                .bizType("000000")
                .channelType("08");
    }
}
