package com.youxi.match.game.match.entity;

import com.youxi.match.game.match.matchType.AbsTeamMatching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 11:28
 * @Description:
 */
public class MatchTeamGroupResult {
	public Map<Long, MatchingTeam> teams = new HashMap<>();
	protected int roleRobotNum;
	protected int realRoleNum;
	protected int robotNum;
	protected int realRoleTeamNum;

	public MatchTeamGroupResult() {
	}

	public boolean checkValid(MatchingTeam matchingTeam) {
		return true;
	}

	public void addTeams(MatchingTeam team) {
		MatchingTeam put = teams.put(team.getTeamId(), team);
		if(put != null) {
			return;
		}

		if(!team.isRobot()) {
			realRoleTeamNum++;
		}
		for (MatchingPlayer player : team.getMembers().values()) {
			this.roleRobotNum++;
			if (player.isRobot()) {
				this.robotNum++;
			} else {
				this.realRoleNum++;
			}
		}
	}

	public MatchingTeam removeTeam(long teamId) {
		MatchingTeam remove = teams.remove(teamId);
		if(remove == null) {
			return null;
		}

		if(!remove.isRobot()) {
			realRoleTeamNum--;
		}
		for (MatchingPlayer value : remove.getMembers().values()) {
			this.roleRobotNum--;
			if (value.isRobot()) {
				this.robotNum--;
			} else {
				this.realRoleNum--;
			}
		}
		return remove;
	}

	public List<MatchingTeam> removeRobotTeam(int num) {
		List<MatchingTeam> removeRobotTeam = teams.values().stream().filter(MatchingTeam::isRobot).limit(num).collect(Collectors.toList());
		removeRobotTeam.forEach(a -> removeTeam(a.getTeamId()));
		if(removeRobotTeam.size() != num) {
			throw new RuntimeException("removeRobotTeam.size != num!!!");
		}
		return removeRobotTeam;
	}

	public void merge(MatchTeamGroupResult result) {
		for (MatchingTeam value : result.teams.values()) {
			addTeams(value);
		}
	}

	public void fillDutyRobotTeam(AbsTeamMatching teamMatching) {
		if (getRoleRobotNum() >= teamMatching.getFullMaxRoleNum()) {
			return;
		}

		int need = teamMatching.getFullMaxRoleNum()- getRoleRobotNum();
		for (int i = 0; i < need; i++) {
			MatchingTeam matchingTeam = new MatchingTeam(teamMatching.createRobotPlayer());
			addTeams(matchingTeam);
		}
	}

	public Map<Long, MatchingTeam> getTeams() {
		return teams;
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
