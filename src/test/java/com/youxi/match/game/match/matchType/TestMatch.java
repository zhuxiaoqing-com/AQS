package com.youxi.match.game.match.matchType;

import com.youxi.building.Misc;
import com.youxi.match.game.match.DungeonDuty;
import com.youxi.match.game.match.DutyPlayerCount;
import com.youxi.match.game.match.entity.MatchTeamResultList;
import com.youxi.match.game.match.entity.MatchingPlayer;
import com.youxi.match.game.match.entity.MatchingTeam;
import com.youxi.match.game.match.entity.gmentity.GMMatchPKTeam;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.youxi.match.game.match.matchType.AbsTeamMatching.TEAM_MAX_NUM;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 18:03
 * @Description:
 */

public class TestMatch {
	/**
	 * 测试用例
	 */
	public static void main(String[] args) {
	/*	int a = 1000;
		for (int i = 0; i < a; i++) {
			List<MatchTeamResultList> matchTeamResultLists = testTeamDutyMatching(2, 10, true, "1#1#4;2#1#2;3#1#2;4#1#2", 2, true);
			for (MatchTeamResultList matchTeamResultList : matchTeamResultLists) {
				if (matchTeamResultList.getRoleRobotNum() != 10) {`
					System.out.println();
				}
			}
		}*/

	/*	TestMatchResult	result1 = testTeamDutyMatching(14, Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), 10, true, true, "1#1#4;2#1#2;3#1#2;4#1#2");
		TestMatchResult result2 = testTeamDutyMatching(14, Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), 5, true, true, "1#1#4;2#1#2;3#1#2;4#1#2");
		TestMatchResult result3 = testTeamDutyMatching(3, Arrays.asList(3, 2, 1), 10, true, true, "1#1#4;2#1#2;3#1#2;4#1#2");
		TestMatchResult result4 = testTeamDutyMatching(3, Arrays.asList(3, 2, 1), 5, true, true, "1#1#4;2#1#2;3#1#2;4#1#2");
		TestMatchResult result5 = testTeamDutyMatching(30000, null, 5, true, true, "1#1#4;2#1#2;3#1#2;4#1#2");
*/

	/*	List<Integer> list = Arrays.asList(2, 5, 1, 2, 3, 3, 3, 1, 5, 3, 3, 2, 2, 5, 3, 2, 2, 2, 1, 3, 5);
		TestMatchResult result5 = testTeamDutyMatching(list.size(), list, 20, true, true, "1#1#8;2#1#4;3#1#4;4#1#4");
		System.out.println(result5);*/

	/*	List<Integer> list = Arrays.asList(3, 2, 1, 1, 1);
		TestMatchResult result5 = testTeamDutyMatching(list.size(), list, 5, true, true, "1#1#8;2#1#4;3#1#4;4#1#4");
		System.out.println(result5.toString());*/
		/*Supplier<TestMatchResult> supplier = () ->
				testTeamDutyMatching(30000, null, 20, true, true, "1#1#4;2#1#2;3#1#2;4#1#2");
		testPrintDetailData(supplier, 50);
		System.out.println();*/

	//	List<MatchTeamResultList> matchTeamResultLists = testTeamMatching(3, Arrays.asList(4, 3, 2), 5, true);
		//TestMatchResult testMatchResult = testTeamGruop5v5Matching(1, Arrays.asList(1),10, true, false, "");
		TestMatchResult testMatchResult2 = testTeamGruop5v5Matching(1, Arrays.asList(1),10, true, false, "1#1#4;2#1#2;3#1#2;4#1#2");
		List<MatchTeamResultList> matchTeamResultLists = testCommonTeamMaching(1, Arrays.asList(1), 10, true);

		System.out.println();
	}


	private static void testPrintDetailData(Supplier<TestMatchResult> supplier, int count) {
		int notBalanceCount = 0;
		int notBalanceMax = 0;
		int totalCostMill = 0;
		int totalFirstAvg = 0;
		int totalSecondAvg = 0;

		List<CheckTeamBalanceResult> notBalanceList = new ArrayList<>();

		int num = count;
		for (int i = 0; i < num; i++) {
			TestMatchResult testMatchResult = supplier.get();
			totalCostMill += testMatchResult.costMill;
			totalFirstAvg += testMatchResult.firstAvg;
			totalSecondAvg += testMatchResult.secondAvg;
			notBalanceCount += testMatchResult.balanceResult.balanceList.size();
			int tempNotBalanceMax = testMatchResult.balanceResult.balanceList.stream()
					.mapToInt(a -> Math.abs(a.get(0).getRealRoleNum() - a.get(1).getRealRoleNum()))
					.max().orElse(0);
			notBalanceMax = Math.max(tempNotBalanceMax, notBalanceMax);

			if (!testMatchResult.balanceResult.balanceList.isEmpty()) {
				notBalanceList.add(testMatchResult.balanceResult);
			}
		}

		System.out.println("avgCostMill -----" + totalCostMill / num);
		System.out.println("avgFirstAvg -----" + totalFirstAvg / num);
		System.out.println("avgSecondAvg -----" + totalSecondAvg / num);
		System.out.println("notBalanceCount -----" + notBalanceCount);
		System.out.println("notBalanceMax -----" + notBalanceMax);
		System.out.println("notBalanceTeam -----" + notBalanceList);
	}

	private static CheckTeamBalanceResult checkTeamBalance(List<MatchTeamResultList> list, int threshold) {
		// count -> index
		CheckTeamBalanceResult result = new CheckTeamBalanceResult();

		for (int i = 0; i < list.size(); i += 2) {
			MatchTeamResultList r1 = list.get(i);
			MatchTeamResultList r2 = list.get(i + 1);

			int abs = Math.abs(r1.getRealRoleNum() - r2.getRealRoleNum());
			if (abs >= threshold) {
				result.balanceList.add(Arrays.asList(r1, r2));
			}
		}
		if (!result.balanceList.isEmpty()) {
			String a = "threshold: %s 匹配人数不平衡 !!!  不平衡队伍数量 %s   ---- %s";
			String format = String.format(a, threshold, result.balanceList.size(), result);
			System.out.println(format);
		}
		return result;
	}


	public static TestMatchResult testTeamGruop5v5Matching(int createTeamNum, List<Integer> teamMemberNumList, int dungeonMaxRoleNum, boolean needRobot, boolean allDuty, String dutyStr) {
		LinkedHashMap<Long, MatchingTeam> testData = createTestData(createTeamNum, teamMemberNumList, allDuty);
		return testTeamGruop5v5Matching(testData, dungeonMaxRoleNum, needRobot, dutyStr);
	}

	private static TestMatchResult testTeamGruop5v5Matching(LinkedHashMap<Long, MatchingTeam> testData, int dungeonMaxRoleNum, boolean needRobot, String dutyStr) {
		long now = System.currentTimeMillis();
		List<String> list = Collections.emptyList();//RewardParseUtil.parseStrListToString(dutyStr);
		DutyPlayerCount dutyPlayerCount = new DutyPlayerCount(list);
		TestMatchResult result = new TestMatchResult();
		Group5V5TeamMatching teamDutyMatching = new Group5V5TeamMatching(dungeonMaxRoleNum, dungeonMaxRoleNum, needRobot, dutyPlayerCount, true) {
			@Override
			public MatchingPlayer createRobotPlayer() {
				return new MatchingPlayer(true, true);
			}

			@Override
			protected boolean pvpTeamListAverage(MatchTeamResultList oldList1, MatchTeamResultList oldList2) {
				boolean b = super.pvpTeamListAverage(oldList1, oldList2);
				if (b) {
					result.firstAvg++;
				}
				return b;
			}

			@Override
			protected boolean secondPvpTeamListAverage(MatchTeamResultList oldList1, MatchTeamResultList oldList2) {
				MatchTeamResultList copy = oldList1.copy();
				int roleRobotNum = copy.getRoleRobotNum();

				boolean b = super.secondPvpTeamListAverage(oldList1, oldList2);
				if (b) {
					result.secondAvg++;

					if (oldList1.getRoleRobotNum() != roleRobotNum || oldList2.getRoleRobotNum() != roleRobotNum) {
						System.out.println("平衡错误！！！！！！！！！！！！！！！！！！！,平衡后人数不对了");
					}
				}
				return b;
			}
		};
		List<MatchTeamResultList> matching = teamDutyMatching.matching(testData);
		System.out.println(teamDutyMatching.getDutyMap());
		System.out.println("匹配成功 n " + matching.size() + " 对");
		long end = System.currentTimeMillis();
		System.out.println("耗时:" + (end - now) + "毫秒!");

		result.matching = matching;
		result.balanceResult = checkTeamBalance(matching, 2);
		result.costMill = end - now;

		return result;
	}


	private static List<MatchTeamResultList> testCommonTeamMaching(int createTeamNum, List<Integer> teamMemberNumList, int dungeonMaxRoleNum, boolean needRobot) {
		LinkedHashMap<Long, MatchingTeam> testData = createTestData(createTeamNum, teamMemberNumList, false);
		long now = System.currentTimeMillis();
		List<MatchTeamResultList> matching = new CommonTeamMatching(dungeonMaxRoleNum, dungeonMaxRoleNum, needRobot) {
			@Override
			public MatchingPlayer createRobotPlayer() {
				return new MatchingPlayer(true, true);
			}
		}.matching(testData);
		System.out.println("匹配成功 n " + matching.size() + " 对");
		long end = System.currentTimeMillis();
		System.out.println("耗时:" + (end - now) + "毫秒!");

		for (MatchTeamResultList matchTeamResultList : matching) {
			if (matchTeamResultList.getRoleRobotNum() != dungeonMaxRoleNum) {
				System.out.println("人数不对！！！  匹配数量 " + matchTeamResultList.getRoleRobotNum() + "  需要数量 "+ dungeonMaxRoleNum);
			}
		}
		return matching;
	}


	private static LinkedHashMap<Long, MatchingTeam> createTestData(int createTeamNum, List<Integer> teamMemberNumList, boolean allDuty) {
		long baseId = System.currentTimeMillis() / 1000;
		int index = 0;

		if (teamMemberNumList == null) {
			teamMemberNumList = Collections.emptyList();
		}

		LinkedHashMap<Long, MatchingTeam> map = new LinkedHashMap<>();
		for (int i = 0; i < createTeamNum; ++i) {
			MatchingTeam maInfo = new MatchingTeam();
			maInfo.setTeamId(baseId + index);
			map.put(maInfo.getTeamId(), maInfo);
			int teamMemberNum = 0;
			if (i < teamMemberNumList.size()) {
				teamMemberNum = teamMemberNumList.get(i);
			}

			if (teamMemberNum <= 0) {
				teamMemberNum = Misc.getRandomBetween(1, TEAM_MAX_NUM);
			}
			for (int j = 0; j < teamMemberNum; ++j) {
				MatchingPlayer matchrole = new MatchingPlayer(true, false);
				List<Integer> collect = Arrays.stream(DungeonDuty.values()).map(DungeonDuty::getId).collect(Collectors.toList());
				if (allDuty) {
					matchrole.getDutyList().addAll(collect);
				} else {
					//matchrole.getDutyList().add(Misc.getRandom(collect));
				}
				maInfo.getMembers().put((long) j, matchrole);
			}
			index++;
		}
		return map;
	}

	public static class CheckTeamBalanceResult {
		public List<List<MatchTeamResultList>> balanceList = new ArrayList<>();

		@Override
		public String toString() {
			ArrayList<GMMatchPKTeam> gmMatchPKTeamList = new ArrayList<>();
			for (List<MatchTeamResultList> resultLists : balanceList) {
				gmMatchPKTeamList.add(new GMMatchPKTeam(resultLists.get(0), resultLists.get(1)));
			}
			return "";//FastJsonUtils.toJSONString(gmMatchPKTeamList);
		}
	}

	public static class TestMatchResult {
		public CheckTeamBalanceResult balanceResult;
		public List<MatchTeamResultList> matching = new ArrayList<>();
		public long costMill;
		public int firstAvg;
		public int secondAvg;

		public TestMatchResult() {
		}

		@Override
		public String toString() {
			ArrayList<GMMatchPKTeam> gmMatchPKTeamList = new ArrayList<>();
			for (int i = 0; i < this.matching.size(); i += 2) {
				MatchTeamResultList left = this.matching.get(i);
				MatchTeamResultList right = this.matching.get(i + 1);
				GMMatchPKTeam gmMatchPKTeam = new GMMatchPKTeam(left, right);
				gmMatchPKTeamList.add(gmMatchPKTeam);
			}

			return "";//FastJsonUtils.toJSONString(gmMatchPKTeamList);
		}
	}
}
