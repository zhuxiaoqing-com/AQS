package com.youxi.match.game.match.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 11:28
 * @Description:
 */
public class MatchTeamResultList {
	public List<MatchTeamGroupResult> resultList = new ArrayList<>();
	protected int roleRobotNum;
	protected int realRoleNum;
	protected int robotNum;
	protected int realRoleTeamNum;

	public MatchTeamResultList() {
	}

	public MatchTeamResultList(MatchTeamGroupResult result) {
		add(result);
	}

	public MatchTeamResultList(MatchTeamResultList resultList) {
		for (MatchTeamGroupResult result : resultList.resultList) {
			add(result);
		}
	}

	public void add(MatchTeamGroupResult result) {
		resultList.add(result);
		this.roleRobotNum += result.getRoleRobotNum();
		this.realRoleNum += result.getRealRoleNum();
		this.robotNum += result.getRobotNum();
		this.realRoleTeamNum += result.getRealRoleTeamNum();
	}

	public void clear() {
		resultList.clear();
		roleRobotNum = 0;
		realRoleNum = 0;
		robotNum = 0;
		realRoleTeamNum = 0;
	}

	public Map<Long, MatchingTeam> getMatchingTeam() {
		Map<Long, MatchingTeam> teams = new HashMap<>();
		for (MatchTeamGroupResult result : resultList) {
			teams.putAll(result.getTeams());
		}
		return teams;
	}

	public MatchTeamResultList copy() {
		return new MatchTeamResultList(this);
	}

	public MatchTeamGroupResult toMatchTeamGroupResult() {
		if (resultList.isEmpty()) {
			return new MatchTeamGroupResult();
		}

		MatchTeamGroupResult result = resultList.get(0);
		for (int i = 1; i < resultList.size(); i++) {
			result.merge(resultList.get(i));
		}
		return result;
	}

	public List<MatchTeamGroupResult> getResultList() {
		return resultList;
	}

	public int getRoleRobotNum() {
		return roleRobotNum;
	}

	public int getRealRoleNum() {
		return realRoleNum;
	}

	public int getRobotNum() {
		return robotNum;
	}

	public int getRealRoleTeamNum() {
		return realRoleTeamNum;
	}
}
