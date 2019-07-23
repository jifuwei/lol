package com.github.lol.pay.component.unionpay.product.qrcode.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
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
    @NotEmpty
    private String txnTime;
    private String accInsCode;
    @NotEmpty
    private String orderId;
    private String certId;
    private String reserved;
    private String ctrlRule;

    public static QRCodeTransactionStatusQueryReqBuilder of(UnionpayConfig config) {
        return QRCodeTransactionStatusQueryReq.builder()
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
                .txnType("00")
                .txnSubType("00")
                .bizType("000000");
    }
}
