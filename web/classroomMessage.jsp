<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 09/12/2017
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>座位信息展示页面</title>
</head>
<body>
<p>
    该教室总共有 ${sumOfSeats} 个座位，剩余 ${restOfSeats} 个座位可选，被占用的座位有：<br/>
    第 ${occupiedArray[0].seatRow} 排第 ${occupiedArray[0].seatColumn} 组 <br/>
    第 ${occupiedArray[1].seatRow} 排第 ${occupiedArray[1].seatColumn} 组 <br/>
</p>
</body>
</html>
