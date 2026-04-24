<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="register">Register</a>
<a href="login">Login</a>
<a href="dashboard">Dashboard</a>

<%
    String errormsg=(String) request.getAttribute("error");
    com.registerlogin.user.model.UserModel user=(com.registerlogin.user.model.UserModel) session.getAttribute("user");

%>
<%
    if(user!=null){
%>
<h1>Welcome ${user.getName()}</h1>
<%}else{%>
<h1>Please Login</h1>
<%}%>


<c:choose>
    <c:when test="${not empty user}">
        <h1>Welcome ${user.name}</h1>
    </c:when>
    <c:otherwise>
        <h1>Please Login</h1>
    </c:otherwise>
</c:choose>
</body>
</html>