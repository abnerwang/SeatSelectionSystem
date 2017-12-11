<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 09/12/2017
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>座位释放界面</title>
</head>
<body>
<p>
<form action="/SeatSelection/servlet/ReleaseSeatServlet" method="post">
    你要释放 <input type="text" name="classroomID"/> 教室第 <input type="text" name="seatRow"/> 排<br/>
    第 <input type="text" name="seatColumn"/> 座。<br/>
    <input type="submit" value="提交"/>
</form>
</p>
</body>
</html>
