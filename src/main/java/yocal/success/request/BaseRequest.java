package yocal.success.request;

import yocal.success.response.BaseResponse;

/**
 * @author success
 * @date 2025/9/10
 * @version v2.0.0
 * @description: 基础请求类，只保留业务内容和命令信息
 */
public abstract class BaseRequest<T extends BaseResponse> {


    protected String bizContent;

    /**
     * 构建业务内容 JSON
     * 子类可以覆盖这个方法，将业务字段序列化为 JSON
     */
    public void buildBizContent() {
        // 默认直接使用 bizContent，子类可覆盖实现序列化逻辑
    }

    /**
     * 设置业务内容
     */
    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    /**
     * 获取业务内容
     */
    public String getBizContent() {
        return bizContent;
    }

    /**
     * 每个请求必须指定对应的 Response 类型
     */
    public abstract Class<T> getResponseClass();

    /**
     * 每个请求必须指定 command
     */
    public abstract String getCommand();
}
