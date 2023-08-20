<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/11/23
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=maintain.device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
   <%@include file="../../home/frame/frame_style.jsp"%>
    
    <link rel="shortcut icon" href="favicon.ico" />
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">

<!-- BEGIN HEADER -->
<%@include file="../../home/frame/frame_header.jsp"%>
<!-- END HEADER -->
<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"> </div>
<!-- END HEADER & CONTENT DIVIDER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <%@include file="../../home/frame/frame_left_siderbar.jsp"%>
    <!-- END SIDEBAR -->
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content" >
            <!-- BEGIN PAGE HEADER-->
            <!-- BEGIN THEME PANEL -->
            <%@include file="../../home/frame/frame_theme_panel.jsp"%>
            <!-- END THEME PANEL -->
            <h3 class="page-title">设备信息
                <small>管理设备信息</small>
            </h3>
<%--            <div class="page-bar">--%>
<%--                <ul class="page-breadcrumb">--%>
<%--                    <li>--%>
<%--                        <i class="icon-home"></i>--%>
<%--                        <a href="index.html">Home</a>--%>
<%--                        <i class="fa fa-angle-right"></i>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">Tables</a>--%>
<%--                        <i class="fa fa-angle-right"></i>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <span>Static Tables</span>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--                <div class="page-toolbar">--%>
<%--                    <div class="btn-group pull-right">--%>
<%--                        <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true"> Actions--%>
<%--                            <i class="fa fa-angle-down"></i>--%>
<%--                        </button>--%>
<%--                        <ul class="dropdown-menu pull-right" role="menu">--%>
<%--                            <li>--%>
<%--                                <a href="#">--%>
<%--                                    <i class="icon-bell"></i> Action</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">--%>
<%--                                    <i class="icon-shield"></i> Another action</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">--%>
<%--                                    <i class="icon-user"></i> Something else here</a>--%>
<%--                            </li>--%>
<%--                            <li class="divider"> </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">--%>
<%--                                    <i class="icon-bag"></i> Separated link</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="print_table"/>
            <div class="portlet">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-bell-o"></i>打印设备 </div>
                    <div class="tools">
                        <a href="javascript:;" class="collapse"> </a>
                        <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                        <a href="javascript:;" class="reload"> </a>
                        <a href="javascript:;" class="remove"> </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-scrollable">
                        <table class="table table-striped table-bordered table-advance table-hover"   id="record_print_table" >
                            <thead>
                            <tr>
                                <th>
                                    <i class="fa fa-briefcase"></i> 设备ID </th>
                                <th class="hidden-xs">
                                    <i class="fa fa-user"></i> 设备名称 </th>
                                <th>
                                    <i class="fa fa-shopping-cart"></i> 创建者 </th>
                                <th> </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="highlight">
                                    <div class="success"></div>
                                    <a href="javascript:;"> RedBull </a>
                                </td>
                                <td class="hidden-xs"> Mike Nilson </td>
                                <td> 2560.60$ </td>
                                <td>
                                    <a href="javascript:;" class="btn btn-outline btn-circle btn-sm purple">
                                        <i class="fa fa-edit"></i> Edit </a>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--页面头更改结束====================================================================================================================-->
        </div>

        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
    <!-- BEGIN QUICK SIDEBAR -->
    <%@include file="../../home/frame/frame_quick_siderbar.jsp"%>
    <!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../../home/frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<!-- BEGIN script frame home/frame/frame_javascript-->
<%@include file="../../home/frame/frame_javascript.jsp"%>

<script src="user.js"></script>
<!--END script frame home/frame/frame_javascript-->
</body>

</html>