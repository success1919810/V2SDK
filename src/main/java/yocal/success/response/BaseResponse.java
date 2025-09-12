package yocal.success.response;
/**
 * @author success
 * @date 2025/9/10
 * @version v2.0.0
 * @description: 基础返回类，如果有更加详细的异常码说明，将会更加方便维护
 */
public class BaseResponse {
    private String code;
    private String msg;
    private String rawJson;


    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }


    public String getRawJson() { return rawJson; }
    public void setRawJson(String rawJson) { this.rawJson = rawJson; }
    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", rawJson='" + rawJson + '\'' +
                '}';
    }
}
