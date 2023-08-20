var module="sue";
var statusnow = 0;
var table;
var user_role;
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
        if(pageId=="sue_manage_list"){
            user_role=$("#user_role").val();
            initSueList();
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
    var initSueList=function(){

       // initRoleoption();
        initGuesthouseListControlEvent();

        initSueRecordList();
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

        $("#on_add_sue").click(function() {
            onaddSue();});
        $("#add_sue_button").click(function() {
            onaddSubmit();});
        $("#handle").click(function() {
            redrawDatatable(1);});
        $("#unhandle").click(function() {
            redrawDatatable(0);});
        $("#resp_button").click(function() {
            onSubmitResponse();});

    }

    /*------------------------------函数----------------------------------------*/



    var initSueRecordList =function () {

        getSueRecordList_datatable();

    }
    var redrawDatatable =function (data) {
        console.log("redrawDatatable"+ data);

        $(".datatable").dataTable().fnDestroy();

        statusnow =data;
        table.api().ajax.reload();

    }

    var onaddSue= function () {

        $("#sue_add_div").modal("show");
        $("#sue_add_div").show();
    }
    var onaddSubmit = function () {

        var sue_type = $('#sue_type').val();
        var sue_context = $('#sue_context').val();
        data.type=sue_type;
        data.content=sue_context;
        data.action = "add_sue_record";
        console.log(data);
        $.post(url, data, function (json) {
            if (json.result_code == 1) {
                alert("登陆过期请重新录");
                window.location.href="../../home/main/login.jsp";

            }else {
                $("#sue_add_div").modal("hide");
                $("#sue_add_div").hide();

                //置空input区域
                $("[name='sue_type']").val("");
                $("[name='sue_context']").val("");
                redrawDatatable(0);
            }
        });


    }
    var onSubmitResponse =function () {
        var note = $('#note').val();
        var response = $('#response').val();
        var data ='';
        data.note=note;
        data.response=response;
        data.action = "modify_sue_record";
        console.log(data);
        $.post(url, data, function (json) {
            if (json.result_code == 0) {
                // alert("get_owner_record成功");


            }
        });
        $("#sue_respons_div").modal("hide");
        $("#sue_respons_div").hide();

        //置空input区域
        $("[name='note']").val("");
        $("[name='response']").val("");
        redrawDatatable(0);

    }

    var getSueRecordList_datatable=function () {

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
                "url": "../../sue_servlet_action?action=get_sue_record",
                "data": function(){
                    var stat= {"status": statusnow};
                    return stat;

                },

                "cache": "false" // 禁用缓存


            },
            "columns": [
                {data: 'sue_id'},
                {data: 'user_id' },
                {data: 'type_name'},
                {data: 'content'},
                {
                    data: 'ad_note',
                    visible: user_role=="0"?true:false,

                },
                {data:'ad_response'},
                {data: 'sue_create_date'},
                // {data: 'deal_status'},

                {
                    data: null,
                    render: function (data, type, full, meta) {
                        console.log(full);
                        sReturn='';
                        if(user_role=="0"&&full.deal_status=="0") {
                            sReturn = '<td> <span class="label label-sm label-success"> <a href="javascript:Page.onResponse(' + full.sue_id + ')"> 回复/添加备注 </a> </span> </td>';
                            sReturn = sReturn + '<td> <span class="label label-sm label-success"> <a href="javascript:Page.onChangeStatus(' + full.sue_id + ',1)"> 处里完成 </a> </span> </td>';

                       }else if(user_role=="0"&&full.deal_status=="1"){
                            sReturn = sReturn + '<td> <span class="label label-sm label-success"> <a href="javascript:Page.onChangeStatus(' + full.sue_id + ',0)"> 退回待处里 </a> </span> </td>';

                        }else if(full.deal_status=="0"){
                            sReturn = '<td> <span class="label label-sm label-success">我们将近快处里</span> </td>';

                        }else {
                            sReturn = '<td> <span class="label label-sm label-success"> <a href="javascript:Page.onChangeStatus(' + full.sue_id + ',0)"> 再次发起投诉 </a> </span> </td>';

                        }
                        return sReturn;
                    }
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

    }


    /*--------------------以下为地图模块---------------------------------*/

   var  onResponse =function (sue_id) {
       $("#sue_id").val(sue_id);
       $("#sue_respons_div").show();
       $("#sue_respons_div").modal("show");


   }


   var onChangeStatus =function (sue_id,status) {
       var url = "../../sue_servlet_action";
       var data = {};
       data.action = "modify_sue_record";
       data.sue_id=sue_id
       data.deal_status=status;
       console.log(data);
       $.post(url, data, function (json) {
           if (json.result_code == 0) {
               // alert("get_owner_record成功");


           }
       });
   }
///////////////////////////////////////////////地图////////////////////////////////////////////////////////////////////



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
        onResponse:function(sue_id){
            onResponse(sue_id);
        },
        onChangeStatus :function (sue_id,status) {
            onChangeStatus(sue_id,status);
        }
    }



}();//Page
/*================================================================================*/
