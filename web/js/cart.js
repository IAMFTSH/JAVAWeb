$(document).ready(function () {
    $("#selectAll").change(function () {
        // $("input[name='checkbox']:checked");
        if ($(this).prop('checked')) {
            $('input:checkbox').attr("checked", true);
        } else {
            $('input:checkbox').attr("checked", false);
        }
    })
})
function addNum(e) {
    var children = e.parentNode.children;
    var num = e[1].value;
    // $.each(children,function (i,t) {
    //     // alert(t);
    //     if (i==1) {
    //         num=t.value;
    //     }
    // })
    var Num = parseInt(num) + 1;
    $.each(children, function (i, t) {
        if (i == 1)
            t.value = Num;
    })
}