function a(flag) {
    var nowVal = $("#inputVal").val();
    if (flag == '+') {
        $("#inputVal").val(parseInt(nowVal) + 1);
    } else if (flag == '-') {
        if (nowVal > 1) {
            $("#inputVal").val(nowVal - 1);
        }

    }
};

// 输入框中如果没有值则将输入框中的值重置为1
function b(nowVal) {
    if (nowVal.length == 0) {
        $("#inputVal").val(1);
    }
};

$(function () {
    var obj = GetRequest();
    $("#name").text(obj.name);
    $('#commodityImage').attr('src', obj.image);
    $("#intro").text(obj.intro);
    $("#price").text(" 单价：" + obj.price);
    $("#number").text(" 库存：" + obj.number);
});

function GetRequest() {

    var url = location.search; // 获取url中"?"符后的字串
    var theRequest = new Object();
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