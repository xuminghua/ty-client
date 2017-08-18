package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource;

import java.util.Date;

public class Travel_Info {
    private Long id;

    private String uuid;

    private String title;

    private Long productId;

    private Long authorId;

    private Long views;

    private Long likes;

    private Boolean type;

    private Boolean status;

    private Boolean isTop;

    private Date createTime;

    private Integer tmpIntvalue;

    private String tmp1Charvalue;

    private String tmp2Charvalue;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}