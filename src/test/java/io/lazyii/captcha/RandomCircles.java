package io.lazyii.captcha;

import io.lazyii.captcha.base.Bounds;
import io.lazyii.captcha.base.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;


/**
 * 所有坐标，右移n：x+n 下移n: y+n
 */
public class RandomCircles {
    
    private Bounds DEFAULT_BOUNDS = new Bounds(0, 0, 400f, 200f);
    private int    DEFAULT_TRY    = 150;
    
    float r = 30;
    //画布边界
    Bounds       bounds  = DEFAULT_BOUNDS;
    List<Circle> circles = new ArrayList<>();
    
    
    /**
     * 判断已经存在的圆是否与 此圆相交
     * @return false:未相交 true相交(边界重合也算相交)
     */
    public boolean intersect(Circle cr) {
        if (circles.isEmpty()) {
            return false;
        } else {
            //已存在圆的圆心坐标与此圆 x轴，y轴 差值都大于2*r的，肯定不会相交。
            return circles
                    .stream()
                    .map(x -> new Tuple2<Float, Float>(Math.abs(x.getX() - cr.getX()), Math.abs(x.getY() - cr.getY())))
                    .filter(x -> x._1 <= 2 * r || x._2 <= 2 * r)
                    .anyMatch(x -> 4 * r * r >= x._1 * x._1 + x._2 * x._2);
        }
    }
    
    public List<Circle> randomCrs(int num) {
        return randomCrs(num, DEFAULT_TRY);
    }
    
    public List<Circle> randomCrs(int num, int tryTimes) {
        if (bounds == null) {
            throw new RuntimeException("bounds is null, please init bounds first");
        } else if (num > tryTimes) {
            throw new RuntimeException("error! tryTimes must larger than num");
        } else {
            IntStream.rangeClosed(0, tryTimes)
                    .mapToObj(x -> randomCr())
                    .filter(x -> !intersect(x))
                    .peek(circles::add)
                    .limit(num)
                    .count();
        }
        return circles;
    }
    
    public Circle randomCr() {
        float x1 = bounds.getX() + bounds.getWidth() - r;
        float y1 = bounds.getY() + bounds.getHeight() - r;
        float x2 = (float) ThreadLocalRandom.current().doubles(1, r, x1).findAny().getAsDouble();
        float y2 = (float) ThreadLocalRandom.current().doubles(1, r, y1).findAny().getAsDouble();
        return new Circle(x2, y2, r);
    }
    
    
    public List<Circle> randomCrs2(int num, int tryTimes) {
        if (bounds == null) {
            throw new RuntimeException("bounds is null, please init bounds first");
        } else if (num > tryTimes) {
            throw new RuntimeException("error! tryTimes must larger than num");
        } else {
            float x1 = bounds.getX() + bounds.getWidth() - r;
            float y1 = bounds.getY() + bounds.getHeight() - r;
            IntStream.rangeClosed(0, tryTimes).mapToObj(x -> {
                float x2 = (float) ThreadLocalRandom.current().doubles(1, r, x1).findAny().getAsDouble();
                float y2 = (float) ThreadLocalRandom.current().doubles(1, r, y1).findAny().getAsDouble();
                return new Tuple2<Float, Float>(x2, y2);
            }).filter(random -> {
                if (circles.isEmpty()) {
                    return true;
                } else {
                    return circles
                            .stream()
                            .map(x -> new Tuple2<Float, Float>(Math.abs(random._1 - x.getX()), Math.abs(random._2 - x.getY())))
                            .allMatch(x -> x._1 > r + r || x._2 >  r + r);
                }
            }).peek(
                    x -> circles.add(new Circle(x._1, x._2, r))
            ).limit(num).count();
            return circles;
        }
    }
   
}
