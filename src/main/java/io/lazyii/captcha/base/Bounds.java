package io.lazyii.captcha.base;

/**
 * Created by admin on 2020/8/11 15:34:49.
 */
public class Bounds extends Coordinate {
    float width;
    float height;
    
    public Bounds() {}
    
    public Bounds(float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
    
    public float getWidth() {
        return width;
    }
    
    public void setWidth(float width) {
        this.width = width;
    }
    
    public float getHeight() {
        return height;
    }
    
    public void setHeight(float height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return "Bounds{" + "x=" + getX() + ", y=" + getY() + ", width=" + width + ", height=" + height + '}';
    }
    
    
    public static Bounds defaultBounds() {
        return new Bounds(0, 0, 400f, 200f);
    }
    
    public static Bounds createBounds(float width, float height) {
        return new Bounds(0, 0, width, height);
    }
    
    public static Bounds createBounds(float topLeftX, float topLeftY, float width, float height) {
        return new Bounds(topLeftX, topLeftY, width, height);
    }
    
}
