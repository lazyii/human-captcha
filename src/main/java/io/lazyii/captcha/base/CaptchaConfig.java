package io.lazyii.captcha.base;

import io.lazyii.captcha.CircleTextCaptcha;

/**
 * Created by admin on 2020/8/11 16:14:31.
 */
public class CaptchaConfig {
    
    public static CaptchaConfig DEFAULT = new CaptchaConfig(CircleTextCaptcha.class.getName(), Bounds.defaultBounds(), 5, 150);
    
    
    private String serviceName;
    private Bounds bounds;
    private float size;
    private int num;
    private int tryTimes;
    
    public CaptchaConfig() {}
    public CaptchaConfig(String serviceName, Bounds bounds, int num, int tryTimes) {
        this.serviceName = serviceName;
        this.bounds = bounds;
        this.num = num;
        this.tryTimes = tryTimes;
    }
    
    public static CaptchaConfig getDEFAULT() {
        return DEFAULT;
    }
    
    public static void setDEFAULT(CaptchaConfig DEFAULT) {
        CaptchaConfig.DEFAULT = DEFAULT;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public Bounds getBounds() {
        return bounds;
    }
    
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
    
    public float getSize() {
        return size;
    }
    
    public void setSize(float size) {
        this.size = size;
    }
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public int getTryTimes() {
        return tryTimes;
    }
    
    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }
}
