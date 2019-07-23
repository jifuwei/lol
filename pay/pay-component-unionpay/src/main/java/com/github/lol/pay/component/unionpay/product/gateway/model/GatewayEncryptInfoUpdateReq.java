package com.github.lol.pay.component.unionpay.product.gateway.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * encrypt info update request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=279&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayEncryptInfoUpdateReq implements Serializable {
    private static final long serialVersionUID = 5120486086746513979L;

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
    private String channelType;
    private String merId;
    @NotEmpty
    private String orderId;
    private String certType;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String merAbbr;
    private String merCatCode;
    private String merName;
    private String acqInsCode;
    private String certId;
    private String reserved;
    private String reqReserved;

    public static GatewayEncryptInfoUpdateReqBuilder of(UnionpayConfig config) {
        return GatewayEncryptInfoUpdateReq.builder()
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
                .txnType("95")
                .txnSubType("00")
                .bizType("000000")
                .certType("01")
                .channelType("07");
    }
}
