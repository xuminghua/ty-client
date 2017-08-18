<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.jasig.cas.client.util.AssertionHolder"%>


<html>
<body>
<%
        String username = AssertionHolder.getAssertion().getPrincipal().getName();
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        response.getWriter().print(attributes.toString());
        String id = (String)attributes.get("uid");
        String passwd = (String)attributes.get("PASSWD");
        String used = (String)attributes.get("used");
        response.getWriter().print("11 hello, " + username + " . id:" + id + " . password:" + passwd + " . used:" + used);

%>
<h2>Hello <%=username%>!</h2>
<a href="https://sso.byou.com:8443/sso/logout">logout</a>
    <a href="${pageContext.request.contextPath}/logout.jsp">µÇ³ö</a>
</body>
</html>
