package com.youxi.match.game.match.entity;

import com.youxi.match.game.match.matchType.AbsTeamMatching;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 11:28
 * @Description:
 */
public class MatchDutyTeamGroupResult extends MatchTeamGroupResult {
	public Map<Integer, Integer> currNeedDuty;
	protected boolean needRobot;

	public MatchDutyTeamGroupResult(boolean needRobot, Map<Integer, Integer> currNeedDuty) {
		this.needRobot = needRobot;
		this.currNeedDuty = currNeedDuty;
	}

	public boolean checkValid(MatchingTeam matchingTeam) {
		return checkValid(matchingTeam, false);
	}

	public boolean checkValid(MatchingTeam matchingTeam, boolean add) {
		if (matchingTeam.getMemberSize() <= 0) {
			return false;
		}
		HashMap<Integer, Integer> tempCurrNeedDuty = new HashMap<>(currNeedDuty);
		for (MatchingPlayer value : matchingTeam.getMembers().values()) {
			boolean success = false;
			for (Integer duty : value.getDutyList()) {
				Integer count = tempCurrNeedDuty.get(duty);
				// 如果该副本没有要这种指责
				if (count == null) {
					continue;
				}
				// 如果没有指责了 并且不用机器人和补位
				if (!needRobot && count <= 0) {
					continue;
				}
				// 如果是要将其加入队伍 就设置其指责
				if (add) {
					value.setRealSelectDuty(duty);
				}

				// 如果当前职位已经被能扣了 就随机一个职位 -1
				if (count <= 0) {
					duty = tempCurrNeedDuty.entrySet().stream().filter(a -> a.getValue() > 0).findAny().map(Map.Entry::getKey).orElse(0);
				}
				tempCurrNeedDuty.merge(duty, -1, Integer::sum);
				success = true;
				break;
			}
			if (!success) {
				return false;
			}
		}
		if (add) {
			currNeedDuty = tempCurrNeedDuty;
		}
		return true;
	}


	public void addTeams(MatchingTeam team) {
		boolean success = checkValid(team, true);
		if (success) {
			super.addTeams(team);
		}
	}

	public void fillDutyRobotTeam(AbsTeamMatching teamMatching) {
		if (getRoleRobotNum() >= teamMatching.getFullMaxRoleNum()) {
			return;
		}

		for (Map.Entry<Integer, Integer> entry : currNeedDuty.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();
			for (int i = 0; i < value; i++) {
				MatchingPlayer matchingPlayer = teamMatching.createRobotPlayer();
				matchingPlayer.getDutyList().add(key);
				//	matchingPlayer.setRealSelectDuty(key);
				MatchingTeam matchingTeam = new MatchingTeam(matchingPlayer);
				addTeams(matchingTeam);
			}
		}
	}

}
