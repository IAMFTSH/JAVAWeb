<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的店铺</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="css/myShop.css"/>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrapValidator.min.js"></script>
    <script src="js/myShop.js"></script>
</head>
<body>
<div class="wrap">
    <!-- 左边内容 -->
    <div id="left" class="left">
        <div id="logoDiv" class="logoDiv">
            <p id="logoTitle" class="logoTitle">
                <span id="shopName"></span>
                <span style="font-size:18px;">欢迎你！</span>
            </p>
        </div>
        <div class="menu-title">商品管理</div>
        <div class="menu-item" href="#one" data-toggle="tab">
            －上架商品
        </div>
        <div class="menu-item" href="#two" data-toggle="tab">
            －修改商品
        </div>
        <div class="menu-item" href="#three" data-toggle="tab">
            －下架商品
        </div>
    </div>
    <!-- 右边内容 -->
    <div id="right" class="tab-content right">
        <div id="one" class="tab-pane active paddtop">
            <div class="container" style="text-align:center;top: 10%;position: relative">
                <form class="form-horizontal paddtop" role="form" id="add" method="post">
                    <!--商品名称-->
                    <div class="form-group">
                        <label for="add_goodName" class="col-md-3 control-label">商品名称</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="add_goodName"
                                   name="add_goodName">
                            <div id="add_goodName_message"></div>
                        </div>
                    </div>
                    <!--商品图片-->
                    <div class="form-group">
                        <label for="add_image" class="col-md-3 control-label">商品图片</label>
                        <div class="col-md-5">
                            <div class="input-group">
                                <img id="image" src="images/a.png" style="width:200px;height:200px;">
                                <span class="input-group-btn">
                                    <button type="button" id="add_ImageButton" class="btn btn-primary">选择</button>
                                    <input type="file" id="add_image" name="select_Image_path" style="display: none;">
                                </span>
                            </div>
                            <div id="add_Image_message"></div>
                        </div>
                    </div>
                    <!-- 商品介绍 -->
                    <div class="form-group">
                        <label for="add_Introduce" class="col-md-3 control-label">商品介绍</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="add_Introduce"
                                   name="add_Introduce">
                            <div id="add_Introduce_message"></div>
                        </div>
                    </div>
                    <!-- 商品单价 -->
                    <div class="form-group">
                        <label for="add_price" class="col-md-3 control-label">商品单价</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="add_price"
                                   name="add_price">
                            <div id="add_price_message"></div>
                        </div>
                    </div>
                    <!-- 商品数量 -->
                    <div class="form-group">
                        <label for="add_number" class="col-md-3 control-label">商品单数量</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="add_number"
                                   name="add_number">
                            <div id="add_number_message"></div>
                        </div>
                    </div>
                    <!--提交按钮-->
                    <div class="form-group">
                        <div class=" col-md-11">
                            <button id="add_button" type="button" class="btn btn btn-info">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="two" class="tab-pane">
            <span style="margin-left: 40px; text-shadow: 2px 0px 6px rgba(94, 35, 255, 0.91);">内容二 </span>
        </div>
        <div id="three" class="tab-pane">
            <span style="margin-left: 40px; text-shadow: 2px 0px 6px rgba(94, 35, 255, 0.91);">内容三 </span>
        </div>
    </div>
</div>
</body>
</html>