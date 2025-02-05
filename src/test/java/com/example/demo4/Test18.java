package com.example.demo4;

import com.example.demo1.util.vo.Vector2f;
import com.google.common.eventbus.EventBus;
import com.youxi.entity.Position;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.web3j.abi.datatypes.Int;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test18 {

	public static class Obj {
		private Map<Integer,Integer> obj1;
		private int obj2;

		public Map<Integer, Integer> getObj1() {
			return obj1;
		}

		public void setObj1(Map<Integer, Integer> obj1) {
			this.obj1 = obj1;
		}

		public int getObj2() {
			return obj2;
		}

		public void setObj2(int obj2) {
			this.obj2 = obj2;
		}
	}

	@Test
	public void test01() throws InstantiationException, IllegalAccessException, InvocationTargetException {
		Obj obj = Obj.class.newInstance();

		Method method = Arrays.stream(Obj.class.getMethods())
				.filter(a -> a.getName().equals("setObj1"))
				.findAny().orElse(null);

		Map<Integer, Integer> objectObjectHashMap = new HashMap<>();
		Object objectObjectHashMap1 = new HashMap<>();
		method.invoke(obj, objectObjectHashMap1);
		System.out.println(obj);
	}

	}

/**
 * 1.威力（技能属性-基础伤害倍率）-%
 * 2.冷却时间（技能属性-攻速）-秒
 * 3.穿透（子弹属性-穿透）
 * 4.子弹数量（技能属性-发射数量）
 * 5.子弹判定（伤害判定-判定参数矩形取宽，圆形半径）
 * 6.持续时间（子弹属性-子弹存活时间）-秒
 * 7.装弹时间（技能属性-装弹时间）-秒
 * 8.弹药（技能属性-弹药量）
 * 9.生命值
 * 10.固定防御减免
 * 11.生命恢复速度-每秒
 * 12.物理减免-%
 * 13.魔法减免-%
 * 14.SP效率-%
 * 15.每回合额外SP
 * 16.金币效率-%
 * 17.每回合额外金币
 * 18.伤害间隔
 * 19.攻击距离
 * 20.视野距离
 * 21.视野角度
 * 22.子子弹威力
 * 23.护盾值
 * 24.护盾恢复速度-每秒
 * 25.生命恢复-百分比的形式
 * 26.护盾恢复-百分比的形式
 * 27.飞船移动速度Speed （需要最后乘以100，展示在客户端）
 * 28.飞船交战移速GlobalAngleSpeed（需要最后乘以100，展示在客户端）
 * 29.机库---初始出击时间
 * 30.机库---机甲出击间隔
 */


//  -XX:AutoBoxCacheMax=100000















