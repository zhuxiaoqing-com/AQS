package com.youxi.match.game.match.matchType;

import com.youxi.match.game.match.DutyPlayerCount;
import com.youxi.match.game.match.entity.MatchTeamGroupResult;
import com.youxi.match.game.match.entity.MatchTeamResultList;
import com.youxi.match.game.match.entity.MatchingTeam;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 14:31
 * @Description:
 */
public class CommonTeamMatching extends AbsTeamMatching {
	public CommonTeamMatching(int maxRoleNum, int maxRobotNum, boolean needRobot) {
		super(maxRoleNum, maxRobotNum, needRobot);
	}

	public CommonTeamMatching(int maxRoleNum, int maxRobotNum, boolean needRobot, DutyPlayerCount count, boolean pvp) {
		super(maxRoleNum, maxRobotNum, needRobot, count, pvp);
	}


	/**
	 * 匹配分配组情况
	 * 当队伍中有5个人时,直接放到类型为5的分组中
	 * 当队伍中有4个人时,组合情况就是 {4,1}
	 * 当队伍中有3个人时,组合情况就是{3,2},{3,1,1}
	 * 当队伍中有2个人时,组合情况就是{2,2,1},{2,1,1,1}
	 * 当队伍中有1个人时,组合情况及时{1,1,1,1,1}
	 */
	protected MatchTeamGroupResult matchSingleTeam(MatchingTeam mTeam, Map<Integer, List<MatchingTeam>> classifyTeams) {
		MatchTeamGroupResult result = _matchSingleTeam(mTeam, classifyTeams);

		if(result.getRoleRobotNum() >= maxRoleNum) {
			return result;
		}

		if (needRobot) {
			// 填充机器人
			fillRobotTeam(result);
			return result;
		}

		return null;
	}

	/**
	 * 创建机器人组
	 * @return
	 */
	protected MatchTeamResultList createMatchTeamResultList() {
		MatchTeamResultList matchTeamResultList = new MatchTeamResultList();
		matchTeamResultList.add(fillRobotTeam(createMatchTeamGroupResult()));
		return matchTeamResultList;
	}


	/**
	 * 为当前队伍，填充数据 (如当前队伍中有两人,此时需要再从队伍人数分组中找3个填满,这个3个又可以进一步分为，找 3个一个人的队伍，或者找1个一个的队伍,一个两个人的队伍)
	 *
	 * @param mTeam             当期队伍
	 * @param campClassifyTeams 所有的分组队伍数据
	 */
	private MatchTeamGroupResult _matchSingleTeam(MatchingTeam mTeam, Map<Integer, List<MatchingTeam>> campClassifyTeams) {
		Set<Long> cacheUsedTeam = new HashSet<>();//缓存已经分配过的队
		cacheUsedTeam.add(mTeam.getTeamId());// 伍
		MatchTeamGroupResult result = createMatchTeamGroupResult();
		result.addTeams(mTeam);

		int needRoleNum = maxRoleNum - result.getRoleRobotNum();
		outer:for (int i = mTeam.getMemberSize(); i > 0; --i) {
			List<MatchingTeam> teams = campClassifyTeams.get(i);
			if (null == teams || teams.isEmpty()) {
				continue;
			}
			for (MatchingTeam checkTeam : teams) {
				if (needRoleNum <= 0) {
					break outer;
				}
				if (i > needRoleNum) {
					break;
				}
				if (cacheUsedTeam.contains(checkTeam.getTeamId()) || alreadyMatchTeams.contains(checkTeam.getTeamId()))
					continue;
				result.addTeams(checkTeam);

				cacheUsedTeam.add(checkTeam.getTeamId());
				needRoleNum -= checkTeam.getMemberSize();
			}
		}
		return result;
	}

}
