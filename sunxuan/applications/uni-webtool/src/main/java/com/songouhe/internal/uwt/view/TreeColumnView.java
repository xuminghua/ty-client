package com.songouhe.internal.uwt.view;

import com.songouhe.internal.uwt.model.PrivilegeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class TreeColumnView extends AbstractView implements Serializable {

     //以下为treeColumn使用的配置
    private String columnId;
    private PrivilegeModel privilege = new PrivilegeModel();
    private String text;
    private boolean leaf = true;  //如果children.size>0, leaf必须改为false, 否则children不生效
     /*
    tree 结构
     */
    private List<TreeColumnView> children = new ArrayList();


    /*
    panelWorkcakeModel的配置,最多包含4种panel xtype。
     */
//    private List<PanelWorkcakeModel> panelWorkspaces = new ArrayList<>();
    private PanelWorkspaceView panelWorkspaces;
    /**
     * 如果调用了panelWorkspaces.setSubNodesReadAndWrite,set modifyByPriv=true
     */
    private boolean modifyByPriv;



    public TreeColumnView() {
    }

    public TreeColumnView(String text, String columnId, String rPrivilege, String wPrivilege) {
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


    public PanelWorkspaceView getPanelWorkspaces() {
        return panelWorkspaces;
    }

    public void setPanelWorkspaces(PanelWorkspaceView panelWorkspaces) {
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

    public List<TreeColumnView> getChildren() {
        return children;
    }

    public void setChildren(List<TreeColumnView> children) {
        this.children = children;
    }

    public void addChildren(TreeColumnView treeColumnView) {
        if (leaf) leaf = false;
        this.children.add(treeColumnView);
    }

    public PrivilegeModel getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    public boolean isModifyByPriv() {
        return modifyByPriv;
    }

    public void setModifyByPriv(boolean modifyByPriv) {
        this.modifyByPriv = modifyByPriv;
        PanelWorkspaceView pv = this.getPanelWorkspaces();
        if(pv != null)pv.setModified(modifyByPriv);
    }

    @Override
    public List subViewItems() {
        if(!this.isLeaf())
            return this.children;
        else {
            if(this.getPanelWorkspaces() == null)
                return null;
            else
                return this.getPanelWorkspaces().subViewItems();
        }
    }


    public void setSubNodesReadAndWrite( List inPrivileges, MainView inmv){
        if(!this.isLeaf()){
            //枝节点，嵌套调用
            super.setSubNodesReadAndWrite(inPrivileges, inmv);
        }else{
            if(this.getPanelWorkspaces() != null) this.getPanelWorkspaces().setReadOnly(this.isReadOnly());
             //叶节点，user初次登陆并不根据权限做PanelWorkspace的读写配置，而在用户第一次点击当前column时进行读写配置并保存
//             this.getPanelWorkspacesView().setSubNodesReadAndWrite(inBParentReadOnly, inPrivileges);
        }

    }

}
