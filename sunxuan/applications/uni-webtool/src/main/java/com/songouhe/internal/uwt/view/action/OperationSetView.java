package com.songouhe.internal.uwt.view.action;

import com.songouhe.base.sso.action.PrivilegeInfo;
import com.songouhe.internal.uwt.model.viewconfig.action.ActionModel;
import com.songouhe.internal.uwt.model.viewconfig.action.OperationSetModel;

import java.util.Map;

/**
 * @author sunxuan
 * @version 1.0 17-2-13
 */
public class OperationSetView {
    /**
     * 当前workspace支持的操作配置
     */
    private OperationSetModel operationSetModel;

    /*
当前user支持的action集合。key为privilege，value为ActionView。包括所有的角色权限对应的action和1个最高级别的系统权限action
而对于update，delete，complete这种会针对固定角色(SELF,TEAMER,COORDINATOR)的配置，还是从OperationSetModel中直接取即可
 */
    private ActionModel[] defaultSearchAction;
    private ActionModel[] defaultCreateAction;
    private ActionModel[] defaultUpdateAction;
    private ActionModel[] defaultDeleteAction;
    private ActionModel[] defaultCompleteAction;
    /**
     * fieldChange是专门针对field的修改权限，action中只有fields字段是有效的
     */
    private String[] defaultFieldChange;

    private boolean hasDefaultPriv;


    public OperationSetView(OperationSetModel operationSetModel) {
        this.operationSetModel = operationSetModel;
    }

    public OperationSetModel getOperationSetModel() {
        return operationSetModel;
    }

    public void setOperationSetModel(OperationSetModel operationSetModel) {
        this.operationSetModel = operationSetModel;
    }

    public ActionModel[] getDefaultSearchAction() {
        return defaultSearchAction;
    }

    public void setDefaultSearchAction(ActionModel[] defaultSearchAction) {
        this.defaultSearchAction = defaultSearchAction;
    }

    public ActionModel[] getDefaultCreateAction() {
        return defaultCreateAction;
    }

    public void setDefaultCreateAction(ActionModel[] defaultCreateAction) {
        this.defaultCreateAction = defaultCreateAction;
    }

    public ActionModel[] getDefaultUpdateAction() {
        return defaultUpdateAction;
    }

    public void setDefaultUpdateAction(ActionModel[] defaultUpdateAction) {
        this.defaultUpdateAction = defaultUpdateAction;
    }

    public ActionModel[] getDefaultDeleteAction() {
        return defaultDeleteAction;
    }

    public void setDefaultDeleteAction(ActionModel[] defaultDeleteAction) {
        this.defaultDeleteAction = defaultDeleteAction;
    }

    public ActionModel[] getDefaultCompleteAction() {
        return defaultCompleteAction;
    }

    public void setDefaultCompleteAction(ActionModel[] defaultCompleteAction) {
        this.defaultCompleteAction = defaultCompleteAction;
    }

    public String[] getDefaultFieldChange() {
        return defaultFieldChange;
    }

    public void setDefaultFieldChange(String[] defaultFieldChange) {
        this.defaultFieldChange = defaultFieldChange;
    }

    public boolean isHasDefaultPriv() {
        return hasDefaultPriv;
    }

    public void setHasDefaultPriv(boolean hasDefaultPriv) {
        this.hasDefaultPriv = hasDefaultPriv;
    }

    /**
     * user第一次登陆当前view时需要缩小系统权限范围，只留下user可用的最高系统权限所对应的operation
     * @param inUserPrivilege 实际类型是treeMap
     * @return
     */
    public void buildUserOperations(PrivilegeInfo inUserPrivilege){
        if(operationSetModel == null || hasDefaultPriv)return ;
        hasDefaultPriv = true;
        Map.Entry<Integer, String> createSysPriv = null;
        Map.Entry<Integer, String> updateSysPriv = null;
        Map.Entry<Integer, String> deleteSysPriv = null;
        Map.Entry<Integer, String> searchSysPriv = null;
        Map.Entry<Integer, String> completeSysPriv = null;
        Map.Entry<Integer, String> disableFieldsSysPriv = null;
        if(inUserPrivilege.getMapPrivileges() != null &&
                !inUserPrivilege.getMapPrivileges().isEmpty()) {
            int i = 0;
            for (Map.Entry<Integer, String> entry : inUserPrivilege.getMapPrivileges().entrySet()) {
                String sPriv = entry.getValue();
                if (operationSetModel.getCreate() != null &&
                        operationSetModel.getCreate().containsKey(sPriv)) {
                    createSysPriv = entry;
                    i++;
                }
                if (operationSetModel.getUpdate() != null &&
                        operationSetModel.getUpdate().containsKey(sPriv)) {
                    updateSysPriv = entry;
                    i++;
                }
                if (operationSetModel.getDelete() != null &&
                        operationSetModel.getDelete().containsKey(sPriv)) {
                    deleteSysPriv = entry;
                    i++;
                }
                if (operationSetModel.getSearch() != null &&
                        operationSetModel.getSearch().containsKey(sPriv)) {
                    searchSysPriv = entry;
                    i++;
                }
                if (operationSetModel.getComplete() != null &&
                        operationSetModel.getComplete().containsKey(sPriv)) {
                    completeSysPriv = entry;
                    i++;
                }
                if (operationSetModel.getDisabledFields() != null &&
                        operationSetModel.getDisabledFields().containsKey(sPriv)) {
                    disableFieldsSysPriv = entry;
                    i++;
                }
                if (i == 6) break;
            }
        }

        if(createSysPriv != null)
            this.defaultCreateAction =
                    operationSetModel.getCreate().get(createSysPriv.getValue());
        else if(operationSetModel.getCreate() != null)
            this.defaultCreateAction =
                    operationSetModel.getCreate().get("ALL");

        if(completeSysPriv != null)
            this.defaultCompleteAction =
                    operationSetModel.getComplete().get(completeSysPriv.getValue()) ;
        else if(operationSetModel.getComplete() != null)
            this.defaultCompleteAction =
                    operationSetModel.getComplete().get("ALL") ;

        if(deleteSysPriv != null)
            this.defaultDeleteAction =
                    operationSetModel.getDelete().get(deleteSysPriv.getValue());
        else if(operationSetModel.getDelete() != null)
            this.defaultDeleteAction =
                    operationSetModel.getDelete().get("ALL");

        if(searchSysPriv != null)
            this.defaultSearchAction =
                    operationSetModel.getSearch().get(searchSysPriv.getValue());
        else if(operationSetModel.getSearch() != null)
            this.defaultSearchAction =
                    operationSetModel.getSearch().get("ALL");

        if(updateSysPriv != null)
            this.defaultUpdateAction =
                    operationSetModel.getUpdate().get(updateSysPriv.getValue());
        else if(operationSetModel.getUpdate() != null)
            this.defaultUpdateAction = operationSetModel.getUpdate().get("ALL");

        if(disableFieldsSysPriv != null)
            this.defaultFieldChange =
                    operationSetModel.getDisabledFields().get(disableFieldsSysPriv.getValue());
        else if(operationSetModel.getDisabledFields() != null)
            this.defaultFieldChange = operationSetModel.getDisabledFields().get("ALL");


    }



}
