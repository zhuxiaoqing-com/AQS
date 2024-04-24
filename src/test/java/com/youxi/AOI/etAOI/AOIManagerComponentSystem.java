package com.youxi.AOI.etAOI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AOIManagerComponentSystem {

	Map<Long, Cell> cellMap = new HashMap<>();
	public static int CellSize = 10 * 1000;

	public Cell GetCell(Long cell) {
		return cellMap.computeIfAbsent(cell, a -> new Cell(cell));
	}

	public void Add(AOIEntity aoiEntity, float x, float y) {
		int cellX = (int) (x * 1000) / CellSize;
		int cellY = (int) (y * 1000) / CellSize;

		// 9 * 1000
		if (aoiEntity.ViewDistance == 0) {
			aoiEntity.ViewDistance = 1;
		}


		AOIHelper.CalcEnterAndLeaveCell(aoiEntity, cellX, cellY, aoiEntity.SubEnterCells, aoiEntity.SubLeaveCells);

		//遍历EnterCell 别人进入我的视野 并且我要订阅cell 进入事件
		for (Long subEnterCell : aoiEntity.SubEnterCells) {
			Cell cell = GetCell(subEnterCell);
			aoiEntity.SubEnter(cell);
		}
		// 遍历LeaveCell 订阅cell离开事件  要比进入事件大一点
		for (Long subEnterCell : aoiEntity.SubLeaveCells) {
			Cell cell = GetCell(subEnterCell);
			aoiEntity.SubLeave(cell);
		}

		Cell selfCell = GetCell(AOIHelper.CreateCellId(cellX, cellY));
		aoiEntity.Cell = selfCell;
		selfCell.Add(aoiEntity);
		// 通知订阅该Cell Enter的Unit 我进入别人的视野
		for (AOIEntity value : selfCell.SubsEnterEntities.values()) {
			value.EnterSight(aoiEntity);
		}
	}


	public void Remove(AOIEntity aoiEntity) {
		if (aoiEntity.Cell == null) {
			return;
		}

		// 通知订阅该Cell Leave的Unit 我离开了cell
		aoiEntity.Cell.Remove(aoiEntity);
		for (AOIEntity value : aoiEntity.Cell.SubsLeaveEntities.values()) {
			value.LeaveSight(aoiEntity);
		}

		// 通知自己订阅的Enter Cell，清理自己
		for (Long cellId : aoiEntity.SubEnterCells) {
			Cell cell = GetCell(cellId);
			aoiEntity.UnSubEnter(cell);
		}

		for (Long cellId : aoiEntity.SubLeaveCells) {
			Cell cell = GetCell(cellId);
			aoiEntity.UnSubLeave(cell);
		}

		// 检查
	/*	if (aoiEntity.SeeUnits.Count > 1)
		{
			Log.Error($"aoiEntity has see units: {aoiEntity.SeeUnits.Count}");
		}

		if (aoiEntity.BeSeeUnits.Count > 1)
		{
			Log.Error($"aoiEntity has beSee units: {aoiEntity.BeSeeUnits.Count}");
		}
*/
	}

	// 通知监听precell离开视野的人 和 newCell进入视野的人
	public static void Move(AOIEntity aoiEntity, Cell newCell, Cell preCell)
	{
		aoiEntity.Cell = newCell;
		preCell.Remove(aoiEntity);
		newCell.Add(aoiEntity);

		// 通知订阅该newCell Enter的Unit
		for (AOIEntity e : newCell.SubsEnterEntities.values()) {
			if (e.SubEnterCells.contains(preCell.Id))
			{
				continue;
			}
			e.EnterSight(aoiEntity);
		}

		// 通知订阅preCell leave的Unit
		for (AOIEntity e : preCell.SubsLeaveEntities.values()) {
			// 如果新的cell仍然在对方订阅的subleave中
			if (e.SubLeaveCells.contains(newCell.Id)) {
				continue;
			}

			e.LeaveSight(aoiEntity);
		}
	}

	public void Move(AOIEntity aoiEntity, int cellX, int cellY)
	{
		long newCellId = AOIHelper.CreateCellId(cellX, cellY);
		if (aoiEntity.Cell.Id == newCellId) // cell没有变化
		{
			return;
		}

		Cell newCell = GetCell(newCellId);
		// 通知监听precell离开视野的人 和 newCell进入视野的人
		Move(aoiEntity, newCell, aoiEntity.Cell);

		AOIHelper.CalcEnterAndLeaveCell(aoiEntity, cellX, cellY, aoiEntity.enterHashSet, aoiEntity.leaveHashSet);


		// 算出自己leave新Cell
		for (Long cellId : aoiEntity.leaveHashSet) {
			if (aoiEntity.SubLeaveCells.contains(cellId))
			{
				continue;
			}
			Cell cell = GetCell(cellId);
			aoiEntity.SubLeave(cell);
		}

		// 算出需要通知离开的Cell
		aoiEntity.SubLeaveCells.removeAll(aoiEntity.leaveHashSet);
		for (Long cellId : aoiEntity.SubLeaveCells) {
			Cell cell = GetCell(cellId);
			aoiEntity.UnSubLeave(cell);
		}

		// 这里交换两个HashSet,提高性能
		HashSet<Long> temp = aoiEntity.SubLeaveCells;
		aoiEntity.SubLeaveCells = aoiEntity.leaveHashSet;
		aoiEntity.leaveHashSet = temp;

		// 算出自己看到的新Cell
		for (Long cellId : aoiEntity.enterHashSet) {
			if (aoiEntity.SubEnterCells.contains(cellId))
			{
				continue;
			}

			Cell cell = GetCell(cellId);
			aoiEntity.SubEnter(cell);
		}

		// 离开的Enter
		aoiEntity.SubEnterCells.removeAll(aoiEntity.enterHashSet);
		for (Long cellId : aoiEntity.SubEnterCells) {
			Cell cell = GetCell(cellId);
			aoiEntity.UnSubEnter(cell);
		}

		// 这里交换两个HashSet,提高性能
		temp = aoiEntity.SubEnterCells;
		aoiEntity.SubEnterCells = aoiEntity.enterHashSet;
		aoiEntity.enterHashSet = temp;
	}














}
