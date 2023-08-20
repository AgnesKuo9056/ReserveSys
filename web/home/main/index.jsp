<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/11/23
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="user.entitiy.User" %>

<%@ page isELIgnored="false" %>

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
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=maintain.device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <%@include file="../frame/frame_style.jsp"%>

    <link rel="shortcut icon" href="../../favicon.ico" />
</head>

<%--    {--%>
<%--         <%--%>
<%--        System.out.println(u.getUser_role());--%>
<%--    }--%>
<%--%>--%>

    %>
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
            <h3 class="page-title"> Dashboard
                <small>dashboard & statistics</small>
            </h3>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
<%--             <%--%>

<%--             String u =request.getAttribute("user");--%>
<%--           // System.out.println(u.getUser_role());--%>
<%--            if(u!=null)--%>
<%--       %>--%>
            <input type="hidden" id="page_id" name="page_id" value="index"/>

            <input type="hidden" id="user_role" name="user_role" value="${User.user_role}" />
<%--            <div id="toast-container" class="toast-top-right" aria-live="polite" role="alert">--%>
<%--                <div class="toast toast-success" style="opacity: 0.608;">--%>
<%--                    <button class="toast-close-button" role="button">×</button>--%>
<%--                    <div class="toast-title">Toastr Notifications</div>--%>
<%--                    <div class="toast-message">Gnome &amp; Growl type non-blocking notifications</div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <!-- BEGIN DASHBOARD STATS 1-->
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat blue">
                        <div class="visual">
                            <i class="fa fa-comments"></i>
                        </div>
                        <div class="details">
                            <div class="number" >
                                <span data-counter="counterup" data-value="" id="gh_count"></span>间
                            </div>
                            <div class="desc"> 民宿统计</div>
                        </div>
                        <a class="more" href="../../maintain/guesthouse/guesthouse_list.jsp"> 查看更多
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat red" id="device_button">
                        <div class="visual">
                            <i class="fa fa-bar-chart-o"></i>
                        </div>
                        <div class="details" >
                            <div class="number">
                                <span data-counter="counterup" id="order_count" data-value=""></span>笔
                            </div>
                            <div class="desc"> 订单统计 </div>
                        </div>
                        <a class="more" href="../../order/guesthouse/guesthouse_order_list.jsp"> 查看更多
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat green">
                        <div class="visual">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <div class="details">
                            <div class="number">+
                                <span data-counter="counterup" id="housing_rate" data-value=""></span>%
                            </div>
                            <div class="desc"> 入住率 </div>
                        </div>
                    <a class="more">
                        <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat purple">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup"  id="good_rate" data-value=""></span>%
                            </div>
                            <div class="desc"> 好评率 </div>
                        </div>
                        <a class="more">
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <!-- END DASHBOARD STATS 1-->

            <div class="row">
                <div class="col-md-6 col-sm-6">
                    <div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-share font-blue"></i>
                                <span class="caption-subject font-blue bold uppercase">消息</span>
                            </div>
                            <%--                            <div class="actions">--%>
                            <%--                                <div class="btn-group">--%>
                            <%--                                    <a class="btn btn-sm blue btn-outline btn-circle" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> Filter By--%>
                            <%--                                        <i class="fa fa-angle-down"></i>--%>
                            <%--                                    </a>--%>
                            <%--                                    <div class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">--%>
                            <%--                                        <label>--%>
                            <%--                                            <input type="checkbox" /> Finance</label>--%>
                            <%--                                        <label>--%>
                            <%--                                            <input type="checkbox" checked="" /> Membership</label>--%>
                            <%--                                        <label>--%>
                            <%--                                            <input type="checkbox" /> Customer Support</label>--%>
                            <%--                                        <label>--%>
                            <%--                                            <input type="checkbox" checked="" /> HR</label>--%>
                            <%--                                        <label>--%>
                            <%--                                            <input type="checkbox" /> System</label>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                        </div>
                        <div class="portlet-body">
                            <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                                <ul class="feeds">
                                    <c:forEach  items="${Infos}" var="Info">
                                        <li>
                                            <div class="col1">
                                                <div class="cont">
                                                    <div class="cont-col1">
                                                        <c:choose>
                                                            <c:when test="${Info.type eq 0}">
                                                                <%--                                                            商家审核肖希--%>
                                                                <div class="label label-sm label-default">
                                                                    <i class="fa fa-user"></i>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${Info.type eq 1}">
                                                                <%--                                                            民宿信息--%>
                                                                <div class="label label-sm label-info">
                                                                    <i class="fa fa-home"></i>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${Info.type eq 2}">
                                                                <%--                                                            投诉信息--%>
                                                                <div class="label label-sm label-danger">
                                                                    <i class="fa fa-bell-o"></i>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <%--                                                           订单信息--%>
                                                                <div class="label label-sm label-warning">
                                                                    <i class="fa fa-briefcase"></i>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                    <div class="cont-col2">
                                                        <div class="desc"> ${Info.context}</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption caption-md">
                                <i class="icon-bar-chart font-green"></i>
                                <span class="caption-subject font-green bold uppercase">评论集<></span>
                                <span class="caption-helper">${fn:length(comments)} 评论</span>
                            </div>
                            <div class="inputs">
                                <div class="portlet-input input-inline input-small ">
                                    <div class="input-icon right">
                                        <i class="icon-magnifier" onclick="Page.search_comment()"></i>
                                        <input type="text" class="form-control form-control-solid input-circle" name="search_comment" placeholder="search..."> </div>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="scroller" style="height: 338px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
                                <div class="general-item-list">
                                    <c:forEach items="${comments}" var = "comment">
                                    <div class="item">
                                        <div class="item-head">
                                            <div class="item-details">
                                                <c:choose>

                                                    <c:when test="${!empty comment.img}"><!--相当于elseif-->
                                                          <img class="item-pic rounded" style="width: 35px;height: 35px;" src="../../assets/global/img/comment/${comment.img}">
                                                    </c:when>

                                                    <c:otherwise><!--相当于else-->
                                                         <img src="https://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" style="width: 35px;height: 35px;"  class="item-pic rounded"  /> </div>
                                                    </c:otherwise>
                                             </c:choose>

                                                <a href="" class="item-name primary-link">${comment.title} </a>
                                                <span class="item-label">${comment.cm_create_date}</span>
                                            <span class="item-status">

                                        <c:choose>
                                             <c:when test="${fn:substring(comment.star, 0, 1) == '4'|| fn:substring(comment.star, 0, 1) == '5'}">
                                                    <span class="badge badge-empty badge-success"></span> ${comment.star}</span>
                                            </c:when>
                                        <c:when test="${fn:substring(comment.star, 0, 1) == '3'}">

                                        <span class="badge badge-empty badge-warning"></span> ${comment.star}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-empty badge-danger"></span> ${comment.star}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                        <div class="item-body">${comment.comment_content} </div>
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 民宿统计 -->
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light portlet-fit ">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class=" icon-layers font-green"></i>
                                <span class="caption-subject font-green bold uppercase">民宿信息统计与分析表</span>
                            </div>
                            <div class="actions">
                                <div class="portlet-input input-inline">
                                    <div class="input-icon right">
                                        <i class="icon-magnifier"  id="echarts_search"></i>
                                        <input type="text" id="echarts_guesthouset_search" class="form-control input-circle" placeholder="输入地区或民宿关键字搜寻..."> </div>
                                </div>
                            </div>
                            <div class="actions" style="width: 50px;"></div>
                            <div class="actions">
                                统计粒度:
                                <a class="btn btn-circle btn-default" id="five_year_e">
                                    近五年
                                </a>
                                <select name="month" id="month_e" class="btn btn-circle btn-default">
                                    <option value="">月份</option>
                                    <option value="2023">2023</option>
                                    <option value="2022">2022</option>
                                    <option value="2021">2021</option>
                                    <option value="2020">2020</option>
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                </select>
                                <a class="btn btn-circle btn-default" id="season_e">
                                    季节
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="echarts_guesthouse" style="height:500px;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 订单统计-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN CHART PORTLET-->
                    <div class="portlet light bordered">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class=" icon-layers font-green"></i>
                                <span class="caption-subject font-green bold uppercase">订单统计与分析表</span>
                            </div>
                            <div class="actions">
                                <div class="portlet-input input-inline">
                                    <div class="input-icon right">
                                        <i class="icon-magnifier" id="amcharts_search"></i>
                                        <input type="text" id="amcharts_order_search" class="form-control input-circle" placeholder="输入地区或民宿关键字搜寻..."> </div>
                                </div>
                            </div>
                            <div class="actions" style="width: 50px;"></div>
                            <div class="actions">
                                统计粒度:
                                <a class="btn btn-circle btn-default" id="five_year">
                                    近五年
                                </a>
                                <select name="month" id="month" class="btn btn-circle btn-default">
                                    <option value="">月份</option>
                                    <option value="2023">2023</option>
                                    <option value="2022">2022</option>
                                    <option value="2021">2021</option>
                                    <option value="2020">2020</option>
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                </select>
                                <a class="btn btn-circle btn-default" id="season">
                                    季节
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="amcharts_order" class="chart" style="height: 500px;"> </div>
                        </div>
                    </div>
                    <!-- END CHART PORTLET-->
                </div>
            </div>
            </div>

            <!--页面头更改结束====================================================================================================================-->
        </div>

        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
    <!-- BEGIN QUICK SIDEBAR -->
<%--    <%@include file="../frame/frame_quick_siderbar.jsp"%>--%>
    <!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<!-- BEGIN script frame home/frame/frame_javascript-->
<%@include file="../frame/frame_javascript.jsp"%>
<%@include file="../frame/frame_echart.jsp"%>
<%@include file="../../home/frame/frame_amchart.jsp"%>
<script src="index.js"></script>
<!--END script frame home/frame/frame_javascript-->
</body>



</html>