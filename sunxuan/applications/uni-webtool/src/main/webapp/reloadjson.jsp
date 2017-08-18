<%@ page import="com.songouhe.internal.uwt.model.utils.ConfigUtil" %>
<%@ page import="com.songouhe.internal.uwt.action.ViewManager" %>
<%--
  Created by IntelliJ IDEA.
  User: wangwentao
  Date: 17-6-1
  Time: 上午10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
   <%

            //reload json file config
     System.out.print("********************view Config Path[" + ConfigUtil.getViewConfigPath() + "]*******************");
     ViewManager.setMainViewModelFromPath(ConfigUtil.getViewConfigPath());
       ViewManager vm = ViewManager.getUserViewManager(request);
       vm.setIsModifyByPriv(false);

     System.out.print("********************MainView config complete*******************");
            //reload json file config ends



   %>
</body>
</html>
