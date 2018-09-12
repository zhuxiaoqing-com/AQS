package com.example.demo1;

import com.example.demo1.demo.HttpUtil;
import com.example.demo1.demo.SignUtil;
import com.example.demo1.demo.TimeUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author HSimon
 */
public class mainClass {
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



    /**
     * 测试方法
     * @param map
     */
    private void post(Map<String, String> map, String uri) {
        String url = "http://10.42.0.50:11000" + uri;
        Collection<String> values = map.values();
        String sign = SignUtil.getMD5ForGM(values.toArray(new String[values.size()]));
        map.put("sign", sign);
        System.out.println("参数: " + map);
        HttpUtil.doPost(url, map);
    }
}
