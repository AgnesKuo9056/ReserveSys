<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/12/08
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>民宿旅游管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=maintain.device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <%@include file="../frame/frame_style.jsp"%>

    <link rel="shortcut icon" href="../../favicon.ico" />
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">

<!-- BEGIN HEADER -->
<%@include file="../frame/frame_header.jsp"%>
<!-- END HEADER -->
<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"> </div>
<!-- END HEADER & CONTENT DIVIDER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <%@include file="../frame/frame_left_siderbar.jsp"%>
    <!-- END SIDEBAR -->
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
            <!-- BEGIN PAGE HEADER-->
            <!-- BEGIN THEME PANEL -->
            <!-- END THEME PANEL -->
            <h3 class="page-title">用户管理
                <small>用户信息管理</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html"> Home </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">用户管理</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <span>用户信息管理</span>
                    </li>
                </ul>
                <div class="page-toolbar">
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true"> Actions
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right" role="menu">
                            <li>
                                <a href="#">
                                    <i class="icon-bell"></i> Action</a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="icon-shield"></i> Another action</a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="icon-user"></i> Something else here</a>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <a href="#">
                                    <i class="icon-bag"></i> Separated link</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="account_setting"/>
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN PROFILE SIDEBAR -->
                    <div class="profile-sidebar">
                        <!-- PORTLET MAIN -->
                        <div class="portlet light profile-sidebar-portlet ">
                            <!-- SIDEBAR USERPIC -->
                            <div class="profile-userpic">
                                <c:choose>

                                <c:when test="${!empty User.avatar}"><!--相当于elseif-->
                                <img src="../../assets/global/img/avatar/${User.avatar}" class="img-responsive" alt=""> </div>
                            </c:when>

                            <c:otherwise><!--相当于else-->
                            <img src="https://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" class="img-responsive"  alt="" /> </div>
                        </c:otherwise>
                        </c:choose>

                        <!-- END SIDEBAR USERPIC -->
                        <!-- SIDEBAR USER TITLE -->
                        <div class="profile-usertitle">
                            <div class="profile-usertitle-name"> ${User.user_name} </div>
                            <div class="profile-usertitle-job"> ${User.role_name} </div>
                        </div>
                        <!-- END SIDEBAR USER TITLE -->
                        <!-- SIDEBAR BUTTONS -->
                        <div class="profile-userbuttons">
                            <c:choose>
                                <c:when test="${User.user_role  eq 0}"><!--当为管理员-->

                                    <button type="button" id="contact_stor" class="btn btn-circle red btn-sm">联系商家</button>

                                </c:when>
                                <c:when test="${User.user_role  eq 1}"><!--当为商家本人-->

                                </c:when>

                                <c:when test="${Store.status  eq 0}"><!--当为用户本人,已提交申请审核状态-->
                                    <button type="button"  disabled  class="btn btn-circle grey btn-sm">商家审核申请中.....</button>
                                </c:when>
                                <c:when test="${Store.status  eq 2}"><!--当为用户本人,已提交申请审核状态-->
                                    <button type="button"  disabled  class="btn btn-circle grey btn-sm">审核失败,再次申请</button>
                                </c:when>
                                <c:otherwise><!--当为用户本人,普通用户-->
                                    <button type="button"  id="store_apply" class="btn btn-circle green btn-sm">申请成为商家</button>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <!-- END SIDEBAR BUTTONS -->
                        <!-- SIDEBAR MENU -->
                        <div class="profile-usermenu">
                            <ul class="nav">
                                <c:choose>
                                    <c:when test="${User.user_role  eq 0}"><!--当为管理员登入时 不能添加民宿-->

                                    </c:when>
                                    <c:otherwise><!--当为商家或管理员登入时 -->
                                        <li>
                                            <a href="../../order/guesthouse/guesthouse_order_list.jsp">
                                                <i class="icon-home"></i> 我的订单 </a>
                                        </li>
                                        <li>
                                            <a href="../../maintain/sue/sue_list.jsp">
                                                <i class="icon-home"></i> 我要投诉 </a>
                                        </li>

                                    </c:otherwise>
                                </c:choose>

                                <li >
                                    <a href="account_setting.jsp">
                                        <i class="icon-settings"></i> 账户信息 </a>
                                </li>
                                <c:choose>
                                    <c:when test="${User.user_role ne 2}"><!--当为不唯一般用户可以查看该用户的认证书-->
                                        <li class="active">
                                            <a href="store_authentic.jsp">
                                                <i class="icon-info"></i> 商家认证 </a>
                                        </li>

                                    </c:when>
                                    <c:otherwise><!--当为商家或管理员登入时 -->

                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                        <!-- END MENU -->
                    </div>
                    <!-- END BEGIN PROFILE SIDEBAR -->
                </div>
                   <!-- BEGIN PROFILE CONTENT -->
                    <div class="profile-content">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="portlet light ">
                                    <div class="portlet-title tabbable-line">
                                        <div class="caption caption-md">
                                            <i class="icon-globe theme-font hide"></i>
                                            <span class="caption-subject font-blue-madison bold uppercase">${User.user_name}/${User.anony_name} 商家认证信息</span>
                                        </div>
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a href="#licence" data-toggle="tab">营业证书</a>
                                            </li>
                                            <li>
                                                <a href="#prophoto" data-toggle="tab">真实人像</a>
                                            </li>
                                            <li>
                                                <a href="#pay_card" data-toggle="tab">支付账号</a>
                                            </li>
<%--                                            <li>--%>
<%--                                                <a href="#tab_1_4" data-toggle="tab">隐私设定</a>--%>
<%--                                            </li>--%>
                                        </ul>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="tab-content">
                                            <!-- PERSONAL INFO TAB -->
                                            <div class="tab-pane active" id="licence" name="licence">
                                                <form action="../../user_center_servlet_action?action=upload_licence" enctype="multipart/form-data" method="post"role="form">
                                                    <div class="form-group">
                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
                                                            <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">

                                                                <img src="../../assets/global/img/avatar/${Store.licence}" alt="" /> </div>

                                                        <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"> </div>
                                                        <div>
                                                                        <span class="btn default btn-file">
                                                                            <span class="fileinput-new"> 更新证照 </span>
                                                                            <span class="fileinput-exists"> 换一张 </span>
                                                                            <input type="file" name="avatar"> </span>
<%--                                                            <a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput"> 移除 </a>--%>//不允许商家移除证件照
                                                        </div>
                                                    </div>
                                                    </div>

                                                    <div class="margin-top-10">
                                                        <button class="btn green"> 确认更换 </button>
                                                        <%--                                                        <a href="javascript:;" class="btn default"> Cancel </a>--%>
                                                    </div>
                                                </form>
                                            </div>

                                            <!-- END PERSONAL INFO TAB -->
                                            <!-- CHANGE AVATAR TAB -->
                                            <div class="tab-pane" id="prophoto">
<%--                                                <form action="../../user_center_servlet_action?action=upload_prophoto" enctype="multipart/form-data" method="post"role="form">--%>
<%--                                                    <div class="form-group">--%>
<%--                                                        <div class="fileinput fileinput-new" data-provides="fileinput">--%>
<%--                                                            <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">--%>
<%--                                                                <c:choose>--%>

<%--                                                                <c:when test="${!empty User.avatar}"><!--相当于elseif-->--%>
<%--                                                                <img src="../../assets/global/img/store/${Store.avatar}" alt="" /> </div>--%>
<%--                                                            </c:when>--%>

<%--                                                            <c:otherwise><!--相当于else-->--%>
<%--                                                            <img src="https://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt="" /> </div>--%>
<%--                                                        </c:otherwise>--%>
<%--                                                        </c:choose>--%>
<%--                                                        <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"> </div>--%>
<%--                                                        <div>--%>
<%--                                                                        <span class="btn default btn-file">--%>
<%--                                                                            <span class="fileinput-new"> Select image </span>--%>
<%--                                                                            <span class="fileinput-exists"> Change </span>--%>
<%--                                                                            <input type="file" name="avatar"> </span>--%>
<%--                                                            <a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput"> Remove </a>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                    <div class="clearfix margin-top-10">--%>
<%--                                                        <span class="label label-danger">NOTE! </span>--%>
<%--                                                        <span>Attached image thumbnail is supported in Latest Firefox, Chrome, Opera, Safari and Internet Explorer 10 only </span>--%>
<%--                                                    </div>--%>
<%--                                                    <div class="margin-top-10">--%>
<%--                                                        <button class="btn green"> Submit </button>--%>
<%--                                                        &lt;%&ndash;                                                        <a href="javascript:;" class="btn default"> Cancel </a>&ndash;%&gt;--%>
<%--                                                    </div>--%>
<%--                                                </form>--%>
<%--                                                <c:forEach  items="${Store.imgs}" var="img">--%>
<%--                                                    <img src="../../assets/global/img/store/${Store.imgstring}" style="height: 4000px;width: 500px">--%>
<%--                                                </c:forEach>--%>
                                                                <img src="../../assets/global/img/store/${Store.imgstring}" style="height: 4000px;width: 500px">
                                            </div>
                                            <!-- END CHANGE AVATAR TAB -->
                                            <!-- CHANGE PASSWORD TAB -->
                                            <div class="tab-pane" id="pay_card">
                                                <form action="#">
                                                    <div class="form-group">
                                                        <label class="control-label">当前密码</label>
                                                        <input   id="password" readonly="readonly" class="form-control" /> </div>
                                                    <div class="form-group">
                                                        <label class="control-label">新密码</label>
                                                        <input type="new_password" id="new_password" class="form-control" /> </div>
                                                    <div class="form-group">
                                                        <label class="control-label">请再次输入新密码</label>
                                                        <input type="new_re_password" id="new_re_password" class="form-control" /> </div>
                                                    <div id="message">

                                                    </div>
                                                    <div class="margin-top-10">
                                                        <a  id="chg_paw_btn" class="btn green"> 确认更改密码 </a>
                                                        <a id="chg_paw_btn_csl" class="btn default"> 取消 </a>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                  <!-- END PROFILE CONTENT -->
                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->

    <!--页面头更改结束====================================================================================================================-->
</div>

<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->
<!-- BEGIN QUICK SIDEBAR -->
<%@include file="../frame/frame_javascript.jsp"%>
<%@include file="../frame/frame_quick_siderbar.jsp"%>
<!-- END QUICK SIDEBAR -->
</div>

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<!-- BEGIN script frame home/frame/frame_javascript-->
<%@include file="mail_verify.jsp"%>


<script src="user_profile.js"></script>

<!--END script frame home/frame/frame_javascript-->
</body>
</html>