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
    public List<? extends Coordinate> randomCoordinate(CaptchaConfig config) {
        return null;
    }
    
    @Override
    public BufferedImage draw(List<Coordinate> coordinates) {
        return null;
    }
    
    @Override
    public int valid(String param) {
        return 0;
    }
}
