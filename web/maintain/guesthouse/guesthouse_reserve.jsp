<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/12/08
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--[if !IE]><!-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  baidu map api  -->

<!DOCTYPE html >

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
            <input type="hidden" id="page_id" name="page_id" value="reserve_list"/>
            <!--   查找 search -->
            <div style="margin-left: -15px">
                <div class="iq-search-bar">
                    <form id="search-form" class="searchbox" action="/map">
                        <input id ="gh_name" type="text" name="gh_name" class="text search-input" placeholder="搜寻民宿...">
                        <input id="btn-search" type="image" value="Submit" src="../../assets/global/img/map-search.jpg" name="map-search-img"
                               style="width: 28px;height: 28px; position: absolute;float: right;margin-top: 3px"> <!--必须用jpg格式-->
                    </form>
                </div>

            </div>
            <!--地图  请勿在此div外添加div-->
            <div id="container" style="display:inline-block;height: 800px;"></div>
            <div id="myPageTop">
                <table>
                    <tr>
                        <td>
                            <label>请输入关键字：</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input id="tipinput"/>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="portfolio-content portfolio-1">
                <div id="js-filters-juicy-projects" class="cbp-l-filters-button">
                    <div data-filter="*" class="cbp-filter-item-active cbp-filter-item btn dark btn-outline uppercase"> All
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".1" class="cbp-filter-item btn dark btn-outline uppercase"> 单人
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".2" class="cbp-filter-item btn dark btn-outline uppercase"> 双人
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".graphic" class="cbp-filter-item btn dark btn-outline uppercase"> Gr
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".3" class="cbp-filter-item btn dark btn-outline uppercase"> 三人
                        <div class="cbp-filter-counter"></div>
                    </div>
                </div>

                <div id="js-grid-juicy-projects" class="cbp" id="ghlist">
                    <c:forEach  items="${GuestHouses}" var="GuestHouse">

                    <div  class="cbp-item  ${GuestHouse.tags}" onclick="javascript:Page.onClickItem(GuestHouse)">
                        <div class="cbp-caption">
                            <div class="cbp-caption-defaultWrap">
                                <img src="../../assets/global/img/${GuestHouse.gh_img}" alt=""> </div>
                            <div class="cbp-caption-activeWrap">
                                <div class="cbp-l-caption-alignCenter">
                                    <div class="cbp-l-caption-body">
                                        <a href="javascript:Page.onDetailRecord('${GuestHouse.gh_id}','${GuestHouse.owner_id}')" class=" btn red uppercase btn red uppercase" rel="nofollow">more info</a>
                                        <a href="../../assets/global/img/${GuestHouse.gh_img}" class="cbp-lightbox cbp-l-caption-buttonRight btn red uppercase btn red uppercase" data-title="">view larger</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="cbp-l-grid-projects-title uppercase text-center uppercase text-center">${GuestHouse.gh_name}</div>
                        <div class="cbp-l-grid-projects-desc uppercase text-center uppercase text-center">${GuestHouse.gh_address}</div>
                    </div>
                    </c:forEach>
                </div>

                <div id="js-loadMore-juicy-projects" class="cbp-l-loadMore-button" >
                    <a href="../../assets/global/plugins/cubeportfolio/ajax/loadMore.html" class="cbp-l-loadMore-link btn grey-mint btn-outline" rel="nofollow">
                        <span class="cbp-l-loadMore-defaultText">LOAD MORE</span>
                        <span class="cbp-l-loadMore-loadingText">LOADING...</span>
                        <span class="cbp-l-loadMore-noMoreLoading">NO MORE WORKS</span>
                    </a>
                </div>
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
<%--<script>var map = new BMapGL.Map("container");--%>

<%--// 创建点坐标--%>
<%--var centerpoint = new BMapGL.Point(116.404, 39.915);--%>

<%--// 初始化地图，设置中心点坐标和地图级别--%>
<%--map.centerAndZoom(centerpoint, 15);--%>

<%--//开启滚轮缩放地图--%>
<%--map.enableScrollWheelZoom(true);--%>

<%--//版权控件remove有问题--%>
<%--var scaleCtrl = new BMapGL.ScaleControl();  // 添加比例尺控件--%>
<%--map.addControl(scaleCtrl);--%>
<%--var zoomCtrl = new BMapGL.ZoomControl();  // 添加缩放控件--%>
<%--map.addControl(zoomCtrl);--%>
<%--</script>--%>
<!-- BEGIN script frame home/frame/frame_javascript-->

<script src="guesthouse.js"></script>


<!--END script frame home/frame/frame_javascript-->
</body>

</html>
