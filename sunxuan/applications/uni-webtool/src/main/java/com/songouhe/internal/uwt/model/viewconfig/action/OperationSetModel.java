package com.songouhe.internal.uwt.model.viewconfig.action;

import java.util.HashMap;

/**
 * @author sunxuan
 * @version 1.0 17-2-13
 */
public class OperationSetModel {
    /**
     * 当前workspace支持的操作配置
     * search,createByObj,update,delete,complete针对的是record的权限操作配置
     * 若某个HashMap为空，则不支持当前操作，界面上不应该显示那个button
     * 若某个OperationModel不为空，则至少包括权限为ALL对应的action
     * 若某个OperationModel不为空且权限对应的disable=true，则对应button为disable状态
     * 执行当前action所需的sql集合。key为所 需privilege，value为ActionModel
     * HashMap中的String为权限名，ActionModel[]为操作配置
     */

    private HashMap<String, ActionModel[]> search;
    private HashMap<String, ActionModel[]>  create;
    private HashMap<String, ActionModel[]>  update;
    private HashMap<String, ActionModel[]>  delete;
    private HashMap<String, ActionModel[]>  complete;

    private HashMap<String, String[]> disabledFields;

    public OperationSetModel() {
    }

    public HashMap<String, ActionModel[]> getSearch() {
        return search;
    }

    public void setSearch(HashMap<String, ActionModel[]> search) {
        this.search = search;
    }

    public HashMap<String, ActionModel[]> getCreate() {
        return create;
    }

    public void setCreate(HashMap<String, ActionModel[]> create) {
        this.create = create;
    }

    public HashMap<String, ActionModel[]> getUpdate() {
        return update;
    }

    public void setUpdate(HashMap<String, ActionModel[]> update) {
        this.update = update;
    }

    public HashMap<String, ActionModel[]> getDelete() {
        return delete;
    }

    public void setDelete(HashMap<String, ActionModel[]> delete) {
        this.delete = delete;
    }

    public HashMap<String, ActionModel[]> getComplete() {
        return complete;
    }

    public void setComplete(HashMap<String, ActionModel[]> complete) {
        this.complete = complete;
    }

    public HashMap<String, String[]> getDisabledFields() {
        return disabledFields;
    }

    public void setDisabledFields(HashMap<String, String[]> disabledFields) {
        this.disabledFields = disabledFields;
    }


}
