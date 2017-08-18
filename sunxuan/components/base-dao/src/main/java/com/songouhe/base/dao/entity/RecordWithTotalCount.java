package com.songouhe.base.dao.entity;

import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 17-7-27
 */
public class RecordWithTotalCount {
    private List<Object> items;
    private long totalCount;

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public RecordWithTotalCount(){

    }
}
