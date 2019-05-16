package com.example.demo4;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class GameServer implements Serializable {
    private Long id;

    private Long serverId;
    private String platformId;

    private String platformName;

    private String serverName;

    private String ip;

    /**
     * 0, "新增"
     * 1, "发布"
     * 2, "下架"
     */
    private Integer status;

    /**
     * 状态名称
     */
    private String statusName;

    private Date createDate;

    private Date updateDate;

    /**
     * 0, "即将开启"
     * 1, "火爆开启"
     * 2, "流畅"
     * 3, "停服"
     */
    private Integer serverStatus;

    /**
     * 服务器状态名称
     */
    private String serverStatusName;

    private Integer port;

    /**
     * 0, "普通"
     * 1, "推荐"
     */
    private Integer recommend;

    /**
     * 推荐名称
     */
    private String recommendName;

    private Integer orderNum;

    private String innerIp;

    private Integer innerPort;

    /**
     * 1, "体验服"
     * 2, "正式服"
     */
    private Integer serverType;

    /**
     * 类型名称
     */
    private String serverTypeName;

    private Date openDate;

    private String clientCdn;

    private String mysqlUrl;

    private Byte isCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Integer serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getServerStatusName() {
        return serverStatusName;
    }

    public void setServerStatusName(String serverStatusName) {
        this.serverStatusName = serverStatusName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getInnerIp() {
        return innerIp;
    }

    public void setInnerIp(String innerIp) {
        this.innerIp = innerIp == null ? null : innerIp.trim();
    }

    public Integer getInnerPort() {
        return innerPort;
    }

    public void setInnerPort(Integer innerPort) {
        this.innerPort = innerPort;
    }

    public Integer getServerType() {
        return serverType;
    }

    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    public String getServerTypeName() {
        return serverTypeName;
    }

    public void setServerTypeName(String serverTypeName) {
        this.serverTypeName = serverTypeName;
    }

    public Date getOpenDate() {
        return openDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getClientCdn() {
        return clientCdn;
    }

    public void setClientCdn(String clientCdn) {
        this.clientCdn = clientCdn == null ? null : clientCdn.trim();
    }

    public String getMysqlUrl() {
        return mysqlUrl;
    }

    public void setMysqlUrl(String mysqlUrl) {
        this.mysqlUrl = mysqlUrl == null ? null : mysqlUrl.trim();
    }

    public Byte getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Byte isCollection) {
        this.isCollection = isCollection;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "id=" + id +
                ", serverId=" + serverId +
                ", platformId='" + platformId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", serverName='" + serverName + '\'' +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", serverStatus=" + serverStatus +
                ", serverStatusName='" + serverStatusName + '\'' +
                ", port=" + port +
                ", recommend=" + recommend +
                ", recommendName='" + recommendName + '\'' +
                ", orderNum=" + orderNum +
                ", innerIp='" + innerIp + '\'' +
                ", innerPort=" + innerPort +
                ", serverType=" + serverType +
                ", serverTypeName='" + serverTypeName + '\'' +
                ", openDate=" + openDate +
                ", clientCdn='" + clientCdn + '\'' +
                ", mysqlUrl='" + mysqlUrl + '\'' +
                ", isCollection=" + isCollection +
                '}';
    }
}