
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>\ !民宿旅游平台! /</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="../../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="../../assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
    <link href="../../assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="../../assets/pages/css/login.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="../../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="../../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->

    <!-- BEGIN THEME LAYOUT STYLES -->
    <!-- END THEME LAYOUT STYLES -->
    <link rel="shortcut icon" href="../../favicon.ico" /> </head>
<!-- END HEAD -->

<body class=" login">
    <div class="menu-toggler sidebar-toggler"></div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
    <div class="logo">
    <h3 class="form-title font-white">民宿旅游平台</h3>
</div>
    <input type="hidden" id="page_id" name="page_id" value="login"/>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
    <div class="content">
    <!-- BEGIN LOGIN FORM -->
    <div class="main-form" >
        <h3 class="form-title font-green">登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span> Enter any username and password. </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">UserId</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="UserId" id="user_id" name="user_id" /> </div>

        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Password</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" id="password" name="password" /> </div>
        <!-- 出错显示的信息框 -->

        <div class="alert alert-warning alert-dismissible" role="alert"  style="display: none" id="mes">
            <button type="button" class="close" data-dismiss="alert" >
                <span >&times;</span></button>
            <strong id="message_login" ></strong>
        </div>



<div class="form-actions">
    <button id="login_btn"  class="btn green uppercase">Login</button>

    <label class="rememberme check">
        <input type="checkbox" name="remember" value="1" />Remember </label>
    <a href="javascript:;" id="forget-password" class="forget-password">忘记密码</a>
</div>

<div class="create-account">
    <p>
        <a href="javascript:;" id="register-btn" class="uppercase">注册账户</a>
    </p>
</div>
</div>
<!-- END LOGIN FORM -->
<!-- BEGIN FORGOT PASSWORD FORM -->
<form class="forget-form" action="../../user_center_servlet_action?action=forget" method="post">
    <h3 class="font-green">忘记密码</h3>
    <p> 已将密码送到您认证的邮箱 ,请注意查收 !</p>
    <div class="form-group">
        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" /> </div>
    <div class="form-actions">
        <button type="button" id="back-btn" class="btn btn-default">返回</button>
    </div>
</form>
<!-- END FORGOT PASSWORD FORM -->
<!-- BEGIN REGISTRATION FORM -->
<div class="register-form"  >
    <h3 class="font-green">注册账号 </h3>
    <p class="hint"> 请填写以下栏目 : </p>
    <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">名称/昵称</label>
        <input class="form-control placeholder-no-fix" type="text" placeholder="昵称id" id="anony_name" name="fullname" />
    </div>
    <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">真实姓名<span class="required" aria-required="true"> * </span></label>
        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" id="user_name" placeholder="真实姓名" name="username" />
    </div>
    <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9"> 电话<span class="required" aria-required="true"> * </span></label>
        <div>
                <i class="fa"></i>
                <input type="text" class="form-control placeholder-no-fix" id="phone_number" placeholder="电话" name="number">
        </div>
        <span class="help-block small"  id="phone_error" style="color: red ;display: none">请输入有效电话号码 </span>

    </div>
    <div class="form-group  last password-strength ">
        <label class="control-label visible-ie8 visible-ie9">密码<span class="required" aria-required="true"> * </span></label>
        <div>
            <input class="form-control placeholder-no-fix" type="text" autocomplete="off" id="password_strength" placeholder="密码" name="password" />
            <span class="help-block small " style="display: block" id="psw_error" >密码中必须包含大小写字母、数字且长度在8-16字符内 </span>
        </div>
    </div>

    <div class="form-group margin-top-20 margin-bottom-20">
        <label class="check">
            <input type="checkbox" name="tnc" />我已经阅读条款并同意遵守在使用该服务时遵守所有适用的法律法规。
        </label>
        <div id="register_tnc_error"> </div>
    </div>
    <div class="form-actions">
        <button type="button" id="register-back-btn" class="btn btn-default">返回</button>
<%--        <button  id="register-submit-btn"  class="btn btn-success uppercase pull-right">Submit</button>--%>
        <button id="register-submit-btn" name="register-submit-btn" class="btn btn-info  uppercase pull-right" >提交注册</button>
    </div>
</div>
<!-- END REGISTRATION FORM -->
</div>

<div class="copyright"> 2022 -2023 ©  维护  郭品妘 </div>


<%@include file="../frame/frame_javascript.jsp"%>
<%@include file="../frame/frame_footer.jsp"%>
    <script src="index.js"></script>

</body>

</html>