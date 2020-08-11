package io.lazyii.captcha;

import io.lazyii.captcha.base.Coordinate;

import java.util.List;

public interface Captcha {
    
    List<? extends Coordinate> randomCoordinate(int num);
    
    List<? extends Coordinate> randomCoordinate(int num, int tryTimes);
    
    //void draw();
    
    //void valid(String param)
    
}
