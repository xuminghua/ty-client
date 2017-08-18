package com.songouhe.base.sso.action;

import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import flexjson.JSONSerializer;
import org.jasig.cas.client.authentication.AttributePrincipal;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sunxuan
 * @version 1.0 16-11-27
 *          用于处理所有和用户信息有关的配置和操作
 */
public class SSOUserInfo {

    /******
     * 从sso获取的变量
     ********/
    /*
    用户工号
     */
    private String sUserNo;
    /*
    用户handle
     */
    private String sUserHandle;
    /*
    用户名字
     */
    private String sUserName;
    /*
    用户角色ID
     */
    private String sActorID;
    /*
用户角色名称
 */
    private String sActorName;
    /*
    用户部门ID
     */
    private String sDepartmentID;
    /**
     * 用户部门名称
     */
    private String sDepartmentName;

    /*
    用户上级handle
     */
    private String sSuperiorHandle;
    /*
    用户上级名字
     */
    private String sSuperiorName;

    /*
    用户权限组
     */
    private PrivilegeInfo privilege ;
    /******
     * 从sso获取的变量ends
     ********/

    public static SSOUserInfo getInstance(HttpServletRequest request) {
        if(request.getSession().getAttribute("SSOUserInfo") == null){
            SSOUserInfo userInfo = new SSOUserInfo(request);
            request.getSession().setAttribute("SSOUserInfo" , userInfo);
        }
        return (SSOUserInfo) request.getSession().getAttribute("SSOUserInfo");
    }

    private SSOUserInfo(HttpServletRequest request) {
        //从sso中读取所有用户信息
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        if(attributes.get("id") != null)
            this.sUserHandle = (String) attributes.get("id");
        if(attributes.get("name") != null)
            this.sUserName = (String) attributes.get("name");
        if(attributes.get("actorid") != null)
            this.sActorID = attributes.get("actorid").toString();
        if(attributes.get("departmentid") != null)
            this.sDepartmentID = attributes.get("departmentid").toString();
        if(attributes.get("superiorhandle") != null)
            this.sSuperiorHandle = attributes.get("superiorhandle").toString();
        if(attributes.get("superiorname") != null)
            this.sSuperiorName = attributes.get("superiorname").toString();
        if(attributes.get("actorname") != null)
            this.sActorName = attributes.get("actorname").toString();
        if(attributes.get("departmentname") != null)
            this.sDepartmentName = attributes.get("departmentname").toString();
        //should be change ??
        List<String> priv = new ArrayList<String>();
        if(attributes.get("privileges") != null){
            String s_Privilege = attributes.get("privileges").toString();
            String[] array_Priv = s_Privilege.split(",");

            priv = Arrays.asList(array_Priv);
        }
        this.privilege = new PrivilegeInfo(priv, ConfigUtil.getPrivileges());
    }

    public String TransUserInfoToString(){
        JSONSerializer serializer = new JSONSerializer();
        return serializer.exclude("privilege").serialize(this);
    }
    public SSOUserInfo(){

    }

    public String getsUserNo() {
        return sUserNo;
    }

    public String getsUserHandle() {
        return sUserHandle;
    }

    public String getsUserName() {
        return sUserName;
    }



    public String getsSuperiorHandle() {
        return sSuperiorHandle;
    }

    public String getsSuperiorName() {
        return sSuperiorName;
    }

    public PrivilegeInfo getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeInfo privilege) {
        this.privilege = privilege;
    }
    public void setPrivilege(List<String> inPrivileges, HashMap inPrivilegeBaseSet) {
        this.privilege = new PrivilegeInfo(inPrivileges, inPrivilegeBaseSet);
    }

    public void setsUserNo(String sUserNo) {
        this.sUserNo = sUserNo;
    }

    public void setsUserHandle(String sUserHandle) {
        this.sUserHandle = sUserHandle;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsActorName() {
        return sActorName;
    }

    public void setsActorName(String sActorName) {
        this.sActorName = sActorName;
    }


    public String getsDepartmentName() {
        return sDepartmentName;
    }

    public void setsDepartmentName(String sDepartmentName) {
        this.sDepartmentName = sDepartmentName;
    }

    public void setsSuperiorHandle(String sSuperiorHandle) {
        this.sSuperiorHandle = sSuperiorHandle;
    }

    public void setsSuperiorName(String sSuperiorName) {
        this.sSuperiorName = sSuperiorName;
    }

    public String getsActorID() {
        return sActorID;
    }

    public void setsActorID(String sActorID) {
        this.sActorID = sActorID;
    }

    public String getsDepartmentID() {
        return sDepartmentID;
    }

    public void setsDepartmentID(String sDepartmentID) {
        this.sDepartmentID = sDepartmentID;
    }
}
