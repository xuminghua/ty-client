package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource;

import java.util.Date;

public class Image_Info {
    private Long id;

    private Long objectId;

    private String imageUrl;

    private Boolean type;

    private Boolean orderNum;

    private Boolean mainPage;

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

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Boolean orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getMainPage() {
        return mainPage;
    }

    public void setMainPage(Boolean mainPage) {
        this.mainPage = mainPage;
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