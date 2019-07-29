package com.github.lol.pay.component.wechatpay;

import com.github.lol.lib.util.ReflectUtil;
import com.github.lol.lib.util.annotation.NotEmpty;
import com.github.lol.pay.component.wechatpay.internal.IWXPayDomain;
import com.github.lol.pay.component.wechatpay.internal.WXPayConfig;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * alipay config
 *
 * @author: jifuwei
 * @create: 2019-07-24 11:27
 **/
@Data
@Slf4j
@Builder
@EqualsAndHashCode(callSuper = true)
public class WeChatPayConfig extends WXPayConfig {

    @NotEmpty
    private String appId;
    @NotEmpty
    private String mchId;
    @NotEmpty
    private String key;
    @NotEmpty
    private Integer connectTimeout;
    @NotEmpty
    private Integer readTimeout;
    @NotEmpty
    private String certPath;
    private String domain;

    private String notifyUrl;
    private Boolean autoReport;
    private Boolean useSandbox;

    private byte[] certData;
    private IWXPayDomain wXPayDomain;

    public WeChatPayConfig(String appId, String mchId, String key, Integer connectTimeout,
                           Integer readTimeout, String certPath, String domain, String notifyUrl,
                           Boolean autoReport, Boolean useSandbox, byte[] certData, IWXPayDomain wXPayDomain) {
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.certPath = certPath;
        this.domain = domain;
        this.notifyUrl = notifyUrl;
        this.autoReport = autoReport;
        this.useSandbox = useSandbox;
        this.certData = certData;
        this.wXPayDomain = wXPayDomain;

        init();
    }

    @SneakyThrows
    private void init() {
        ReflectUtil.validateNotNullField(this);

        File file = new File(certPath);
        @Cleanup InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);

        wXPayDomain = new WeChatPayDomain(domain, Boolean.TRUE);

        this.autoReport = false;
        this.useSandbox = false;
    }

    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return mchId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return wXPayDomain;
    }

    @Data
    public static class WeChatPayDomain implements IWXPayDomain {

        private String domain;
        private Boolean primaryDomain;

        public WeChatPayDomain(String domain, Boolean primaryDomain) {
            this.domain = domain;
            this.primaryDomain = primaryDomain;
        }

        @Override
        public void report(String domain, long elapsedTimeMillis, Exception ex) {
            log.debug("[WeChatPay] ==> api report domain: {} elapsedTimeMillis: {} ex: {}", domain, elapsedTimeMillis, ex.getMessage());
        }

        @Override
        public DomainInfo getDomain(WXPayConfig config) {
            return new DomainInfo(domain, primaryDomain);
        }
    }
}
