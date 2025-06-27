package com.youxi.match.game.match.entity.gmentity;

import com.youxi.match.game.match.entity.MatchTeamResultList;
import com.youxi.match.game.match.entity.MatchingTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/6 18:09
 * @Description:
 */
public class GMMatchPKTeam {
	private List<Integer> left = new ArrayList<>();
	private List<Integer> right = new ArrayList<>();

	public GMMatchPKTeam(MatchTeamResultList left, MatchTeamResultList right) {
		List<Integer> gmLeft = left.resultList.stream().flatMap(a -> a.getTeams().values().stream()).filter(a -> !a.isRobot()).map(MatchingTeam::getMemberSize).collect(Collectors.toList());
		List<Integer> gmRight = right.resultList.stream().flatMap(a -> a.getTeams().values().stream()).filter(a -> !a.isRobot()).map(MatchingTeam::getMemberSize).collect(Collectors.toList());
		this.left = gmLeft;
		this.right = gmRight;
	}


	public GMMatchPKTeam(List<Integer> left, List<Integer> right) {
		this.left = left;
		this.right = right;
	}

	public List<Integer> getLeft() {
		return left;
	}

	public void setLeft(List<Integer> left) {
		this.left = left;
	}

	public List<Integer> getRight() {
		return right;
	}

	public void setRight(List<Integer> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		//return FastJsonUtils.toJSONString(this);
		return "";
	}
}
