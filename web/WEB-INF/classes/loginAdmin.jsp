<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员登录</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/loginAdmin.css"/>
    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrapValidator.min.js"></script>
    <script src="js/bootstrapValidator.js"></script>
    <script src="js/checkForm.js"></script>
</head>
<body class="clearfix" onload="onload()">
<%
    Cookie[] coo = request.getCookies();
    String username = "";
    String password = "";

    for (Cookie co : coo) {
        if ("userID".equals(co.getName())) {
            username = co.getValue();
        } else if ("userPassword".equals(co.getName())) {
            password = co.getValue();
        }
    }
%>
<form id="myform" action="/shopping_mall/UserServlet?method=loginAdmin" method="post">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>Login</h2>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" name="username" value="<%=username %>"
                           placeholder="请输入账户名" required autofocus/>
                    <div id="username_message"></div>
                </div>
            </div>
            <div class="col-lg-10"></div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="password" class="form-control" name="password" value="<%=password %>"
                           placeholder="请输入密码" required autofocus/>
                    <div id="password_message"></div>
                </div>
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1" name="remember"><span
                    id="remember">记住密码</span>
            </div>
            <div class="col-lg-10"></div>
            <div class="form-group">
                <div class="col-lg-10">
                    <button type="submit" class="btn btn-success col-lg-12" onclick="submit();">登录</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>