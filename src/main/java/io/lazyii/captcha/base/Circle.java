package io.lazyii.captcha.base;

public class Circle extends Coordinate{
    
    //半径:radius, 直径:diameter = 2*radius
    private float r;
    
    public Circle() {}
    
    public Circle(float x, float y, float r) {
        super(x, y);
        this.r = r;
    }
    
    public float getR() {
        return r;
    }
    
    public Circle setR(float r) {
        this.r = r;
        return this;
    }
    
    @Override
    public String toString() {
        return "Circle{" + "x=" + super.getX() + ", y=" + super.getY() + ", r=" + r + '}';
    }
}
