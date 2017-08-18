package com.songouhe.internal.uwt.action;

import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.entity.RecordWithTotalCount;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.songouhe.base.sso.action.PrivilegeInfo;
import com.songouhe.base.sso.action.SSOUserInfo;
import com.songouhe.base.util.service.ToolUtil;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.model.utils.JsonFileUtil;
import com.songouhe.internal.uwt.model.viewconfig.action.ActionModel;
import com.songouhe.internal.uwt.view.PanelWorkspaceView;
import com.songouhe.internal.uwt.view.TreeColumnView;
import com.songouhe.internal.uwt.view.action.OperationSetView;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.BasicDateTransformer;
import flexjson.transformer.DateTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author sunxuan
 * @version 1.0 17-2-14
 */
public class ActionXManager {
    private static Logger logger
            = LoggerFactory.getLogger(ActionXManager.class);
    private OperationSetView operationSetView;
    PanelWorkspaceView panelWorkspaceView;
    private PrivilegeInfo userPrivilegeInfo;
    private SSOUserInfo userInfo;


    private ArrayList<String> setDisabledFieldsForRec(){
        ArrayList<String> result = new ArrayList<>();
        return result;
    }

    public ActionXManager(String inColumnId, String inPCid, HttpServletRequest request){
        userInfo = (SSOUserInfo) request.getSession().getAttribute("SSOUserInfo");
        userPrivilegeInfo = userInfo.getPrivilege();
        ViewManager vm = ViewManager.getUserViewManager(request);
        TreeColumnView columnView = vm.getUserView().getTreeColumnView(inColumnId);
        panelWorkspaceView = columnView.getPanelWorkspaces();
        operationSetView = panelWorkspaceView.getUserOperations();

    }
    public boolean create(HashMap fieldValues) throws Exception {
        BaseDao baseDao = ConfigUtil.getBaseDao();
        String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
        if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
            int index = baseBeanStr.indexOf(".");
            String strDbSource = baseBeanStr.substring(0,index);
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
        }
        if(baseDao == null){
            logger.error("error", "class:[ActionXManager],method:[create],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置");
            return false;
        }
        if(operationSetView == null){
            logger.error("error", "class:[ActionXManager],method:[create],user:["
                    + userInfo.getsUserHandle() + "]: operationSetView=null, 请检查json文件配置");
            return false;
        }
        ActionModel[] createOpSet = operationSetView.getDefaultCreateAction();
        try {
            List objList = new ArrayList();
            for(ActionModel action : createOpSet) {
                if (action.getBeanStr() == null) continue;
                String beanName = ConfigUtil.getEntityPath() + action.getBeanStr();
                Class c = Class.forName(beanName);
                HashMap fieldValuesForBean = action.getConditionFields();
                if(fieldValuesForBean != null ){
                    fieldValuesForBean = (HashMap) fieldValuesForBean.clone();
                    fieldValuesForBean.putAll(fieldValues);
                }else fieldValuesForBean = fieldValues;
                // 若table包含uuid字段，需先生成uuid
                boolean existsField = ToolUtil.existsField(c, "uuid");
                if(existsField &&
                        (!fieldValuesForBean.containsKey("uuid")
                                || fieldValuesForBean.get("uuid") == null
                                || fieldValuesForBean.get("uuid").equals(""))){
                    fieldValuesForBean.put("uuid", UUID.randomUUID().toString());
                }
                //生成create_time
                long currentTimeMillis = System.currentTimeMillis();
                existsField = ToolUtil.existsField(c, "create_time");
                if(existsField && (!fieldValuesForBean.containsKey("create_time")
                        || fieldValuesForBean.get("create_time") == null
                        || fieldValuesForBean.get("create_time").equals(""))){
                    fieldValuesForBean.put("create_time", new Timestamp(currentTimeMillis));
                }
                //生成modify_time
                existsField = ToolUtil.existsField(c, "modify_time");
                if(existsField && (!fieldValuesForBean.containsKey("modify_time")
                        || fieldValuesForBean.get("modify_time") == null
                        || fieldValuesForBean.get("modify_time").equals(""))){
                    fieldValuesForBean.put("modify_time",new Timestamp(currentTimeMillis));
                }

                Object obj = JsonFileUtil.hashMapToJavaBean(fieldValuesForBean,c);

                objList.add(obj);
            }
            if(objList.size() > 0)
                baseDao.createByObj(objList.toArray());

        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
        return true;
    }

    public boolean delete(HashMap fieldValues) throws Exception {
        BaseDao baseDao = ConfigUtil.getBaseDao();
        if(baseDao == null){
            logger.error("error","class:[ActionXManager],method:[delete],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置");
            return false;
        }
        String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
        if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
            int index = baseBeanStr.indexOf(".");
            String strDbSource = baseBeanStr.substring(0,index);
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
        }
        ActionModel[] deleteOpSet = operationSetView.getDefaultDeleteAction();
        try {
            for(ActionModel action : deleteOpSet) {
                if (action.getBeanStr() == null) continue;
                String beanName = ConfigUtil.getEntityPath() + action.getBeanStr();
                Class c = Class.forName(beanName);
                Object obj = JsonFileUtil.hashMapToJavaBean(fieldValues, c);
                HashMap updateMap = new HashMap();

                for(Object key: fieldValues.keySet()){
                    Field f = obj.getClass().getDeclaredField((String)key);
                    f.setAccessible(true);
                    if(f != null) {
                        updateMap.put(key, f.get(obj));
                    }
                }
                baseDao.delete(obj, updateMap);

            }


        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
        return true;
    }

    public boolean update(HashMap fieldValues) throws Exception {
        BaseDao baseDao = ConfigUtil.getBaseDao();
        if(baseDao == null){
            logger.error("error","class:[ActionXManager],method:[update],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置");
            return false;
        }
        String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
        if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
            int index = baseBeanStr.indexOf(".");
            String strDbSource = baseBeanStr.substring(0,index);
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
        }
        ActionModel[] updateOpSet = operationSetView.getDefaultUpdateAction();
        try {
            for(ActionModel action : updateOpSet) {
                if (action.getBeanStr() == null) continue;

                String beanName = ConfigUtil.getEntityPath() + action.getBeanStr();
                Class c = Class.forName(beanName);
                //生成modify_time
                long currentTimeMillis = System.currentTimeMillis();
                boolean existsField = ToolUtil.existsField(c, "modify_Time");
                if(existsField && (!fieldValues.containsKey("modify_Time")
                        || fieldValues.get("modify_Time") == null
                        || fieldValues.get("modify_Time").equals(""))){
                    fieldValues.put("modify_Time",new Timestamp(currentTimeMillis));
                }
                Object obj = JsonFileUtil.hashMapToJavaBean(fieldValues, c);
                HashMap condiionMap = new HashMap();
                HashMap updateMap = new HashMap();

                if(fieldValues.containsKey("id")){
                    Field f = obj.getClass().getDeclaredField("id");
                    f.setAccessible(true);
                    if(f != null){
                        condiionMap.put("id",f.get(obj));
                    }
                    fieldValues.remove("id");
                }
                for(Object key: fieldValues.keySet()){
                    Field f = obj.getClass().getDeclaredField((String)key);
                    f.setAccessible(true);
                    if(f != null) {
                        updateMap.put(key,f.get(obj));
                    }
                }
                baseDao.update(obj, updateMap,condiionMap);

            }


        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
        return true;
    }

    public String search(HashMap fieldValues) throws Exception {
        String result = "";
        BaseDao baseDao = ConfigUtil.getBaseDao();
        if(baseDao == null){
            logger.error("error","class:[ActionXManager],method:[search],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置");
            return result;
        }
        String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
        if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
            int index = baseBeanStr.indexOf(".");
            String strDbSource = baseBeanStr.substring(0,index);
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
        }
        ActionModel[] searchOpSet = operationSetView.getDefaultSearchAction();
        if(searchOpSet == null){
            logger.error("error","class:[ActionXManager],method:[search],user:["
                    + userInfo.getsUserHandle() + "]: searchOpSet=null, 请检查json文件配置");
            return result;
        }

        try {
            int i = 0;
            JSONSerializer serializer = new JSONSerializer().exclude("*.class")
                    .transform(new BasicDateTransformer(), Timestamp.class)
                    .transform(new DateTransformer("yyyy-MM-dd"), Timestamp.class);
            for(ActionModel action : searchOpSet) {
                if(i ++ == 0 ){
                    String page = "", start = "", limit = "";
                    if(fieldValues.get("page") != null) {
                        page = ((String[]) fieldValues.get("page"))[0];
                        start = ((String[]) fieldValues.get("start"))[0];
                        limit = ((String[]) fieldValues.get("limit"))[0];
                    }
                    HashMap condition;
                    if(fieldValues.get("condition") != null) {
                        String sCondition = ((String[])fieldValues.get("condition"))[0];
                        condition = new JSONDeserializer<HashMap>().deserialize(sCondition, HashMap.class);
                    }
                    else
                        condition = new HashMap<Object,Object>();
                    if (action.getBeanStr() == null) continue;
                    String beanName = ConfigUtil.getEntityPath() + action.getBeanStr();
                    Class c = Class.forName(beanName);
                    String order = (action.getSortCondition() == null ? "" :action.getSortCondition());

                    RecordWithTotalCount records = baseDao.getQueryAndCountByCondition(
                            c.newInstance(), condition, start, limit, order, false);
                    if(records.getItems() != null)
                        setItemOperationWithPriv(records, c);

                    result = serializer.deepSerialize(records);
                }

            }

        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
        return result;
    }
    private void setItemOperationWithPriv(RecordWithTotalCount records, Class c){
        if(records.getItems() == null)return;
        for(Object item: records.getItems()){
            Map newItem = JsonFileUtil.JavaBeanToHashMap(item);


        }

    }

}
