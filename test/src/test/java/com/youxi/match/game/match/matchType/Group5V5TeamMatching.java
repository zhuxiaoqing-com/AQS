package com.youxi.match.game.match.matchType;

import com.youxi.match.game.match.DutyPlayerCount;
import com.youxi.match.game.match.entity.MatchTeamGroupResult;
import com.youxi.match.game.match.entity.MatchTeamResultList;
import com.youxi.match.game.match.entity.MatchingTeam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 14:31
 * @Description:
 */
public class Group5V5TeamMatching extends AbsTeamMatching {


	public Group5V5TeamMatching(int maxRoleNum, int maxRobotNum, boolean needRobot, DutyPlayerCount count, boolean pvp) {
		super(maxRoleNum, maxRobotNum, needRobot, count, pvp);
	}

	/**
	 * 将分类整合后的队伍进一步的去整合,将team中不足5人的队伍补满。
	 */
	protected List<MatchTeamResultList> getMatchTeamResultLists(Map<Integer, List<MatchingTeam>> campTeams) {
		List<MatchTeamGroupResult> result = matchAllTeam(campTeams);
		int ratio = maxRoleNum / getFullMaxRoleNum();

		List<MatchTeamResultList> finalResultList = new ArrayList<>();
		for (int i = 0; i < result.size(); i += ratio) {
			MatchTeamResultList resultList = new MatchTeamResultList();
			for (int j = i; j < ratio + i; j++) {
				MatchTeamGroupResult tempResult;
				if (j >= result.size()) {
					if (!needRobot) {
						break;
					}
					tempResult = fillRobotTeam(createMatchTeamGroupResult());
				} else {
					tempResult = result.get(j);
				}
				resultList.add(tempResult);
			}
			if (resultList.getRoleRobotNum() >= maxRoleNum) {
				finalResultList.add(resultList);
			}
		}


		// pvp队伍填充
		pvpTeamProcess(finalResultList);

		return finalResultList;
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
		List<MatchTeamGroupResult> allTypeMatchResult = new ArrayList<>();
		switch (mTeam.getMemberSize()) {
			case 5:
				checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList()));
				break;
			case 4:
				checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList(1)));
				break;
			case 3:
				boolean success = checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList(2)));
				if (!success || needRobot) {
					checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList(1, 1)));
				}
				break;
			case 2:
				success = checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList(2, 1)));
				if (!success || needRobot) {
					checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList(1, 1, 1)));
				}
				break;
			case 1:
				checkAddMatchGroupResult(allTypeMatchResult, _matchSingleTeam(mTeam, classifyTeams, Arrays.asList(1, 1, 1, 1)));
				break;
			default:
				break;
		}
		if (allTypeMatchResult.isEmpty()) {
			return null;
		}

		MatchTeamGroupResult max = allTypeMatchResult.stream().max(Comparator.comparingInt(MatchTeamGroupResult::getRoleRobotNum)).orElse(null);

		if (needRobot) {
			// 填充机器人
			fillRobotTeam(max);
		}
		return max;
	}

	/**
	 * 创建机器人组
	 *
	 * @return
	 */
	@Override
	public MatchTeamResultList createMatchTeamResultList() {
		MatchTeamResultList matchTeamResultList = new MatchTeamResultList();
		int ratio = maxRoleNum / TEAM_MAX_NUM;

		for (int i = 0; i < ratio; i++) {
			matchTeamResultList.add(fillRobotTeam(createMatchTeamGroupResult()));
		}
		return matchTeamResultList;
	}

	@Override
	public int getFullMaxRoleNum() {
		return TEAM_MAX_NUM;
	}

	private void pvpTeamProcess(List<MatchTeamResultList> finalResultList) {
		// 如果是奇数并且是队伍对抗
		if (!pvp) {
			return;
		}

		// pvp 队伍数量对齐填充
		pvpFull(finalResultList);

		if (finalResultList.size() < 2) {
			return;
		}

		finalResultList.sort(Comparator.comparingInt(MatchTeamResultList::getRealRoleNum).reversed());

		for (int i = 0; i < finalResultList.size(); i += 2) {
			MatchTeamResultList r1 = finalResultList.get(i);
			MatchTeamResultList r2 = finalResultList.get(i + 1);
			pvpTeamListAverage(r1, r2);
		}

		// 二次平衡只平衡最后一只队伍
		secondPvpTeamListAverage(finalResultList.get(finalResultList.size() - 2), finalResultList.get(finalResultList.size() - 1));
	}

	/**
	 * pvp 队伍数量对齐填充
	 */
	private void pvpFull(List<MatchTeamResultList> finalResultList) {
		// 偶数队伍不需要平衡 因为已经平衡了
		if ((finalResultList.size() & 1) == 0) {
			return;
		}

		// 如果不需要机器人就删除一个队伍
		if (!needRobot) {
			finalResultList.remove(finalResultList.size() - 1);
			return;
		}
		// 需要机器人就添加一个全机器人队伍
		finalResultList.add(createMatchTeamResultList());
	}

	protected boolean pvpTeamListAverage(MatchTeamResultList oldList1, MatchTeamResultList oldList2) {
		MatchTeamResultList newList1 = new MatchTeamResultList();
		MatchTeamResultList newList2 = new MatchTeamResultList();


		List<MatchTeamGroupResult> collect = Stream.concat(oldList1.getResultList().stream(), oldList2.getResultList().stream())
				.sorted(Comparator.comparingInt(MatchTeamGroupResult::getRealRoleNum).reversed().thenComparingInt(MatchTeamGroupResult::getRealRoleTeamNum))
				.collect(Collectors.toList());

		for (MatchTeamGroupResult result : collect) {
			if (newList1.getRoleRobotNum() < newList2.getRoleRobotNum()) {
				newList1.add(result);
			} else {
				newList2.add(result);
			}
		}

		oldList1.clear();
		oldList2.clear();

		newList1.getResultList().forEach(oldList1::add);
		newList2.getResultList().forEach(oldList2::add);
		return true;
	}

	private boolean checkIsNeedAverage(MatchTeamResultList oldList1, MatchTeamResultList oldList2) {
		return Math.abs(oldList1.getRealRoleNum() - oldList2.getRealRoleNum()) >= 2;
	}


	/**
	 * 进行二次平衡队伍
	 * 人多的那方用人 去交换 人少那方的机器人
	 */
	protected boolean secondPvpTeamListAverage(MatchTeamResultList oldList1, MatchTeamResultList oldList2) {
		if (!checkIsNeedAverage(oldList1, oldList2)) {
			return false;
		}

		// 人数差
		int roleNumDiff = Math.abs(oldList1.getRealRoleNum() - oldList2.getRealRoleNum());

		MatchTeamResultList remTeamResultList = oldList1.getRealRoleNum() > oldList2.getRealRoleNum() ? oldList1 : oldList2;
		MatchTeamResultList remRobotResultList = oldList1.getRealRoleNum() > oldList2.getRealRoleNum() ? oldList2 : oldList1;

		// 可以交换的机器人数量
		int canSwapRobotNum = Math.min(remRobotResultList.getRoleRobotNum(), roleNumDiff / 2);
		if (canSwapRobotNum <= 0) {
			return false;
		}

		MatchTeamGroupResult remTeamResult = remTeamResultList.toMatchTeamGroupResult();
		MatchTeamGroupResult remRobotResult = remRobotResultList.toMatchTeamGroupResult();

		int finalCanSwapRobotNum = canSwapRobotNum;
		List<MatchingTeam> sortRemTeamList = remTeamResult.teams.values().stream()
				.filter(a -> !a.isRobot())
				.filter(a -> a.getMemberSize() <= finalCanSwapRobotNum)
				.sorted(Comparator.comparingInt(MatchingTeam::getMemberSize).reversed())
				.collect(Collectors.toList());


		for (MatchingTeam sortTeam : sortRemTeamList) {
			if (canSwapRobotNum <= 0) {
				break;
			}
			if (sortTeam.getMemberSize() > canSwapRobotNum) {
				continue;
			}

			canSwapRobotNum = canSwapRobotNum - sortTeam.getMemberSize();

			remTeamResult.removeTeam(sortTeam.getTeamId());
			remRobotResult.addTeams(sortTeam);

			List<MatchingTeam> removeRobotTeam = remRobotResult.removeRobotTeam(sortTeam.getMemberSize());
			removeRobotTeam.forEach(remTeamResult::addTeams);
		}

		remTeamResultList.clear();
		remRobotResultList.clear();

		remTeamResultList.add(remTeamResult);
		remRobotResultList.add(remRobotResult);
		return true;
	}





	private boolean checkAddMatchGroupResult(List<MatchTeamGroupResult> allTypeMatchResult, MatchTeamGroupResult result) {
		if (null == result)
			return false;
		allTypeMatchResult.add(result);
		return true;
	}

	/**
	 * 为当前队伍，填充数据 (如当前队伍中有两人,此时需要再从队伍人数分组中找3个填满,这个3个又可以进一步分为，找 3个一个人的队伍，或者找1个一个的队伍,一个两个人的队伍)
	 *
	 * @param mTeam             当期队伍
	 * @param campClassifyTeams 所有的分组队伍数据
	 * @param groupTypes        需要填充的数据
	 */
	private MatchTeamGroupResult _matchSingleTeam(MatchingTeam mTeam, Map<Integer, List<MatchingTeam>> campClassifyTeams, List<Integer> groupTypes) {
		Set<Long> cacheUsedTeam = new HashSet<>();//缓存已经分配过的队伍
		cacheUsedTeam.add(mTeam.getTeamId());
		MatchTeamGroupResult result = createMatchTeamGroupResult();
		// 如果要匹配的队伍都不合法的话
		if (!result.checkValid(mTeam)) {
			return null;
		}
		result.addTeams(mTeam);

		if (groupTypes.isEmpty()) {
			return result;
		}
		for (int groupType : groupTypes) {//根据group的信息,去找满足条件的数据
			List<MatchingTeam> checkFromList = campClassifyTeams.get(groupType);
			if (null == checkFromList || checkFromList.isEmpty()) {
				if (needRobot) {
					continue;
				} else {
					return null;
				}
			}
			MatchingTeam currSelectTeam = null;
			for (MatchingTeam checkTeam : checkFromList) {
				if (mTeam.getTeamId() == checkTeam.getTeamId())
					continue;
				if (cacheUsedTeam.contains(checkTeam.getTeamId()) || alreadyMatchTeams.contains(checkTeam.getTeamId()))
					continue;

				if (!result.checkValid(checkTeam)) {
					continue;
				}
				currSelectTeam = checkTeam;
				break;
			}
			if (currSelectTeam == null) {
				if (needRobot) {
					continue;
				} else {
					return null;
				}
			}
			cacheUsedTeam.add(currSelectTeam.getTeamId());
			result.addTeams(currSelectTeam);
		}

		if (cacheUsedTeam.isEmpty()) {
			if (needRobot) {
				return result;
			} else {
				return null;
			}
		}
		return result;
	}

}
