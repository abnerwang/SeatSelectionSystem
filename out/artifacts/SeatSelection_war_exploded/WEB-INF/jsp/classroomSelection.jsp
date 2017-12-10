<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 09/12/2017
  Time: 12:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教室选择界面</title>
</head>
<body>
<form action="/SeatSelection/servlet/ClassroomSelectionServlet" method="post">
    教室号： <input type="text" name="classroomID"/><br/>
    <input type="submit" value="发送">
</form>
</body>
</html>
