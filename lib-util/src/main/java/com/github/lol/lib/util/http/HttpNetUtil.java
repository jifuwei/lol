package com.github.lol.lib.util.http;

import com.github.lol.lib.util.StrUtil;
import com.github.lol.lib.util.ValidUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * http client util
 *
 * @author: jifuwei
 * @create: 2019-07-17 10:03
 **/
@Slf4j
public class HttpNetUtil {

    private final static boolean DEFAULT_DO_INPUT = true;
    private final static boolean DEFAULT_DO_OUTPUT = true;
    private final static String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=";
    private final static String HTTPS_PROTOCOL = "https";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT = 30000;
    private static final boolean DEFAULT_CACHE_ENABLE = false;
    private static final String DEFAULT_RESP_NONE_MSG = "[lol-default]远程服务无任何返回";

    /**
     * http method
     */
    public final static String HTTP_METHOD_GET = "GET";
    public final static String HTTP_METHOD_POST = "POST";

    /**
     * http code
     */
    private final static int CODE_SUCCESS = 200;

    private String encoding;
    private String reqUrl;
    private String reqMethod;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Boolean cacheEnable;
    // need to verify certificate
    private Boolean verifyCert;


    private HttpURLConnection conn;
    // need send data
    private Map<String, Object> requestFormMap;

    public static HttpNetUtil of(String url, String reqMethod) {
        return new HttpNetUtil(url, reqMethod);
    }

    public HttpNetUtil(String url, String reqMethod) {
        new HttpNetUtil(url, reqMethod,
                null, null, null, null, null);
    }

    public HttpNetUtil(String url, String reqMethod, String encoding, Integer connectTimeout,
                       Integer readTimeout, Boolean cacheEnable, Boolean verifyCert) {
        this.reqUrl = url;
        this.encoding = StrUtil.isEmpty(encoding) ? DEFAULT_ENCODING : encoding;
        this.reqMethod = reqMethod;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.cacheEnable = cacheEnable;
        this.verifyCert = verifyCert;

        // init
        config();
    }

    /**
     * set url connection config
     */
    @SneakyThrows(IOException.class)
    private void config() {
        ValidUtil.notEmpty(reqUrl);

        URL url = new URL(reqUrl);
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout((Objects.isNull(connectTimeout) || connectTimeout <= 0) ?
                DEFAULT_CONNECT_TIMEOUT : connectTimeout);
        conn.setReadTimeout((Objects.isNull(readTimeout) || readTimeout <= 0) ?
                DEFAULT_READ_TIMEOUT : readTimeout);
        conn.setDoInput(DEFAULT_DO_INPUT);
        conn.setDoOutput(DEFAULT_DO_OUTPUT);
        conn.setUseCaches(Objects.isNull(cacheEnable) ? DEFAULT_CACHE_ENABLE : cacheEnable);
        conn.setRequestProperty("Content-type", DEFAULT_CONTENT_TYPE + encoding);
        conn.setRequestMethod(reqMethod);

        if (!HTTPS_PROTOCOL.equalsIgnoreCase(url.getProtocol())
                || Objects.isNull(verifyCert) || !verifyCert) {
            return;
        }

        // verify https certificate
        HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
        httpsConn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
        httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
    }

    /**
     * get
     *
     * @return
     */
    public String get() {
        return request();
    }

    public Map<String, String> post(Map<String, Object> requestFormMap) {
        this.requestFormMap = requestFormMap;

        String respSeq = request();

        return covertRespSeq2Map(respSeq);
    }

    /**
     * request service
     *
     * @return
     */
    @SneakyThrows(IOException.class)
    private String request() {
        try {
            log.debug("{} begin connect", preLogStr());
            // 1.begin
            conn.connect();
            log.debug("{} reqUrl: {} reqMethod: {} encoding: {}", preLogStr(), reqUrl, reqMethod, encoding);
            log.debug("{} connectTimeout: {} readTimeout: {} cacheEnable: {} verifyCert: {}",
                    preLogStr(), connectTimeout, readTimeout, cacheEnable, verifyCert);

            // 2.send data
            if (HTTP_METHOD_POST.equals(reqMethod)
                    && !Objects.isNull(requestFormMap) && !requestFormMap.isEmpty()) {

                String encodeReqSeq = convertMap2EncodeSeq(requestFormMap);
                log.debug("{} encodeReqSeq: {}", preLogStr(), encodeReqSeq);
                @Cleanup PrintStream out = new PrintStream(conn.getOutputStream(), false, encoding);
                out.print(encodeReqSeq);
                out.flush();

            }

            // 3.response
            String respSeq;
            log.debug("{} resp code: {}", preLogStr(), conn.getResponseCode());
            @Cleanup InputStream in = null;
            if (CODE_SUCCESS != conn.getResponseCode()) {
                in = conn.getErrorStream();
                respSeq = read(in);
                log.error("{} server resp msg: {}", preLogStr(), respSeq);
                throw new RuntimeException(preLogStr() + " request failed, respCode: " +
                        conn.getResponseCode() + " respMsg: " + respSeq);
            }

            in = conn.getInputStream();
            respSeq = read(in);
            log.debug("{} resp: {}", preLogStr(), respSeq);

            return respSeq;
        } finally {
            if (null != conn) {
                conn.disconnect();
                log.debug("{} end connect", preLogStr());
            }
        }
    }

    private Map<String, String> covertRespSeq2Map(String respSeq) {
        if (Objects.isNull(respSeq) || respSeq.isEmpty()) {
            return null;
        }

        if (respSeq.startsWith("{") && respSeq.endsWith("}")) {
            respSeq = respSeq.substring(1, respSeq.length() - 1);
        }

        return parseQString(respSeq);
    }

    private Map<String, String> parseQString(String str) {
        Map<String, String> map = new HashMap<String, String>();
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        char curChar;
        String key = null;
        boolean isKey = true;
        boolean isOpen = false;//值里有嵌套
        char openName = 0;
        if (len > 0) {
            for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
                curChar = str.charAt(i);// 取当前字符
                if (isKey) {// 如果当前生成的是key

                    if (curChar == '=') {// 如果读取到=分隔符
                        key = temp.toString();
                        temp.setLength(0);
                        isKey = false;
                    } else {
                        temp.append(curChar);
                    }
                } else {// 如果当前生成的是value
                    if (isOpen) {
                        if (curChar == openName) {
                            isOpen = false;
                        }

                    } else {//如果没开启嵌套
                        if (curChar == '{') {//如果碰到，就开启嵌套
                            isOpen = true;
                            openName = '}';
                        }
                        if (curChar == '[') {
                            isOpen = true;
                            openName = ']';
                        }
                    }

                    if (curChar == '&' && !isOpen) {// 如果读取到&分割符,同时这个分割符不是值域，这时将map里添加
                        putKeyValueToMap(temp, isKey, key, map);
                        temp.setLength(0);
                        isKey = true;
                    } else {
                        temp.append(curChar);
                    }
                }

            }
            putKeyValueToMap(temp, isKey, key, map);
        }
        return map;
    }

    private void putKeyValueToMap(StringBuilder temp, boolean isKey, String key,
                                  Map<String, String> map) {

        key = isKey ? temp.toString() : key;
        String val = isKey ? "" : temp.toString();
        map.put(key, val);
    }

    @SneakyThrows(IOException.class)
    private String read(InputStream in) {
        if (Objects.isNull(in)) {
            return DEFAULT_RESP_NONE_MSG;
        }

        byte[] buf = new byte[1024];
        int length;

        @Cleanup ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            bout.write(buf, 0, length);
        }
        bout.flush();
        byte[] target = bout.toByteArray();


        return new String(target, encoding);
    }


    private String convertMap2EncodeSeq(Map<String, Object> dataMap) {
        if (Objects.isNull(dataMap) || dataMap.isEmpty()) {
            return null;
        }

        return dataMap.entrySet().stream().map(this::encodeEntry).collect(Collectors.joining("&"));
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private String encodeEntry(Map.Entry e) {
        String encodeVal = null == e.getValue() || e.getValue().toString().isEmpty() ?
                "" : URLEncoder.encode(e.getValue().toString(), encoding);
        return e.getKey() + "=" + encodeVal;
    }

    private String preLogStr() {
        return "[HttpNetUtil]:";
    }


    /**
     * simple ssl factory
     */
    static class BaseHttpSSLSocketFactory extends SSLSocketFactory {
        private SSLContext getSSLContext() {
            return createEasySSLContext();
        }

        @Override
        public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2,
                                   int arg3) throws IOException {
            return getSSLContext().getSocketFactory().createSocket(arg0, arg1,
                    arg2, arg3);
        }

        @Override
        public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
                throws IOException {
            return getSSLContext().getSocketFactory().createSocket(arg0, arg1,
                    arg2, arg3);
        }

        @Override
        public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
            return getSSLContext().getSocketFactory().createSocket(arg0, arg1);
        }

        @Override
        public Socket createSocket(String arg0, int arg1) throws IOException {
            return getSSLContext().getSocketFactory().createSocket(arg0, arg1);
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return null;
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return null;
        }

        @Override
        public Socket createSocket(Socket arg0, String arg1, int arg2, boolean arg3)
                throws IOException {
            return getSSLContext().getSocketFactory().createSocket(arg0, arg1,
                    arg2, arg3);
        }

        private SSLContext createEasySSLContext() {
            try {
                SSLContext context = SSLContext.getInstance("SSL");
                context.init(null,
                        new TrustManager[]{MyX509TrustManager.manger}, null);
                return context;
            } catch (Exception e) {
                return null;
            }
        }

        public static class MyX509TrustManager implements X509TrustManager {

            static MyX509TrustManager manger = new MyX509TrustManager();

            public MyX509TrustManager() {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }
    }

    /**
     * 解决由于服务器证书问题导致HTTPS无法访问的情况 PS:HTTPS hostname wrong: should be <localhost>
     */
    class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // direct true
            return true;
        }
    }
}
