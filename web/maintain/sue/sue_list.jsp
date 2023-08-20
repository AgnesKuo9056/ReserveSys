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
            <h3 class="page-title">投诉
                <small>投诉管理</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html"> 管理 </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">投诉管理</a>
                        <i class="fa fa-angle-right"></i>
                    </li>

                </ul>

            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="sue_manage_list"/>

            <input type="hidden" id="user_role" name="user_role" value="${User.user_role}" />

            <!-- BEGIN EXAMPLE TABLE PORTLET 可排序,修改 列表 模版-->
            <div class="portlet light ">
                <div class="portlet-title">
                    <div class="caption font-dark">
                        <i class="icon-settings font-dark"></i>
                        <span class="caption-subject bold uppercase"> 投诉列表</span>
                    </div>
                    <div class="actions">
                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active"   id="unhandle">
                                <input type="radio" name="options" class="toggle">待处里</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"  id="handle">
                                <input type="radio" name="options" class="toggle" >已处里</label>

                        </div>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
                             <c:choose>
                                <c:when test="${User.user_role  eq 0}"><!--当为不为管理员登入时 才能添加民宿-->
                                </c:when>
                            <c:otherwise><!--当为商家登入时 可以添加民宿-->
                            <div class="btn-group">
                                <button id="on_add_sue" class="btn sbold green"> 我要投诉!!>^<
                                    <i class="icon-envelope-letter"></i>
                                </button>
                            </div>

                        </c:otherwise>
                        </c:choose>

                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dt-responsive dataTable" width="100%" id="sue_manage">

                        <thead>
<%--                        1个预留给+ 九个为正常显示(第九个为more链接 跳转到statistic页面) 四个为扩展--%>
                        <tr>
                            <th >ID</th>
                            <th >投诉用户/商家</th>
                            <th id="gh_id">类型</th>
                            <th class="all">内容</th>
                            <th class="min-tablet">备注note</th>
                            <th class="min-phone-l">回复</th>
                            <th class="desktop">创建时间</th>
<%--                            <th class="min-phone-l">完成时间</th>--%>
                            <th class="min-phone-l">操作</th>
                        </tr>
<%--                        <tr role="row" class="filter">--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                扩展列表&ndash;%&gt;--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                民宿编号&ndash;%&gt;--%>
<%--                                <input type="text" class="form-control form-filter input-sm" name="gh_id"> </td>--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                民宿名称&ndash;%&gt;--%>
<%--                                <input type="text" class="form-control form-filter input-sm" name="gh_name"> </td>--%>
<%--                            <td>--%>
<%--                            <td>--%>
<%--                            &lt;%&ndash;                                地址&ndash;%&gt;--%>
<%--                                <input type="text" class="form-control form-filter input-sm" name="gh_address"> </td>--%>
<%--                            <td>--%>
<%--                            <td>--%>
<%--                            &lt;%&ndash;                                房东&ndash;%&gt;--%>
<%--                                <input type="text" class="form-control form-filter input-sm" name="user_name"> </td>--%>
<%--                            <td>--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                创建时间&ndash;%&gt;--%>
<%--                                <div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">--%>
<%--                                    <input type="text" class="form-control form-filter input-sm" readonly name="create_date_from" placeholder="From">--%>
<%--                                    <span class="input-group-btn">--%>
<%--                                                                <button class="btn btn-sm default" type="button">--%>
<%--                                                                    <i class="fa fa-calendar"></i>--%>
<%--                                                                </button>--%>
<%--                                                            </span>--%>
<%--                                </div>--%>
<%--                                <div class="input-group date date-picker" data-date-format="dd/mm/yyyy">--%>
<%--                                    <input type="text" class="form-control form-filter input-sm" readonly name="create_date_to" placeholder="To">--%>
<%--                                    <span class="input-group-btn">--%>
<%--                                                                <button class="btn btn-sm default" type="button">--%>
<%--                                                                    <i class="fa fa-calendar"></i>--%>
<%--                                                                </button>--%>
<%--                                                            </span>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                更新时间&ndash;%&gt;--%>
<%--                                <div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">--%>
<%--                                    <input type="text" class="form-control form-filter input-sm" readonly name="update_date_from" placeholder="From">--%>
<%--                                    <span class="input-group-btn">--%>
<%--                                                                <button class="btn btn-sm default" type="button">--%>
<%--                                                                    <i class="fa fa-calendar"></i>--%>
<%--                                                                </button>--%>
<%--                                                            </span>--%>
<%--                                </div>--%>
<%--                                <div class="input-group date date-picker" data-date-format="dd/mm/yyyy">--%>
<%--                                    <input type="text" class="form-control form-filter input-sm" readonly name="update_date_to" placeholder="To">--%>
<%--                                    <span class="input-group-btn">--%>
<%--                                                                <button class="btn btn-sm default" type="button">--%>
<%--                                                                    <i class="fa fa-calendar"></i>--%>
<%--                                                                </button>--%>
<%--                                                            </span>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                审核管理员&ndash;%&gt;--%>
<%--                                <input type="text" class="form-control form-filter input-sm" name="gh_examine_admin"> </td>--%>
<%--                            <td>--%>
<%--                            <td>--%>
<%--                                &lt;%&ndash;                                更多祥情&ndash;%&gt;--%>
<%--                                <div class="margin-bottom-5">--%>
<%--                                    <button class="btn btn-sm green btn-outline filter-submit margin-bottom">--%>
<%--                                        <i class="fa fa-search"></i> Search</button>--%>
<%--                                </div>--%>
<%--                                <button class="btn btn-sm red btn-outline filter-cancel">--%>
<%--                                    <i class="fa fa-times"></i> Reset</button>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
                        </thead>
<%--                        <tbody>--%>
<%--                        <tr id="gh_list">--%>
<%--                            <th></th>--%>
<%--                            <td>民宿名称</td>--%>
<%--                            <td>地址</td>--%>
<%--                            <td>所有者</td>--%>
<%--                            <td>评分</td>--%>
<%--                            <td>创建时间</td>--%>
<%--                            <td>更新时间</td>--%>
<%--                            <td>--%>
<%--                                <a class="edit" href="javascript:Page.onDetailRecord(list.id)"> 详情 </a>--%>
<%--                            </td>--%>
<%--                            <td>房型与数量</td>--%>
<%--                            <td>--%>
<%--                                <a href="mailto:shuxer@gmail.com"> shuxer@gmail.com 房型与数量 </a>--%>
<%--                            </td>--%>
<%--                            <td>房东信息</td>--%>
<%--                            <td>其它备注</td>--%>
<%--                        </tr>--%>
                        <tbody></tbody>

<%--                        </tbody>--%>
                    </table>
                </div>
            </div>
            <!-- END EXAMPLE TABLE PORTLET-->

        </div>
    </div>
            <!--页面头更改结束====================================================================================================================-->
        </div>

        <!-- END CONTENT BODY -->

    <!-- END CONTENT -->
    <!-- BEGIN QUICK SIDEBAR -->
    <%@include file="../../home/frame/frame_javascript.jsp"%>
    <%@include file="../../home/frame/frame_quick_siderbar.jsp"%>
    <!-- END QUICK SIDEBAR -->


<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../../home/frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<!-- BEGIN script frame home/frame/frame_javascript-->

<%@include file="sue_add.jsp"%>
<script src="sue.js"></script>


<!--END script frame home/frame/frame_javascript-->
</body>
</html>
