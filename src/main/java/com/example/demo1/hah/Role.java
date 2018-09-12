package com.example.demo1.hah;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     * 上一次退出的时间
     */
    private Date lastLoginOutTime;
    /**
     * 角色等级
     */
    private Integer level;
    /**
     * 登录天数
     */
    private Integer loginDays;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 充值金额
     */
    private Integer reCharge;
    /**
     * 角色id
     */
    private String rid;
    /**
     * 会员等级
     */
    private Integer vipLevel;







    public Date getLastLoginOutTime() {
        return lastLoginOutTime;
    }

    public void setLastLoginOutTime(Date lastLoginOutTime) {
        this.lastLoginOutTime = lastLoginOutTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLoginDays() {
        return loginDays;
    }

    public void setLoginDays(Integer loginDays) {
        this.loginDays = loginDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReCharge() {
        return reCharge;
    }

    public void setReCharge(Integer reCharge) {
        this.reCharge = reCharge;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }
}
