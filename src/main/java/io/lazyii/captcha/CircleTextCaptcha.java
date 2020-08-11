package io.lazyii.captcha;

import io.lazyii.captcha.base.Bounds;
import io.lazyii.captcha.base.CaptchaConfig;
import io.lazyii.captcha.base.Circle;
import io.lazyii.captcha.base.Coordinate;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author wyd
 * @time 2020/8/8 10:27 下午
 * @description
 */
public class CircleTextCaptcha implements Captcha {
    
    @Override
    public List<Circle> randomCoordinate(CaptchaConfig config) {
        return randomCrs(config);
    }
    
    @Override
    public BufferedImage draw(List<Coordinate> coordinates) {
        return null;
    }
    
    @Override
    public int valid(String param) {
        return 0;
    }
    
    
    /**
     * 判断已经存在的圆是否与 此圆相交
     *
     * @return false:未相交 true相交(边界重合也算相交)
     */
    public boolean intersect(Circle newCircle, List<Circle> circles) {
        if (circles.isEmpty()) {
            return false;
        } else {
            //已存在圆的圆心坐标与此圆 x轴，y轴 差值都大于2*r的，肯定不会相交。
            float r = newCircle.getR();
            return circles
                    .stream()
                    .map(x -> new Tuple<Float, Float>(
                            Math.abs(x.getX() - newCircle.getX()),
                            Math.abs(x.getY() - newCircle.getY())))
                    .filter(x -> x._1 <= 2 * r || x._2 <= 2 * r)
                    .anyMatch(x -> 4 * r * r >= x._1 * x._1 + x._2 * x._2);
        }
    }
    
    
    public List<Circle> randomCrs(CaptchaConfig config) {
        List<Circle> circles = new ArrayList<>();
        if (config.getBounds() == null) {
            throw new RuntimeException("bounds is null, please init bounds first");
        } else if (config.getNum() > config.getTryTimes()) {
            throw new RuntimeException("error! tryTimes must larger than num");
        } else {
            IntStream
                    .rangeClosed(0, config.getTryTimes())
                    .mapToObj(x -> randomCr(config.getBounds(), config.getSize()))
                    .filter(x -> !intersect(x, circles))
                    .peek(circles::add)
                    .limit(config.getNum())
                    .count();
        }
        return circles;
    }
    
    public Circle randomCr(Bounds bounds, float radius) {
        float x1 = bounds.getX() + bounds.getWidth() - radius;
        float y1 = bounds.getY() + bounds.getHeight() - radius;
        float x2 = (float) ThreadLocalRandom.current().doubles(1, radius, x1).findAny().getAsDouble();
        float y2 = (float) ThreadLocalRandom.current().doubles(1, radius, y1).findAny().getAsDouble();
        return new Circle(x2, y2, radius);
    }
    
}
