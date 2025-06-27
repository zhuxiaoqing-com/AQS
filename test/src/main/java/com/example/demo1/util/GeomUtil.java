package com.example.demo1.util;


import com.example.demo1.util.vo.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * 几何计算
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/8/15.
 */
public class GeomUtil {

    private static int[][] POINT_ROUND_OFFSET = new int[20][];

    /**
     * 自己周围八方向的偏移量
     */
    public static final int[] EIGHT_DIR_OFFSET = new int[]{-1, -1, 0, -1, 1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0};

    public static int getPointId(int x, int y) {
        return (x << 16) | y;
    }

    /**
     * 获取某个区域对于中心区域坐标的偏移（正方形）
     *
     * @param range 最远距离中心点的格子数, 0：当前点，1：周围9个点，2：周围25个点
     * @return
     */
    public static final int[] getPointRoundOffset(int range) {

        if (range >= POINT_ROUND_OFFSET.length) { //数组超长，自动进行扩展
            int[][] offset = new int[range][];
            System.arraycopy(POINT_ROUND_OFFSET, 0, offset, 0, POINT_ROUND_OFFSET.length);
            POINT_ROUND_OFFSET = offset;
        }

        int[] ret = POINT_ROUND_OFFSET[range];
        if (ret != null) {
            return ret;
        }

        // 0 = 2 * (0 * 2 + 1) * (0 * 2 + 1) = 2; 0 = 1
        // 1 = 2 * (1 * 2 + 1) * (1 * 2 + 1) = 18; 1 = 9
        // 2 = 2 * (2 * 2 + 1) * (2 * 2 + 1) = 50 2 = 25
        // 3 = 2 * (3 * 2 + 1) * (3 * 2 + 1) = 98 3 = 49
        ret = new int[2 * (range * 2 + 1) * (range * 2 + 1)];
        int i = 0;
        // 1 = 0  到  0
        // 2 = -1 到  1
        // 3 = -2 到   2
        for (int col = 0 - range; col <= range; col++) {
            for (int row = 0 - range; row <= range; row++) {
                ret[i++] = col;
                ret[i++] = row;
            }
        }

        POINT_ROUND_OFFSET[range] = ret;
        return ret;
    }

   /* public static MapConst.Dir getDir(Point fromPoint, Point toPoint) {
        return getDir(fromPoint.getX(), fromPoint.getY(), toPoint.getX(), toPoint.getY());
    }

    public static Dir getDir(int fx, int fy, int tx, int ty) {
        int colDiff = tx - fx;
        int rowDiff = ty - fy;
        if (colDiff > 0) {
            if (rowDiff > 0) {
                if (rowDiff == colDiff) {
                    return Dir.RIGHT_BOTTOM;
                } else if (rowDiff > colDiff) {
                    return Dir.BOTTOM;
                } else {
                    return Dir.RIGHT;
                }
            } else if (rowDiff < 0) {
                if (rowDiff + colDiff == 0) {
                    return Dir.RIGHT_TOP;
                } else if (rowDiff + colDiff > 0) {
                    return Dir.RIGHT;
                } else {
                    return Dir.TOP;
                }
            } else {
                return Dir.RIGHT;
            }
        } else if (colDiff == 0) {
            if (ty > fy) {
                return Dir.BOTTOM;
            } else if (ty == fy) {
                // 两个点在同一个位置上
                return Dir.NONE;
            } else {
                return Dir.TOP;
            }
        } else {
            if (rowDiff > 0) {
                if (rowDiff + colDiff == 0) {
                    return Dir.LEFT_BOTTOM;
                } else if (rowDiff + colDiff > 0) {
                    return Dir.BOTTOM;
                } else {
                    return Dir.LEFT;
                }
            } else if (rowDiff < 0) {
                if (rowDiff == colDiff) {
                    return Dir.LEFT_TOP;
                } else if (rowDiff > colDiff) {
                    return Dir.LEFT;
                } else {
                    return Dir.TOP;
                }
            } else {
                return Dir.LEFT;
            }
        }
    }

    public static int distance(Point a, Point b) {
        if (a == null || b == null) return 0;
        return distance(a.getX(), a.getY(), b.getX(), b.getY());
    }*/

    public static int distance(int x1, int y1, int x2, int y2) {
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    /**
     * 判断点是否在另外一个点构建的矩形中 float
     *
     * @param center 构建坐标位置
     * @param target 目标位置
     * @param w      方向
     * @param length 长
     * @param width  宽
     */
    public static boolean checkInRange(Vector3f center, Vector3f target, float w, float length, float width) {
        //获得目标距离
        double distance = distance(center, target);
        //获得目标角度
        double degrees = getDegrees(w, angle(center, target));
        //判断角度是否小于180度（判断正前方）
        double dValue = 180 - degrees;
        if (dValue < -0.01) {
            return false;
        }
        //计算目标坐标
        double x = distance * Math.cos(Math.toRadians(degrees));
        double y = distance * Math.sin(Math.toRadians(degrees));
        //判断是否在矩形范围
        boolean checkL = length - Math.abs(y) >= -0.01;
        boolean checkW = width - Math.abs(x) >= -0.01;
        return checkL && checkW;
    }

    /**
     * 判断是否在圆中 float
     *
     * @param center 位置
     * @param target 目标点
     * @param radius 半径
     */
    public static boolean checkInRangeCylinder(Vector3f center, Vector3f target, float radius) {
        //判断距离小于圆半径
        double dValue = radius - center.distance(target);
        return dValue >= -0.01;
    }

    /**
     *
     * 我的扇形的生成方式是从中心方向像两边展开若干角度。
     *
     * 所以我们只要算出扇形的中心向量与角色向量的夹脚是否大于扇形的展开角度即可。
     *
     *  扇形的偏移角度LHL:
     * 伤害中心点与释放者正前方的偏移角度
     * 顺时针为负，逆时针为正
     * 不填则为释放者正前方
     *
     * 判断是否在扇形中 float
     *
     * @param center  当前位置
     * @param target  目标位置
     * @param radius  半径
     * @param w       方向
     * @param degrees 扇形的角度
     * @return
     */
    public static boolean checkInRangeFan(Vector3f center, Vector3f target, float w, float radius, float degrees) {
        // 判断玩家是否在圆的半径范围内
        //判断距离小于圆半径
        boolean isRange = checkInRangeCylinder(center, target, radius);
        if (!isRange) {
            return false;
        }
        //如果大于360度（圆的范围）
        if (degrees >= 360) {
            return true;
        }
        // 判断 扇形的中心向量与角色向量的夹脚是否大于扇形的展开角度
        //目标角度
        double targetDegrees = getDegrees(w, angle(center, target));
        //判断角度是否小于配置角度
        float halfDegrees = degrees / 2;
        float startDegrees = 90 - halfDegrees;
        float endDegrees = 90 + halfDegrees;
        //如果扇形范围小于等于180度
        if (halfDegrees <= 90) {
            return checkBetween(targetDegrees, startDegrees, endDegrees);
        } else {
            startDegrees += 360;
            return checkBetween(targetDegrees, startDegrees, 360) && checkBetween(targetDegrees, 0, endDegrees);
        }
    }

    /**
     * 校验目标数值是否在两个值之间
     *
     * @param targetValue 目标数值
     * @param startValue  起始数值
     * @param endValue    结束数值
     * @return
     */
    private static boolean checkBetween(double targetValue, double startValue, double endValue) {
        double sdValue = targetValue - startValue;
        double edValue = endValue - targetValue;
        return sdValue >= -0.01 && edValue >= -0.01;
    }

    /**
     * 计算根据角度计算夹角的点 float
     *
     * @param source       原点
     * @param unityDegrees unity 度数
     * @param distance     距离
     * @return 返回在degrees方向，distance距离的点
     */
    public static Vector3f createPointFromDegrees(Vector3f source, double unityDegrees, double distance) {
        double angle = unityDegreesToAngle(unityDegrees);
        return createPointFromAngle(source, angle, distance);
    }

    /**
     * unity 角度转换为java用弧度 float
     *
     * @param degrees 角度
     * @return
     */
    private static double unityDegreesToAngle(double degrees) {
        return Math.toRadians((90 - degrees) % 360);
    }

    /**
     * 根据source计算方向距离的点
     *
     * @param source   原点
     * @param angle    弧度
     * @param distance 距离
     * @return
     */
    private static Vector3f createPointFromAngle(Vector3f source, double angle, double distance) {
        Vector3f p = new Vector3f();
        float xDist = (float) (Math.cos(angle) * distance);
        float yDist = (float) (Math.sin(angle) * distance);
        p.setX(source.getX() + xDist);
        p.setZ(source.getZ() + yDist);
        p.setY(source.getY());
        return p;
    }

    /**
     * 获取2点之间基于方向 float
     * 根据两点组成的直线在x z平面的角度，顺时针
     *
     * @param srcV3 起始点
     * @param tgtV3 目标点
     * @return 角度
     */
    public static double degrees(Vector3f srcV3, Vector3f tgtV3) {
        return toUnityDegrees(angle(srcV3, tgtV3));
    }

    /**
     * java弧度转unity角度
     *
     * @param angle
     * @return
     */
    public static double toUnityDegrees(double angle) {
        return 90 - Math.toDegrees(angle);
    }

    /**
     * 获取2点之间的弧度方向
     *
     * @param srcV3 起始点
     * @param tgtV3 目标点
     * @return 弧度
     */
    private static double angle(Vector3f srcV3, Vector3f tgtV3) {
        double x = tgtV3.getX() - srcV3.getX();
        double z = tgtV3.getZ() - srcV3.getZ();
        double angle = Math.atan2(z, x);
        if (angle < 0) {
            angle += Math.PI * 2;
        }
        return angle;
    }

    /**
     * 根据配置弧度转成角度
     *
     * @param referenceDir 参考方向
     * @param angle        弧度
     * @return 角度
     */
    private static double getDegrees(double referenceDir, double angle) {
        double degrees = (referenceDir - (360 - Math.toDegrees(angle))) % 360;
        if (degrees < 0) {
            degrees += 360;
        }
        return degrees;
    }

    /*
     * referenceDir - (360 - Math.toDegrees(angle)) = referenceDir +  Math.toDegrees(angle);
     *
     * referenceDir - 360 + Math.toDegrees(angle) = referenceDir +  Math.toDegrees(angle)
     *
     */

    /**
     * 这个方法和上面的  getDegrees 一样
     * 根据配置弧度转成角度
     *
     * @param referenceDir 参考方向
     * @param angle        弧度
     * @return 角度
     */
    private static double getDegrees1(double referenceDir, double angle) {
        double degrees = referenceDir + Math.toDegrees(angle) % 360;
        if (degrees < 0) {
            degrees += 360;
        }
        return degrees;
    }


    /**
     * 计算2个点之间的距离
     *
     * @param v1 点1
     * @param v2 点2
     * @return 距离
     */
    public static double distance(Vector3f v1, Vector3f v2) {
        float x = v1.getX() - v2.getX();
        float z = v1.getZ() - v2.getZ();
        return Math.sqrt(x * x + z * z);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 根据配置角度转成弧度
     *
     * @param referenceDir 参考方向
     * @param degrees      角度
     * @return 弧度
     */
    public static double getAngle(double referenceDir, double degrees) {
        return Math.toRadians((360 - (referenceDir - degrees)) % 360); //弧度
    }

    /**
     * 根据配置角度转成弧度 float
     *
     * @param referenceDir 参考方向
     * @param degrees      角度
     * @return 弧度
     */
    public static float getAngle(float referenceDir, float degrees) {
        return (float) Math.toRadians((360 - (referenceDir - degrees)) % 360); //弧度
    }


    /**
     * 求起点到终点之间的 distance距离的点
     *
     * @param x        起点坐标x
     * @param z        起点坐标y
     * @param x2       终点坐标x
     * @param z2       终点坐标y
     * @param distance 距离起点的距离
     * @return v3坐标
     */
    private static Vector3f betweenPointsByDis(double x, double y, double z, double x2, double y2, double z2, double distance) {
        Vector3f p = new Vector3f();
        double xDiff = (x2 - x);
        double yDiff = (y2 - y);
        double zDiff = (z2 - z);
        //勾股定理求第三边
        double ptDist = Math.sqrt(xDiff * xDiff + zDiff * zDiff + yDiff * yDiff);
        double distOnPtDist = distance / ptDist;
        double xDist = xDiff * distOnPtDist;
        double yDist = yDiff * distOnPtDist;
        double zDist = zDiff * distOnPtDist;
        p.setX((float) (x + xDist));
        p.setY((float) (y + yDist));
        p.setZ((float) (z + zDist));
        return p;
    }


    /**
     * 求起点到终点之间的 distance距离的点 float
     *
     * @param x        起点坐标x
     * @param z        起点坐标y
     * @param x2       终点坐标x
     * @param z2       终点坐标y
     * @param distance 距离起点的距离
     * @return v3坐标
     */
    private static Vector3f betweenPointsByDis(float x, float y, float z, float x2, float y2, float z2, float distance) {
        Vector3f p = new Vector3f();
        float xDiff = (x2 - x);
        float yDiff = (y2 - y);
        float zDiff = (z2 - z);
        //勾股定理求第三边
        float ptDist = (float) Math.sqrt(xDiff * xDiff + zDiff * zDiff + yDiff * yDiff);
        float distOnPtDist = distance / ptDist;
        float xDist = xDiff * distOnPtDist;
        float yDist = yDiff * distOnPtDist;
        float zDist = zDiff * distOnPtDist;
        p.setX(x + xDist);
        p.setY(y + yDist);
        p.setZ(z + zDist);
        return p;
    }

    /**
     * 起点 srcV3,到目标点tgtV3 distance距离的点
     *
     * @param srcV3    起点
     * @param tgtV3    目标点
     * @param distance 距离起点的距离
     * @return 起点到重点distance的点，如果距离超过原本距离则直接返回终点
     */
    public static Vector3f betweenPointsByDis(Vector3f srcV3, Vector3f tgtV3, double distance) {
        //距离小于则直接返回目标点
        if (srcV3.distance(tgtV3) <= distance) {
            return new Vector3f(tgtV3.getX(), tgtV3.getY(), tgtV3.getZ());
        }
        return betweenPointsByDis(srcV3.getX(), srcV3.getY(), srcV3.getZ(), tgtV3.getX(), tgtV3.getY(), tgtV3.getZ(), distance);
    }


    /**
     * 起点 srcV3,到目标点tgtV3 distance距离的点 float
     *
     * @param srcV3    起点
     * @param tgtV3    目标点
     * @param distance 距离起点的距离
     * @return 起点到重点distance的点，如果距离超过原本距离则直接返回终点
     */
    public static Vector3f betweenPointsByDis(Vector3f srcV3, Vector3f tgtV3, float distance) {
        //距离小于则直接返回目标点
        if (srcV3.distance(tgtV3) <= distance) {
            return tgtV3;
        }
        return betweenPointsByDis(srcV3.getX(), srcV3.getY(), srcV3.getZ(), tgtV3.getX(), tgtV3.getY(), tgtV3.getZ(), distance);
    }


    public static double lookAtRot(Vector3f v1, Vector3f v2) {
        double x = v2.getX() - v1.getX();
        double z = v2.getZ() - v1.getZ();

        if (x == 0) {
            if (z > 0) {
                return 0;
            } else if (z < 0) {
                return 180;
            }
        }

        double rot = Math.atan(x / z);
        if (rot > 0) {
            if (x < 0) {
                rot = rot - 180;
            }

        } else {

            if (x > 0) {
                rot += 180;
            }
        }


        if (rot < 0) {
            rot += 360;
        }
        return rot;
    }


    /**
     * 构建矩形 float
     *
     * @param position 当前位置
     * @param distance 距离
     * @param w        方向
     * @param width    宽
     * @param height   高
     */
    public static List<Vector3f> getPolygon(Vector3f position, float distance, float w, float width, float height) {

        Vector3f source = createPointFromDegrees(position, 0, distance);
        Vector3f corner_1 = createPointFromDegrees(source, -90, width / 2);
        Vector3f corner_2 = createPointFromDegrees(source, 90, width / 2);
        Vector3f corner_3 = createPointFromDegrees(corner_2, 0, height);
        Vector3f corner_4 = createPointFromDegrees(corner_1, 0, height);
        List<Vector3f> sectors = new ArrayList<>(4);
        sectors.add(corner_1);
        sectors.add(corner_4);
        sectors.add(corner_3);
        sectors.add(corner_2);

        return sectors;
    }

    /**
     * 长方形 float
     *
     * @param w
     * @param x
     * @param z
     * @param offset
     * @param vr_width
     * @param vr_hight
     */
    public static void getRectangle(float w, float x, float z, float offset, float vr_width, float vr_hight) {

        vr_width = vr_width / 2;


        float w_360 = getATan360(w, 0);
        float w_a = getATan360(w_360, -90);
        float w_b = getATan360(w_360, 90);


        /* 根据三角函数计算出 A 点偏移量 */
        float v12_A_X = getV12XD(vr_width, w_a);
        float v12_A_Y = getV12ZD(vr_width, w_b);
        /* 由于在计算12方向位移函数里面已经计算偏移量是正负值 */
        float A_X = x + v12_A_X;
        float A_Y = z + v12_A_Y;


    }


    /**
     * 当前角度加上修正角度，并且换算360 float
     *
     * @param atan360
     * @param tmptan
     * @return
     */
    private static float getATan360(float atan360, float tmptan) {
        atan360 += tmptan;
        if (atan360 < 0) {
            atan360 = 360 + atan360;
        }
        return BitUtil.getFloat4(atan360 % 360);
    }

    /**
     * 位移是z轴 float
     *
     * @param offset
     * @param sin
     * @return
     */
    private static float getV12ZD(float offset, float sin) {
        offset = Math.abs(offset);
        /* 三角函数计算器 */
        float sinr = (float) (offset * Math.sin(Math.toRadians(sin)));
        /* 拿到保留4位小数计算器 */
        return BitUtil.getFloat4(sinr);
    }

    /**
     * 位移时的X轴 float
     *
     * @param offset
     * @param cos
     * @return
     */
    private static float getV12XD(float offset, float cos) {
        offset = Math.abs(offset);
        /* 三角函数计算器 */
        float cosr = (float) (offset * Math.cos(Math.toRadians(cos)));
        /* 拿到保留4位小数计算器 */
        return BitUtil.getFloat4(cosr);
    }

    public static void aa(int x, int y, float a){
        int newX = (int) (x* Math.cos(Math.toRadians(a)));
        int newY = (int) (y* Math.sin(Math.toRadians(a)));
        System.out.println(newX);
        System.out.println(newY);
    }

    public static void main(String[] args) {
        aa(2,3,90);
    }
}
