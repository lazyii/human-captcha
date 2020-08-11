package io.lazyii.captcha.base;

/**
 * Created by admin on 2020/8/11 16:14:31.
 */
public class CaptchaConfig {
    
    public static CaptchaConfig DEFAULT = new CaptchaConfig();
    
    private String serviceName;
    
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
