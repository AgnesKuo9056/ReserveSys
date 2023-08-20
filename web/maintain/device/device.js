var module="device";
var sub="file";
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
		if(pageId=="device_list"){
			initDeviceList();
		}
		if(pageId=="device_add"){
			initDeviceAdd();
		}
		if(pageId=="device_modify"){
			initDeviceModify();
		}
		if(pageId=="device_search"){
			initDeviceQuery();
		}
		if(pageId=="print_table"){
			initPrintTable();
		}
		if(pageId=="device_statistic"){
			initDeviceStatistic();
		}
	};
	/*----------------------------------------入口函数  结束----------------------------------------*/
	var columnsData=undefined;
	var recordResult=undefined;
	var chartData =[
			{year:2009,income:23.5,expenses:18.1},
			{year:2010,income:26.2,expenses:22.8},
			{year:2011,income:30.1,expenses:23.9},
			{year:2012,income:29.5,expenses:25.1},
			{year:2013,income:30.6,expenses:27.2,dashLengthLine:5},
			{year:2014,income:34.1,expenses:29.9,dashLengthColumn:5,alpha:.2,additional:"(projection)"}];
	/*----------------------------------------业务函数  开始----------------------------------------*/
	/*------------------------------针对各个页面的入口  开始------------------------------*/
	var initDeviceList=function(){
		initDeviceListControlEvent();
		initDeviceRecordList();
	}
	var initDeviceAdd=function(){
		initDeviceAddControlEvent();
	}
	var initDeviceModify=function(){
		initDeviceModifyControlEvent();
		initDeviceRecordView();
	}
	var initDeviceQuery=function () {
		initDeviceQueryControlEvent();
	}
	initPrintTable=function () {
		initPrintTableView();
	}
	initDeviceStatistic=function () {
		$.ajaxSettings.async =false;
		initDeviceStatisticRecord();
		$.ajaxSettings.async =true;
		initBarChart();
	}
	/*------------------------------针对各个页面的入口 结束------------------------------*/
	var getUrlParam=function(name){
		//获取url中的参数
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		console.log(r);
		if (r != null) return decodeURI(r[2]); return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
	}
	var initDeviceListControlEvent=function(){
		$("#help_button").click(function() {help();});
		$('#query_btn').click(function() {onqueryRecord();});
		$('#add_button').click(function() {onAddRecord();});
		$('#button_datatable').click(function() {onButton_datatable();});
		$('#button_definetable').click(function() {onButton_definetable();});
		$('#button_bar').click(function() {onButton_bar();});
		$('#export_button').click(function() {onExport_Button();});
		$('#print_table').click(function() {onPrint_Table();});
		$('#statistic_device').click(function() {onStatistic_Device();});



	}
	var initDeviceAddControlEvent=function(){
		$("#help_button").click(function() {help();});
		$('#add_button').click(function() {submitAddRecord();});
	}
	var initDeviceModifyControlEvent=function(){
		$("#help_button").click(function() {help();});
		$('#modify_button').click(function() {submitModifyRecord();});
	}
	var initDeviceQueryControlEvent=function () {
		$("#help_button").click(function() {help();});
		$('#search_btn').click(function() {submitSearchRecord();});
		$('#back_btn').click(function() {backtoDeviceList();});
	}
	var initBarChart =function () {
		var e=AmCharts.makeChart("chart_1",
			{type:"serial",theme:"light",
				pathToImages:App.getGlobalPluginsPath()+"amcharts/amcharts/images/",
				autoMargins:!1,
				marginLeft:30,
				marginRight:8,
				marginTop:10,
				marginBottom:26,
				fontFamily:"Open Sans",
				color:"#888",
				dataProvider: chartData,
				valueAxes:[{
					axisAlpha:0,position:"left"
				}], startDuration:1,
				graphs:[{
						alphaField:"alpha",
						balloonText:"<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b> [[additional]]</span>",
						dashLengthField:"dashLengthColumn",
						fillAlphas:1,
						title:"Income",
						type:"column",
						valueField:"income"
					},{
						balloonText:"<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b> [[additional]]</span>",
						bullet:"round",
						dashLengthField:"dashLengthLine",
						lineThickness:3,
						bulletSize:7,
						bulletBorderAlpha:1,
						bulletColor:"#FFFFFF",
						useLineColorForBulletBorder:!0,
						bulletBorderThickness:3,
						fillAlphas:0,
						lineAlpha:1,
						title:"Expenses",
						valueField:"expenses"
				}],
				categoryField:"year",
				categoryAxis:{
					gridPosition:"start",
					axisAlpha:0,
					tickLength:0
			}
			});
		$("#chart_1").closest(".portlet").find(".fullscreen").click(function(){
			e.invalidateSize()
		})
	}

	var initDeviceRecordView=function(){

		var id=getUrlParam("id");
		var data={};
		data.action="get_device_record";
		data.id=id;
		$.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){
			console.log(JSON.stringify(json));
			if(json.result_code==0){
				var list=json.aaData;
				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						$("#device_id").val(record.device_id);
						$("#device_name").val(record.device_name);
						$("#creator").val(record.creator);
						$("#create_time").val(record.create_time);

					}
				}
			}
		})
	}
	var initDeviceStatisticRecord =function () {
		var data={};
		data.action="get_gps_count_by_hour";
		$.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){
			var html="";
			if(json.result_code==0){
				console.log(JSON.stringify(json));
				var list=json.aaData;
				if(list!=undefined && list.length>0){
					changeResultDataToCahrtData(list,chartData);
					console.log(JSON.stringify(chartData));
				}else {
					alert("[initDeviceStatisticRecord]查询出错"+json.result_msg);
				}
			}
		})
	}
	var changeResultDataToCahrtData =function (list,chartData) {
		//list 元素是 time_interval 和 total
		//chartData 元素是 data 和 duration
		for (var i=0;i<list.length;i++){
			list[i].time_interval = i;
			var json = {"year":list[i].time_interval,"income":list[i].total,"expenses":list[i].total};
			chartData.push(json);
		}
	}
	var onAddRecord=function(){
		window.location.href="sue_add.jsp";
	}
	var onButton_datatable=function () {
		$("#datatable_tab").show();
		$("#table_tab").hide();
		$("#bar_tab").hide();
	}
	var onButton_definetable=function () {
		$("#table_tab").show();
		$("#datatable_tab").hide();
		$("#bar_tab").hide();
	}
	var onButton_bar=function () {
		$("#bar_tab").show();
		$("#table_tab").hide();
		$("#datatable_tab").hide();
	}
	var submitAddRecord=function(){
		var url="../../device_file_servlet_action";
		var data={};
		data.action="add_device_record";
		data.device_id=$("#device_id").val();
		data.device_name=$("#device_name").val();
		data.creator=$("#creator").val();
		data.create_time=$("#create_time").val();
		$.post(url,data,function(json){
			if(json.result_code==0){
				alert("已经完成设备添加。");
				window.location.href="device_list.jsp";
			}
		});
	}

	var submitSearchRecord =function () {
				$('.datatable').dataTable( {
					"paging":true,
					"searching":false,
					"oLanguage": {
						"aria": {
							"sortAscending": ": activate to sort column ascending",
							"sortDescending": ": activate to sort column descending"
						},
						"sProcessing":   "处理中...",
						"sLengthMenu":   "_MENU_ 记录/页",
						"sZeroRecords":  "没有匹配的记录",
						"sInfo":         "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
						"sInfoEmpty":    "显示第 0 至 0 项记录，共 0 项",
						"sInfoFiltered": "(由 _MAX_ 项记录过滤)",
						"sInfoPostFix":  "",
						"sSearch":       "过滤:",
						"oPaginate": {
							"sFirst":    "首页",
							"sPrevious": "上页",
							"sNext":     "下页",
							"sLast":     "末页"
						}
					},
					"aoColumns": [
						{"mRender": function(data, type, full) {
								console.log(data);
								console.log(full);
								sReturn = '<input type="checkbox" class="checkboxes" value="'+full.id+'"/>';
								return sReturn;
							},"orderable": false
						},{"mRender": function(data, type, full) {
								console.log(data);
								console.log(full);
								sReturn = '<div>'+full.device_id+'</div>';
								return sReturn;
							},"orderable": false
						},{"mRender": function(data, type, full) {
								console.log(data);
								console.log(full);
								sReturn = full.device_name;
								return sReturn;
							},"orderable": false
						},{"mRender": function(data, type, full) {
								console.log(data);
								console.log(full);
								sReturn = '<div><font color="red">'+full.creator+'</font></div>';
								return sReturn;
							},"orderable": false
						},{"mRender": function(data, type, full) {
								console.log(data);
								console.log(full);
								sReturn = '<div><font color="red">'+full.create_time+'</font></div>'+'<div><a href="javascript:Page.onModifyRecord('+full.id+')">【修改记录】</a><a href="javascript:Page.onDeleteRecord('+full.id+')">【删除记录】</a><div>';
								return sReturn;
							},"orderable": false
						}],
					"aLengthMenu": [[5,10,15,20,25,40,50,-1],[5,10,15,20,25,40,50,"所有记录"]],
					"fnDrawCallback": function(){$(".checkboxes").uniform();$(".group-checkable").uniform();},
					//"sAjaxSource": "get_record.jsp"
					"sAjaxSource": "../../"+module+"_"+sub+"_servlet_action?action=query_device_record&device_id="+$("#qu_device_id").val()
				});
				$('.datatable').find('.group-checkable').change(function () {
					var set = jQuery(this).attr("data-set");
					var checked = jQuery(this).is(":checked");
					jQuery(set).each(function () {
						if (checked) {
							$(this).attr("checked", true);
							$(this).parents('tr').addClass("active");
						} else {
							$(this).attr("checked", false);
							$(this).parents('tr').removeClass("active");
						}
					});
					jQuery.uniform.update(set);
				});
				$('.datatable').on('change', 'tbody tr .checkboxes', function () {
					$(this).parents('tr').toggleClass("active");
				});

			}
	var initPrintTableView=function () {
			$(".page-sidebar-wrapper").hide();
			$(".page-header").hide();
			$(".page-content").attr("style","margin-left: 0px");
			// $(".page-content").attr("style","margin-bottom: 0px");
			$(".page-container").attr("style","margin-top: 0px");

		$.post("../../"+module+"_"+sub+"_servlet_action?action=get_device_record",function(json){
			if(json.result_code==0){
				var list=json.aaData;
				var html="";
				html=html+"							<thead>";
				html=html+"                            <tr>";
				html=html+"                                <th>";
				html=html+"                                    <i class=\"fa fa-briefcase\"></i> 设备ID </th>";
				html=html+"                                <th class=\"hidden-xs\">";
				html=html+"                                    <i class=\"fa fa-user\"></i> 设备名称 </th>";
				html=html+"                                <th>";
				html=html+"                                    <i class=\"fa fa-shopping-cart\"></i> 创建者 </th>";
				html=html+"                                <th> </th>";
				html=html+"                            </tr>";
				html=html+"                            </thead>";
				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						html=html+"		                      <tr>";
						html=html+"                                <td class=\"highlight\">";
						html=html+"                                    <div class=\"success\"></div>";
						html=html+"                                    <a href=\"javascript:;\"> "+record.device_id+" </a>";
						html=html+"                                </td>";
						html=html+"                                <td class=\"hidden-xs\"> "+record.device_name+" </td>";
						html=html+"                                <td> "+record.creator+" </td>";
						html=html+"                                <td>";
						html=html+"                                    <a href=\"javascript:;\" class=\"btn btn-outline btn-circle btn-sm purple\">";
						html=html+"                                        <i class=\"fa fa-edit\"></i> "+record.create_time+" </a>";
						html=html+"                                </td>";
						html=html+"                            </tr>";
					}
				}
				$("#record_print_table").html(html);
			}
		})
	}

	var submitModifyRecord=function(){
		if(confirm("您确定要修改该记录吗？")){
			var id=getUrlParam("id");
			var url="../../device_file_servlet_action";
			var data={};
			data.action="modify_device_record";
			data.id=id;
			data.device_id=$("#device_id").val();
			data.device_name=$("#device_name").val();
			data.creator=$("#creator").val();
			data.create_time=$("#create_time").val();
			$.post(url,data,function(json){
				if(json.result_code==0){
					alert("已经完成设备修改。");
					window.location.href="device_list.jsp";
				}
			});
		}
	}

	
	var initDeviceRecordList=function(){
		getDeviceRecordList_datatable();
		getDeviceRecordList_table();
		getDeviceRecordList_bar();
	}

	var initDeviceMobileRecord=function(){
		getDeviceMobileRecord();
	}
	var getDeviceRecordList1=function(){
		$.post(module+"_"+sub+"_servlet_action?action=get_device_record",function(json){
			console.log(JSON.stringify(json));
			if(json.result_code==0){
				var list=json.aaData;
				var html="";
				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						html=html+"<div>序号："+i+"<div>";
						html=html+"<div>设备ID："+record.device_id+"<div>";
						html=html+"<div>设备名称："+record.device_name+"<div>";
						html=html+"<div><a href=\"javascript:Page.onModifyRecord("+record.id+")\">【修改记录】</a><a href=\"javascript:Page.onDeleteRecord("+record.id+")\">【删除记录】</a><div>";
						html=html+"<p>";
					}
				}
				$("#record_list_div").html(html);
			}
		})
	}
	var getDeviceRecordList_table=function(){
		console.log("进入getDeviceRecordList2了");
		$.post("../../"+module+"_"+sub+"_servlet_action?action=get_device_record",function(json){
			console.log(JSON.stringify(json));
			if(json.result_code==0){
				var list=json.aaData;
				var html="";

				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						html=html+"<tr class=\"active\">";
						html=html+	"<td>";
						html=html+"<input type=\"checkbox\" class=\"checkboxes\" value=\""+record.id+"\">";
						html=html+	"</td>";
						html=html+	"<td>";
						html=html+	"  "+record.device_id;
						html=html+	" </td>";
						html=html+	"<td>";
						html=html+	"  "+record.device_name;
						html=html+	" </td>";
						html=html+	"<td>";
						html=html+	"  "+record.creator;
						html=html+	" </td>";
						html=html+	"<td>";
						html=html+	"  "+record.create_time;
						html=html+	" </td>";
						html=html+	"<td>";
						html=html+	" <a href=\"javascript:Page.onModifyRecord("+record.id+")\">【修改记录】</a>";
						html=html+	" </td>";
						html=html+	"<td> ";
						html=html+  "<a href=\"javascript:Page.onDeleteRecord("+record.id+")\">【删除记录】</a>";
						html=html+	"</td>";
						html=html+"</tr>";

					}
				}

				$("#record_table_content_div").html(html);
			}
		})
	}

	var getDeviceRecordList_bar=function () {
		$.post("../../"+module+"_"+sub+"_servlet_action?action=get_device_record",function(json){
			console.log(JSON.stringify(json));
			if(json.result_code==0){
				var list=json.aaData;
				var html="";
				// html = html +"                                    <thead>\n" +
				// 	"                                    <tr>\n" +
				// 	"                                        <th class=\"table-checkbox\"><input type=\"checkbox\" class=\"group-checkable\" data-set=\"#record_table_content_div .checkboxes \" /></th>\n" +
				// 	"                                        <th>设备ID</th>\n" +
				// 	"                                        <th>设备名称</th>\n" +
				// 	"                                        <th>创建人</th>\n" +
				// 	"                                        <th>创建时间</th>\n" +
				// 	"                                        <th>modify</th>\n" +
				// 	"                                        <th>delete</th>\n" +
				// 	"                                    </tr>\n" +
				// 	"                                    </thead>";
				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						html=html+"         <div class=\"media\" style=\"padding: 10px\">";
						html=html+"                        <div class=\"media-left\">";
						html=html+"                            <a href=\"#\">";
						html=html+"                                <img class=\"media-object\" alt=\"\" src=\"../../assets/pages/img/avatars/team1.jpg\" style=\"width: 50px; height: 50px; border-radius: 50px;border-top-left-radius: 50px;border-top-right-radius: 50px; border-bottom-right-radius: 50px;border-bottom-left-radius: 50px;\"> </a>";
						html=html+"                        </div>";
						html=html+"                        <div class=\"media-body\">";
						html=html+"                            <h4 class=\"media-heading\">";
						html=html+"                                <a > equ ID : "+record.id+"</a> create in ";
						html=html+"                                <span class=\"c-date\">"+record.creator+"  "+record.create_time+"</span>";
						html=html+"                            </h4>";
						html=html+"                            <p>";
						html=html+"                                设备名称 :  <a > "+record.device_name+"</a> ";
						html=html+"<a href=\"javascript:Page.onModifyRecord("+record.id+")\">【修改记录】</a><a href=\"javascript:Page.onDeleteRecord("+record.id+")\">【删除记录】</a>";
						html=html+"                            </p>";
						html=html+"                        </div>";
						html=html+"                    </div>";
						html=html+"                    <hr style=\"background-color:blue; height:1px; border:none;\">";
					}
				}
				$("#record_bar_div").html(html);
			}
		})
	}

	var getDeviceRecordList_datatable=function () {
		$('.datatable').dataTable( {
			"paging":true,
			"searching":false,
			"oLanguage": {
				"aria": {
					"sortAscending": ": activate to sort column ascending",
					"sortDescending": ": activate to sort column descending"
				},
				"sProcessing":   "处理中...",
				"sLengthMenu":   "_MENU_ 记录/页",
				"sZeroRecords":  "没有匹配的记录",
				"sInfo":         "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
				"sInfoEmpty":    "显示第 0 至 0 项记录，共 0 项",
				"sInfoFiltered": "(由 _MAX_ 项记录过滤)",
				"sInfoPostFix":  "",
				"sSearch":       "过滤:",
				"oPaginate": {
					"sFirst":    "首页",
					"sPrevious": "上页",
					"sNext":     "下页",
					"sLast":     "末页"
				}
			},
			"aoColumns": [
				{"mRender": function(data, type, full) {
				console.log(data);
				console.log(full);
					sReturn = '<input type="checkbox" class="checkboxes" value="'+full.id+'"/>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = '<div>'+full.device_id+'</div>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = full.device_name;
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = '<div><font color="red">'+full.creator+'</font></div>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
				sReturn = '<div><font color="red">'+full.create_time+'</font></div>'+'<div><a href="javascript:Page.onModifyRecord('+full.id+')">【修改记录】</a><a href="javascript:Page.onDeleteRecord('+full.id+')">【删除记录】</a><div>';
					return sReturn;
				},"orderable": false
			}],
			"aLengthMenu": [[5,10,15,20,25,40,50,-1],[5,10,15,20,25,40,50,"所有记录"]],
			"fnDrawCallback": function(){$(".checkboxes").uniform();$(".group-checkable").uniform();},
			//"sAjaxSource": "get_record.jsp"
			"sAjaxSource": "../../"+module+"_"+sub+"_servlet_action?action=get_device_record"
		});
		$('.datatable').find('.group-checkable').change(function () {
			var set = jQuery(this).attr("data-set");
			var checked = jQuery(this).is(":checked");
			jQuery(set).each(function () {
				if (checked) {
					$(this).attr("checked", true);
					$(this).parents('tr').addClass("active");
				} else {
					$(this).attr("checked", false);
					$(this).parents('tr').removeClass("active");
				}
			});
			jQuery.uniform.update(set);
		});
		$('.datatable').on('change', 'tbody tr .checkboxes', function () {
			$(this).parents('tr').toggleClass("active");
		});

	}

	var onDeleteRecord = function(id){
		if(confirm("您确定要删除这条记录吗？")){
			if(id>-1){
				var url="../../device_file_servlet_action";
				var data={};
				data.action="delete_device_record";
				data.id=id;
				$.post(url,data,function(json){
					if(json.result_code==0){
						window.location.href="device_list.jsp";
					}
				})
			}
		}
	};

	var backtoDeviceList=function () {
		window.location.href="device_list.jsp";

	}
	var onqueryRecord = function () {
		window.location.href="guesthouse_search.jsp";
	}
	var onExport_Button =function () {
		var data={};
		data.action ="export_device_list";
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
		data.action ="print_table";
		$.post("../../"+module+"_"+sub+"_servlet_action",data,function(json){

			if(json.result_code==0){
				console.log(JSON.stringify(json));
				window.location.href="guesthouse_list_print_table.jsp";
			}else {
				alert("[onPrint_Table]与后端交互出现错误: "+json.result_msg);
			}

		})
	}
	var onModifyRecord =function () {
		window.location.href="guesthouse_modify.jsp?id="+id;
	}

	var onStatistic_Device=function(id){
		window.location.href="device_statistic.jsp";
	}
	//Page return 开始
	return {
		init: function() {
			initPageControl();
		},
		onDeleteRecord:function(id){
			onDeleteRecord(id);
		},
		onModifyRecord:function(id){
			console.log(id);
			onModifyRecord(id);
		}
	}
}();//Page
/*================================================================================*/
