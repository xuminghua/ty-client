package com.songouhe.internal.uwt.servlet;

/**
 * 各个处理类的入口类，页面的ajax请求通过本类委托各个处理类进行处理
 * Created by sunxuan on 16-11-24.
 */

import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class MainServlet extends HttpServlet {
    private static final String defaultPackage = "com.songouhe.internal.uwt.action.";
    private static org.slf4j.Logger logger
                = LoggerFactory.getLogger(MainServlet.class);


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String cName = request.getParameter("cName");                 // 类名
        String cMethod = request.getParameter("cMethod");           // 方法名
        String para = request.getParameter("para");                        // 参数：json格式

        logger.info("**MainServlet**cName:" + cName + ", cMethod:" + cMethod);
        if(cName == null || cMethod == null){
            logger.error("error", "cName,cMethod 不能为空!");
            response.setContentType("text/json; charset=" + ConfigUtil.getEncoding());
            PrintWriter writer = response.getWriter();
            writer.write("cName,cMethod 不能为空!");
            writer.close();
            return;
        }
        try {
            Class c = Class.forName(defaultPackage + cName);
            Object cons = c.newInstance();
            String strResponse = null;

            Object[] argss = null;

            Method method = null;   //c.getMethod("testMethod", argsC[0]);
            Method methods[] = c.getMethods();
            for (int i = 0; i < methods.length; i++) {
                method = methods[i];
                String name = method.getName();
                //there is find for 1st method
                if (name.equals(cMethod)) {
                    Class[] cc = method.getParameterTypes();
                    argss = new Object[cc.length];
                    for (int j = 0; j < cc.length; j++) {
                        //System.out.println(cc[j].getName());
                        if ((cc[j].getName()).equals("javax.servlet.http.HttpServletRequest")) {
                            argss[j] = request;
                        } else if ((cc[j].getName()).equals("javax.servlet.http.HttpServletResponse")) {
                            argss[j] = response;
                        } else {
                            argss[j] = para;
                        }
                    }
                    break;
                }
            }


            if (argss.length != 0) {
                strResponse = (String) method.invoke(cons, argss);
            } else {
                strResponse = (String) method.invoke(cons);
            }
            response.setContentType("text/json; charset=" + ConfigUtil.getEncoding());
            PrintWriter writer = response.getWriter();

            if (ConfigUtil.isDisplayJsonOutput())
                logger.debug(strResponse);
            writer.write(strResponse);
            writer.close();

        } catch (Exception e) {
            logger.error("error", e);
            response.setContentType("text/json; charset=" + ConfigUtil.getEncoding());
            PrintWriter writer = response.getWriter();

            writer.write(e.getMessage());
            writer.close();

        }


    }
}
