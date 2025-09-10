package yocal.success.exception;

/**
 * Yocal API异常类
 */
public class YocalApiException extends YocalException {
    private String errorCode;
    private String errorMsg;
    
    public YocalApiException(String message) {
        super(message);
    }
    
    public YocalApiException(String errorCode, String errorMsg) {
        super(errorCode + ": " + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public YocalApiException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }
}
