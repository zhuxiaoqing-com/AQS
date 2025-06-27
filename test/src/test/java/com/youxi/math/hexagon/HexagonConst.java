package com.youxi.math.hexagon;


import com.youxi.math.hexagon.beans.AxialCoordinates;
import com.youxi.math.hexagon.beans.Coordinates;
import com.youxi.math.hexagon.beans.OffsetCoordinates;

import java.util.ArrayList;
import java.util.List;

public class HexagonConst {
    public static final float outerRadius = 1.0f;
    public static final float innerRadius = outerRadius * 0.866025404f;
    public static final float sqrt3 = 0.866025404f * 2;

    public static final int height = 8;
    public static final int width = 7;
    public static OffsetCoordinates offsetReflection00 = new OffsetCoordinates(width - 1, height - 1);
    public static AxialCoordinates axialReflection00 = offsetToAxial(offsetReflection00);
    public static AxialCoordinates axial00 = offsetToAxial(new OffsetCoordinates(0, 0));

    public static final int HEX_SIZE = height * width;
    public static final int HEX_SELF_SIZE = HEX_SIZE / 2;

    public static final int INVALID_INDEX = -1;

    //手里的牌最多可以有几张
    public static final int HAND_SIZE = 8;

    /**
     * 是否是合法的手里坐标
     */
    public static boolean isValidHandIndex(int handIndex) {
        return handIndex >= 0 && handIndex < HAND_SIZE;
    }

    /**
     * 是否是自己的六边形格子(可以放置的六边形格子)
     */
    public static boolean isValidSelfHex(int index) {
        if(!isValidHex(index)){
            return false;
        }
        // 一半是 28个
        int halfSize = height * width / 2;
        // index是下标从0开始 所以要小于halfSize才行
        return index< halfSize;
    }

    /**
     * 是否是合法的六边形格子
     */
    public static boolean isValidHex(int index) {
        return index >= 0 && index < HEX_SIZE;
    }


    /////////////////////下面的是六边形格子各种转换方法///////////////////////
    /////////////////////下面的是六边形格子各种转换方法///////////////////////
    /////////////////////下面的是六边形格子各种转换方法///////////////////////
    private static AxialCoordinates odd_rToAxial(OffsetCoordinates hex) {
        int q = hex.getCol() - (hex.getRow() - (hex.getRow() & 1)) / 2;
        int r = hex.getRow();
        return new AxialCoordinates(q, r);
    }

    private static AxialCoordinates even_rToAxial(OffsetCoordinates hex) {
        int q = hex.getCol() - (hex.getRow() + (hex.getRow() & 1)) / 2;
        int r = hex.getRow();
        return new AxialCoordinates(q, r);
    }

    private static OffsetCoordinates axialToOdd_r(AxialCoordinates hex) {
        int col = hex.getQ() + (hex.getR() - (hex.getR() & 1)) / 2;
        int row = hex.getR();
        return new OffsetCoordinates(col, row);
    }

    private static OffsetCoordinates axialToEven_r(AxialCoordinates hex) {
        int col = hex.getQ() + (hex.getR() + (hex.getR() & 1)) / 2;
        int row = hex.getR();
        return new OffsetCoordinates(col, row);
    }

    public static AxialCoordinates offsetToAxial(OffsetCoordinates hex) {
        return odd_rToAxial(hex);
    }

    public static OffsetCoordinates axialToOffset(AxialCoordinates hex) {
        return axialToOdd_r(hex);
    }


    public static Coordinates pointy_hex_to_pixel(AxialCoordinates hex) {
        float size = HexagonConst.outerRadius;
        float sqrt3 = HexagonConst.sqrt3;
        float x = size * (sqrt3 * hex.getQ() + sqrt3 / 2 * hex.getR());
        float z = size * (3f / 2 * hex.getR());
        return new Coordinates(x, z);
    }

    public static AxialCoordinates pixel_to_pointy_hex(Coordinates point) {
        float size = HexagonConst.outerRadius;
        float sqrt3 = HexagonConst.sqrt3;

        float q = (sqrt3 / 3 * point.getX() - 1f / 3 * point.getZ()) / size;
        float r = (2f / 3 * point.getZ()) / size;

        return axial_round(q, r, -q - r);
    }

    public static AxialCoordinates axial_round(float hexQ, float hexR, float hexS) {
        int q = Math.round(hexQ);
        int r = Math.round(hexR);
        int s = Math.round(hexS);


        float q_diff = Math.abs(q - hexQ);
        float r_diff = Math.abs(r - hexR);
        float s_diff = Math.abs(s - hexS);

        if (q_diff > r_diff && q_diff > s_diff)
            q = -r - s;
        else if (r_diff > s_diff)
            r = -q - s;
        else
            s = -q - r;

        return new AxialCoordinates(q, r);
    }


    public static int getIndex(OffsetCoordinates offsetCoordinates) {
        // index 不支持 <0的转换 不然会出错
        if (offsetCoordinates.getRow() < 0 || offsetCoordinates.getCol() < 0) {
            return INVALID_INDEX;
        }

        if (offsetCoordinates.getRow() >= HexagonConst.height || offsetCoordinates.getCol() >= HexagonConst.width) {
            return INVALID_INDEX;
        }
        return offsetCoordinates.getCol() + offsetCoordinates.getRow() * HexagonConst.width;
    }

    /**
     * 反转
     */
    public static AxialCoordinates reflection(AxialCoordinates hex) {
        AxialCoordinates subtract = hex.subtract(axial00);
        return axialReflection00.subtract(subtract);
    }


    /**
     * 获取周围的坐标，包含自己
     */
    public static List<AxialCoordinates> coordinateRange(AxialCoordinates hex, int N) {
        ArrayList<AxialCoordinates> resultList = new ArrayList<>();
        for (int q = -N; q <= N; q++) {
            int rMin = Math.max(-N, -q - N);
            int rMax = Math.min(N, -q + N);
            for (int r = rMin; r <= rMax; r++) {
                AxialCoordinates offset = new AxialCoordinates(q, r);
                resultList.add(hex.add(offset));
            }
        }
        return resultList;
    }



    public static void main(String[] args) {
        AxialCoordinates axialCoordinates = new AxialCoordinates(3, 4);
        System.out.println(reflection(axialCoordinates));

        axialCoordinates = new AxialCoordinates(0, 0);
        System.out.println(reflection(axialCoordinates));

        axialCoordinates = new AxialCoordinates(3, 5);
        System.out.println(reflection(axialCoordinates));


        axialCoordinates = new AxialCoordinates(-2, 5);
        System.out.println(reflection(axialCoordinates));
    }


}
