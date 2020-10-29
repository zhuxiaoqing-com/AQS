package com.youxi.sssh.fsm;

import java.util.Objects;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/10/29 17:35
 * @Description:
 */
public class AbstractFSMState<S extends Enum, T extends Object> {
	/**
	 * 当前状态机类型
	 */

	protected S type;
}
