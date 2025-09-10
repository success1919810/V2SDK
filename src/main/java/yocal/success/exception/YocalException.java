package yocal.success.exception;

/**
 * Yocal SDK基础异常类
 */
public class YocalException extends Exception {
    
    public YocalException(String message) {
        super(message);
    }
    
    public YocalException(String message, Throwable cause) {
        super(message, cause);
    }
}