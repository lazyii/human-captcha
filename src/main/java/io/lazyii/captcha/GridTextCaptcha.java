package io.lazyii.captcha;

import io.lazyii.captcha.base.Coordinate;

import java.util.List;

/**
 * @author wyd
 * @time 2020/8/8 10:27 下午
 * @description
 */
public class GridTextCaptcha implements Captcha {
    
    @Override
    public List<? extends Coordinate> randomCoordinate(int num) {
        return null;
    }
    
    @Override
    public List<? extends Coordinate> randomCoordinate(int num, int tryTimes) {
        return null;
    }
}
