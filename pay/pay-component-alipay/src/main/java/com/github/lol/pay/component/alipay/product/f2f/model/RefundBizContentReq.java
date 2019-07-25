package com.github.lol.pay.component.alipay.product.f2f.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * refund alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/alipay.trade.refund
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundBizContentReq {
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String tradeNo;
    @NotEmpty
    private String refundAmount;
    private String refundCurrency;
    private String refundReason;
    private String outRequestNo;
    private String operatorId;
    private String storeId;
    private String terminalId;
    private GoodsDetail goodsDetail;
    private RefundRoyaltyParameters refundRoyaltyParameters;
    private String orgPid;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GoodsDetail {
        private String goodsId;
        private String alipayGoodsId;
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
    public static class RefundRoyaltyParameters {
        private String royaltyType;
        private String transOut;
        private String transOutType;
        private String transInType;
        private String transIn;
        private String amount;
        private String amountPercentage;
        private String desc;
    }
}
