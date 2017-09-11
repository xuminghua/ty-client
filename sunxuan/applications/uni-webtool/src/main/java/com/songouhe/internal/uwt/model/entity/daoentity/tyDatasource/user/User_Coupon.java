package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.user;

import java.sql.Timestamp;

public class User_Coupon {
    private Long id;

    private Long user_id;

    private Long coupon_id;

    private Long product_id;

    private Timestamp exchange_deadline;

    private Timestamp use_deadline;

    private int status;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Long coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Timestamp getExchange_deadline() {
        return exchange_deadline;
    }

    public void setExchange_deadline(Timestamp exchange_deadline) {
        this.exchange_deadline = exchange_deadline;
    }

    public Timestamp getUse_deadline() {
        return use_deadline;
    }

    public void setUse_deadline(Timestamp use_deadline) {
        this.use_deadline = use_deadline;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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