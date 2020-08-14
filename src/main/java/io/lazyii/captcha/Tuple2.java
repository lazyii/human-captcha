package io.lazyii.captcha;

public class Tuple2<T,U> {
    T _1;
    U _2;
    
    
    public Tuple2() {}
    public Tuple2(T _1, U _2) {
        this._1 = _1;
        this._2 = _2;
    }
    
    @Override
    public String toString() {
        return "Tuple{" + "_1=" + _1 + ", _2=" + _2 + '}';
    }
}
