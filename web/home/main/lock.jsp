
<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.5.2
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>民宿旅游平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />

    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="../../assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="../../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="../../assets/pages/css/lock.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <!-- END THEME LAYOUT STYLES -->
    <link rel="shortcut icon" href="../../favicon.ico" /> </head>
<!-- END HEAD -->

<body class="">
<div class="page-lock">
    <div class="page-logo">
        <a class="brand" href="../main/index.jsp">
<%--            <img src="../../assets/pages/img/logo-big.png" alt="logo" /> </a>--%>
        <img src="../../assets/layouts/layout2/img/logo.jpg" style="width: 200px;height: 100px;" alt="logo"/> </a>
    </div>
    <div class="page-body">
        <div class="lock-head"> Locked </div>
        <div class="lock-body">
            <div class="pull-left lock-avatar-block">
                <img src="../../assets/global/img/avatar/${User.avatar}" class="lock-avatar"> </div>

            <form class="lock-form pull-left"  method="post"   action="../main/index.jsp" onsubmit="return check()">
                <h4  id="user_id" name="user_id" >${User.user_name}</h4>
                <div class="form-group">
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password"  id="password" name="password" /> </div>
                <div class="form-actions">
                    <button type="submit" class="btn red uppercase">Back To System</button>
                </div>
            </form>
        </div>
        <div class="alert alert-warning alert-dismissible" id="te" role="alert"  >
            <button type="button" class="close" data-dismiss="alert" >
                <span >&times;</span></button>
            <strong id="message_lock" ></strong>
        </div>
        <div class="lock-bottom">
            <a href="../main/login.jsp">Not ${User.user_name} ? PLZ Click to change user</a>
        </div>
    </div>
    <div class="page-footer-custom"> 2023 &copy; maintain by pinyun. </div>
</div>
<script type="application/javascript">
    var timelimit =3;
    function check() {
        //判断密码是否一致
        var verify_psw = '${User.password}';
        var password = document.getElementById("password").value;

        if(verify_psw!=password){
            console.log("verify_psw ="+verify_psw+"   password="+password);

            document.getElementById("message_lock").innerText="密码错误,请重新输入";

            return false;
        }

        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "1000",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
        toastr[success]("欢迎回来 ${User.user_name} 有更多特色民宿及产品等着你探索噢! ", "解锁成功");

        setInterval(function () {
            return true;
            clearInterval(t);

        }, 2000);  // 5s调用一次
    }


</script>
<!--[if lt IE 9]>
<script src="../../assets/global/plugins/respond.min.js"></script>
<script src="../../assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="../../assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="../../assets/global/scripts/app.min.js" type="text/javascript"></script>

<script src="../../assets/pages/scripts/ui-toastr.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../assets/pages/scripts/lock.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<!-- END THEME LAYOUT SCRIPTS -->
</body>

</html>