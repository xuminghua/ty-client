package com.songouhe.internal.uwt.service;

import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.entity.RecordWithTotalCount;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.songouhe.base.sso.action.PrivilegeInfo;
import com.songouhe.base.sso.action.SSOUserInfo;
import com.songouhe.base.util.service.ToolUtil;
import com.songouhe.internal.uwt.exceptions.ServiceException;
import com.songouhe.internal.uwt.model.enums.OperationTypeEnum;
import com.songouhe.internal.uwt.utils.ConfigUtil;
import com.songouhe.internal.uwt.utils.CustomizedActionHandling;
import com.songouhe.internal.uwt.utils.JsonFileUtil;
import com.songouhe.internal.uwt.model.viewconfig.action.ActionModel;
import com.songouhe.internal.uwt.model.viewconfig.action.DbPreValidationModel;
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
    private String columnId;


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
        columnId = inColumnId;
    }

    public boolean create(HashMap fieldValues) throws Exception {
        CustomizedActionHandling cah = new CustomizedActionHandling(
                this.userInfo,this,OperationTypeEnum.create,fieldValues);
        BaseDao baseDao = ConfigUtil.getBaseDao();

        if(baseDao == null){
            String errMsg = "class:[ActionXManager],method:[create],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置";
            throw new ServiceException(errMsg);
        }
        if(operationSetView == null){
            String errMsg = "class:[ActionXManager],method:[create],user:["
                    + userInfo.getsUserHandle() + "]: operationSetView=null, 请检查json文件配置";
            throw new ServiceException(errMsg);
        }
        ActionModel[] createOpSet = operationSetView.getDefaultCreateAction();
        try {
            cah.preActionHandling();

            String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
            if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
                int index = baseBeanStr.indexOf(".");
                String strDbSource = baseBeanStr.substring(0,index);
                DatabaseContextHolder.clearDatabaseType();
                DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
            }

            List objList = new ArrayList();
            for(ActionModel action : createOpSet) {
                //每个action必须包含beanStr,否则报错
                if(action.getBeanStr() == null){
                    String errMsg = "class:[ActionXManager],method:[create],user:["
                            + userInfo.getsUserHandle() +
                            "]: create的action配置缺少beanStr,无法创建新纪录";
                    throw new ServiceException(errMsg);
                }
                String beanName = ConfigUtil.getEntityPath() + action.getBeanStr();
                Class c = Class.forName(beanName);


                //组建要create的数据结构 start
                HashMap fieldValuesForBean = action.getConditionFields();
                if (fieldValuesForBean != null) {
                    fieldValuesForBean = (HashMap) fieldValuesForBean.clone();
                    fieldValuesForBean.putAll(fieldValues);
                } else fieldValuesForBean = fieldValues;
                // 若table包含uuid字段，需先生成uuid
                boolean existsField = ToolUtil.existsField(c, "uuid");
                if (existsField &&
                        (!fieldValuesForBean.containsKey("uuid")
                                || fieldValuesForBean.get("uuid") == null
                                || fieldValuesForBean.get("uuid").equals(""))) {
                    fieldValuesForBean.put("uuid", UUID.randomUUID().toString());
                }
                //生成create_time
                long currentTimeMillis = System.currentTimeMillis();
                existsField = ToolUtil.existsField(c, "create_time");
                if (existsField && (!fieldValuesForBean.containsKey("create_time")
                        || fieldValuesForBean.get("create_time") == null
                        || fieldValuesForBean.get("create_time").equals(""))) {
                    fieldValuesForBean.put("create_time", new Timestamp(currentTimeMillis));
                }
                //生成modify_time
                existsField = ToolUtil.existsField(c, "modify_time");
                if (existsField && (!fieldValuesForBean.containsKey("modify_time")
                        || fieldValuesForBean.get("modify_time") == null
                        || fieldValuesForBean.get("modify_time").equals(""))) {
                    fieldValuesForBean.put("modify_time", new Timestamp(currentTimeMillis));
                }
                Object obj = JsonFileUtil.hashMapToJavaBean(fieldValuesForBean, c);
                //组建要create的数据结构 end

                //校验create的前提条件 start
                DbPreValidationModel[] validations = action.getDbPreValidation();
                if (validations != null && validations.length > 0) {
                    for (DbPreValidationModel validationModel : validations) {
                        List valuesList = new ArrayList();
                        String[] values = validationModel.getValues();
                        boolean stopValid = false;
                        if (values != null && values.length > 0) {
                            for (String value : values) {
                                if (value.indexOf("$") != -1) {
                                    String fieldName = value.substring(1);
                                    if (!fieldValues.containsKey(fieldName)) {
                                        logger.warn("上传的创建信息并未包含field[" + fieldName + "]的值,停止进行有效校验!");
                                        stopValid = true;
                                        break;
                                    }
                                    Field f = c.getDeclaredField(fieldName);
                                    if (f != null) {
                                        f.setAccessible(true);
                                        valuesList.add(f.get(obj));
                                    }
                                } else {
                                    valuesList.add(value);
                                }
                            }
                        }
                        if (!stopValid) {
                            int count = baseDao.getRecordCount(validationModel.getSql(), valuesList.toArray());
                            switch (validationModel.getValid()) {
                                case EXIST:
                                    if (count == 0) {
                                        Exception e = new ServiceException(validationModel.getError_msg());
                                        logger.error("error", e);
                                        throw e;
                                    }
                                    break;
                                case NOT_EXIST:
                                    if (count > 0) {
                                        Exception e = new ServiceException(validationModel.getError_msg());
                                        logger.error("error", e);
                                        throw e;
                                    }
                                    break;
                            }
                        }

                    }
                }

                objList.add(obj);

                if (objList.size() > 0)
                    baseDao.createByObj(objList.toArray());
            }
            cah.postActionHandling();
            return true;
        }catch (Exception e) {
            throw e;
        }
    }

    public boolean delete(HashMap fieldValues) throws Exception {
        CustomizedActionHandling cah = new CustomizedActionHandling(
                this.userInfo,this,OperationTypeEnum.delete,fieldValues);
        BaseDao baseDao = ConfigUtil.getBaseDao();
        if(baseDao == null){
            String errMsg = "class:[ActionXManager],method:[delete],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置";
            throw new ServiceException(errMsg);
        }

        try {
            cah.preActionHandling();
            String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
            if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
                int index = baseBeanStr.indexOf(".");
                String strDbSource = baseBeanStr.substring(0,index);
                DatabaseContextHolder.clearDatabaseType();
                DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
            }
            ActionModel[] deleteOpSet = operationSetView.getDefaultDeleteAction();
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
            cah.postActionHandling();
            return true;
        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
    }

    public boolean update(HashMap fieldValues) throws Exception {
        CustomizedActionHandling cah = new CustomizedActionHandling(
                this.userInfo,this,OperationTypeEnum.update,fieldValues);
        BaseDao baseDao = ConfigUtil.getBaseDao();
        if(baseDao == null){
            String errMsg = "class:[ActionXManager],method:[update],user:["
                    + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置";
            throw new ServiceException(errMsg);
        }

        ActionModel[] updateOpSet = operationSetView.getDefaultUpdateAction();
        try {
            cah.preActionHandling();

            String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
            if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
                int index = baseBeanStr.indexOf(".");
                String strDbSource = baseBeanStr.substring(0,index);
                DatabaseContextHolder.clearDatabaseType();
                DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
            }
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
                baseDao.update(obj, updateMap, condiionMap);

            }
            cah.postActionHandling();
            return true;

        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
    }

    public String search(HashMap fieldValues) throws Exception {
        CustomizedActionHandling cah = new CustomizedActionHandling(
                this.userInfo,this,OperationTypeEnum.search,fieldValues);
        String result = "";

        String baseBeanStr = panelWorkspaceView.getBaseBeanStr();
        if(baseBeanStr != null && baseBeanStr.indexOf(".") > 0){
            int index = baseBeanStr.indexOf(".");
            String strDbSource = baseBeanStr.substring(0,index);
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(strDbSource));
        }
        ActionModel[] searchOpSet = operationSetView.getDefaultSearchAction();
        if(searchOpSet == null){
            String errMsg = "class:[ActionXManager],method:[search],user:["
                    + userInfo.getsUserHandle() + "]: searchOpSet=null, 请检查json文件配置";
            throw new ServiceException(errMsg);
        }

        try {
            cah.preActionHandling();
            BaseDao baseDao = ConfigUtil.getBaseDao();
            if(baseDao == null){
                String errMsg = "class:[ActionXManager],method:[search],user:["
                        + userInfo.getsUserHandle() + "]: baseDao=null, 请检查数据库链接配置";
                throw new ServiceException(errMsg);
            }
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
            cah.postActionHandling();
            return result;

        }catch (Exception e) {
            logger.error("error", e);
            throw e;
        }
    }
    private void setItemOperationWithPriv(RecordWithTotalCount records, Class c){
        if(records.getItems() == null)return;
        for(Object item: records.getItems()){
            Map newItem = JsonFileUtil.JavaBeanToHashMap(item);
        }

    }



    public OperationSetView getOperationSetView() {
        return operationSetView;
    }

    public PanelWorkspaceView getPanelWorkspaceView() {
        return panelWorkspaceView;
    }

    public PrivilegeInfo getUserPrivilegeInfo() {
        return userPrivilegeInfo;
    }

    public SSOUserInfo getUserInfo() {
        return userInfo;
    }

    public String getColumnId() {
        return columnId;
    }
}
