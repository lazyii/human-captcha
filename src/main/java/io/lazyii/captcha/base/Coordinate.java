package io.lazyii.captcha.base;

/**
 * Created by admin on 2020/8/11 15:48:37.
 */
public class Coordinate {
    //图形中心 x轴坐标
    private float x;
    //图形中心 y轴坐标
    private float y;
    
    public Coordinate() {}
    
    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }
}
