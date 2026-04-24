<%@ page import="java.util.ArrayList" %>
<%@ page import="com.registerlogin.user.model.TopicModel" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/16/2026
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>All Topics</title>
</head>
<body>
<div>
    <a href="add-topic">Add Topic</a>
</div>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Topic Name</th>
            <th>Created At</th>
            <th>Updated At</th>
            <th>Action</th>
        </tr>
        <c:forEach var="t" items="${topics}">
            <tr>
                <td>${t.getId()}</td>
                <td>${t.getName()}</td>
                <td>${t.getCreatedAt()}</td>
                <td>${t.getUpdatedAt()}</td>
                <td>
                    <a href="update-topic?id=${t.getId()}">
                        Updated
                    </a>

                    <button>Delete</button>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>