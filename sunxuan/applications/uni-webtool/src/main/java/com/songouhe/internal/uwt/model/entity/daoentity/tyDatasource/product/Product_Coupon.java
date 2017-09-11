package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.product;

import java.sql.Timestamp;

public class Product_Coupon {
    private Long id;

    private String uuid;

    private String title;

    private int type;

    private String info;

    private Long product_id;

    private Integer total;

    private Integer reserved;

    private Timestamp pick_end_time;

    private Timestamp exchange_deadline;

    private Timestamp use_deadline;

    private Timestamp pick_start_time;

    private Byte exchange_price;

    private Boolean status;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }


    public Byte getExchange_price() {
        return exchange_price;
    }

    public void setExchange_price(Byte exchange_price) {
        this.exchange_price = exchange_price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getPick_end_time() {
        return pick_end_time;
    }

    public void setPick_end_time(Timestamp pick_end_time) {
        this.pick_end_time = pick_end_time;
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

    public Timestamp getPick_start_time() {
        return pick_start_time;
    }

    public void setPick_start_time(Timestamp pick_start_time) {
        this.pick_start_time = pick_start_time;
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