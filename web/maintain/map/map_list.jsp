<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/12/08
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="guesthouse.entitiy.Guesthouse" %>
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
    <%@include file="../../home/frame/frame_map_style.jsp"%>
    <%@include file="../../home/frame/frame_style.jsp"%>

    <style>
        #container {
            float: left;
            margin-top:45px;
            margin-left: 50px;
            position:relative;
            clear: both;
        }
        .portfolio-1{
            clear: both;
        }
        /*.aa{*/
        /*    clear: both;*/

        /*}*/
        .input-card {
            background: none;
            width: 130px;
            top: 10px;
            left: 0px;
            bottom: auto;
            position:absolute;/*相对定位*/
            z-index:9999;
        }

        .myPageTop{
            top: 10px;
            right: 10px;
            position:absolute;/*相对定位*/
            z-index:9999;
        }
        .info-title {
            color: white;
            font-size: 14px;
            background-color: #25A5F7;
            line-height: 26px;
            padding: 0px 0 0 6px;
            font-weight: lighter;
            letter-spacing: 1px
        }

        .info-content {
            font: 12px Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial;
            padding: 4px;
            color: #666666;
            line-height: 23px;
        }

        .info-content img {
            float: left;
            margin: 3px;
        }

        .amap-info-combo .keyword-input{
            height: 25px;
            border-radius: 2px 0 0 2px;
        }
    </style>
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

            <!-- END THEME PANEL -->
<%--            <h3 class="page-title">用户管理--%>
<%--                <small>用户信息管理</small>--%>
<%--            </h3>--%>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="../../home/main/index.jsp"> Home </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="../../maintain/map/map_list.jsp">民宿预定</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                </ul>
                <div class="page-toolbar">
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-fit-height grey-salt "  onclick="javascript:Page.onqueryRecord()" > 更多筛选
                            <i class="fa fa-server"></i>
                        </button>
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
                    </div>
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="reserve_list"/>

            <!--   查找 search div-->
            <p class="search-desc" id="map_search_result" style="display: none; color:#add2c2; " ></p>
            <p  id="map_search_result_2" style="display: none">共有
                <span class="search_map">${fn:length(GuestHouses)}</span> 间 民宿符合您的筛选条件
                <a  onclick="Page.onreset()"> 点击重置筛选</a>
            </p>
            <%@include file="../map/map_search.jsp"%>

            <!--地图  请勿在此div外添加div-->
            <div id="container" style="display:block; width:1000px ;height: 350px;">
                <div class='input-card'  style="display:inline-block;">
                    <div class="input-item" >
                        <input type="checkbox" onclick="javascript:Page.toggleScale(this)"/>比例尺
                    </div>

                </div>
                <div class="myPageTop" style="display:inline-block;">
                    <table>
                        <tr>
                            <td>
                                <label>请输入关键字：</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input id="shuru" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="portfolio-content portfolio-1" style="display:block">
                <div id="js-filters-juicy-projects" class="cbp-l-filters-button">
                    <div data-filter="*" class="cbp-filter-item-active cbp-filter-item btn dark btn-outline uppercase" > All
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".1" class="cbp-filter-item btn dark btn-outline uppercase"> 单人
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".2" class="cbp-filter-item btn dark btn-outline uppercase"> 双人
                        <div class="cbp-filter-counter"></div>
                    </div>
                    <div data-filter=".3" class="cbp-filter-item btn dark btn-outline uppercase"> 三人
                        <div class="cbp-filter-counter"></div>
                    </div>
                </div>

                <div id="aa">
                <div id="js-grid-juicy-projects" >
                    <c:forEach  items="${GuestHouses}" var="GuestHouse">

                        <div  class="cbp-item ${GuestHouse.tags}" onclick="javascript:Page.onClickItem('${GuestHouse.gh_x}','${GuestHouse.gh_y}')">
                            <div class="cbp-caption">
                                <div class="cbp-caption-defaultWrap">
                                    <img src="../../assets/global/img/gh_pic/${GuestHouse.gh_img}"  style="width: 270px;height: 270px" alt=""> </div>
                                <div class="cbp-caption-activeWrap" style="width: 270px" >
                                    <div class="cbp-l-caption-alignCenter">
                                        <div class="cbp-l-caption-body">
                                            <a href="javascript:Page.onDetailRecord('${GuestHouse.gh_id}','${GuestHouse.owner.user_id}')" class=" btn red uppercase btn red uppercase" rel="nofollow">more info</a>
                                            <a href="../../assets/global/img/gh_pic/${GuestHouse.gh_img}" class="cbp-lightbox cbp-l-caption-buttonRight btn red uppercase btn red uppercase" data-title="">view larger</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="cbp-l-grid-projects-title uppercase text-center uppercase text-center">${GuestHouse.gh_name}</div>
                            <div class="cbp-l-grid-projects-desc uppercase text-center uppercase text-center">${GuestHouse.gh_address}</div>
                        </div>
                    </c:forEach>
                </div>
                </div>
            <div id="js-loadMore-juicy-projects" class="cbp-l-loadMore-button" >
                <a href="../../assets/global/plugins/cubeportfolio/ajax/loadMore.html" class="cbp-l-loadMore-link btn grey-mint btn-outline" rel="nofollow">
                    <span class="cbp-l-loadMore-defaultText">LOAD MORE</span>
                    <span class="cbp-l-loadMore-loadingText">LOADING...</span>
                    <span class="cbp-l-loadMore-noMoreLoading">NO MORE WORKS</span>
                </a>
            </div>




<%--                <div id="js-grid-juicy-projects " class="cbp col-sm-8 col-lg-6"  style="display:block">--%>

<%--&lt;%&ndash;                    <c:forEach  items="${GuestHouses}" var="GuestHouse">&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <div  class="cbp-item  ${GuestHouse.tags}" onclick="javascript:Page.onClickItem('${GuestHouses.gh_x}','${GuestHouses.gh_y}')" >&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="cbp-caption">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="cbp-caption-defaultWrap">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <img src="../../assets/global/img/${GuestHouse.gh_img}" alt=""> </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="cbp-caption-activeWrap">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <div class="cbp-l-caption-alignCenter">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <div class="cbp-l-caption-body">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <a href="javascript:Page.onDetailRecord('${GuestHouse.gh_id}','${GuestHouse.owner_id}')" class=" btn red uppercase btn red uppercase" rel="nofollow">more info</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <a href="../../assets/global/img/${GuestHouse.gh_img}" class="cbp-lightbox cbp-l-caption-buttonRight btn red uppercase btn red uppercase" data-title="">view larger</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="cbp-l-grid-projects-title uppercase text-center uppercase text-center">${GuestHouse.gh_name}</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="cbp-l-grid-projects-desc uppercase text-center uppercase text-center">${GuestHouse.gh_address}</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </c:forEach>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    <div class="cbp-item graphic web-design">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <div class="cbp-caption">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            <div class="cbp-caption-defaultWrap">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                <img src="../../assets/global/img/portfolio/600x600/96.jpg" alt=""> </div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            <div class="cbp-caption-activeWrap">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                <div class="cbp-l-caption-alignCenter">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                    <div class="cbp-l-caption-body">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                        <a href="../../assets/global/plugins/cubeportfolio/ajax/project2.html" class="cbp-singlePage cbp-l-caption-buttonLeft btn red uppercase" rel="nofollow">more info</a>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                        <a href="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/26519543&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true"&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                           class="cbp-lightbox cbp-l-caption-buttonRight btn red uppercase" data-title="Speed Detector<br>by Cosmin Capitanu">view sound</a>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        </div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <div class="cbp-l-grid-projects-title uppercase text-center">Speed Detector</div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <div class="cbp-l-grid-projects-desc uppercase text-center">Graphic / Web Design</div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    </div>&ndash;%&gt;--%>

<%--                </div>--%>

<%--                <div id="js-loadMore-juicy-projects" class="cbp-l-loadMore-button" >--%>
<%--                    <a href="../../assets/global/plugins/cubeportfolio/ajax/loadMore.html" class="cbp-l-loadMore-link btn grey-mint btn-outline" rel="nofollow">--%>
<%--                        <span class="cbp-l-loadMore-defaultText">LOAD MORE</span>--%>
<%--                        <span class="cbp-l-loadMore-loadingText">LOADING...</span>--%>
<%--                        <span class="cbp-l-loadMore-noMoreLoading">NO MORE WORKS</span>--%>
<%--                    </a>--%>
<%--                </div>--%>



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

<script src="map.js"></script>


<%@include file="map_search.jsp"%>


<!--END script frame home/frame/frame_javascript-->
</body>

</html>
