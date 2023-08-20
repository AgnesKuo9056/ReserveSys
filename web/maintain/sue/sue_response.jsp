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
            <input type="hidden" id="page_id" name="page_id" value="sue_response"/>

            <div class="modal fade draggable-modal" id="sue_respons_div" tabindex="-1" role="basic" aria-hidden="true">

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title"> 回复/备注</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal"  role="form">
                                <input type="hidden"  id="sue_id" name="sue_id" class="form-control" >
                                <div class="form-body">
                                    <div class="form-group">
                                        <label>Textarea</label>
                                        <textarea class="form-control" id="note" rows="3"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Textarea</label>
                                        <textarea class="form-control" id="response" rows="5"></textarea>
                                    </div>

                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn dark btn-outline" data-dismiss="modal">取消</button>
                            <button type="button" class="btn green" id="resp_button" name="resp_button">确认添加</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>



            <!--页面头更改结束====================================================================================================================-->


<!--END script frame home/frame/frame_javascript-->
