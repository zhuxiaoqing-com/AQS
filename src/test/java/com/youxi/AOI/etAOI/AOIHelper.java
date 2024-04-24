package com.youxi.AOI.etAOI;

import java.util.HashSet;

public class AOIHelper {

	public static long CreateCellId(int x, int y)
	{
		return (long) ((long) x << 32) | (int) y;
	}

	public static void CalcEnterAndLeaveCell(AOIEntity aoiEntity, int cellX, int cellY, HashSet<Long> enterCell, HashSet<Long> leaveCell) {
		enterCell.clear();
		leaveCell.clear();

		// (9-1)/10 +1 = 1
		int r = (aoiEntity.ViewDistance - 1) / AOIManagerComponentSystem.CellSize + 1;

		int leaveR = r;
		if (aoiEntity.Type == AOIEntity.Player) // 如果是玩家
		{
			leaveR += 1;
		}

		for (int i = cellX - leaveR; i <= cellX + leaveR; ++i)
		{
			for (int j = cellY - leaveR; j <= cellY + leaveR; ++j)
			{
				long cellId = CreateCellId(i, j);
				leaveCell.add(cellId);

				if (i > cellX + r || i < cellX - r || j > cellY + r || j < cellY - r)
				{
					continue;
				}

				enterCell.add(cellId);
			}
		}

	}

}
