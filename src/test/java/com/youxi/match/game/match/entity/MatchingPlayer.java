package com.youxi.match.game.match.entity;


import com.youxi.building.Misc;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 玩家匹配对象
 * 
 * @author Eric.Ma
 *
 */
public class MatchingPlayer {

	private Object playerInfo;
	private List<Integer> dutyList = new ArrayList<>();
	private List<Integer> dungeonList = new ArrayList<>();

	private long matchTime = System.currentTimeMillis();

	private boolean isRobot;

	private int robotHeadId;
	private String robotName;

	private int realSelectDuty;
	private long teamId;

	private int robotAvatar;

	public MatchingPlayer(boolean test, boolean robot) {
		this.isRobot = robot;
	}

	public MatchingPlayer() {
		this(0);
	}
	public MatchingPlayer(int duty) {
		this.isRobot = true;
		if (duty > 0) {
			this.dutyList.add(duty);
		}
		// 头
	/*	robotHeadId = Misc.getRandom(ConfigManager.getInstance().getConf(HeadModel.class).keySet());
		// 名字
		robotName = ModelMisc.<CharacterModule>getModule(CharacterModule.MODULE_NAME).getBoyName();
		//头像
		robotAvatar = Misc.getRandom(ConfigManager.getInstance().getConf(FaceModel.class).keySet());*/
	}

	public MatchingPlayer(Object playerInfo, List<Integer> dutyList, List<Integer> dungeonList) {
		super();
		this.playerInfo = playerInfo;
		this.dutyList.addAll(dutyList);
		// 打乱玩家职责
		Collections.shuffle(this.dutyList, ThreadLocalRandom.current());
		this.dutyList = Collections.unmodifiableList(this.dutyList);
		this.dungeonList = Collections.unmodifiableList(dungeonList);
	}

	public long getPlayerId() {
		return isRobot ? 0: 0;//playerInfo.getPlayerId();
	}

	public int getRobotHeadId() {
		return robotHeadId;
	}

	public String getRobotName() {
		return robotName;
	}

	public int getRobotAvatar() {
		return robotAvatar;
	}

	public Object getPlayerInfo() {
		return playerInfo;
	}

	public List<Integer> getDutyList() {
		return dutyList;
	}

	public boolean isRobot() {
		return isRobot;
	}

	public long getMatchTime() {
		return matchTime;
	}

	public List<Integer> getDungeonList() {
		return dungeonList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerInfo, dutyList, dungeonList, matchTime, isRobot, robotHeadId, robotName, realSelectDuty, teamId, robotAvatar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchingPlayer other = (MatchingPlayer) obj;
		return this.getPlayerId() == other.getPlayerId();
	}

	public int getRealSelectDuty() {
		return realSelectDuty;
	}

	public void setRealSelectDuty(int realSelectDuty) {
		this.realSelectDuty = realSelectDuty;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public long getTeamId() {
		return teamId;
	}
}
