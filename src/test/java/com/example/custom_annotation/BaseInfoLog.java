package com.example.custom_annotation;


/**
 * @author Ouyangweiliang Created on 2018/8/27
 */
@Table(tableName = "log_union_rename", indexes = {
        @Index(name = "xx", columnNames = {"a", "b"}),
        @Index(name = "ss", columnNames = {"a", "b"}, unique = true)
})
public class BaseInfoLog extends AbstractLog {

    /**
     * 表Id,主键
     */
    @Column(fieldType = FieldType.INT, size = 11, commit = "ID", colName = "id", autoIncrement = true)
    private int id;


    /**
     * 平台Id
     */
    @Column(fieldType = FieldType.INT, size = 11, commit = "平台Id")
    private int platformId;


    /**
     * 服务器Id
     */
    @Column(fieldType = FieldType.INT, size = 11, commit = "服务器Id")
    private int serverId;

    /**
     * 时间
     */
    @Column(fieldType = FieldType.BIGINT, size = 20, commit = "时间")
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
