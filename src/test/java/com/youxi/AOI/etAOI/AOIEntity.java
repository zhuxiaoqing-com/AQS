package com.youxi.AOI.etAOI;

import java.util.HashMap;
import java.util.HashSet;

public class AOIEntity {

	public static final int Player = 1;
	public static final int MONSTER = 2;

	public int Type;
	public long Id;
	public int ViewDistance;
	public Cell Cell; // 所在的cell


	// 观察进入视野的Cell 用来记住进入观察视野的cell
	public HashSet<Long> SubEnterCells = new HashSet<Long>();

	// 观察离开视野的Cell 用来记住退出观察视野的cell
	public HashSet<Long> SubLeaveCells = new HashSet<Long>();

	// 观察进入视野的Cell move时临时使用
	public HashSet<Long> enterHashSet = new HashSet<Long>();

	// 观察离开视野的Cell move时临时使用
	public HashSet<Long> leaveHashSet = new HashSet<Long>();

	// 我看的见的Unit
	public HashMap<Long, AOIEntity> SeeUnits = new HashMap<Long, AOIEntity>();

	// 看见我的Unit
	public HashMap<Long, AOIEntity> BeSeeUnits = new HashMap<Long, AOIEntity>();

	// 我看的见的Player
	public HashMap<Long, AOIEntity> SeePlayers = new HashMap<Long, AOIEntity>();

	// 看见我的Player单独放一个Dict，用于广播
	public HashMap<Long, AOIEntity> BeSeePlayers = new HashMap<Long, AOIEntity>();

	public Cell getCell() {
		return Cell;
	}

	public void setCell(Cell cell) {
		this.Cell = cell;
	}

	// cell中的unit进入self的视野
	public void SubEnter(Cell cell)
	{
		cell.SubsEnterEntities.put(Id, this);

		for (AOIEntity kv : cell.AOIUnits.values()) {
			if (kv.Id == Id)
			{
				continue;
			}

			EnterSight(kv);
		}

	}

	public void UnSubEnter(Cell cell)
	{
		cell.SubsEnterEntities.remove(Id);
	}

	public  void SubLeave(Cell cell)
	{
		cell.SubsLeaveEntities.put(Id, this);
	}

	// cell中的unit离开self的视野
	public  void UnSubLeave(Cell cell)
	{
		for (AOIEntity kv : cell.AOIUnits.values()) {
			if (kv.Id == Id)
			{
				continue;
			}

			LeaveSight(kv);
		}
		cell.SubsLeaveEntities.remove(Id);
	}

	public void EnterSight(AOIEntity enter) {
		// 有可能之前在Enter，后来出来了Enter还在LeaveCell，这样仍然没有删除，继续进来Enter，这种情况不需要处理
		if (SeeUnits.containsKey(enter.Id))
		{
			return;
		}
		if (!AOISeeCheckHelper.IsCanSee(this, enter))
		{
			return;
		}

		if(this.Type == Player) {
			if(enter.Type == Player) {
				this.SeeUnits.put(enter.Id, enter);
				enter.BeSeeUnits.put(this.Id, this);
				this.SeePlayers.put(enter.Id, enter);
				enter.BeSeePlayers.put(this.Id, this);
			} else {
				this.SeeUnits.put(enter.Id, enter);
				enter.BeSeeUnits.put(this.Id, this);
				enter.BeSeePlayers.put(this.Id, this);
			}
		}else {
			if (enter.Type == Player)
			{
				this.SeeUnits.put(enter.Id, enter);
				enter.BeSeeUnits.put(this.Id, this);
				this.SeePlayers.put(enter.Id, enter);
			}
			else
			{
				this.SeeUnits.put(enter.Id, enter);
				enter.BeSeeUnits.put(this.Id, this);
			}
		}

		// EventSystem.Instance.Publish(self.Scene(), new UnitEnterSightRange() { A = self, B = enter });

	}

	// leave离开self视野
	public void LeaveSight(AOIEntity leave)
	{
		if (this.Id == leave.Id)
		{
			return;
		}

		if (!this.SeeUnits.containsKey(leave.Id))
		{
			return;
		}

		this.SeeUnits.remove(leave.Id);
		if (leave.Type == Player)
		{
			this.SeePlayers.remove(leave.Id);
		}

		leave.BeSeeUnits.remove(this.Id);
		if (this.Type == Player)
		{
			leave.BeSeePlayers.remove(this.Id);
		}

		//  EventSystem.Instance.Publish(self.Scene(), new UnitLeaveSightRange { A = self, B = leave });
	}

	public boolean IsBeSee(long unitId)
	{
		return BeSeePlayers.containsKey(unitId);
	}


}
