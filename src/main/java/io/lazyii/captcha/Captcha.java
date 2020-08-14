package io.lazyii.captcha;

import io.lazyii.captcha.base.CaptchaConfig;
import io.lazyii.captcha.base.Coordinate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public interface Captcha {
    
    List<? extends Coordinate> randomCoordinate(CaptchaConfig config);
    
    BufferedImage draw(char[] chars, List<Coordinate> coordinates, CaptchaConfig config, BufferedImage bufferedImage);
    
    int valid(String msg, String param);
    
    default Graphics2D getGraphics2D(BufferedImage bufferedImage) {
        //创建绘图工具对象
        Graphics2D g2d = bufferedImage.createGraphics();
        //消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2d;
    }
    
    
}
