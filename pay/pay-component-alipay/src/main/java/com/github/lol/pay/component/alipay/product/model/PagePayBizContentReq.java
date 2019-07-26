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
 * page pay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api1/alipay.trade.page.pay/
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagePayBizContentReq implements Serializable {
    private static final long serialVersionUID = -1131059759873048349L;

    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String productCode;
    @NotEmpty
    private String totalAmount;
    @NotEmpty
    private String subject;
    private String body;
    private String timeExpire;
    private String goodsDetail;
    private String passbackParams;
    private ExtendParams extendParams;
    private GoodsDetail goodsType;
    private String timeoutExpress;
    private RoyaltyInfo royaltyInfo;
    private SubMerchant subMerchant;
    private String merchantOrderNo;
    private String enablePayChannels;
    private String storeId;
    private String disablePayChannels;
    private String qrPayMode;
    private String qrcodeWidth;
    private SettleInfo settleInfo;
    private InvoiceInfo invoiceInfo;
    private AgreementSignParams agreementSignParams;
    private String integrationType;
    private String requestFromUrl;
    private String businessParams;
    private ExtUserInfo extUserInfo;

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
    public static class RoyaltyInfo {
        private String royaltyType;
        private RoyaltyDetailInfos royaltyDetailInfos;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RoyaltyDetailInfos {
        private String serialNo;
        private String transInType;
        private String batchNo;
        private String outRelationId;
        private String transOutType;
        private String transOut;
        private String transIn;
        private String amount;
        private String desc;
        private String amountPercentage;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SubMerchant {
        private String merchantId;
        private String merchantType;
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
    public static class InvoiceInfo {
        private KeyInfo keyInfo;
        private String details;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class KeyInfo {
        private String isSupportInvoice;
        private String invoiceMerchantName;
        private String taxNum;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AgreementSignParams {
        private String personalProductCode;
        private String signScene;
        private String externalAgreementNo;
        private String externalLogonId;
        private String signValidityPeriod;
        private String thirdPartyType;
        private String buckleAppId;
        private String buckleMerchantId;
        private String promoParams;
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
}