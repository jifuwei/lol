package com.github.lol.pay.component.unionpay.core;

import com.github.lol.lib.util.StrUtil;
import com.github.lol.lib.util.ValidUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.*;

/**
 * certificate util
 *
 * @author: jifuwei
 * @create: 2019-07-12 14:22
 **/
@Slf4j
public class CertificateService {

    private UnionpayConfig config;

    public CertificateService(UnionpayConfig config) {
        this.config = config;
        init();
    }

    public static CertificateService of(UnionpayConfig config) {
        return new CertificateService(config);
    }

    /**
     * 证书容器，存储对商户请求报文签名私钥证书
     */
    private KeyStore keyStore;

    /**
     * 验签中级证书
     */
    private X509Certificate middleCert;

    /**
     * 验签根证书
     */
    private X509Certificate rootCert;

    /**
     * 敏感信息加密公钥证书
     */
    private static X509Certificate encryptCert;

    /**
     * 磁道加密公钥
     */
    private PublicKey encryptTrackKey;

    /**
     * 验证银联返回报文签名证书.
     */
    private X509Certificate validateCert;

    /**
     * 验证银联返回报文签名的公钥证书存储Map.
     */
    private Map<String, X509Certificate> certMap = new HashMap<>();

    /**
     * init all certificate
     */
    private void init() {
        // 向系统添加BC provider
        addProvider();
        // 初始化签名私钥证书
        initSignCert();
        // 初始化验签证书的中级证书
        initMiddleCert();
        // 初始化验签证书的根证书
        initRootCert();
        // 初始化加密公钥
        initEncryptCert();
        // 构建磁道加密公钥
        initTrackKey();
        // 初始化所有的验签证书
        initValidateCertFromDir();
    }

    /**
     * add sign, verify，encrypt provider
     */
    private void addProvider() {
        if (Security.getProvider(DEFAULT_PROVIDER) == null) {
            log.info("==> add [provider] name: {}", DEFAULT_PROVIDER);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } else {
            // 解决eclipse调试时tomcat自动重新加载时，BC存在不明原因异常的问题。
            Security.removeProvider(DEFAULT_PROVIDER);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            log.info("==> re-add [provider] name: {}", DEFAULT_PROVIDER);
        }

        printSysInfo();
    }

    private void printSysInfo() {
        log.info("================= SYS INFO begin====================");
        log.info("os_name: {}", System.getProperty("os.name"));
        log.info("os_arch: {}", System.getProperty("os.arch"));
        log.info("os_version: {}", System.getProperty("os.version"));
        log.info("java_vm_specification_version: {}", System.getProperty("java.vm.specification.version"));
        log.info("java_vm_specification_vendor: {}", System.getProperty("java.vm.specification.vendor"));
        log.info("java_vm_specification_name: {}", System.getProperty("java.vm.specification.name"));
        log.info("java_vm_version: {}", System.getProperty("java.vm.version"));
        log.info("java_vm_name: {}", System.getProperty("java.vm.name"));
        log.info("java.version: {}", System.getProperty("java.version"));
        log.info("java.vm.vendor=[{}]", System.getProperty("java.vm.vendor"));
        log.info("java.version=[{}]", System.getProperty("java.version"));
        printProviders();
        log.info("================= SYS INFO end=====================");
    }

    private void printProviders() {
        log.info("Providers List:");
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            log.info(i + 1 + "." + providers[i].getName());
        }
    }

    /**
     * init sign certificate
     */
    private void initSignCert() {
        if (!SIGN_METHOD_RSA.equals(config.getSignMethod())) {
            log.warn("非RSA签名方式，不需要加载签名证书");
            return;
        }

        if (StringUtils.isEmpty(config.getSignCertPath())
                || StringUtils.isEmpty(config.getSignCertPwd())
                || StringUtils.isEmpty(config.getSignCertType())) {
            throw new RuntimeException("can't find certificate or pwd or certType");
        }

        if (null != keyStore) {
            keyStore = null;
        }

        keyStore = getKeyInfo(config.getSignCertPath(), config.getSignCertPwd(), config.getSignCertType());
        log.info("==> InitSignCert Successful. [CertId]: {}", getSignCertId());
    }

    /**
     * init middle certificate
     */
    private void initMiddleCert() {
        if (StringUtils.isEmpty(config.getMiddleCertPath())) {
            throw new RuntimeException("middleCertPath is empty");
        }

        middleCert = initCert(config.getMiddleCertPath());
        log.info("==> Load MiddleCert Successful");
    }

    private void initRootCert() {
        if (StringUtils.isEmpty(config.getRootCertPath())) {
            throw new RuntimeException("rootCertPath is empty");
        }

        rootCert = initCert(config.getRootCertPath());
        log.info("==> Load RootCert Successful");
    }

    private void initEncryptCert() {
        if (StringUtils.isEmpty(config.getEncryptCertPath())) {
            throw new RuntimeException("encryptCertPath is empty");
        }

        encryptCert = initCert(config.getEncryptCertPath());
        log.info("==> Load EncryptCert Successful");
    }

    private void initTrackKey() {
        if (StringUtils.isEmpty(config.getEncryptTrackKeyModulus())
                || StringUtils.isEmpty(config.getEncryptTrackKeyExponent())) {

            log.warn("==> encryptTrackKey or encryptTrackKeyExponent is empty");
            return;
        }

        encryptTrackKey = getPublicKey(config.getEncryptTrackKeyModulus(), config.getEncryptTrackKeyExponent());
        log.info("==> Load EncryptCert Successful");
    }

    private void initValidateCertFromDir() {
        if (!SIGN_METHOD_RSA.equals(config.getSignMethod())) {
            log.warn("==> 非RSA签名方式，不需要加载签名证书");
            return;
        }

        certMap.clear();
        String dir = config.getValidateCertDir();
        if (StringUtils.isEmpty(dir)) {
            log.warn("==> validateCert dir empty");
            return;
        }

        FileInputStream in = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance(DEFAULT_CERT_TYPE, DEFAULT_PROVIDER);
            File fileDir = new File(dir);
            File[] files = fileDir.listFiles(new CerFilter());

            assert files != null;
            for (File file : files) {
                try {
                    in = new FileInputStream(file.getAbsolutePath());
                    validateCert = (X509Certificate) cf.generateCertificate(in);
                    if (validateCert == null) {
                        log.error("==> Load verify cert error, {} has error cert content.", file.getAbsolutePath());
                        continue;
                    }

                    certMap.put(validateCert.getSerialNumber().toString(), validateCert);
                    // 打印证书加载信息,供测试阶段调试
                    log.info("==> [path]: {}, [CertId]: {}", file.getAbsolutePath(),
                            validateCert.getSerialNumber().toString());

                } catch (CertificateException e) {
                    log.error("LoadVerifyCert Error", e);
                } catch (FileNotFoundException e) {
                    log.error("LoadVerifyCert Error File Not Found", e);
                } finally {
                    if (null != in) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            log.error(e.toString());
                        }
                    }
                }
            }

            log.info("==> initValidateCertFromDir success");
        } catch (Exception e) {
            throw new RuntimeException("initValidateCertFromDir error", e);
        }
    }

    private PublicKey getPublicKey(String encryptTrackKeyModulus, String encryptTrackKeyExponent) {
        try {
            BigInteger b1 = new BigInteger(encryptTrackKeyModulus);
            BigInteger b2 = new BigInteger(encryptTrackKeyExponent);
            KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ALGORITHM, DEFAULT_PROVIDER);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);

            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("构造RSA公钥失败", e);
        }
    }


    private X509Certificate initCert(String path) {
        try (FileInputStream in = new FileInputStream(path)) {
            CertificateFactory cf = CertificateFactory.getInstance(DEFAULT_CERT_TYPE, DEFAULT_PROVIDER);
            X509Certificate encryptCertTemp = (X509Certificate) cf.generateCertificate(in);

            // 打印证书加载信息,供测试阶段调试
            log.info("==> [path]: {}, [CertId]: {}", path, encryptCertTemp.getSerialNumber().toString());

            return encryptCertTemp;
        } catch (Exception e) {
            throw new RuntimeException("InitCert Error", e);
        }
    }

    /**
     * 将签名私钥证书文件读取为证书存储对象
     *
     * @param signCertPath
     * @param signCertPwd
     * @param signCertType
     * @return
     */
    private KeyStore getKeyInfo(String signCertPath, String signCertPwd, String signCertType) {

        try (FileInputStream fis = new FileInputStream(signCertPath)) {
            KeyStore ks = KeyStore.getInstance(signCertType, DEFAULT_PROVIDER);
            log.info("==> load RSA Cert [path]: {}, [pwd]: {}, [type]: {}", signCertPath, signCertPwd, signCertType);
            char[] nPassword = StringUtils.isEmpty(signCertPwd) ? null : signCertPwd.toCharArray();
            ks.load(fis, nPassword);

            return ks;
        } catch (Exception e) {
            throw new RuntimeException("getKeyInfo error", e);
        }
    }

    public String getSignCertId() {
        try {
            Enumeration<String> aliasenum = keyStore.aliases();
            String keyAlias = null;
            if (aliasenum.hasMoreElements()) {
                keyAlias = aliasenum.nextElement();
            }

            X509Certificate cert = (X509Certificate) keyStore.getCertificate(keyAlias);

            return cert.getSerialNumber().toString();
        } catch (Exception e) {
            throw new RuntimeException("getSignCertId Error", e);
        }
    }

    public PrivateKey getSignCertPrivateKey() {
        try {
            Enumeration<String> aliasenum = keyStore.aliases();
            String keyAlias = null;
            if (aliasenum.hasMoreElements()) {
                keyAlias = aliasenum.nextElement();
            }
            return (PrivateKey) keyStore.getKey(keyAlias,
                    config.getSignCertPwd().toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("getSignCertPrivateKey error", e);
        }
    }

    @SneakyThrows
    public X509Certificate genCertificateByStr(String certStr) {
        CertificateFactory cf = CertificateFactory.getInstance(DEFAULT_CERT_TYPE, DEFAULT_PROVIDER);
        @Cleanup InputStream tIn = new ByteArrayInputStream(certStr.getBytes(DEFAULT_CHARSET_NAME));
        return (X509Certificate) cf.generateCertificate(tIn);
    }

    @SneakyThrows
    public boolean verifyCertificate(X509Certificate cert) {
        ValidUtil.notNull(cert);

        cert.checkValidity();
        if (!verifyCertificateChain(cert)) {
            throw new RuntimeException("verifyCertificate fail");
        }

        if (config.isIfValidateCNName()) {
            // 验证证书是否属于银联
            if (!UNIONPAY_CNNAME.equals(getIdentitiesFromCertficate(cert))) {
                throw new RuntimeException("cer owner is not CUP:" + getIdentitiesFromCertficate(cert));
            }
        } else {
            // 验证公钥是否属于银联
            if (!UNIONPAY_CNNAME.equals(getIdentitiesFromCertficate(cert))
                    && !"00040000:SIGN".equals(getIdentitiesFromCertficate(cert))) {
                throw new RuntimeException("cer owner is not CUP:" + getIdentitiesFromCertficate(cert));
            }
        }

        return true;
    }

    private String getIdentitiesFromCertficate(X509Certificate aCert) {
        String tDN = aCert.getSubjectDN().toString();
        String tPart = "";
        if (Objects.isNull(tDN)) {
            return tPart;
        }

        String[] tSplitStr = tDN.substring(tDN.indexOf("CN=")).split("@");
        if (tSplitStr.length > 2 && tSplitStr[2] != null) {
            tPart = tSplitStr[2];
        }

        return tPart;
    }

    @SneakyThrows
    private boolean verifyCertificateChain(X509Certificate cert) {
        ValidUtil.notNull(cert);

        X509Certificate middleCert = getMiddleCert();
        if (Objects.isNull(middleCert)) {
            throw new RuntimeException("can't load middleCert");
        }

        X509Certificate rootCert = getRootCert();
        if (Objects.isNull(rootCert)) {
            throw new RuntimeException("can't load rootCert");
        }

        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(cert);

        Set<TrustAnchor> trustAnchors = new HashSet<>();
        trustAnchors.add(new TrustAnchor(rootCert, null));
        PKIXBuilderParameters pkixParams = new PKIXBuilderParameters(
                trustAnchors, selector);

        Set<X509Certificate> intermediateCerts = new HashSet<>();
        intermediateCerts.add(rootCert);
        intermediateCerts.add(middleCert);
        intermediateCerts.add(cert);

        pkixParams.setRevocationEnabled(false);

        CertStore intermediateCertStore = CertStore.getInstance("Collection",
                new CollectionCertStoreParameters(intermediateCerts), DEFAULT_PROVIDER);
        pkixParams.addCertStore(intermediateCertStore);

        CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", DEFAULT_PROVIDER);

        @SuppressWarnings("unused")
        PKIXCertPathBuilderResult result = (PKIXCertPathBuilderResult) builder
                .build(pkixParams);

        return true;
    }

    private X509Certificate getRootCert() {
        if (Objects.isNull(rootCert)) {
            String path = config.getRootCertPath();
            if (StrUtil.isEmpty(path)) {
                log.warn("==> not set middleCertPath");
                return null;
            }

            initRootCert();
        }

        return rootCert;
    }

    private X509Certificate getMiddleCert() {
        if (Objects.isNull(middleCert)) {
            String path = config.getMiddleCertPath();
            if (StrUtil.isEmpty(path)) {
                log.warn("==> not set middleCertPath");
                return null;
            }

            initMiddleCert();
        }

        return middleCert;
    }

    /**
     * 证书文件过滤器
     */
    static class CerFilter implements FilenameFilter {
        boolean isCer(String name) {
            if (name.toLowerCase().endsWith(".cer")) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean accept(File dir, String name) {
            return isCer(name);
        }
    }
}
