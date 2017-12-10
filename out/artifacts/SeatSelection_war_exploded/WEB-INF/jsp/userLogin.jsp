<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 07/12/2017
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录界面</title>
</head>
<body>
<form action="/SeatSelection/servlet/UserLoginServlet" method="post">
    学号：<input type="text" name="studentID">${loginForm.errorMap.studentID }${message}<br/>
    密码：<input type="password" name="password"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
