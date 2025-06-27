package com.youxi.match.game.match;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * a 职责对应人数范围
 * 
 * @author Eric.Ma
 *
 */
public class DutyPlayerCount {

	private Map<Integer, Range<Integer>> duty2Range = new HashMap<>();

	public DutyPlayerCount(List<String> dutyList) {
		for (String str : dutyList) {
			String[] raw = str.split("#");
			duty2Range.put(Integer.parseInt(raw[0]), new Range<>(Integer.parseInt(raw[1]), Integer.parseInt(raw[2])));
		}
	}

	/**
	 * a获取职责最小人数
	 * 
	 * @param duty
	 * @return
	 */
	public int getDutyMin(int duty) {
		Range<Integer> r = this.duty2Range.get(duty);
		return r == null ? Integer.MAX_VALUE : (int) r.getKey();
	}

	/**
	 * a获取职责最大人数
	 * 
	 * @param duty
	 * @return
	 */
	public int getDutyMax(int duty) {
		Range<Integer> r = this.duty2Range.get(duty);
		return r == null ? 0 : (int) r.getValue();
	}

	public Range<Integer> getDutyRange(int duty) {
		return this.duty2Range.get(duty);
	}
	
	public Set<Integer> listDuty(){
		return duty2Range.keySet();
	}

	public boolean isEmpty() {
		return duty2Range.isEmpty();
	}

}
