var module="guesthouse";
var sub="order";
var t;  // 用于验证按钮的60s计时
var statusnow = 0;
var start_date = null;
var end_date =null;
var roleoption =[] ;
var table;
var user_role;//记录登入用户的角色
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
        if(pageId=="gh_order_list"){

            initGuesthouseOrderList();
        }

    };
    /*----------------------------------------入口函数  结束----------------------------------------*/

    /*----------------------------------------业务函数  开始----------------------------------------*/
    /*------------------------------针对各个页面的入口  开始------------------------------*/
    var initGuesthouseOrderList=function(){
        user_role=getUrlParam("user_role");
       // initRoleoption();
        initGuesthouseOrderListControlEvent();

        initGuesthouseOrderRecordList();
      //  initRoleoption();

    }
    var initUserAdd=function(){
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

    var initGuesthouseOrderListControlEvent=function(){
        $('#submit_comment_btn ').click(function () {
            OnModifySubmit();
        });
        $('#guesthouse_order_edit_modal #submit_modify_order_btn').click(function() {
            onSubmitModifyGHOrderRecord();});

        $("#reserved").click(function() {
            //成功付款并预定的订单
            redrawDatatable(0);});
        $("#fulfill").click(function() {
            //以完成的订单
            redrawDatatable(1);});
        $("#cancel").click(function() {
            //已取消的订单
            redrawDatatable(2);});

        $('#user_add_div #add_button').click(function() {
            submitAddRecord();});



    }

    /*------------------------------函数----------------------------------------*/

    var getUrlParam=function(name){
        //获取url中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        console.log(r);
        if (r != null) return decodeURI(r[2]); return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
    }

    var initGuesthouseOrderRecordList =function () {

        getGhOrderRecordList_datatable();
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

        //订单状态赋值 已预订0 已完成1 已取消2
        statusnow =data;
        table.api().ajax.reload();
      //  table.api().ajax.reload(null, false);
       // getUserRecordList_datatable();
    }


    var getGhOrderRecordList_datatable=function () {

         table = $('.dataTable').dataTable({
             "autoWidth":true,
             "scrollX": true,
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
                "emptyTable": "没有符合的订单信息",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries found",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "_MENU_ entries",
                "search": "Search:",
                "zeroRecords": "没有符合的信息"
            },
            "ajax": {

                "dataType": 'json',
                "type": "POST",
                "url": "../../guesthouse_order_center_servlet_action?action=get_gh_order_record",
                "data": function(){
                    var stat= {"status": statusnow,"start_date":start_date, "end_date":end_date};
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
                {data: 'order_id'},
                {data: 'check_in_date'},
                {data: 'check_out_date'},
                {data: 'cus_name'},
                {data: 'cus_phone_num'},
                {
                    data: null,
                    render: function (data, type, full, meta) {

                        sReturn = '<td  >' + full.room_name +' /  '+full.room_type_id+ '人房  </td>';
                        return sReturn;

                    }
                },
                {
                    data: null,//'owner.user_name' ,
                    render: function (data, type, full, meta) {
                        var owner = full.owner;
                        sReturn = '<td><a>' + full.owner.user_name + '/ '+full.owner.phone_number+'</a></td>';
                        return sReturn;
                    }
                },
                {data: 'total'},
                {
                    data: null,//'owner.user_name' ,
                    render: function (data, type, full,meta) {
                        if(full.status == '1'){
                            console.log("此条为已完成订单不可再进行修改");
                            sReturn ='<td>  </td>';
                            return  sReturn;
                        }else if(full.status == '2'){
                            console.log("此条为已取消订单 也不能进行操作");
                            sReturn ='<td>  </td>';
                            return  sReturn;
                        }
                        sReturn ='<button class="btn btn-circle default blue-stripe"  onclick="javascript:Page.onModifyRecord(' + full.order_id + ')"  type="button"><i class="fa fa-edit"></i>修改</button>';
                       sReturn =  sReturn + '<button class="btn btn-circle default red-stripe"  onclick="javascript:Page.onCancelRecord(' + full.order_id + ')"  type="button"> <i class="fa fa-times">取消</button>';
                        return sReturn;

                    }
                },
                //以下为隐藏可扩展列表项 预定日期 备注 评价与反馈
                {
                    data: null,
                    visible: false,
                    render: function (data, type, full, meta) {

                        sReturn = '<td  class="reservedate">' + full.reserve_date + '</td>';
                        return sReturn;

                    },
                    className: 'none'
                },
                {
                    data: null,
                    visible: false,
                    render: function (data, type, full, meta) {

                        sReturn = '<td  class="note">' + full.note + '</td>';
                        return sReturn;
                    },
                    className: 'none'
                },
                {
                    data: null,//'owner.comment',
                    visible: false,
                    render: function (data, type, full, meta) {
                        if((full.comment==""||full.comment==null)&&user_role == 2){
                            sReturn = '<td class="comment"><a href="javascript:Page.onaddComment('+full.check_in_date+','+full.check_out_date+',\''+full.room_name+'\' )">点击留下你的评论吧!\-*-/</a></td>';
                        }else {
                            var comment =(full.comment==""||full.comment==null)? "用户没有留下评论\\[-.-]/***":full.comment;
                            sReturn = '<td class="comment">' +comment + '</td>';
                        }
                        return sReturn;
                    },
                    className: 'none'
                }


            ],
             dom: 'lBfrtip',
            // setup buttons extentension: http://datatables.net/extensions/buttons/
            buttons: [
                {extend: 'print', text : "print",className: 'btn dark btn-outline'},
                {extend: 'pdf',text : "pdf", className: 'btn green btn-outline'},
                {extend: 'excel',text : "excel", className: 'btn purple btn-outline '}
            ],
            "order": [
                [0, 'asc']
            ],

            "lengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,

            });
        // 添加 Row Details 功能
        $('#gh_order_manage tbody').on('click', 'td.details-control', function () {
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
        });
        // 配置每一行要展示的内容
        function format(row) {

            var details = '<table cellpadding=\"5px \" cellspacing=\"5px\" border="10px" style=\"padding-left:50px;\">';
            details += '<tr style="margin: 10px"><b> 预订日期:</b> ' + row.context[0].aoData[row.index()].anCells[10].firstChild.data + '</tr><br><br>';
            details += '<tr style="margin: 10px"><b>备注:</b> ' + row.context[0].aoData[row.index()].anCells[11].firstChild.data + '</tr><br><br>';
            details += '<tr style="margin: 10px"><b>评价与反馈:</b> ' + row.context[0].aoData[row.index()].anCells[12].innerHTML + '</tr>';
            details += '</table>';
            console.log("details:" + details)
            return details;
        }
    }

    var onsearchGHOrderRecord=function(){


         //   data.user_role= $("#user_modify_div #user_role option:selected").val();
            //搜索日期区间 只要有重叠的订单都显示 而非查询空房
            start_date = $(".start_date").val();
            end_date = $(".end_date").val();

            var searchview ="您的筛选条件　： <h3>";
            if( start_date&&end_date){
                searchview=searchview+" 介于您所选日期 : <h3> " ;
                if(start_date ==end_date){
                    searchview=searchview+"<b>"+start_date+"</b> 的所有订单如下: " ;
                }else {
                    searchview=searchview+"<b>"+start_date+" 至 "+end_date+" </b> 的所有订单如下: " ;
                }
                $("#gh_order_search_result").html(searchview+"</h3>");
                $("#gh_order_search_result").show();

                //默认先搜索已经预定 重置选中状态
                $("#reserved").addClass("active");
                $("#fulfill").removeClass("active");
                $("#cancel").removeClass("active");
                //请求数据
                redrawDatatable(0);
                //隐藏搜索div
                $("#date_div").hide();
                //显示重置搜索控件
                $("#map_search_result_2").show();
            }else {
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "positionClass": "toast-top-right",
                    "onclick": null,
                    "showDuration": "1000",
                    "hideDuration": "1000",
                    "timeOut": "3000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                toastr["error"]("请正确选择日期,若要查询某天订单,请选择相同日期", "错误");
            }

    }
    var onSubmitModifyGHOrderRecord=function () {
       var data =getmodify();
        var url="../../"+module+"_"+sub+"_center_servlet_action";
        $.post(url,data,function(json){
            if(json.result_code==0){
                alert("订单信息修改成功");
                $("#guesthouse_order_edit_modal").modal("hide");
                getGhOrderRecordList_datatable();
            }
        });
        $("#reload_body ").load(location.href + " #reload_body");



    }
    function getmodify() {
        var d = {};

        d.order_id = $("#order_id").val();
        d.cus_name = $("#cus_name").val();
        d.cus_phone_num = $("#cus_phone_num").val();
        d.total = $("#total").val();

        d.action ="modify_GHOrderRecord";
        return d;

    }

    /*********************************************页面函数********************************************/


    var onInitModifyRecord =function (order_id) {
        //  window.location.href="user_modify.jsp?id="+id;
        // var url="../../"+module+"_"+sub+"_center_servlet_action";
        // var data={};
        // data.action="get_gh_order_record";
        // data.order_id=order_id;
        // $.post(url,data,function(json){
        //     if(json.result_code==0){
        //         $("#guesthouse_order_edit_modal").modal("show");
        //     }
        // })
        $.ajax({
            url: '../../guesthouse_order_center_servlet_action?action=get_gh_order_record',  // 后台接口 URL
            type: 'get',      // 请求类型
            dataType: 'json', // 返回数据类型
            data: { order_id: order_id },
            success: function(data) {
                // 根据请求回来的数据更新 modal 的内容
              //  $('#guesthouse_order_edit_modal .modal-body').html(data);
                $("#reload_body ").load(location.href + " #reload_body");
                $("#guesthouse_order_edit_modal").modal("show");
             //   $("#guesthouse_order_edit_modal").load(location.href + " #guesthouse_order_edit_modal");
            }
        });

    };
    var onreset =function () {
        //显示重置搜索控件
        $("#map_search_result_2").hide();
        $("#date_div").load(location.href + " #date_div");
        $("#date_div").show();
        end_date=null;
        start_date=null;
        //默认先搜索已经预定 重置选中状态
        $("#reserved").addClass("active");
        $("#fulfill").removeClass("active");
        $("#cancel").removeClass("active");
        //请求数据
        redrawDatatable(0);
    }

    var onCancelRecord = function(order_id){
        if(confirm("您确定要取消这笔订单吗？")){
            if(order_id>-1){
                var url="../../"+module+"_"+sub+"_center_servlet_action";
                var data={};
                data.action="cancel_order_record";
                data.order_id=order_id;
                $.post(url,data,function(json){
                    if(json.result_code==0){
                        window.location.reload();
                    }
                })
            }
        }
    }
    var GetVcode =function (method) {
        console.log("进入GetVcode");

        $.ajax({
            url: '../../comment_center_servlet_action?action=verifyvcode',
            type: 'post',
            data: {method:method},
            dataType: 'json',
            success: function (json) {
                console.log(typeof json);
                var vCode=json.vCode;
                console.log(json.vCode);
                $("#code").text(vCode);
                $("#code").html(vCode);
            }
        });
    }


    var OnModifySubmit=function () {
        if( verifyCode()){
            console.log("对");


        }
        console.log("错误???");
    }
    var verifyCode =function () {

        $.ajax({
            url: '../../comment_center_servlet_action?action=verifyvcode',
            type: 'post',
            data: {vcode: $("input[name='vcode']").val(),method:"verify"},
            dataType: 'text',
            success: function (json) {
                if (json.verify_result == "true") {
                    return true;
                } else {
                    return false;
                }
            }
        });

    }
    var initcommnet_add_div =function (check_in_date,check_out_date,roome_name) {

        GetVcode("getVCode");
        $("#comment_title").val("于"+check_in_date+" -"+ check_out_date+"入住"+ roome_name);
        $("#comment_add_modal").modal("show");
        $("#comment_add_modal").show();

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
        onCancelRecord:function(order_id){
            onCancelRecord(order_id);
        },
        onModifyRecord:function(order_id){
            console.log(order_id);
            $("#reload_body ").load(location.href + " #reload_body");
            //等待五秒在显示modal 等待后台请求渲染数据
            t = setInterval(function () {
                onInitModifyRecord(order_id);
                clearInterval(t);

            }, 2000);  // 5s调用一次

        },
        submitqueryRecord :function(){
            onsearchGHOrderRecord();
        },

        onreset:function () {
        onreset();

        },
        onreGetVcode:function(){
            GetVcode("reGetVCode");
        },
        onaddComment:function(check_in_date,check_out_date,roome_name){
            // $("#comment_add_modal_body ").load(location.href + " #comment_add_modal_body");
            //等待五秒在显示modal 等待后台请求渲染数据
            // t = setInterval(function () {
                initcommnet_add_div(check_in_date,check_out_date,roome_name)
                // clearInterval(t);

            // }, 2000);  // 5s调用一次


        },
        }



}();//Page
/*================================================================================*/
