package io.lazyii.captcha;

import io.lazyii.captcha.base.CaptchaConfig;
import io.lazyii.captcha.base.Coordinate;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Captcha {
    
    List<? extends Coordinate> randomCoordinate(CaptchaConfig config);
    
    BufferedImage draw(List<Coordinate> coordinates);
    
    int valid(String param);
    
}
