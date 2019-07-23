package com.github.pay.component.core;

import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

/**
 * simple order id generator
 *
 * @author: jifuwei
 * @create: 2019-07-23 16:25
 **/
public class SimpleOrderIdGenerator implements PayGenerator {

    @Override
    public String generate(@NonNull String prefix) {
        prefix = prefix.replaceAll("_", "");
        prefix = prefix.replaceAll("-", "");
        String currDateSeq = DateTime.now().toString("yyyyMMddHHmmss");
        String randomSeq = RandomStringUtils.randomAlphanumeric(4);
        return prefix + currDateSeq + randomSeq;
    }

    public static String get(@NonNull String prefix) {
        return new SimpleOrderIdGenerator().generate(prefix);
    }

    public static void main(String[] args) {
        System.out.println(SimpleOrderIdGenerator.get("QR_CODE"));
        System.out.println(SimpleOrderIdGenerator.get("GATEWAY"));
    }
}
