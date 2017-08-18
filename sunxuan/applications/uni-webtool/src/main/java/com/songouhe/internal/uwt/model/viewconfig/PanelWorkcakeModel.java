package com.songouhe.internal.uwt.model.viewconfig;

import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.enums.DetailWorkcakeTypeEnum;
import com.songouhe.internal.uwt.model.enums.OperationTypeEnum;
import com.songouhe.internal.uwt.model.enums.PanelLayoutTypeEnum;
import com.songouhe.internal.uwt.model.enums.SubPanelXTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 17-1-17
 */
public class PanelWorkcakeModel implements Serializable {
    private String id;
    private String title;
    /**
     * privilege用于配置view的读写属性
     */
    private PrivilegeModel privilege = new PrivilegeModel();

    private SubPanelXTypeEnum xtype;
    private PanelLayoutTypeEnum layout;
    /**
     * html域只用于HTMLPanel
     */
    private String html;
    private List<UniFieldModel> orig_fields;
    /**
     * 只用于grid的每个item显示detail信息所关联的infoform：panel或者window
     */
    private DetailWorkcakeTypeEnum detailWorkcake;
//    private Object sqlHandler;
    private OperationTypeEnum operationType;

    public PanelWorkcakeModel(String id, String title,
                              String rPrivilege, String wPrivilege) {
        this.id = id;
        this.title = title;
        this.privilege = new PrivilegeModel(rPrivilege, wPrivilege);

    }
    public PanelWorkcakeModel(){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public PrivilegeModel getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
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

    public List<UniFieldModel> getOrig_fields() {
        return orig_fields;
    }

    public void setOrig_fields(List<UniFieldModel> orig_fields) {
        this.orig_fields = orig_fields;
    }

    public SubPanelXTypeEnum getXtype() {
        return xtype;
    }

    public void setXtype(SubPanelXTypeEnum xtype) {
        this.xtype = xtype;
    }

    public DetailWorkcakeTypeEnum getDetailWorkcake() {
        return detailWorkcake;
    }

    public void setDetailWorkcake(DetailWorkcakeTypeEnum detailWorkcake) {
        this.detailWorkcake = detailWorkcake;
    }

    public OperationTypeEnum getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeEnum operationType) {
        this.operationType = operationType;
    }
}
