package com.songouhe.internal.uwt.model.viewconfig;

import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.enums.SubFieldTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-24
 */
public class UniFieldModel implements Serializable {
    /**
     * 在infoForm，searchArea的view中直接使用name即可，在grid中转为dataIndex
     */
    private String name;
    /**
     * 在infoForm，searchArea的view中直接使用fieldLabel即可，在grid中转为text
     */
    private String fieldLabel;
    private SubFieldTypeEnum xtype;  //xtype
    private String fieldDefaultValue;
    /**
     * privilege用于配置view的读写属性
     */
    private PrivilegeModel privilege = new PrivilegeModel();
    /**
     * privilege用于配置view中record的读写属性
     */
    private PrivilegeModel record_privilege = new PrivilegeModel();
    /**
     * store和dataModel用于combobox
     */
    private List store;
    private String dataModel;

    private String format;

    /**
     * 只用于infoFormPanel，作为fieldset的节点，items才是其中的详细field
     */
    private List<UniFieldModel> items;
    /**
     * 用于infoForm，searchArea,表示不能为空
     */
    private boolean notNull;


    public UniFieldModel(SubFieldTypeEnum fieldtype, String name, String fieldLabel,
                         String fieldDefaultValue, String rPrivilege, String wPrivilege) {
        this.xtype = fieldtype;
        this.name = name;
        this.fieldLabel = fieldLabel;
        this.fieldDefaultValue = fieldDefaultValue;
        this.privilege = new PrivilegeModel(rPrivilege, wPrivilege);

    }

    public UniFieldModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }


    public PrivilegeModel getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
    }

    public List<UniFieldModel> getItems() {
        return items;
    }

    public void setItems(List<UniFieldModel> items) {
        this.items = items;
    }

    public SubFieldTypeEnum getXtype() {
        return xtype;
    }

    public void setXtype(SubFieldTypeEnum xtype) {
        this.xtype = xtype;
    }

    public String getFieldDefaultValue() {
        return fieldDefaultValue;
    }

    public void setFieldDefaultValue(String fieldDefaultValue) {
        this.fieldDefaultValue = fieldDefaultValue;
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
    public PrivilegeModel getRecord_privilege() {
        return record_privilege;
    }

    public void setRecord_privilege(PrivilegeModel record_privilege) {
        this.record_privilege = record_privilege;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getDataModel() {
        return dataModel;
    }

    public void setDataModel(String dataModel) {
        this.dataModel = dataModel;
    }
}
