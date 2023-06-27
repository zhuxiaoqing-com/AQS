package com.youxi.match.game.match.matchType;


import com.youxi.building.Misc;
import com.youxi.match.game.match.DungeonDuty;
import com.youxi.match.game.match.DutyPlayerCount;
import com.youxi.match.game.match.Range;
import com.youxi.match.game.match.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbsTeamMatching {

	public static final int TEAM_MAX_NUM = 5;
	protected boolean needRobot;
	protected int maxRobotNum;
	protected int maxRoleNum;

	protected Set<Long> alreadyMatchTeams = new HashSet<>();
	protected Map<Integer, Integer> dutyMap;
	protected boolean pvp;

	public AbsTeamMatching(int maxRoleNum, int maxRobotNum, boolean needRobot) {
		this(maxRoleNum, maxRobotNum, needRobot, null, false);
	}

	public AbsTeamMatching(int maxRoleNum, int maxRobotNum, boolean needRobot, DutyPlayerCount count, boolean pvp) {
		this.maxRoleNum = maxRoleNum;
		this.needRobot = needRobot;
		if (needRobot) { // 不需要机器人的话 最大机器人数量就是为0
			this.maxRobotNum = maxRobotNum;
		}
		this.pvp = pvp;
		this.dutyMap = randRealDuty(count, maxRoleNum);
	}

	public List<MatchTeamResultList> matching(Map<Long, MatchingTeam> teamMap) {
		// 匹配前先判断匹配池人数是否可以组成一个队伍
		int sum = teamMap.values().stream().mapToInt(MatchingTeam::getMemberSize).sum();
		int checkMaxRoleNum = pvp ? maxRoleNum * 2 : maxRoleNum;
		if (checkMaxRoleNum - sum > maxRobotNum) {
			return Collections.emptyList();
		}

		Map<Integer, List<MatchingTeam>> integerListMap = classifyTeams(teamMap);
		if(integerListMap.isEmpty()) {
			return Collections.emptyList();
		}

		List<MatchTeamResultList> matchTeamResultLists = getMatchTeamResultLists(integerListMap);
		removeByMaxRobotNum(matchTeamResultLists);
		return matchTeamResultLists;
	}

	private void removeByMaxRobotNum(List<MatchTeamResultList> matchTeamResultLists) {
		// 不需要机器人的话 组成的队伍里面肯定都是真实玩家
		if(!needRobot) {
			return;
		}
		ArrayList<MatchTeamResultList> removeArrayList = new ArrayList<>();
		for (int i = 0; i < matchTeamResultLists.size(); i += 2) {
			MatchTeamResultList host = matchTeamResultLists.get(i);
			MatchTeamResultList guest = matchTeamResultLists.get(i + 1);
			if (pvp) {
				if (host.getRoleRobotNum() + guest.getRoleRobotNum() > maxRobotNum) {
					removeArrayList.add(host);
					removeArrayList.add(guest);
				}
			} else {
				if (host.getRoleRobotNum() > maxRobotNum) {
					removeArrayList.add(host);
				}
				if (guest.getRoleRobotNum() > maxRobotNum) {
					removeArrayList.add(guest);
				}
			}
		}
		matchTeamResultLists.removeAll(removeArrayList);
	}

	protected List<MatchTeamResultList> getMatchTeamResultLists(Map<Integer, List<MatchingTeam>> campTeams) {
		List<MatchTeamGroupResult> campMatchGroupResult = matchAllTeam(campTeams);
		return campMatchGroupResult.stream().map(MatchTeamResultList::new).collect(Collectors.toList());
	}

	/**
	 * 找到所有可以组成的队伍
	 */
	protected List<MatchTeamGroupResult> matchAllTeam(Map<Integer, List<MatchingTeam>> campTeams) {
		List<MatchTeamGroupResult> campMatchResult = new ArrayList<>();
		//优先给人数多的队伍分配
		for (int i = TEAM_MAX_NUM; i > 0; --i) {
			List<MatchingTeam> teams = campTeams.get(i);
			if (null == teams || teams.isEmpty())
				continue;
			for (MatchingTeam mt : teams) {
				if (alreadyMatchTeams.contains(mt.getTeamId()))
					continue;
				MatchTeamGroupResult matchResult = matchSingleTeam(mt, campTeams);
				if (null == matchResult)
					continue;
				campMatchResult.add(matchResult);

				alreadyMatchTeams.addAll(matchResult.teams.keySet());
			}
		}
		return campMatchResult;
	}


	/**
	 * 找到单组队友
	 */
	protected abstract MatchTeamGroupResult matchSingleTeam(MatchingTeam mTeam, Map<Integer, List<MatchingTeam>> classifyTeams);



	/**
	 * 获取一个队伍可以有的玩家人数
	 */
	public int getFullMaxRoleNum() {
		return maxRoleNum;
	}

	/**
	 * 创建机器人组
	 */
	protected abstract MatchTeamResultList createMatchTeamResultList();

	public MatchingPlayer createRobotPlayer() {
		return new MatchingPlayer(0);
	}

	protected MatchTeamGroupResult fillRobotTeam(MatchTeamGroupResult result) {
		result.fillDutyRobotTeam(this);
		return result;
	}

	protected MatchTeamGroupResult createMatchTeamGroupResult(){
		// 如果地图没有规定 dutyMap
		if (dutyMap.isEmpty()) {
			return new MatchTeamGroupResult();
		}
		return new MatchDutyTeamGroupResult(needRobot, dutyMap);
	}


	private Map<Integer, Integer> randRealDuty(DutyPlayerCount dutyLimit, int maxEnterPer) {
		if(dutyLimit == null || dutyLimit.isEmpty()) {
			return Collections.emptyMap();
		}
		int tempMaxEnterPer = maxEnterPer;
		Map<Integer, Integer> realDutyMap = new HashMap<>();
		for (DungeonDuty dd : DungeonDuty.values()) {
			Range<Integer> range = dutyLimit.getDutyRange(dd.getId());
			if (range == null) {
				continue;
			}
			int randValue = Math.min(Misc.getRandomBetween(range.getKey(), range.getValue()), tempMaxEnterPer);
			tempMaxEnterPer -= randValue;
			realDutyMap.put(dd.getId(), randValue);
			if (tempMaxEnterPer <= 0)
				break;
		}
		if (tempMaxEnterPer > 0) {
			realDutyMap.put(DungeonDuty.LIGHT.getId(), realDutyMap.get(DungeonDuty.LIGHT.getId()) + tempMaxEnterPer);
		}
		return findTeamDuty(realDutyMap, maxEnterPer);
	}

	/**
	 * 将需要人数大于5人的地图进行处理;分成每个队伍需要的职责
	 */
	private Map<Integer, Integer> findTeamDuty(Map<Integer, Integer> dutyMap, int maxEnterPer) {
		int ratio = maxEnterPer / getFullMaxRoleNum();
		if (ratio <= 1) {
			return dutyMap;
		}

		int needEnterPer = TEAM_MAX_NUM;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (Map.Entry<Integer, Integer> entry : dutyMap.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if (key == DungeonDuty.LIGHT.getId()) {
				continue;
			}

			int splitNum = value / ratio;
			splitNum = Math.max(1, splitNum);
			needEnterPer -= splitNum;
			map.put(key, splitNum);
		}

		if (needEnterPer > 0) {
			map.put(DungeonDuty.LIGHT.getId(), map.getOrDefault(DungeonDuty.LIGHT.getId(), 0) + needEnterPer);
		}
		return map;
	}

	/**
	 * 整合所有的正在匹配的队伍数据,将其按照队伍人数分类,将5个人，4个人.....等等分类存储起来key：为队伍人数,value：队伍信息
	 *
	 * @return Map<Integer, List < MatchTeam>>
	 */
	private Map<Integer, List<MatchingTeam>> classifyTeams(Map<Long, MatchingTeam> teamMap) {
		return teamMap.values().stream().collect(Collectors.groupingBy(MatchingTeam::getMemberSize, Collectors.toList()));
	}

	public Map<Integer, Integer> getDutyMap() {
		return dutyMap;
	}
}
