package com.songouhe.internal.uwt.model;

import java.io.Serializable;

/**
 * @author sunxuan
 * @version 1.0 17-2-14
 * 对于1个Workspace，如果RecordOperationPrivModel没有配置，则默认为封闭所有操作权限给所有人。
 * 如果createPrivilege配置了ALL，则对所有人开放权限。
 */
public class RecordOperationPrivModel implements Serializable {
    /**
     * create权限不需要判断是否是self，teamer等角色限定的权限，只需要判断系统权限，所以用String而不是String[]
     */
    private String createPrivilege;
    private String[] updatePrivilege;
    private String[] deletePrivilege;

    /**
     * disableField是专门针对field的修改权限
     */
    private String[] disabledFields;

    public RecordOperationPrivModel() {
    }

    public String getCreatePrivilege() {
        return createPrivilege;
    }

    public void setCreatePrivilege(String createPrivilege) {
        this.createPrivilege = createPrivilege;
    }

    public String[] getUpdatePrivilege() {
        return updatePrivilege;
    }

    public void setUpdatePrivilege(String[] updatePrivilege) {
        this.updatePrivilege = updatePrivilege;
    }

    public String[] getDeletePrivilege() {
        return deletePrivilege;
    }

    public void setDeletePrivilege(String[] deletePrivilege) {
        this.deletePrivilege = deletePrivilege;
    }

    public String[] getDisabledFields() {
        return disabledFields;
    }

    public void setDisabledFields(String[] disabledFields) {
        this.disabledFields = disabledFields;
    }
}
