package com.example.demo4.testObj1;

/**
 * 举报日志，用于gm消费
 */
public class ReportLog {

    private String platform;

    // 区服id
    private int lobbyId;

    // 被举报人账号
    private String beReportedUserId;
    // 被举报人playerId
    private long beReportedPlayerId;

    // 被举报人角色名称
    private String beReportedPlayerName;

    // 举报原因
    private int reason;

    // 详细描述
    private String detail;

    // 举报人账号
    private String reportUserId;
    // 举报人playerId
    private long reportPlayer;

    // 举报人角色名称
    private String reportPlayerName;

    // 副本id
    private int dungeonId;

    // 举报时间戳
    private long reportTime;

    //备注
    private String note;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getBeReportedUserId() {
        return beReportedUserId;
    }

    public void setBeReportedUserId(String beReportedUserId) {
        this.beReportedUserId = beReportedUserId;
    }

    public long getBeReportedPlayerId() {
        return beReportedPlayerId;
    }

    public void setBeReportedPlayerId(long beReportedPlayerId) {
        this.beReportedPlayerId = beReportedPlayerId;
    }

    public String getBeReportedPlayerName() {
        return beReportedPlayerName;
    }

    public void setBeReportedPlayerName(String beReportedPlayerName) {
        this.beReportedPlayerName = beReportedPlayerName;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(String reportUserId) {
        this.reportUserId = reportUserId;
    }

    public long getReportPlayer() {
        return reportPlayer;
    }

    public void setReportPlayer(long reportPlayer) {
        this.reportPlayer = reportPlayer;
    }

    public String getReportPlayerName() {
        return reportPlayerName;
    }

    public void setReportPlayerName(String reportPlayerName) {
        this.reportPlayerName = reportPlayerName;
    }

    public int getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(int dungeonId) {
        this.dungeonId = dungeonId;
    }

    public long getReportTime() {
        return reportTime;
    }

    public void setReportTime(long reportTime) {
        this.reportTime = reportTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ReportLog{" +
                "platform=" + platform +
                "lobbyId=" + lobbyId +
                ", beReportedUserId='" + beReportedUserId + '\'' +
                ", beReportedPlayerId=" + beReportedPlayerId +
                ", beReportedPlayerName='" + beReportedPlayerName + '\'' +
                ", reason='" + reason + '\'' +
                ", detail='" + detail + '\'' +
                ", reportUserId='" + reportUserId + '\'' +
                ", reportPlayer=" + reportPlayer +
                ", reportPlayerName='" + reportPlayerName + '\'' +
                ", dungeonId=" + dungeonId +
                ", reportTime=" + reportTime +
                ", note=" + note +
                '}';
    }
}
