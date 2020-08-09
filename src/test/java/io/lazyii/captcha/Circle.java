package io.lazyii.captcha;

public class Circle {
    private float x;
    private float y;
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
}
