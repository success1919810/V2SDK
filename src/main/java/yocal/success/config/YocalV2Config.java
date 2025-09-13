package yocal.success.config;

/**
 * @Author: success
 * @Date: 2025/9/10 13:30
 * @Version: v2.0.0
 * @Description: V2模式下的SDK配置类
 **/
public class YocalV2Config {
    /**
     * 必填字段
     */
    private final String appId;
    private final String privateKey;
    private final String publicKey;
    private final String aesKey;
    private final String version;
    private final String encryptType;
    private final String gateway;
    /**
    *可选字段
     */
    private final String charset;
    private final String signType;
    private final String format;

    private YocalV2Config(Builder builder) {
        this.appId = builder.appId;
        this.privateKey = builder.privateKey;
        this.publicKey = builder.publicKey;
        this.aesKey = builder.aesKey;
        this.encryptType = builder.encryptType;
        this.gateway = builder.gateway;
        this.charset = builder.charset;
        this.signType = builder.signType;
        this.format = builder.format;
        this.version = builder.version;
    }

    public String getAppId() { return appId; }
    public String getPrivateKey() { return privateKey; }
    public String getAesKey() { return aesKey; }
    public String getVersion() { return version; }
    public String getEncryptType() { return encryptType; }
    public String getPublicKey() { return publicKey; }
    public String getGateway() { return gateway; }
    public String getCharset() { return charset; }
    public String getSignType() { return signType; }
    public String getFormat() { return format; }

    /**
     * 判断是否需要AES加密
     * @return 如果encryptType不为空且需要加密则返回true，否则返回false
     */
    public boolean isEncryptEnabled() {
        return encryptType != null && !encryptType.isEmpty() && "AES".equalsIgnoreCase(encryptType.trim());
    }



    public static class Builder {
        private final String appId;
        private final String privateKey;
        private final String aesKey;
        private final String version;
        private final String encryptType;
        private final String gateway;
        private final String publicKey;
        private String charset = "UTF-8";
        private String signType = "RSA2";
        private String format = "json";

        public Builder(String appId, String privateKey, String publicKey, String gateway,String aesKey,String encryptType,String version) {
            this.appId = appId;
            this.privateKey = privateKey;
            this.publicKey = publicKey;
            this.gateway = gateway;
            this.aesKey = aesKey;
            this.version = version;
            this.encryptType = encryptType;
        }

        public Builder charset(String charset) {
            this.charset = charset;
            return this;
        }

        public Builder signType(String signType) {
            this.signType = signType;
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public YocalV2Config build() {
            return new YocalV2Config(this);
        }
    }
}
