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
<style>
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

</style>
            <!--页面头更改开始====================================================================================================================-->


            <div class="modal fade draggable-modal" id="store_apply_model" tabindex="-1"   role="basic" aria-hidden="true">
                 <div class="modal-dialog" style="width: 80% ;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title"> 认证成为商家</h4>请完整填写以下栏位
                        </div>
                        <div class="modal-body">
                            <form id="tobestore" action="../../user_center_servlet_action?action=upload_store_apply" class="form-horizontal" method="POST" enctype="multipart/form-data">
                                <div class="form-body">
                                    <input type="hidden" id="user_id" name="user_id" value="${User.user_id}"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">银行卡号:
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-4">
                                            <input name="creditcard" type="text" class="form-control" />
                                            <span class="help-block"> e.g: 5500 0000 0000 0004 </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">上传凭证:
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-4">
                                            <input name="licence"    type="file" class="form-control" />

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">上传凭证(包括 营业执照 人像照片 身份证件等):
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-4">
                                            <!-- 图片上传 -->
                                            <div class="from-text">
                                                <a href="javascript:;" class="file"> <img  onclick="seletinput()" src="../../assets/global/img/gh_pic/uploadfile.jpg" alt="我要上传 :" />
                                                    <input type="file" name="multi" style="display: none" value="选择文件" id="uploadfile" accept=".jpeg,.png,.gif,.jpg" >
                                                </a>
                                                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                                    预览图：
                                                    <ul class="layui-upload-list" id="img-box"></ul>
                                                </blockquote>
                                            </div>

<%--&lt;%&ndash;                                            <div class="pc">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <a onclick="input_file()"><span class="btn"></span><img src="../../assets/global/img/gh_pic/uploadfile.jpg" alt="我要上传 :" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                </a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <input type="file" style="display: none" id="licences" name="files[]" accept="image/*"  multiple="multiple "/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <span class="help-block"> 仅支持 jpeg jpg png gif 格式图片 </span>&ndash;%&gt;--%>

<%--&lt;%&ndash;                                            <ul class="content-img-list">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <!-- <li class="content-img-list-item"><img src="https://www.baidu.com/img/bd_logo1.png" alt=""><a class="delete-btn"><i class="ico-delete"></i></a></li> -->&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <div class="file">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <i class="icon-plus"></i>上传证明，支持jpg/png/jpeg/gif<input type="file"  style="opacity: 0" name="file" accept="image/*" id="upload" >&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </div>&ndash;%&gt;--%>

<%--                                        </div>--%>
<%--&lt;%&ndash;                                        <div id="previewImg" style="clear: both;"></div>&ndash;%&gt;--%>
<%--                                    </div>--%>
                                </div>
                            </form>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn dark btn-outline" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn green"  id="apply_button" name="apply_button">送出申请</button>
                        </div>

                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
<script>
    //-----------当选中图片时显示图片----------------
    function seletinput() {
        $("#uploadfile").click();
    }
    var input=document.getElementById("uploadfile");
    var submitbtn=document.getElementById("apply_button");
    var div;
    var filelist=[];
    var form = document.getElementById("tobestore");
    var formdata=new FormData(form);

    console.log(formdata);
    // 当用户上传时触发事件
    input.onchange=function(){
        readFile(this);
    }
    submitbtn.onclick=function () {
        // 从 FormData 对象中删除名为 multi 的键值对
        formdata.delete('multi');
        console.log( new FormData(form));
        fetch("../../user_center_servlet_action?action=upload_store_apply", {
            method: 'POST',
            body: new FormData(form)
        }).then(function(response) {
            // 处理响应
            $(".profile-userbuttons").load(location.href + " .profile-userbuttons");
            $("#store_apply_model").modal("hide");
            $("#store_apply_model").hide();
        });


    }

    //处理图片并添加到dom中的函数
    var readFile=function(obj){
        // 获取input里面的文件组
        var fileList=obj.files;
        console.log(fileList);
        //对文件组进行遍历，可以到控制台打印出fileList去看看
        for(var i=0;i<fileList.length;i++){
            formdata.append("file", fileList[i]);
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
                    $('#img-box').append('<li class="boxdel" style="background-image:url("../../assets/global/img/gh_pic/delete.png"); "><img style="width: 100px;height:100px ;vertical-align: top;"\n src="'+ this.result +'" alt="'+ this.name +'" class="layui-upload-img"></li>')
                }else if(flies=="mp4") {
                    $('#img-box').append('<li class="boxdel"><video width="92" height="92" src="'+ this.result +'" type="video/mp4"> </video></li>')
                } else {
                    return layer.msg('只支持jpg和mp4格式');
                }
                // }

            }
        }
        for (var pair of formdata.entries()) {
            console.log(pair[0] + ': ' + pair[1]);
        }
    }


    //----------删除选中的图片-----------
    $('#img-box').on('click','li',function () {
        $(this).toggleClass("delli");
        var delindex = $(this).index();
        filelist.pop(filelist[delindex]);
        var values = formdata.getAll('file');
        formdata.delete('file');
        values.splice(delindex, 1);
        for (var i = 0; i < values.length; i++) {
            formdata.append('file', values[i]);
        }

        console.log($(this).index())
        $('.delli').remove()
        for (var pair of formdata.entries()) {
            console.log(pair[0] + ': ' + pair[1]);
        }
    })

    //--------------end-------------

</script>


<%--<script>--%>
<%--    // $('#upload').on('change', function(){--%>
<%--    //     var imgFiles = $(this)[0].files--%>
<%--    //     for (i=0;i<imgFiles.length;i++){--%>
<%--    //         filePath = imgFiles[i].name--%>
<%--    //         fileFormat = filePath.split('.')[1].toLowerCase()--%>
<%--    //         src = window.URL.createObjectURL(imgFiles[i])--%>
<%--    //         if( !fileFormat.match(/png|jpg|jpeg|gif/) ) {--%>
<%--    //             alert('上传错误,文件格式必须为：png/jpg/jpeg')--%>
<%--    //             return--%>
<%--    //         }--%>
<%--    //         var preview = document.getElementById("previewImg")--%>
<%--    //         var img = document.createElement('img')--%>
<%--    //         img.width = 100--%>
<%--    //         img.height = 100--%>
<%--    //         img.src = src--%>
<%--    //         preview.appendChild(img)--%>
<%--    //     }--%>
<%--    // })--%>
<%--    var imgName = new Array();--%>
<%--    var imgSrc= new Array();--%>
<%--    var imgFile= new Array();--%>


<%--    $('#upload').on('change', function(){--%>
<%--        if(imgSrc.length==4){--%>
<%--            return alert("最多只能上传4张图片");--%>
<%--        }--%>
<%--        var imgSize = this.files[0].size;  //b--%>
<%--        if(imgSize>1024*1024*1){//1M--%>
<%--            return alert("上传图片不能超过1M");--%>
<%--        }--%>
<%--        if(this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif' &&this.files[0].type != 'image/jpg' ){--%>
<%--            return alert("图片上传格式不正确");--%>
<%--        }--%>
<%--        var fileList = $(this)[0].files;--%>
<%--        for(var i = 0; i < fileList.length; i++) {--%>
<%--            var imgSrcI = getObjectURL(fileList[i]);--%>
<%--            imgName.push(fileList[i].name);--%>
<%--            imgSrc.push(imgSrcI);--%>
<%--            imgFile.push(fileList[i]);--%>
<%--        }--%>



<%--    })--%>
<%--    function getObjectURL(file) {--%>
<%--        var url = null ;--%>
<%--        if (window.createObjectURL!=undefined) { // basic--%>
<%--            url = window.createObjectURL(file) ;--%>
<%--        } else if (window.URL!=undefined) { // mozilla(firefox)--%>
<%--            url = window.URL.createObjectURL(file) ;--%>
<%--        } else if (window.webkitURL!=undefined) { // webkit or chrome--%>
<%--            url = window.webkitURL.createObjectURL(file) ;--%>
<%--        }--%>
<%--        return url ;--%>
<%--    }--%>
<%--    $('.content-img-list').on('mouseover','.content-img-list-item',function(){--%>
<%--        $(this).children('a').removeClass('hide');--%>
<%--    });--%>
<%--    $('.content-img-list').on('mouseleave','.content-img-list-item',function(){--%>
<%--        $(this).children('a').addClass('hide');--%>
<%--    });--%>
<%--    $(".content-img-list").on("click",'.content-img-list-item a',function(){--%>
<%--        var index = $(this).attr("index");--%>
<%--        imgSrc.splice(index, 1);--%>
<%--        imgFile.splice(index, 1);--%>
<%--        imgName.splice(index, 1);--%>
<%--        var boxId = ".content-img-list";--%>
<%--        addNewContent(boxId);--%>
<%--        if(imgSrc.length<4){//显示上传按钮--%>
<%--            $('.content-img .file').show();--%>
<%--        }--%>
<%--    });--%>
<%--    function addNewContent(obj) {--%>
<%--        $(obj).html("");--%>
<%--        for(var a = 0; a < imgSrc.length; a++) {--%>
<%--            var oldBox = $(obj).html();--%>
<%--            $(obj).html(oldBox + '<li class="content-img-list-item"><img src="'+imgSrc[a]+'" alt=""><a index="'+a+'" class="hide delete-btn"><i class="icon-trash"></i></a></li>');--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>

            <!--页面头更改结束====================================================================================================================-->
<%--
 <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                                        <div class="row fileupload-buttonbar">
                                            <div class="col-lg-7">
                                                <!-- The fileinput-button span is used to style the file input field as button -->
                                                <span class="btn green fileinput-button">
                                            <i class="fa fa-plus"></i>
                                            <span> Add files... </span>
                                            <input type="file" name="files[]" multiple=""/> </span>
                                                <button type="submit" class="btn blue start">
                                                    <i class="fa fa-upload"></i>
                                                    <span> 开始上传 </span>
                                                </button>
                                                <button type="reset" class="btn warning cancel">
                                                    <i class="fa fa-ban-circle"></i>
                                                    <span> 取消上传 </span>
                                                </button>
                                                <button type="button" class="btn red delete">
                                                    <i class="fa fa-trash"></i>
                                                    <span> 删除 </span>
                                                </button>
                                                <input type="checkbox" class="toggle"/>
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


--%>

/<!--END script frame home/frame/frame_javascript-->
