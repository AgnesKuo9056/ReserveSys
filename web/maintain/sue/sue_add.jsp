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
            <div class="modal fade draggable-modal" id="sue_add_div" tabindex="-1" role="basic" aria-hidden="true">

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 class="modal-title uppercase">我要投诉</h3>
                            <div class="c-line-left bg-dark"></div>
                        </div>

                        <div class="modal-body">
                            <div class="col-md-15">
                                <div class="c-contact">
                                    <div class="c-content-title-1">
<%--                                        <h3 class="uppercase">Keep in touch</h3>--%>
<%--                                        <div class="c-line-left bg-dark"></div>--%>
                                        <p class="c-font-lowercase"><small>尊敬的用户，感谢您对我们平台的支持。我们非常重视您的意见和建议，以便我们能够不断改进和提高我们的服务质量。为了让我们更好地了解您的反馈，我们设置了以下投诉类型，请您根据实际情况选择相应类型,尊敬的用户，感谢您对我们平台的支持。我们非常重视您的意见和建议，以便我们能够不断改进和提高我们的服务质量。为了让我们更好地了解您的反馈，我们设置了以下投诉类型，请您根据实际情况选择相应类型.</small></p>
                                    </div>
                                    <form  class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">投诉类型
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-4">
                                                <select class="form-control"  style="width:350px;" id="sue_type" name="sue_type">
                                                    <option value="">请选择...</option>
                                                    <option value="0">服务态度</option>
                                                    <option value="1">卫生问题</option>
                                                    <option value="2">设施问题</option>
                                                    <option value="3">安全问题</option>
                                                    <option value="4">费用问题</option>
                                                    <option value="5">广告虚假宣传</option>
                                                    <option value="6">其他问题</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3">投诉内容:</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control input-md" style="width:350px;"  id="sue_context" placeholder="请填写您的建议与投诉内容" rows="9" name="sue_context"></textarea>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn dark btn-outline" data-dismiss="modal">取消</button>
                            <button type="button" class="btn green" id="add_sue_button" name="add_sue_button">确认添加</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>



            <!--页面头更改结束====================================================================================================================-->


<!--END script frame home/frame/frame_javascript-->
