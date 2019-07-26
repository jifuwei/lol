package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * alipay trade pay biz content req
 * <p>
 * reference doc: https://docs.open.alipay.com/api_1/alipay.trade.pay
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayBizContentReq implements Serializable {
    private static final long serialVersionUID = -5869502543539206493L;

    public final static String SCENE_BAR_CODE = "bar_code";

    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String scene;

    // 支付授权码，25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
    @NotEmpty
    private String authCode;
    private String productCode;
    @NotEmpty
    private String subject;
    private String buyerId;
    private String sellerId;

    // 单位【元】精确到小数点后两位
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

    // 该笔订单允许的最晚付款时间，逾期将关闭交易。
    // 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
    // 该参数数值不接受小数点， 如 1.5h，可转换为 90m
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
        private String sysServiceProviderId;
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
    public static class PromoParams {
        private String actualOrderTime;
    }
}
