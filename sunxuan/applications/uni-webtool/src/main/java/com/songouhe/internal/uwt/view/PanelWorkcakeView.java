package com.songouhe.internal.uwt.view;

import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.enums.DetailWorkcakeTypeEnum;
import com.songouhe.internal.uwt.model.enums.OperationTypeEnum;
import com.songouhe.internal.uwt.model.enums.PanelLayoutTypeEnum;
import com.songouhe.internal.uwt.model.enums.SubPanelXTypeEnum;
import com.songouhe.internal.uwt.view.detailField.FormField;
import com.songouhe.internal.uwt.view.detailField.FormFieldSet;
import com.songouhe.internal.uwt.view.detailField.GridField;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-12-9
 */
public class PanelWorkcakeView  extends AbstractView implements Serializable {
    private String id;
    private String title;
    /**
     * privilege用于配置view的读写属性
     */
    private PrivilegeModel privilege = new PrivilegeModel();
    /**
     * privilege用于配置view中record的读写属性
     */
    private PrivilegeModel record_privilege = new PrivilegeModel();
    private SubPanelXTypeEnum xtype;
    private PanelLayoutTypeEnum layout;
    /**
     * html域只用于HTMLPanel
     */
    private String html;
    /**
     * fields用于除了HTMLPanel之外的panel
     */
    private List<UniFieldView> orig_fields;
    private HashMap fieldsMap = new HashMap();

    private DetailWorkcakeTypeEnum detailWorkcake = DetailWorkcakeTypeEnum.panel;

    //    private Object sqlHandler;
    private OperationTypeEnum operationType;

    public OperationTypeEnum getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeEnum operationType) {
        this.operationType = operationType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubPanelXTypeEnum getXtype() {
        return xtype;
    }

    public void setXtype(SubPanelXTypeEnum xtype) {
        this.xtype = xtype;
    }

    public PanelLayoutTypeEnum getLayout() {
        return layout;
    }

    public void setLayout(PanelLayoutTypeEnum layout) {
        this.layout = layout;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<UniFieldView> getOrig_fields() {
        return orig_fields;
    }

    public void setOrig_fields(List<UniFieldView> orig_fields) {
        this.orig_fields = orig_fields;
    }

    @Override
    public PrivilegeModel getPrivilege() {
        return privilege;
    }

    @Override
    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public HashMap getFieldsMap() {
        return fieldsMap;
    }

    public void setFieldsMap(HashMap fieldsMap) {
        this.fieldsMap = fieldsMap;
    }

    public PrivilegeModel getRecord_privilege() {
        return record_privilege;
    }

    public void setRecord_privilege(PrivilegeModel record_privilege) {
        this.record_privilege = record_privilege;
    }

    public DetailWorkcakeTypeEnum getDetailWorkcake() {
        return detailWorkcake;
    }

    public void setDetailWorkcake(DetailWorkcakeTypeEnum detailWorkcake) {
        this.detailWorkcake = detailWorkcake;
    }

    @Override
    public List subViewItems() {
        return this.getOrig_fields();
    }

    public void setNodeWrite(boolean inBParentReadOnly, List inPrivileges){
        PrivilegeModel privilege = this.getPrivilege();
        //写权限
        String wPrivilegeNode = privilege.getWritePrivilege();
        //父节点为readonly，则下层所有子节点均为readonly
        if(inBParentReadOnly ||(!wPrivilegeNode.equals("ALL") &&
                (inPrivileges == null || !inPrivileges.contains(wPrivilegeNode)))) {
            this.setReadOnly(true);
        }
    }

    public PanelWorkcakeView() {
    }

    public void buildFieldForView(SubPanelXTypeEnum inXTpye, Class beanClass){
        this.xtype = inXTpye;
        switch (inXTpye){
            case html:
                break;
            case grid:
                this.setGridView(beanClass);
                break;
            case form:
            case formwindow:
                this.setFormView();
                break;
            case searchfield:
                this.setSearchAreaView();
                break;
        }

    }

    private void setGridView(Class beanClass){
        if(this.orig_fields == null || this.orig_fields.size() == 0)return;
        List columns = new ArrayList();
        List fields = new ArrayList();
        //组成columns
        for(UniFieldView orig_field: orig_fields){
            GridField gridField = new GridField(orig_field);
            columns.add(gridField);

        }
        //组成fields
        Field[] classFields = beanClass.getDeclaredFields();
        for(Field f: classFields){
            HashMap fm = new HashMap();
            fm.put("name",f.getName());
            fields.add(fm);
        }

        //组成data
        List data = new ArrayList();
        fieldsMap.put("columns",columns);
        fieldsMap.put("fields",fields);
        fieldsMap.put("data",data);


    }
    private void setFormView(){
        if(this.orig_fields == null || this.orig_fields.size() == 0)return;
        List fields = new ArrayList();
        for(UniFieldView orig_field: orig_fields){
            FormFieldSet formFieldset = new FormFieldSet(orig_field);
            fields.add(formFieldset);
        }
        //组成data
        List data = new ArrayList();
        fieldsMap.put("fields",fields);
        fieldsMap.put("data",data);
    }

    private void setSearchAreaView(){
        if(this.orig_fields == null || this.orig_fields.size() == 0)return;
        List fields = new ArrayList();
        for(UniFieldView orig_field: orig_fields){
            FormField formField = new FormField(orig_field);
            fields.add(formField);
        }
        fieldsMap.put("fields",fields);
    }

}
