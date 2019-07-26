package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * app pay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api1/alipay.trade.app.pay
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppPayBizContentReq implements Serializable {
    private static final long serialVersionUID = -7698221908645664826L;

    @NotEmpty
    private String timeoutExpress;
    @NotEmpty
    private String totalAmount;
    private String productCode;
    private String body;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String timeExpire;
    private String goodsType;
    private PromoParams promoParams;
    private String passbackParams;
    private String ExtendParams;
    private String merchantOrderNo;
    private String enablePayChannels;
    @NotEmpty
    private String storeId;
    private String specifiedChannel;
    private String disablePayChannels;
    private ExtUserInfo extUserInfo;
    private BusinessParams businessParams;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PromoParams {
        private String storeIdType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ExtendParams {
        private String sysServiceProviderId;
        private String hbFqNum;
        private String hbFqSellerPercent;
        private IndustryRefluxInfo industryRefluxInfo;
        private String cardType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class IndustryRefluxInfo {
        private String sceneCode;
        private String channel;
        private Map<String, String> sceneData;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ExtUserInfo {
        private String name;
        private String mobile;
        private String certType;
        private String certNo;
        private String minAge;
        private String fixBuyer;
        private String needCheckInfo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BusinessParams {
        private String data;
    }
}