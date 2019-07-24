package com.github.lol.pay.component.alipay.product.f2f.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cancel alipay trade biz content request
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitorHeartbeatSynBizContentReq {
    @NotEmpty
    private String product;
    @NotEmpty
    private String type;
    @NotEmpty
    private String equipmentId;
    @NotEmpty
    private String time;
    @NotEmpty
    private String storeId;
    @NotEmpty
    private String networkType;
    private String sysServiceProviderId;
    private String mac;
    @NotEmpty
    private String tradeInfo;
    private String exceptionInfo;
    private String extendInfo;
}
