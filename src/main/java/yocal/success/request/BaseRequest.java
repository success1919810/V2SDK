package yocal.success.request;

import yocal.success.YocalV2Config;
import yocal.success.constant.YocalConstants;
import yocal.success.util.SignatureUtil;
import yocal.success.util.EncryptUtil;
import yocal.success.exception.YocalException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: success
 * @date: 2025/9/10 17:59
 * @version: v1.0.0
 * @description: 基础请求类
 **/
public class BaseRequest {
    protected Map<String, String> commonParams = new LinkedHashMap<>();
    protected String bizContent;
    
    public BaseRequest(YocalV2Config config, String command) {
        commonParams.put("appId", config.getAppId());
        commonParams.put("command", command);
        commonParams.put("version", config.getVersion());
        commonParams.put("timestamp", getCurrentTimestamp());
        commonParams.put("charset", YocalConstants.DEFAULT_CHARSET);
        commonParams.put("format", YocalConstants.DEFAULT_FORMAT);
        commonParams.put("signType", YocalConstants.DEFAULT_SIGN_TYPE);
        commonParams.put("nonce", generateNonce());

        if (config.isEncryptEnabled()) {
            commonParams.put("encryptType", config.getEncryptType());
        }
    }
    
    /**
     * 获取当前时间戳
     */
    private String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YocalConstants.TIMESTAMP_FORMAT);
        return now.format(formatter);
    }
    
    /**
     * 生成随机字符串
     */
    private String generateNonce() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
    
    /**
     * 设置业务内容
     */
    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }
    
    /**
     * 获取公共参数
     */
    public Map<String, String> getCommonParams() {
        return new LinkedHashMap<>(commonParams);
    }
    
    /**
     * 获取业务内容
     */
    public String getBizContent() {
        return bizContent;
    }
    
    /**
     * 构建完整请求参数
     */
    public Map<String, String> buildRequestParams(YocalV2Config config) throws YocalException {
        Map<String, String> params = new LinkedHashMap<>(commonParams);
        
        String finalBizContent = bizContent;
        // 如果需要加密，对业务内容进行加密
        if (config.isEncryptEnabled()) {
            finalBizContent = EncryptUtil.encryptAES(config, bizContent);
        }
        
        params.put("bizContent", finalBizContent);
        return params;
    }
    
    /**
     * 生成签名
     */
    public String generateSign(YocalV2Config config, Map<String, String> params) throws YocalException {
        return SignatureUtil.sign(config, params);
    }
}
