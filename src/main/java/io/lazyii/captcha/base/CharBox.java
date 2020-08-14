package io.lazyii.captcha.base;

import java.awt.*;

/**
 * Created by admin on 2020/8/13 11:21:13.
 */
public class CharBox {
    //g2d绘制字符时的基点
    Coordinate drawPoint;
    FontMetrics fm;
    
    private float boxWidth;
    private float boxHeight;
    private float boxDescent;
    
    public CharBox() {}
    
    public CharBox(Coordinate drawPoint, FontMetrics fm) {
        this.drawPoint = drawPoint;
        this.setFm(fm);
    }
    
    public Coordinate getDrawPoint() {
        return drawPoint;
    }
    
    public void setDrawPoint(Coordinate drawPoint) {
        this.drawPoint = drawPoint;
    }
    
    public FontMetrics getFm() {
        return fm;
    }
    
    public void setFm(FontMetrics fm) {
        this.fm = fm;
        this.boxWidth = fm.getMaxAdvance();
        this.boxHeight = fm.getHeight();
        this.boxDescent = fm.getMaxDescent();
    }
    
    /**
     * 获得字体的中心点，进行旋转
     */
    public Coordinate getCenter() {
        Coordinate bottomLeft = getBottomLeft();
        return new Coordinate(bottomLeft.getX() + boxWidth / 2.0f, bottomLeft.getY() - boxHeight / 2.0f);
    }
    
    /**
     * 通过中心点确定charBox
     *
     * @return
     */
    public static CharBox fromCenter(Coordinate center, FontMetrics fm) {
        float originX = center.getX() - fm.getMaxAdvance() / 2.0f;
        float originY = center.getY() + fm.getHeight() / 2.0f - fm.getMaxDescent();
        Coordinate drawPoint = new Coordinate(originX, originY);
        return new CharBox(drawPoint, fm);
    }
    
    /**
     * <p>
     * 单字符串， 根据drawString时的坐标获取 包裹字符的box的左下角点坐标。
     * 因为draw时，y轴 其实时根据font的BaseLine来进行对其的。所以会有一个descent的差别。x轴没有偏差。
     * <p>
     * 向下移动box，获得真实的字符串box。
     */
    public Coordinate getBottomLeft() {
        return new Coordinate(drawPoint.getX(), drawPoint.getY() + boxDescent);
    }
    
}
