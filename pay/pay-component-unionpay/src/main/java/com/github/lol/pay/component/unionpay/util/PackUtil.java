package com.github.lol.pay.component.unionpay.util;

import com.github.lol.lib.util.StrUtil;
import com.github.lol.pay.component.unionpay.product.gateway.model.GatewayFileTransferSyncResp;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.github.lol.pay.component.unionpay.constant.UnionpayConstant.UTF_8_ENCODING;

/**
 * mix util
 *
 * @author: jifuwei
 * @create: 2019-07-19 14:52
 **/
@Slf4j
public class PackUtil {

    private final static int[] ZM_LENGTHARRAY = {3, 11, 11, 6, 10, 19, 12, 4, 2, 21, 2,
            32, 2, 6, 10, 13, 13, 4, 15, 2, 2, 6, 2, 4, 32, 1, 21, 15, 1, 15, 32,
            13, 13, 8, 32, 13, 13, 12, 2, 1, 32, 98};

    private final static int[] ZME_LENGTHARRAY = {3, 11, 11, 6, 10, 19, 12, 4, 2, 2, 6,
            10, 4, 12, 13, 13, 15, 15, 1, 12, 2, 135};

    /**
     * 分析文件
     *
     * @param syncResp
     * @param savePath
     * @param encoding
     * @return
     */
    @SneakyThrows
    public static String doCodeFileContent(@NonNull GatewayFileTransferSyncResp syncResp,
                                           @NonNull String savePath, String encoding) {
        encoding = StrUtil.isEmpty(encoding) ? UTF_8_ENCODING : encoding;
        String fileContent = syncResp.getFileContent();
        if (StrUtil.isEmpty(fileContent)) {
            log.warn("file conteny is Empty");
            return null;
        }

        byte[] fileArray = PackUtil.inflater(SecurityUtil.base64Decode(fileContent.getBytes(encoding)));
        savePath = StrUtil.isEmpty(syncResp.getFileName()) ?
                savePath + File.separator + syncResp.getMerId() + "_" + syncResp.getTxnTime() + ".txt" :
                savePath + File.separator + syncResp.getFileName();

        File file = new File(savePath);
        if (file.exists()) {
            file.delete();
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            log.debug("==> 目标文件所在目录不存在，创建成功");
        }

        file.createNewFile();
        @Cleanup FileOutputStream out = new FileOutputStream(file);
        out.write(fileArray, 0, fileArray.length);
        out.flush();

        return savePath;
    }

    /**
     * 解压缩
     *
     * @param inputByte
     * @return
     */
    @SneakyThrows
    public static byte[] inflater(@NonNull final byte[] inputByte) {
        Inflater compresser = new Inflater(false);
        compresser.setInput(inputByte, 0, inputByte.length);
        @Cleanup ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
        byte[] result = new byte[1024];
        int compressedDataLength;
        while (!compresser.finished()) {
            compressedDataLength = compresser.inflate(result);
            if (compressedDataLength == 0) {
                break;
            }
            o.write(result, 0, compressedDataLength);
        }

        compresser.end();

        return o.toByteArray();
    }

    /**
     * 解压压缩包
     *
     * @param zipFilePath
     * @param outPutDirectory
     * @return
     */
    @SneakyThrows
    public static List<String> unzip(String zipFilePath, String outPutDirectory) {
        List<String> fileList = new ArrayList<>();
        @Cleanup ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath));
        @Cleanup BufferedInputStream bin = new BufferedInputStream(zin);

        @Cleanup BufferedOutputStream bout = null;
        File file = null;
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null && !entry.isDirectory()) {
            file = new File(outPutDirectory, entry.getName());
            if (!file.exists()) {
                (new File(file.getParent())).mkdirs();
            }

            bout = new BufferedOutputStream(new FileOutputStream(file));
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
            bout.flush();
            fileList.add(file.getAbsolutePath());
            log.debug("==> 解压缩成功 file: {}" + file);
        }

        return fileList;
    }

    /**
     * 解析全渠道商户对账文件中的ZME文件并以List<Map>方式返回
     *
     * @param filePath
     * @return
     */
    public static List<Map> parseZMFile(String filePath) {
        return parseFile(filePath, ZM_LENGTHARRAY);
    }

    /**
     * 解析全渠道商户对账文件中的ZME文件并以List<Map>方式返回
     *
     * @param filePath
     */
    public static List<Map> parseZMEFile(String filePath) {
        return parseFile(filePath, ZME_LENGTHARRAY);
    }

    /**
     * 解析全渠道商户 ZM,ZME对账文件
     *
     * @param filePath
     * @param lengthArray
     * @return
     */
    @SneakyThrows
    private static List<Map> parseFile(String filePath, int[] lengthArray) {
        List<Map> zmDataList = new ArrayList<>();
        // 文件是gbk编码
        String encoding = "gbk";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            @Cleanup InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.ISO_8859_1);
            @Cleanup BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                byte[] bs = lineTxt.getBytes(StandardCharsets.ISO_8859_1);
                // 解析的结果MAP，key为对账文件列序号，value为解析的值
                Map<Integer, String> ZmDataMap = new LinkedHashMap<>();
                // 左侧游标
                int leftIndex = 0;
                // 右侧游标
                int rightIndex = 0;
                for (int i = 0; i < lengthArray.length; i++) {
                    rightIndex = leftIndex + lengthArray[i];
                    String filed = new String(Arrays.copyOfRange(bs, leftIndex, rightIndex), encoding);
                    leftIndex = rightIndex + 1;
                    ZmDataMap.put(i, filed);
                }

                zmDataList.add(ZmDataMap);
            }
        }

        return zmDataList;
    }

    public static String getFileContentTable(List<Map> zmDataList) {
        return zmDataList.toString();
    }
}
