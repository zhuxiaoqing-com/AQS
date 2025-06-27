package com.youxi.match;

import com.example.TestArithmetic.snowflakeIdWorker.BitMap;
import com.example.common.wheelTimer.kafka.TimerTaskList;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/24 11:48
 * @Description:
 */
public class MatchingTeamInfo {
	public int serverId;
	public long teamid;
	public List<MatchingRoleInfo> roleinfos;
}
