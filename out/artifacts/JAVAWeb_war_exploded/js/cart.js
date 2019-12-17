$(document).ready(function (){
    $("#selectAll").change(function () {
        // $("input[name='checkbox']:checked");
        if($(this).prop('checked')){
            $('input:checkbox').attr("checked", true);
        }else {
            $('input:checkbox').attr("checked", false);
        }
    })
})