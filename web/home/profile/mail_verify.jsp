<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

-------------------------------------------------

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />

<div class="modal fade draggable-modal" id="user_profile_mailverify_div" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">邮箱验证</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <tr height="35px">
                        <div class="form-group">
                            <td >
                            <label class="col-md-3 control-label">收件邮箱</label>
                            </td>
                            <td>
                            <div class="col-md-9">
                                <input type="text" id="verify_email" name="verify_email" class="form-control" placeholder="输入邮箱">
                                <button id="btnGetVcode" style="cursor:pointer">获取验证码</button>
                            </div>
                            </td>
                            <td>

                            </td>
                        </div>
                        </tr>
                        <tr height="35px">
                            <div class="form-group">
                                <td >
                                    <label class="col-md-3 control-label">验证码 :</label>
                                </td>
                                <td>
                                    <div class="col-md-9">
                                        <input type="text" id="vcode" name="vcode" class="form-control" placeholder="输入验证码">
                                    </div>
                                </td>
                                <td >
                                    <div id="message">

                                    </div>
                                </td>
                            </div>
                        </tr>
                        <a target="_self">
                            <button type="button" class="btn green" id="btnVerify" name="btnVerify" style="cursor:pointer">验证</button></a>
                    </div>
                </form>
            </div>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
