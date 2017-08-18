package com.songouhe.internal.uwt.model.viewconfig;

import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.utils.JsonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class TabCategoryModel implements Serializable {

    private String id;
    private PrivilegeModel privilege = new PrivilegeModel();
    private String title;
    private List<TreeColumnModel> treeColumnMap = new ArrayList<TreeColumnModel>();
    private PanelWorkspaceModel initPanels;
    private static Logger logger
            = LoggerFactory.getLogger(TabCategoryModel.class);

    public TabCategoryModel(String id, String title, String rPrivilege, String wPrivilege) {
        this.id = id;
        this.title = title;
        this.privilege = new PrivilegeModel(rPrivilege, wPrivilege);

    }

    public TabCategoryModel() {
    }


    public List<TreeColumnModel> getTreeColumnMap() {
        return treeColumnMap;
    }

    public void setTreeColumnMap(List<TreeColumnModel> treeColumnMap) {
        this.treeColumnMap = treeColumnMap;
    }

    public void addTreeColumn(TreeColumnModel treeColumn) {
        this.treeColumnMap.add(treeColumn);
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

    public PanelWorkspaceModel getInitPanels() {
        return initPanels;
    }

    public void setInitPanels(PanelWorkspaceModel initPanels) {
        this.initPanels = initPanels;
    }

    /**
     * 自己嵌套调用自己的函数，以columID搜索当前TabCategoryModel的所有column
     * @param intTreeNode 嵌套调用时才赋值，外部调用时设为null
     * @param inSTreeColumnId
     * @return
     */
    public TreeColumnModel getTreeColumnModel(TreeColumnModel intTreeNode, String inSTreeColumnId){

        List<TreeColumnModel> children;
        if (intTreeNode == null) {
            children = this.treeColumnMap;
        }else{
            children = intTreeNode.getChildren();
        }
        if ( children == null || children.size() == 0)return null;
        if (inSTreeColumnId == null || inSTreeColumnId.equals(""))
            return (TreeColumnModel) children.get(0);

        for(TreeColumnModel node : children){
            if(inSTreeColumnId.equals(node.getColumnId())){
                return (node);
            }else if( !node.isLeaf() ){
                //嵌套调用，搜索treecolumn所有level的node
                TreeColumnModel subnode = this.getTreeColumnModel(node, inSTreeColumnId);
                if(subnode !=null)return subnode;
            }
        }
        return null;
    }

    /**
     * 自己嵌套调用自己的函数，将Map中的json file转换为PanelWorkspace的objct并放到实例中,包括treeColumns
     * @param inTreeNode 嵌套调用时才赋值，外部调用时设为null
     * @param inMap 包含initPanels和所有columnID为key的Map，value为PanelWorkspace file.
     *              columnID在Map中没有层级结构，都是leaf=true的column
     */
    public void setPanelWorkspace(HashMap inMap, TreeColumnModel inTreeNode){
        List<TreeColumnModel> children;
        if (inTreeNode == null) {
            children = this.treeColumnMap;
        }else{
            children = inTreeNode.getChildren();
        }
        //获取tab的initpanels=panelworkspace
        if(inTreeNode == null) {
            logger.info("tab[" + this.getId() + "] initPanel 开始读取配置");
            PanelWorkspaceModel initPanels = JsonFileUtil.getPanelsFromJsonFile(null, inMap);
            if (initPanels == null) {
                logger.info("tab[" + this.getId() + "]没有initPanels<PanelWorkspace>");
            } else{
                this.setInitPanels(initPanels);
                logger.info("tab[" + this.getId() + "] initPanel 配置完成");
            }
        }
        //获取每个column的panelworkspace
        if ( children == null || children.size() == 0 || inMap == null || inMap.isEmpty())return ;
        List<TreeColumnModel> deletedNodes = new ArrayList<TreeColumnModel>();
        for(TreeColumnModel node : children){
            if(!node.isLeaf()){
                this.setPanelWorkspace(inMap, node);
                if(node.getChildren() == null || node.getChildren().size() == 0)
                    deletedNodes.add(node);
            }else{
                logger.info("tab[" + this.getId() + "] , column[" + node.getColumnId() + "]开始读取配置");
                PanelWorkspaceModel columnPanels = JsonFileUtil.getPanelsFromJsonFile(node.getColumnId(), inMap);
                if(columnPanels == null){
                    logger.info("tab[" + this.getId() + "], column[" + node.getColumnId() + "]没有PanelWorkspace");
                    deletedNodes.add(node);
                }else {
                    node.setPanelWorkspaces(columnPanels);
                    logger.info("tab[" + this.getId() + "], column[" + node.getColumnId() + "]PanelWorkspace配置完成");
                }

            }
        }
        children.removeAll(deletedNodes);
    }

}
