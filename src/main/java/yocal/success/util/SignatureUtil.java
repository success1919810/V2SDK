package yocal.success.util;

import yocal.success.config.YocalV2Config;
import yocal.success.exception.YocalException;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import java.util.List;

/**
 * 签名工具类
 * 抽取自YocylApiDemo2中的签名方法
 */
public class SignatureUtil {

    /**
     * 生成RSA2签名
     * @param config SDK配置
     * @param params 参数Map
     * @return 签名字符串
     * @throws YocalException 签名异常
     */
    public static String sign(YocalV2Config config, Map<String, String> params) throws YocalException {
        try {
            // 复制YocylApiDemo2中的sign方法逻辑
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
            signature.initSign(getPrivateKey(config.getPrivateKey()));
            signature.update(signContent.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new YocalException("签名生成失败", e);
        }
    }

    /**
     * 验签
     * @param config SDK配置
     * @param params 参数Map
     * @param sign 签名
     * @return 验签结果
     * @throws YocalException 验签异常
     */
    public static boolean verify(YocalV2Config config, Map<String, String> params, String sign) throws YocalException {
        try {
            // 检查是否配置了公钥
            if (config.getPublicKey() == null || config.getPublicKey().isEmpty()) {
                return false;
            }

            // 复制YocylApiDemo2中的verify方法逻辑
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
            signature.initVerify(getPublicKey(config.getPublicKey()));
            signature.update(signContent.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            throw new YocalException("验签失败", e);
        }
    }

    /**
     * 获取RSA私钥对象
     * 抽取自YocylApiDemo2中的getPrivateKey方法
     */
    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    /**
     * 获取RSA公钥对象
     * 抽取自YocylApiDemo2中的getPublicKey方法
     */
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}
