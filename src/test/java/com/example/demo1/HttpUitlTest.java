package com.example.demo1;

import com.example.demo1.demo.HttpUtil;
import com.example.demo1.demo.SignUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
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
    public void recharge() {
        String uri = "/item/recharge";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "傲慢的巴里");
        params.put("count", "222");
        params.put("type", "true");
        post(params, uri);
    }

    /**
     * 公告接口
     */
    @Test
    public void sendGMNotice() {
        String uri = "/message/sendNotice";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "1");
        params.put("type", "1");
        params.put("content", "xxxxx");
        params.put("color", "#FE0000");
        params.put("stime", "11111");
        params.put("etime", "1560000000");
        params.put("ltime", "1560000000");
        params.put("time", "45");
        post(params, uri);
    }


    /**
     * 邮件接口 个人
     */
    @Test
    public void sendSomeRoleGMEmail1() {
        String uri = "/message/sendEmail";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("title", "体验游戏");
        //params.put("subject", "xxxxxxxxxxx");
        params.put("content", "222");
        params.put("items", "42001|1&101|300");
        params.put("roleNames", "傲慢的巴里");
        params.put("closeDay", "3");
        post(params, uri);
    }

    /**
     * 邮件接口 全服
     */
    @Test
    public void sendSomeRoleGMEmail2() {
        String uri = "/message/sendEmail";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "2");
        params.put("title", "xxxx");
        //params.put("subject", "xxxxxxxxxxx");
        params.put("content", "xxxxxxxxxxx");
        params.put("items", "42001|1");
        //params.put("key", "内向的");
        params.put("closeDay", "3");
        post(params, uri);
    }


    /**
     * 根据id查询角色接口
     */
    @Test
    public void selectId() {
        String uri = "/role/selectId";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleId", "383579202062115");
        post(params, uri);
    }

    /**
     * 根据角色名称查询角色接口
     */
    @Test
    public void selectName() {
        String uri = "/role/selectName";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "庆小猪");
        post(params, uri);
    }

    /**
     * 封禁角色
     */
    @Test
    public void setRoleLock() {
        String uri = "/role/setRoleLockAndChat";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("roleName", "aaa");
        params.put("lockTime", "111111");
        params.put("reason", "傲慢的巴里开外挂了");
        post(params, uri);
    }


    /**
     * 解封角色
     */
    @Test
    public void setRoleUnLock() {
        String uri = "/role/setRoleLockAndChat";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("roleName", "傲慢的巴里");
        params.put("lockTime", "0");
        params.put("reason", "傲慢的巴里私底下交易给钱了");// 禁封理由
        post(params, uri);
    }


    /**
     * 设置禁言
     */
    @Test
    public void chatBan() {
        String uri = "/role/setRoleLockAndChat";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "2");
        params.put("roleName", "傲慢的巴里");
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
        String uri = "/role/setRoleLockAndChat";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("channelType", "1");
        params.put("banType", "1");
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
        System.out.println(result);
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
        params.put("guildId", "383681319098674");
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
        params.put("guildId", "383680247304774");
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




    String content = "1995年中共执政当局开始寻求强化法轮功的组织构架及与政府的关系。" +
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

        java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
        java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");

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

    /**
     * 测试方法
     *
     * @param map
     */
    private String post(Map<String, String> map, String uri) {
        //String url = "http://192.168.5.128:11001" + uri;
        String url = "http://10.42.0.50:11000" + uri;
        //String url = "http://10.42.0.35:11000" + uri;
        //String url = "http://10.42.0.50:8089" + uri;
        Collection<String> values = map.values();
        String sign = SignUtil.getMD5ForGM(values.toArray(new String[values.size()]));
        map.put("sign", sign);
        System.out.println("参数: " + map);
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
