package com.github.lol.pay.component.unionpay.product.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

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

    public String buildAutoFormHtml() {
        Validate.notEmpty(actionUrl);
        Validate.notEmpty(encoding);
        Validate.isTrue(!Objects.isNull(inputDataMap) && inputDataMap.size() > 0);

        StringBuffer sf = new StringBuffer();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=")
                .append(encoding)
                .append("\"/></head><body>");
        sf.append("<form id = \"pay_form\" action=\"").append(actionUrl).append("\" method=\"post\">");

        for (Map.Entry<String, String> ey : inputDataMap.entrySet()) {
            String key = ey.getKey();
            String value = ey.getValue();
            sf.append("<input type=\"hidden\" name=\"").append(key).append("\" id=\"").append(key).append("\" value=\"").append(value).append("\"/>");
        }

        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");

        return sf.toString();
    }
}
