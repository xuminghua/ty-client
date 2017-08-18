package com.songouhe.internal.uwt.action;

import com.songouhe.base.sso.action.SSOUserInfo;
import flexjson.JSONDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sunxuan
 * @version 1.0 16-12-3
 */
public class ReadDataManager {
    private static Logger logger
                = LoggerFactory.getLogger(ReadDataManager.class);

    public String getPanelWorkspaceData(HttpServletRequest request, String inSParams ) throws Exception{
//        String operationID, String columnID
        Map<String,String> params = (Map) new JSONDeserializer()
                .deserialize(inSParams, Map.class);
        SSOUserInfo userInfo = (SSOUserInfo) request.getSession().getAttribute("SSOUserInfo");

        ViewManager vm = ViewManager.getUserViewManager(request);
        if(vm == null){
            logger.error("error","ViewManager hasn't be built for user[" + userInfo.getsUserHandle() + "]");
            return ("");
        }
        String result = vm.getJSONArrayPanelWorkspace(userInfo.getPrivilege(),
                params.get("tabCategoryId"), params.get("columnID"));
        return (result);
    }
    public String getTreeColumnMapData(HttpServletRequest request, String inSParams){
        Map<String,String> params = (Map) new JSONDeserializer().deserialize(inSParams, Map.class);

        ViewManager vm = ViewManager.getUserViewManager(request);
        String result = vm.getJSONArrayTreeColumnMap(params.get("tabCategory"));
        return (result);
        }


}
