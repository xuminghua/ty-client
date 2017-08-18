package com.songouhe.internal.uwt.action;

import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.DaoSysException;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.songouhe.internal.uwt.model.entity.daoentity.classes.AbstractCommonClass;
import com.songouhe.internal.uwt.model.entity.daoentity.classes.User_Class_Actor;
import com.songouhe.internal.uwt.model.entity.daoentity.classes.User_Class_Department;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import com.songouhe.internal.uwt.model.utils.JsonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CommonPrepareAction为非页面响应时执行的运行逻辑，主要被DataPretreater调用
 * @author sunxuan
 * @version 1.0 17-7-19
 */
public class CommonPrepareAction {
    private final static Logger logger = LoggerFactory.getLogger(CommonPrepareAction.class);

    public static void prepareDBClass(BaseDao baseDao) throws Exception {
        //获取combo配置
        HashMap combosForView = JsonFileUtil.getCombosFromJsonFile(ConfigUtil.getComboConfigPath());
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.uwtDatasource);
        //User_Class_Department
        List dbtList = getObjList(baseDao, new User_Class_Department());
        combosForView.put("User_Class_Department", dbtList);
        //User_Class_Actor
        List actorList = getObjList(baseDao,new User_Class_Actor());
        combosForView.put("User_Class_Actor", actorList);

        ConfigUtil.setCombosForView(combosForView);


    }

    /**
     * 根据输入的obj的类型和class name去数据库取所有record并返回
     * @param baseDao
     * @param obj
     * @return
     * @throws DaoSysException
     */
    private static List getObjList(BaseDao baseDao, Object obj) throws DaoSysException {
        ArrayList objs = (ArrayList) baseDao.getQueryByObj(obj);
        List result = new ArrayList();
        for(Object object:objs){
            AbstractCommonClass ac = (AbstractCommonClass)object;
            String[] s = {ac.getId(), ac.getName()};
            //每一项0索引的值将被假定为combo的valueField，1索引的值被假定为combo的displayField。
            result.add(s);
        }
        logger.info("table: "+ obj.getClass().getName() + " size: " + result.size());
        return result;
    }
}
