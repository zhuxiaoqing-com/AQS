package com.youxi.building.buildingPos.impl;

import com.youxi.building.buildingPos.IBuildPosHandler;
import com.youxi.building.entity.PosPositionInfo;

import java.util.Map;

/**
 * 载具位置校验暂时不进行处理
 *
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/13 21:01
 * @Description:
 */
public class CarrierBuildPosHandler implements IBuildPosHandler {
	@Override
	public boolean checkIsCanBuilding(long playerId, int buildingId, int pos, int dir) {
		return true;
	}

	@Override
	public void addBuilding(long playerId, Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {

	}

	@Override
	public void removeBuilding(Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {

	}
}
