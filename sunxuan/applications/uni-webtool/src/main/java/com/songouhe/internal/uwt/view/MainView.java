package com.songouhe.internal.uwt.view;

import com.songouhe.internal.uwt.model.PrivilegeModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class MainView extends AbstractView implements Serializable {

    //========singleton config========//
    private static MainView mainView = new MainView();

    private MainView() {
    }

    public static MainView getInstance() {
        return mainView;
    }
    //========singleton config ends========//



    private PrivilegeModel privilege = new PrivilegeModel();
    private List<TabCategoryView> tabCategories = new ArrayList<>();
    private PanelWorkspaceView initPanels ;
    /**
     * 用于快速索引所有的leaf=true 的叶columns，获取其panel配置
     */
    private HashMap leafColumns = new HashMap();

    public PanelWorkspaceView getInitPanels() {
        return initPanels;
    }

    public HashMap getLeafColumns() {
        return leafColumns;
    }

    public void setLeafColumns(HashMap leafColumns) {
        this.leafColumns = leafColumns;
    }

    public void setInitPanels(PanelWorkspaceView initPanels) {
        this.initPanels = initPanels;
    }

    public List<TabCategoryView> getTabCategories() {
        return tabCategories;
    }

    public void setTabCategories(List<TabCategoryView> tabCategories) {
        this.tabCategories = tabCategories;
    }


    public void addTabCategory(TabCategoryView tabCategoryViewModel) {
        this.tabCategories.add(tabCategoryViewModel);
    }

    public TabCategoryView getTabCategoryView(String inSTabCategoryId){
        if(this.tabCategories == null || this.tabCategories.size() == 0){
            return null;
        }
        if (inSTabCategoryId == null || inSTabCategoryId.equals(""))
            return this.tabCategories.get(0) ;
        for(TabCategoryView node : this.tabCategories){
            if(inSTabCategoryId.equals(node.getId())){
                return (node);
            }
        }
        return null;
    }

    /**
     *
     * @param
     */

    public TreeColumnView getTreeColumnView(String inColumnId) {

        if(this.leafColumns == null || this.leafColumns.size() == 0
                || inColumnId == null || inColumnId.equals("")){
            return null;
        }
        return (TreeColumnView) this.leafColumns.get(inColumnId);
    }

    /*
    深层复制函数，以序列化和反序列化来copy一个新的实例MainView
     */
    public MainView deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
        //将对象写入流中
        ByteArrayOutputStream baOS = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baOS);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream baIS = new ByteArrayInputStream(baOS.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(baIS);
        return (MainView) ois.readObject();

    }


    @Override
    public PrivilegeModel getPrivilege() {
        return this.privilege;
    }

    @Override
    public void setPrivilege(PrivilegeModel privilege) {
        this.privilege = privilege;
    }

    @Override
    public List subViewItems(){
        return this.tabCategories;
    }

    @Override
    public void setSubNodesReadAndWrite(List inPrivileges, MainView inmv){
        super.setSubNodesReadAndWrite(inPrivileges, inmv);
        this.initPanels.setSubNodesReadAndWrite( inPrivileges, inmv);
    }

}
