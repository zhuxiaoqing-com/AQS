package com.example.demo1.util.vo;

/**
 * 四元数
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/8/15.
 */
public class Vector4f {

    private float x;
    private float y;
    private float z;
    private float w;

    public Vector4f() {
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(Vector3f v3, float w) {
        this.x = v3.getX();
        this.y = v3.getY();
        this.z = v3.getZ();
        this.w = w;
    }

    public void setVector(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void setVector(Vector3f vector3, float w) {
        this.x = vector3.getX();
        this.y = vector3.getY();
        this.z = vector3.getZ();
        this.w = w;
    }

    public void setVector(Vector4f vector4) {
        this.x = vector4.getX();
        this.y = vector4.getY();
        this.z = vector4.getZ();
        this.w = vector4.getW();
    }

    public Vector3f findV3() {
        return new Vector3f(this.x, this.y, this.z);
    }

    public Vector2f findV2() {
        return new Vector2f(this.x, this.z);
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public Vector4f copy() {
        return new Vector4f(this.x, this.y, this.z, this.w);
    }

}
