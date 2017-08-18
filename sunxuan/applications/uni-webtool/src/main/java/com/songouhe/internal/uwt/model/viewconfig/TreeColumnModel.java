package com.songouhe.internal.uwt.model.viewconfig;

import com.songouhe.internal.uwt.model.PrivilegeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class TreeColumnModel implements Serializable {

     //以下为treeColumn使用的配置
    private String columnId;
    private PrivilegeModel privilege = new PrivilegeModel();
    private String text;

    private boolean leaf = true;  //如果children.size>0, leaf必须改为false, 否则children不生效
     /*
    tree 结构
     */
    private List<TreeColumnModel> children = new ArrayList();

    /*
    panelWorkcakeModel的配置,最多包含4种panel xtype。
     */
//    private List<PanelWorkcakeModel> panelWorkspaces = new ArrayList<>();
    private PanelWorkspaceModel panelWorkspaces;



    public TreeColumnModel() {
    }

    public TreeColumnModel(String text, String columnId, String rPrivilege, String wPrivilege) {
        this.text = text;
        this.columnId = columnId;
        this.privilege = new PrivilegeModel(rPrivilege, wPrivilege);
    }

//    public List<PanelWorkcakeModel> getPanelWorkspaces() {
//        return panelWorkspaces;
//    }
//
//    public void setPanelWorkspaces(List<PanelWorkcakeModel> panelWorkspaces) {
//        this.panelWorkspaces = panelWorkspaces;
//    }
//
//    public void addPanelWorkspace(PanelWorkcakeModel panelWorkcakeModel) {
//        this.panelWorkspaces.add(panelWorkcakeModel);
//    }


    public PanelWorkspaceModel getPanelWorkspaces() {
        return panelWorkspaces;
    }

    public void setPanelWorkspaces(PanelWorkspaceModel panelWorkspaces) {
        this.panelWorkspaces = panelWorkspaces;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<TreeColumnModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeColumnModel> children) {
        this.children = children;
    }

    public void addChildren(TreeColumnModel treeColumn) {
        if (leaf) leaf = false;
        this.children.add(treeColumn);
    }

    public PrivilegeModel getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
    }


}
