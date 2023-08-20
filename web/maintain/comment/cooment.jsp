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
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html"> Home </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">旅游墙</a>
                        <i class="fa fa-angle-right"></i>
                    </li>

                </ul>
                <div class="page-toolbar">
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true" aria-expanded="false"> 进阶排序
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right" role="menu">
                            <li>
                                <a href="../../maintain/comment/cooment.jsp?action=query_comment&&orderby=cm_create_date">
                                    <i class="icon-calendar"></i>发布日期</a>
                            </li>
                            <li>
                                <a href="../../maintain/comment/cooment.jsp?action=query_comment&&orderby=star">
                                    <i class="icon-star"></i> 评分</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="comment_list"/>


            <div class="blog-page blog-content-1">
                <div class="row">
                    <c:forEach items="${comments}" var="comment" varStatus="status">
                        <div class="col-lg-6">
                        <c:choose>
                            <c:when test="${status.index % 9 eq 0||status.index % 9 eq 1}">
                                <div class="blog-post-lg bordered blog-container">
                                    <div class="blog-img-thumb">
                                        <a href="javascript:;">
                                            <img src="../../assets/global/img/comment/${comment.img}" />
                                        </a>
                                    </div>
                                    <div class="blog-post-content">
                                        <h2 class="blog-title blog-post-title">
                                            <a href="javascript:;">${comment.title} / BY ${comment.name}</a>
                                        </h2>
                                        <p class="blog-post-desc">${comment.comment_content} </p>
                                        <div class="blog-post-foot">
                                            <div class="blog-post-meta">
                                                <i class="icon-calendar font-blue"></i>
                                                <a href="javascript:;">${comment.cm_create_date}</a>
                                            </div>
                                            <div class="blog-post-meta">
                                                <i class="icon-star font-blue"></i>
                                                <a href="javascript:;">${comment.star}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                         </div>
                        <div class="col-lg-6">
                            <div class="row">
                            <c:when test="${(status.index % 9) eq 2 || (status.index % 9)eq 3} ">
                                <div class="col-sm-6">
                                    <div class="blog-quote bordered blog-container">
                                        <div class="blog-quote-label bg-green-jungle">
                                            <i class="fa fa-quote-left"></i>${comment.comment_content} </div>
                                        <div class="blog-quote-avatar">
                                            <a href="javascript:;">
                                                <img src="../../assets/global/img/comment/${comment.img}" />
                                            </a>
                                        </div>
                                        <div class="blog-quote-author">
                                            <h3 class="blog-title blog-quote-title">
                                                <a href="javascript:;">${comment.title} </a>
                                            </h3>
                                            <p class="blog-quote-desc">${comment.name}</p>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            </div>
                            <c:when test="${status.index % 9 eq 4}">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="blog-banner blog-container" style="background-image:url(../../assets/global/img/comment/${comment.img});">
                                            <h2 class="blog-title blog-banner-title">
                                                <a href="javascript:;"><p>${comment.title}/ <small>${comment.name}</small><br>${comment.comment_content}</p></a>
                                            </h2>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <div class="row">
                            <c:when test="${(status.index % 9) eq 5 || (status.index % 9) eq 6 || (status.index % 9) 9 eq 7 || (status.index % 9) eq 8}">
                                <div class="col-sm-6">
                                    <div class="blog-post-sm bordered blog-container">
                                        <div class="blog-img-thumb">
                                            <a href="javascript:;">
                                                <img src="../assets/pages/img/page_general_search/1.jpg" />
                                            </a>
                                        </div>
                                        <div class="blog-post-content">
                                            <h2 class="blog-title blog-post-title">
                                                <a href="javascript:;">${comment.title} / <small>BY ${comment.name}</small></a>
                                            </h2>
                                            <p class="blog-post-desc">${comment.comment_content}</p>
                                            <div class="blog-post-foot">
                                                <div class="blog-post-meta">
                                                    <i class="icon-calendar font-blue"></i>
                                                    <a href="javascript:;">${comment.cm_create_date}</a>
                                                </div>
                                                <div class="blog-post-meta">
                                                    <i class="icon-star font-blue"></i>
                                                    <a href="javascript:;">${comment.star}</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:when>
                            </div>
                        </c:choose>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
    /*****************************************************************************************************************************************/
<!-- BEGIN QUICK SIDEBAR -->

<%@include file="../../home/frame/frame_javascript.jsp"%>
<%@include file="../../home/frame/frame_quick_siderbar.jsp"%>
<!-- END QUICK SIDEBAR -->
</div>

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../../home/frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<script src="comment.js"></script>



<!--END script frame home/frame/frame_javascript-->
</body>

</html>
