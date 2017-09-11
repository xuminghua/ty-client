package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.common;


import java.sql.Timestamp;

public class Image_Info {
    private Long id;

    private Long object_id;

    private String image_url;

    private Boolean type;

    private Boolean order_num;

    private Boolean main_page;

    private Timestamp create_time;

    private Integer tmp_intvalue;

    private String tmp1_charvalue;

    private String tmp2_charvalue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObject_id() {
        return object_id;
    }

    public void setObject_id(Long object_id) {
        this.object_id = object_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Boolean order_num) {
        this.order_num = order_num;
    }

    public Boolean getMain_page() {
        return main_page;
    }

    public void setMain_page(Boolean main_page) {
        this.main_page = main_page;
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