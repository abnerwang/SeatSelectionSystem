<%--
  Created by IntelliJ IDEA.
  User: abnerwang
  Date: 07/12/2017
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户注册界面</title>
    <script type="text/javascript">
        /*
        *	防止表单重复提交
        */
        var isCommited = false;

        function doSubmit() {
            if (!isCommited) {
                isCommited = true;
            }
            return isCommited;
        }
    </script>
</head>
<body>
<form action="/SeatSelection/servlet/UserRegisterServlet" method="post">
    学号：<input type="text" name="studentID"/>${registerForm.errorMap.studentID }${message }<br/>
    姓名：<input type="text" name="username"/><br/>
    密码：<input type="password" name="password"/>${registerForm.errorMap.password }<br/>
    确认密码：<input type="password" name="confirmedPassword"/>${registerForm.errorMap.confirmedPassword }<br/>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
