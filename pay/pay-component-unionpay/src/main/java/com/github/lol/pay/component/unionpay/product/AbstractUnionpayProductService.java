package com.github.lol.pay.component.unionpay.product;

import com.github.lol.pay.component.unionpay.core.CertificateService;
import com.github.lol.pay.component.unionpay.core.UnionpayConfig;
import com.github.lol.pay.component.unionpay.core.UnionpaySignService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * abstract unionpay gateway service
 *
 * @author: jifuwei
 * @create: 2019-07-15 17:18
 **/
public abstract class AbstractUnionpayProductService {

    @Getter
    @Setter
    protected UnionpayConfig config;

    @Getter
    @Setter
    protected UnionpaySignService signService;

    @Getter
    @Setter
    protected CertificateService certService;

    /**
     * define product name
     *
     * @return
     */
    protected abstract String productName();

    /**
     * define product id
     *
     * @return
     */
    protected abstract String productId();

    /**
     * convert Data to Map
     *
     * @param data
     * @return
     */
    protected Map<String, String> convertData2Map(Object data) {
        Validate.notNull(data);

        return Arrays.stream(data.getClass().getDeclaredFields())
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .filter(f -> !Objects.isNull(getFieldValue(f, data)))
                .collect(Collectors.toMap(Field::getName, f -> getFieldValue(f, data).toString()));
    }

    private Object getFieldValue(Field f, Object source) {
        try {
            f.setAccessible(true);
            return f.get(source);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("reflect get field error", e);
        }
    }
}
