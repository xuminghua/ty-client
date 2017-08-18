package com.songouhe.internal.uwt.servlet;

import com.songouhe.internal.uwt.action.ActionXManager;
import com.songouhe.internal.uwt.action.ViewManager;
import com.songouhe.internal.uwt.model.enums.OperationTypeEnum;
import com.songouhe.internal.uwt.model.utils.ConfigUtil;
import flexjson.JSONSerializer;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author sunxuan
 * @version 1.0 17-2-13
 */
public class ActionXServlet extends HttpServlet {
    private static org.slf4j.Logger logger
            = LoggerFactory.getLogger(ActionXServlet.class);


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
        //执行操作前准备参数
        String sColumnId = request.getParameter("columnId");                 // panelcake id
        String sPCid = request.getParameter("panelcakeId");                 // panelcake id
        String sAction = request.getParameter("action");           // 方法名
        logger.info("**actionXServlet**columnId:" + sColumnId + ", panelcakeId:"
                + sPCid + ", action:" + sAction);

        HashMap paramMap = (HashMap) request.getParameterMap();

        //获取当前用户的viewmanager
        ActionXManager actionXManager = new ActionXManager(sColumnId,sPCid, request);
        if(sAction.equals("search")){
            search(response,paramMap, actionXManager);
        }else{
            operation(response, sAction, paramMap, actionXManager);
        }
    }
    private void search( HttpServletResponse response,
                         HashMap paramMap, ActionXManager actionXManager) throws IOException {
        String result;

        try {
            result = actionXManager.search(paramMap);

            response.setContentType("text/json; charset=" + ConfigUtil.getEncoding());
            PrintWriter writer = response.getWriter();

            if (ConfigUtil.isDisplayJsonOutput())
                logger.debug(result);
            writer.write(result);
            writer.close();

        } catch (Exception e) {
            logger.error("error", e);
            response.setContentType("text/json; charset=" + ConfigUtil.getEncoding());
            PrintWriter writer = response.getWriter();
            writer.close();

        }

    }
    private void operation(HttpServletResponse response, String sAction, HashMap paramMap,
                           ActionXManager actionXManager) throws IOException {
        String strResponse = "";
        boolean isSuccess = false;
        String msg = "操作成功!";
        response.setContentType("text/json; charset=" + ConfigUtil.getEncoding());
        try {
            if(sAction == null || sAction.equals("")){
                strResponse = "{\"success\":false,\"msg\":\"action 不能为空.\"}";
                return;
            }else {
                HashMap fieldValues = new HashMap();
                Set<String> keys = (Set<String>) paramMap.keySet();
                for(String key: keys){
                    if(!(key.equals("columnId") || key.equals("panelcakeId") ||
                            key.equals("action")))
                    fieldValues.put(key,((Object[])paramMap.get(key))[0]) ;
                }

                OperationTypeEnum action = OperationTypeEnum.valueOf(sAction);
                switch (action) {
                    case create:
                        isSuccess = actionXManager.create(fieldValues);
                        break;
                    case update:
                        isSuccess = actionXManager.update(fieldValues);
                        break;
                    case delete:
                        isSuccess = actionXManager.delete(fieldValues);
                        break;
                }

            }


            if (ConfigUtil.isDisplayJsonOutput())
                logger.debug(strResponse);


        } catch (Exception e) {
            logger.error("error", e);
            msg = "操作失败, 请重新尝试:" + e.getMessage();


        }finally {
            if (isSuccess) {
                strResponse = "{\"success\":true,\"msg\":\"操作成功!\"}";

            } else {
                strResponse = "{\"success\":false,\"msg\":\"" + msg + "\"}";

            }
            PrintWriter writer = response.getWriter();
            writer.write(strResponse);
            writer.close();
        }
    }
}
