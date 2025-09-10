package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Base64;
/**
 * @Author: success
 * @Date: 2025/9/9 13:54
 * @Version: v1.0.0
 * @Description: TODO
 **/
public class YocylApiDemo2 {

    // 应用私钥（纯Base64编码内容）
    private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS0BX4rCjWYGV5WRwsVY2A0DtVg35pph2qk5FJUZm6AhXGhm/ZwqXbXxq6xggyU6ddA2/y/xNN5WuuKf5h4imUczDm0LkHKhu99eVWMiOqU7LguGog/uGuG1CcK6gne/txujBmwmY4dWBxo4moCm5SlUmz4kH7BFKuZGp+P66D/jcVT+1HT5ScXfPeCkEyrwI1ScnaJOTP0uQ0rLi9xO4uu7RiKtDaONbCPYzax+CIo5SgZOZ909JWJFNolzCTEQrGxNdquK1dUktdTAYkk36Xg/C7vcJ/U/W6pIEOyr6p+zPg+Y87T2PY6nfb7/H6pxotDCN4TPIIC9jlmq9M+ND1AgMBAAECggEAfczaltwGnjO5n/KwhnjEqNVFkm/7eVIDZ6/NNbM7c7znpdZ6r3DwJ/rlb5fTXDR8W+JfIixaAUyOKY4IUR23nWHbTF0bvzzgyrSS0HpkP0Y7J/49yoo9HbCAMWHjU/oUKo5tfRAlAUnq21VT1m8hu5f6sK429X3tz0tyST4OL0DueM54R9N7WXs2tquWkj6RD4aZca5wSqtR254NakRmBSVdTVEKbMvgXoEsegjwbcy4hunxOCXfz89UG/LjwRRgBMM4scVHI3ERZg2yImhc5pDk3zv9zmP/glz+pEkaA+KfdWU5p1Gjs3KRG/KBnS+m+0agp7MG5aQbe6UzPP2dAQKBgQDbLcr+wv3aPDmFBmvIUzSRELFijw8JX4hYFdJNUFBOwO6ePUllgP1lzK0sfnqu4fInc3pq0NtubfU9oBKsIJtbSDkWER1OUG079JW6aa13cZOASHMIhAnBbFIQ9yPvRy8TEuCyP7z0CQcrE7CgLPUswqWo+g9isynK9oPcxo+2tQKBgQCreg1+pp6eMbSSbXcIwPd0+fI31iSWF4y9tt7v5oG6QjWmlhakiZybqt9jEUOo4j8fFmTjArx7DqYvSHhD3D4XpSbqeBTkPwjOJsQpkSzvsJGSYToCfojDWsv3gqPCTXuX76Z0YKLLCObUkX2Y5HgEpGuFSoPS/OyEFgtTc6zZQQKBgQCY3fZ/+2XsKScBBbp07Lt0Fg1yLU8SfYPt08Jq1AI++0cyLJKdbfrOXpPFva05fjNTmrId/++btKtgQN3lGZThdJ0ELAmhjmyxbWRksMIg1aFHzsAUh2r6cd9HH+f7Qk2t4vO+vr++APHz9HazMMgLYPMDyOykLUuP2KGdgQvJJQKBgCTXFA8hvPI/u4u5+NjonEGFcYSNfU7BwHsBzuO7oiNiFiS0Gik/Z2YKT/P4wZCHCwiixwn+jH/jpdCCwVPS/YQW90VohxSCdmHT8lD736ufQ6cvPEdM6BUQbMHAT25vNx5tXlWibVcxkmYY2+L9MsvMh00btTafIDFAiy5iq8ABAoGAPkje6LWiXe5SBx5soiwF4SMSZo0sr5/dvxLC7YtX0qYpmGBhGoT6+EBT8VdETaMYvRoRqvl0q0S0tqDHE+10s2J0XgBaA6aRRVHzRnllAzMZ+iA5fSvb2kyrh/OczE6xXnZdvujo0OA3tCLQKgxJe+3n1/2BkzqkFMKLrkbhIhI=";
    // 应用公钥（平台验签时用）
    private static final String APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAktAV+Kwo1mBleVkcLFWNgNA7VYN+aaYdqpORSVGZugIVxoZv2cKl218ausYIMlOnXQNv8v8TTeVrrin+YeIplHMw5tC5ByobvfXlVjIjqlOy4LhqIP7hrhtQnCuoJ3v7cbowZsJmOHVgcaOJqApuUpVJs+JB+wRSrmRqfj+ug/43FU/tR0+UnF3z3gpBMq8CNUnJ2iTkz9LkNKy4vcTuLru0YirQ2jjWwj2M2sfgiKOUoGTmfdPSViRTaJcwkxEKxsTXaritXVJLXUwGJJN+l4Pwu73Cf1P1uqSBDsq+qfsz4PmPO09j2Op32+/x+qcaLQwjeEzyCAvY5ZqvTPjQ9QIDAQAB";
    // AES Key（和平台约定好的）
    private static final String AES_KEY = "v8MB73faXlVdb8uPSmmjge3oALKwudOO";
    // AES IV (16字节，全零)
    //private static final String AES_IV = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";

    /** RSA 私钥对象 */
    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    /** RSA 公钥对象 */
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    /** 生成RSA2签名（应用私钥） */
    public static String sign(Map<String, String> params) throws Exception {
        // 1. 排序
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        // 2. 拼接
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            if ("sign".equals(key)) {
                continue;
            }
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        String signContent = sb.substring(0, sb.length() - 1);

        // 3. 签名
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(getPrivateKey(APP_PRIVATE_KEY));
        signature.update(signContent.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /** 验签（平台用应用公钥） */
    public static boolean verify(Map<String, String> params, String sign) throws Exception {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            if ("sign".equals(key)) {
                continue;
            }
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        String signContent = sb.substring(0, sb.length() - 1);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(getPublicKey(APP_PUBLIC_KEY));
        signature.update(signContent.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    private static byte[] initIv() {
        int blockSize = 16;
        byte[] iv = new byte[blockSize];
        for(int i = 0; i < blockSize; ++i) {
            iv[i] = 0;
        }
        return iv;
    }

    /** AES CBC 加密 */
    public static String encryptAES(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        // 这里平台给的是Base64编码的AES Key，这里需要解码
        byte[] keyBytes = Base64.getDecoder().decode(AES_KEY);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(initIv());
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /** AES CBC 解密（模拟平台解密） */
    public static String decryptAES(String base64Data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        byte[] keyBytes = Base64.getDecoder().decode(AES_KEY);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(initIv());
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(base64Data));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    /** 构建请求 URL */
    public static String buildUrl(String gateway, Map<String, String> params, String sign) throws Exception {
        StringBuilder sb = new StringBuilder(gateway).append("?");
        for (Map.Entry<String, String> e : params.entrySet()) {
            if ("bizContent".equals(e.getKey())) {
                continue; // bizContent 不拼URL
            }
            sb.append(e.getKey()).append("=")
                    .append(URLEncoder.encode(e.getValue(), "UTF-8"))
                    .append("&");
        }
        sb.append("sign=").append(URLEncoder.encode(sign, "UTF-8"));
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        // 网关地址
        String gateway = "https://openapi-test.yocyl.com/api";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);

        // 业务参数（原始未加密内容）
        //String originalBizContent = "{\"batchInfo\":[{\"applyOrgName\":\"扬帆集团\",\"currencyCode\":\"CNY\",\"payBizTypeCode\":\"单笔\",\"payWayCode\":\"A1\",\"purpose\":\"往来\",\"recAccountName\":\"杭州经济技术开发区普奔起重设备租赁服务部\",\"recAccountNumber\":\"12401214889632100000\",\"recBankLocation\":\"农业银行xxxx支行\",\"recName\":\"杭州经济技术开发区普奔起重设备租赁服务部\",\"sourceFlowNumber\":\"cs001-001\",\"sourceNoteCode\":\"cs001\",\"transAmount\":100,\"dataBizType\":2}],\"payOrgName\":\"扬帆集团\",\"sourceBatchNumber\":\"cs001-001\",\"totalAmount\":100,\"totalNum\":1}";
        String originalBizContent = "{\"pageNo\":1,\"pageSize\":100}";
        // 公共参数
        Map<String, String> params = new TreeMap<>();
        //params.put("appId", "20250909100641821272");
        params.put("appId", "20250909102701729790");
        //params.put("command", "yocyl.pay.query");
        params.put("command","yocyl.account.trans.query");
        //params.put("version", "1.0.0");
        params.put("version","2.0.0");
        params.put("timestamp", timestamp);
        params.put("charset", "UTF-8");
        params.put("format", "json");
        params.put("signType", "RSA2");
        params.put("encryptType", "AES"); // 当为空或非AES时，将不加密

        // 根据encryptType决定是否加密
        String encryptType = params.get("encryptType");
        String bizToSend;
        boolean isEncrypted = false;
        if (encryptType == null || encryptType.trim().isEmpty() || !"AES".equalsIgnoreCase(encryptType.trim())) {
            // 不加密：移除encryptType参数，使用原始内容
            params.remove("encryptType");
            bizToSend = originalBizContent;
        } else {
            // 加密：AES/CBC/PKCS5Padding，全零IV
            bizToSend = encryptAES(originalBizContent);
            isEncrypted = true;
        }
        params.put("bizContent", bizToSend);

        // 基于当前参数（可能包含或不包含encryptType，且bizContent为加密或明文）生成签名
        Map<String, String> signParams = new TreeMap<>(params);
        String sign = sign(signParams);

        // 构建请求 URL
        String url = buildUrl(gateway, params, sign);

        // 打印
        System.out.println("原始业务参数: " + originalBizContent);
        if (isEncrypted) {
            System.out.println("加密后业务参数: " + bizToSend);
        } else {
            System.out.println("未加密，直接发送业务参数: " + bizToSend);
        }
        System.out.println("请求URL: " + url);
        System.out.println("请求Body: bizContent=" + bizToSend);
        System.out.println("签名: " + sign);

        // 平台验证（模拟）- 使用当前bizContent（加密或未加密）验证签名
        System.out.println("开始验签...");
        Map<String, String> verifyParams = new TreeMap<>(params);
        boolean verifyOk = verify(verifyParams, sign);
        System.out.println("平台验签结果: " + verifyOk);

        // 测试解密（仅在加密时）
        if (isEncrypted) {
            String decryptedBiz = decryptAES(bizToSend);
            System.out.println("平台解密结果: " + decryptedBiz);
            System.out.println("解密内容与原始内容一致: " + originalBizContent.equals(decryptedBiz));
        }
    }
}