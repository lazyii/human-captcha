package io.lazyii.captcha.base;

/**
 * Created by admin on 2020/8/11 16:41:15.
 */
public class CaptchaException extends RuntimeException{
    
    public CaptchaException() {
        super();
    }
    
    public CaptchaException(String message) {
        super(message);
    }
    
    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CaptchaException(Throwable cause) {
        super(cause);
    }
    
    protected CaptchaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
