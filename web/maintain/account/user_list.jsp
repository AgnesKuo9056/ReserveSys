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

            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="user_list"/>

            <button id="add_button_condition" name="add_button_condition" class="btn btn-primary"> + 添加用户</button>
<%--            <input type="button" name="query_btn" id="query_btn" class="btn btn-primary" value="查找用户">--%>
            <button id="export_button" name="export_button" class="btn btn-primary">Excel</button>
            <button id="print_table" name="print_table" class="btn btn-primary">Print</button>
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

            <i class="icon-magnifier" onclick="javascript:Page.onqueryRecord()"></i>
            <div></div>
            <p class="search-desc" id="search_result" style="display: none; color:#add2c2; " ></p>
            <%@include file="../account/user_search.jsp"%>
            <div class="row" id="datatable_userlist">
                <div class="col-md-10">
                    <table class="table table-striped table-bordered table-hover datatable" id="user_list">
                        <thead>
                        <tr>
<%--                            <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#user_list .checkboxes" /></th>--%>
                            <th>用户ID</th>
                            <th>用户名称</th>
                            <th>联系方式</th>
                            <th>邮箱</th>
                            <th>用户角色</th>
                            <th>更改</th>
                            <th>注销</th>
                        </tr>
                        </thead>
                    </table>
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

<script src="user.js"></script>
<%@include file="user_search.jsp"%>
<%@include file="user_download.jsp"%>
<%@include file="user_add.jsp"%>
<%@include file="user_modify.jsp"%>

<!--END script frame home/frame/frame_javascript-->
</body>
<%--<script>--%>
<%--    //搜索--%>
<%--    function search() {--%>


<%--        var user_id =$(" #user_search #user_id").val();--%>
<%--        var user_name =$(" #user_search #user_name").val();--%>
<%--        var mail =$("#user_search #mail").val();--%>
<%--        var phone_number = $(" #user_search #phone_number").val();--%>
<%--        var user_role  = $("#user_search #user_role option:selected").val();--%>
<%--        //查询参数--%>
<%--        var param = {--%>
<%--            "user_id":user_id,--%>
<%--            "user_name":user_name,--%>
<%--            "mail":mail,--%>
<%--            "phone_number":phone_number,--%>
<%--            "user_role":user_role,--%>
<%--        };--%>
<%--        if (user_id === ''){--%>
<%--            delete param["user_id"];--%>
<%--        }--%>
<%--        if (user_name === ''){--%>
<%--            delete param.user_name;--%>
<%--        }--%>
<%--        if (mail === ''){--%>
<%--            delete param.mail;--%>
<%--        }--%>
<%--        if (phone_number === ''){--%>
<%--            delete param.phone_number;--%>
<%--        }--%>
<%--        if (user_role === ''){--%>
<%--            delete param["user_role"];--%>
<%--        }--%>

<%--        console.log(param);--%>
<%--        if (param !== {}){--%>
<%--            //设置参数，重新加载--%>
<%--            dataTable.settings()[0].ajax.data = param;--%>
<%--            dataTable.ajax.reload();--%>
<%--        }--%>
<%--    }--%>

<%--</script>--%>
</html>
