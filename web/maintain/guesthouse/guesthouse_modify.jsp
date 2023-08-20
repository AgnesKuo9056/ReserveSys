
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />

<div class="modal fade draggable-modal" id="user_modify_div" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title"> 用户信息 更新</h4>
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

                                </select>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn dark btn-outline" data-dismiss="modal">取消</button>
                <button type="button" class="btn green" id="submit_modify_btn" name="submit_modify_btn">确认修改</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
