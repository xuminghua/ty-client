package com.songouhe.internal.uwt.view;

import com.songouhe.base.sso.action.PrivilegeInfo;
import com.songouhe.internal.uwt.model.PrivilegeModel;
import com.songouhe.internal.uwt.model.enums.OperationTypeEnum;
import com.songouhe.internal.uwt.model.enums.SubPanelXTypeEnum;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.model.viewconfig.action.OperationSetModel;
import com.songouhe.internal.uwt.view.action.OperationSetView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxuan
 * @version 1.0 16-11-22
 */
public class PanelWorkspaceView extends AbstractView implements Serializable {
    /**
     * 控制整个view的读写权限
     */
    private PrivilegeModel privilege = new PrivilegeModel();
    /**
     * 控制每个record的操作权限，若为null则封闭所有操作权限
     */

    private OperationSetModel operations;

    private OperationSetView userOperations;

    /*
    panelWorkcakeModel的配置,最多包含以下4种panel,可能为null。
     */
    private PanelWorkcakeView htmlPanel;
    private PanelWorkcakeView infoFormPanel;
    private PanelWorkcakeView searchPanel;
    private PanelWorkcakeView resultGridPanel;
    private PanelWorkcakeView infoFormWindow;
    /**
     * 用于当前workspace的基础实体类
     */
    private String baseBeanStr;
    private Class baseBeanClass;
    /**
     * 如果recordPrivilege没有配置，则默认为封闭所有操作权限给所有人。
     * 以下4个field为当前用户的操作权限，例如createRec为true，则当前用户可以创建record
     */
    private boolean createRec;
    private boolean deleteRec;
    private boolean updateRec;
    private boolean completeRec;
    private boolean searchRec;

    /**
     * 以下3个filed为过程控制变量，例如若hasBuildFields为false，
     * 则需要重新进行field结构处理并设为true
     */
    private boolean hasBuildFields;
    private boolean hasPrivilegeFilterd;
    private boolean hasBuildUserOperations;


    public PanelWorkspaceView() {
    }

    public void setModified(boolean modified){
        hasBuildFields = modified;
        hasPrivilegeFilterd = modified;
        hasBuildUserOperations = modified;
    }
    public boolean isSearchRec() {
        return searchRec;
    }

    public void setSearchRec(boolean searchRec) {
        this.searchRec = searchRec;
    }

    public boolean isCompleteRec() {
        return completeRec;
    }

    public void setCompleteRec(boolean completeRec) {
        this.completeRec = completeRec;
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

    public PanelWorkcakeView getHtmlPanel() {
        return htmlPanel;
    }

    public void setHtmlPanel(PanelWorkcakeView htmlPanel) {
        this.htmlPanel = htmlPanel;
    }

    public PanelWorkcakeView getInfoFormPanel() {
        return infoFormPanel;
    }

    public void setInfoFormPanel(PanelWorkcakeView infoFormPanel) {
        this.infoFormPanel = infoFormPanel;
    }

    public PanelWorkcakeView getSearchPanel() {
        return searchPanel;
    }

    public void setSearchPanel(PanelWorkcakeView searchPanel) {
        this.searchPanel = searchPanel;
    }

    public PanelWorkcakeView getInfoFormWindow() {
        return infoFormWindow;
    }

    public void setInfoFormWindow(PanelWorkcakeView infoFormWindow) {
        this.infoFormWindow = infoFormWindow;
    }

    public PanelWorkcakeView getResultGridPanel() {
        return resultGridPanel;
    }

    public void setResultGridPanel(PanelWorkcakeView resultGridPanel) {
        this.resultGridPanel = resultGridPanel;
    }


    public OperationSetModel getOperations() {
        return operations;
    }

    public void setOperations(OperationSetModel operations) {
        this.operations = operations;
    }

    public boolean isCreateRec() {
        return createRec;
    }

    public void setCreateRec(boolean createRec) {
        this.createRec = createRec;
    }

    public boolean isDeleteRec() {
        return deleteRec;
    }

    public void setDeleteRec(boolean deleteRec) {
        this.deleteRec = deleteRec;
    }

    public boolean isUpdateRec() {
        return updateRec;
    }

    public void setUpdateRec(boolean updateRec) {
        this.updateRec = updateRec;
    }


    /**
     * 当调用本方法时，默认配置中至少有1个panel是可读的，即当前PanelWorkspaceView不会完全被delete
     * PanelWorkspaceView的readOnly属性在TreeColumnView class的setSubNodesReadAndWrite中设置
     * PanelWorkspaceView的privilege不起任何作用
     * @param inPrivileges
     * @param inmv
     */
    public void setSubNodesReadAndWrite(List inPrivileges, MainView inmv){
        if(hasPrivilegeFilterd)return;
        hasPrivilegeFilterd = true;
        PrivilegeModel privilege = this.getPrivilege();
        //读权限
        String rPrivilege = privilege.getReadPrivilege();
        if (!rPrivilege.equals("ALL") && !inPrivileges.contains(rPrivilege)) {
            this.setInfoFormPanel(null);
            this.setHtmlPanel(null);
            this.setResultGridPanel(null);
            this.setSearchPanel(null);
        }else{
            //写权限和嵌套子节点设置读写权限
            if(this.htmlPanel != null) {
                this.htmlPanel.setNodeWrite(this.isReadOnly(), inPrivileges);
                this.htmlPanel.setSubNodesReadAndWrite(inPrivileges, null);
            }
            if(this.infoFormPanel != null) {
                this.infoFormPanel.setNodeWrite(this.isReadOnly(), inPrivileges);
                this.infoFormPanel.setSubNodesReadAndWrite(inPrivileges, null);
            }
            if(this.resultGridPanel != null) {
                this.resultGridPanel.setNodeWrite(this.isReadOnly(), inPrivileges);
                this.resultGridPanel.setSubNodesReadAndWrite(inPrivileges, null);
            }
            if(this.searchPanel != null) {
                this.searchPanel.setNodeWrite(this.isReadOnly(), inPrivileges);
                this.searchPanel.setSubNodesReadAndWrite(inPrivileges, null);
            }
            if(this.infoFormWindow != null) {
                this.infoFormWindow.setNodeWrite(this.isReadOnly(), inPrivileges);
                this.infoFormWindow.setSubNodesReadAndWrite(inPrivileges, null);
            }

        }
    }

    /**
     * 根据最终确定的用户操作反过来作用于view，删掉不存在操作的view
     */
    public void setViewFromOperations(){
        if(!this.searchRec)searchPanel = null;
        if(!this.createRec){
            if(infoFormPanel != null &&
                    infoFormPanel.getOperationType() == OperationTypeEnum.create){
                infoFormPanel = null;
            }else if(infoFormWindow != null &&
                    infoFormWindow.getOperationType() == OperationTypeEnum.create){
                infoFormWindow = null;
            }
        }
    }
    public void setRecordPrivilege(){
        if( operations == null || userOperations == null)return;
        if( userOperations.getDefaultCreateAction() != null &&
                userOperations.getDefaultCreateAction().length > 0){
            this.createRec = true;
        }
        if((userOperations.getDefaultUpdateAction() != null  &&
                userOperations.getDefaultUpdateAction().length > 0)||
                (userOperations.getDefaultUpdateAction() == null &&
                        operations.getUpdate() != null &&
                        ConfigUtil.checkPrivilegeRange(0,99,operations.getUpdate().keySet())))
            this.updateRec = true;

        if((userOperations.getDefaultDeleteAction() != null  &&
                userOperations.getDefaultDeleteAction().length > 0)||
                (userOperations.getDefaultDeleteAction() == null &&
                        operations.getDelete() != null &&
                        ConfigUtil.checkPrivilegeRange(0,99,operations.getDelete().keySet())))
            this.deleteRec = true;

        if((userOperations.getDefaultCompleteAction() != null  &&
                userOperations.getDefaultCompleteAction().length > 0)||
                (userOperations.getDefaultCompleteAction() == null &&
                        operations.getComplete() != null &&
                        ConfigUtil.checkPrivilegeRange(0,99,operations.getComplete().keySet())))
            this.completeRec = true;
        if((userOperations.getDefaultSearchAction() != null  &&
                userOperations.getDefaultSearchAction().length > 0)||
                (userOperations.getDefaultSearchAction() == null &&
                        operations.getSearch() != null &&
                        ConfigUtil.checkPrivilegeRange(0,99,operations.getSearch().keySet())))
            this.searchRec = true;

    }


    public List subViewItems() {
        List result = new ArrayList();
        if(htmlPanel != null)result.add(htmlPanel);
        if(infoFormPanel != null)result.add(infoFormPanel);
        if(infoFormWindow != null)result.add(infoFormWindow);
        if(resultGridPanel != null)result.add(resultGridPanel);
        if(searchPanel != null)result.add(searchPanel);
        return result;
    }

    /**
     * 组建为传输到页面展示的结构数据
     */
    public void buildFieldForView() throws Exception{
        if(hasBuildFields)return;
        hasBuildFields = true;
        if(this.baseBeanStr != null && !this.baseBeanStr.equals("")) {
            String beanName = ConfigUtil.getEntityPath() + this.baseBeanStr;
            this.baseBeanClass = Class.forName(beanName);
        }else{
            this.baseBeanClass = Class.forName("java.lang.Object");
        }
        if (htmlPanel != null)htmlPanel.buildFieldForView(SubPanelXTypeEnum.html,baseBeanClass);
        if (infoFormPanel != null)infoFormPanel.buildFieldForView(SubPanelXTypeEnum.form,baseBeanClass);
        if (resultGridPanel != null)resultGridPanel.buildFieldForView(SubPanelXTypeEnum.grid,baseBeanClass);
        if (searchPanel != null)searchPanel.buildFieldForView(SubPanelXTypeEnum.searchfield,baseBeanClass);
        if (infoFormWindow != null)infoFormWindow.buildFieldForView(SubPanelXTypeEnum.formwindow,baseBeanClass);

    }

    public void buildUserOperations(PrivilegeInfo inUserPrivInfo){
        //确定具体action
//        if(hasBuildUserOperations)return;
        hasBuildUserOperations = true;
        userOperations = new OperationSetView(operations);
        userOperations.buildUserOperations(inUserPrivInfo);
        //如果没有默认的action，对应的权限就关闭
        setRecordPrivilege();
        //选择完权限的操作后，删除无任何操作view组件
        setViewFromOperations();

    }

    public OperationSetView getUserOperations() {
        return userOperations;
    }

    public void setUserOperations(OperationSetView userOperations) {
        this.userOperations = userOperations;
    }

    public String getBaseBeanStr() {
        return baseBeanStr;
    }

    public void setBaseBeanStr(String baseBeanStr) {
        this.baseBeanStr = baseBeanStr;
    }
}
