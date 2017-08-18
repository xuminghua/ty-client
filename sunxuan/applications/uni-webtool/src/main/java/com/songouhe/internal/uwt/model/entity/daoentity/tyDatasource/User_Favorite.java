package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource;

import java.util.Date;

public class User_Favorite {
    private Long id;

    private Long userId;

    private Long objectId;

    private Boolean type;

    private Date createTime;

    private Integer tmpIntvalue;

    private String tmp1Charvalue;

    private String tmp2Charvalue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTmpIntvalue() {
        return tmpIntvalue;
    }

    public void setTmpIntvalue(Integer tmpIntvalue) {
        this.tmpIntvalue = tmpIntvalue;
    }

    public String getTmp1Charvalue() {
        return tmp1Charvalue;
    }

    public void setTmp1Charvalue(String tmp1Charvalue) {
        this.tmp1Charvalue = tmp1Charvalue;
    }

    public String getTmp2Charvalue() {
        return tmp2Charvalue;
    }

    public void setTmp2Charvalue(String tmp2Charvalue) {
        this.tmp2Charvalue = tmp2Charvalue;
    }
}