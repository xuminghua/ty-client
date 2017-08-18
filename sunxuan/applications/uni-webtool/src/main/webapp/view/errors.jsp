<%@ page import="com.test1" %>
<%--
  Created by IntelliJ IDEA.
  User: wangwentao
  Date: 16-12-1
  Time: 下午5:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:directive.include file="includes/top.jsp" />
  <div id="msg" class="errors">
    <h2><spring:message code="screen.unavailable.heading" /></h2>
    <h3><spring:message code="screen.unavailable.message" /></h3>
    <%Exception e = (Exception)request.getAttribute("javax.servlet.error.exception");
      if( e != null )e.printStackTrace();%>
    <p>Error Message:<%=request.getAttribute("javax.servlet.error.message")%></p>
    <p>Error Status code:<%=request.getAttribute("javax.servlet.error.status_code")%></p>
    <p>Error request URI:<%=request.getAttribute("javax.servlet.error.request_uri")%></p>
  </div>
<jsp:directive.include file="includes/bottom.jsp" />
