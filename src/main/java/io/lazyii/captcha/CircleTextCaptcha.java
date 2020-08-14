package io.lazyii.captcha;

import io.lazyii.captcha.base.*;

import java.awt.*;
import java.awt.geom.Arc2D;
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
    public BufferedImage draw(char[] chars, List<Coordinate> coordinates, CaptchaConfig config, BufferedImage bufferedImage) {
        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate center = coordinates.get(i);
            if (config.getDebug()) {
                drawCircle(bufferedImage, new Circle(center.getX(), center.getY(), config.getSize()));
                drawCenter(bufferedImage, new Circle(center.getX(), center.getY(), config.getSize()));
            }
            drawChar(chars[i], bufferedImage, config.getFont(), center);
        }
        return bufferedImage;
    }
    
    public void drawCircle(BufferedImage bufferedImage, Circle circle) {
        Graphics2D g2d = getGraphics2D(bufferedImage);
        
        g2d.setColor(Color.RED);
        //圆形
        float topLeftX = circle.getX() - circle.getR();
        float topLeftY = circle.getY() - circle.getR();
        float diameter = 2.0f * circle.getR();
        g2d.fill(new Arc2D.Float(topLeftX, topLeftY, diameter, diameter, 0, 360, Arc2D.PIE));
        g2d.dispose();
    }
    
    public void drawCenter(BufferedImage bufferedImage, Circle circle) {
    
        Graphics2D g2d = getGraphics2D(bufferedImage);
        
        g2d.setColor(Color.PINK);
        //圆形
        float tmp = circle.getR() / 2.0f;
        float topLeftX = circle.getX() - tmp;
        float topLeftY = circle.getY() - tmp;
        g2d.fill(new Arc2D.Float(topLeftX, topLeftY, circle.getR(), circle.getR(), 0, 360, Arc2D.PIE));
        g2d.dispose();
    }
    
    public void drawChar(char c, BufferedImage bufferedImage, Font font, Coordinate center) {
        Graphics2D g2d = getGraphics2D(bufferedImage);
        
        g2d.setFont(font);
        
        //设置画笔
        //设置渐变
        GradientPaint paint = new GradientPaint(20, 20, Color.decode("#3d3b4f"), 40, 40, Color.decode("#56004f"), true);
        g2d.setPaint(paint);
    
        CharBox charBox = CharBox.fromCenter(center, g2d.getFontMetrics());
        
        int angle = ThreadLocalRandom.current().nextInt(-90, 90);
        g2d.rotate(Math.toRadians(angle), center.getX(), center.getY());
        g2d.drawString(String.valueOf(c), charBox.getDrawPoint().getX(), charBox.getDrawPoint().getY());
        g2d.dispose();
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
                    .map(x -> new Tuple2<Float, Float>(
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
    
    /**
     * uuid|char1|char2|x1|y1|x2|y2|timestamp(System.currentTimeMillis())
     *  
     * @param param
     * @return
     */
    @Override
    public int valid(String msg, String param) {
        String[] tmp = msg.split("\\|");
        String[] tmp1 = param.split("\\|");
        if (tmp.length != 8 || tmp1.length != 4) {
            return -1;
        } else {
            String value3 = tmp[3];
            String value4 = tmp[4];
            String value5 = tmp[5];
            String value6 = tmp[6];
            long timestamp = Long.valueOf(tmp[7]);
            
            String p0 = tmp1[0];
            String p1 = tmp1[1];
            String p2 = tmp1[2];
            String p3 = tmp1[3];
            int s0 = Float.valueOf(value3) > Float.valueOf(p0) ? 1 : 0;
            int s1 = Float.valueOf(value4) > Float.valueOf(p1) ? 1 : 0;
            int s2 = Float.valueOf(value5) > Float.valueOf(p2) ? 1 : 0;
            int s3 = Float.valueOf(value6) > Float.valueOf(p3) ? 1 : 0;
            int s4 = timestamp - System.currentTimeMillis() > 6000 ? 1 : 0;
            return s0 + s1 + s2 + s3 + s4;
        }
    }
    
}
