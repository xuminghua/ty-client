package com.songouhe.internal.uwt.model.viewconfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class MainViewModel implements Serializable {

    //========singleton config========//
    private static MainViewModel mainViewModel = new MainViewModel();


    private List<TabCategoryModel> tabCategories = new ArrayList<>();
    private PanelWorkspaceModel initPanels ;

    private MainViewModel() {
    }

    public static MainViewModel getInstance() {
        return mainViewModel;
    }
    //========singleton config ends========//





    public PanelWorkspaceModel getInitPanels() {
        return initPanels;
    }

    public void setInitPanels(PanelWorkspaceModel initPanels) {
        this.initPanels = initPanels;
    }

    public List<TabCategoryModel> getTabCategories() {
        return tabCategories;
    }

    public void setTabCategories(List<TabCategoryModel> tabCategories) {
        this.tabCategories = tabCategories;
    }


    public void addTabCategory(TabCategoryModel tabCategoryModel) {
        this.tabCategories.add(tabCategoryModel);
    }

    public TabCategoryModel getTabCategoryModel(String inSTabCategoryId){
        if(this.tabCategories == null || this.tabCategories.size() == 0){
            return null;
        }
        if (inSTabCategoryId == null || inSTabCategoryId.equals(""))
            return this.tabCategories.get(0) ;
        for(TabCategoryModel node : this.tabCategories){
             if(inSTabCategoryId.equals(node.getId())){
                 return (node);
             }
        }
        return null;
    }
            /*
    深层复制函数，以序列化和反序列化来copy一个新的实例MainView
     */
    public MainViewModel deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
        ByteArrayOutputStream baOS = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baOS);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream baIS = new ByteArrayInputStream(baOS.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(baIS);
        return (MainViewModel) ois.readObject();

    }
}
