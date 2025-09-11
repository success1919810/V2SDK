package yocal.success.client;

import yocal.success.YocalV2Config;
import yocal.success.request.BaseRequest;
import yocal.success.util.HttpUtil;
import yocal.success.util.EncryptUtil;
import yocal.success.exception.YocalException;
import yocal.success.exception.YocalApiException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

/**
 * Yocal V2 SDK客户端
 * @author success
 */
public class YocalV2Client {
    private final YocalV2Config config;
    private String gateway;

    public YocalV2Client(YocalV2Config config) {
        this.config = config;
        // 使用配置中的网关URL作为默认值
        this.gateway = config.getGatewayUrl();
    }

    /**
     * 设置网关地址
     */
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    /**
     * 执行请求
     * @param command 命令
     * @param bizContent 业务内容（JSON格式）
     * @return 响应结果
     * @throws YocalException SDK异常
     */
    public  String execute(String command, String bizContent) throws YocalException {
        // 创建请求对象
        BaseRequest request = new BaseRequest(config, command);
        request.setBizContent(bizContent);

        // 构建请求参数
        Map<String, String> params = request.buildRequestParams(config);

        // 生成签名
        String sign = request.generateSign(config, params);

        // 发送HTTP请求
        String response = HttpUtil.post(gateway, params, sign);

        // 如果启用了加密，则对响应进行解密
        if (config.isEncryptEnabled()) {
            // 解析响应中的加密内容
            String decryptedResponse = decryptResponse(response, command);
            return decryptedResponse;
        }

        return response;
    }

    /**
     * 解密响应内容
     * @param response 原始响应
     * @param command 命令
     * @return 解密后的响应
     * @throws YocalException 解密异常
     */
    private String decryptResponse(String response, String command) throws YocalException {
        try {
            // 根据命令构造响应字段名，格式为: command + "_response"
            // 将命令中的点号替换为下划线
            String responseFieldName = command.replace(".", "_") + "_response";

            // 使用正则表达式提取加密内容
            Pattern pattern = Pattern.compile("\"" + responseFieldName + "\":\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(response);

            if (matcher.find()) {
                String encryptedContent = matcher.group(1);
                // 对加密内容进行解密
                String decryptedContent = EncryptUtil.decryptAES(config, encryptedContent);

                // 构造解密后的响应JSON
                return "{\"" + responseFieldName + "\":" + decryptedContent + "}";
            } else {
                throw new YocalException("无法从响应中提取加密内容，字段名: " + responseFieldName);
            }
        } catch (Exception e) {
            throw new YocalException("响应解密失败", e);
        }
    }
}
