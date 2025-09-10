package yocal.success.client;

import yocal.success.YocalV2Config;
import yocal.success.request.BaseRequest;
import yocal.success.util.HttpUtil;
import yocal.success.exception.YocalException;

import java.util.Map;

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
    public String execute(String command, String bizContent) throws YocalException {
        // 创建请求对象
        BaseRequest request = new BaseRequest(config, command);
        request.setBizContent(bizContent);

        // 构建请求参数
        Map<String, String> params = request.buildRequestParams(config);

        // 生成签名
        String sign = request.generateSign(config, params);

        // 发送HTTP请求
        return HttpUtil.post(gateway, params, sign);
    }
}
