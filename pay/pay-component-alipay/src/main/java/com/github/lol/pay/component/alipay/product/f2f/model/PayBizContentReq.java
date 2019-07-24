package com.github.lol.pay.component.alipay.product.f2f.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * alipay trade pay biz content req
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayBizContentReq {
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String scene;
    @NotEmpty
    private String authCode;
    private String productCode;
    @NotEmpty
    private String subject;
    private String buyerId;
    private String sellerId;
    @NotEmpty
    private String totalAmount;
    private String transCurrency;
    private String settleCurrency;
    private String discountableAmount;
    private String body;
    private List<GoodsDetail> goodsDetail;
    private String operatorId;
    @NotEmpty
    private String storeId;
    private String terminalId;
    private ExtendParams extendParams;
    @NotEmpty
    private String timeoutExpress;
    private String authConfirmMode;
    private String terminalParams;
    private PromoParams promoParams;
    private String advancePaymentType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GoodsDetail {
        private String goodsId;
        private String goodsName;
        private String quantity;
        private String price;
        private String goodsCategory;
        private String categoriesTree;
        private String body;
        private String showUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ExtendParams {
        String sysServiceProviderId;
        IndustryRefluxInfo industryRefluxInfo;
        String cardType;
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
    public static class PromoParams {
        private String actualOrderTime;
    }
}
