<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- BEGIN SIDEBAR -->


            <c:choose>
                <c:when test="${User.user_role  eq 0}"><!--当为管理员登入时 拥有所有权限-->
<div class="page-sidebar-wrapper">
                    <!-- END SIDEBAR -->
        <div class="page-sidebar navbar-collapse collapse">

              <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                    <li class="nav-item start " id="user_manage">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-home"></i>
                            <span class="title">用户管理</span>
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu" >
                            <li class="nav-item start " >
                                <a href="../../maintain/account/user_list.jsp" class="nav-link ">
                                    <i class="icon-bar-chart"></i>
                                    <span class="title"> 账号管理 </span>
                                </a>
                            </li>
                        </ul>
                        <ul class="sub-menu" >
                            <li class="nav-item start " >
                                <a href="../../maintain/account/store_apply.jsp" class="nav-link ">
                                    <i class="icon-bar-chart"></i>
                                    <span class="title"> 商家审核 </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item  " id="gh_manage">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-diamond"></i>
                            <span class="title">民宿管理</span>
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li class="nav-item  ">
                                <a href="../../maintain/guesthouse/guesthouse_list.jsp" class="nav-link ">
                                    <span class="title">民宿信息管理</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item  " id="order_manage">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-puzzle"></i>
                            <span class="title">订单管理</span>
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li class="nav-item  " id="gh_order_manage">
                                <a href="../../order/guesthouse/guesthouse_order_list.jsp?user_role=${User.user_role}" class="nav-link ">
                                    <span class="title">民宿订单</span>
                                </a>
                            </li>

                        </ul>
                    </li>
                  <li class="nav-item  " id="sue_manage">
                      <a href="javascript:;" class="nav-link nav-toggle">
                          <i class="icon-puzzle"></i>
                          <span class="title">投诉管理</span>
                          <span class="arrow"></span>
                      </a>
                      <ul class="sub-menu">
                          <li class="nav-item  " id="suer_manage">
                              <a href="../../maintain/sue/sue_list.jsp" class="nav-link ">
                                  <span class="title">投诉列表</span>
                              </a>
                          </li>

                      </ul>
                  </li>
              </ul>
            <!-- END SIDEBAR MENU -->
        </div>
    <!-- END SIDEBAR -->
</div>
                    <!-- END SIDEBAR -->
                </c:when>
                <c:when test="${User.user_role  eq 1}"><!--当为商家登入时 不能对用户管理-->
<div class="page-sidebar-wrapper">
                    <!-- END SIDEBAR -->
       <div class="page-sidebar navbar-collapse collapse">

              <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                    <li class="nav-item  " id="gh_manage_store">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-diamond"></i>
                            <span class="title">民宿管理</span>
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li class="nav-item  ">
                                <a href="../../maintain/guesthouse/guesthouse_list.jsp" class="nav-link ">
                                    <span class="title">民宿信息管理</span>
                                </a>
                            </li>
                            <li class="nav-item  ">
                                <a href="../../maintain/guesthouse/guesthouse_add.jsp" class="nav-link ">
                                    <span class="title">添加民宿/房型</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item  " id="order_manage_store">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-puzzle"></i>
                            <span class="title">订单管理</span>
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li class="nav-item  " id="gh_order_manage_2">
                                <a href="../../order/guesthouse/guesthouse_order_list.jsp?user_role=${User.user_role}" class="nav-link ">
                                    <span class="title">民宿订单</span>
                                </a>
                            </li>

                        </ul>
                    </li>
              </ul>
           <!-- END SIDEBAR MENU -->
       </div>
    <!-- END SIDEBAR -->
</div>
                    <!-- END SIDEBAR -->
                </c:when>
                <c:otherwise><!--当为普通用户登录时,没有管理列表显示-->


                </c:otherwise>
            </c:choose>




<%--<script src="../frame/frame.js"></script>--%>