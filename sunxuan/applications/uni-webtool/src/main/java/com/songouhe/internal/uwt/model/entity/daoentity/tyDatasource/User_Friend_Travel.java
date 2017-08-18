package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource;

import java.util.Date;

public class User_Friend_Travel {
    private Long id;

    private Long travelId;

    private Long travelUserId;

    private Long friendId;

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

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    public Long getTravelUserId() {
        return travelUserId;
    }

    public void setTravelUserId(Long travelUserId) {
        this.travelUserId = travelUserId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
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