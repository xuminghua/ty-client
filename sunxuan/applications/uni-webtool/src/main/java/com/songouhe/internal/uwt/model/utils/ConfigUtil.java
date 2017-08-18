package com.songouhe.internal.uwt.model.utils;


import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.internal.uwt.model.viewconfig.MainViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author sunxuan
 * @version 1.0 16-8-14
 */
public class ConfigUtil {
    public static Properties getPropertiesFromContext() {
        return propertiesFromContext;
    }

    private final static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    public static void setPropertiesFromContext(Properties propertiesFromContext) {
        ConfigUtil.propertiesFromContext = propertiesFromContext;
    }

    /**
     * 存取从commonproperties中读取的配置信息
     */
    private static Properties propertiesFromContext;
    /**
     * 存取DataBaseType名称
     */
    private static DataBaseTypeEnum dataBaseTypeEnum = DataBaseTypeEnum.defaultDateSource;

    /**
     * 存取操作系统名称
     */
    private static String osName = "Windows";

    /**
     * 存取数据库类型
     */
    private static int dbType = 0;
    /**
     * 存取dao实例
     */
    private static BaseDao baseDao;

    /**
     * 存取区域及语言
     */

    private static String env_locale = "zh_CN";

    /**
     * projectID设置
     */
    private static String projectID;

    /**
     * entityPath设置
     */
    private static String entityPath;

    /**
     * uploaded file base path
     */
    private static String fileBasePath;

    /**
     * uploaded file max size, unit: MB
     */
    private static int fileMaxSize = 1;

    /**
     * 页面传输数据的编码
     */
    private static String encoding = "UTF-8";

    /**
     * 是否后台打印传输的json数据
     */
    private static boolean displayJsonOutput;

    /*
    权限配置集合
     */
    private static HashMap<String,Integer> privileges;
    /*
    页面配置
     */
    private static MainViewModel mainViewModel;

    private static String viewConfigPath;
    private static String comboConfigPath;

    private static String errorURL;
    /**
     * 页面所需的combo的集合
     */
    private static Map combosForView;

    public static Map getCombosForView() {
        return combosForView;
    }

    public static void setCombosForView(Map inCombosForView){
        combosForView = inCombosForView;

    }

    public static String getComboConfigPath() {
        return comboConfigPath;
    }

    public static void setComboConfigPath(String comboConfigPath) {
        ConfigUtil.comboConfigPath = comboConfigPath;
    }

    public static String getEncoding() {
        return encoding;
    }

    public static void setEncoding(String encoding) {
        ConfigUtil.encoding = encoding;
    }

    public static boolean isDisplayJsonOutput() {
        return displayJsonOutput;
    }

    public static void setDisplayJsonOutput(boolean displayJsonOutput) {
        ConfigUtil.displayJsonOutput = displayJsonOutput;
    }

    public static String getOsName() {
        return osName;
    }

    public static void setOsName(String osName) {
        ConfigUtil.osName = osName;
    }

    public static DataBaseTypeEnum getDataBaseTypeEnum() {
        return dataBaseTypeEnum;
    }

    public static void setDataBaseTypeEnum(DataBaseTypeEnum dataBaseTypeEnum) {
        ConfigUtil.dataBaseTypeEnum = dataBaseTypeEnum;
    }

    public static int getDbType() {
        return dbType;
    }

    public static void setDbType(int dbType) {
        ConfigUtil.dbType = dbType;
    }

    public static String getEnv_locale() {
        return env_locale;
    }

    public static void setEnv_locale(String env_locale) {
        ConfigUtil.env_locale = env_locale;
    }

    public static String getProjectID() {
        return projectID;
    }

    public static void setProjectID(String projectID) {
        ConfigUtil.projectID = projectID;
    }

    public static HashMap getPrivileges() {
        return privileges;
    }

    public static BaseDao getBaseDao() {
        return baseDao;
    }

    public static void setBaseDao(BaseDao baseDao) {
        ConfigUtil.baseDao = baseDao;
    }



    public static int getPrivilegeCode(String inPrivilege) {
        if(ConfigUtil.privileges.containsKey(inPrivilege)){
            return ConfigUtil.privileges.get(inPrivilege);
        }else
            return -1;
    }
    public static int[] getPrivilegeCodes(String[] inPrivilege) {
        if(inPrivilege == null || inPrivilege.length == 0)return null;
        int[] privCodes = new int[inPrivilege.length];
        for(int i =0; i < inPrivilege.length; i ++){
            if(ConfigUtil.privileges.containsKey(inPrivilege[i]))
                privCodes[i] = ConfigUtil.privileges.get(inPrivilege[i]);
        }
        Arrays.sort(privCodes);  //排序可以减少比较次数
        return privCodes;
    }
    public static boolean checkPrivilegeRange( int inMinPrivilege, int inMaxPrivilege,
                                              Set<String> inPrivileges){
        if(inPrivileges == null || inPrivileges.isEmpty())return false;
        for(String priv: inPrivileges){
            if(priv == null || priv.equals(""))return false;
          int iPriCode = getPrivilegeCode(priv);
          if(iPriCode <= inMaxPrivilege && inMaxPrivilege >= inMinPrivilege){
              return true;
          }
        }

        return false;
    }


    public static MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public static void setMainViewModel(MainViewModel mainViewModel) {
        ConfigUtil.mainViewModel = mainViewModel;
    }

    public static String getErrorURL() {
        return errorURL;
    }

    public static void setErrorURL(String errorURL) {
        ConfigUtil.errorURL = errorURL;
    }

    public static String getViewConfigPath() {
        return viewConfigPath;
    }

    public static void setViewConfigPath(String viewConfigPath) {
        ConfigUtil.viewConfigPath = viewConfigPath;
    }

    public static String getEntityPath() {
        return entityPath;
    }

    public static void setEntityPath(String entityPath) {
        ConfigUtil.entityPath = entityPath;
    }

    public static void setPrivileges(HashMap<String, Integer> privileges) {
        ConfigUtil.privileges = privileges;
    }

    public static String getFileBasePath() {
        return fileBasePath;
    }

    public static void setFileBasePath(String fileBasePath) {
        ConfigUtil.fileBasePath = fileBasePath;
    }

    public static int getFileMaxSize() {
        return fileMaxSize;
    }

    public static void setFileMaxSize(int fileMaxSize) {
        ConfigUtil.fileMaxSize = fileMaxSize;
    }
}
