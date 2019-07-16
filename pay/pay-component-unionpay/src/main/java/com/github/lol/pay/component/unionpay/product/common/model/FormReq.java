package com.github.lol.pay.component.unionpay.product.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * form request data
 *
 * @author: jifuwei
 * @create: 2019-07-16 09:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormReq implements Serializable {
    private static final long serialVersionUID = 4853213567026159492L;

    private String actionUrl;
    private String encoding;
    private Map<String, String> inputDataMap;
}
