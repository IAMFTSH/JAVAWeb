function close_loginMotai() {
    $('#loginModal').modal("hide");
    $('#registModal').modal("show");
};

function close_registMotai() {
    $('#registModal').modal("hide");
    $('#loginModal').modal("show");
};

$(function () {
    $('#registForm')
        .bootstrapValidator(
            {
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    regist_username: {
                        container: '#regist_username_message',
                        message: '用户名不符合系统要求',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 18,
                                message: '用户名长度必须在6到18位之间'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '用户名只能包含大写、小写、数字和下划线'
                            }
                        }
                    },
                    regist_password: {
                        container: '#regist_password_message',
                        message: '密码不满足系统要求',
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 18,
                                message: '用户新密码长度不能少于8位'
                            },
                            regexp: {
                                regexp: /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{8,16}$/,
                                message: '密码长度为8至16位,且包含数字、小写字母、大写字母、符号(至少三种)'
                            }
                        }
                    },
                    confirmPassword: {
                        container: '#confirmPassword_message',
                        message: '密码不满足系统要求',
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 18,
                                message: '用户新密码长度不能少于8位'
                            },
                            regexp: {
                                regexp: /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{8,16}$/,
                                message: '密码长度为8至16位,且包含数字、小写字母、大写字母、符号(至少三种)'
                            },
                            identical: {
                                field: 'regist_password',
                                message: '两次密码不相同'
                            }
                        }
                    },
                    gender: {
                        container: '#gender_message',
                        message: '性別不满足系统要求',
                        validators: {
                            notEmpty: {
                                message: '性別不能为空'
                            }
                        }
                    },
                    address: {
                        container: '#address_message',
                        message: '地址不符合系统要求',
                        validators: {
                            notEmpty: {
                                message: '地址不能为空'
                            },
                            stringLength: {
                                min: 10,
                                max: 25,
                                message: '地址长度必须在10到25位之间'
                            },
                        }
                    }
                }
            });
});
$(function () {
    $('#loginForm')
        .bootstrapValidator(
            {
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    login_username: {
                        container: '#login_username_message',
                        message: '用户名不符合系统要求',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 18,
                                message: '用户名长度必须在6到18位之间'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '用户名只能包含大写、小写、数字和下划线'
                            }
                        }
                    },
                    login_password: {
                        container: '#login_password_message',
                        message: '密码不满足系统要求',
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 18,
                                message: '用户新密码长度不能少于8位'
                            },
                            regexp: {
                                regexp: /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{8,16}$/,
                                message: '密码长度为8至16位,且包含数字、小写字母、大写字母、符号(至少三种)'
                            }
                        }
                    }
                }
            });
});

$(document).ready(
    function () {
        $("#login_button").click(
            function () {
                $('#loginForm').data('bootstrapValidator').validate();
                if (!$('#loginForm').data('bootstrapValidator')
                    .isValid()) {
                    return;
                } else {
                    // //document.getElementById("dataForm").submit();
                    var username = $("#login_username").val();// 取值
                    var password = $("#login_password").val();
                    var param = "username=" + username + "&password="
                        + password;
                    // var param = {
                    // "username" : username,
                    // "password" : password
                    // };
                    $.post("/shopping_mall/UserServlet?method=login",
                        param, function (result) {
                            if (result.result) {
                                alert("登陆成功！");
                                $('#loginModal').modal("hide");
                                $('#login').text(
                                    "  " + result.username);
                                $("#login")
                                    .attr("data-target", "#");
                            } else {
                                alert("用户名或密码错误！");
                            }
                        });
                }
            });
    });

$(document).ready(function () {
    $("#regist_button").click(function () {
        $('#registForm').data('bootstrapValidator').validate();
        if (!$('#registForm').data('bootstrapValidator').isValid()) {
            return;
        }
        document.getElementById("dataForm").submit();
    });
});

// $(function() { $("#dv") .after( "<div class='col-xs-12 col-sm-9 col-md-6
// col-lg-3'><div class='thumbnail word'>"+" <img class='img1'
// src='images/chongdianbao.jpg' alt='图片' ><div
// class='line-limit-length'>手游截图</div><spanclass='glyphicon
// glyphicon-yen'>单价：</span><span><a href='#' class='label label-success
// pull-right'>详情</a></span></div></div>"); });

$(function () {
    $.post("/shopping_mall/CommodityServlet?method=list",
        null,
        function (result) {
            for (var i = 0; i < result.length; i++) {
                $("#dv")
                    .after(
                        /*"<div class='col-xs-12 col-sm-9 col-md-6 col-lg-3'><div class='thumbnail word thumbnail-size'>"
                                + " <img src='"
                                + result[i].commodityImage
                                + "' alt='图片'   style='height:150px;width:240px;'><div class='line-limit-length'>"
                                + result[i].commodityIntroduce
                                + "</div><span class='glyphicon glyphicon-yen'>单价:"
                                + result[i].commodityPrice
                                + "</span><span><a  value='"+result[i].commodityID+"' class='label label-success pull-right' onclick='commodity(this);'>详情</a></span></div></div>"  */
                        "<div class='col-xs-12 col-sm-9 col-md-6 col-lg-3 paddbottom'>" +
                        "<div class='thumbnail word thumbnail-size'>"
                        + " <img src='" + result[i].commodityImage + "' alt='图片'   style='height:150px;width:240px;'></div>" +
                        "<div class='container container-width'>" +
                        "<p  class='line-limit-length'>" + result[i].commodityIntroduce + "</p>" +
                        "<span class='glyphicon glyphicon-yen'>单价: " + result[i].commodityPrice + "</span>" +
                        "<span><a  value='" + result[i].commodityID + "' class='label label-success pull-right' onclick='commodity(this);'>详情</a></span>" +
                        "</div>" +
                        "</div>"
                    );
            }
        });
});

function commodity(obj) {
    var ID = $(obj).attr("value");
    var param = {
        "commodityID": ID
    };
    $.post("/shopping_mall/CommodityServlet?method=queryByID",
        param,
        function (result) {
            var obj = {
                id: result.commodityID,
                name: result.commodityName,
                number: result.commodityNumber,
                image: result.commodityImage,
                intro: result.commodityIntroduce,
                price: result.commodityPrice,
                shopID: result.shopID
            };
            window.location.href = "details.html?" + parseParam(obj);
        });
    var parseParam = function (paramObj, key) {
        var paramStr = "";
        if (paramObj instanceof String || paramObj instanceof Number || paramObj instanceof Boolean) {
            paramStr += "&" + key + "=" + encodeURIComponent(paramObj);
        } else {
            $.each(paramObj, function (i) {
                var k = key == null ? i : key + (paramObj instanceof Array ? "[" + i + "]" : "." + i);
                paramStr += '&' + parseParam(this, k);
            });
        }
        return paramStr.substr(1);
    };
}



