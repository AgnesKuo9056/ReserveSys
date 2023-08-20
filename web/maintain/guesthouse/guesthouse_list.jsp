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
            <h3 class="page-title">民宿管理
                <small>民宿信息管理</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html"> 管理 </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">民宿管理</a>
                        <i class="fa fa-angle-right"></i>
                    </li>

                </ul>

            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="gh_manage_list"/>

<%--            <div class="row" style="padding-bottom: 10px">--%>
<%--                <div class="col-md-6">--%>
<%--                    <div class="portlet-body form" id="user_search_div" name="user_search_div">--%>
<%--                    <form class="form-horizontal" role="form">--%>
<%--                        <div class="form-body">--%>
<%--                            <div class="form-group">--%>
<%--                                <label class="col-md-3 control-label">ID</label>--%>
<%--                                <div class="col-md-9">--%>
<%--                                    <input type="text" id="user_search_id" name="user_search_id" class="form-control" placeholder="Enter text">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="form-group">--%>
<%--                                <label class="col-md-3 control-label">姓名</label>--%>
<%--                                <div class="col-md-9">--%>
<%--                                    <input type="text" id="user_search_name" name="user_search_name" class="form-control" placeholder="Enter text">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="form-group">--%>
<%--                                <label class="col-md-3 control-label">邮箱</label>--%>
<%--                                <div class="col-md-9">--%>
<%--                                    <input type="text"  id="user_search_mail" name="user_search_mail" class="form-control" placeholder="Enter text">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="form-group">--%>
<%--                                <label class="col-md-3 control-label">联系方式 </label>--%>
<%--                                <div class="col-md-9">--%>
<%--                                    <input type="text"  id="user_search_phone_number" name="user_search_phone_number" class="form-control" placeholder="Enter text">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="form-group">--%>
<%--                                <label class="col-md-3 control-label">用户权限</label>--%>
<%--                                <div class="col-md-9" id="option_div">--%>
<%--                                    <select  id="user_role" name="user_role" class="form-control">--%>
<%--                                        <option>Option 1</option>--%>
<%--                                        <option>Option 2</option>--%>
<%--                                        <option>Option 3</option>--%>
<%--                                        <option>Option 4</option>--%>
<%--                                        <option>Option 5</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                        </div>--%>
<%--                    </form>--%>
<%--                        <button id="search_btn" name="search_btn" class="btn btn-info" >搜索用户信息</button>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>

            <!-- BEGIN EXAMPLE TABLE PORTLET 可排序,修改 列表 模版-->
            <div class="portlet light ">
                <div class="portlet-title">
                    <div class="caption font-dark">
                        <i class="icon-settings font-dark"></i>
                        <span class="caption-subject bold uppercase"> 民宿信息管理</span>
                    </div>
                    <div class="actions">
                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active"   id="passed">
                                <input type="radio" name="options" class="toggle">审核通过</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"  id="waitpassed">
                                <input type="radio" name="options" class="toggle" >待审核</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"    id="unpassed">
                                <input type="radio" name="options" class="toggle">未通过审核/取消</label>
                        </div>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
                             <c:choose>
                                <c:when test="${User.user_role  eq 0}"><!--当为管理员登入时 不能添加民宿-->
                            </c:when>
                            <c:otherwise><!--当为商家登入时 可以添加民宿-->
                            <div class="btn-group">
                                <button id="add_gh" class="btn sbold green"> 添加民宿
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>

                        </c:otherwise>
                        </c:choose>

                            </div>
<%--                            <div class="col-md-6">--%>
<%--                                <div class="btn-group pull-right">--%>
<%--                                    <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">Tools--%>
<%--                                        <i class="fa fa-angle-down"></i>--%>
<%--                                    </button>--%>
<%--                                    <ul class="dropdown-menu pull-right">--%>
<%--                                        <li>--%>
<%--                                            <a href="javascript:;">--%>
<%--                                                <i class="fa fa-print"></i> Print </a>--%>
<%--                                        </li>--%>
<%--                                        <li>--%>
<%--                                            <a href="javascript:;">--%>
<%--                                                <i class="fa fa-file-excel-o"></i> Export to Excel </a>--%>
<%--                                        </li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover dt-responsive dataTable" width="100%" id="gh_manage">

                        <thead>
<%--                        1个预留给+ 九个为正常显示(第九个为more链接 跳转到statistic页面) 四个为扩展--%>
                        <tr>
                            <th></th>
                            <th id="gh_id">民宿编号</th>
                            <th class="all">民宿名称</th>
                            <th class="min-tablet">地址</th>
                            <th class="min-phone-l">房东</th>
                            <th class="desktop">评分</th>
                            <th class="min-phone-l">创建时间</th>
                            <th class="min-phone-l">更新时间</th>
                            <th class="desktop">审核管理员</th>
                            <th class="desktop">MORE INFO</th>
                            <th class="none">房型与数量</th>
                            <th class="none">房东联系方式</th>
                            <th class="none">房东信息</th>
                            <th class="none">其它备注</th>
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

<script src="guesthouse.js"></script>
<%@include file="guesthouse_search.jsp"%>
<%@include file="guesthouse_download.jsp"%>
<%@include file="guesthouse_add.jsp"%>
<%@include file="guesthouse_modify.jsp"%>

<!--END script frame home/frame/frame_javascript-->
</body>
</html>
