package com.example.demo1.util.vo;

/**
 * 向量2d
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/8/17.
 */
public class Vector2f {

    public float x;

    public float y;


    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f add(Vector2f vector2f) {
        return new Vector2f(x+ vector2f.x, y+ vector2f.y);
    }

    public Vector2f multiply(float v) {
        return new Vector2f(x * v, y * v);
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
