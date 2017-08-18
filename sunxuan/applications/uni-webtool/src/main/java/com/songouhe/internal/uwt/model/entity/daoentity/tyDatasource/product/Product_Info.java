package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.product;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author sunxuan
 * @version 1.0 17-8-8
 */
public class Product_Info {
    private int id;
    private String name;
    private String title;
    private String content;
    private int merchant_id;
    private BigDecimal price;
    private BigDecimal discount;
    private Boolean is_top;
    private Boolean is_self;
    private int audit_status;
    private String ages;
    private String link;
    private int likes;
    private String address_point;
    private String address;
    private String city;
    private int type;
    private int status;
    private Timestamp start_time;
    private Timestamp end_time;
    private Timestamp expiry_time;
    private Timestamp create_time;
    private int views;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }



    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public Timestamp getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(Timestamp expiry_time) {
        this.expiry_time = expiry_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public Boolean getIs_top() {
        return is_top;
    }

    public void setIs_top(Boolean is_top) {
        this.is_top = is_top;
    }

    public Boolean getIs_self() {
        return is_self;
    }

    public void setIs_self(Boolean is_self) {
        this.is_self = is_self;
    }

    public int getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(int audit_status) {
        this.audit_status = audit_status;
    }

    public String getAddress_point() {
        return address_point;
    }

    public void setAddress_point(String address_point) {
        this.address_point = address_point;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Product_Info(){

    }
}
