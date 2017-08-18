package com.songouhe.internal.uwt.view;

import com.songouhe.internal.uwt.model.PrivilegeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-29
 */
public abstract class AbstractView {
    protected boolean readOnly;


    public abstract PrivilegeModel getPrivilege();
    public abstract void setPrivilege(PrivilegeModel privilege);


    public boolean isReadOnly() {return this.readOnly;}

    public void setReadOnly(boolean readOnly){this.readOnly = readOnly;}

    /**
     * 根据父节点的readOnly属性和当前实例的所有Privilege配置设置每个节点的读写属性,必须由setReadOnly()调用
     */
    public void setSubNodesReadAndWrite( List inPrivileges, MainView inmv){


        List<AbstractView> deletedNodes = new ArrayList();

        List<AbstractView> subnodes = this.subViewItems();
        if(subnodes == null || subnodes.size() == 0)return;
        for(AbstractView node: subnodes) {
            PrivilegeModel privilege = node.getPrivilege();
            //读权限
            String rPrivilege = privilege.getReadPrivilege();
            if (!rPrivilege.equals("ALL") && !inPrivileges.contains(rPrivilege)) {
                deletedNodes.add(node);
                continue;
            }
            //写权限
            String wPrivilegeNode = privilege.getWritePrivilege();
            //父节点为readonly，则下层所有子节点均为readonly
            if(this.isReadOnly() ||
                    (!wPrivilegeNode.equals("ALL") && !inPrivileges.contains(wPrivilegeNode))) {
                node.setReadOnly(true);
            }
            //TreeColumnView的单独处理
            boolean leaf = false;
            if(node instanceof TreeColumnView){
                List columnchildren =  ((TreeColumnView) node).getChildren();
                if(columnchildren == null || columnchildren.size() == 0){
                    leaf = true;
                    ((TreeColumnView) node).setLeaf(true);
                    inmv.getLeafColumns().put(((TreeColumnView) node).getColumnId(), node);
                }else {
                    ((TreeColumnView) node).setPanelWorkspaces(null);
                }
            }
            node.setSubNodesReadAndWrite(inPrivileges, inmv);
             //如果节点的子节点都被删除，则父节点也需删除
            if (!(node instanceof UniFieldView) &&
                    !leaf && (node.subViewItems() == null || node.subViewItems().size() == 0)){
                deletedNodes.add(node);
            }
        }
        if(deletedNodes.size() > 0){
            subnodes.removeAll(deletedNodes) ;
                }

    }
    public abstract List subViewItems();

}
