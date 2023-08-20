<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/12/08
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="guesthouse.entitiy.Guesthouse" %>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>民宿旅游管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=maintain.device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <%@include file="../../home/frame/frame_map_style.jsp"%>
    <%@include file="../../home/frame/frame_style.jsp"%>

    <link rel="shortcut icon" href="../../favicon.ico" />
</head>
<style>
    #asd {
        margin-top:45px;
        margin-left: 50px;
        position:absolute;
        clear: both;
    }
    /*选中的li悬浮变色*/
    .boxdel:after {
        content: "";
        background:rgba(0,0,0,0.4);
        z-index:1;
        -webkit-transition: 0.2s;
        -moz-transition: 0.2s;
        -ms-transition: 0.2s;
        -o-transition: 0.2s;
        transition: 0.2s;
        position: absolute;
        top:1px;
        right:9.2px;
        width: 92px;
        height: 92px;
        opacity: 0;
        filter: alpha(opacity=0);
    }
    .boxdel:hover:after {
        opacity: 100;
        filter: alpha(opacity=0);
        cursor: pointer;
    }
    .boxdel:before {
        content: "";
        display: inline-block;
        background-image: url("../../assets/global/img/gh_pic/delete.png");
        background-size:cover;
        float: right;
        position: absolute;
        z-index: 5;
        top:31px;
        right: 45px;
        width: 1.5rem;
        height: 1.5rem;
        opacity: 0;
        filter: alpha(opacity=0);
    }
    .boxdel:hover:before {
        opacity: 100;
        filter: alpha(opacity=0);
        cursor: pointer;
    }
    /*end*/
    #img-box li {
        display: inline-block;
        position: relative;
    }
    #img-box-two li {
        display: inline-block;
        position: relative;
    }
</style>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">

<!-- BEGIN HEADER -->
<%@include file="../../home/frame/frame_header.jsp"%>
<!-- END HEADER -->
<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"> </div>
<!-- END HEADER & CONTENT DIVIDER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <%@include file="../../home/frame/frame_left_siderbar.jsp"%>
    <!-- END SIDEBAR -->
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
            <!-- BEGIN PAGE HEADER-->
            <!-- BEGIN THEME PANEL -->

            <!-- END THEME PANEL -->
            <h3 class="page-title">民宿管理
                <small>民宿信息管理</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="index.html"> 管理 </a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <a href="#">添加民宿</a>
                        <i class="fa fa-angle-right"></i>
                    </li>

                </ul>

            </div>

            <!--页面头更改开始====================================================================================================================-->
            <input type="hidden" id="page_id" name="page_id" value="guesthouse_add"/>
            <div class="row">
                <div class="col-md-12">

                    <div class="portlet light " id="form_wizard_1">
                        <div class="portlet-body form">
                            <form action="../../guesthouse_center_servlet_action?action=add_gh_record" class="form-horizontal" enctype="multipart/form-data" id="submit_form" method="POST">
                                <div class="form-wizard">
                                    <div class="form-body">
                                        <ul class="nav nav-pills nav-justified steps">
                                            <li>
                                                <a href="#tab1" data-toggle="tab" class="step">
                                                    <span class="number"> 1 </span>
                                                    <span class="desc">
                                                                <i class="fa fa-check"></i> 基本民宿信息 </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#tab2" data-toggle="tab" class="step">
                                                    <span class="number"> 2 </span>
                                                    <span class="desc">
                                                                <i class="fa fa-check"></i> 房型 </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#tab4" data-toggle="tab" class="step">
                                                    <span class="number"> 3 </span>
                                                    <span class="desc">
                                                                <i class="fa fa-check"></i> 确认表单 </span>
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="bar" class="progress progress-striped" role="progressbar">
                                            <div class="progress-bar progress-bar-success"> </div>
                                        </div>
                                        <div class="tab-content">
                                            <div class="alert alert-danger display-none">
                                                <button class="close" data-dismiss="alert"></button> 列表中有信息错误请检查并修改 </div>
                                            <div class="alert alert-success display-none">
                                                <button class="close" data-dismiss="alert"></button> 表单填写格式正确! </div>
                                            <div class="tab-pane active" id="tab1">
                                                <h3 class="block">请完整填写民宿信息</h3>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">民宿名称
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control" name="username" />
                                                        <span class="help-block"> 帮民宿起个名吧! 支持5-15字符</span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">联系电话
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control" name="phone" />
                                                        <span class="help-block"> 留下民宿联系方式 </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">民宿简介:</label>
                                                    <div class="col-md-4">
                                                        <textarea class="form-control" rows="3" name="remarks_gh"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group" style="position: relative">
                                                    <label class="control-label col-md-3">民宿地址:
                                                        <span class="required"> * </span>
                                                    </label>
                                                  <div class="col-md-4" style="position: absolute;top: 0;left: 325px;width: 200px;">
                                                      <input id='tipinput'  onblur="Page.geoCode()" type="text"  class="form-control" name="address">
                                                  </div>
                                                </div>
                                                <div class="form-group " style="position: relative;width: 550px; height: 400px;left: 250px;">
                                                    <div id="asd" style="position: absolute;width: 550px; height: 300px;"></div>
                                                </div>

                                                <div class="form-group ">
                                                    <label class="control-label col-md-3">民宿外观
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-9">
                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
                                                            <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"> </div>
                                                            <div>
                                                            <span class="btn red btn-outline btn-file">
                                                                <span class="fileinput-new"> 选取民宿照片 </span>
                                                                <span class="fileinput-exists"> 换一张 </span>
                                                                <input type="file" name="gh_pic"> </span>
                                                                <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix margin-top-10">
                                                            <span class="label label-success">温馨提示!</span> <small>请选择适宜照片避免审核失败</small></div>
                                                    </div>
                                                    <%--                                        <div id="previewImg" style="clear: both;"></div>--%>
                                                </div>

                                            </div>

                                            <div class="tab-pane" id="tab2">
                                                <h3 class="block">添加你的民宿吧</h3>

                                                <div class="form-group">
                                                    <label class="control-label col-md-3">房名
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control" name="fullname" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">房间数量
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control" name="number" />
                                                        <span class="help-block">  </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">可入住人数
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-4">
                                                        <select name="cus_num" id="cus_num" class="form-control">
                                                            <option value="0"></option>
                                                            <option value="1">单人</option>
                                                            <option value="2">双人</option>
                                                            <option value="3">三人</option>
                                                            <option value="4">四人</option>
                                                            <option value="5">五人</option>
                                                            <option value="6">六人及以上</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">定价
<%--                                                        <span class="required"> * </span>--%>
                                                    </label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control" name="price_number" />
                                                        <span class="help-block"> 房间/晚价格 </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">房间介绍:</label>
                                                    <div class="col-md-4">
                                                        <textarea class="form-control" rows="3" name="remarks_room"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">标签tags :</label>
                                                    <div class="col-md-9">
                                                        <input type="text" value="靠窗,海景" data-role="tagsinput"> </div>
                                                    <span class="help-block"> 为这个房间添加标签吧\-?-/ </span>
                                                </div>
                                                <div class="form-group ">
                                                    <label class="control-label col-md-3">民宿照片
                                                        <span class="required"> * </span>
                                                    </label>
                                                    <div class="col-md-9">
                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
                                                            <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"> </div>
                                                            <div>
                                                            <span class="btn red btn-outline btn-file">
                                                                <span class="fileinput-new"> 选取民宿照片 </span>
                                                                <span class="fileinput-exists"> 换一张 </span>
                                                                <input type="file" name="room_pic"> </span>
                                                                <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix margin-top-10">
                                                            <span class="label label-success">温馨提示!</span> <small>请选择适宜照片避免审核失败</small></div>
                                                    </div>
                                                    <%--                                        <div id="previewImg" style="clear: both;"></div>--%>
                                                </div>
                                                    <%--                                        <div id="previewImg" style="clear: both;"></div>--%>
                                                 <div class="col-md-8">
                                                        <table class="table table-striped table-bordered table-hover datatable" id="show_add_room">
                                                            <thead>
                                                            <tr>
                                                                <%--                            <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#user_list .checkboxes" /></th>--%>
                                                                <th>民宿名称</th>
                                                                <th>房间数量</th>
                                                                <th>入住人数</th>
                                                                <th>定价</th>
                                                                <th>介绍</th>
                                                                <th>标签</th>
                                                                <th>照片</th>
                                                                <th>操作</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>


                                                <div class="help-block" id="room_error" style="display:none;"> 房间名称 数量 定价 入住人数不得未空</div>
                                                <button class="btn btn-circle default blue-stripe"  onclick="javascript:Page.appendroom()"  type="button"><i class="fa fa-edit"></i>添加房型</button>
<%--                                                 <div class="form-group ">
                                                <label class="control-label col-md-3">Image Upload #1</label>
                                                <div class="col-md-9">
                                                    <div class="fileinput fileinput-new" data-provides="fileinput">
                                                        <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"> </div>
                                                        <div>
                                                            <span class="btn red btn-outline btn-file">
                                                                <span class="fileinput-new"> Select image </span>
                                                                <span class="fileinput-exists"> Change </span>
                                                                <input type="file" name="..."> </span>
                                                            <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> Remove </a>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix margin-top-10">
                                                        <span class="label label-success">NOTE!</span> Image preview only works in IE10+, FF3.6+, Safari6.0+, Chrome6.0+ and Opera11.1+. In older browsers the filename is shown instead. </div>
                                                </div>
                                            </div>--%>
                                            </div>

                                            <div class="tab-pane" id="tab4">
                                                <h3 class="block">请确认填写信息</h3>
                                                <h4 class="form-section">民宿基本信息</h4>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">民宿名称:</label>
                                                    <div class="col-md-4">
                                                        <p class="form-control-static" data-display="username"> </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">民宿联络电话:</label>
                                                    <div class="col-md-4">
                                                        <p class="form-control-static" data-display="phone"> </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">民宿简介:</label>
                                                    <div class="col-md-4">
                                                        <p class="form-control-static" data-display="remarks_gh"> </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">地址:</label>
                                                    <div class="col-md-4">
                                                        <p class="form-control-static" data-display="address"> </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">环境照片:</label>
                                                    <div class="col-md-4" id="show_result_pic">
                                                        <p class="form-control-static" data-display="gh"> </p>
                                                    </div>
                                                </div>
                                                <h4 class="form-section">房型</h4>
                                                <table class="table table-striped table-bordered table-hover datatable" id="show_confirm_room">
                                                    <thead>
                                                    <tr>
                                                        <%--                            <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#user_list .checkboxes" /></th>--%>
                                                        <th>民宿名称</th>
                                                        <th>房间数量</th>
                                                        <th>入住人数</th>
                                                        <th>定价</th>
                                                        <th>介绍</th>
                                                        <th>标签</th>
                                                        <th>照片</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <div class="row">
                                            <div class="col-md-offset-3 col-md-9">
                                                <a href="javascript:;" class="btn default button-previous">
                                                    <i class="fa fa-angle-left"></i> Back </a>
                                                <a href="javascript:;" class="btn btn-outline green button-next"> Continue
                                                    <i class="fa fa-angle-right"></i>
                                                </a>
                                                <a href="javascript:;"  onclick="Page.onSubmit_GH_add()" class="btn green button-submit"> Submit
                                                    <i class="fa fa-check"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>





            <!--页面头更改结束====================================================================================================================-->
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
</div>
<!-- BEGIN QUICK SIDEBAR -->
<%@include file="../../home/frame/frame_javascript.jsp"%>
<%@include file="../../home/frame/frame_quick_siderbar.jsp"%>
<!-- END QUICK SIDEBAR -->


<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../../home/frame/frame_footer.jsp"%>
<!-- END FOOTER -->

<!-- BEGIN script frame home/frame/frame_javascript-->
<script>
    //-----------当选中图片时显示图片----------------

    function seletinput() {
        $("#uploadfile").click();
    }
    function seletinputteo() {
        $("#uploadfile_two").click();
    }
    var input=document.getElementById("uploadfile");
    var div;
    var filelist=[];
    var formdata=new FormData();

    var inputtwo=document.getElementById("uploadfile_two");
    var divtwo;
    var filelisttwo=[];
    var formdatatwo=new FormData();


    console.log(formdata);
    // 当用户上传时触发事件
    input.onchange=function(){
        readFile(this);
    }
    inputtwo.onchange=function(){
        readFileTwo(this);
    }

    //处理图片并添加到dom中的函数
    var readFile=function(obj){
        // 获取input里面的文件组
        var fileList=obj.files;
        console.log(fileList);
        //对文件组进行遍历，可以到控制台打印出fileList去看看
        for(var i=0;i<fileList.length;i++){
            var reader= new FileReader();
            reader.readAsDataURL(fileList[i]);
            reader.filename = fileList[i].name;
            console.log(fileList[i]);
            filelist.push(fileList[i]);
            // 当文件读取成功时执行的函数
            reader.onload=function(e){
                // for(var i=0;i<obj.files.length;i++){
                console.log(obj.files.length)
                console.log(this.filename);
                var flies = this.filename.split(".")[1];
                console.log(flies);
                if(flies=="jpg"||flies=="jpeg"||flies=="gif"||flies=="png"){
                    $(obj).val("");
                    $('#img-box').append('<li class=\"boxdel\" style="background-image:url(../../assets/global/img/gh_pic/delete.png)"><img style="width: 100px;height:100px ;vertical-align: top;"\n src="'+ this.result +'" alt="'+ this.name +'" class="layui-upload-img"></li>')
                }else if(flies=="mp4") {
                    $('#img-box').append('<li class="boxdel"><video width="92" height="92" src="'+ this.result +'" type="video/mp4"> </video></li>')
                } else {
                    return layer.msg('只支持jpg和mp4格式');
                }
                // }

            }
        }

    }

    //处理图片并添加到dom中的函数
    var readFileTwo=function(obj){
        // 获取input里面的文件组
        var filelisttwo=obj.files;
        console.log(filelisttwo);
        //对文件组进行遍历，可以到控制台打印出fileList去看看
        for(var i=0;i<filelisttwo.length;i++){
            var reader= new FileReader();
            reader.readAsDataURL(filelisttwo[i]);
            reader.filename = filelisttwo[i].name;
            console.log(filelisttwo[i]);
            filelist.push(filelisttwo[i]);
            // 当文件读取成功时执行的函数
            reader.onload=function(e){
                // for(var i=0;i<obj.files.length;i++){
                console.log(obj.files.length)
                console.log(this.filename);
                var flies = this.filename.split(".")[1];
                console.log(flies);
                if(flies=="jpg"||flies=="jpeg"||flies=="gif"||flies=="png"){
                    $(obj).val("");
                    $('#img-box-two').append('<li class="boxdel" style="background-image:url(../../assets/global/img/gh_pic/delete.png)"><img style="width: 100px;height:100px ;vertical-align: top;"\n src="'+ this.result +'" alt="'+ this.name +'" class="layui-upload-img"></li>')
                }else if(flies=="mp4") {
                    $('#img-box-two').append('<li class="boxdel"><video width="92" height="92" src="'+ this.result +'" type="video/mp4"> </video></li>')
                } else {
                    return layer.msg('只支持jpg和mp4格式');
                }
                // }

            }
        }

    }

    //----------删除选中的图片-----------
    $('#img-box').on('click','li',function () {
        $(this).toggleClass("delli");
        var delindex = $(this).index();
        filelist.pop(filelist[delindex]);
        console.log($(this).index())
        $('.delli').remove()
    })
    $('#img-box-two').on('click','li',function () {
        $(this).toggleClass("delli");
        var delindex = $(this).index();
        filelist.pop(filelist[delindex]);
        console.log($(this).index())
        $('.delli').remove()
    })

    //--------------end-------------

</script>
<script src="guesthouse.js"></script>

<!--END script frame home/frame/frame_javascript-->
</body>
</html>

<!--END script frame home/frame/frame_javascript-->
