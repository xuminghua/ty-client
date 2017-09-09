package com.songouhe.internal.uwt.utils;

import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.songouhe.base.sso.action.SSOUserInfo;
import com.songouhe.internal.uwt.exceptions.ServiceException;
import com.songouhe.internal.uwt.model.enums.OperationTypeEnum;
import com.songouhe.internal.uwt.service.ActionXManager;
import flexjson.JSONSerializer;
import flexjson.transformer.BasicDateTransformer;
import flexjson.transformer.DateTransformer;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * 工具类，主要被ActionXManager调用
 * @author sunxuan
 * @version 1.0 17-9-9
 */
public class CustomizedActionHandling {
    private OperationTypeEnum action;
    private HashMap fieldValues;
    private ActionXManager actionXManager;
    private SSOUserInfo userInfo;

    public CustomizedActionHandling(SSOUserInfo ui, ActionXManager actionXManager, OperationTypeEnum action, HashMap fieldValues){
        this.action = action;
        this.actionXManager = actionXManager;
        this.fieldValues = fieldValues;
        this.userInfo = ui;
    }
    /**
     * create,update,search,delete等行为之前做的定制化操作，针对具体column和具体action
     */
    public void preActionHandling() throws Exception{
        String columnId = this.actionXManager.getColumnId();
        if(columnId == null)return;
        switch (action){
            case create:
                switch (columnId){
                    case "op_pdt_coupon":
                        this.preCreateOpPdtCoupon();
                        break;
                    case "op_usr_coupon_add":
                        this.preCreateOpUserCoupon();
                }

                break;
            case search:
                break;
            case update:
                break;
            case delete:
                break;

        }
    }
    /**
     * create,update,search,delete等行为之后做的定制化操作，针对具体column和具体action
     */
    public void postActionHandling() throws Exception{
        String columnId = this.actionXManager.getColumnId();
        if(columnId == null)return;
        switch (action){
            case create:
                break;
            case search:
                break;
            case update:
                break;
            case delete:
                break;

        }
    }

    private void preCreateOpPdtCoupon() throws Exception{
        if(fieldValues.get("total") == null){
            String errMsg = getErrorMsg("preCreateOpPdtCoupon","total不能为空!");
            throw new ServiceException(errMsg);
        }
        Integer total = Integer.parseInt((String) fieldValues.get("total"));
        if(total > 0)
            this.fieldValues.put("reserved",total);
    }

    private void preCreateOpUserCoupon() throws Exception{
        BaseDao baseDao = ConfigUtil.getBaseDao();
        if(fieldValues.get("coupon_id") == null){
            String errMsg = getErrorMsg("preCreateOpUserCoupon","coupon_id不能为空!");
            throw new ServiceException(errMsg);
        }
        Integer couponId = Integer.parseInt((String) fieldValues.get("coupon_id"));
        if( couponId == 0){
            String errMsg = getErrorMsg("preCreateOpUserCoupon","coupon_id不能为空!");
            throw new ServiceException(errMsg);
        }
        Object[] values = {couponId};

        DatabaseContextHolder.clearDatabaseType();
        DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.tyDatasource);
        baseDao.updateBySql("update product_coupon set reserved=reserved-1 where id=?",values);
    }

    private String getErrorMsg(String method, String reason){
        String strObj = "";
        		if(this.fieldValues != null){
        			JSONSerializer serializer = new JSONSerializer().exclude("*.class")
        					.transform(new BasicDateTransformer(), Timestamp.class)
        					.transform(new DateTransformer("yyyy-MM-dd"), Timestamp.class);
        			strObj = serializer.deepSerialize(fieldValues) ;
        		}
        return "class:[ActionXManager-CustomizedActionHandling]," +
                "method:[" + this.action + "-" + method + "],user:["
                + userInfo.getsUserHandle() + "]: " + reason + "\n" +
                "fieldValues:[" + strObj + "]";
    }

}
