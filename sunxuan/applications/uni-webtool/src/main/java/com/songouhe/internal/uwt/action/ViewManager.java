package com.songouhe.internal.uwt.action;

import com.songouhe.base.sso.action.PrivilegeInfo;
import com.songouhe.base.sso.action.SSOUserInfo;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.model.utils.JsonFileUtil;
import com.songouhe.internal.uwt.model.viewconfig.TabCategoryModel;
import com.songouhe.internal.uwt.view.MainView;
import com.songouhe.internal.uwt.view.PanelWorkspaceView;
import com.songouhe.internal.uwt.view.TabCategoryView;
import com.songouhe.internal.uwt.view.TreeColumnView;
import com.songouhe.internal.uwt.model.viewconfig.MainViewModel;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author sunxuan
 * @version 1.0 16-11-29
 * 用于处理所有和view有关的配置和操作
 */
public class ViewManager {
    private volatile MainView userView;
    private boolean isModifyByPriv;
    private static Logger logger
            = LoggerFactory.getLogger(ViewManager.class);

    public boolean isModifyByPriv() {
        return isModifyByPriv;
    }

    /**
     * 仅用于重新载入总配置
     * @param isModifyByPriv
     */
    public void setIsModifyByPriv(boolean isModifyByPriv) {
        this.isModifyByPriv = isModifyByPriv;
        Collection<TreeColumnView> columns = userView.getLeafColumns().values();
        for(TreeColumnView column: columns){
            column.setModifyByPriv(isModifyByPriv);
        }
    }

    //===========创建和验证，使用本类时只需要new ViewManager()即可完成验证，无需进入以下其他方法=============/
    /*
    验证ConfigUtil是否已经加载MainView的配置模版，并验证当前实例是否创建了用户的MainView object
     */
    private synchronized boolean validate() {
        if(this.userView == null || this.userView.getTabCategories().size() == 0){
            if(ConfigUtil.getMainViewModel() == null || ConfigUtil.getMainViewModel().getTabCategories().size() == 0){
                ViewManager.setMainViewModelFromPath(ConfigUtil.getViewConfigPath());
                if(ConfigUtil.getMainViewModel() == null || ConfigUtil.getMainViewModel().getTabCategories().size() == 0){
                    logger.error("MainView配置失败!");
                    return (false);
                }
            }
            this.createUserMainView();
            if(this.userView == null || this.userView.getTabCategories().size() == 0){
                logger.error("无法从MainView模版中提取正确的用户MainView");
                return (false);
            }
            return (true);
        }
        return (true);
    }

    public ViewManager() {
        this.validate();
    }

    //===========创建和验证 ends=============/




    //===========用户初次登陆的view操作=============/

    /**
     *根据MainView的原型获取一个new MainView object，来配置当前用户的view信息
     *深层复制函数，以序列化和反序列化来copy一个新的实例MainView
     */
    private MainView createUserMainView() {
        try {
            //将对象写入string中再转换为class
            JSONSerializer serializer = new JSONSerializer();
            String temp1 = serializer.exclude("class", "*.class").deepSerialize(ConfigUtil.getMainViewModel());
            this.userView = new JSONDeserializer<MainView>().deserialize(temp1,MainView.class);

        } catch (Exception e) {
            logger.error("error", e);
        }
        return (this.userView);
    }
    /*
    根据权限编写当前用户的MainView
     */
    public void setUserViewFromPrivilege(SSOUserInfo ui) {
        if(!this.validate() || this.isModifyByPriv)return;
        List privileges = ui.getPrivilege().getPrivileges();
        this.userView.setSubNodesReadAndWrite(privileges,this.userView);

        this.isModifyByPriv = true;
        JSONSerializer serializer = new JSONSerializer();
        logger.debug("user[" + ui.getsUserHandle() + "]的view data:" +
                serializer.exclude("*.class", "*.privilege", "*.record_privilege", "*.subViewItems", "*.leafColumns").deepSerialize(this.userView));

    }
    //===========用户初次登陆的view操作 end=============/

    //===========详细点击的操作=============/
    /*
    获取tabCategories的数组信息，传到前端用于页面tab的显示
     */
    public String getJSONArrayTabCategories() {
        String result = "[]";
        if(!this.validate())return(result);

        JSONSerializer serializer = new JSONSerializer();
        result = serializer.exclude("*.class",
                "treeColumnMap", "*.privilege", "*.initPanels", "*.leafColumns", "*.readOnly",
                "*.modifyByPriv")
                .include("tabCategories").serialize(this.userView);
        logger.info("tabOperations json:" + result);

        return (result);
    }

    /*
    获取tabCategory的columnMap信息，传到前端用于页面tree的显示
     */
    public String getJSONArrayTreeColumnMap(String inSTabCategoryId) {
        String result = "[]";
        if(!this.validate())return result;
        //inSTabCategoryId为空的判断已经在getTabOperationView()中调用
        TabCategoryView tab = this.userView.getTabCategoryView(inSTabCategoryId);
        if(tab == null)return result;

        JSONSerializer serializer = new JSONSerializer();
        result = serializer.exclude("*.class", "*.panelWorkspaces", "*.privilege", "*.modifyByPriv")
                .deepSerialize(tab.getTreeColumnMap());

        logger.info("treeColumnMap tab[" + inSTabCategoryId +"] json:"+ result);

        return (result);
    }

    /**
     * 传到前端用于页面的显示
     */
    public String getJSONArrayPanelWorkspace(PrivilegeInfo inPrivilegeInfo, String inSTabCategoryId, String inSTreeColumnId) throws Exception{
        PanelWorkspaceView panelWorkspaceView = this.getPanelWorkspaceView(inPrivilegeInfo, inSTabCategoryId, inSTreeColumnId);
        if(panelWorkspaceView == null )return "";
        panelWorkspaceView.buildFieldForView();
        //就用户的固定权限做一次权限过滤，以最小权限code即最高权限值取操作
        panelWorkspaceView.buildUserOperations(inPrivilegeInfo);
        JSONSerializer serializer = new JSONSerializer();
        String result = serializer.include("*.store").exclude("*.class","*.orig_fields", "*.privilege",
                "*.record_privilege", "*.uniFieldView", "*.operations","*.userOperations")
                .deepSerialize(panelWorkspaceView);
        logger.info("PanelWorkspace tab[" + inSTabCategoryId +"],tree[" + inSTreeColumnId + "] json:"+ result);

        return (result);
    }
    /**
     *获取PanelWorkspace的view信息,包括用户权限过滤的处理
     */
    public PanelWorkspaceView getPanelWorkspaceView(PrivilegeInfo inPrivilegeInfo, String inSTabCategoryId, String inSTreeColumnId) {
        PanelWorkspaceView result = null;
        if(!this.validate())return(result);

        //获取登陆界面的initPanels：inSTabCategoryId为空
        if(inSTabCategoryId == null || inSTabCategoryId.equals("")){
            result = this.userView.getInitPanels();
        }
        //获取某个column的Panels：inSTabCategoryId不为空且inSTreeColumnId不为空
        else if (!(inSTreeColumnId == null || inSTreeColumnId.equals(""))){
            TreeColumnView treeColumn = this.userView.getTreeColumnView(inSTreeColumnId);
            if(treeColumn == null || !treeColumn.isLeaf()){
                logger.info("PanelWorkspace tab[" + inSTabCategoryId +"],tree[" +
                        inSTreeColumnId + "] not exists or not leaf ");
                return result;}
            //对于一个column所属的panel配置，如果没有过滤用户权限，需要先进行过滤用户权限的处理
            if(!treeColumn.isModifyByPriv() && treeColumn.getPanelWorkspaces() != null){
                treeColumn.getPanelWorkspaces().setSubNodesReadAndWrite(inPrivilegeInfo.getPrivileges(),this.userView);
            }
            result = treeColumn.getPanelWorkspaces();


            //获取某个tab的initPanels：inSTreeColumnId为空且inSTabCategoryId不为空
        }else{
            TabCategoryView tabCategoryView = this.userView.getTabCategoryView(inSTabCategoryId);
            if(tabCategoryView == null){
                logger.info("PanelWorkspace tab[" + inSTabCategoryId +"] not exists");
                return result;}
            result = tabCategoryView.getInitPanels();

        }

        if(result == null){
            logger.info("PanelWorkspace tab[" + inSTabCategoryId +"],tree[" +
                    inSTreeColumnId + "] have no panelWorkspace configed");
            return result;
        }
        return result;

    }

    //===========详细点击的操作 end=============/



    public MainView getUserView() {
        return userView;
    }

    public void setUserView(MainView userView) {
        this.userView = userView;
    }


    //===========静态工具方法 =============/

    public static void setMainViewModelFromPath(String path){
        logger.info("view config from[" + path + "]");
        HashMap jsonFiles = JsonFileUtil.getJsonFiles(path);

        try {

            //载入总布局结构
            File mainFile = (File) jsonFiles.get("uwtview");
            if(mainFile == null){
                logger.error("配置错误! path["+ path +"]缺少uwtview.json文件");
                return;
            }
            BufferedReader readerMain = new BufferedReader(new FileReader(mainFile.getPath()));
            MainViewModel mainViewModel =
                    new JSONDeserializer<MainViewModel>().deserialize(readerMain, MainViewModel.class);
            readerMain.close();

            //载入初始界面的center workspace配置
            mainViewModel.setInitPanels(JsonFileUtil.getPanelsFromJsonFile(null, jsonFiles));

            //载入每个tab的center workspace配置
            List<TabCategoryModel> tabs = mainViewModel.getTabCategories();
            if(tabs == null || tabs.size() == 0)return;
            for(TabCategoryModel tabCM: tabs ){

                String sTabPath = tabCM.getId();
                HashMap tabMap = (HashMap) jsonFiles.get(sTabPath);

                if(tabMap != null && !tabMap.isEmpty()){
                    tabCM.setPanelWorkspace(tabMap, null);
                }

            }

            ConfigUtil.setMainViewModel(mainViewModel);
            JSONSerializer serializer = new JSONSerializer();
            logger.debug("mainViewModel:" + serializer.exclude("class", "*.class").deepSerialize(mainViewModel));
        } catch (Exception e) {
            logger.error("error", e);
        }

    }

    public static ViewManager getUserViewManager(HttpServletRequest request){
        ViewManager vm = (ViewManager)request.getSession().getAttribute("UserViewManager");
        if(vm == null){
            vm = new ViewManager();
            request.getSession().setAttribute("UserViewManager", vm);
        }
        return (vm);
    }

    public static MainView getUserMainView(HttpServletRequest request){
        ViewManager vm = getUserViewManager(request);
        return (vm.userView);
    }




    //===========静态工具方法 ends=============/


}
