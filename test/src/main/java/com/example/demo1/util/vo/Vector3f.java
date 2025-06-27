package com.example.demo1.util.vo;


/**
 * 3dF 向量
 *
 * @author HSimon
 */
public class Vector3f implements Cloneable {

    private float x;
    private float y;
    private float z;

    public Vector3f() {
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float[] points) {
        this.x = points[0];
        this.y = points[1];
        this.z = points[2];
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

    public Vector3f copy() {
        return new Vector3f(this.x, this.y, this.z);
    }

    public void setVector(Vector3f v) {
        setVector(v.getX(), v.getY(), v.getZ());
    }

    public void setVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector2f findV2() {
        return new Vector2f(this.x, this.z);
    }

    /**
     * 判断两坐标点是否相等
     */
    public boolean equals(Vector3f p) {
        return p != null && equals(p.x, p.y, p.z);
    }

    /**
     *判断量坐标是否相等,由于精度问题允许0.01f的误差
     */
    public boolean equals(float x, float y, float z) {
        return Math.abs(this.x - x) <= 0.01f && Math.abs(this.z - z) <= 0.01f;
    }

    /**
     * 计算2点之间距离
     */
    public double distance(Vector3f p) {
        float x = this.x - p.x;
        float z = this.z - p.z;
        return Math.sqrt(x * x + z * z);
    }

    /**
     * 是否零点坐标
     */
    public boolean isZero() {
        return equals(0.0f, 0.0f, 0.0f);
    }

    @Override
    public String toString() {
        return "Vector3d [x = " + getX() + ", y = " + getY() + ", z = " + getZ() + "]";
    }

}
