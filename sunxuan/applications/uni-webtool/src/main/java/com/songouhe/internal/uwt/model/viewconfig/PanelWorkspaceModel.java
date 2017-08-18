package com.songouhe.internal.uwt.model.viewconfig;

import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.RecordOperationPrivModel;
import com.songouhe.internal.uwt.model.viewconfig.action.OperationSetModel;

import java.io.Serializable;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class PanelWorkspaceModel implements Serializable {

    /**
     * 控制整个view的读写权限
     */
    private PrivilegeModel privilege = new PrivilegeModel();
    /**
     * 控制每个record的操作权限
     */
    private RecordOperationPrivModel recordPrivilege;
    private OperationSetModel operations;
    /**
     * 用于当前workspace的基础实体类
     */
    private String baseBeanStr;


    /*
    panelWorkcakeModel的配置,最多包含以下4种panel,可能为null。
     */
    private PanelWorkcakeModel htmlPanel;
    private PanelWorkcakeModel infoFormPanel;
    private PanelWorkcakeModel searchPanel;
    private PanelWorkcakeModel resultGridPanel;
    private PanelWorkcakeModel infoFormWindow;


    public PanelWorkspaceModel() {
    }



    
    
    public PrivilegeModel getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
    }

    public PanelWorkcakeModel getHtmlPanel() {
        return htmlPanel;
    }

    public void setHtmlPanel(PanelWorkcakeModel htmlPanel) {
        this.htmlPanel = htmlPanel;
    }

    public PanelWorkcakeModel getInfoFormPanel() {
        return infoFormPanel;
    }

    public void setInfoFormPanel(PanelWorkcakeModel infoFormPanel) {
        this.infoFormPanel = infoFormPanel;
    }

    public PanelWorkcakeModel getSearchPanel() {
        return searchPanel;
    }

    public void setSearchPanel(PanelWorkcakeModel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public PanelWorkcakeModel getResultGridPanel() {
        return resultGridPanel;
    }

    public void setResultGridPanel(PanelWorkcakeModel resultGridPanel) {
        this.resultGridPanel = resultGridPanel;
    }

    public OperationSetModel getOperations() {
        return operations;
    }

    public void setOperations(OperationSetModel operations) {
        this.operations = operations;
    }

    public RecordOperationPrivModel getRecordPrivilege() {
        return recordPrivilege;
    }

    public void setRecordPrivilege(RecordOperationPrivModel recordPrivilege) {
        this.recordPrivilege = recordPrivilege;
    }

    public PanelWorkcakeModel getInfoFormWindow() {
        return infoFormWindow;
    }

    public void setInfoFormWindow(PanelWorkcakeModel infoFormWindow) {
        this.infoFormWindow = infoFormWindow;
    }

    public String getBaseBeanStr() {
        return baseBeanStr;
    }

    public void setBaseBeanStr(String baseBeanStr) {
        this.baseBeanStr = baseBeanStr;
    }
}
