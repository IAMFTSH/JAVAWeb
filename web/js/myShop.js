// $(function () {
//     var obj = GetRequest();
//     var userID = obj.userID;
//     var param = {
//         "userID": userID
//     };
//     $.post("ShopServlet?method=queryByID", param, function (
//         result) {
//         $("#shopName").text(result[0].shopName);
//     });
// });

function GetRequest() {
    var url = location.search;
    var theRequest = {};
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURIComponent((strs[i]
                .split("=")[1]));
        }
    }
    return theRequest;
}


$(document).ready(function () {
    $("#add_ImageButton").click(function () {
        return $("#add_image").click();
    });
    $("#add_image").change(function () {
        var file = $("#add_image").get(0).files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            // alert(reader.result);
            $("#image").attr("src", reader.result);
        }
    });
    $("#add_button").click(function () {
        var IMG_BASE = $("#image").attr("src"); //要上传的图片的base64编码
        // var IMG_ROUTE = $(".imgfile").val();//获取上传图片路径，为获取图片类型使用
        // var IMG_ENDFOUR = IMG_ROUTE.substr(IMG_ROUTE.length - 4, 4);//截取路径后四位，判断图片类型
        // var IMG_FOMATE = "jpeg"; //图片类型***
        // if (IMG_ENDFOUR.trim() == ".jpg")
        //     IMG_FOMATE = "jpg";
        // else if (IMG_ENDFOUR.trim() == ".png")
        //     IMG_FOMATE = "png";
        // else if (IMG_ENDFOUR.trim() == ".bmp")
        //     IMG_FOMATE = "bmp";
        //图片正式开始上传
        var json = {
            'imgBase': IMG_BASE
            // 'imgFormat': IMG_FOMATE
        };
        $.post("imgServlet", json, function (result) {
            alert("yes");
        });
    });
});




   

