<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/9/2026
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="update-topic" method="post">
    <input type="hidden" name="id" value="${topic.getId()}"/>
    <input name="topic_name" value="${topic.getName()}"/>
    <button type="submit">Update Topic</button>
</form>
</body>
</html>