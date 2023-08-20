<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/11/23
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8" />
            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="user_download"/>
<div class="modal fade draggable-modal" id="record_download_div" name="record_download_div" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">下载链接 </h4>
            </div>
            <div class="modal-body">
                <span>
                    <a href="" id="download_url" name="download_url"> 点击下载</a>
                </span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn dark btn-outline" data-dismiss="modal">Close</button>
                <button type="button" class="btn green">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
            <!--页面头更改结束====================================================================================================================-->



<!--END script frame home/frame/frame_javascript-->
</body>

</html>