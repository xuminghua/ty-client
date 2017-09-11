package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.travel;

import java.sql.Timestamp;

public class Travel_Info {
    private Long id;

    private String uuid;

    private String title;

    private Long product_id;

    private Long author_id;

    private Long views;

    private Long likes;

    private Boolean type;

    private Boolean status;

    private Boolean is_top;

    private Timestamp create_time;

    private Integer tmp_intvalue;

    private String tmp1_charvalue;

    private String tmp2_charvalue;

    private String content;

    private String audit_comments;

    private Timestamp audit_time;


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

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
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

    public Boolean getIs_top() {
        return is_top;
    }

    public void setIs_top(Boolean is_top) {
        this.is_top = is_top;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public Integer getTmp_intvalue() {
        return tmp_intvalue;
    }

    public void setTmp_intvalue(Integer tmp_intvalue) {
        this.tmp_intvalue = tmp_intvalue;
    }

    public String getTmp1_charvalue() {
        return tmp1_charvalue;
    }

    public void setTmp1_charvalue(String tmp1_charvalue) {
        this.tmp1_charvalue = tmp1_charvalue;
    }

    public String getTmp2_charvalue() {
        return tmp2_charvalue;
    }

    public void setTmp2_charvalue(String tmp2_charvalue) {
        this.tmp2_charvalue = tmp2_charvalue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudit_comments() {
        return audit_comments;
    }

    public void setAudit_comments(String audit_comments) {
        this.audit_comments = audit_comments;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(Timestamp audit_time) {
        this.audit_time = audit_time;
    }
}