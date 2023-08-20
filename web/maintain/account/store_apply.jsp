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
            <h3 class="page-title">用户管理
                <small>商家审核</small>
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
                        <span>商家审核</span>
                    </li>
                </ul>

            </div>
            <!-- END PAGE HEADER-->
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="store_apply"/>

            <!-- BEGIN EXAMPLE TABLE PORTLET 可排序,修改 列表 模版-->
            <div class="portlet light ">
                <div class="portlet-title">
                    <div class="caption font-dark">
                        <i class="icon-settings font-dark"></i>
                        <span class="caption-subject bold uppercase"> 商家审核管理</span>
                    </div>
                    <div class="actions">
                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active"   id="wait_apply">
                                <input type="radio" name="options" class="toggle">待审核</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"  id="passed">
                                <input type="radio" name="options" class="toggle" >审核通过</label>
                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm"    id="refused">
                                <input type="radio" name="options" class="toggle">审核失败</label>
                            <%--                            状态：商家审核的当前状态，例如待审核0、已通过1、未通过2 --%>
                        </div>
                    </div>
                </div>
                <div class="portlet-body">

                    <table class="table table-striped table-bordered table-hover dt-responsive dataTable" width="100%" id="store_apply_list">

                        <thead>
<%--                       6个正常显示 不要扩展行--%>
                        <tr>

<%--                            <th></th>--%>
<%--                            --%>
                            <th id="user_id">用户编号</th>

                            <th class="min-tablet">申请者名称</th>

                            <th class="min-phone-l">邮箱</th>

                            <th class="desktop">认证申请文件</th>

                            <th class="desktop">支付账号</th>

                            <th class="desktop">申请日期</th>

                            <th class="min-phone-l">操作</th>


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
<script src="user.js"></script>
<script>
    // 为所有图片添加点击事件
    var images = document.getElementsByTagName("img");
    for (var i = 0; i < images.length; i++) {
        images[i].addEventListener("click", function() {
            // 创建放大显示图片的div
            var div = document.createElement("div");
            div.style.position = "fixed";
            div.style.top = 0;
            div.style.left = 0;
            div.style.bottom = 0;
            div.style.right = 0;
            div.style.backgroundColor = "rgba(0,0,0,0.5)";
            div.style.display = "flex";
            div.style.justifyContent = "center";
            div.style.alignItems = "center";
            div.style.zIndex = "9999";
            document.body.appendChild(div);

            // 创建放大显示的图片
            var img = document.createElement("img");
            img.src = this.src;
            img.style.maxWidth = "90%";
            img.style.maxHeight = "90%";
            div.appendChild(img);

            // 点击div后，移除放大显示图片的div
            div.addEventListener("click", function() {
                document.body.removeChild(div);
            });
        });
    }

</script>
<!--END script frame home/frame/frame_javascript-->
</body>
</html>
