package com.github.lol.pay.component.unionpay.product.qrcode.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code cancel consume
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeCancelConsumeReq implements Serializable {
    private static final long serialVersionUID = -7118276573073189637L;

    private String version;
    private String encoding;
    private String bizType;
    @NotEmpty
    private String txnTime;
    @NotEmpty
    private String backUrl;
    @NotEmpty
    private String txnAmt;
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
    private String txnType;
    private String reserved;
    private String reqReserved;
    private String termId;

    public static QRCodeCancelConsumeReqBuilder of(UnionpayConfig config) {
        return QRCodeCancelConsumeReq.builder()
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
                .txnType("31")
                .txnSubType("00")
                .bizType("000000")
                .channelType("08");
    }
}
