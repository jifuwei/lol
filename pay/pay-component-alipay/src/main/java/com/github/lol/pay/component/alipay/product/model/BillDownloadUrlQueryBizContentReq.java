package com.github.lol.pay.component.alipay.product.model;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * bill download url query alipay trade biz content request
 * <p>
 * reference doc: https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query
 *
 * @author: jifuwei
 * @create: 2019-07-24 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDownloadUrlQueryBizContentReq implements Serializable {
    private static final long serialVersionUID = 5022100582161029283L;

    public final static String BILL_TYPE_TRADE = "trade";

    @NotEmpty
    private String billType;
    @NotEmpty
    private String billDate;
}
