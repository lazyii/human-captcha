package io.lazyii.captcha;

public class Tuple3<T,U, R> {
    T _1;
    U _2;
    R _3;
    
    
    public Tuple3() {}
    public Tuple3(T _1, U _2, R _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }
    
    @Override
    public String toString() {
        return "Tuple3{" + "_1=" + _1 + ", _2=" + _2 + ", _3=" + _3 + '}';
    }
}
