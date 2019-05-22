package com.example.demo1;

import com.example.demo1.demo.HttpUtil;
import com.example.demo1.demo.SignUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author HSimon
 */
public class HttpUitlTest {
    /**
     * 道具查询接口
     */
    @Test
    public void itemQuery() {
        String uri = "/item/query";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("itemId", "1");
        post(params, uri);
    }

    /**
     * 道具补偿
     */
    @Test
    public void compensate() {
        String uri = "/item/compensate";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("items", "42001|1");
        post(params, uri);
    }

    /**
     * 充值接口
     */
    @Test
    public void recharge() {
        String uri = "/item/recharge";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("count", "222");
        params.put("type", "1");
        post(params, uri);
    }

    /**
     * 公告接口
     */
    @Test
    public void sendGMNotice() {
        String uri = "/message/GMNotice";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("noticeModel", "xxxx");
        params.put("noticeTimes", "222");
        params.put("context", "xxxxxxxxxxx");
        post(params, uri);
    }


    /**
     * 邮件接口 个人
     */
    @Test
    public void sendSomeRoleGMEmail1() {
        String uri = "/message/GMEmail";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("title", "xxxx");
        //params.put("subject", "xxxxxxxxxxx");
        params.put("content", "xxxxxxxxxxx");
        params.put("items", "42001|1");
        params.put("key", "内向的");
        params.put("closeDay", "3");
        post(params, uri);
    }

    /**
     * 邮件接口 全服
     */
    @Test
    public void sendSomeRoleGMEmail2() {
        String uri = "/message/GMEmail";
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
        params.put("roleId", "381949133783432");
        post(params, uri);
    }

    /**
     * 根据角色名称查询角色接口
     */
    @Test
    public void selectName() {
        String uri = "/role/selectName";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        post(params, uri);
    }

    /**
     * 封禁角色
     */
    @Test
    public void setRoleLock() {
        String uri = "/role/setRoleLock";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("lockTime", "2222");
        params.put("reason", "内向的罗夫斯xxxxx");
        post(params, uri);
    }


    /**
     * 解封角色
     */
    @Test
    public void setRoleUnLock() {
        String uri = "/role/setRoleUnLock";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("lockTime", "2222");
        params.put("reason", "内向的罗夫斯xxxxx");
        post(params, uri);
    }


    /**
     * 设置禁言
     */
    @Test
    public void chatBan() {
        String uri = "/role/chatBan";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("banTime", "2222");
        params.put("reason", "xxxxxx");
        params.put("channelType", "1");
        params.put("banType", "1");
        post(params, uri);
    }


    /**
     * 解除禁言
     */
    @Test
    public void chatUnBan() {
        String uri = "/role/chatUnBan";
        Map<String, String> params = new HashMap<>();
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
        Map<String, String> params = new HashMap<>();
        post(params, uri);
    }


    /**
     * 测试 新 封号
     */
    @Test
    public void setRoleLockAndChat1() {
        String uri = "/role/setRoleLockAndChat";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "1");
        params.put("roleName", "内向的罗夫斯");
        params.put("lockTime", "2222");
        // params.put("reason", "内向的罗夫斯xxxxx");
        post(params, uri);
    }

    /**
     * 测试 新 聊天
     */
    @Test
    public void setRoleLockAndChat2() {
        String uri = "/role/setRoleLockAndChat";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", "2");
        params.put("roleName", "内向的罗夫斯");
        params.put("banTime", "2222");
        params.put("reason", "xxxxxx");
        params.put("channelType", "1");
        params.put("banType", "1");
        post(params, uri);
    }


    /**
     * 道具删除
     */
    @Test
    public void modify() {
        String uri = "/item/modify";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleId", "381949133783432");
        params.put("location", "1");
        params.put("id", "1");
        params.put("count", "1");
        post(params, uri);
    }

    /**
     * 道具查询
     */
    @Test
    public void query() {
        String uri = "/item/query";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("roleName", "内向的罗夫斯");
        params.put("location", "2");
        //params.put("urlAdd", "xxxx");
        //params.put("urlName", "xxxx");
        post(params, uri);
    }

    /**
     * 公告接口
     */
    @Test
    public void sendGMNotice1() {
        String uri = "/message/GMNotice";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "1");
        params.put("type", "14");
        params.put("str", "xxxx");
        params.put("color", "xxxx");
        params.put("stime", "2018-08-29 16:45:23");
        params.put("etime", "2018-08-29 16:46:23");
        params.put("ltime", "1111");
        //params.put("urlAdd", "xxxx");
        //params.put("urlName", "xxxx");
        post(params, uri);
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
     * 公告接口
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
     * 公告接口
     */

    @Test
    public void textKeyWordRelease() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
        String uri = "/keyWord/filter";
        uri = uri + "?filter=" + content;

        int testNum = 100_000;
        String s = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            s = get(uri);
        }

        long end = System.currentTimeMillis();

        System.out.println(start - end / 1000);

        System.out.println(s);
    }

    /**
     * 测试方法
     *
     * @param map
     */
    private void post(Map<String, String> map, String uri) {
        //String url = "http://10.42.0.50:11000" + uri;
        String url = "http://10.42.0.50:8089" + uri;
        Collection<String> values = map.values();
        String sign = SignUtil.getMD5ForGM(values.toArray(new String[values.size()]));
        map.put("sign", sign);
        System.out.println("参数: " + map);
        HttpUtil.doPost(url, map);
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
