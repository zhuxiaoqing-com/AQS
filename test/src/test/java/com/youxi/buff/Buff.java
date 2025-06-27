package com.youxi.buff;

import com.youxi.buff.entity.Context;
import com.youxi.buff.entity.MapObject;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/3/9 17:58
 * @Description:
 */
public class Buff {
	private List<Integer> state; // 状态
	private MapObject caster; // buff施加者
	private MapObject target; // buff 当前挂载的目标
	private int skillId; // buff 是由哪个技能创建的
	private int layer; // 层数
	private int buffTag; // buff 类型
	private int buffImmuneTag; // buff 免疫类型
	private Context context; // buff 创建时的一些相关上下文
	private boolean bNoCaster; // 是否强制设置 caster 为空


	/**
	 * 当Buff生效时（加入到Buff容器后）
	 */
	public void onBuffStart() {

	}

	/**
	 * Buff执行刷新流程（更新Buff层数，等级，持续时间等数据）
	 */
	public void onBuffRefresh() {

	}

	/**
	 *当Buff销毁前（还未从Buff容器中移除)
	 */
	public void onBuffRemove() {

	}

	/**
	 * 当Buff销毁后（已从Buff容器中移除）
	 */
	public void onBuffDestroy() {

	}


	/**
	 * 创建定时器，以触发间隔持续效果
	 */
	public void startIntervalThink() {

	}

	/**
	 * 触发间隔持续效果
	 */
	public void onIntervalThink() {

	}
}
