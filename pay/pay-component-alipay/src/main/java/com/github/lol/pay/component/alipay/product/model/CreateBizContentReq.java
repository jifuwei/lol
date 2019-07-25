package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * create alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/alipay.trade.create
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBizContentReq {
    @NotEmpty
    private String outTradeNo;
    private String sellerId;
    @NotEmpty
    private String totalAmount;
    private String discountableAmount;
    @NotEmpty
    private String subject;
    private String body;
    private String buyerId;
    private List<GoodsDetail> goodsDetail;
    private String productCode;
    private String operatorId;
    private String storeId;
    private String terminalId;
    private ExtendParams extendParams;
    private String timeoutExpress;
    private SettleInfo settleInfo;
    private LogisticsDetail logisticsDetail;
    private BusinessParams businessParams;
    private ReceiverAddressInfo receiverAddressInfo;

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
    public static class SettleInfo {
        private List<SettleDetailInfos> settleDetailInfos;
        private String merchantType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SettleDetailInfos {
        private String transInType;
        private String transIn;
        private String summaryDimension;
        private String settleEntityId;
        private String settleEntityType;
        private String amount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LogisticsDetail {
        private String logisticsType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BusinessParams {
        private String campusCard;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReceiverAddressInfo {
        private String name;
        private String address;
        private String mobile;
        private String zip;
        private String divisionCode;
    }
}