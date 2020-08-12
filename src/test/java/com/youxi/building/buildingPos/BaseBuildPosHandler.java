package com.youxi.building.buildingPos;


import com.youxi.building.HomePositionBuildingManager;
import com.youxi.building.Misc;
import com.youxi.building.entity.HomeBuildingChunkBean;
import com.youxi.building.entity.PosHomeBuildingInfo;
import com.youxi.building.entity.PosPositionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 17:15
 * @Description:
 */
public abstract class BaseBuildPosHandler implements IBuildPosHandler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取建筑该建筑需要占据的坐标
	 *
	 * @param buildingId 建筑的配表id
	 * @param pos        玩家
	 * @param dir        朝向
	 * @return 建筑占得格子数的 list
	 */
	protected Set<Integer> findBuildingOccupyPos(int buildingId, int pos, int dir) {
		short chunkX = Misc.getIntHigh(pos);
		short chunkZ = Misc.getIntLow(pos);
		/* BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(buildingId);*/

		// 这里配表是这么配置的 z;x
		//ArrayList<Integer> placeSize = buildingModel.getPlaceSize();
		List<Integer> placeSize = Collections.emptyList();
		int bodySizeX = placeSize.get(1);
		int bodySizeY = placeSize.get(0);
		int rotateCenterPosByDir = countCenterPos(dir, chunkX, chunkZ, bodySizeX, bodySizeY);
		return countPosList(dir, Misc.getIntHigh(rotateCenterPosByDir), Misc.getIntLow(rotateCenterPosByDir), bodySizeX, bodySizeY);
	}

	@Override
	public boolean checkIsCanBuilding(long playerId, int buildingId, int pos, int dir) {
		Set<Integer> buildingOccupyPos = findBuildingOccupyPos(buildingId, pos, dir);
		// 坐标是否合法
		for (int position : buildingOccupyPos) {
			if (!checkPosIsLegal(position)) {
				return false;
			}
		}
		// 检测放置条件  todo 现在这个判断规则不明确 策划那边表示以后还要改, 所以先删除这个判断
        /*if (!checkPreCondition(playerId, buildingId, buildingOccupyPos)) {
            return false;
        }*/


		return checkIsCanBuilding(playerId, buildingOccupyPos, dir);
	}

	/**
	 * 判断是否满足 condition
	 * <p>
	 * 必须是可建筑区域内所有的格子都是某一个 condition;
	 * 例子 condition [1111,2222] 建筑区域4个格子
	 * 必须4个格子都有 1111;
	 * 或者必须4个格子都有 2222
	 * 不能 2个格子 1111，2个格子 2222
	 *
	 * @param playerId          playerId
	 * @param buildingId        buildingId
	 * @param buildingOccupyPos buildingOccupyPos
	 * @return true 满足条件,能放置; false 不满足条件不能放置
	 */
	private boolean checkPreCondition(long playerId, int buildingId, Set<Integer> buildingOccupyPos) {
		/*PlayerHomeProxy homeProxy = new PlayerHomeProxy(playerId);
		Map<Long, HomeBuilding> homeBuilding = homeProxy.getHome().getBuild();

		// 检测放置条件
		BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(buildingId);
		ArrayList<Integer> condition = buildingModel.getCondition();
		// 如果没有放置条件说明不能放置
		if (condition.isEmpty()) {
			return false;
		}
		// 地表不受 condition 这个条件的影响(但是如果 condition 这个字段为空的话，地表还是不能放置的; 墙同上)
		if (buildingModel.getType() == HomeEnum.HomeBuildingType.SOIL_SURFACE ||
				(buildingModel.getType() == HomeEnum.HomeBuildingType.FLOOR_OR_WALL && buildingModel.getSubType() == HomeEnum.HomeBuildingSubType.WALL)) {
			return true;
		}

		Map<Integer, PosPositionInfo> buildingInfos = findBuildingInfoByPosList(playerId, buildingOccupyPos);


		// 判断是否有空地; 默认 0 为空地;  如果该建筑区域没有任何东西(地表也算，有地表就不算空地了， 但是墙不算，墙不占用任何地块，占用线)
		if (condition.contains(0)) {
			// 如果都是空地的话 这里只能判断该坐标不为空,不能判断该坐标为空; 因为buildingInfos 数量不一定是和 buildingOccupyPos 一样
			long count = buildingInfos.values().stream().filter(info -> !info.isSpaceArea()).count();
			if (count == 0) {
				return true;
			}
		}


		// 如果没有空地 或者不能在空地上面建筑 condition 只校验地板和地表id;
		for (int cfgId : condition) {
			if (cfgId <= 0) {
				continue;
			}
			long count = buildingInfos.values().stream().filter(info -> {
				// 判断地板id
				if (info.getFloorSrl() != 0 && cfgId == homeBuilding.get(info.getFloorSrl()).getBid()) {
					return true;
				}
				// 判断地表Id
				for (long soilSurfaceSrl : info.getSoilSurfaceSrl()) {
					int buidingId = homeBuilding.get(soilSurfaceSrl).getBid();
					if (cfgId == buidingId) {
						return true;
					}
				}
				return false;
			}).count();

			// 如果 和 要新建筑的占据的格子数量相同
			if (count == buildingOccupyPos.size()) {
				return true;
			}
		}*/
		return false;
	}

	/**
	 * 检测是否可以建筑
	 */
	protected abstract boolean checkIsCanBuilding(long playerId, Set<Integer> allPosList, int dir);


	/**
	 * 获取posList 里面的建筑信息
	 *
	 * @param posCollection 地板占据的坐标
	 * @return key: 建筑的唯一Id  value: 该建筑的infos
	 */
	protected Map<Integer, PosPositionInfo> findBuildingInfoByPosList(long playerId, Collection<Integer> posCollection) {
	/*	PosHomeBuildingInfo homeBuildingInfo = HomePositionBuildingManager.getInstance().getPosHomeBuildingInfo(playerId);

		//  key: 建筑的唯一Id  value: 该建筑的infos
		Map<Integer, PosPositionInfo> posOccupyMap = new HashMap<>();

		Map<Integer, PosPositionInfo> positionInfoMap = homeBuildingInfo.getPositionInfoMap();
		for (int pos : posCollection) {
			PosPositionInfo positionInfo = positionInfoMap.get(pos);
			if (positionInfo == null) {
				continue;
			}
			posOccupyMap.put(pos, positionInfo);
		}
		return posOccupyMap;*/
		return Collections.emptyMap();
	}


	/**
	 * 检验坐标合法性
	 *
	 * @param position 坐标
	 */
	protected boolean checkPosIsLegal(int position) {
       /* Map<Integer, HomeBuildingChunkBean> chunkMap = ModuleManager.getInstance()
                .<HomeBuildingChunkModule>getModuleByName(HomeBuildingChunkModule.MODULE_NAME).getChunkMap();*/
		Map<Integer, HomeBuildingChunkBean> chunkMap = new HashMap<>();
       /* short x = Misc.getIntHigh(position);
        short z = Misc.getIntLow(position);*/
		short x = 0;
		short z = 0;
		for (HomeBuildingChunkBean bean : chunkMap.values()) {
			if (x >= bean.getMinChunkX() && x <= bean.getMaxChunkX()
					&& z >= bean.getMinChunkZ() && z <= bean.getMaxChunkZ()) {
				return true;
			}
		}
		return false;
	}


	/**
	 * @param dir       客户端朝向 默认朝向 1；朝向旋转 1->2->3->0->1
	 * @param chunkX    玩家选中要放置坦克的格子 X 轴
	 * @param chunkY    玩家选中要放置坦克的格子 Z 轴
	 * @param bodySizeX 坦克占X轴的几个格子(默认朝向下)
	 * @param bodySizeY 坦克占Z(Y)轴的几个格子(默认朝向下)
	 * @return 当前方向的旋转中心点position(x > > z); 坦克会根据朝向不同，有不同的旋转中心点的; 旋转中心点就是坦克绕哪个方块进行旋转的
	 */
	private int countCenterPos(int dir, short chunkX, short chunkY, int bodySizeX, int bodySizeY) {
		int abs = Math.min(bodySizeX, bodySizeY);
		int centerPos = 0;
		switch (dir) {
			case 1: {
				centerPos = Misc.getIntIndexByXY(chunkX, chunkY);
				break;
			}
			case 2: {
				centerPos = Misc.getIntIndexByXY(chunkX, (short) (chunkY + abs - 1));
				break;
			}
			case 3: {
				centerPos = Misc.getIntIndexByXY((short) (chunkX + abs - 1), (short) (chunkY + abs - 1));
				break;
			}
			case 0: {
				centerPos = Misc.getIntIndexByXY((short) (chunkX + abs - 1), chunkY);
				break;
			}
			default: {
				throw new RuntimeException("获取中心点错误 dir不存在：" + dir);
			}
		}

		logger.debug("countCenterPos  dir:{} x:{} y:{}  centerX:{} centerY:{}", dir, chunkX, chunkY, Misc.getIntHigh(centerPos), Misc.getIntLow(centerPos));
		return centerPos;
	}


	/**
	 * @param dir        客户端朝向 默认朝向 1；朝向旋转 1->2->3->0->1
	 * @param centerPosX 当前方向的旋转中心点X轴; 坦克会根据朝向不同，有不同的旋转中心点的; 旋转中心点就是建筑绕哪个方块进行旋转的
	 * @param centerPosY 当前方向的旋转中心点Z(Y)轴
	 * @param bodySizeX  建筑占X轴的几个格子(默认朝向下)
	 * @param bodySizeY  建筑占Z(Y)轴的几个格子(默认朝向下)
	 * @return 建筑占得格子数的 list
	 */
	private Set<Integer> countPosList(int dir, short centerPosX, short centerPosY, int bodySizeX, int bodySizeY) {
		Set<Integer> poss = new HashSet<>();
		switch (dir) {
			case 1: {
				for (int x = 0; x < bodySizeX; x++) {
					for (int y = 0; y < bodySizeY; y++) {
						poss.add(Misc.getIntIndexByXY((short) (centerPosX + x), (short) (centerPosY + y)));
					}
				}
				break;
			}
			case 2: {
				for (int x = 0; x < bodySizeY; x++) {
					for (int y = 0; y < bodySizeX; y++) {
						poss.add(Misc.getIntIndexByXY((short) (centerPosX + x), (short) (centerPosY - y)));
					}
				}
				break;
			}
			case 3: {
				for (int x = 0; x < bodySizeX; x++) {
					for (int y = 0; y < bodySizeY; y++) {
						poss.add(Misc.getIntIndexByXY((short) (centerPosX - x), (short) (centerPosY - y)));
					}
				}
				break;
			}
			case 0: {
				for (int x = 0; x < bodySizeY; x++) {
					for (int y = 0; y < bodySizeX; y++) {
						poss.add(Misc.getIntIndexByXY((short) (centerPosX - x), (short) (centerPosY + y)));
					}
				}
				break;
			}
			default: {
				throw new RuntimeException("获取建筑占据的格子错误 dir不存在：" + dir);
			}
		}
		return poss;
	}
}
