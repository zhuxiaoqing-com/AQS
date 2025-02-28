package com.youxi.math.hexagon.distField;

import com.youxi.math.hexagon.HexagonConst;

public class DistFieldObj {
    int[] distField = new int[HexagonConst.HEX_SIZE];
    Long[] enemyCanAttack = new Long[HexagonConst.HEX_SIZE];
    int startIndex;
    long srl;

    public DistFieldObj(int startIndex, long srl) {
        this.startIndex = startIndex;
        this.srl = srl;
    }

    public int[] getDistField() {
        return distField;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public long getSrl() {
        return srl;
    }

    public void setDistField(int[] distField) {
        this.distField = distField;
    }

    public Long[] getEnemyCanAttack() {
        return enemyCanAttack;
    }

    public void setEnemyCanAttack(Long[] enemyCanAttack) {
        this.enemyCanAttack = enemyCanAttack;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setSrl(long srl) {
        this.srl = srl;
    }
}
