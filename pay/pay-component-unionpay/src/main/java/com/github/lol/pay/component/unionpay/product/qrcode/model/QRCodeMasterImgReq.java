package com.github.lol.pay.component.unionpay.product.qrcode.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
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
    @NotEmpty
    private String txnTime;
    @NotEmpty
    private String backUrl;
    private String currencyCode;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    private String channelType;
    private String merId;
    @NotEmpty
    private String orderId;
    private String termInfo;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String accSplitData;
    private String accInsCode;
    private String certId;
    private String reserved;
    @NotEmpty
    private String txnAmt;
    private String ctrlRule;
    private String reqReserved;
    private String payTimeout;
    private String termId;

    public static QRCodeMasterImgReqBuilder of(UnionpayConfig config) {
        return QRCodeMasterImgReq.builder()
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
                .txnSubType("07")
                .bizType("000000")
                .channelType("08");
    }
}
