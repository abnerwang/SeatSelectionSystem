<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 09/12/2017
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户选座界面</title>
</head>
<body>
<p>
<form action="/SeatSelection/servlet/SeatSelectionServlet" method="post">
    学号：<input type="text" name="studentID"/> <br/>
    选择 <input type="text" name="classroomID"/> 教室<br/>
    第 <input type="text" name="seatRow"/> 排第 <input type="text" name="seatColumn"/> 座。<br/>
    ${message}
    <input type="submit" value="发送"/>
</form>
</p>
</body>
</html>
