package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.common;


import java.sql.Timestamp;

public class Common_Trust {
    private Integer id;

    private String uuid;

    private Long img_id;

    private String content;

    private Boolean status;

    private Timestamp create_time;

    private Integer tmp_intvalue;

    private String tmp1_charvalue;

    private String tmp2_charvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getImg_id() {
        return img_id;
    }

    public void setImg_id(Long img_id) {
        this.img_id = img_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
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
}