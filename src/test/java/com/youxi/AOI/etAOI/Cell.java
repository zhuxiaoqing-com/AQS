package com.youxi.AOI.etAOI;

import java.util.HashMap;
import java.util.Map;

public class Cell {
	public Long Id;
	// 处在这个 cell 的单位
	public Map<Long, AOIEntity> AOIUnits = new HashMap<>();
	// 订阅了这个cell的进入事件 进入视野遍历他
	public Map<Long, AOIEntity> SubsEnterEntities  = new HashMap<>();
	// 订阅了这个cell的退出事件  离开视野遍历他
	public Map<Long, AOIEntity> SubsLeaveEntities  = new HashMap<>();

	public Cell(Long id) {
		Id = id;
	}

	public  void Add(AOIEntity aoiEntity)
	{
		this.AOIUnits.put(aoiEntity.Id, aoiEntity);
	}

	public  void Remove(AOIEntity aoiEntity)
	{
		this.AOIUnits.put(aoiEntity.Id, aoiEntity);
	}

}
