var module="comment";
var sub="center";
var time00 = 5;
var time11 = time00;
var t;  // 用于验证按钮的60s计时
/*================================================================================*/

jQuery(document).ready(function () {
    Page.init();

})
/* ================================================================================ */
//关于页面的控件生成等操作都放在Page里
var Page = function() {
    /*----------------------------------------入口函数  开始----------------------------------------*/

    var initPageControl = function () {
        pageId = $("#page_id").val();
        console.log("识别到page id了");
        if (pageId == "comment_list") {
            ;
            initCommentList();

        }
    };
    /*----------------------------------------入口函数  结束----------------------------------------*/

    /*----------------------------------------业务函数  开始----------------------------------------*/
    /*------------------------------针对各个页面的入口  开始------------------------------*/
    var initCommentList = function () {

        initGuesthouseListControlEvent();
        initCommentRecordList();

    }
    /*------------------------------针对各个页面的入口 结束------------------------------*/

    var initGuesthouseListControlEvent = function () {

        $('#iq-search-bar #search_btn').click(function () {
            submitSearchRecord();
        });

    }

    /*------------------------------函数----------------------------------------*/
    var getUrlParam=function(name){
        //获取url中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        console.log(r);
        if (r != null) return decodeURI(r[2]); return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
    }

    var initCommentRecordList = function () {
        get_cm_record();

    }

    var get_cm_record = function () {
        var action=getUrlParam("action");
        var data={};
        if(action==""||action==undefined){
            data.action="query_comment";
        }else {
            data.action=action;
        }
        data.query=getUrlParam("query");
        data.orderby=getUrlParam("orderby");
        console.log(data);
        var url = "../../" + module + "_" + sub + "_servlet_action";

        $.post(url, data, function (json) {
        });
    }
    return {
        init: function() {
            initPageControl();
        }
    }
}();//Page