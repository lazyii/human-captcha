package io.lazyii.captcha;

public class Circle {
    //圆心，x轴坐标
    private float x;
    //圆心，y轴坐标
    private float y;
    //半径:radius, 直径:diameter = 2*radius
    private float r;
    
    public Circle() {}
    
    public Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getR() {
        return r;
    }
    
    public Circle setX(float x) {
        this.x = x;
        return this;
    }
    
    public Circle setY(float y) {
        this.y = y;
        return this;
    }
    
    public Circle setR(float r) {
        this.r = r;
        return this;
    }
    
    @Override
    public String toString() {
        return "{\"x\":" + x + ",\"y\":" + y + ",\"r\":" + r + "}";
    }
}
