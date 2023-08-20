<%--<%@ taglib prefix="sf" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--<input type="hidden" id="page_id" name="page_id" value="frame_header"/>--%>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top" >
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner ">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
            <c:choose>
                <c:when test="${User.user_role  eq 2}"><!--当为管理员登入时 不能添加民宿-->
                <a href="../../maintain/map/map_list.jsp">
                </c:when>
                <c:otherwise><!--当为商家登入时 可以添加民宿-->
                    <a href="../../home/main/index.jsp">

                </c:otherwise>
            </c:choose>

                <img src="../../assets/layouts/layout2/img/logo.jpg" style="width: 130px;height: 68px; padding: 0px 15px 0px 10px;margin: 0px" alt="logo" class="logo-default" /> </a>
            <div class="menu-toggler sidebar-toggler">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN PAGE ACTIONS -->
        <!-- DOC: Remove "hide" class to enable the page header actions -->
        <c:choose>
            <c:when test="${User.user_role  eq 1}"><!--当为管理员登入时 不能添加民宿-->
                <div class="page-actions" id="store_add" >
                    <div class="btn-group">
                        <button type="button" class="btn btn-circle btn-outline red dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-plus"></i>&nbsp;
                            <span class="hidden-sm hidden-xs">添加 &nbsp;</span>&nbsp;
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="javascript:;">
                                    <i class="icon-docs"></i> 民宿 </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="icon-tag"></i> 房型 </a>
                            </li>
                        </ul>

                    </div>
                </div>
            </c:when>
            <c:otherwise><!--当为商家登入时 可以添加民宿-->


            </c:otherwise>
        </c:choose>

        <!-- END PAGE ACTIONS -->
        <!-- BEGIN PAGE TOP -->

        <div class="page-top">

            <!-- BEGIN HEADER SEARCH BOX -->
            <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
            <form class="search-form search-form-expanded" action="../../maintain/comment/cooment.jsp?action=query_comment" method="GET">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="搜索评论..." name="query">
                    <span class="input-group-btn">
                                <a href="javascript:;" class="btn  ubmit">
                                    <i class="icon-magnifier"></i>
                                </a>
                            </span>
                </div>
            </form>
            <!-- END HEADER SEARCH BOX -->
            <!-- BEGIN TOP NAVIGATION MENU -->

            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">

                    <!-- BEGIN NOTIFICATION DROPDOWN -->
                    <c:choose>
                        <c:when test="${User.user_role  eq 2}"><!--当为管理员登入时 不能添加民宿-->
                            <li class="dropdown dropdown-inbox" id="header_inbox_bar2" href="../../maintain/map/map_list.jsp" style="margin: 10px 17px 19px 16px">
                                <a href="../../maintain/map/map_list.jsp" >
                                    <span aria-hidden="true" class="icon-book-open"> </span>
                                        <%--                            <span class="badge badge-default"> 4 </span>--%>
                                    民宿预定
                                </a>
                            </li>
                            <li class="dropdown dropdown-inbox" id="header_inbox_bar" href="../../maintain/guesthouse/guesthouse_list.jsp" style="display: none;margin: 10px 17px 19px 16px">
                                <a href="../../maintain/comment/comment.jsp" >
                                    <span aria-hidden="true" class="icon-anchor"> </span>
                                        <%--                            <span class="badge badge-default"> 4 </span>--%>
                                    旅游墙
                                </a>
                            </li>

                            <li class="dropdown dropdown-extended dropdown-tasks" id="dropdown-user" >
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <span aria-hidden="true" class="icon-heart"></span>
                                </a>
                                <ul class="dropdown-menu extended tasks">
                                    <li class="external">
                                        <h3>You have
                                            <span class="bold">${fn:length(Favorites)}</span> favorites </h3>
                                            <%--                                <a href="app_todo.html">view all</a>--%>
                                    </li>
                                    <li>
                                        <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                            <c:forEach items="${Favorites}" var = "favorite">
                                                <li>
                                                    <a href="../../maintain/map/map_detail.jsp?gh_id=${favorite.value.gh_id}&owner_id=${favorite.value.owner_id}">
                                                    <span class="photo">
                                                        <img src="../../assets/global/img/gh_pic/${favorite.value.gh_img}" class="img-circle" style="height: 50px;width: 50px" alt=""> </span>
                                                        <span class="subject">
                                                        <span class="from"><b>${favorite.value.gh_name} </b> </span>
                                                        <span class="time">${favorite.value.grade}</span>
                                                    </span><br>
                                                        <span class="message"> ${favorite.value.tags}</span><br>
                                                        <span class="message"> ${favorite.value.gh_address}</span><br>
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise><!--当为商家登入时 可以添加民宿-->


                        </c:otherwise>
                    </c:choose>

                    <li class="dropdown dropdown-user">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <img alt="" class="img-circle" src="../../assets/global/img/avatar/${User.avatar}" />
                            <span class="username username-hide-on-mobile" id="user_name" name="user_name"> ${User.user_name} </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">
                            <li>
                                <a href="../../home/profile/account_setting.jsp">
                                <i class="icon-user"></i> 个人中心 </a>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <a href="../../home/main/lock.jsp">
                                    <i class="icon-lock"></i> 锁屏 </a>
                            </li>
                            <li>
                                <a href="../../user_center_servlet_action?action=log_out">
                                    <i class="icon-key"></i> 登出 </a>
                            </li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                    <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                    <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                    <c:choose>
                        <c:when test="${User.user_role eq 1 || Store.status eq  3}"><!--当为商家登入或是商家切换角色为用户时 可以点击切换角色-->
                            <li class="" style="width: 100px;height: 24px;padding: 24px 24px 12px 12px" onclick="changeUser()">
                                <i class="icon-logout"  style="width:100px;">
                                    <span > <small>
                            <c:choose>
                                <c:when test="${Store.status eq  3}"><!--当为商家登入或是商家切换角色为用户时 可以点击切换角色-->
                                        切换回商家
                                </c:when>
                                <c:otherwise>
                                        切换为用户
                                </c:otherwise>
                            </c:choose>
                                    </small></span>
                                </i>
                            </li>
                        </c:when>

                    </c:choose>

                    <!-- END QUICK SIDEBAR TOGGLER -->
                </ul>
            </div>
<%--            <div class="top-menu">--%>
<%--                <ul class="nav navbar-nav pull-right">--%>

<%--                    <!-- BEGIN NOTIFICATION DROPDOWN -->--%>
<%--                    <li class="dropdown dropdown-extended dropdown-notification" id="reserve_guesthouse_bar">--%>
<%--&lt;%&ndash;                        <a href="../../maintain/map/map_list.jsp"></a>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <span class="username username-hide-on-mobile" >  </span>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        </a>&ndash;%&gt;--%>
<%--                        <a href="../../maintain/map/map_list.jsp" class="icon-btn">--%>
<%--                            <span aria-hidden="true" class="icon-book-open"></span>--%>
<%--                            <div> 民宿预定 </div>--%>
<%--                            <span class="badge badge-success"> book </span>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li class="dropdown dropdown-extended dropdown-notification" id="reserve_product_bar">--%>
<%--&lt;%&ndash;                        <a href="../../maintain/product/cooment.jsp"></a>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <span class="username username-hide-on-mobile" >  </span>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        </a>&ndash;%&gt;--%>
<%--                        <a href="javascript:;" class="icon-btn">--%>
<%--                            <span aria-hidden="true" class="icon-anchor"></span>--%>
<%--                            <div> 特产预定 </div>--%>

<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
            <!-- END TOP NAVIGATION MENU -->
<%--            用户超时提示弹窗--%>
            <div class="modal fade" id="idle-timeout-dialog" data-backdrop="static">
                <div class="modal-dialog modal-small">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Your session is about to expire.</h4>
                        </div>
                        <div class="modal-body">
                            <p>
                                <i class="fa fa-warning font-red"></i> 为了确保数据安全,系统将於
                                <span id="idle-timeout-counter"></span> 秒后</p>
                            将移除您的登录状态
                            <p> 是否维持登录状态 </p>
                        </div>
                        <div class="modal-footer">
                            <button id="idle-timeout-dialog-logout" type="button" class="btn dark btn-outline sbold uppercase">登出</button>
                            <button id="idle-timeout-dialog-keepalive" type="button" class="btn green btn-outline sbold uppercase" data-dismiss="modal">继续浏览</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END PAGE TOP -->
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<script>
    function changeUser() {
        $.post("../../user_center_servlet_action?action=change_store_status", function (json) {
            window.location.href="../../"+json.redirect_url;
        });

    }

</script>
<%--<script src="../../assets/global/plugins/jquery-idle-timeout/jquery.idletimeout.js" type="text/javascript"></script>--%>
<%--<script src="../../assets/global/plugins/jquery-idle-timeout/jquery.idletimer.js" type="text/javascript"></script>--%>
<%--<script src="../../assets/pages/scripts/ui-idletimeout.min.js" type="text/javascript"></script>--%>
<%--<%@include file="../../home/frame/frame_javascript.jsp"%>--%>
<%--<script src="../frame/frame.js"></script>--%>