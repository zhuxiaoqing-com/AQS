package com.youxi.building;

public class HomePositionBuildingManager {

  /*  public static Logger logger = LoggerFactory.getLogger(HomePositionBuildingManager.class);

    private static HomePositionBuildingManager instance = new HomePositionBuildingManager();

    public static HomePositionBuildingManager getInstance() {
        return instance;
    }

    *//**
     * key ：玩家id
     * value ：家园建筑坐标类
     *//*
    private Map<Long, PosHomeBuildingInfo> playerBuildingPosMap = new ConcurrentHashMap<>();


    *//**
     * 进入家园初始化家园建筑坐标信息
     *
     * @param playerId playerId
     *//*
    public void enterHome(long playerId) {
        if (checkIsNotOpen()) {
            return;
        }
        PosHomeBuildingInfo homePositionInfo = new PosHomeBuildingInfo();
        setBuildingPosMap(playerId, homePositionInfo);
*//*
        PlayerHomeProxy homeProxy = new PlayerHomeProxy(playerId);
        Map<Long, HomeBuilding> homeBuildingMap = homeProxy.getHome().getBuild();*//*
    }

    *//**
     * 退出家园，删除玩家建筑坐标信息
     *
     * @param playerId playerId
     *//*
    public void exitHome(long playerId) {
        if (checkIsNotOpen()) {
            return;
        }
        removeBuildingPosMap(playerId);
    }


    *//**
     * 检测 坐标是否合法
     *
     * @param playerId       playerId
     * @param currBuildingId currBuildingId
     * @param pos            pos
     * @param dir            dir
     *//*
    public boolean checkPosIsLegal(long playerId, int currBuildingId, int pos, int dir) {
        // 没有开放默认为 校验通过
        if (checkIsNotOpen()) {
            return true;
        }

        BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(currBuildingId);
        IBuildPosHandler handler = BuildPosFactory.getHandler(buildingModel.getType(), buildingModel.getSubType());

        return handler.checkIsCanBuilding(playerId, currBuildingId, pos, dir);
    }


    *//**
     * 添加建筑
     *
     * @param playerId playerId
     * @param building building
     *//*
    public void addBuilding(long playerId, xbean.logic.HomeBuilding building) {
        if (checkIsNotOpen()) {
            return;
        }
        PosHomeBuildingInfo posHomeBuildingInfo = getPosHomeBuildingInfo(playerId);

        BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(building.getBid());
        IBuildPosHandler handler = BuildPosFactory.getHandler(buildingModel.getType(), buildingModel.getSubType());

        handler.addBuilding(playerId, posHomeBuildingInfo.getPositionInfoMap(), building.getBsrl(), building.getBid(), building.getPos(), building.getDir());
    }

    *//**
     * 删除建筑
     *
     * @param playerId playerId
     * @param building building
     *//*
    public void removeBuilding(long playerId, xbean.logic.HomeBuilding building) {
        if (checkIsNotOpen()) {
            return;
        }
        PosHomeBuildingInfo posHomeBuildingInfo = getPosHomeBuildingInfo(playerId);

        BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(building.getBid());
        IBuildPosHandler handler = BuildPosFactory.getHandler(buildingModel.getType(), buildingModel.getSubType());

        handler.removeBuilding(posHomeBuildingInfo.getPositionInfoMap(), building.getBsrl(), building.getBid(), building.getPos(), building.getDir());
    }


    *//**
     * 是否可以移动建筑
     *
     * @param playerId playerId
     * @param building building
     *//*
    public boolean checkIsCanMoveBuilding(long playerId, xbean.logic.HomeBuilding building, int pos, int dir) {
        if (checkIsNotOpen()) {
            return true;
        }
        PosHomeBuildingInfo posHomeBuildingInfo = getPosHomeBuildingInfo(playerId);

        BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(building.getBid());
        IBuildPosHandler handler = BuildPosFactory.getHandler(buildingModel.getType(), buildingModel.getSubType());

        // 删除旧的建筑信息
        handler.removeBuilding(posHomeBuildingInfo.getPositionInfoMap(), building.getBsrl(), building.getBid(), building.getPos(), building.getDir());
        // 查看新的坐标信息是否合法
        boolean result = handler.checkIsCanBuilding(playerId, building.getBid(), pos, dir);
        // 再将旧的建筑信息添加回去
        handler.addBuilding(playerId, posHomeBuildingInfo.getPositionInfoMap(), building.getBsrl(), building.getBid(), building.getPos(), building.getDir());
        return result;
    }

    *//**
     * 移动建筑
     *//*
    public void moveBuilding(long playerId, xbean.logic.HomeBuilding oldBuilding, int newPos, int newDir) {
        if (checkIsNotOpen()) {
            return;
        }
        PosHomeBuildingInfo posHomeBuildingInfo = getPosHomeBuildingInfo(playerId);

        BuildingModel buildingModel = ConfigManager.getInstance().getConf(BuildingModel.class).get(oldBuilding.getBid());
        IBuildPosHandler handler = BuildPosFactory.getHandler(buildingModel.getType(), buildingModel.getSubType());

        // 删除旧的坐标位置
        handler.removeBuilding(posHomeBuildingInfo.getPositionInfoMap(), oldBuilding.getBsrl(), oldBuilding.getBid(), oldBuilding.getPos(), oldBuilding.getDir());
        // 添加新的坐标位置
        handler.addBuilding(playerId, posHomeBuildingInfo.getPositionInfoMap(), oldBuilding.getBsrl(), oldBuilding.getBid(), newPos, newDir);
    }


    private boolean checkIsNotOpen() {
        String check = ConfigManager.getInstance().getPropConf("sys").getProperty("sys.home.buildpos.check");
        return !Boolean.valueOf(check);
    }

    public PosHomeBuildingInfo getPosHomeBuildingInfo(long playerId) {
        PosHomeBuildingInfo homeBuildingInfo = playerBuildingPosMap.get(playerId);
        if (homeBuildingInfo == null) {
            logger.error("homePositionInfo is null player:{}", playerId);
        }
        return homeBuildingInfo;
    }

    private void setBuildingPosMap(long playerId, PosHomeBuildingInfo homePositionInfo) {
        this.playerBuildingPosMap.put(playerId, homePositionInfo);
    }

    private void removeBuildingPosMap(long playerId) {
        this.playerBuildingPosMap.remove(playerId);
    }
*/
}
