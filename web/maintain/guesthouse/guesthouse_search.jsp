<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />

<div class="modal fade draggable-modal" id="user_search_div" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title"> 用户信息 查询</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">ID</label>
                            <div class="col-md-9">
                                <input type="text" id="user_search_id" name="user_search_id" class="form-control" placeholder="Enter text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">姓名</label>
                            <div class="col-md-9">
                                <input type="text" id="user_search_name" name="user_search_name" class="form-control" placeholder="Enter text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">邮箱</label>
                            <div class="col-md-9">
                                <input type="text"  id="user_search_mail" name="user_search_mail" class="form-control" placeholder="Enter text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">联系方式 </label>
                            <div class="col-md-9">
                                <input type="text"  id="user_search_phone_number" name="user_search_phone_number" class="form-control" placeholder="Enter text">
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
                <button id="search_btn" name="search_btn" class="btn btn-info" >搜索用户信息</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
