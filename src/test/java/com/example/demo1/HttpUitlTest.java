package com.example.demo1;

import com.example.demo1.demo.HttpUtil;
import com.example.demo1.demo.SignUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2019-05-30 16:32:44 | INFO  | 游戏公共驱动线程-9 | RoleManager:366 | role=383579202062115 傲慢的巴里 升级！oldLv=73,
 * curLv = 75, addExp=111111111, extraExp=0
 *
 * @author zhuxiaoqing
 */
public class HttpUitlTest {
    /**
     * 道具查询接口
     */
    @Test
    public void itemQuery() {
        String uri = "/item/query";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "傲慢的巴里");
        //params.put("location", "1");
        params.put("itemId", "42011");
        post(params, uri);
    }


    /**
     * 道具补偿
     */
    @Test
    public void compensate() {
        String uri = "/item/compensate";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "傲慢的巴里");
        params.put("items", "42001|1");
        post(params, uri);
    }

    /**
     * 道具修改接口
     */
    @Test
    public void itemModify() {
        String uri = "/item/modify";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "傲慢的巴里");
        params.put("itemId", "42001");
        params.put("count", "1");
        // params.put("roleName", "内向的罗夫斯");
        //params.put("itemId", "1");
        post(params, uri);
    }

    /**
     * 充值接口
     */
    @Test
    public void rechargeIngot() {
        String uri = "/recharge";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "塞缪尔丶美伊");
        params.put("rechargeId", "81");
        post(params, uri);
    }

  /*  *//**
     * 激活特权接口
     *//*
    @Test
    public void activatePrivilege() {
        String uri = "/recharge/privilege";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "aaaa");
        params.put("privilegeId", "7");
        post(params, uri);
    }*/



    /**
     * 公告接口
     */
    @Test
    public void sendGMNotice() {
        long milli = System.currentTimeMillis() + 1000 * 10;
        String stime =  String.valueOf(milli / 1000);
        String etime =  String.valueOf(milli / 1000 + 120);
        String uri = "/message/sendNotice";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "2");
        params.put("type", "1");
        // <color=#76EE00>杨帅能上天</color>
        params.put("content", "<color=#76EE00>王·战要上天入地下海王战要上天入地下海王战要上天入地下海王战要上天入地下海</color>");
        params.put("color", "#FE0000");
        params.put("stime", stime);
        params.put("etime", etime);
        params.put("ltime", "1");
        params.put("count", "2");
        post(params, uri);
        //post(params, uri);
    }


    /**
     * 邮件接口 个人
     */
    @Test
    public void sendSomeRoleGMEmail1() {
        String uri = "/message/sendEmail";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("title", "title");
        params.put("content", "content");
        params.put("items", "13430|100&103|300000");
        params.put("roleNames", "细心的威尔");
        params.put("closeDay", "3");
        params.put("subject", "subject");
        params.put("emailName", "emailName");
        post(params, uri);
    }

    /**
     * 邮件接口 个人 2
     */
    @Test
    public void sendSomeRoleGMEmail3() {
        String uri = "/message/sendRoleEmail";
        Map<String, String> params = new LinkedHashMap<>();
        //params.put("type", "1");
        params.put("title", "2");
        params.put("content", "3");
        params.put("items", "42001|1&104|300000");
        params.put("roleNames", "老实的霍顿");
        params.put("closeDay", "3");
        params.put("subject", "4");
        params.put("emailName", "5");
        post(params, uri);
    }

    /**
     * 邮件接口 全服
     */
    @Test
    public void sendSomeRoleGMEmail2() {
        String uri = "/message/sendServerEmail";
        Map<String, String> params = new LinkedHashMap<>();
        //params.put("type", "2");
        params.put("title", "测试邮件");
        params.put("content", "测试邮件");
        params.put("items", "42001|1");
        //params.put("roleNames", "老实的霍顿");
        params.put("closeDay", "3");
        params.put("subject", "测试邮件");
        params.put("emailName", "测试邮件");
        post(params, uri);
    }


    /**
     * 根据id查询角色接口
     */
    @Test
    public void selectId() {
        String uri = "/role/selectId";
        Map<String, String> params = new LinkedHashMap<>();
        // 72058144007497728 本机
        params.put("roleId", "1152930300834362913");
        post(params, uri);
    }

    /**
     * 根据角色名称查询角色接口
     */
    @Test
    public void selectName() {
        String uri = "/role/selectName";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "aaa");
        post(params, uri);
    }

    /**
     * 封禁角色
     */
    @Test
    public void setRoleLock() {
        String uri = "/role/setRoleLock";
        Map<String, String> params = new LinkedHashMap<>();
        //params.put("type", "1");
        params.put("roleName", "老实的霍顿");
        params.put("lockTime", "1111111");
        params.put("reason", "开挂了");
        post(params, uri);
    }


    /**
     * 解封角色
     */
    @Test
    public void setRoleUnLock() {
        String uri = "/role/setRoleLock";
        Map<String, String> params = new LinkedHashMap<>();
        //params.put("type", "1");
        params.put("roleName", "老实的霍顿");
        params.put("lockTime", "0");
        params.put("reason", "傲慢的巴里私底下交易给钱了");// 禁封理由
        post(params, uri);
    }


    /**
     * 设置禁言
     */
    @Test
    public void chatBan() {
        String uri = "/role/chatBan";
        Map<String, String> params = new LinkedHashMap<>();
        //params.put("type", "2");
        params.put("roleName", "老实的霍顿");
        params.put("lockTime", "20000");
        params.put("reason", "xxxxxx");
        params.put("channelType", "0"); // 设置默认 0全频道 1综合 2 世界 3 工会 5 场景  默认全频道禁言
        params.put("banType", "0"); //0禁言 1//自言自语，不发送信息   默认禁言
        post(params, uri);
    }


    /**
     * 解除禁言
     */
    @Test
    public void chatUnBan() {
        String uri = "/role/chatBan";
        Map<String, String> params = new LinkedHashMap<>();
        //params.put("type", "2");
        params.put("roleName", "老实的霍顿");
        params.put("lockTime", "0");
        params.put("reason", "xxxxxx");
        params.put("channelType", "0"); // 设置默认 0全频道 1综合 2 世界 3 工会 5 场景  默认全频道禁言
        params.put("banType", "0"); //0禁言 1//自言自语，不发送信息   默认禁言
        post(params, uri);
    }


    /**
     * 在线人数
     */
    @Test
    public void countNumbers() {
        String uri = "/role/countNumbers";
        Map<String, String> params = new LinkedHashMap<>();
        post(params, uri);
    }

    /**
     * 关闭服务器
     */
    @Test
    public void closeServer() {
        String uri = "/system/closeServer";
        Map<String, String> params = new LinkedHashMap<>();
        post(params, uri);
    }


    /**
     * 修改在线人数
     */
    @Test
    public void updateMaxOnlineNum() {
        String uri = "/system/updateMaxOnlineNum";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("onlineNum", "111");
        post(params, uri);
    }


    /**
     * 获取所有公会的id和名字
     */
    @Test
    public void guildIdNameList() {
        String uri = "/guild/guildIdNameList";
        Map<String, String> params = new LinkedHashMap<>();
        String result = post(params, uri);
    }


    /**
     * 获取所有公会
     */
    @Test
    public void guildList() {
        String uri = "/guild/guildList";
        Map<String, String> params = new LinkedHashMap<>();
        String result = post(params, uri);
    }

    /**
     * 获取公会所有成员
     */
    @Test
    public void guildMemberListByGuildId() {
        String uri = "/guild/guildMemberList";
        Map<String, String> params = new LinkedHashMap<>();
        // todo 获取所有公会id和name 随机取一个公会id作为测试参数
        params.put("guildId", "1729391053139762401");
        String result = post(params, uri);
    }


    /**
     * 解散公会
     */
    @Test
    public void disbandGuild() {
        String uri = "/guild/disband";
        Map<String, String> params = new LinkedHashMap<>();
        // todo 获取所有公会id和name 随机取一个公会id作为测试参数
        params.put("guildId", "1729391053137962401");
        String result = post(params, uri);
    }

    /**
     * 公会会长转移
     */
    @Test
    public void guildOwnerTransfer() {
        String uri = "/guild/ownerTransfer";
        Map<String, String> params = new LinkedHashMap<>();
        // todo 获取所有公会id和name 随机取一个公会id作为测试参数
        params.put("guildId", "383680247304774");
        params.put("roleId", "58086030552467210");
        String result = post(params, uri);
    }

    /**
     * 修改行会公告
     */
    @Test
    public void updateNotice() {
        String uri = "/guild/updateNotice";
        Map<String, String> params = new LinkedHashMap<>();
        // todo 获取所有公会id和name 随机取一个公会id作为测试参数
        params.put("guildId", "383680247304774");
        params.put("bulletin", "修改 bulletin 公告");
        String result = post(params, uri);
        System.out.println(result);
    }

    /**
     * 查看排行榜
     */
    @Test
    public void queryRanker() {
        String uri = "/ranker/query";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("career", "0");
        String result = post(params, uri);
    }

    /**
     * 热更资源
     */
    @Test
    public void hotSwapRes() {
        String uri = "/system/hotswap_res";
        Map<String, String> params = new LinkedHashMap<>();
        String result = post(params, uri);
    }


    /**
     * 热更脚本
     */
    @Test
    public void hotSwapScript() {
        String uri = "/system/hotswap_script";
        Map<String, String> params = new LinkedHashMap<>();
        String result = post(params, uri);
    }

    /**
     * 关闭运营活动
     */
    @Test
    public void closeOperateAct() {
        String uri = "/operateActivity/close";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("operateActOpenId", "201");
        String result = post(params, uri);
    }


    /**
     * 热更新运营活动
     */
    @Test
    public void hotswapOperateAct() {
        String uri = "/operateActivity/hotswap";
        Map<String, String> params = new LinkedHashMap<>();
        String result = post(params, uri);
    }
    /**
     * 热更新活动
     */
    @Test
    public void hotswapAct() {
        String uri = "/system/hotswap_activity";
        Map<String, String> params = new LinkedHashMap<>();
        String result = post(params, uri);
    }


    String content1 = "1995年中共执政当局开始寻求强化法轮功的组织构架及与政府的关系。" +
            "中国政府的国家体委、公共健康部和气功科研会，访问李洪志，要求联合成立法轮功协会，但李洪志表示拒绝。" +
            "同年，气功科研会通过一项新规定，命令所有气功分会必须建立中国共产党党支部，但李洪志再次表示拒绝。" +
            "李洪志与中国气功科研会的关系在1996年持续恶化。" +
            "1996年3月，法轮功因拒不接受中国气功协会新负责人在气功团体内部收取会员费创收和成立中国共产党党支部组织的要求，" +
            "主动申请退出中国气功协会和中国气功科研会,以独立非政府形式运作。" +
            "自此，李洪志及其法轮功脱离了中国气功协会中的人脉和利益交换，同时失去了功派在中国政府体制系统的保护。" +
            "法轮功申请退出中国气功协会，是与中国政府对气功的态度产生变化相对应的;" +
            "当时随气功激进反对者在政府部门中的影响力增加，中国政府开始控制和影响各气功组织。" +
            "90年代中期，中国政府主管的媒体开始发表文章批评气功。" +
            "法轮功起初并没有受批评，但在1996年3月退出中国气功协会后，失去了政府体制的保护。";

    String content = "等会啊服你习习近平";
    /**
     * 过滤器
     */
    @Test
    public void textKeyWord() {
        String uri = "/keyWord/filter";
        uri = uri + "?filter=" + content;
        String s = get(uri);
        System.out.println(s);
    }

    @Test
    public void textssss() {
        String uri = "/keyWord/filter";
        uri = uri + "?filter=" + content;
        System.out.println(uri.charAt(203) + "" + uri.charAt(204) + uri.charAt(205));
    }

    /**
     * 过滤器
     */

    @Test
    public void textKeyWordRelease() {

        String uri = "/keyWord/filter";
        uri = uri + "?filter=" + content;

        int testNum = 1;
        String s = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            s = get(uri);
        }

        long end = System.currentTimeMillis();
//        Assert.assertEquals(((end - start)), 1l);
    }
    //http://10.40.2.68/login/user/token
    @Test
    public void a() {

        HttpUtil.doPost("http://10.40.2.68/login/user/token", Collections.EMPTY_MAP);
    }
    /**
     * 测试方法
     *
     * @param map
     */
    private String post(Map<String, String> map, String uri) {
        //String url = "http://192.168.5.128:11001" + uri; // 内网一
        //String url = "http://10.40.2.68:11000" + uri; // 俞樟鹏
        String url = "http://10.42.0.50:11000" + uri; // 本机 游戏服务器
        //String url = "http://10.42.0.35:11000" + uri;
        //String url = "http://10.42.0.50:8089" + uri; // 本机 gmweb
        //String url = "http://106.52.215.79:8015" + uri;// XY 线上
        Collection<String> values = map.values();
        String sign = SignUtil.getMD5ForGM(values.toArray(new String[values.size()]));
        map.put("sign", sign);
        System.out.println("参数: " + map);
        System.out.println(url);
        return HttpUtil.doPost(url, map);
    }


    /**
     * 测试方法
     */
    private String get(String uri) {
        //System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        //System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        //System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");

        //String url = "http://10.42.0.50:11000" + uri;
        String url = "http://10.42.0.50:8089" + uri;
        return HttpUtil.doGet(url);
    }

}
