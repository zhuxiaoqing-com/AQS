package com.example.demo4;

import io.swagger.models.auth.In;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * id生成器
 * 
 * 最大可以用34年 每秒最大生成id数量32768个 区数量最大支持8192个
 * 
 * 区ID最大上限 65000 区id+时间+（id++） 最高位正负数不算 16位+32位+15位
 *
 * private static final int SERVER_POS = 47;// 64-47 = 17 还有17位可以用 如果要保证正数那就只有16位可以用
 *
 * 	private static final int ADD_POS = 15; // 47-15 = 32 32位可以用;32位可以用作秒数;
 *
 * 	15 = 32767
 * 
 */
public class IdFactory {
	private static Logger logger = LoggerFactory.getLogger(IdFactory.class);

	/** 服务器id **/
	private static int sid = 200;

	/** 玩家id自增 **/
	private static long playerIdAuto = 0;

	/** User id自增 **/
	private static long userIdAuto = 0;

	/** 道具id自增 **/
	private static long itemIdAuto = 0;

	/** 帮派id自增 **/
	private static long bangIdAuto = 0;

	/** 队伍Id自增 */
	private static long teamIdAuto = 0;

	/** 邮件id **/
	private static long emailIdAuto = 0;

	/** 建筑id */
	private static long buildingIdAuto = 0;

	/** orderID */
	private static long orderIdAuto = 0;

	/*** tokenID */
	private static long tokenIdAuto = 0;

	/*** 掉落id自增 */
	private static long pickUpIdAuto = 0;

	/*** resourceID */
	private static long resourceIdAuto = 0;
	/*** 乘员id */
	private static long crewIdAuto = 0;
	/**BgLog*/
	private static long bglogIdAuto = 0;
	/**chat*/
	private static long chatIdAuto = 0;


	/** 上次玩家id生成时间 **/
	private static int lastPlayerCreateTime;

	/** 上次用户id生成时间 **/
	private static int lastUserCreateTime;

	/** 上次道具id生成时间 **/
	private static int lastItemCreateTime;

	/** 上次帮派id生成时间 **/
	private static int lastBangCreateTime;

	/** 上次队伍Id生成时间 */
	private static int lastTeamCreateTime;

	/** 上次邮件Id生成时间 */
	private static int lastEmailCreateTime;

	/** 上次建筑ID生成时间 */
	private static int lastBuildingCreateTime;

	/** 上次邮件Id生成时间 */
	private static int lastOrderCreateTime;

	/** 上次tokenId生成时间 */
	private static int lastTokenCreateTime;

	/** 上次资源 */
	private static int lastResourceCreateTime;
	/*** 上次掉落id */
	private static long pickUpIdCreateTime;

	/** 上次乘员id */
	private static int lastCreateCrewTime;
	/**BgLog*/
	private static int lastBgLogCreateTime;

	/**chat*/
	private static int lastChatLogCreateTime;

	private static long timeBegin;

	private static final int SERVER_POS = 47;

	private static final int ADD_POS = 15;
	static {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.YEAR, 2013);
		calender.set(Calendar.MONTH, Calendar.JANUARY);
		calender.set(Calendar.DAY_OF_YEAR, 1);
		calender.set(Calendar.HOUR_OF_DAY, 12);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		timeBegin = calender.getTimeInMillis();
	}

	public static int currentTimeSecond() {
		return (int) ((System.currentTimeMillis() - timeBegin) / 1000);
	}

	public static void setSid(int sid) {
		if(sid> 65000) {
			logger.warn("!!!!!!!!!!!!!!!!!sid:{} > 65000  !!!!!!!!!!!!!!!, serverId must less than 65000 !!!!!!!!!!!!!!!", sid);
			// 目前只有logic和lobby需要生成，別的服務器sid會超過65000
			sid = 0;
		}
		IdFactory.sid = sid;
	}

	/** 生成用户id 每秒生成32768个id **/
	public synchronized static long createBuildingId() {
		int second = currentTimeSecond();
		if (second == lastBuildingCreateTime) {
			buildingIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (buildingIdAuto > seedMax) {
				buildingIdAuto = 0;
			}
		} else {
			lastBuildingCreateTime = second;
			buildingIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + buildingIdAuto;
	}

	/** 生成用户id 每秒生成65536个id **/
	public synchronized static long createOrderId(final int sid) {
		int second = currentTimeSecond();
		if (second == lastOrderCreateTime) {
			orderIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (orderIdAuto > seedMax) {
				orderIdAuto = 0;
			}
		} else {
			lastOrderCreateTime = second;
			orderIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + orderIdAuto;
	}

	/** 生成用户id 每秒生成65536个id **/
	public synchronized static long createUserId() {
		int second = currentTimeSecond();
		if (second == lastUserCreateTime) {
			userIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (userIdAuto > seedMax) {
				userIdAuto = 0;
			}
		} else {
			lastUserCreateTime = second;
			userIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + userIdAuto;
	}

	/** 生成玩家id 每秒生成65536个id **/
	public synchronized static long createPlayerId() {
		int second = currentTimeSecond();
		if (second == lastPlayerCreateTime) {
			playerIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (playerIdAuto > seedMax) {
				playerIdAuto = 0;
			}
		} else {
			lastPlayerCreateTime = second;
			playerIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + playerIdAuto;
	}

	/** 生成道具id 每秒生成65536个id **/
	public synchronized static long createItemId() {
		int second = currentTimeSecond();
		if (second == lastItemCreateTime) {
			itemIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (itemIdAuto >= seedMax) {
				itemIdAuto = 0;
			}
		} else {
			lastItemCreateTime = second;
			itemIdAuto = 0;
		}
		return ((long) sid << SERVER_POS) + ((long) second << ADD_POS) + itemIdAuto;
	}

	/** 生成帮派id 每秒生成65536个id **/
	public static long createBangId() {
		int second = currentTimeSecond();
		if (second == lastBangCreateTime) {
			bangIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (bangIdAuto > seedMax) {
				bangIdAuto = 0;
			}
		} else {
			lastBangCreateTime = second;
			bangIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + bangIdAuto;
	}


	/** 生成队伍id 每秒生成65536个id **/
	public synchronized static long createTeamId() {
		int second = currentTimeSecond();
		if (second == lastTeamCreateTime) {
			teamIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (teamIdAuto > seedMax) {
				teamIdAuto = 0;
			}
		} else {
			lastTeamCreateTime = second;
			teamIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + teamIdAuto;
	}

	/** 生成邮件id **/
	public synchronized static long createEmailId() {
		int second = currentTimeSecond();
		if (second == lastEmailCreateTime) {
			emailIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (emailIdAuto > seedMax) {
				emailIdAuto = 0;
			}
		} else {
			lastEmailCreateTime = second;
			emailIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + emailIdAuto;
	}
	
	public static int parseServerId(long id) {
		return (int) (id >> SERVER_POS);
	}


	/**
	 * 没有自增id的原理懂了 是因为 一边是不变的000 一边是频繁的秒数 所以其实就是吧000移除掉 用秒数来计算桶
	 * 如果是>>>32 左边的基本是不会变得 所以再没自增id的时候会都在一个桶上
	 *
	 * 有自增id的呢? 一边是自增频繁的id 一边是秒数  与起来 变化就更多了 所以就更分布均匀了
	 *
	 * @param value
	 * @return
	 */
	public static int hash(long value) {
		return (int) (value ^ (value >>> ADD_POS));
	}

	/** 生成tokenId 每秒生成65536个id **/
	public synchronized static long createTokenId() {
		int second = currentTimeSecond();
		if (second == lastTokenCreateTime) {
			tokenIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (tokenIdAuto > seedMax) {
				tokenIdAuto = 0;
			}
		} else {
			lastTokenCreateTime = second;
			tokenIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + tokenIdAuto;
	}

	/** 生成资源Id 每秒生成65536个id **/
	public synchronized static long createResourceId() {
		int second = currentTimeSecond();
		if (second == lastResourceCreateTime) {
			resourceIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (resourceIdAuto > seedMax) {
				resourceIdAuto = 0;
			}
		} else {
			lastResourceCreateTime = second;
			resourceIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + resourceIdAuto;
	}

	/** 生成资源Id 每秒生成65536个id **/
	public synchronized static long createPickUpId() {
		int second = currentTimeSecond();
		if (second == pickUpIdCreateTime) {
			pickUpIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (pickUpIdAuto > seedMax) {
				pickUpIdAuto = 0;
			}
		} else {
			pickUpIdCreateTime = second;
			pickUpIdAuto = 0;
		}
		return (sid * 1L << SERVER_POS) + (second * 1L << ADD_POS) + pickUpIdAuto;
	}

	/** 生成载具乘员Id每秒生成65536个id **/
	public synchronized static long createCrewId() {
		int second = currentTimeSecond();
		if (second == lastCreateCrewTime) {
			crewIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持65536个id的生成
			if (crewIdAuto > seedMax) {
				crewIdAuto = 0;
			}
		} else {
			lastCreateCrewTime = second;
			crewIdAuto = 0;
		}
		return ((long) sid << SERVER_POS) + ((long) second << ADD_POS) + crewIdAuto;
	}

	/**生成bgLogId  每秒生成32768个id**/
	public synchronized static long createBgLogId()
	{
		int second = currentTimeSecond();
		if (second == lastBgLogCreateTime)
		{
			bglogIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持32768个id的生成
			if (bglogIdAuto > seedMax)
			{
				bglogIdAuto = 0;
			}
		}
		else
		{
			lastBgLogCreateTime= second;
			bglogIdAuto = 0;
		}
		return (sid*1L<<SERVER_POS) + (second*1L<<ADD_POS)+bglogIdAuto;
	}

	/**生成chatId  每秒生成32768个id**/
	public synchronized static long createChatId()
	{
		int second = currentTimeSecond();
		if (second == lastChatLogCreateTime)
		{
			chatIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持32768个id的生成
			if (chatIdAuto > seedMax)
			{
				chatIdAuto = 0;
			}
		}
		else
		{
			lastChatLogCreateTime= second;
			chatIdAuto = 0;
		}
		return (sid*1L<<SERVER_POS) + (second*1L<<ADD_POS)+chatIdAuto;
	}

	// 防止回调方法1 et7 这里溢出了也没事 会一直借用下一秒
	public synchronized static long __createChatId()
	{
		int second = currentTimeSecond();
		if (second > lastChatLogCreateTime)
		{
			lastChatLogCreateTime = second;
			chatIdAuto = 0;
		} else {
			chatIdAuto++;
			long seedMax = (1 << ADD_POS);// 每秒支持32768个id的生成
			if (chatIdAuto > seedMax) {
				chatIdAuto = 0;
				//借用下一秒
				lastChatLogCreateTime++;
				// 打印报错日志 id count per sec overflow
			}
		}

		return (sid*1L<<SERVER_POS) + (second*1L<<ADD_POS)+chatIdAuto;
	}

	// 防止回调方法2 et8
	// 这里回调了也没事 因为chatIdAuto没有重置成0; 除非chatIdAuto溢出了 才会重复
	public synchronized static long ___createChatId()
	{
		int second = currentTimeSecond();
		chatIdAuto++;
		long seedMax = (1 << ADD_POS);// 每秒支持32768个id的生成
		if (chatIdAuto > seedMax) {
			chatIdAuto = 0;
		}


		return (sid*1L<<SERVER_POS) + (second*1L<<ADD_POS)+chatIdAuto;
	}

	public static void main(String[] args) {
		Map<Long, Long> ss = new HashMap<Long, Long>();
		for (int i = 0; i < 40000; i++) {
			long id = createEmailId();

			if (ss.containsKey(id)) {
				System.out.println(i + "ddddddddddddddddddddddd");

				break;
			}
			ss.put(id, id);
			if (id < 0) {
				System.out.println("ssssssss");
				return;
			}
			long sid = (id >> 47);
			// System.out.println( sid);
			if (sid != 200) {
				System.out.println("65000xxxxxxxxxxxxxxxxxxx");
				break;
			}

		}
	}

	@Test
	public void test01() {
		long a = 14215278684340227L;
		a = 14215273031270400L;
		a = 14215267485483008L;
		a = 14215276104613896L;

		long serverId = a >> SERVER_POS;
		System.out.println(serverId);

		long auto = a & 32767;
		System.out.println(auto);

		System.out.println(1 << 12);
	}


}
