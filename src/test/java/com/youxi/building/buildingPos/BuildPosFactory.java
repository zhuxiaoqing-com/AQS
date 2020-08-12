package com.youxi.building.buildingPos;

import com.youxi.building.Misc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/14 13:35
 * @Description:
 */
public class BuildPosFactory {
    public static Logger logger = LoggerFactory.getLogger(BuildPosFactory.class);

    private static final Map<Integer, IBuildPosHandler> BUILD_POS_HANDLERS = new HashMap<>();

    static {
       /* // 地板
        int floor = Misc.getIntIndexByXY((short) HomeEnum.HomeBuildingType.FLOOR_OR_WALL, (short) HomeEnum.HomeBuildingSubType.FLOOR);
        BUILD_POS_HANDLERS.put(floor, new FloorBuildPosHandler());
        // 墙
        int wall = Misc.getIntIndexByXY((short) HomeEnum.HomeBuildingType.FLOOR_OR_WALL, (short) HomeEnum.HomeBuildingSubType.WALL);
        BUILD_POS_HANDLERS.put(wall, new WallBuildPosHandler());
        // 地表
        BUILD_POS_HANDLERS.put(HomeEnum.HomeBuildingType.SOIL_SURFACE, new SoilSurfacePosHandler());
        // 摆件
        BUILD_POS_HANDLERS.put(HomeEnum.HomeBuildingType.BAI_JIAN, new OtherBuildPosHandler());
        // 装饰品
        BUILD_POS_HANDLERS.put(HomeEnum.HomeBuildingType.ORNAMENTS, new OtherBuildPosHandler());*/
    }



    public static IBuildPosHandler getHandler(int type, int subType) {
        int key = type;
        if (subType != 0) {
            key = Misc.getIntIndexByXY((short) type, (short) subType);
        }
        IBuildPosHandler iBuildPosHandler = BUILD_POS_HANDLERS.get(key);

        if (iBuildPosHandler == null) {
            logger.error("homePositionInfo is null type:{} subType:{}", type, subType);
        }
        return iBuildPosHandler;
    }
}
