<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/12/08
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="guesthouse.entitiy.Guesthouse" %>
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
    <%@include file="../../home/frame/frame_style.jsp"%>
    <style>
        table.dataTable tbody tr th td.none {
            display: none;

        }
        table.dataTable td:before {
            content: none;
            display: none;
        }
        .dataTables_wrapper .dt-buttons {
            float: none;
            text-align: right;
            margin-bottom: 10px;
            /* 修改padding-right的值以调整PDF导出按钮与其他元素的间距 */
            padding-right: 50px;
        }
    </style>
    <link rel="shortcut icon" href="../../favicon.ico" />
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

            <!-- END THEME PANEL -->
            <h3 class="page-title">订单管理
                <small>民宿订单管理</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html"> 管理 </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">订单管理</a>
                        <i class="fa fa-angle-right"></i>
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
            <input type="hidden" id="page_id" name="page_id" value="gh_order_list"/>
<%--        error toast--%>
<%--            <div id="toast-container" class="toast-top-right" aria-live="polite" role="alert">--%>
<%--                <div class="toast toast-error" style="">--%>
<%--                    <button class="toast-close-button" role="button">×</button>--%>
<%--                    <div class="toast-title">错误!!</div>--%>
<%--                    <div class="toast-message">请正确选择日期,若要查询某天订单,请选择相同日期</div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <!--   查找 search div-->
            <div class="input-inline" id="date_div">
                <div class="input-group input-large date-picker input-daterange" data-date="2023-4-16" data-date-format="yyyy-mm-dd">
                    <input type="text" class="form-control start_date" name="start_date">
                    <span class="input-group-addon"> to </span>
                    <input type="text" class="form-control end_date" name="end_date"> </div>
                <!-- /input-group -->
                <span class="help-block"> </span>
                <i class="icon-magnifier" onclick="javascript:Page.submitqueryRecord()"></i>
            </div>
            <p class="search-desc" id="gh_order_search_result" style="display: none; color:#add2c2; " ></p>
            <p  id="map_search_result_2" style="display: none">
                <a  onclick="Page.onreset()"> 点击重置 </a>
            </p>
            <!-- BEGIN EXAMPLE TABLE PORTLET 可排序,修改 列表 模版-->
            <div class="portlet light ">
                <div class="portlet-title">
                    <div class="caption font-dark">
                        <i class="icon-settings font-dark"></i>
                        <span class="caption-subject bold uppercase"> 订单信息管理</span>
                    </div>
                    <div class="actions">
                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active"   id="reserved">
                                <input type="radio" name="options" class="toggle">已预订</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"  id="fulfill">
                                <input type="radio" name="options" class="toggle" >已完成</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"    id="cancel">
                                <input type="radio" name="options" class="toggle">已取消</label>
                            <%--                            状态：订单的当前状态，例如成功预定0、已完成(已经离店)1、已取消2 --%>
                        </div>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
<%--暂时不提供手动添加订单--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button id="add_gh" class="btn sbold green"> 添加民宿--%>
<%--                                        <i class="fa fa-plus"></i>--%>
<%--                                    </button>--%>
<%--                                </div>--%>
                            </div>

                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dt-responsive dataTable" width="100%" id="gh_order_manage">

                        <thead>
<%--                        1个预留给+ 9个为正常显示(包含一列为 修改 删除 操作) 3个为扩展信息--%>
                        <tr>

                            <th></th>
                            <th id="order_id">订单编号</th>
<%--                            订单号：用于唯一标识订单的号码。--%>
                            <th class="min-tablet">入住日期</th>
<%--                            入住日期：客人预订的入住日期。--%>
                            <th class="min-phone-l">离店日期</th>
<%--                            离店日期：客人预订的离店日期。--%>
                            <th class="desktop">客人姓名</th>
<%--                            客人姓名：客人的姓名。--%>
                            <th class="min-phone-l">客人联系方式</th>
<%--                            联系方式：客人的联系方式，例如电话号码或电子邮件地址。--%>
                            <th class="min-phone-l">房型</th>
<%--                             房型：客人预订的房间类型。--%>
                            <th class="min-phone-l">房东</th>
<%--                             房东：该预定房间所对应的所有者。--%>
                            <th class="desktop">总价</th>
<%--                            总价：订单的总价--%>
                            <th class="desktop">操作</th>
<%--                            操作：针对订单进行的操作，例如修改、取消订单等。--%>
                            <th class="none">预订日期</th>
<%--                            预订日期：客人下单的日期。--%>
                            <th class="none">其它备注</th>
<%--                            备注: 可以为店家添加 或是预订者添加--%>
                            <th class="none">评价与反馈</th>
 <%--                           评价与反馈：客人入住的反馈与建议的真实评价。--%>

                        </tr>

                        </thead>

                    </table>
                </div>
            </div>
            <!-- END EXAMPLE TABLE PORTLET-->

        </div>
    </div>
            <!--页面头更改结束====================================================================================================================-->
        </div>

        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
    <!-- BEGIN QUICK SIDEBAR -->
    <%@include file="../../home/frame/frame_javascript.jsp"%>
    <%@include file="../../home/frame/frame_quick_siderbar.jsp"%>
    <!-- END QUICK SIDEBAR -->
</div>

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../../home/frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<!-- BEGIN script frame home/frame/frame_javascript-->
<%@include file="../../order/guesthouse/ghuesthouse_comment_add.jsp"%>

<%@include file="../../order/guesthouse/guesthouseorder_edit.jsp"%>

<script src="GHOrder.js"></script>
<!--END script frame home/frame/frame_javascript-->
</body>
</html>
