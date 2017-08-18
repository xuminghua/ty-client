package com.songouhe.base.dao.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author sunxuan
 * @version 1.0 17-8-8
 */
public class Product_Info {
    private int id;
    private String name;
    private String title;
    private String content;
    private int merchant_Id;

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

    public int getMerchant_Id() {
        return merchant_Id;
    }

    public void setMerchant_Id(int merchant_Id) {
        this.merchant_Id = merchant_Id;
    }


}
