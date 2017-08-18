package com.songouhe.internal.uwt.view;

import com.songouhe.internal.uwt.model.PrivilegeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class TabCategoryView extends AbstractView implements Serializable {

    private String id;
    private PrivilegeModel privilege = new PrivilegeModel();
    private String title;
    private List<TreeColumnView> treeColumnMap = new ArrayList<TreeColumnView>();
    private PanelWorkspaceView initPanels;

    public TabCategoryView(String id, String title, String rPrivilege, String wPrivilege) {
        this.id = id;
        this.title = title;
        this.privilege = new PrivilegeModel(rPrivilege, wPrivilege);

    }

    public TabCategoryView() {
    }


    public List<TreeColumnView> getTreeColumnMap() {
        return treeColumnMap;
    }

    public void setTreeColumnMap(List<TreeColumnView> treeColumnMap) {
        this.treeColumnMap = treeColumnMap;
    }

    public void addTreeColumn(TreeColumnView treeColumnView) {
        this.treeColumnMap.add(treeColumnView);
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

    @Override
    public List subViewItems() {
        return treeColumnMap;
    }

    public PanelWorkspaceView getInitPanels() {
        return initPanels;
    }

    public void setInitPanels(PanelWorkspaceView initPanels) {
        this.initPanels = initPanels;
    }

    /**
     * 自己嵌套调用自己的函数，以columID搜索当前tabCategoryModel的所有column
     * server启动时需要调用的函数。此后用户访问时可直接从MainView.leafColumn进入TreeColumnView实例
     * @param intTreeNode 嵌套调用时才赋值，外部调用时设为null
     * @param inSTreeColumnId
     * @return
     */
    public TreeColumnView getTreeColumnView(TreeColumnView intTreeNode, String inSTreeColumnId){

        List<TreeColumnView> children;
        if (intTreeNode == null) {
            children = this.treeColumnMap;
        }else{
            children = intTreeNode.getChildren();
        }
        if ( children == null || children.size() == 0)return null;
        if (inSTreeColumnId == null || inSTreeColumnId.equals(""))
            return (TreeColumnView) children.get(0);

        for(TreeColumnView node : children){
            if(inSTreeColumnId.equals(node.getColumnId())){
                return (node);
            }else if( !node.isLeaf() ){
                //嵌套调用，搜索treecolumn所有level的node
                TreeColumnView subnode = this.getTreeColumnView(node, inSTreeColumnId);
                if(subnode !=null)return subnode;
            }
        }
        return null;
    }

    public void setSubNodesReadAndWrite( List inPrivileges, MainView inmv){
        super.setSubNodesReadAndWrite(inPrivileges, inmv);
        if(this.initPanels != null ){
            this.initPanels.setReadOnly(this.isReadOnly());
            this.initPanels.setSubNodesReadAndWrite(inPrivileges, inmv);
        }
    }


}
