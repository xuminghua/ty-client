package com.songouhe.internal.uwt.view.detailField;

import com.songouhe.internal.uwt.view.UniFieldView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 17-1-24
 */
public class FormFieldSet {
    private UniFieldView uniFieldView;
    private boolean readOnly;
    private String title;
    private List items;

    public FormFieldSet(UniFieldView inUniFieldView){
        this.uniFieldView = inUniFieldView;
        this.readOnly = uniFieldView.isReadOnly();
        this.title = uniFieldView.getName();
        if(uniFieldView.getItems() != null && uniFieldView.getItems().size() != 0)
            this.items = this.buildItemsFromUniFieldView(uniFieldView.getItems());

    }
    public FormFieldSet(){}

    public List buildItemsFromUniFieldView(List<UniFieldView> uniFieldViewItems){
        List result = new ArrayList();
        for(UniFieldView uniFieldView: uniFieldViewItems ){
                FormField formField = new FormField(uniFieldView) ;
                result.add(formField);
        }
        return result;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
