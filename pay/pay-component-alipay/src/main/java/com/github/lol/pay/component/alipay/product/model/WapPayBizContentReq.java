package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * wap pay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/203/107090/
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WapPayBizContentReq implements Serializable {
    private static final long serialVersionUID = -479256155741677764L;

    public final static String DEFAULT_PRODUCT_CODE = "QUICK_WAP_WAY";

    private String body;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String outTradeNo;
    @NotEmpty
    private String timeoutExpress;
    @NotEmpty
    private String timeExpire;
    @NotEmpty
    private String totalAmount;
    private String authToken;
    @NotEmpty
    private String productCode;
    private String goodsType;
    private String passbackParams;
    private PromoParams promoParams;
    private ExtendParams extendParams;
    private String enablePayChannels;
    private String disablePayChannels;
    private String storeId;
    private String quitUrl;
    private String extUserInfo;

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
    }
}