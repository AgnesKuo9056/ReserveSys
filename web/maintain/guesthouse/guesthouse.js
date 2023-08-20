var module="guesthouse";
var sub="center";
var statusnow = 1;
var roleoption =[] ;
var table;
var map;
/*================================================================================*/

jQuery(document).ready(function () {
    Page.init();

})
/* ================================================================================ */
//关于页面的控件生成等操作都放在Page里
var Page = function() {
    /*----------------------------------------入口函数  开始----------------------------------------*/

    var initPageControl=function(){
        pageId=$("#page_id").val();
        console.log("识别到page id了" );
        if(pageId=="gh_manage_list"){

            initGuesthouseList();
        }
        if(pageId=="guesthouse_add"){
            initmap();
          //  initGuesthouseAdd();
        }
        // if(pageId=="user_modify"){
        //     initUserModify();
        // }
        if(pageId=="print_table"){
            initPrintTable();
        }
    };
    /*----------------------------------------入口函数  结束----------------------------------------*/

    /*----------------------------------------业务函数  开始----------------------------------------*/
    /*------------------------------针对各个页面的入口  开始------------------------------*/
    var initGuesthouseList=function(){

       // initRoleoption();
        initGuesthouseListControlEvent();

        initGuesthouseRecordList();
      //  initRoleoption();

    }
    var initGuesthouseAdd=function(){
       // initRoleoption();
        initUserAddControlEvent();
       // initAddRecordView();

    }
    var initUserModify=function(){
        initUserModifyControlEvent();
        initUserRecordView();
    }
    initPrintTable=function () {
        initPrintTableView();
    }


    /*------------------------------针对各个页面的入口 结束------------------------------*/

    var initGuesthouseListControlEvent=function(){
        $("#help_button").click(function() {help();});
        $("#passed").click(function() {
            redrawDatatable(1);});
        $("#unpassed").click(function() {
            redrawDatatable(2);});
        $("#waitpassed").click(function() {
            redrawDatatable(0);});
        $('#add_gh').click(function() {onAddRecord();});
        $('#export_button').click(function() {onExport_Button();});
        $('#print_table').click(function() {onPrint_Table();});
        $('#user_modify_div #submit_modify_btn').click(function() {OnModifySubmit();});
        $('#user_search_div #search_btn').click(function() {submitSearchRecord();});
        $('#user_add_div #add_button').click(function() {submitAddRecord();});
         $('#iq-search-bar #search_btn').click(function() {submitSearchRecord();});


    }

    /*------------------------------函数----------------------------------------*/

    var submitSearchRecord =function () {
        var d={};
        d.gh_name=$("#gh_name").val();
        console.log("gh_name"+$("#gh_name").val());
        // d.user_name=$(" #user_search_name").val();
        // d.mail=$("#user_search_mail").val();
        // d.phone_number=  $(" #user_search_phone_number").val();
        // d.user_role= $("#user_search_div  #user_role option:selected").val();
        // var string1 ="&user_id="+d.user_id+"&username="+d.user_name+"&mail="+d.mail+"&phone_number="+d.phone_number;
       get_gh_record(d)
            console.log("触发search 事件");
            $(".datatable").dataTable().fnDestroy();

            initUserRecordList();
            $("#user_search_div").modal("hide");



    }

    var initGuesthouseRecordList =function () {

        getUserRecordList_datatable();
        // $("#js-grid-juicy-projects").load(location.href + " #js-grid-juicy-projects>*",  "");
        // setTimeout(function() {
        //     getUserRecordList_datatable();
        // }, 30000);
    }
    var redrawDatatable =function (data) {
        console.log("redrawDatatable"+ data);

        // table.fnDestroy(function(){
        //     //getUserRecordList_datatable();
        //     var param = {
        //         "status": statusnow
        //     };
        //     table.api().settings.ajax.data = param;
        //     table.api().ajax.reload();
        // });
        $(".datatable").dataTable().fnDestroy();

        statusnow =data;
        table.api().ajax.reload();
      //  table.api().ajax.reload(null, false);
       // getUserRecordList_datatable();
    }

    var get_owner_record = function (data) {
        var url = "../../user_center_servlet_action";

        data.action = "get_owner_record";
        console.log(data);
        $.post(url, data, function (json) {
            if (json.result_code == 0) {
                // alert("get_owner_record成功");


            }
        });
    }
    var getUserRecordList_datatable=function () {

        var d = {};

        // d.user_id=$("#user_search_id").val();
        // console.log("user_id"+$("#user_search_id").val());
        // d.user_name=$(" #user_search_name").val();
        // d.mail=$("#user_search_mail").val();
        // d.phone_number=  $(" #user_search_phone_number").val();
        // d.user_role= $("#user_search_div  #user_role option:selected").val();
        // var string1 ="&user_id="+d.user_id+"&username="+d.user_name+"&mail="+d.mail+"&phone_number="+d.phone_number;
        // if( d.user_role!="Option 1"&&d.user_role!="null"){
        //     string1=string1+"&user_role="+d.user_role;
        // }

         table = $('.dataTable').dataTable({
            "retrieve": true,
            "paging": true,
            "searching": true,
            "ColumnDefs": [
                {"bSortable": false, "aTargets": [0], "targets": "actions",}
            ],
            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "没有符合的民宿信息",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries found",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "_MENU_ entries",
                "search": "查找关键字:",
                "zeroRecords": "没有匹配的信息"
            },
            "ajax": {

                "dataType": 'json',
                "type": "POST",
                "url": "../../guesthouse_center_servlet_action?action=get_gh_record",
                "data": function(){
                    var stat= {"status": statusnow};
                    return stat;

                },

                "cache": "false" // 禁用缓存


            },
            "columns": [
                {
                    className: 'details-control',
                    defaultContent: '<span></span>',// data_id=\"'+aData[1]+ '\"
                    data: null,
                    orderable: false,
                    searchable: false
                },
                {data: 'gh_id'},
                {data: 'gh_name'},
                {data: 'gh_address'},
                {
                    data: null,//'owner.user_name' ,
                    render: function (data, type, full, meta) {
                        var owner = full.owner;
                        console.log(data);
                        console.log(full.owner);
                        sReturn = '<td>' + full.owner.user_name + '</td>';
                        return sReturn;
                    }
                },
                {data: 'grade'},
                {data: 'createdAt'},
                {data: 'updatedAt'},
                {data: 'gh_examine_admin'},
                {
                    data: null,
                    render: function (data, type, full, meta) {

                        console.log(data);
                        console.log(full);
                        sReturn = '<td> <span class="label label-sm label-success"> <a class="moreinfo" href="javascript:Page.onDetailRecord(' + full.gh_id + ')"> 详情 </a> </span> </td>';
                        return sReturn;
                    }
                },
                {
                    data: null,
                    visible: false,
                    render: function (data, type, full, meta) {
                        roomtype = full.roomtype;
                        console.log(roomtype[0].room_num);
                        var context = "";
                        for (var j = 0; j < roomtype.length; j++) {
                            context = context + roomtype[j].room_name + "(" + roomtype[j].room_type_name + ") : " + roomtype[j].room_num + "     ";
                        }
                        console.log(context);
                        sReturn = '<td  class="roomtypeformat">' + context + '</td>';
                        return sReturn;

                    },
                    className: 'none'
                },
                {
                    data: null,
                    visible: false,
                    render: function (data, type, full, meta) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<td> <a   class="mail" href="mailto:' + full.owner.mail + '">' + full.owner.mail + '</a> </td>';
                        return sReturn;
                    },
                    className: 'none'
                },
                {
                    data: null,//'owner.phone_number',
                    visible: false,
                    render: function (data, type, full, meta) {

                        console.log(data);
                        console.log(full);
                        sReturn = '<td class="phone_number">' + full.owner.phone_number + '</td>';
                        return sReturn;
                    },
                    className: 'none'
                },
                {
                    data: null,
                    visible: false,
                    className: 'none'
                },


            ],
            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},
             dom: 'lBfrtip',
            // setup buttons extentension: http://datatables.net/extensions/buttons/
            buttons: [
                {extend: 'print', text : "print",className: 'btn dark btn-outline'},
                {extend: 'pdf',text : "pdf", className: 'btn green btn-outline'},
                {extend: 'excel',text : "excel", className: 'btn purple btn-outline '}
            ],

            // // setup responsive extension: http://datatables.net/extensions/responsive/
            // responsive: {
            //     details: {
            //
            //     }
            // },

            "order": [
                [0, 'asc']
            ],

            "lengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,

            // "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
            // So when dropdowns used the scrollable div should be removed.
            //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
        });
        // 添加 Row Details 功能
        $('#gh_manage tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            //var row = table.DataTable().row(tr);
            var row = table.api().row($(this).parents('tr'));
            console.log(tr);
            console.log(row);
            if (row.child.isShown()) {
                // 已经展开，需要隐藏;
                console.log("已经展开，需要隐藏");
                //  $(this).addClass("glyphicon glyphicon-plus").removeClass("glyphicon glyphicon-minus");
                row.child.hide();
                // tr.childNodes.hide();//
                tr.childNodes.removeClass('shown');
            } else {
                // 还没有展开，需要加载数据并展开
                console.log("还没有展开，需要加载数据并展开");
                //$(this).addClass("glyphicon glyphicon-minus").removeClass("glyphicon glyphicon-plus");
                row.child(format(row)).show();
                tr.childNodes.addClass("shown");
                // tr.style.display='';
                console.log(row.child());
                console.log(row);

            }
        });//[0].firstChild.innerHTML
        // 配置每一行要展示的内容
        function format(row) {

            var details = '<table cellpadding=\"5px \" cellspacing=\"5px\" border="10px" style=\"padding-left:50px;\">';
            details += '<tr style="margin: 10px"><b> 房型与数量:</b> ' + row.context[0].aoData[row.index()].anCells[10].firstChild.nodeValue + '</tr><br><br>';
            details += '<tr style="margin: 10px"><b>房东联系方式:</b> ' + row.context[0].aoData[row.index()].anCells[11].innerHTML + '</tr><br><br>';
            details += '<tr style="margin: 10px"><b>房东信息:</b> ' + row.context[0].aoData[row.index()].anCells[12].firstChild.data + '</tr><br><br>';
            details += '<tr style="margin: 10px"><b>其它备注:</b>暂时无</tr>';
            details += '</table>';
            console.log("details:" + details)
            return details;
        }

        //console.log( 'Data source: '+table.ajax.url() );
        // console.log("getUserRecordList_datatable status:"+statusnow);
    }
        <!--限制文件上传大小-->
   // var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    function fileChange(target,id) {
        var fileSize = 0;
        var filetypes =[".jpg",".png",".rar",".txt",".zip",".doc",".ppt",".xls",".pdf",".docx",".xlsx"];
        var filepath = target.value;
        var filemaxsize = 1024*1.8;//1.8M
        if(filepath){
            var isnext = false;
            var fileend = filepath.substring(filepath.lastIndexOf("."));
            if(filetypes && filetypes.length>0){
                for(var i =0; i<filetypes.length;i++){
                    if(filetypes[i]==fileend){
                        isnext = true;
                        break;
                    }
                }
            }
            if(!isnext){
                alert("不接受此文件类型！");
                target.value ="";
                return false;
            }
        }else{
            return false;
        }
        if (isIE && !target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            if(!fileSystem.FileExists(filePath)){
                alert("附件不存在，请重新输入！");
                return false;
            }
            var file = fileSystem.GetFile (filePath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
        }

        var size = fileSize / 1024;
        if(size>filemaxsize){
            alert("附件大小不能大于"+filemaxsize/1024+"M！");
            target.value ="";
            return false;
        }
        if(size<=0){
            alert("附件大小不能为0M！");
            target.value ="";
            return false;
        }
    }

    function onDetailRecord (gh_id) {

     window.location.href="gh_statistical.jsp?gh_id="+gh_id;//还未创建
    }
    /*--------------------以下为地图模块---------------------------------*/

    function appendroom(){
        var fullname = $("input[name='fullname']").val();
        var number = $("input[name='number']").val();
        var price_number = $("input[name='price_number']").val();
        var remarks = $("textarea[name='remarks']").val();
        var tags = $("input[data-role='tagsinput']").val();


        var html = '<div class="row"><div class="col-md-12"><div class="portlet box blue-madison"><div class="portlet-title"><div class="caption"><i class="fa fa-gift"></i>房间信息</div></div><div class="portlet-body form"><div class="form-group"><label class="control-label col-md-3">Fullname:</label><div class="col-md-4"><p class="form-control-static">'+fullname+'</p></div></div><div class="form-group"><label class="control-label col-md-3">房间数量:</label><div class="col-md-4"><p class="form-control-static">'+number+'</p></div></div><div class="form-group"><label class="control-label col-md-3">定价:</label><div class="col-md-4"><p class="form-control-static">'+price_number+'</p></div></div><div class="form-group"><label class="control-label col-md-3">房间介绍:</label><div class="col-md-4"><p class="form-control-static">'+remarks+'</p></div></div><div class="form-group"><label class="control-label col-md-3">标签:</label><div class="col-md-4"><p class="form-control-static">'+tags+'</p></div></div><div class="form-group"><label class="control-label col-md-3">民宿外观:</label><div class="col-md-4">'+imgHtml+'</div></div></div></div></div></div>';

        $("#show_add_room").append(html);
    }
    function appendroom() {

        var form = document.getElementById("submit_form");
        var formdata=new FormData(form);
        $.ajax({
            url: "../../guesthouse_center_servlet_action?action=add_room_record",
            type: 'POST',
            data: formdata,
            processData: false,
            contentType: false,
            success: function (response) {
                // 处理响应结果
                console.log(response);
            },
            error: function (xhr, status, error) {
                // 处理请求错误
                console.error(error);
            }
        });
        // 用房间信息填充新的div
        //房间民称
        var fullnameInput = $("[name='fullname']").val();
        //房间数量
        var numberInput = $("[name='number']").val();
        //入驻人数
        var CusnumText = $("#cus_num option:selected").val();
        //房间定价
        var priceInput = $("[name='price_number']").val();
        //房间介绍
        var remarksTextarea = $("[name='remarks_room']").val();
        //房间标签
        var tagsInputs = $(".bootstrap-tagsinput").find("span");
        var tagsValue = "";
        for (var i = 0; i < tagsInputs.length; i++) {
            tagsValue += tagsInputs[i].textContent.trim()+";";
        }
        // 房间照片
        var imgUrls = [];
        $("#img-box-two li").each(function(){
            imgUrls.push($(this).find('img').attr('src'));
        });
        var imgHtml = '';
        for (var i = 0; i < imgUrls.length; i++) {
            imgHtml += '<img src="' + imgUrls[i] + '" style="max-width:100px;max-height:100px;margin-right:5px;"/>';
        }
        // 添加一行数据到表格中
        var newRow = '<tr>' +
            '<td name="room_name">' + fullnameInput + '</td>' +
            '<td name="room_num">' + numberInput + '</td>' +
            '<td name="cus_num">' + CusnumText + '</td>' +
            '<td name="price">' + priceInput + '</td>' +
            '<td name="intro">' + remarksTextarea + '</td>' +
            '<td name="tags">' + tagsValue + '</td>' +
            '<td name="imgs[]">' + imgHtml + '</td>';

        console.log(newRow);
        if (fullnameInput && numberInput && CusnumText && priceInput) {
            // 执行需要的操作

        // 将新的div添加到room_show div和最终确认表单show_confirm_room中
        $('#show_add_room tbody').append(newRow +
            '<td><button type="button" class="btn btn-danger btn-xs delete-btn">删除</button></td>' +
            '</tr>');
        //确认表单不添加删除操作
        $('#show_confirm_room tbody').append(newRow +
            '</tr>');

        // 为新添加的行中的删除按钮绑定点击事件
        $('#show_add_room tbody tr:last-child .delete-btn').on('click', function() {
            $(this).closest('tr').remove();
        });



        // 清空表单输入
        // $("[name='fullname']").val("");
        // $("[name='number']").val("");
        // $("[name='price_number']").val("");
        // $("[name='remarks']").val("");
        // $(".bootstrap-tagsinput").tagsinput("removeAll");
        $("#cus_num").val("0");
        $("#img-box-two li").remove();

        }
        else {
            $("#room_error").show();
        }

    }

///////////////////////////////////////////////地图////////////////////////////////////////////////////////////////////
    var map;


    // 创建地图实例
    var initmap =function () {
        map = new AMap.Map("asd");

    }
    //输入提示
    var autoOptions = {
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({
        map: map
    });  //构造地点查询类
    console.log("auto" + auto + "placeSearch" + placeSearch)
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
        placeSearch.setCity(e.poi.adcode);
        placeSearch.search(e.poi.name);  //关键字查询查询
        var address  = document.getElementById('tipinput').value;
        // geocoder.getLocation(address, function(status, result) {
        //     if (status === 'complete'&&result.geocodes.length) {
        //         var lnglat = result.geocodes[0].location;
        //         console.log(lnglat);
        //         // document.getElementById('lnglat').value = lnglat;
        //         marker.setPosition(lnglat);
        //         map.add(marker);
        //         map.setFitView(marker);
        //     }else{
        //         log.error('根据地址查询位置失败');
        //     }
        // });
    }

    var geocoder = new AMap.Geocoder({

    });

    var marker = new AMap.Marker();

    function geoCode() {
        console.log("geoCode");
        var address  = document.getElementById('tipinput').value;
        geocoder.getLocation(address, function(status, result) {
            if (status === 'complete'&&result.geocodes.length) {
                var lnglat = result.geocodes[0].location
               // document.getElementById('lnglat').value = lnglat;
                marker.setPosition(lnglat);
                map.add(marker);
                map.setFitView(marker);
            }else{
                log.error('根据地址查询位置失败');
            }
        });
    }

  function submit_gh_add() {
      var table = document.getElementById('show_confirm_room');
      var form =document.getElementById('submit_form');
      var formData = new FormData(form);
      formdata.delete('room_pic');
      $.ajax({
          url: "../../guesthouse_center_servlet_action?action=add_gh_record",
          type: 'POST',
          data: formdata,
          processData: false,
          contentType: false,
          success: function (response) {
              // 处理响应结果
              console.log(response);
          },
          error: function (xhr, status, error) {
              // 处理请求错误
              console.error(error);
          }
      });

      var data = [];
      var rows = table.getElementsByTagName('tr');
      for (var i = 1; i < rows.length; i++) {
          var row = rows[i];
          var nameInput = row.cells.room_name;
          var numInput = row.cells.room_num;
          var cusInput = row.cells.cus_num;
          var priceInput = row.cells.price;
          var introInput = row.cells.intro;
          var tagsInput = row.cells.tags;
          var imgInputs = row.cells["imgs[]"].children;
          var rowFormData = new FormData();
          rowFormData.append('room_name', nameInput.innerText);
          rowFormData.append('room_num', numInput.innerText);
          rowFormData.append('cus_num', cusInput.innerText);
          rowFormData.append('price', priceInput.innerText);
          rowFormData.append('intro', introInput.innerText);
          rowFormData.append('tags', tagsInput.innerText);

          for (var j = 0; j < imgInputs.length; j++) {
              rowFormData.append('imgs[]', imgInputs[j].currentSrc);
          }
          console.log(rowFormData);
          data.push(rowFormData);
      }

      for (var i = 0; i < data.length; i++) {
          var rowFormData = data[i];
          for (var pair of rowFormData.entries()) {
              formData.append(pair[0], pair[1]);
          }
      }

      //
      // $.ajax({
      //     url: form.action,
      //     type: 'POST',
      //     data: formData,
      //     processData: false,
      //     contentType: false,
      //     success: function (response) {
      //         // 处理响应结果
      //         console.log(response);
      //     },
      //     error: function (xhr, status, error) {
      //         // 处理请求错误
      //         console.error(error);
      //     }
      // });
  }








    return {
        init: function() {
            initPageControl();
        },
        onchangeStatus: function(status){
            console.log("onchangeStatus"+status);
            statusnow=status;
            $('.dataTable').dataTable().fnClearTable(); //清空表格
           // $('.dataTable').empty(); // 删除旧实例的 DOM 元素
           // $('.dataTable').dataTable().clear().draw();

            console.log(statusnow);
            getUserRecordList_datatable();
           // $('.dataTable').find('tbody').append("<tr><td><value1></td><td><value1></td></tr>");
           // $('.dataTable').dataTable().api().ajax.reload();

        },
        appendroom : function () {
            appendroom();
        },
        geoCode : function () {
            geoCode();
        },
        onSubmit_GH_add :function () {
            submit_gh_add();


        }
    }



}();//Page
/*================================================================================*/
