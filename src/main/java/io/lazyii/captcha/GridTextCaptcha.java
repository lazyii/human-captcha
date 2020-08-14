package io.lazyii.captcha;

import io.lazyii.captcha.base.CaptchaConfig;
import io.lazyii.captcha.base.Coordinate;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author wyd
 * @time 2020/8/8 10:27 下午
 * @description
 */
public class GridTextCaptcha implements Captcha {
    
    @Override
    public List<Coordinate> randomCoordinate(CaptchaConfig config) {
        return null;
    }
    
    @Override
    public BufferedImage draw(char[] chars, List<Coordinate> coordinates, CaptchaConfig config, BufferedImage bufferedImage) {
        return null;
    }
    
    @Override
    public int valid(String msg, String param) {
        return 0;
    }
}
