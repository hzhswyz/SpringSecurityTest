<%--
  Created by IntelliJ IDEA.
  User: HZH
  Date: 2019/4/16
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
首页INDEX
<form action="/logout" method="post">
    <input type="submit" value="Sign Out"/>
    <input type="text" name="restful">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>
