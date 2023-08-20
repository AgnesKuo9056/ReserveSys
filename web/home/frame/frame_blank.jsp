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
        <div class="page-content">
            <!-- BEGIN PAGE HEADER-->
            <!-- BEGIN THEME PANEL -->
            <%@include file="../../home/frame/frame_theme_panel.jsp"%>
            <!-- END THEME PANEL -->
            <h3 class="page-title">设备信息
                <small>管理设备信息</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html">Home</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">Tables</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <span>Static Tables</span>
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
            <input type="hidden" id="page_id" name="page_id" value="xxx_list"/>
            <div class="row">
                <div class="col-md-6">
                    <button type="button" id="button_datatable" name="button_datatable" class="btn btn-primary">切换到DataTable</button>
                    <button type="button" id="button_definetable" name="button_definetable" class="btn btn-success">切换到自定义Table</button>
                    <button type="button" id="button_bar" name="button_bar" class="btn btn-info">切换到自定义大横条</button>
                </div>
            </div>
            <div class="row" id="datatable_tab">
                <div class="col-md-6">
                    <table class="table table-striped table-bordered table-hover datatable" id="record_list">
                        <thead>
                        <tr>
                            <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#record_list .checkboxes" /></th>
                            <th>设备ID</th>
                            <th>设备名称</th>
                            <th>创建人</th>
                            <th>创建时间</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="row display-none" id="bar_tab">
                <div class="col-md-6">
                    <div id="record_bar_div"  name="record_bar_div">
                        <div class="media" style="padding: 20px">
                            <div class="media-left">
                                <a href="#">
                                    <img class="media-object" alt="" src="../../assets/pages/img/avatars/team1.jpg" style="width: 50px; height: 50px; border-radius: 50px;border-top-left-radius: 50px;border-top-right-radius: 50px; border-bottom-right-radius: 50px;border-bottom-left-radius: 50px;!important;"> </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    <a >Sean</a> on
                                    <span class="c-date">23 May 2015, 10:40AM</span>
                                </h4>
                                <p>
                                    设备
                                </p>
                            </div>
                        </div>
                        <hr style="background-color:blue; height:1px; border:none; margin: 0px" >
                    </div>
                </div>
            </div>
            <div class="row display-none" id="table_tab">
                <div class="col-md-6">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-comments"></i>Equipment Table </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                <a href="javascript:;" class="reload"> </a>
                                <a href="javascript:;" class="remove"> </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-scrollable">

                                <table class="table table-bordered table-hover" id="record_table_content_div">
                                    <thead>
                                    <tr>
                                        <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#record_table_content_div .checkboxes " /></th>
                                        <th>设备ID</th>
                                        <th>设备名称</th>
                                        <th>创建人</th>
                                        <th>创建时间</th>
                                        <th>modify</th>
                                        <th>delete</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->
                </div>


            </div>

            <button id="add_button" name="add_button" class="btn btn-primary">添加设备</button>
            <input type="button" name="query_btn" id="query_btn" class="btn btn-primary" value="以设备编号查询记录">
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
<script src="device.js"></script>
<!--END script frame home/frame/frame_javascript-->
</body>

</html>