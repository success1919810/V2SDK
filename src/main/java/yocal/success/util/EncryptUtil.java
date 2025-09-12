package yocal.success.util;

import yocal.success.config.YocalV2Config;
import yocal.success.exception.YocalException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 加密工具类
 * 抽取自YocylApiDemo2中的加密方法
 */
public class EncryptUtil {

    /**
     * AES CBC 加密
     * @param config SDK配置
     * @param data 待加密数据
     * @return 加密后数据
     * @throws YocalException 加密异常
     */
    public static String encryptAES(YocalV2Config config, String data) throws YocalException {
        try {
            // 复制YocylApiDemo2中的encryptAES方法逻辑
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 这里平台给的是Base64编码的AES Key，这里需要解码
            byte[] keyBytes = Base64.getDecoder().decode(config.getAesKey());
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec iv = new IvParameterSpec(initIv());
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new YocalException("AES加密失败", e);
        }
    }

    /**
     * AES CBC 解密
     * @param config SDK配置
     * @param base64Data 待解密数据
     * @return 解密后数据
     * @throws YocalException 解密异常
     */
    public static String decryptAES(YocalV2Config config, String base64Data) throws YocalException {
        try {
            // 复制YocylApiDemo2中的decryptAES方法逻辑
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 这里平台给的是Base64编码的AES Key，这里需要解码
            byte[] keyBytes = Base64.getDecoder().decode(config.getAesKey());
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec iv = new IvParameterSpec(initIv());
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(base64Data));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new YocalException("AES解密失败", e);
        }
    }

    /**
     * 初始化IV向量
     */
    private static byte[] initIv() {
        int blockSize = 16;
        byte[] iv = new byte[blockSize];
        for(int i = 0; i < blockSize; ++i) {
            iv[i] = 0;
        }
        return iv;
    }
}
