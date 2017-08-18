package com.songouhe.internal.uwt.view;

import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.enums.SubFieldTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * field中间类，不是传到页面的最终data结构，而是仅用于field配置的读取和配置，各种panel的各种field都能用
 * 本类存储配置信息，最终需转换为页面所需结构类
 * @author sunxuan
 * @version 1.0 16-11-24
 */
public class UniFieldView extends AbstractView implements Serializable {
    /**
     * 在infoForm，searchArea的view中直接使用name即可，在grid中转为dataIndex
     */
    private String name;
    /**
     * 在infoForm，searchArea的view中直接使用fieldLabel即可，在grid中转为text
     */
    private String fieldLabel;
    private SubFieldTypeEnum xtype;  //xtype
    /**
     * 用于searchPanel 的field xtype为combobox时的枚举值
     */
    private List store;
    private String dataModel;
    /**
     * 用于resultGridPanel 的field xtype为datecolumn时，做date的format
     */
    private String format;
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
     * 只用于infoFormPanel，作为fieldset的节点，items才是其中的详细field
     */
    private List<UniFieldView> items;
    private boolean notNull;


    public UniFieldView(SubFieldTypeEnum fieldtype, String name, String fieldLabel,
                        String fieldDefaultValue, String rPrivilege, String wPrivilege) {
        this.xtype = fieldtype;
        this.name = name;
        this.fieldLabel = fieldLabel;
        this.fieldDefaultValue = fieldDefaultValue;
        this.privilege = new PrivilegeModel(rPrivilege, wPrivilege);

    }

    public UniFieldView() {
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

    @Override
    public List subViewItems() {
        return this.items;
    }

    public List<UniFieldView> getItems() {
        return items;
    }

    public void setItems(List<UniFieldView> items) {
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
    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getDataModel() {
        return dataModel;
    }

    public void setDataModel(String dataModel) {
        this.dataModel = dataModel;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
