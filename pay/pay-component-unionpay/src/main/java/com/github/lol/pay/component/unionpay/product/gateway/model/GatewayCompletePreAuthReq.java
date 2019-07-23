package com.github.lol.pay.component.unionpay.product.gateway.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.unionpay.UnionpayConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * complete pre auth request data
 * referenced document:
 * https://open.unionpay.com/tjweb/acproduct/APIList?acpAPIId=282&&apiservId=448&&version=V2.2
 *
 * @author: jifuwei
 * @create: 2019-07-11 20:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayCompletePreAuthReq implements Serializable {
    private static final long serialVersionUID = 7048555483088483007L;

    private String version;
    private String encoding;
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
    @NotEmpty
    private String origQryId;
    private String subMerId;
    private String subMerAbbr;
    private String subMerName;
    private String certId;
    private String reserved;
    private String reqReserved;
    private String termId;

    public static GatewayCompletePreAuthReqBuilder of(UnionpayConfig config) {
        return GatewayCompletePreAuthReq.builder()
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
                .txnType("03")
                .txnSubType("00")
                .bizType("000301")
                .channelType("07");
    }
}
