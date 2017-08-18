<%@ page contentType="text/html; charset=GBK" %>


<html>
<body>
<%
session.invalidate();
response.sendRedirect(application.getInitParameter("casServerLogoutUrl")
    + "?service="
    + application.getInitParameter("serverName") + "/uwt");
%>
</body>
</html>