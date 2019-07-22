package com.github.lol.pay.component.unionpay;

import com.github.lol.pay.component.unionpay.constant.UnionpayConstant;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

/**
 * test for config
 *
 * @author: jifuwei
 * @create: 2019-07-22 17:46
 **/
public class UnionpayGlobalConfig {

    public static UnionpayConfig init() {
        String propertyFileName = "/unionpay_config.properties";
        Properties prop = load(propertyFileName);
        return UnionpayConfig.builder()
                .merId(prop.getProperty(UnionpayConstant.PARAM_MER_ID))
                .accessType(prop.getProperty(UnionpayConstant.PARAM_ACCESS_TYPE))
                .domain(prop.getProperty(UnionpayConstant.PARAM_DOMAIN))
                .fileDownLoadUrl(prop.getProperty(UnionpayConstant.PARAM_FILE_DOWNLOAD_URL))
                .signMethod(prop.getProperty(UnionpayConstant.PARAM_SIGN_METHOD))
                .signCertPath(prop.getProperty(UnionpayConstant.PARAM_SIGN_CERT_PATH))
                .signCertPwd(prop.getProperty(UnionpayConstant.PARAM_SIGN_CERT_PWD))
                .signCertType(prop.getProperty(UnionpayConstant.PARAM_SIGN_CERT_TYPE))
                .middleCertPath(prop.getProperty(UnionpayConstant.PARAM_MIDDLE_CERT_PATH))
                .rootCertPath(prop.getProperty(UnionpayConstant.PARAM_ROOT_CERT_PATH))
                .encryptCertPath(prop.getProperty(UnionpayConstant.PARAM_ENCRYPT_CERT_PATH))
                .build();
    }

    @SneakyThrows
    private static Properties load(String fileName) {
        @Cleanup InputStream in = UnionpayGlobalConfig.class.getResourceAsStream(fileName);
        Properties properties = new Properties();
        properties.load(in);

        return properties;
    }

    public static void main(String[] args) {
        init();
    }
}
