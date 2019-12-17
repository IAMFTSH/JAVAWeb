$(function () {
    $('#myform').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                container: '#username_message',
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
            password: {
                container: '#password_message',
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

function submit() {

    /*手动验证表单，当是普通按钮时。*/
    $('#myform').data('bootstrapValidator').validate();
    if (!$('#myform').data('bootstrapValidator').isValid()) {
        return;
    }
    document.getElementById("dataForm").submit();
}