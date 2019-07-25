package com.github.lol.pay.component.alipay.product.f2f.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cancel alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/monitor.heartbeat.syn
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitorHeartbeatSynBizContentReq {

    public final static String PRODUCT_FP = "FP";

    // 收银机
    public final static String TYPE_CR = "CR";
    // 门店
    public final static String TYPE_STORE = "STORE";
    // 售卖
    public final static String TYPE_VM = "VM";

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
    private TradeInfo tradeInfo;
    private String exceptionInfo;
    private String extendInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TradeInfo {
        private String OTN;
        private String TC;
        private String STAT;
    }
}
