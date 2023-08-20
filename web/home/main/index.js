var module="../../user";
var sub="center";
var t ;//internal的赋值
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


         var pageId=$("#page_id").val();

        console.log("识别到page id了:" +pageId);


        if(pageId=="index"){
            user_role=$("#user_role").val();
            initPage_view_bar(function() {
                console.log("user_role:" +user_role); // 这里输出应该是正确的值
                geteChart("latestfiveyear",0);
            });
            initgetComment();
            initIndexControlEvent();
            initcountbar();
            console.log("user_role:" +user_role);


        }
        if(pageId=="login"){
            initLoginControlEvent();

        }

    };
    /*********************************************control********************************************************/
    var initIndexControlEvent=function(){
        // 获取选择框元素
        var selectBox = document.getElementById("month_e");
        var selectBox2 = document.getElementById("month");
        // 监听选择框变化事件
        selectBox.addEventListener("change", function() {
            var selectedValue = selectBox.value;
            if (selectedValue !== "") {
                // 执行某些操作
                geteChart("month",1,selectedValue)
            }
        });

        // 监听选择框变化事件
        selectBox2.addEventListener("change", function() {
            var selectedValue = selectBox.value;
            if (selectedValue !== "") {
                // 执行某些操作
                console.log("选中了" + selectedValue + "年份");
                geteChart("month",2,selectedValue)
            }
        });

        $("#amcharts_search").click(function () {
            geteChart("latestfiveyear",2);

        });
        $("#echarts_search").click(function () {
            geteChart("latestfiveyear",1);

        });
        $("#season").click(function () {
            geteChart("season",2)
        });

        $("#season_e").click(function () {
            geteChart("season",1)
        });

        $("#five_year_e").click(function () {
            geteChart("latestfiveyear",1)
        });

        $("#five_year").click(function () {
            geteChart("latestfiveyear",2)
        });

    }

    var initLoginControlEvent = function () {
        $("#login_btn").click(function () {
            submitLogin();
        });
        $('#register-submit-btn').click(function () {
            submitRegist();
        });
    }
    /*----------------------------------------入口函数  结束----------------------------------------*/
    var getUrlParam=function(name){
        //获取url中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        console.log(r);
        if (r != null) return decodeURI(r[2]); return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
    }
/////////echart基本参数///////////////////////////////////////////////////////////////////////////////////////////
    // x轴初始数据为近五年的年份
    var xAxisData = [];
    var chartData =[];
    var amchartData=[];
    var legendData=[];
    var initxAxis =function (xindex) {

        if(xindex=="latestfiveyear") {
            xAxisData=[];
            for (var i = (new Date().getFullYear() - 4); i <= new Date().getFullYear(); i++) {
                xAxisData.push(i);
            }
        }else if(xindex=="month"){
            xAxisData = ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];
        }
        else if(xindex=="season"){
            xAxisData = ['春季(3-5月)', '夏季(6-8月)', '秋季(9-11月)', '冬季(12-2月)'];

        }
    }

    ///////echart相关函数///////////////////////////////////////////////////////////////////////////

    //对统计表刷新发送请求等数据
    function geteChart  (xindex,operate,selectedValue){
         xAxisData = [];
         chartData =[];
        amchartData=[];
         legendData=[];
        initxAxis(xindex);
        if(operate==0) {
            //全部初始化
            $.ajaxSettings.async = false;
            initStatisticRecordEchart(xindex, $("#echarts_guesthouset_search option:selected").val());
            initStatisticRecordAmchart(xindex, $("amcharts_order_search").val())
            $.ajaxSettings.async = true;
            initEchart();
            initAmchart();
        }else if(operate==1){
            //刷新查询民宿统计
            $.ajaxSettings.async = false;
            initStatisticRecordEchart(xindex, $("#echarts_guesthouset_search option:selected").val(),selectedValue);
            $.ajaxSettings.async = true;
            // 清空旧图表
            echarts_guesthouse.clear();
            initEchart();
        }else if(operate==2){
            //刷新查询订单统计
            $.ajaxSettings.async = false;
            initStatisticRecordAmchart(xindex, $("amcharts_order_search").val()),selectedValue;
            $.ajaxSettings.async = true;
            e.dataProvider = AmchartData;
            // 重绘图表
            e.validateData();
        }
    }

    ///民宿统计表
    var echarts_guesthouse;
    function initEchart  () {
        echarts_guesthouse= echarts.init(document.getElementById('echarts_guesthouse'));
        echarts_guesthouse.setOption({
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: legendData
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {
                        show: true
                    },
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    magicType: {
                        show: true,
                        type: ['line', 'bar']
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            calculable: true,
            xAxis: [{
                type: 'category',
                data: xAxisData
            }],
            yAxis: [{
                type: 'value',
                splitArea: {
                    show: true
                }
            }],
            series:chartData
        });

    }

    ///订单统计表
    var e;
     var initAmchart =function () {
         e =AmCharts.makeChart("amcharts_order",
             {type:"serial",theme:"light",
                 pathToImages:App.getGlobalPluginsPath()+"amcharts/amcharts/images/",
                 autoMargins:!1,
                 marginLeft:30,
                 marginRight:8,
                 marginTop:10,
                 marginBottom:26,
                 fontFamily:"Open Sans",
                 color:"#888",
                 dataProvider: amchartData,
                startDuration:1,
                 graphs: [{
                     valueAxis: "v1",
                     alphaField: "alpha",
                     balloonText: "<span style='font-size:13px;'>[[title]] 有<b>[[value]]</b> 笔</span>",
                     dashLengthField: "dashLengthColumn",
                     fillAlphas: 1,
                     title: "订单数量",
                     type: "column",
                     valueField: "order_num"
                 }, {
                     valueAxis: "v2",
                     balloonText: "<span style='font-size:13px;'>[[title]] :<b>[[value]]</b> RMB</span>",
                     bullet: "round",
                     dashLengthField: "dashLengthLine",
                     lineThickness: 3,
                     bulletSize: 7,
                     bulletBorderAlpha: 1,
                     bulletColor: "#FFFFFF",
                     useLineColorForBulletBorder: true,
                     bulletBorderThickness: 3,
                     fillAlphas: 0,
                     lineAlpha: 1,
                     title: "收入",
                     valueField: "revenue"
                 }],
                 categoryField: "groupby",
                 categoryAxis:{
                     gridPosition:"start",
                     axisAlpha:0,
                     tickLength:0
                 },
                 valueAxes: [{
                     id: "v1",
                     position: "left"
                 }, {
                     id: "v2",
                     position: "right"
                 }],
             });
         $("#chart_1").closest(".portlet").find(".fullscreen").click(function(){
             e.invalidateSize()
         })
     }

     //get_gh_statistic  //egroupby /esearch //
    function initStatisticRecordEchart  (egroupby,esearch,selectedValue) {
        var data={};
        data.action="get_gh_statistic";
        data.egroupby =egroupby;
        data.esearch=esearch;
        data.selectedValue=selectedValue;
        $.post("../../guesthouse_center_servlet_action",data,function(json){
            var html="";
            if(json.result_code==0){
                console.log(JSON.stringify(json));
                var list=json.aaData;
                if(list!=undefined && list.length>0){
                    changeResultDataToECahrtData(list,egroupby);
                    console.log(JSON.stringify(chartData));
                }else {
                    alert("[initStatisticRecordEchart]查询出错"+json.result_msg);
                }
            }
        })
    }
    //get_order_statistic //amgroupby//amsearch
    function initStatisticRecordAmchart  (amgroupby,amsearch,selectedValue) {
        var data={};
        data.action="get_order_statistic";
        data.amgroupby =amgroupby;
        data.amsearch=amsearch;
        data.selectedValue=selectedValue;
        $.post("../../guesthouse_order_center_servlet_action",data,function(json){
            var html="";
            if(json.result_code==0){
                console.log(JSON.stringify(json));
                var amlist = json.aaData;
                if(amlist!=undefined && amlist.length>0){
                    changeResultDataToAmCahrtData(amlist,amgroupby);
                    console.log(JSON.stringify(amchartData));
                }else {
                    alert("[initStatisticRecordAmchart]查询出错"+json.result_msg);
                }
            }
        })
    }

    ///订单统计表填装数据
    function changeResultDataToAmCahrtData (list,groupby) {
        var fianlData={};
        var datalength="";
        var start="";
        if(groupby=="latestfiveyear") {
            //根据近5年
            datalength = 5;
            let yearData = {}; // 创建一个对象保存按季度分组的数据
            list.forEach(item => {
                yearData[item.year] = {
                    "groupby": item.year, // 对应quarter
                    "order_num": item.order_num, // 对应order_num
                    "revenue": item.revenue// 对应revenue
                };
            });
            fianlData=yearData;
            start=xAxisData[0];
        }else if(groupby=="month"){
            // 根据某年的12个月份遍历数据，将数据按照不同民宿ID分组存储
            datalength = 12;
            let monthData = {}; // 创建一个对象保存按季度分组的数据
            list.forEach(item => {
                monthData[item.month] = {
                    "groupby": item.month, // 对应quarter
                    "order_num": item.order_num, // 对应order_num
                    "revenue": item.revenue// 对应revenue
                };
            });
            fianlData=monthData;
            start=1;
        }
        else if(groupby=="season"){
            // 假设返回结果保存在resultList数组中
            datalength = 4;
            let quarterData = {}; // 创建一个对象保存按季度分组的数据
            list.forEach(item => {
                quarterData[item.quarter] = {
                    "groupby": item.quarter, // 对应quarter
                    "order_num": item.order_num, // 对应order_num
                    "revenue": item.revenue// 对应revenue
                };
            });
            fianlData=quarterData;
            start=0;
        }

        console.log(fianlData );
        console.log(legendData);
        console.log(start);
        // 根据季 月 年 数据组装成amcharts需要的格式
        for (let i = start; i < start+datalength; i++) {
            if (fianlData[i]) {
                amchartData.push(fianlData[i]);
            } else {
                // 若数据中没有对应季度的数据，则添加一个空对象
                amchartData.push({ "groupby": i, "order_num": 0, "revenue": 0 });
            }
        }

    }

    ///民宿统计表填装数据
    function changeResultDataToECahrtData  (data,groupby) {
         console.log(data);
        var dataMap = {};


        if(user_role=="1"){
            console.log(user_role);
            if(groupby=="latestfiveyear") {
                //根据近5年
                data.forEach(item => {
                    if (!dataMap.hasOwnProperty(item.gh_id)) {
                        // 如果当前民宿ID不存在，则创建一个新键名，并存入数组
                        dataMap[item.gh_id] = new Array(5).fill(0);
                        let index = item.year - (new Date().getFullYear() - 4);
                        dataMap[item.gh_id][index] = item.total_days;
                    } else {
                        // 如果当前民宿ID已经存在，则将民宿预定天数存入
                        let index = item.year - (new Date().getFullYear() - 4);
                        dataMap[item.gh_id][index] = item.total_days;
                    }
                });

            }else if(groupby=="month"){
                // 根据某年的12个月份遍历数据，将数据按照不同民宿ID分组存储
                data.forEach(item => {
                    if (!dataMap.hasOwnProperty(item.gh_id)) {
                        // 如果当前民宿ID不存在，则创建一个新键名，并存入数组
                        dataMap[item.gh_id] = new Array(12).fill(0);
                        dataMap[item.gh_id][item.month-1] = item.total_days;
                    } else {
                        // 如果当前民宿ID已经存在，则将订单数量存入数组
                        dataMap[item.gh_id][item.month-1] = item.total_days;
                    }
                });
            }
            else if(groupby=="season"){
                data.forEach(item => {
                    if (!dataMap.hasOwnProperty(item.gh_id)) {
                        // 如果当前民宿ID不存在，则创建一个新键名，并存入数组
                        dataMap[item.gh_id] = new Array(4).fill(0);
                        dataMap[item.gh_id][item.quarter] = item.total_days;
                    } else {
                        // 如果当前民宿ID已经存在，则将订单数量存入数组
                        dataMap[item.gh_id][item.quarter] = item.total_days;
                    }
                });
            }
            console.log(dataMap);
            // 根据数据组装成echarts需要的格式
            for (let key in dataMap) {
                console.log(typeof (key));
                data.forEach(item => {
                    console.log(typeof (item.gh_id));
                });

                let name = data.find(item => item.gh_id === key).gh_name; // 获取 gh_id 为当前 key 的数据的 gh_name 值
                chartData.push({
                    name: name,
                    type: 'bar',
                    data: dataMap[key]
                });
                legendData.push(name);
            }
        }
        else if(user_role=="0") {
            console.log(user_role);
            if(groupby=="latestfiveyear") {
                //根据近5年
                dataMap = new Array(5).fill(0);
                data.forEach(item => {
                    // 如果当前民宿ID不存在，则创建一个新键名，并存入数组

                    let index = item.year - (new Date().getFullYear() - 4);
                    dataMap[index] = item.total_days;
                });

            }else if(groupby=="month"){
                // 根据某年的12个月份遍历数据，将数据按照不同民宿ID分组存储
                dataMap = new Array(12).fill(0);
                data.forEach(item => {
                    // 如果当前民宿ID不存在，则创建一个新键名，并存入数组

                    let index = item.year - (new Date().getFullYear() - 4);
                    dataMap[index] = item.total_days;
                });
            }
            else if(groupby=="season"){
                dataMap = new Array(4).fill(0);
                data.forEach(item => {
                    // 如果当前民宿ID不存在，则创建一个新键名，并存入数组

                    let index = item.year - (new Date().getFullYear() - 4);
                    dataMap[index] = item.total_days;
                });
            }
            console.log(dataMap);
                chartData.push({
                    name: "预定天数",
                    type: 'bar',
                    data: dataMap
                });
                legendData.push("该月民宿预定天数");
            }



        console.log("chartData:"+chartData);
        console.log("legendData:"+legendData)

    }



    /*********************************************************************************************/

    var initgetComment= function () {
        $.post("../../comment_center_servlet_action?action=owner_get_cm_record ",function(json){
        })
    }

    var initcountbar =function () {
        $.post("../../guesthouse_order_center_servlet_action?action=get_order_count ",function(json){
            var html="";
            if(json.result_code==0){
                console.log(JSON.stringify(json));
                var count_ghorder = json.aaData;
                // 设置data-value属性
                $('#order_count').attr('data-value', count_ghorder[0].order_num);
                // 设置文本值
                $('#order_count').text(count_ghorder[0].order_num);

            }
        })
        $.post("../../guesthouse_center_servlet_action?action=get_gh_count",function(json){
            var html="";
            if(json.result_code==0){
                console.log(JSON.stringify(json));
                var count_gh = json.aaData;

                // 设置data-value属性
                $('#gh_count').attr('data-value', count_gh[0].order_num);
                // 设置文本值
                $('#gh_count').text(count_gh[0].order_num);
            }
        })
        $.post("../../guesthouse_center_servlet_action?action=get_gh_rate",function(json){
            var html="";
            if(json.result_code==0){
                console.log(JSON.stringify(json));
                var count_gh = json.aaData;

                // 设置data-value属性
                $('#housing_rate').attr('data-value', count_gh[0].status_percentage);
                // 设置文本值
                $('#housing_rate').text(count_gh[0].status_percentage);
            }
        })
        $.post("../../guesthouse_center_servlet_action?action=get_gh_goodRate",function(json){
            var html="";
            if(json.result_code==0){
                console.log(JSON.stringify(json));
                var count_gh = json.aaData;

                // 设置data-value属性
                $('#good_rate').attr('data-value', count_gh[0].percentage);
                // 设置文本值
                $('#good_rate').text(count_gh[0].percentage);
            }
        })


    }
    var submitRegist = function () {
        var a=checkrgform();
        console.log(a);
        if(a==true){
            console.log("submitRegist");
            var data = {};
            data.action = "regist";
            data.user_name = $("#user_name").val();
            data.phone_number = $("#phone_number").val();
            data.anony_name = $("#anony_name").val();
            data.password_strength = $("#password_strength").val();
            $.post("../../user_center_servlet_action", data, function (json) {
                window.location.href="index.jsp";
            })
            }
        }

    var submitLogin = function () {
        var c =checkform();
        console.log(c);
      if(c==true){
          console.log("submitLogin");
          var data = {};
          data.action = "login";
          data.user_id = $("#user_id").val();
          data.password = $("#password").val();
          $.post("../../user_center_servlet_action", data, function (json) {
                var redirect = json.redirect_url;
                window.location.href="../../"+redirect;

          })
      }
    }

    var initPage_view_bar=function( callback){
        var data={};
        data.action="get_authority";
        var is_ad;
        $.post(module+"_"+sub+"_servlet_action",data,function(json){
            console.log(JSON.stringify(json));
                is_ad = json.is_admin;
                console.log(is_ad);
            console.log(typeof(is_ad));

            console.log(is_ad == "true");
            console.log(is_ad);

           // var obj = JSON.parse(json);//JSON字符串转对象
            if(json.is_admin &&!json.is_store){
              user_role= "0";

            }else if(json.is_store){
                user_role= "1";
            }
            else {
                user_role= "2";

            }

        })
        console.log("user_role:" +user_role);
        if (callback) {
            callback(); // 调用回调函数
        }

    }

    var  checkform =function () {
        console.log("checkform");
        //判断登陆是否合法
        var user_id =$("#user_id").val();
        var password = $("#password").val();
        if(user_id =="" || user_id ==null){
            document.getElementById("message_login").innerHTML ="用户ID不得为空";
            $("#mes").show();
            t= setInterval(function () {
                $("#mes").hide();
                clearInterval(t);
            }, 3000);  // 5s调用一次
            return false;
        }
        if (password =="" || password==null){
            document.getElementById("message_login").innerHTML ="密码不得为空";
            $("#mes").show();
            t= setInterval(function () {
                $("#mes").hide();
                clearInterval(t);

            }, 3000);  // 5s调用一次
            return false;
        }
        return true;
    }

    var  checkrgform =function () {
        console.log("再checkrgform者里");
        //判断登陆是否合法
        var phone_number =$("#phone_number").val();
        var password_strength =$("#password_strength").val();
        var regexp = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$');
        var phoneRegex = /^[1][3,4,5,7,8,9][0-9]{9}$/;

        if( !phoneRegex.test(phone_number)){
            console.log("电话簿ok");
            $("#phone_error").show();
           t= setInterval(function () {
               $("#phone_error").hide();
                clearInterval(t);

            }, 3000);  // 5s调用一次
        }
        if( !regexp.test(password_strength)){
            console.log("密码不ok");
            $("#psw_error").css("color", "red");
            t=setInterval(function () {
                $("#psw_error").css("color", "black");
                clearInterval(t);

            }, 3000);  // 5s调用一次
        }

        return   regexp.test(password_strength)&& phoneRegex.test(phone_number);
    }
    function onsearch_comment() {
        var data={};
        data.action="owner_get_cm_record";
        data.search=document.querySelector('input[name="search_comment"]').value;
        $.post("../../comment_center_servlet_action",data,function(json){
        })

        $("#general-item-list").load(location.href + " #general-item-list");
    }

    //Page return 开始
    return {
        init: function() {
            initPageControl();
        },
        search_comment :function () {
            onsearch_comment();
        }
    }
}();//Page
/*================================================================================*/
