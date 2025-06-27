package com.example.代码优化.设计模式之美.a_14鉴权功能;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/15 15:22
 * @Description:
 */
public class Readme {

	/**
	 * 面向过程编程
	 */
	public void auth(String appId, String password, String createTime, String token){
		// 判断时间
		// 服务器计算token
		// 比较服务器token 客户端 token 是否相同
	}
}

/**
 *
 * 面向对象编程
 *
 * 四个步骤
 * 1、划分职责而识别出有哪些类
 * 		根据需求描述，我们把其中涉及的功能点，一个一个罗列出来，然后再去看那些功能点职责相近，操作同样的属性，可否归类为同一个类
 *
 *
 * 2、定义类及其属性和方法
 * 		我们识别出需求描述中的动词，作为候选的方法，再进一步过滤筛选出真正的方法，
 * 		把功能点中涉及的名词，作为候选属性，然后同样再过滤筛选。
 *
 *
 * 3、定义类与类之间的交互关系
 * 		UML 统一建模语言中定义了六种类之间的关系。
 * 		它们分别是：泛化、实现、关联、聚合、组合、依赖。
 * 		泛化：就是抽象
 * 		实现：实现接口
 * 		聚合：A类里面有B类，但是两者的对象的生命周期 没有关联
 * 		组合：A类里面有B类，两者对象的生命周期互相关联; 比如：鸟类和翅膀类：两者的生命周期互相关联,鸟对象被销毁了,翅膀对象也就被销毁了
 * 		关联：聚合和组合的父类, A类里面有B类 就算是关联
 * 		依赖：和关联相比更加的抽象，只要A类里面有B类的引用就算是依赖。比如 class A{ a(B b){}}; A类的一个方法的入参是B类
 *
 * 	我们从更加贴近编程的角度，对类与类之间的关系做了调整，保留四个关系：泛化、实现、组合、依赖。
 *
 *
 * 	4、将类组装起来并提供执行入口
 * 		我们要将所有的类组装在一起，提供一个执行入口。
 * 		这个入口可能是一个 main()函数，也可能是一组给外部用的 API 接口。
 * 		通过这个入口，我们能触发整个代码跑起来。
 *
 *
 *
 * 下面我演示下 1、划分职责而识别出有哪些类 该怎么分析；
 *
 * 鉴权需求有如下流程：
 * 客户端
 * 	1、把URL、AppId、密码、时间戳拼接为一个字符串;
 * 	2、对字符串通过加密算法加密生成 token
 * 	3、将token、AppId、时间戳坪拼接URL中，形成新的URL
 * 然后把URL 发送到服务器
 *  4、解析URL，得到 token、appId、 时间戳等信息
 *  5、从存储中取到 appId 和对应的密码
 *  6、根据时间戳判断 token 是否过期失效
 *  7、验证两个 token 是否匹配
 *
 *
 *  首先1、2、6、7 是权限校验 可以建立一个 Auth 类
 *
 *  3、4 是URL 解析，可以建立一个 URL 类; 但是由于可能不止是 http 还有可能是 RPC; 所以类名改为 ApiRequest
 *
 *  5、 是从数据库里面获取密码类 是一个存储类
 *
 *	class Auth{
 *	   private long createTime;
 *	   private String token;
 *	   private int timeInterval = 1 * 60 * 1000; // 一分钟
 *
 *	   public Auth(long createTime, String token){
 *	       // 赋值
 *	   }
 *
 *	   public static Auth createAuth(long createTime, Map<String, String> params){
 *	       // 将 prams 加密成为 token
 *	       String token =encodeMD5(createTime,params);
 *	       return new Auth(createTime, token);
 *	   }
 *
 *	   // 判断该token 是否过期
 *	   public boolean isExpired(){
 *	       return System.currentMill() - createTime > timeInterval
 *	   }
 *
 *	   public boolean match(Auth auth){
 *	       return this.token.equals(auth.getToken());
 *	   }
 *
 *
 *	}
 *
 *
 *
 */

























