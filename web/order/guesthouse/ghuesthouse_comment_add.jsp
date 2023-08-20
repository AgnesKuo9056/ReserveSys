<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />
<%--aria-hidden="true" --%>
<div class="modal fade draggable-modal" id="comment_add_modal" tabindex="-1"   role="basic" aria-hidden="true">
    <div class="modal-dialog" style="width: 80% ;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title"> 添加评论 </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 control-label">标题:</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control maxlength-handler" readonly="readonly" name="product[meta_title]" maxlength="100" id="comment_title">
                                <span class="help-block"> max 100 chars </span>
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="col-md-2 control-label">评分:</label>
                            <div class="col-md-10 input-inline ">
                                <input type="text" id="total" class="form-control maxlength-handler input-xsmall"  value="10"   maxlength="2" name="number">
                                <span class="help-block"> /10</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">请写下你的评论与反馈:</label>
                            <div class="col-md-10">
                                <textarea class="form-control maxlength-handler" rows="8" id="comment_context" placeholder="感谢您选择我们的民宿，我们期待您的评论和反馈，以便我们在未来为您提供更好的服务。" name="product[meta_description]" maxlength="255"></textarea>
                                <span class="help-block"> 至多255字 </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-10" style="margin-left:20px;">

                                <div class="md-checkbox">
                                    <input type="checkbox" id="anonymousyes" class="md-check">
                                    <label for="anonymousyes">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 匿名评论 </label>
                                </div>
                            </div>
                        </div>
                        <div class=" form-group input-inline" style="margin-left:20px;">
                            验证码：<input type="text" id="vcode" name="vcode"/>
<%--                            <img alt="验证码" id="imagecode" src="<%=request.getContextPath() %>/servlet/servletImage"><!--src的方式实现链接到servletImage-->--%>
                            <div  CLASS="input-inline " id="code" name="code" ></div>
                            <a href="javascript:Page.onreGetVcode()">换一组!</a>


                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn dark btn-outline" data-dismiss="modal">忍心离开>-<</button>
                <button type="button" class="btn green" id="submit_comment_btn" name="submit_comment_btn">送出</button>
            </div>
        </div>
        <!-- /.modal-content -->
        </div>
    <!-- /.modal-dialog -->
</div>


<%--          <div class="col-md-12">
                    <div class="m-heading-1 border-green m-bordered">
                        <h3>jQuery Validation Plugin</h3>
                        <p> File Upload widget with multiple file selection, drag&amp;drop support, progress bars and preview images for jQuery.
                            <br> Supports cross-domain, chunked and resumable file uploads and client-side image resizing.
                            <br> Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads. </p>
                        <p> For more info please check out
                            <a class="btn red btn-outline" href="https://github.com/blueimp/jQuery-File-Upload" target="_blank">the official documentation</a>
                        </p>
                    </div>
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">注意!</h3>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li> 上传文件的最大限制为
                                    <strong>5 MB</strong> (default file size is unlimited). </li>
                                <li> 支持以下格式文件上传 (
                                    <strong>JPG, GIF, PNG</strong>) are allowed in this demo (by default there is no file type restriction). </li>
                                <li> Uploaded files will be deleted automatically after
                                    <strong>5 minutes</strong>  </li>
                            </ul>
                        </div>
                    </div>
                    <form id="fileupload" action="../../comment_center_servlet_action?action=uploadpics" method="POST" enctype="multipart/form-data">
                        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                        <div class="row fileupload-buttonbar">
                            <div class="col-lg-7">
                                <!-- The fileinput-button span is used to style the file input field as button -->
                                <span class="btn green fileinput-button">
                                            <i class="fa fa-plus"></i>
                                            <span> Add files... </span>
                                            <input type="file" name="files[]" multiple=""> </span>
                                <button type="submit" class="btn blue start">
                                    <i class="fa fa-upload"></i>
                                    <span> Start upload </span>
                                </button>
                                <button type="reset" class="btn warning cancel">
                                    <i class="fa fa-ban-circle"></i>
                                    <span> Cancel upload </span>
                                </button>
                                <button type="button" class="btn red delete">
                                    <i class="fa fa-trash"></i>
                                    <span> Delete </span>
                                </button>
                                <input type="checkbox" class="toggle">
                                <!-- The global file processing state -->
                                <span class="fileupload-process"> </span>
                            </div>
                            <!-- The global progress information -->
                            <div class="col-lg-5 fileupload-progress fade">
                                <!-- The global progress bar -->
                                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                                    <div class="progress-bar progress-bar-success" style="width:0%;"> </div>
                                </div>
                                <!-- The extended global progress information -->
                                <div class="progress-extended"> &nbsp; </div>
                            </div>
                        </div>
                        <!-- The table listing the files available for upload/download -->
                        <table role="presentation" class="table table-striped clearfix">
                            <tbody class="files"> </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <!-- The blueimp Gallery widget -->
            <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
                <div class="slides"> </div>
                <h3 class="title"></h3>
                <a class="prev"> ‹ </a>
                <a class="next"> › </a>
                <a class="close white"> </a>
                <a class="play-pause"> </a>
                <ol class="indicator"> </ol>
            </div>
            <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
            <script id="template-upload" type="text/x-tmpl"> {% for (var i=0, file; file=o.files[i]; i++) { %}
                        <tr class="template-upload fade">
                            <td>
                                <span class="preview"></span>
                            </td>
                            <td>
                                <p class="name">{%=file.name%}</p>
                                <strong class="error text-danger label label-danger"></strong>
                            </td>
                            <td>
                                <p class="size">Processing...</p>
                                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
                                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                                </div>
                            </td>
                            <td> {% if (!i && !o.options.autoUpload) { %}
                                <button class="btn blue start" disabled>
                                    <i class="fa fa-upload"></i>
                                    <span>Start</span>
                                </button> {% } %} {% if (!i) { %}
                                <button class="btn red cancel">
                                    <i class="fa fa-ban"></i>
                                    <span>Cancel</span>
                                </button> {% } %} </td>
                        </tr> {% } %} </script>
            <!-- The template to display files available for download -->
            <script id="template-download" type="text/x-tmpl"> {% for (var i=0, file; file=o.files[i]; i++) { %}
                        <tr class="template-download fade">
                            <td>
                                <span class="preview"> {% if (file.thumbnailUrl) { %}
                                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery>
                                        <img src="{%=file.thumbnailUrl%}">
                                    </a> {% } %} </span>
                            </td>
                            <td>
                                <p class="name"> {% if (file.url) { %}
                                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl? 'data-gallery': ''%}>{%=file.name%}</a> {% } else { %}
                                    <span>{%=file.name%}</span> {% } %} </p> {% if (file.error) { %}
                                <div>
                                    <span class="label label-danger">Error</span> {%=file.error%}</div> {% } %} </td>
                            <td>
                                <span class="size">{%=o.formatFileSize(file.size)%}</span>
                            </td>
                            <td> {% if (file.deleteUrl) { %}
                                <button class="btn red delete btn-sm" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}" {% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}' {% } %}>
                                    <i class="fa fa-trash-o"></i>
                                    <span>Delete</span>
                                </button>
                                <input type="checkbox" name="delete" value="1" class="toggle"> {% } else { %}
                                <button class="btn yellow cancel btn-sm">
                                    <i class="fa fa-ban"></i>
                                    <span>Cancel</span>
                                </button> {% } %} </td>
                        </tr> {% } %} </script>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->--%>

<%--$("#eg1").attr("readOnly","readOnly");--%>

<%--<%--                        <div class="form-group">--%>
<%--                            <label class="control-label col-md-3">评分</label>--%>
<%--                            <div class="col-md-9">--%>
<%--                                <div class="margin-bottom-10">--%>
<%--                                    <select class="bs-select form-control input-small" data-style="btn-primary">--%>
<%--                                        <option>环境</option>--%>
<%--                                        <option value="0.8">4</option>--%>
<%--                                        <option value="0.6">3</option>--%>
<%--                                        <option value="0.4">2</option>--%>
<%--                                        <option value="0.2">1</option>--%>
<%--                                        <option value="0">0</option>--%>
<%--                                    </select>--%>
<%--                                    <select class="bs-select form-control input-small" data-style="btn-success">--%>
<%--                                        <option selected="selected">服务</option>--%>
<%--                                        <option value="0.8">4</option>--%>
<%--                                        <option value="0.6">3</option>--%>
<%--                                        <option value="0.4">2</option>--%>
<%--                                        <option value="0.2">1</option>--%>
<%--                                        <option value="0">0</option>--%>
<%--                                    </select>--%>
<%--                                    <select class="bs-select form-control input-small" data-style="btn-info">--%>
<%--                                        <option selected="selected">卫生</option>--%>
<%--                                        <option value="0.8">4</option>--%>
<%--                                        <option value="0.6">3</option>--%>
<%--                                        <option value="0.4">2</option>--%>
<%--                                        <option value="0.2">1</option>--%>
<%--                                        <option value="0">0</option>--%>
<%--                                    </select>--%>
<%--                                    <select class="bs-select form-control input-small" data-style="btn-warning">--%>
<%--                                        <option selected="selected">总体</option>--%>
<%--                                        <option value="0.8">4</option>--%>
<%--                                        <option value="0.6">3</option>--%>
<%--                                        <option value="0.4">2</option>--%>
<%--                                        <option value="0.2">1</option>--%>
<%--                                        <option value="0">0</option>--%>
<%--                                    </select>--%>
<%--                                    <select class="bs-select form-control input-small" data-style="btn-danger">--%>
<%--                                        <option selected="selected">会再来</option>--%>
<%--                                        <option value="0.8">4</option>--%>
<%--                                        <option value="0.6">3</option>--%>
<%--                                        <option value="0.4">2</option>--%>
<%--                                        <option value="0.2">1</option>--%>
<%--                                        <option value="0">01</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <span class="help-block">默认满分</span>--%>
<%--                        </div>--%>--%>

<%--<script src="../../order/guesthouse/GHOrder.js"></script>--%>
