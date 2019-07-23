package com.github.lol.pay.component.unionpay.product.gateway.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * file transfer request data
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
public class GatewayFileTransferReq implements Serializable {
    private static final long serialVersionUID = -1061067119765751510L;

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
    private String certId;
    private String reqReserved;

    public static GatewayFileTransferReqBuilder of(UnionpayConfig config) {
        return GatewayFileTransferReq.builder()
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
