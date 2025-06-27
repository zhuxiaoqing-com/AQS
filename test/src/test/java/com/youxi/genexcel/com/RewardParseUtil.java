package com.youxi.genexcel.com;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/12 13:49
 * @Description:
 */
public class RewardParseUtil {


	public static <K> List<K> parseStrList(String rewardGroupStr, String split, Function<String, K> function) {
		if (rewardGroupStr == null || rewardGroupStr.isEmpty()) {
			return Collections.emptyList();
		}
		String[] param = rewardGroupStr.split(split);
		return Arrays.stream(param).map(function).collect(Collectors.toList());
	}



	public static <K,V> Map<K, V> parseRewardStrKV(String rewardGroupStr, Function<String ,K> k, Function<String, V> v) {
		if (rewardGroupStr == null || rewardGroupStr.trim().isEmpty()) {
			return Collections.emptyMap();
		}
		String[] rewardStrArray = rewardGroupStr.split(SplitSymbolConst.JingHao);
		HashMap<K, V> rewardMap = new HashMap<>();

		for (String rewardStr : rewardStrArray) {
			String[] param = rewardStr.split(SplitSymbolConst.FenHao);
			if (param.length < 2) {
				continue;
			}
			K itemId = k.apply(param[0]);
			V itemNum = v.apply(param[1]);
			rewardMap.put(itemId, itemNum);
		}
		return rewardMap;
	}

	public static void main(String[] args) {
		parseStrList("","",String::valueOf);
		parseStrList("","",Integer::parseInt);
		parseStrList("","",Double::parseDouble);
		parseStrList("","",Boolean::parseBoolean);
	}
}
