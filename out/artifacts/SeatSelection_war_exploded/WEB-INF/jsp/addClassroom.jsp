<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 09/12/2017
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新建教室界面</title>
</head>
<body>
<p>
<form action="/SeatSelection/servlet/AddClassroomServlet" method="post">
    教室号：<input type="text" name="classroomID"/> ${message} <br/>
    教室容量：<input type="text" name="countOfSeats"/> <br/>
    <input type="submit" value="创建"/>
</form>
</p>
</body>
</html>
