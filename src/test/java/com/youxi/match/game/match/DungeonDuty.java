package com.youxi.match.game.match;

/**
 * a副本职责
 * 
 * @author Eric.Ma
 *
 */
public enum DungeonDuty {
	CARRIER(4), // 装甲兵
	HEAVY(3), // 重步兵
	RECONBOT(2), // 后勤兵
	LIGHT(1), // 轻步兵
	// 以上顺序遍历即为递补规则，下面补上面
	;

	private int id;

	private DungeonDuty(int id) {
		this.id = id;
	}

	public static DungeonDuty parse(int id) {
		for (DungeonDuty dt : DungeonDuty.values()) {
			if (dt.id == id)
				return dt;
		}
		return null;
	}

	public DungeonDuty getPriorityDuty() {
		return parse(this.id + 1);
	}

	public int getId() {
		return id;
	}

	public static void main(String[] args) {
		for (DungeonDuty dt : DungeonDuty.values()) {
			System.err.println(dt.id);
		}
	}

}
