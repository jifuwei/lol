package com.github.lol.pay.component.unionpay.product.qrcode.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qr code file transfer request
 *
 * @author: jifuwei
 * @create: 2019-07-19 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeFileTransferReq implements Serializable {
    private static final long serialVersionUID = -4809315316540937177L;

    private String version;
    private String encoding;
    private String bizType;
    @NotEmpty
    private String txnTime;
    private String txnType;
    private String txnSubType;
    private String accessType;
    private String signature;
    private String signMethod;
    @NotEmpty
    private String settleDate;
    private String merId;
    private String fileType;
    private String accInsCode;
    private String certId;
    private String reqReserved;

    public static QRCodeFileTransferReqBuilder of(UnionpayConfig config) {
        return QRCodeFileTransferReq.builder()
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
                .txnType("76")
                .txnSubType("01")
                .bizType("000000")
                .fileType("00");
    }
}
