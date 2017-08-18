package com.songouhe.internal.sample.test;

import com.songouhe.base.dao.BaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author sunxuan
 * @version 1.0 17-2-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext02.xml"})
public class BaseDaoThreadTest {
    @Autowired
    private ThreadRunner thR;
    @Autowired
    private BaseDao baseDao;
    @Test
    public void getQueryList(){

        for (int i=0; i<10; i++){
            System.out.println(i);
            new Thread(thR).start();
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i=0; i<10; i++){
            System.out.println(i);
            new Thread(thR).start();
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i=0; i<10; i++){
            System.out.println(i);
            new Thread(thR).start();
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Test
    public void getIdleConnection(){
        try {
            DataSource ds = baseDao.getDatasource();
            Connection conn = baseDao.getDatasource().getConnection();
//            PooledDataSource pds = (PooledDataSource) ds;

//            System.out.println("空闲的连接: " + pds.getNumIdleConnectionsDefaultUser());
//            System.out.println("使用中的连接: " + pds.getNumBusyConnectionsDefaultUser());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
