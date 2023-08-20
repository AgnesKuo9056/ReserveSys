<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />
<%--aria-hidden="true" --%>
<div class="modal fade draggable-modal" id="guesthouse_order_edit_modal" tabindex="-1"  role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title"> 订单详情</h4>
            </div>
            <div class="modal-body" id="reload_body">
                <c:forEach  items="${Orders}" var="order">
<%--                    <c:choose>--%>
<%--                        <c:when test="${User.user_role eq 2}"><!--壹般用户查看自己的订单 只能更改 入住者姓名 联系方式 评价与反馈 若是订单已经完成,则可以有评价与反馈-->--%>
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal" id="form_sample_1">
                                <div class="form-body">
                                    <div class="alert alert-danger display-hide">
                                        <button class="close" data-close="alert"></button> You have some form errors. Please check below. </div>
                                    <div class="alert alert-success display-hide">
                                        <button class="close" data-close="alert"></button> Your form validation is successful! </div>
                                        <%--                        订单编号--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >订单编号
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" id="order_id" class="form-control" readonly="readonly" value=" ${order.order_id}">
                                            <div class="form-control-focus"> </div>

                                        </div>
                                    </div>
                                        <%--                         入住日期--%>

                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >入住日期

                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" readonly="readonly" value="${order.check_in_date}" >
                                            <div class="form-control-focus"> </div>

                                        </div>
                                    </div>
                                        <%--                         退房日期--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >退房日期
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" readonly="readonly" value="${order.check_out_date}">
                                            <div class="form-control-focus"> </div>
                                        </div>
                                    </div>
                                        <%--                         客人姓名--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >入住者姓名
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-9">
                                         <c:choose>

                                            <c:when test="${User.user_role eq 2 }"><!--相当于用户查看自己订单-->
                                                <input type="text" id="cus_name" class="form-control"  value="${order.cus_name} " name="name">
                                             </c:when>

                                            <c:otherwise><!--管理员或是商家查看订单-->
                                                <input type="text" id="cus_name" class="form-control"  readonly="readonly" value="${order.cus_name} " >
                                            </c:otherwise>
                                         </c:choose>

                                            <div class="form-control-focus"> </div>
                                        </div>
                                    </div>
                                        <%--                         客人联系方式--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >入住者联系方式
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-9">
                                            <div class="input-icon">
                                                <c:choose>

                                                    <c:when test="${User.user_role eq 2 }"><!--相当于用户查看自己订单-->
                                                        <input type="text" id="cus_phone_num" class="form-control"  value=" ${order.cus_phone_num}" name="number">
                                                    </c:when>

                                                    <c:otherwise><!--管理员或是商家查看订单-->
                                                        <input type="text" id="cus_phone_num" class="form-control" readonly="readonly" value=" ${order.cus_phone_num}" >
                                                    </c:otherwise>
                                                </c:choose>

                                                <div class="form-control-focus"> </div>
                                                 <i class="fa fa-phone"></i>

                                            </div>
                                        </div>
                                    </div>

                                        <%--                         房型--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >房型
                                        </label>
                                        <div class="col-md-9">
                                            <div class="col-md-9">
                                                <input type="text"  id="room_NameAndType" class="form-control"  readonly="readonly" value="${order.room_name} / ${order.room_type_id}人房  " name="name">
                                                <div class="form-control-focus"> </div>
                                            </div>
                                        </div>
                                    </div>
                                        <%--                         民宿所有者--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >民宿所有者</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control"  value="${order.owner.user_name} / ${order.owner.phone_number} " >
                                            <div class="form-control-focus"> </div>
                                        </div>
                                    </div>
                                        <%--                         总价--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >总价</label>
                                        <div class="col-md-9">
                                            <div class="input-icon">
                                                <c:choose>

                                                    <c:when test="${User.user_role eq 2 }"><!--相当于用户查看自己订单-->
                                                        <input type="text" id="total" class="form-control"  readonly="readonly" value=" ${order.total}" >
                                                    </c:when>

                                                    <c:otherwise><!--管理员或是商家查看订单-->
                                                        <input type="text" id="total" class="form-control"  value=" ${order.total}" name="number">
                                                    </c:otherwise>

                                                </c:choose>

                                                <div class="form-control-focus"> </div>
                                            </div>
                                        </div>
                                    </div>

                                        <%--                         预订日期--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >预订日期</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control"  readonly="readonly" value="${order.reserve_date}" >
                                            <div class="form-control-focus"> </div>
                                        </div>
                                    </div>
                                        <%--                         备注--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >备注</label>
                                        <div class="col-md-9">
                                            <textarea class="form-control" name="memo" rows="3">
                                                    ${order.note}</textarea>
                                            <div class="form-control-focus"> </div>
                                        </div>
                                    </div>
<%--                                        ${order.Comment}--%>
                                        <%--                         评价与反馈--%>
                                    <div class="form-group form-md-line-input">
                                        <label class="col-md-3 control-label" >评价与反馈</label>
                                        <div class="col-md-9">
                                            <textarea class="form-control" name="comment" rows="3" readonly="readonly">

                                              <c:choose>
                                                        <c:when test="${empty  order.comment}">
                                                            暂时无评论
                                                        </c:when>
                                                     <c:otherwise>
                                                         ${order.comment}
                                                    </c:otherwise>

                                            </c:choose>
                                            </textarea>
                                            <div class="form-control-focus"> </div>
                                        </div>
                                    </div>

                            </div>
                        </form>
                    <!-- END VALIDATION STATES-->
                </c:forEach>
            </div>
                    <div class="modal-footer">
                    <button type="button" class="btn dark btn-outline" data-dismiss="modal">取消</button>
                    <button type="button" class="btn green" id="submit_modify_order_btn" name="submit_modify_order_btn">提交修改</button>
                    </div>
      <!-- /.modal-content -->
        </div>
   <!-- /.modal-dialog -->

    </div>


<%--$("#eg1").attr("readOnly","readOnly");--%>