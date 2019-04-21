<%--
  Created by IntelliJ IDEA.
  User: HZH
  Date: 2019/4/16
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <style>
        .container{
            width: 100%;
            display: flex;
            flex-direction: column;
        }
        .title{
            display: flex;
            flex-direction: row;
            justify-content: center;
            margin-bottom: 20px;
            font-size: 20px;
        }
        .form{
            display: flex;
            flex-direction: row;
            justify-content: center;
        }
        table{
            border-collapse:separate;
            border-spacing:0px 10px;
        }
        .button{
            display: flex;
            flex-direction: row;
            justify-content: center;
        }
    </style>
</head>
<body>
<c:if test="${param.error == true }">
    error : ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
</c:if>
<br>
<div class="container">
    <div class="title">管理员登陆页面</div>
    <form class="form" action="/login" method="post">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" style="width:150px;"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td>
                    <input type="password" name="password" style="width:150px;" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="checkbox"
                                       name="_spring_security_remember_me"/>记住密码</td>
            </tr>
            <tr>
                <td ><input type="submit" value="登陆" /> </td>
                <td ><input type="reset" value="重置" /></td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>
