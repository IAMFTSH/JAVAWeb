<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>邝明山购物商城</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/pokemmo.css"/>
    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrapValidator.min.js"></script>
    <script src="js/pokemmo.js"></script>
</head>
<body>
<!--页眉-->
<header class="container-fluid">
    <div class="row clearfix" id="head-images">
        <div class="col-md-3 paddtop">
            <img src="images/pokemmo.png" class="img-responsive"/>
        </div>
        <div class="col-md-6">
            <div class="row">
                <!--
                <div class="col-md-9.5">
                    <input class="search-input" placeholder="请输入商品名称">
                </div>
                <div class="col-md-2.5">

                    <a class="search-btn glyphicon glyphicon-search" href="#">搜索</a>

                </div>
            -->
            </div>
        </div>
        <div class="col-md-3 paddtop">
            <img src="images/pokemmo.png" class="img-responsive">
        </div>
    </div>
    <!--
        描述：导航栏
    -->
    <div class="row">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <!-- 定义汉堡按钮 -->
                    <button type="button" class="navbar-toggle collapsed"
                            data-toggle="collapse" data-target="#navbar-collapse"
                            aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand glyphicon glyphicon-home"
                       href="/shopping_mall/index.jsp">首页</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">商城 <span class="sr-only">(current)</span></a>
                        </li>
                        <li><a href="">店铺</a></li>
                        <li><a href="">店铺</a></li>
                        <li><a href="">店铺</a></li>
                        <li><a href="">店铺</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right margin-right">
                        <li><a id="login"
                               class="glyphicon glypglyphicon-off glyphicon glyphicon-user"
                               data-toggle="modal" data-target="#loginModal">&nbsp;登录</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
    </div>
</header>
<!--
        轮播图
   -->
<div class="row" id="carousel">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <div id="carousel-example-generic" class="carousel slide"
             data-ride="carousel">
            <!-- 指示符 -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0"
                    class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
            </ol>
            <!-- 轮播图片 -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <a href="/shopping_mall/loginAdmin.jsp"><img
                            style="width: 500px; height: 220px;" src="images/a.png" alt="..."
                            class="img-responsive"></a>
                </div>
                <div class="item">
                    <a href="/shopping_mall/loginAdmin.jsp"><img
                            style="width: 500px; height: 220px;" src="images/b.png" alt="..."
                            class="img-responsive"></a>
                </div>
                <div class="item">
                    <a href="/shopping_mall/loginAdmin.jsp"><img
                            style="width: 500px; height: 220px;" src="images/c.png" alt="..."
                            class="img-responsive"></a>
                </div>
                <div class="item">
                    <a href="/shopping_mall/loginAdmin.jsp"><img
                            style="width: 500px; height: 220px;" src="images/d.png" alt="..."
                            class="img-responsive"></a>
                </div>
                <div class="item">
                    <a href="/shopping_mall/loginAdmin.jsp"><img
                            style="width: 500px; height: 220px;" src="images/e.png" alt="..."
                            class="img-responsive"></a>
                </div>
            </div>
            <!-- 左右切换按钮-->
            <a class="left carousel-control" href="#carousel-example-generic"
               role="button" data-slide="prev"> <span
                    class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a> <a class="right carousel-control" href="#carousel-example-generic"
                    role="button" data-slide="next"> <span
                class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
        </div>
    </div>
    <div class="col-md-4"></div>
</div>
<!--
主体
-->
<div class="container" id="jx-container">
    <div class="row">
        <div class="glyphicon glyphicon-sunglasses jx">
            <span>pokemmo精选</span>
        </div>
    </div>
    <div id="dv" class="row paddtop">
        <%-- <%
        if (session.getAttribute("AllCommodityList") != null) {
                List<Commodity> list = (List<Commodity>) session.getAttribute("AllCommodityList");
                for (Commodity com : list) {
                    out.print(com.toString());
                }
            }
            %> --%>
    </div>


</div>
<!-- 页脚-->
<footer class="container-fluid">
    <div class="row company">
        <span class="glyphicon glyphicon-sunglasses">17软件1班javaWeb期末考核作业，组长：邝明山；组员：吕昊、廖森</span>

    </div>
</footer>
<!--
        登录悬浮框
    -->
<div>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
         aria-labelledby="loginModalLabel" aria-hidden="true"
         data-backdrop="true">
        <div class="modal-dialog" id="loginModal-stytle">
            <div class="modal-content modal_bg">
                <!--登陆框头部-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="loginModalLabel">欢迎登陆！</h4>
                </div>
                <!--登陆框中间部分(from表单)-->
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="loginForm"
                          action="#" method="post">
                        <!--用户框-->
                        <div class="form-group">
                            <label for="login_username" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="login_username"
                                       name="login_username">
                                <div id="login_username_message"></div>
                            </div>
                        </div>
                        <!--密码框-->
                        <div class="form-group">
                            <label for="login_password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="login_password"
                                       name="login_password">
                                <div id="login_password_message"></div>
                            </div>
                        </div>
                        <!--记住密码-->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="checkbox">
                                    <label> <input type="checkbox"> 记住密码
                                    </label>
                                </div>
                            </div>
                        </div>
                        <!--登陆按钮-->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" id="login_button" onclick="login_submit();"
                                        class="btn btn btn-info">登录
                                </button>
                            </div>
                            <div class="col-sm-offset-8 col-sm-10">
                                <p>
                                    还没有账号 <a class="go_regist cursor" onclick="close_loginMotai()">前去注册</a>
                                </p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--
注册模态框
-->
<div>
    <div class="modal fade" id="registModal" tabindex="-1" role="dialog"
         aria-labelledby="registModalLabel" aria-hidden="true"
         data-backdrop="true">
        <div class="modal-dialog" id="registModal-stytle">
            <div class="modal-content modal_bg">
                <!--注册框头部-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="registModalLabel">欢迎注册！</h4>
                </div>
                <!--注册框中间部分(from表单)-->
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="registForm"
                          action="/shopping_mall/UserServlet?method=add" method="post">
                        <!--用户框-->
                        <div class="form-group">
                            <label for="regist_username" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="regist_username"
                                       name="regist_username">
                                <div id="regist_username_message"></div>
                            </div>
                        </div>
                        <!--密码框-->
                        <div class="form-group">
                            <label for="regist_password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control"
                                       id="regist_password" name="regist_password">
                                <div id="regist_password_message"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control"
                                       id="confirmPassword" name="confirmPassword">
                                <div id="confirmPassword_message"></div>
                            </div>
                        </div>
                        <div class="form-group">


                            <div class="row">
                                <div class="col-sm-offset-1 col-sm-2">
                                    <label>性别</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="radio" value="0" name="gender">男
                                </div>
                                <div class="col-sm-3"></div>
                                <div class="col-sm-3">
                                    <input type="radio" value="1" name="gender">女
                                </div>
                            </div>

                            <div class="col-sm-offset-2" id="gender_message"></div>
                        </div>
                        <div class="form-group">
                            <label for="address" class="col-sm-2 control-label">地址</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="address"
                                       name="address">
                                <div id="address_message"></div>
                            </div>
                        </div>
                        <!--注册按钮-->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button id="regist_button" type="button" class="btn btn btn-info">注册</button>
                            </div>
                            <div class="col-sm-offset-8 col-sm-10">
                                <p>
                                    已有账号？ <a class="go_regist cursor"
                                             onclick="close_registMotai()">前去登录</a>
                                </p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>