package io.lazyii.captcha;

import io.lazyii.captcha.base.CaptchaException;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 2020/8/18 15:07:38.
 */
public class CaptchaFactory {
    private static Map<String, Captcha> captchaMap = new ConcurrentHashMap<>();
    
    static {
        ServiceLoader<Captcha> captchaServices = ServiceLoader.load(Captcha.class);
        Iterator<Captcha> iterator = captchaServices.iterator();
        while (iterator.hasNext()) {
            Captcha captcha = iterator.next();
            String className = captcha.getClass().getName();
            if (captchaMap.containsKey(className)) {
                throw new CaptchaException("duplicated " + className + " when load Captcha service");
            } else {
                captchaMap.put(className, captcha);
            }
        }
    }
    
    public static Captcha getCaptcha(String className) {
        return captchaMap.get(className);
    }
}
