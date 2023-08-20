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
    <%@include file="../../home/frame/frame_style.jsp"%>
    <style type="text/css">
        /*html{height:100%}*/
        /*body{height:100%;margin:0px;padding:0px}*/

        #container{
            /*height: 100%;*/
            width: 60%;
            float: right;
            margin-top:35px;
            margin-right: 20px;
        }

        .my-map-table{
            width: 35%;
            margin-top:0px;
            margin-left: 15px;
            margin-right: 10px;
        }

    </style>
    <!--  baidu map api  -->
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=1.0&type=webgl&ak=2COzFaICuIyVj7V3VetKfmdRVnX8BhVr"></script>
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
            <input type="hidden" id="page_id" name="page_id" value="user_list"/>
            <!--查询表格-->
            <div style="display:inline-block" class="my-map-table">
                <div style="display:inline;"class="col-sm-8 col-lg-6">
                    <div  class="iq-card">
                        <!--    标题-->
                        <div class="iq-card-header d-flex justify-content-between">
                            <div class="iq-header-title">
                                <h4 class="card-title">定位搜索 - Find Position</h4>
                            </div>
                        </div>

                        <!--    内容-->
                        <div class="iq-card-body">

                            <!--   查找 search -->
                            <div style="margin-left: -15px">
                                <div class="iq-search-bar">
                                    <form id="search-form" class="searchbox" action="/map">
                                        <input id ="value-search" type="text" name="agencyName" class="text search-input" placeholder="Type here to search...">
                                        <input id="btn-search2" type="image" value="Submit" src="imgs/map-search.jpg" name="map-search-img"
                                               style="width: 28px;height: 28px; position: absolute;float: right;margin-top: 3px"> <!--必须用jpg格式-->
                                        <!--                        <a class="search-link" href="#"><i class="ri-search-line"></i></a>-->
                                    </form>
                                </div>
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                </button>
                            </div>

                            <div class="iq-card">
                                <ul class="nav nav-tabs justify-content-end" id="myTab-4" role="tablist">
                                    <li class="nav-item" onclick="createPoint(getAgencyData())">
                                        <a class="nav-link active" id="home-tab1-end" data-toggle="tab" href="#home-end" role="tab" aria-controls="home" aria-selected="true">全部</a>
                                    </li>
                                    <li class="nav-item" onclick="createPoint(getAgencySmallData())">
                                        <a class="nav-link" id="home2-tab-end" data-toggle="tab" href="#home2-end" role="tab" aria-controls="home" aria-selected="true">幼儿园</a>
                                    </li>
                                    <li class="nav-item" onclick="createPoint(getAgencyMiddleData())">
                                        <a class="nav-link" id="home3-tab-end" data-toggle="tab" href="#home3-end" role="tab" aria-controls="home" aria-selected="true">中小学</a>
                                    </li>
                                    <li class="nav-item" onclick="createPoint(getAgencyCollegeData())">
                                        <a class="nav-link" id="profile-tab-end" data-toggle="tab" href="#profile-end" role="tab" aria-controls="profile" aria-selected="false">大学</a>
                                    </li>
                                    <li class="nav-item" onclick="createPoint(getAgencyOtherData())">
                                        <a class="nav-link" id="contact-tab-end" data-toggle="tab" href="#contact-end" role="tab" aria-controls="contact" aria-selected="false">其他机构</a>
                                    </li>
                                </ul>

                                <div class="tab-content" id="myTabContent-5">
                                    <div class="tab-pane fade show active" id="home-end" role="tabpanel" aria-labelledby="home-tab-end">
                                        <!--  table1 -->
                                        <p id="search-p">一共查询到<mark><span th:text="${agencySNum}"></span>个</mark>与<mark><span th:text="${agencySearchName}"></span></mark>有关的机构.</p>
                                        <table id="search-table" class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">机构名称</th>
                                                <th scope="col">机构地址</th>
                                                <th scope="col">联系方式</th>
                                            </tr>
                                            </thead>
                                            <tbody id="search-table-tbody">
                                            <tr id="search-table-tr" th:each="agency : ${agency}" onclick="findPosition(this)">
                                                <th scope="row">[[${agency.agencyID}]]</th>
                                                <td>[[${agency.agencyName}]]</td>
                                                <td>[[${agency.agencyAddress}]]</td>
                                                <td>[[${agency.agencyPhone}]]</td>
                                                <td style="display: none">[[${agency.agencyX}]]</td>
                                                <td style="display: none">[[${agency.agencyY}]]</td>
                                                <td style="display: none">[[${agency.agencyImg}]]</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="tab-pane fade" id="home2-end" role="tabpanel" aria-labelledby="home-tab-end">
                                        <!--  table2 -->
                                        <p>一共查询到<mark><span th:text="${smallNum}"></span>个</mark>幼儿园机构.</p>
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">机构名称</th>
                                                <th scope="col">机构地址</th>
                                                <th scope="col">联系方式</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr th:each="agencySmall : ${agencySmall}" onclick="findPosition(this)">
                                                <th scope="row">[[${agencySmall.agencyID}]]</th>
                                                <td>[[${agencySmall.agencyName}]]</td>
                                                <td>[[${agencySmall.agencyAddress}]]</td>
                                                <td>[[${agencySmall.agencyPhone}]]</td>
                                                <td style="display: none">[[${agencySmall.agencyX}]]</td>
                                                <td style="display: none">[[${agencySmall.agencyY}]]</td>
                                                <td style="display: none">[[${agencySmall.agencyImg}]]</td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="tab-pane fade" id="home3-end" role="tabpanel" aria-labelledby="home-tab-end">
                                        <!--  table3 -->
                                        <p>一共查询到<mark><span th:text="${middleNum}"></span>个</mark>中小学机构.</p>
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">机构名称</th>
                                                <th scope="col">机构地址</th>
                                                <th scope="col">联系方式</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr th:each="agencyMiddle : ${agencyMiddle}" onclick="findPosition(this)">
                                                <th scope="row">[[${agencyMiddle.agencyID}]]</th>
                                                <td>[[${agencyMiddle.agencyName}]]</td>
                                                <td>[[${agencyMiddle.agencyAddress}]]</td>
                                                <td>[[${agencyMiddle.agencyPhone}]]</td>
                                                <td style="display: none">[[${agencyMiddle.agencyX}]]</td>
                                                <td style="display: none">[[${agencyMiddle.agencyY}]]</td>
                                                <td style="display: none">[[${agencyMiddle.agencyImg}]]</td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="tab-pane fade" id="profile-end" role="tabpanel" aria-labelledby="profile-tab-end">
                                        <!--  table3 -->
                                        <p>一共查询到<mark><span th:text="${collegeNum}"></span>个</mark>大学机构.</p>
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">机构名称</th>
                                                <th scope="col">机构地址</th>
                                                <th scope="col">联系方式</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr th:each="agencyCollege : ${agencyCollege}" onclick="findPosition(this)">
                                                <th scope="row">[[${agencyCollege.agencyID}]]</th>
                                                <td>[[${agencyCollege.agencyName}]]</td>
                                                <td>[[${agencyCollege.agencyAddress}]]</td>
                                                <td>[[${agencyCollege.agencyPhone}]]</td>
                                                <td style="display: none">[[${agencyCollege.agencyX}]]</td>
                                                <td style="display: none">[[${agencyCollege.agencyY}]]</td>
                                                <td style="display: none">[[${agencyCollege.agencyImg}]]</td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="tab-pane fade" id="contact-end" role="tabpanel" aria-labelledby="contact-tab-end">
                                        <!--  table4 -->
                                        <p>一共查询到<mark><span th:text="${otherNum}"></span>个</mark>校外独立机构.</p>
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">机构名称</th>
                                                <th scope="col">机构地址</th>
                                                <th scope="col">联系方式</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr th:each="agencyOther : ${agencyOther}" onclick="findPosition(this)">
                                                <th scope="row">[[${agencyOther.agencyID}]]</th>
                                                <td>[[${agencyOther.agencyName}]]</td>
                                                <td>[[${agencyOther.agencyAddress}]]</td>
                                                <td>[[${agencyOther.agencyPhone}]]</td>
                                                <td style="display: none">[[${agencyOther.agencyX}]]</td>
                                                <td style="display: none">[[${agencyOther.agencyY}]]</td>
                                                <td style="display: none">[[${agencyOther.agencyImg}]]</td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!--地图  请勿在此div外添加div-->
            <div style="display:inline-block;height: 800px;" id="container"></div>
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

<script src="guesthouse.js"></script>

<!--END script frame home/frame/frame_javascript-->
</body>
</html>
