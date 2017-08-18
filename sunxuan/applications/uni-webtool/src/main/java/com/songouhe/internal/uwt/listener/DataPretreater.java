package com.songouhe.internal.uwt.listener;


import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.impl.BaseDaoImpl;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.songouhe.base.util.model.EnvironmentVariable;
import com.songouhe.internal.uwt.action.CommonPrepareAction;
import com.songouhe.internal.uwt.action.ViewManager;
import com.songouhe.internal.uwt.model.entity.daoentity.classes.Class_Privilege;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.util.*;

/**
 * @author sunxuan
 * @version 1.0 2016-11-10
 */
public class DataPretreater
        implements java.io.Serializable, ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(DataPretreater.class);
    //@Autowired
    private BaseDao baseDao;
    @Autowired
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        try {
            //*********************环境读取********************//
            // 取到操作系统名称
            Properties properties = new Properties(System.getProperties());
            ConfigUtil.setOsName(properties.getProperty("os.name"));
            logger.info("********************OS name[" + ConfigUtil.getOsName() + "]*******************");
            // 以Spring的配置方式读取common.properties
            WebApplicationContext appContext =
                    WebApplicationContextUtils.getWebApplicationContext(context);
            Properties propertiesFromContext = (Properties) appContext.getBean("commons");
            ConfigUtil.setPropertiesFromContext(propertiesFromContext);

            // 取到区域, 如果强行配置了区域, 以配置为准
            String locale = propertiesFromContext.getProperty("env.locale");
            ConfigUtil.setEnv_locale(locale);
            logger.info("********************Real Locale[" + Locale.getDefault() + "]*******************");
            logger.info("********************Locale[" + ConfigUtil.getEnv_locale() + "]*******************");

            // 取到应用服务器版本
            logger.info("********************Webserver version[" + context.getServerInfo() + "]*******************");

            // tomcat 对于jndi的写法与weblogic和websphere不一样
//            if (webserver != null && (webserver.indexOf("Tomcat") != -1)) {
//                ConfigUtil.setJndiName("java:comp/env/" + BaseConfig.getParameter("datasource_jndi_name"));
//            } else {
//                ConfigUtil.setJndiName(BaseConfig.getParameter("datasource_jndi_name"));
//            }

            logger.info("********************Current DataBaseType[" + ConfigUtil.getDataBaseTypeEnum() + "]*******************");

            //设置file uplad info
            String fileBasePath = propertiesFromContext.getProperty("file.base.path");
            ConfigUtil.setFileBasePath(fileBasePath);
            logger.info("********************file base path[" + ConfigUtil.getFileBasePath() + "]*******************");
            int filemaxsize = Integer.parseInt(propertiesFromContext.getProperty("file.max.size"));
            ConfigUtil.setFileMaxSize(filemaxsize);
            logger.info("********************file max size[" + ConfigUtil.getFileMaxSize() + " MB]*******************");

            //设置page encoding
            String encoding = propertiesFromContext.getProperty("web.encoding");
            ConfigUtil.setEncoding(encoding);
            logger.info("********************webpage encoding[" + ConfigUtil.getEncoding() + "]*******************");

            String entityPath = propertiesFromContext.getProperty("entity.path");
            ConfigUtil.setEntityPath(entityPath);
            logger.info("********************entity path[" + ConfigUtil.getEntityPath() + "]*******************");

            //*********************环境读取end********************//

            //*********************应用数据读取********************//
            // 确定使用的数据库类型
            for(DataBaseTypeEnum dbType: DataBaseTypeEnum.values()){
                logger.info("********************DataBaseType list contains[" + dbType + "]*******************");
            }
            ApplicationContext applicationContext = (WebApplicationContext)sce.getServletContext()
                    .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
            baseDao = applicationContext.getBean("baseDao",BaseDaoImpl.class );
            baseDao.setAutoCommit(false);  //支持回滚
            ConfigUtil.setBaseDao(baseDao);
//          dao用法
//            DatabaseContextHolder.clearDatabaseType();
//            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.ssoDatasource);
//
//            List records = baseDao.getQueryByObj(new BaseUserInfo());
//            for(Object ue:records){
//                logger.info(((BaseUserInfo) ue).getName());
//            }



            HashMap privleges = new HashMap();
            privleges.put("ALL", 1);
            privleges.put("SELF", 2);
            privleges.put("TEAMER", 3);
            privleges.put("COORDINATOR", 4);

            //从staffuser取权限配置
            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.ssoDatasource);

            ArrayList orig_privileges = (ArrayList) baseDao.getQueryByObj(new Class_Privilege());
            for(Object object:orig_privileges){
                Class_Privilege orig_privilege = (Class_Privilege)object;
                privleges.put(orig_privilege.getId(),orig_privilege.getCode());
            }

            DatabaseContextHolder.clearDatabaseType();
            DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.uwtDatasource);

//            privleges.put("HR_BASE", 100);
//            privleges.put("RD_BASE", 200);
//            privleges.put("OP_BASE", 300);
            privleges.put("SYS_BASE", 900);

            ConfigUtil.setPrivileges(privleges);
            logger.info("********************privleges config complete*******************");

            String path = sce.getServletContext().getRealPath("/") + propertiesFromContext.getProperty("viewjson.path");
            ConfigUtil.setViewConfigPath(path);
            logger.info("********************view Config Path[" + ConfigUtil.getViewConfigPath() + "]*******************");
            ViewManager.setMainViewModelFromPath(ConfigUtil.getViewConfigPath());
            logger.info("********************MainView config complete*******************");

            String comboPath = sce.getServletContext().getRealPath("/") +
                    propertiesFromContext.getProperty("combojson.path") ;
            ConfigUtil.setComboConfigPath(comboPath);
            logger.info("********************combo Config Path[" + ConfigUtil.getComboConfigPath() + "]*******************");

            CommonPrepareAction.prepareDBClass(baseDao);
            logger.info("********************combos config complete*******************");

            ConfigUtil.setErrorURL(propertiesFromContext.getProperty("error.url"));
            logger.info("********************Error URL[" + ConfigUtil.getErrorURL() + "]*******************");


            EnvironmentVariable projectID = new EnvironmentVariable();
            ConfigUtil.setProjectID(projectID.getValue());
            logger.info("********************ProjectID[" + ConfigUtil.getProjectID() + "]*******************");

            ConfigUtil.setDisplayJsonOutput(true);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("********************Songouhe UWT Started Successfully!*******************");
    }


    @SuppressWarnings("deprecation")
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            System.out.println("**********stopping UWT*************");
            LogManager.shutdown();
            for(Thread t:Thread.getAllStackTraces().keySet()){

              if(t.getName().equals("FileWatchdog")  ||
                      t.getName().equals("perf4j-async-stats-appender-sink-CoalescingStatistics")){
                System.out.println("Found " +t.getName() + " thread.Killing it");
                  t.stop();
              }
//              else if(t.getName().equals("perf4j-async-stats-appender-sink-CoalescingStatistics")){
//                  System.out.println("Found perf4j-async-stats-appender-sink-CoalescingStatistics thread.Killing it");
//                  t.stop();
//              }
            }
            System.out.println("contextDestroyed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
