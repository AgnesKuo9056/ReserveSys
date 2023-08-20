var module="user";
var sub="center";
var roleoption =[] ;
var daata;
var statusnow = 0;
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
        if(pageId=="user_list"){
            initUserList();
        }
        if(pageId=="user_add"){
            initUserAdd();
        }
        if(pageId=="store_apply"){
            initStoreApply();
        }
        if(pageId=="print_table"){
            initPrintTable();
        }

    };
    /*----------------------------------------入口函数  结束----------------------------------------*/

    /*----------------------------------------业务函数  开始----------------------------------------*/
    /*------------------------------针对各个页面的入口  开始------------------------------*/
    var initUserList=function(){
        getUserrroleOption();
        // initRoleoption();
        initUserListControlEvent();
        initUserRecordList();
        //  initRoleoption();

    }
    var initStoreApply=function(){
        initStoreList();
        initStoreApplyControlEvent();
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
    var getUrlParam=function(name){
        //获取url中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        console.log(r);
        if (r != null) return decodeURI(r[2]); return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
    }
    var initUserListControlEvent=function(){
        $("#help_button").click(function() {help();});
        //   $('#query_btn').click(function() {onqueryRecord();});
        $('#add_button_condition').click(function() {onAddRecord();});
        $('#export_button').click(function() {onExport_Button();});
        $('#print_table').click(function() {onPrint_Table();});
        $('#user_modify_div #submit_modify_btn').click(function() {OnModifySubmit();});
        $('#user_search_div #search_btn').click(function() {submitSearchRecord();});
        $('#user_add_div #add_button').click(function() {submitAddRecord();});


    }
    var initStoreApplyControlEvent=function(){
        $("#wait_apply").click(function() {

            redrawDatatable(0);});
        $("#passed").click(function() {

            redrawDatatable(1);});
        $("#refused").click(function() {

            redrawDatatable(2);});

    }
    var initUserModifyControlEvent=function(){
        $("#help_button").click(function() {help();});
        $('#modify_button').click(function() {submitModifyRecord();});
    }
    var initUserQueryControlEvent=function () {
        $("#help_button").click(function() {help();});
        $('#search_btn').click(function() {submitSearchRecord();});
        $('#back_btn').click(function() {backtoUserList();});
    }


    var getUserrroleOption =function () {
        roleoption =[];
        var data={};
        data.action="get_user_role_option";
        $.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){
            console.log(JSON.stringify(json));
            if(json.result_code==0){
                var list=json.role_option;
                if(list!=undefined && list.length>0){
                    roleoption.push(list);
                    var html = [];
                    var option =roleoption[0];
                    console.log(roleoption[0]);
                    console.log(option);
                    html=html+" <select  id=\"user_role\" name=\"user_role\" class=\"form-control\">";
                    var i=0
                    for(i=0;i<option.length;i++){
                        var role=option[i];
                        console.log(role);
                        html=html+"    <option id=\""+i;
                        html=html+"\" value=\""+role.user_role;
                        html=html+"\">"+role.role_name;
                        html=html+"</option>";
                    }

                    console.log("html="+html);
                    var htmlsearch = html+" <option id=\""+(i+1)+"\" value='null'> 无 </option> </select>"
                    $(" #user_search_div #option_div").html(htmlsearch);
                    html=html+"</select>";
                    $(" #user_add_div #option_div").html(html);
                }
            }
        })
    }
    var initRoleoption = function () {
        roleoption=[];
        getUserrroleOption();
        console.log(roleoption);
        var html ;
        var option =roleoption[0];
        console.log(roleoption[0]);
        console.log(option);
        html=html+" <select  id=\"user_role\" name=\"user_role\" class=\"form-control\">";
        for(var i=0;i<option.length;i++){
            var role=option[i];
            console.log(role);
            html=html+"    <option id=\""+i;
            html=html+"\" value=\""+role.user_role;
            html=html+"\">"+role.role_name;
            html=html+"</option>";
        }
        html=html+"</select>";
        console.log("html="+html);

        $(" #user_search #option_div").html(html);
        $(" #user_add_div #option_div").html(html);
    }
    var initAddRecordView = function () {
        roleoption=[];
        getUserrroleOption();
        for(var i=0;i<roleoption.length;i++){
            var option=roleoption[i];
            html=html+" <select  id=\"user_role\" name=\"user_role\" class=\"form-control\">";
            html=html+"    <option id=\""+i;
            html=html+"\" value=\""+option.user_role;
            html=html+"\">"+option.role_name;
            html=html+"</option>";
        }
        html=html+"</select>";
        $(" #user_add_div #option_div").html(html);
    }
    var initUserRecordView=function(user_id){

        // roleoption=[];
        //getUserrroleOption();
        //var user_id=getUrlParam("user_id");
        var data={};
        data.action="get_user_record";
        data.user_id=user_id;
        var html ="";
        var modify_user_role ;


        $.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){
            console.log(JSON.stringify(json));
            if(json.result_code==0){
                var list=json.aaData;
                if(list!=undefined && list.length>0){
                    for(var i=0,j=0;i<list.length;i++){
                        var user=list[i];
                        $(" #user_modify_div #user_id").val(user.user_id);
                        $(" #user_modify_div #user_name").val(user.user_name);
                        $("#user_modify_div #mail").val(user.mail);
                        $(" #user_modify_div #phone_number").val(user.phone_number);
                        console.log(roleoption);
                        var option =roleoption[0];
                        console.log(option);
                        html=html+" <select  id=\"user_role\" name=\"user_role\" class=\"form-control\">";
                        for(var i=0;i<option.length;i++){
                            var role=option[i];
                            console.log(role);
                            html=html+"    <option id=\""+i;
                            html=html+"\" value=\""+role.user_role;
                            if(role.user_role.toString()==user.user_role.toString()) {
                                html = html + "\"selected=\"selected\"";
                            }
                            html=html+"\">"+role.role_name;
                            html=html+"</option>";
                        }
                        html=html+"</select>";
                        console.log(html);
                        console.log(user.role_name);

                        $(" #user_modify_div #option_div").html(html);


                    }
                }
            }

        })


    }


    var onAddRecord=function(){
        $("#user_add_div").modal("show");
    }
    var onqueryRecord =function () {
        $("#user_search_div").modal("show");
    }


    var submitAddRecord=function(){
        var url="../../"+module+"_"+sub+"_servlet_action";
        var data={};
        data.action="add_user_record";
        data.user_id=$(" #user_add_div #user_id").val();
        data.password =$("#user_add_div #password").val();
        data.user_name=$(" #user_add_div #user_name").val();
        data.mail=$("#user_add_div #mail").val();
        data.phone_number=  $(" #user_add_div #phone_number").val();
        data.user_role= $("#user_add_div #user_role option:selected").val();
        console.log("data"+data.user_id);
        $.post(url,data,function(json){
            if(json.result_code==0){
                alert("已经完成设备添加。");
                window.location.reload();
            }
        });
    }
    var submitSearchRecord = function () {
        console.log("触发search 事件")
        console.log("user_search_id =="+$("#user_search_id").val());
        $(".datatable").dataTable().fnDestroy();


        // getUserSearchRecordList_datatable();
        initUserRecordList();
        $("#user_search_div").modal("hide");
        $("#search_result").show();


    }

    var initPrintTableView=function () {
        $(".page-sidebar-wrapper").hide();
        $(".page-header").hide();
        $(".page-content").attr("style","margin-left: 0px");
        // $(".page-content").attr("style","margin-bottom: 0px");
        $(".page-container").attr("style","margin-top: 0px");

        $.post("../../"+module+"_"+sub+"_servlet_action?action=get_user_record",function(json){
            if(json.result_code==0){
                //roleoption=[];
                //getUserrroleOption();
                var list=json.aaData;
                var html="";
                html=html+"							<thead>";
                html=html+"                            <tr>";
                html=html+"                                <th>";
                html=html+"                                    <i class=\"fa fa-briefcase\"></i> 用户ID </th>";
                html=html+"                                <th class=\"hidden-xs\">";
                html=html+"                                    <i class=\"fa fa-user\"></i> 用户名称 </th>";
                html=html+"                                <th>";
                html=html+"                                    <i class=\"fa fa-user\"></i> 邮箱 </th>";
                html=html+"                                <th>";
                html=html+"                                    <i class=\"fa fa-user\"></i> 联系方式 </th>";
                html=html+"                                <th>";
                html=html+"                                    <i class=\"fa fa-shopping-cart\"></i> 用户权限 </th>";
                html=html+"                                <th>";
                html=html+"                            </tr>";
                html=html+"                            </thead>";
                if(list!=undefined && list.length>0){
                    for(var i=0;i<list.length;i++){
                        var record=list[i];
                        html=html+"		                      <tr>";
                        html=html+"                                <td class=\"highlight\">";
                        html=html+"                                    <div class=\"success\"></div>"+record.user_id;
                        html=html+"                                </td>";
                        html=html+"                                <td class=\"hidden-xs\"> "+record.user_name+" </td>";
                        html=html+"                                <td class=\"hidden-xs\"> "+record.mail+" </td>";
                        html=html+"                                <td class=\"hidden-xs\"> "+record.phone_number+" </td>";
                        // for(var j=0;j<user_list.length;j++){
                        //     if(roleoption[j].user_role.toString()==record.user_role.toString()){
                        //         html=html+"                                <td class=\"hidden-xs\"> "+roleoption[j].role_name+" </td>";
                        //     }
                        // }
                        // html=html+"                                <td> "+record.creator+" </td>";
                        html=html+"                                <td class=\"hidden-xs\"> "+record.role_name+" </td>";
                        html=html+"                            </tr>";
                    }
                }
                $("#record_print_table").html(html);
            }
        })
    }

    var submitModifyRecord=function(){
        if(confirm("您确定要修改该记录吗？")){
            //var user_id=getUrlParam("user_id");
            var url="../../"+module+"_"+sub+"_servlet_action";
            var data={};
            data.action="modify_user_record";
            data.user_id=$(" #user_modify_div #user_id").val();
            data.user_name=$(" #user_modify_div #user_name").val();
            data.mail=$("#user_modify_div #mail").val();
            data.phone_number=  $(" #user_modify_div #phone_number").val();
            data.user_role= $("#user_modify_div #user_role option:selected").val();
            console.log(data);
            $.post(url,data,function(json){
                if(json.result_code==0){
                    alert("已经完成用户信息修改。");
                    $("#user_modify_div").modal("hide");
                    //window.location.reload();
                    window.location.href="user_list.jsp";
                }
            });
        }
    }

    var initUserRecordList=function(){
        getUserRecordList_datatable();
    }




    var user_list=[];
    var getUserRecordList_datatable=function () {
        user_list = [];
        roleoption = [];
        var d={};
        var searchview ="";
        d.user_id=$("#user_search_id").val();
        if( d.user_id){
            searchview=searchview+"<b> 用户ID: </b>"+ d.user_id;
        }
        console.log("user_id"+$("#user_search_id").val());
        d.user_name=$(" #user_search_name").val();
        if( d.user_name){
            searchview=searchview+" <b> 用户姓名: </b>"+ d.user_name;
        }
        d.mail=$("#user_search_mail").val();
        if( d.mail){
            searchview=searchview+" <b> Email: </b>"+ d.mail;
        }
        d.phone_number=  $(" #user_search_phone_number").val();
        if( d.phone_number){
            searchview=searchview+" <b> 联系电话 : </b>"+ d.phone_number;
        }
        d.user_role= $("#user_search_div  #user_role option:selected").val();
        var string1 ="&user_id="+d.user_id+"&username="+d.user_name+"&mail="+d.mail+"&phone_number="+d.phone_number;

        if( d.user_role!="Option 1"&&d.user_role!="null"){
            string1=string1+"&user_role="+d.user_role;
            searchview=searchview+" <b> 用户权限 : </b>"+ $("#user_search_div  #user_role option:selected").text ();
        }
        console.log(d);
        console.log(searchview);

        // getUserrroleOption();
        var d = {};

        daata = $('.datatable').dataTable({
            "retrieve": true,
            "paging": true,
            "searching": false,
            "oLanguage": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "sProcessing": "处理中...",
                "sLengthMenu": "_MENU_ 记录/页",
                "sZeroRecords": "没有匹配的记录",
                "sInfo": "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项记录，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项记录过滤)",
                "sInfoPostFix": "",
                "sSearch": "过滤:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }
            },
            "ajax":{

                "dataType": 'json',
                "type": "POST",
                "url":"../../user_center_servlet_action?action=get_user_record"+string1.toString()

            },
            "aoColumns": [
                {
                    "mRender": function (data, type, full) {
                        user_list.push(full);
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.user_id + '</div>';
                        console.log(sReturn);
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.user_name + '</div>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.phone_number + '</div>';
                        console.log(sReturn);
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.mail + '</div>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        // for(var j=0;j<user_list.length;j++){
                        //     if(roleoption[j].user_role==full.user_role){
                        //         sReturn = '<div>'+roleoption[j].role_name+'</div>';
                        //         break;
                        //     }
                        // }
                        sReturn = '<div>' + full.role_name + '</div>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {

                        console.log(data);
                        console.log(full);
                        sReturn ='<button class="btn btn-circle default blue-stripe"  onclick="javascript:Page.onModifyRecord(' + full.user_id + ')"  type="button"><i class="fa fa-edit"></i>更改</button>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {

                        console.log(data);
                        console.log(full);
                        sReturn ='<button class="btn btn-circle default red-stripe"  onclick="javascript:Page.onDeleteRecord(' + full.user_id + ')"  type="button"> <i class="fa fa-times">注销</button>';
                        return sReturn;
                    }, "orderable": false

                }],
            "aLengthMenu": [[5, 10, 15, 20, 25, 40, 50, -1], [5, 10, 15, 20, 25, 40, 50, "所有记录"]],
            "fnDrawCallback": function () {
                $(".checkboxes").uniform();
                $(".group-checkable").uniform();
            },
            //"sAjaxSource": "get_record.jsp"
            // "sAjaxSource": "../../"+module+"_"+sub+"_servlet_action?action=get_user_record&datasearch="+datasearch.toString()

        });

        $('.datatable').on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });

        var html =    "<small> 您的搜寻条件 "+searchview  +"</small>";
        console.log("搜寻条件:"+html);
        $("#search_result").html(html);
    }
    var getUserSearchRecordList_datatable=function () {
        console.log('执行到这里了');
        $('.datatable').dataTable({

            "retrieve": true,
            "paging": true,
            "searching": false,
            "oLanguage": {
                "aria": {

                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "sProcessing": "处理中...",
                "sLengthMenu": "_MENU_ 记录/页",
                "sZeroRecords": "没有匹配的记录",
                "sInfo": "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项记录，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项记录过滤)",
                "sInfoPostFix": "",
                "sSearch": "过滤:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }
            }
            // ,
            // "ajax": {
            //     url: " ../../" + module + "_" + sub + "_servlet_action?action=get_user_record", //访问路径，返回Json数据
            //     data: datasearch,
            //     type: "POST"   //设置请求类型
            // }
            ,
            "aoColumns": [

                {
                    "mRender": function (data, type, full) {
                        user_list.push(full);
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.user_id + '</div>';
                        console.log(sReturn);
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.user_name + '</div>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.phone_number + '</div>';
                        console.log(sReturn);
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.mail + '</div>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {
                        console.log(data);
                        console.log(full);
                        sReturn = '<div>' + full.role_name + '</div>';
                        return sReturn;
                    }, "orderable": false
                }, {
                    "mRender": function (data, type, full) {

                        console.log(data);
                        console.log(full);
                        sReturn = '<div><a href="javascript:Page.onModifyRecord(' + full.user_id + ')">【修改用户信息】</a><a href="javascript:Page.onDeleteRecord(' + full.user_id + ')">【删除用户】</a><div>';
                        return sReturn;
                        console.log('水噢执行到这里了');
                    }, "orderable": false
                }],
            "aLengthMenu": [[5, 10, 15, 20, 25, 40, 50, -1], [5, 10, 15, 20, 25, 40, 50, "所有记录"]],
            "fnDrawCallback": function () {
                $(".checkboxes").uniform();
                $(".group-checkable").uniform();
            },
            //"sAjaxSource": "get_record.jsp"

            "sAjaxSource": "../../"+module+"_"+sub+"_servlet_action?action=query_user_record&user_id="+$("#user_search_id").val()
        });

        $('.datatable').on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });

    }

    var initStoreList = function () {
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
                "emptyTable": "没有商家申请记录",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries found",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "_MENU_ entries",
                "search": "Search:",
                "zeroRecords": "没有符合的商家申请记录"
            },
            "ajax": {

                "dataType": 'json',
                "type": "POST",
                "url": "../../user_center_servlet_action?action=get_store_apply",
                "data": function(){
                    var stat= {"status": statusnow};
                    return stat;

                },

                "cache": "false" // 禁用缓存

            },
            "columns": [

                {data: 'user_id'},
                {data: 'user_name'},
                {
                    data: null,
                    render: function (data, type, full, meta) {
                        console.log(data);
                        console.log(full);
                        sReturn='';
                        sReturn = '<td> <a   class="mail" href="mailto:' + full.mail + '">' + full.mail + '</a> </td>';
                        return sReturn;
                    }

                },
                {
                    data: null,
                    render: function (data, type, full, meta) {
                        sReturn='';
                        var imgs = full.licences.split(';');
                        console.log(imgs);
                        for (var i = 0; i < imgs.length; i++) {
                            sReturn += '<img src="../../assets/global/img/store/' + imgs[i] + '" width="100px" height="100px">';
                        }
                        return sReturn;
                    }
                },
                {data: 'card_number'},
                {data: 'apply_date'},
                {
                    data: null,//'owner.user_name' ,
                    render: function (data, type, full, meta) {
                        sReturn='';
                        var status = full.status;
                        switch (status) {
                            case '0'://待审核
                                sReturn ='<button class="btn btn-circle default blue-stripe"  onclick="javascript:Page.onApply_store(' + full.user_id + ')"  type="button"><i class="fa fa-check-square-o"></i></button>';
                                sReturn =  sReturn + '<button class="btn btn-circle default red-stripe"  onclick="javascript:Page.onRefuse_Appply(' + full.user_id + ')"  type="button"> <i class="fa fa-close"></i></button>';

                                break;

                            case '2'://审核不通过

                              sReturn ='<button class="btn btn-circle default blue-stripe"  onclick="javascript:Page.onApply_store(' + full.user_id + ')"  type="button"><i class="fa fa-check-square-o"></i></button>';

                                break;

                            default://审核通过

                                sReturn =  sReturn + '<button class="btn btn-circle default red-stripe"  onclick="javascript:Page.onRefuse_Appply(' + full.user_id + ')"  type="button"> <i class="fa fa-close" style="width: 20px">注销商家</i></button>';

                                break;
                        }
                        return sReturn;
                    }
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
    }
    var onDeleteRecord = function(user_id){
        if(confirm("您确定要删除这条记录吗？")){
            if(user_id>-1){
                var url="../../"+module+"_"+sub+"_servlet_action";
                var data={};
                data.action="delete_user_record";
                data.user_id=user_id;
                $.post(url,data,function(json){
                    if(json.result_code==0){
                        window.location.reload();
                    }
                })
            }
        }
    };

    var backtoUserList=function () {
        window.location.href="user_list.jsp";

    }

    var onExport_Button =function () {
        var data={};
        data.action ="export_user_list";
        $.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){

            if(json.result_code==0){
                console.log(JSON.stringify(json));
                $("#record_download_div #download_url").attr("href", json.export_url); //原页面跳转
                //$("#record_download_div #download_url").attr("href", "javascript:window.open('"+json.export_url+"')"); 会以新的页面跳转 而非原页面跳转
                $("#record_download_div ").modal("show");
            }else {
                alert("[onExport_Button]与后端交互出现错误: "+json.result_msg);
            }

        })
    }
    var onPrint_Table=function () {
        var data={};
        data.action ="print_user_table";
        $.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){

            if(json.result_code==0){
                console.log(JSON.stringify(json));
                window.location.href="user_list_print_table.jsp";
            }else {
                alert("[onPrint_Table]与后端交互出现错误: "+json.result_msg);
            }

        })
    }


    var OnModifySubmit=function () {
        submitModifyRecord();

    }

    var onModifyRecord =function (user_id) {
        //  window.location.href="user_modify.jsp?id="+id;
        $("#user_modify_div").modal("show");
        initUserRecordView(user_id);
    }

    var onModify_store =function (user_id , update_type) {
        $.ajax({
            url: '../../user_center_servlet_action?action=update_store',  // 后台接口 URL
            type: 'POST',      // 请求类型
            dataType: 'json', // 返回数据类型
            data: { status: update_type , user_id :user_id },
            success: function(data) {
            }
        });
        $("#wait_apply").addClass("active");
        $("#passed").removeClass("active");
        $("#refused").removeClass("active");
        redrawDatatable(0);

    }

    var redrawDatatable =function (data) {
        console.log("redrawDatatable"+ data);

        $(".datatable").dataTable().fnDestroy();

        statusnow =data;
        table.api().ajax.reload();

    }

    var onStatistic_User=function(id){
        window.location.href="user_statistic.jsp";
    }
    //Page return 开始
    return {
        init: function() {
            initPageControl();
        },
        onDeleteRecord:function(user_id){
            onDeleteRecord(user_id);
        },
        onModifyRecord:function(user_id){
            console.log(user_id);
            onModifyRecord(user_id);
        },
        onqueryRecord:function () {
            onqueryRecord();
        } ,
        onApply_store:function (user_id) {
            onModify_store(user_id,1);
        },
        onRefuse_Appply:function (user_id) {
            onModify_store(user_id,2);
        }
    }
}();//Page
/*================================================================================*/
