package io.lazyii.captcha;

import io.lazyii.captcha.base.CaptchaConfig;
import io.lazyii.captcha.base.CaptchaException;
import io.lazyii.captcha.base.Coordinate;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by admin on 2020/8/11 16:05:13.
 */
public class HumanCaptche {
    
    private static Map<String, Captcha> captchaMap;
    static {
        ServiceLoader<Captcha> captchaServices = ServiceLoader.load(Captcha.class);
        Iterator<Captcha> iterator = captchaServices.iterator();
        while (iterator.hasNext()) {
            Captcha captcha = iterator.next();
            String className = captcha.getClass().getName();
            System.out.println("heihei  " + className);
            if (captchaMap.containsKey(className)) {
                throw new CaptchaException("duplicated " + className + " when load Captcha service");
            } else {
                captchaMap.put(className, captcha);
            }
        }
    }
    
    private CaptchaConfig config;
    
    
    private HumanCaptche() {}
    private HumanCaptche(CaptchaConfig config) {
        this.config = config;
    }
    
    public static HumanCaptche instance() {
        return new HumanCaptche(CaptchaConfig.DEFAULT);
    }
    
    public static HumanCaptche instance(CaptchaConfig config) {
        return new HumanCaptche(config);
    }
    
    public BufferedImage createImg() {
        //todo 待实现
        List<Coordinate> coordinates = (List<Coordinate>) captchaMap.get(config.getServiceName()).randomCoordinate(1);
        return null;
    }
    
}
