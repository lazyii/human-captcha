package io.lazyii.captcha;

import io.lazyii.captcha.base.*;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 2020/8/11 16:05:13.
 */
public class HumanCaptche {
    
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
    
    public Tuple3<List<Coordinate>, BufferedImage, char[]> createImg(BufferedImage image) {
        Captcha captcha = captchaMap.get(config.getServiceName());
        List<Coordinate> coordinates = (List<Coordinate>) captcha.randomCoordinate(config);
        char[] chars = CJKUtil.getChar(coordinates.size());
        BufferedImage bufferedImage = captcha.draw(chars, coordinates, config, image);
        return new Tuple3<>(coordinates, bufferedImage, chars);
    }
    
    public Tuple3<BufferedImage, String, String> createImgWithMsg(BufferedImage image) {
        Captcha captcha = captchaMap.get(config.getServiceName());
        List<Coordinate> coordinates = (List<Coordinate>) captcha.randomCoordinate(config);
        char[] chars = CJKUtil.getChar(coordinates.size());
        BufferedImage bufferedImage = captcha.draw(chars, coordinates, config, image);
        
        //uuid|char1|char2|x1|y1|x2|y2|timestamp(System.currentTimeMillis())
        float x0 = coordinates.get(0).getX();
        float y0 = coordinates.get(0).getY();
        float x1 = coordinates.get(1).getX();
        float y1 = coordinates.get(1).getY();
        long timestamp = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        String msg = String.format("%s|%s|%s|%s|%s|%s|%s|%s", uuid, chars[0], chars[1], x0, y0, x1, y1, timestamp);
        
        String chosen = String.valueOf(new char[]{chars[0], chars[1]});
    
        return new Tuple3<>(bufferedImage, HexUtil.hexEncode(msg), chosen);
    }
    
}
