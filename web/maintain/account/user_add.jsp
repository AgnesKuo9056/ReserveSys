<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/11/23
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />

            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="user_add"/>

            <div class="modal fade draggable-modal" id="user_add_div" tabindex="-1" role="basic" aria-hidden="true">

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title"> 用户信息 添加</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">ID</label>
                                        <div class="col-md-9">
                                            <input type="text" id="user_id" name="user_id" class="form-control" placeholder="Enter text">
                                            <span class="help-block"> 请输入用户ID </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">密码 </label>
                                        <div class="col-md-9">
                                            <input type="text"  id="password" name="password" class="form-control" placeholder="Enter text">
                                            <span class="help-block"> 请输入用户登陆密码 </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">姓名</label>
                                        <div class="col-md-9">
                                            <input type="text" id="user_name" name="user_name" class="form-control" placeholder="Enter text">
                                            <span class="help-block"> 请输入用户姓名 </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">邮箱</label>
                                        <div class="col-md-9">
                                            <input type="text"  id="mail" name="mail" class="form-control" placeholder="Enter text">
                                            <span class="help-block">  请输入用户邮箱</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">联系方式 </label>
                                        <div class="col-md-9">
                                            <input type="text"  id="phone_number" name="phone_number" class="form-control" placeholder="Enter text">
                                            <span class="help-block"> 请输入用户联系方式 </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">用户权限</label>
                                        <div class="col-md-9" id="option_div">
                                            <select  id="user_role" name="user_role" class="form-control">
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                                <option>Option 3</option>
                                                <option>Option 4</option>
                                                <option>Option 5</option>
                                            </select>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn dark btn-outline" data-dismiss="modal">取消</button>
                            <button type="button" class="btn green" id="add_button" name="add_button">确认添加</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>



            <!--页面头更改结束====================================================================================================================-->


<!--END script frame home/frame/frame_javascript-->
