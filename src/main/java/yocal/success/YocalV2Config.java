package yocal.success;

/**
 * @Author: success
 * @Date: 2025/9/10 13:30
 * @Version: v1.0.0
 * @Description: V2模式下的SDK配置类
 **/
public class YocalV2Config {
    private final String appId;
    private final String privateKey;
    private final String publicKey;
    private final String aesKey;
    private final String version;
    private final String command;
    private final String encryptType;
    private final String gatewayUrl;

    public YocalV2Config(String appId, String privateKey, String aesKey, String version,
                         String command, String encryptType, String publicKey, String gatewayUrl) {
        this.appId = appId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.aesKey = aesKey;
        this.version = version;
        this.command = command;
        this.encryptType = encryptType;
        this.gatewayUrl = gatewayUrl;
    }

    /**
     * 支持用户自定义请求域名
    */
    public YocalV2Config(String appId, String privateKey, String aesKey, String version,
                         String command, String encryptType) {
        this(appId, privateKey, aesKey, version, command, encryptType, null,
                "https://openapi.yocyl.com/api");
    }


    public String getAppId() { return appId; }
    public String getPrivateKey() { return privateKey; }
    public String getPublicKey() { return publicKey; }
    public String getAesKey() { return aesKey; }
    public String getVersion() { return version; }
    public String getCommand() { return command; }
    public String getEncryptType() { return encryptType; }
    public String getGatewayUrl() { return gatewayUrl; }

    /**
     * 判断是否需要AES加密
     * @return 如果encryptType不为空且需要加密则返回true，否则返回false
     */
    public boolean isEncryptEnabled() {
        return encryptType != null && !encryptType.isEmpty() && "AES".equalsIgnoreCase(encryptType.trim());
    }

    /**
     * 判断是否需要签名验证
     * @return 如果公钥存在则返回true，否则返回false
     */
    public boolean isSignVerificationEnabled() {
        return publicKey != null && !publicKey.isEmpty();
    }
}
