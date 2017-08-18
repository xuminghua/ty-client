package com.byou.oa.sso;


import org.apache.log4j.LogManager;
import org.perf4j.log4j.AsyncCoalescingStatisticsAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;

/**
 * @author sunxuan
 * @version 1.0 2016-11-10
 */
public class ByouDataPretreater
        implements java.io.Serializable, ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(ByouDataPretreater.class);

    @Autowired
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("********************SSO Started Successfully!*******************");
    }

    @SuppressWarnings("deprecation")
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("**********stopping SSO*************");
            LogManager.shutdown();
            for(Thread t:Thread.getAllStackTraces().keySet()){

              if(t.getName().equals("FileWatchdog")  ){
                System.out.println("Found FileWatchdog thread.Killing it");
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
