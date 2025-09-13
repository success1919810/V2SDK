package yocal.success.client;

import yocal.success.config.YocalV2Config;
import yocal.success.constant.YocalConstants;
import yocal.success.request.BaseRequest;
import yocal.success.response.BaseResponse;
import yocal.success.util.HttpUtil;
import yocal.success.util.EncryptUtil;
import yocal.success.exception.YocalException;
import yocal.success.util.SignatureUtil;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Yocal V2 SDK客户端
 * @author success
 */
public class YocalV2Client {
    private final YocalV2Config config;

    public YocalV2Client(YocalV2Config config) {
        this.config = config;
    }


    /**
     * 执行请求
     * @param request 根据method去请求的业务地址
     * @return 响应结果
     * @throws YocalException SDK异常
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request) throws YocalException {
        try {

            request.buildBizContent();
            String bizContent = request.getBizContent();


            if (config.isEncryptEnabled()) {
                bizContent = EncryptUtil.encryptAES(config, bizContent);
            }


            Map<String, String> params = buildRequestParams(request, bizContent);


            String sign = SignatureUtil.sign(config, params);


            String responseJson = HttpUtil.post(config, params, sign);



            if (config.isEncryptEnabled()) {
                responseJson = decryptResponse(responseJson, request.getCommand());
            }


            BaseResponse baseResp = new BaseResponse();
            baseResp.setRawJson(responseJson);
            return (T) baseResp;

        } catch (Exception e) {
            throw new YocalException("请求执行失败", e);
        }
    }

    /**
     * 构建请求参数（公共参数 + bizContent）
     */
    private <T extends BaseResponse> Map<String, String> buildRequestParams(BaseRequest<T> request, String bizContent) {
        Map<String, String> params = new HashMap<>();
        params.put("appId", config.getAppId());
        params.put("command", request.getCommand());
        params.put("timestamp", getCurrentTimestamp());
        params.put("charset", config.getCharset());
        params.put("format", config.getFormat());
        params.put("signType", config.getSignType());
        params.put("encryptType", config.getEncryptType());
        params.put("version", config.getVersion());
        params.put("bizContent", bizContent);
        params.put("nonce", generateNonce());
        return params;
    }

    /**
     * 生成随机字符串
     */
    private String generateNonce() {
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    /**
     * 解密响应内容
     */
    private String decryptResponse(String response, String command) throws YocalException {
        try {
            String responseFieldName = command.replace(".", "_") + "_response";

            // 验证响应是否为空
            if (response == null || response.trim().isEmpty()) {
                throw new YocalException("响应内容为空");
            }

            // 打印调试信息（生产环境中应移除或使用proper日志）
            System.out.println("Expected field: " + responseFieldName);
            System.out.println("Response content: " + response);

            // 使用更健壮的正则表达式
            String regex = "\"" + Pattern.quote(responseFieldName) + "\"\\s*:\\s*\"([^\"]+)\"";
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(response);

            if (matcher.find()) {
                String encryptedContent = matcher.group(1);
                String decryptedContent = EncryptUtil.decryptAES(config, encryptedContent);
                return "{\"" + responseFieldName + "\":" + decryptedContent + "}";
            } else {
                // 如果没有找到加密字段，可能是非加密响应，直接返回原响应
                System.out.println("未找到加密字段 " + responseFieldName + "，返回原始响应");
                return response;
            }
        } catch (Exception e) {
            // 如果解密失败，返回原始响应
            System.out.println("响应解密失败，返回原始响应: " + e.getMessage());
            return response;
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
}
