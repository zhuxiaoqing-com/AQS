package com.youxi.match.game.match.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 玩家匹配对象
 * 
 * @author Eric.Ma
 *
 */
public class MatchingTeam {
	private static int autoIncrTeamId;

	private long teamId;
	private Map<Long, MatchingPlayer> members = new HashMap<>();
	private List<Integer> dungeonList;
	private boolean team; // 是否是队伍
	private boolean robot; // 是否是机器人

	private long matchTime = System.currentTimeMillis();

	public MatchingTeam() {
	}

	public MatchingTeam(MatchingPlayer player) {
		this.teamId = findNextTempTeam();
		this.members.put(0L, player);
		player.setTeamId(teamId);
		this.dungeonList = player.getDungeonList();
		this.robot = player.isRobot();
	}

/*	public MatchingTeam(SsCenter.LB2MBTeamMatch pb) {
		this.teamId = pb.getTeamId();
		for (SsCenter.PBTeamMatchMember pbMember : pb.getTeamMemberList()) {
			MatchingPlayer matchingPlayer = new MatchingPlayer(pbMember.getPlayerInfo().toBuilder(), pbMember.getReadyInfo().getDutyList(), pb.getDungeonIdList());
			matchingPlayer.setTeamId(teamId);
			members.put(matchingPlayer.getPlayerId(), matchingPlayer);
		}
		this.dungeonList = Collections.unmodifiableList(pb.getDungeonIdList());
		this.team = true;
	}*/


	private static int findNextTempTeam() {
		++autoIncrTeamId;

		if (autoIncrTeamId <= 0) {
			autoIncrTeamId = 1;
		}
		return autoIncrTeamId;
	}

	public long getTeamId() {
		return teamId;
	}

	public Map<Long, MatchingPlayer> getMembers() {
		return members;
	}

	public List<Integer> getDungeonList() {
		return dungeonList;
	}

	public long getMatchTime() {
		return matchTime;
	}

	public int getMemberSize() {
		return members.size();
	}

	public int getRealRoleMemberSize() {
		return isRobot() ? 0 : members.size();
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public void setMembers(Map<Long, MatchingPlayer> members) {
		this.members = members;
	}

	public void setDungeonList(List<Integer> dungeonList) {
		this.dungeonList = dungeonList;
	}

	public void setMatchTime(long matchTime) {
		this.matchTime = matchTime;
	}

	public boolean isTeam() {
		return team;
	}

	public boolean isRobot() {
		return robot;
	}

	@Override
	public String toString() {
		return "MatchingTeamSize{" +
				"membersSize=" + members.size() +
				'}';
	}
}
