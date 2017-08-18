<%--
  Created by IntelliJ IDEA.
  User: wangwentao
  Date: 16-11-21
  Time: 上午7:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.songouhe.base.sso.action.SSOUserInfo" %>
<%@ page import="com.songouhe.internal.uwt.action.ViewManager" %>

<html>
<head>
    <%--<meta charset="UTF-8" />--%>
    <title>统一工作平台</title>
    <link rel="stylesheet" href="css/uwt.css" />
    <!--[if lt IE 9]>
    <![endif]-->
</head>
<body id="uwt">

<%--<h2>Hello <%=username%>!</h2>--%>

<div id="container">
    <header>
        <a id="logo" href="http://www.songouhe.com" title="进入公司主页">B游</a>
        <h1>统一工作平台</h1>
    </header>
    <div id="loading-mask" style=""></div>

    <div id="loading">
        <div class="loading-indicator"><img src="images/uwt/public/loadmask.gif" width="32" height="32"
                                            style="margin-right:8px;float:left;vertical-align:top;"/>Uni Web Tool<br/><span
                id="loading-msg">Loading styles and images...</span></div>
    </div>

    <div id="mainbd">
        <link rel="stylesheet" type="text/css" href="ext4/resources/css/ext-all.css"/>
        <script type="text/javascript" src="ext4/ext-all.js" ></script>
        <script type="text/javascript" src="js/uwt/dictionary/original_resource_zh_CN.js" ></script>
        <script type="text/javascript" src="js/uwt/app/lib/bubblebox.js" ></script>
        <script type="text/javascript" src="js/uwt/UWTConfiguration.js" ></script>
        <%
            //user information
            SSOUserInfo userInfo = SSOUserInfo.getInstance(request);
            //get tab operations
            ViewManager vm = ViewManager.getUserViewManager(request);
            vm.setUserViewFromPrivilege(userInfo);

        %>
        <script type="text/javascript">
            var tabs = <%=vm.getJSONArrayTabCategories()%>;
            var init_TreeColumnMap = <%=vm.getJSONArrayTreeColumnMap(null)%>;
            var init_panelWorkspace =
                    <%=vm.getJSONArrayPanelWorkspace(
                    userInfo.getPrivilege(),"","")%>;
            var userInfo = Ext.decode('<%=userInfo.TransUserInfoToString()%>');
            UWTConfiguration.initFromJsp(
                    "<%=request.getContextPath()%>/logout.jsp",
                    <%--'<%=application.getInitParameter(--%>
//            "casServerLogoutUrl")%>',
                    tabs.tabCategories,
                    init_TreeColumnMap,
                    init_panelWorkspace);
            UWTConfiguration.setUserinfo(userInfo );
        </script>

        <script type="text/javascript" src="js/uwt/app/view/Windows.js" ></script>
        <script type="text/javascript" src="js/uwt/main.js" ></script>

    </div>

    <div id="content">
        <div id="mainpanel"/>
    </div> <!-- END #content -->

    <jsp:directive.include file="view/includes/bottom.jsp" />


