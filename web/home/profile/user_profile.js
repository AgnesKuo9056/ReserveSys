var module="user";
var sub="center";
var time0 = 60;
var time = time0;
var t;  // 用于验证按钮的60s计时
/*================================================================================*/

jQuery(document).ready(function () {
    Page.init();

})
/* ================================================================================ */
//关于页面的控件生成等操作都放在Page里
var Page = function() {
    /*----------------------------------------入口函数  开始----------------------------------------*/

    var initPageControl = function () {
        pageId = $("#page_id").val();
        console.log("识别到page id了");
        if (pageId == "account_setting") {
            console.log("识别到page id =account_setting了");
            initaccount_setting();


        }
        if (pageId == "user_profile") {
            console.log("识别到page id =user_profile");
            initaccount_setting();


        }
        // if(pageId=="user_add"){
        //     initUserAdd();
        // }
        // // if(pageId=="user_modify"){
        // //     initUserModify();
        // // }
        // if(pageId=="print_table"){
        //     initPrintTable();
        // }
    };
    /*----------------------------------------入口函数  结束----------------------------------------*/

    /*----------------------------------------业务函数  开始----------------------------------------*/
    /*------------------------------针对各个页面的入口  开始------------------------------*/
    var initaccount_setting = function () {
        initUserInfo();
        // initChangPassword();
        initUserProfileControlEvent();

    }


    /*------------------------------针对各个页面的入口 结束------------------------------*/
    var getUrlParam = function (name) {
        //获取url中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        console.log(r);
        if (r != null) return decodeURI(r[2]);
        return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
    }
    var initUserProfileControlEvent = function () {


        $('#store_apply').click(function () {
            console.log("点击");
            $("#store_apply_model").show();
            $("#store_apply_model").modal("show");
             handleValidation1();
        });

        $('#apply_button').click(function () {

        });

        $('#profile_save_change').click(function () {
            OnModifySubmit("user_info");
        });
        $('#chg_paw_btn').click(function () {
            OnModifySubmit("password");
        });
        $('#chg_paw_btn_csl').click(function () {
            window.location.reload();
        });
        $('#user_profile_mailverify_div #btnGetVcode ').click(function () {
            GetVcode();
        });
        $('#user_profile_mailverify_div #btnVerify ').click(function () {
            VeriVcode();
        });

    }
    var initUserAddControlEvent = function () {
        $("#help_button").click(function () {
            help();
        });
        $('#user_add_div #add_button').click(function () {
            submitAddRecord();
        });
    }

/***************************************************************************************************************/

    // basic validation
    var handleValidation1 = function() {
        // for more info visit the official plugin documentation:
        // http://docs.jquery.com/Plugins/Validation

        var form1 = $('#fileupload');
        var error1 = $('.alert-danger', form1);
        var success1 = $('.alert-success', form1);

        form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            messages: {
                select_multi: {
                    maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                }
            },
            rules: {

                creditcard: {
                    required: true,
                    creditcard: true
                },
                "files[]":{
                    required: true
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                success1.hide();
                error1.show();
                App.scrollTo(error1, -200);
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
            },

            submitHandler: function (form) {
                success1.show();
                error1.hide();
            }
        });


    }

    // var getpassword = function () {
    //     console.log("js_getpassword");
    //     var data = {};
    //     data.action = "get_user_password";
    //
    //
    //     $.post("../../" + module + "_" + sub + "_servlet_action", data, function (json) {
    //         console.log(JSON.stringify(json));
    //         if (json.result_code == 0) {
    //             var list = json.aaData;
    //             console.log(list.length);
    //             if (list != undefined && list.length > 0) {
    //                 for (var i = 0, j = 0; i < list.length; i++) {
    //                     var userpassword = list[i];
    //                     console.log(userpassword.password);
    //                     $("#change_password #password").val(userpassword.password);
    //
    //                 }
    //             }
    //         }
    //
    //     })
    //
    // }


    var initUserRecordView = function () {
        var data = {};
        data.action = "get_user";
        var html = "";

        $.post("../../" + module + "_" + sub + "_servlet_action", data, function (json) {
            console.log(JSON.stringify(json));
            if (json.result_code == 0) {
                var list = json.aaData;
                console.log(list[0]);
                if (list != undefined && list.length > 0) {
                    for (var i = 0, j = 0; i < list.length; i++) {
                        var user = list[i];
                        $(" #profile_setting #user_id").val(user.user_id);
                        $(" #profile_setting #user_name").val(user.user_name);
                        if (user.mail != null && user.mail != "") {//已经验证过

                            html = html + "<div class=\"form-group\">";
                            html = html + "   <i class=\"fa fa-envelope font-purple\"></i>";
                            html = html + "<input type=\"text\" placeholder=\"" + user.mail + "\" readonly=\"readonly\" class=\"form-control\"  id=\"mail\" name=\"mail\"/>";
                            html = html + "<button class=\"btn green\"  onclick=\"javascript:Page.onverifyMail()\" id=\"verify_mail\" type=\"button\">更新邮箱</button>";
                            html = html + " </div>";

                        } else {//未验证过

                            html = html + "<div class=\"form-group\">";
                            html = html + "  <i class=\"fa fa-envelope\"></i>";
                            html = html + "<input type=\"text\" placeholder=\"请进快完成邮箱验证\" readonly=\"readonly\" class=\"form-control\"  id=\"mail\" name=\"mail\"/>";
                            html = html + "<button class=\"btn green\"  onclick=\"javascript:Page.onverifyMail()\" id=\"verify_mail\" type=\"button\">认证邮箱</button>";
                            html = html + " </div>";
                        }
                        $("#email_div").html(html);
                        console.log(html);
                        $("#profile_setting #phone_number").val(user.phone_number);
                        $("#profile_setting #role_name").val(user.role_name);

                    }
                }
            }

        })


    }

    var veryfy_password = function () {
        var password = $("#change_password #password").val();
        var new_password = $("#change_password #new_password").val();
        var renew_password = $("#change_password #new_re_password").val();
        console.log("receive:  pasw="+password+"new_psw="+new_password+"renew="+renew_password);
        var verify = {};
        verify.pass =true;
        var regexp = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$');
        //var pwdRegex = /^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]){8,16}$/;
        if (renew_password != new_password) {
            verify.message="两次输入不一致！";
            verify.pass =false;
        } else if (password == new_password) {
            verify.message="输入与原密码一致！";
            verify.pass =false;
        } else if (!regexp.test(new_password)) {
            console.log(regexp.test(new_password));
            verify.message="您的密码复杂度太低（密码中必须包含大小写字母、数字且长度在8-16字符内），请及时修改密码！！";
            verify.pass =false;

        }
        if(verify.pass){
            submitModifypassword(new_password);
        }else {
            $("#change_password #message").html(verify.message);
            $("#change_password #message").css("color", "red");
        }

    }

   var submitModifypassword =function (new_password) {
       if(confirm("您确定要修改密码吗？")){
           //var user_id=getUrlParam("user_id");
           var url="../../"+module+"_"+sub+"_servlet_action";
           var data={};
           data.action="modify_user_password";
           data.password=new_password;
           console.log(data);
           $.post(url,data,function(json){
               if(json.result_code==0){
                   alert("已完成修改");
                   window.location.reload();
               }
           });
       }



   }


    var submitModifyRecord=function(param){
        if(confirm("您确定要修改该记录吗？")){
            //var user_id=getUrlParam("user_id");
            var url="../../"+module+"_"+sub+"_servlet_action";
            var data={};
            data.action="modify_user_record";
            data.user_id=$(" #profile_setting #user_id").val();
            data.user_name=$(" #profile_setting #user_name").val();
            if(param!=null&&param!=""&&param.mail!=null&param.mail!="") {
                data.mail = param.mail;
            }
            data.phone_number=  $(" #profile_setting #phone_number").val();

            console.log(data);
            $.post(url,data,function(json){
                if(json.result_code==0){
                    alert("已完成修改");
                    window.location.reload();
                }
            });
        }
    }


    var backtoUserList=function () {
        window.location.href="store_apply.jsp";

    }



    var onverifyMail =function () {
        $("#user_profile_mailverify_div").modal("show");
    }
    var VeriVcode =function () {
        var message = document.getElementById("message");  // 显示提示信息
        console.log($("input[name='vcode']").val());
        $.ajax({
            url: '../../email_profile_servlet_action?method=verify',
            type: 'post',
            data: {vcode: $("input[name='vcode']").val()},
            dataType: 'text',
            success: function(msg1) {
                msg1 = jQuery.parseJSON(msg1);

                if(msg1 == 1){
                    var msg={};
                    msg.mail=$("#verify_email").val();
                    console.log(msg1);
                    $("#user_profile_mailverify_div #message").html("验证码正确！");
                   // message.innerHTML = "验证码正确！";
                    submitModifyRecord(msg);
                    $("#message").css("color","green");

                    $("#user_profile_mailverify_div").modal("hide");
                }
                else{
                    $("#user_profile_mailverify_div #message").html("验证码错误！");
                    //message.innerHTML = "验证码错误！";
                    $("#message").css("color","red");
                }
            },
            error:function(msg){
            }
        });
    }


    var GetVcode =function () {
        console.log("进入GetVcode");
        var btnGet = document.getElementById("btnGetVcode");
        btnGet.disabled = true;  // 为了防止多次点击
        $.ajax({
            url: '../../email_profile_servlet_action?method=getVCode',
            type: 'post',
            data: {email: $("input[name='verify_email']").val()},
            dataType: 'text',
            success: function (msg) {
                msg = jQuery.parseJSON(msg);
                console.log(msg);
                if (msg == -1) {
                    window.alert("请输入正确的邮箱！");
                    btnGet.disabled = false;
                } else {
                    console.log("useChangeBTN");
                    useChangeBTN();  // 控制下一次重新获取验证码
                }
            },
            error: function (msg) {
            }
        });
    }

    function  changeBTN (){
        if(time > 0){
            $("#btnGetVcode").text("("+time+"s)"+"重新获取");
            time = time - 1;
        }
        else{
            var btnGet = document.getElementById("btnGetVcode");
            btnGet.disabled = false;
            $("#btnGetVcode").text("获取验证码");
            vcode_timeout();
            clearInterval(t);
            time = time0;
        }
    }

    var vcode_timeout =function () {
        $.ajax({
            url: '../../email_profile_servlet_action?method=timeout',
            type: 'post',
            // data: {email: $("input[name='verify_email']").val()},
            dataType: 'text',
            success: function (msg) {
                msg = jQuery.parseJSON(msg);
                console.log(msg);
            },
            error: function (msg) {
            }
        });
    }
    var useChangeBTN =function (){
        $("#btnGetVcode").text("("+time+"s)"+"重新获取");
        time = time - 1;
        t = setInterval(changeBTN, 1000);  // 1s调用一次
    }


    var OnModifySubmit=function (modify) {

        if(modify=="user_info"){
            console.log("user_info");
            submitModifyRecord();

        }else if(modify=="password"){

            veryfy_password();
        }


    }
    var initUserInfo =function () {
        $("#user_profile_mailverify_div").modal("hide");
        initUserRecordView();
    }
    // var initChangPassword =function () {
    //     getpassword();
    // }

    var onStatistic_User=function(id){
        window.location.href="user_statistic.jsp";
    }
    //Page return 开始
    return {
        init: function() {
            initPageControl();
        },

        onverifyMail:function(){
        onverifyMail();
    }


    }
}();//Page
/*================================================================================*/
