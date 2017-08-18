package com.songouhe.internal.uwt.view.detailField;

import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.view.UniFieldView;

import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 17-1-24
 */
public class FormField {
    private UniFieldView uniFieldView;
    private String fieldLabel;
    private String name;
    private String xtype;
    private List store;
    private String format;
    private boolean notNull;


    public FormField(UniFieldView inUniFieldView){
        this.uniFieldView = inUniFieldView;
        this.fieldLabel = inUniFieldView.getFieldLabel();
        this.name = inUniFieldView.getName();
        if(inUniFieldView.getXtype() != null)
            this.xtype = inUniFieldView.getXtype().getName();
        this.store = inUniFieldView.getStore();
        this.format = inUniFieldView.getFormat();
        this.notNull = inUniFieldView.isNotNull();
        String dataModel = inUniFieldView.getDataModel();
        if(dataModel != null && !dataModel.equals("") && ConfigUtil.getCombosForView() != null
                && ConfigUtil.getCombosForView().get(dataModel) != null){
            this.store = (List) ConfigUtil.getCombosForView().get(dataModel);

        }
    }
    public FormField(){}

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXtype() {
        return xtype;
    }

    public void setXtype(String xtype) {
        this.xtype = xtype;
    }

    public List getStore() {
        return store;
    }

    public void setStore(List store) {
        this.store = store;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
