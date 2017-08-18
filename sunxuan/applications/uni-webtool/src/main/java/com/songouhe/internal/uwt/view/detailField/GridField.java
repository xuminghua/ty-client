package com.songouhe.internal.uwt.view.detailField;

import com.songouhe.internal.uwt.view.UniFieldView;

/**
 * @author sunxuan
 * @version 1.0 17-1-24
 */
public class GridField {
    private UniFieldView uniFieldView;
    private String text;
    private String dataIndex;
    private String format;
    private String xtype;

    public GridField(UniFieldView inUniFieldView){
        this.uniFieldView = inUniFieldView;
        this.text = inUniFieldView.getFieldLabel();
        this.dataIndex = inUniFieldView.getName();
        if(inUniFieldView.getXtype() != null)
        this.xtype = inUniFieldView.getXtype().getName();
    }
    public GridField(){}
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getXtype() {
        return xtype;
    }

    public void setXtype(String xtype) {
        this.xtype = xtype;
    }
}
