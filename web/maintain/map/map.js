var module="map";
var sub="center";
var time00 = 5;
var time11 = time00;
var t;  // 用于验证按钮的60s计时
var onadvancedquery =false;
var getdata ={};//用户进接搜索所有条件
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
        if (pageId == "reserve_list") {
            initPage_header();
            initGuesthouseList();

        }
        if(pageId=="reserve_detail"){
          //  initDetail();
        }
    };
    /*----------------------------------------入口函数  结束----------------------------------------*/

    /*----------------------------------------业务函数  开始----------------------------------------*/
    /*------------------------------针对各个页面的入口  开始------------------------------*/
    var initGuesthouseList = function () {

        // initRoleoption();
        initmap();
        initGuesthouseListControlEvent();
        //initGuesthouseRecordList();
        //  initRoleoption();

    }
    var initDetail = function () {
        // initRoleoption();
        initGhDetail();
       // initUserAddControlEvent();
        // initAddRecordView();

    }
    var initUserModify = function () {
        initUserModifyControlEvent();
        initUserRecordView();
    }
    initPrintTable = function () {
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

    var initGuesthouseListControlEvent = function () {
        $("#help_button").click(function () {help();});

        //   $('#query_btn').click(function() {onqueryRecord();});
        // $('#add_button_condition').click(function() {onAddRecord();});
        // $('#export_button').click(function() {onExport_Button();});
        // $('#print_table').click(function() {onPrint_Table();});
        // $('#user_modify_div #submit_modify_btn').click(function() {OnModifySubmit();});
        $('#map_search_div #search_map_btn').click(function () {submitSearchRecord();});
        // $('#user_add_div #add_button').click(function() {submitAddRecord();});
        //$('#export_button')

    }

    /*------------------------------函数----------------------------------------*/
    var initPage_header=function(){
        console.log("initPage_header");
        var data={};
        data.action ="get_favorites";
        $.post("../../favorite_file_servlet_action",data,function(json){

        })
    }
function getDaysBetween(dateString1,dateString2) {

        var  startDate = Date.parse(dateString1);
        var  endDate = Date.parse(dateString2);
        if (startDate>endDate){
            return 0;
        }
        if (startDate==endDate){
            return 1;
        }
    var days=(endDate - startDate)/(1*24*60*60*1000);

        $(".help-block").val(days);
}
    var submitSearchRecord = function () {
        onadvancedquery =true;

        //参数获取完毕  关闭弹窗
        $("#map_search_div").modal("hide");

        getdata=getsearchrequire();
        //界面显示搜寻条件
        showsearchview(getdata);
        //避免在setmark时造成界面moved 而重新查询
        //提交查询后20秒内不再因为经维度变化重新查询民宿
        relogbound.reload = false;

        if (!relogbound.reload) {
            console.log("根据用户输入条件查询中........................")
            logMapBounds(getdata);
        }
        // get_gh_record(d)
        $("#map_search_result_2").show();

    }
    function getsearchrequire() {
        var d = {};

        //对tags标签的关键字搜索进行组装,以;间隔
        var tags = document.querySelectorAll('.select2-selection__rendered>li');
        var tagString = '';
        //最后一个li为后方空白填充,不读取
        for (var i = 0; i < tags.length-2; i++) {
            var one =tags[i].textContent.trim();
            tagString += one.substr(1,one.length)+ ';';
            console.log(tags[i].textContent.valueOf());
        }
        d.tags =tagString;

        //价格区间 截取掉第一个$字符
        var pricemin=$(".irs-from").text();
        d.price_min = pricemin.substr(1,pricemin.length);
        var pricemax =$(".irs-to").text();
        d.price_max = pricemax.substr(1, pricemax.length);

        //日期区间
        d.start_date = $(".from_date").val();
        d.end_date = $(".to_date").val();

        //人数
        d.cust_num=$("#map_search_div  #cust_num option:selected").val();

        //房间数
        d.room_num=$("#map_search_div  #room_num option:selected").val();

        d.action ="advanced_query";
        return d;

    }

    function showsearchview(d){
        var searchview ="根据您的筛选条件　： <h3>";
        if( d.tagString){
            searchview=searchview+"<b> 关键字 : </b>"+ d.tagString;
        }

        if( d.price_min&&d.price_max){
            searchview=searchview+" <b> 价格区间: </b> $"+ d.price_min +" - $" +d.price_max;
        }
        if( d.start_date&&d.end_date){
            searchview=searchview+" <b> 入住-退房时间: </b> "+ d.start_date +" -  " +d.end_date;
        }3
        if( d.cust_num){
            searchview=searchview+" <b> 入住人数 : </b>"+ d.cust_num;
        }
        if( d.room_num){
            searchview=searchview+" <b> 房间数 : </b>"+ d.room_num;
        }

        $("#map_search_result").html(searchview+"</h3>");
        $("#map_search_result").show();
    }

    var initGuesthouseRecordList = function (data) {
        get_gh_record(data);
        // $("#js-grid-juicy-projects").load(location.href + " #js-grid-juicy-projects");
        $("#aa").load(location.href + " #aa");
        $(".search_map").load(location.href + " .search_map");

        // $("#js-filters-juicy-projects").load(location.href + " #js-filters-juicy-projects");

       // $("#ghlist").load(location.href + " #ghlist");
       // $(".bb").load(location.href + " .bb");
    }
    var get_gh_record = function (data) {
        var url = "../../" + module + "_" + sub + "_servlet_action";
        var send = {};
        if(data!=""||data!=undefined){
            send=data;
            if(data.action==""||data.action==undefined||data.action=="get_owner_record"){
                send.action = "get_guesthouse_record";
            }
        }
        console.log(send);
        $.post(url, send, function (json) {
            if (json.result_code == 0) {
                //alert("1");
               // alert("get_guesthouse_record成功" + json.toString());
                console.log(json.aaData);
                var list=json.aaData;
                var html="";
                html=html+"<div  id=\"js-grid-juicy-projects\" >";
                if(list!=undefined && list.length>0){
                    for(var i=0;i<list.length;i++) {
                        console.log(list[i].gh_name);
                        html=renew_list(html,list[i])
                        $(".about-header").css("background-image","url(../../assets/global/img/gh_pic/"+list[i].gh_img+")");
                    }
                    console.log(html);
                    //document.getElementById("insert").html("");
                    $('#js-grid-juicy-projects').modal("hide");

                    //var ghlist = document.getElementById("ghlist");
                    //$("#insert").html(html);
                   //$("#aa").html(html);
                    //ghlist.innerHTML = html;
                    gh_list=list;
                    createPoint(gh_list);
                }


            }
        });
    }
    var renew_list =function (html,data) {

        html=html+" <div  class=\"cbp-item "+data.tags+"\" style='z-index: 9999' onclick=\"javascript:Page.onClickItem('"+data.gh_x+"','"+data.gh_y+"')\" >";
        html=html+"                            <div class=\"cbp-caption\">";
        html=html+"                                <div class=\"cbp-caption-defaultWrap\">";
        html=html+"                                    <img src=\"../../assets/global/img/gh_pic/"+data.gh_img+"\" style=\"width: 290px;height: 290px\"alt=\"\"> </div>";
        html=html+"                                <div class=\"cbp-caption-activeWrap\">";
        html=html+"                                    <div class=\"cbp-l-caption-alignCenter\">";
        html=html+"                                        <div class=\"cbp-l-caption-body\">";
        html=html+"                                            <a href=\"javascript:Page.onDetailRecord('"+data.gh_id+"','"+data.owner_id+"')\" class=\" btn red uppercase btn red uppercase\" rel=\"nofollow\">more info</a>";
        html=html+"                                            <a href=\"../../assets/global/img/gh_pic/"+data.gh_img+"\"  class=\"cbp-lightbox cbp-l-caption-buttonRight btn red uppercase btn red uppercase\" data-title=\"\">view larger</a>";
        html=html+"                                        </div>";
        html=html+"                                    </div>";
        html=html+"                                </div>";
        html=html+"                            </div>";
        html=html+"                            <div class=\"cbp-l-grid-projects-title uppercase text-center uppercase text-center\">"+data.gh_name+"</div>";
        html=html+"                            <div class=\"cbp-l-grid-projects-desc uppercase text-center uppercase text-center\">"+data.gh_address+"</div>";
        html=html+"</div>";
    return html;
    }
    var get_owner_record = function (data) {
        var url = "../../user_center_servlet_action";
        data.mapdetail = true;
        data.action = "get_owner_record";
        console.log(data);
        $.post(url, data, function (json) {
            if (json.result_code == 0) {
               // alert("get_owner_record成功");


            }
        });
    }

    function initGhDetail(gh_id,owner_id) {
        console.log("initGhDetail");
       // var gh_id=getUrlParam("gh_id");
        //var owner_id=getUrlParam("owner_id");
        var data = {};
        data.user_id = owner_id;
        data.gh_id = gh_id;
        console.log("data" + data);
        get_owner_record(data);
        get_gh_record(data);



    }

    /*--------------------以下为地图模块---------------------------------*/
    var map,scale ;
    var maxLng = "";	//最大经度
    var minLng = "";	//最小经度
    var maxLat = "";	//最大纬度
    var minLat = "";	//最小纬度
    var gh_list = "";
    var relogbound={};
    relogbound.reload =true;
    // 创建地图实例
    var initmap =function () {
        map = new AMap.Map("container");

        <!--初始化地图-->
        // var initmap = function () {
            logMapBounds();
            addscalecontrol();
            //  setUserCenter();
            tishi();

        // }
        map.on('moveend', logMapBounds);
        map.on('zoomend', logMapBounds);
        //添加场景控件
    }
        var addscalecontrol = function () {

            AMap.service('AMap.Scale', function () {
                console.log("成功进入Scale");
                scale = new AMap.Scale({
                    visible: false
                });
            });
            map.addControl(scale);
        }

        //初始化地图 ,并获取用户位置设置为中心
        var setUserCenter = function () {
            AMap.service('AMap.Geolocation', function () {
                console.log("获取用户位置设置为中心");
                var geolocation = new AMap.Geolocation({
                    enableHighAccuracy: true,//是否使用高精度定位，默认:true
                    timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                    maximumAge: 0,           //定位结果缓存0毫秒，默认：0
                    convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
                    showButton: true,        //显示定位按钮，默认：true
                    buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
                    buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                    showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
                    showCircle: false,        //定位成功后用圆圈表示定位精度范围，默认：true
                    panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
                    zoomToAccuracy: true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                });
                map.addControl(geolocation);
                geolocation.getCurrentPosition();
            });
        }

        // map.event.addListener(auto, 'select', this.select);// 注册监听，当选中某条记录时会触发
        //绑定地图移动与缩放事件
        //auto.on("select", select);//注册监听，当选中某条记录时会触发


        function logMapBounds(search) {
            var data = {}; // data为对象类型
            if (!relogbound.reload&& !onadvancedquery) {
                //触动其他事件 移动地图暂时不做查询

                console.log("relogbound.reload =" + relogbound.reload)
                return;
            } else if(search !== "" && search !== undefined && search.type !=="moveend" &&search.type!=="zoomend") {
                //若search不为空 则代表上一个调用函数为submitsearch

                data = search;
                console.log("search不为空");
                console.log(search.action+" "+search.type);

            }else if(onadvancedquery){
                //代表此时状态为 用户进行了进阶查询 拖动地图事件不造成全局查询 仍在原本用户搜索范围内查询在视野内的合适民宿
                data = getdata;
            }

                var bounds = map.getBounds();
                var bssw = bounds.getSouthWest();   //可视区域左下角
                var bsne = bounds.getNorthEast();   //可视区域右上角
                mapTier = map.getZoom();
                console.log("地图层级" + mapTier);
                maxLng = bsne.lng;
                minLng = bssw.lng;
                maxLat = bsne.lat;
                minLat = bssw.lat;
                console.log("最大经度:" + maxLng + "最小经度" + minLng);

                data.minLng = minLng;
                data.maxLng = maxLng;
                data.minLat = minLat;
                data.maxLat = maxLat;
                initGuesthouseRecordList(data);
        }

        //提示关键字查询
        var tishi = function () {
            //输入提示
            var autoOptions = {
                input: "shuru"
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
            }
        }


        //点击单个数据，找到地理位置
        var findPosition = function (x, y) {
            console.log("调用findPosition");
            //取消因为打开窗口造成页面自适应时窗口边界值的变化而调用logbound
            relogbound.reload = false;
            //判断是否为用户进阶查询,是则不进行计时器,避免恢复全局查询
            if(!onadvancedquery) {
                // 重新启动logbound监视的计时器
                t = setInterval(function () {
                    relogbound.reload = true;
                    console.log("findPosition恢复bound监听...");
                    clearInterval(t);

                }, 5000);  // 5s调用一次
            }
            //循环计时器

            //设置地图中心点
            map.setZoomAndCenter(15, [x, y]);
           // map.setCenter([x, y]);
            logMapBounds();

        }

        //搜索重定位
        function flushPosition(x) {
            console.log("调用flushPositon函数");
            var tr = x.childNodes;
            var posX;
            var posY;
            var posName;
            var posAdd;
            var posImg;
            var posID;

            for (var i = 0; i < tr.length; i++) {
                // console.log(tr[i]);
                if (tr[i].innerHTML != undefined) {
                    switch (i) {
                        case 1:
                            posID = tr[i].innerHTML;
                            console.log("posID:" + posID);
                            break;
                        case 3:
                            posName = tr[i].innerHTML;
                            console.log("posName:" + posName);
                            break;
                        case 5:
                            posAdd = tr[i].innerHTML;
                            console.log("posAdd:" + posAdd);
                            break;
                        case 9:
                            posX = tr[i].innerHTML;
                            console.log("posX:" + posX);
                            break;
                        case 11:
                            posY = tr[i].innerHTML;
                            console.log("posY:" + posY);
                            break;
                        case 13:
                            posImg = tr[i].innerHTML;
                            console.log("posImg:" + posImg);
                            break;
                        default:
                            console.log(i + ":" + tr[i].innerHTML);
                            break;
                    }
                }

            }
            setMyMarker(posName, posAdd, posImg, posX, posY, posID);

        }

        //根据位置、名称创建点坐标
        function setMyMarker(posName, posAdd, posImg, posX, posY, posID) {
            var findX = posX;
            var findY = posY;
            var findName = posName;
            var findAdd = posAdd;
            var findImg = posImg;
            var point = new BMapGL.Point(findX, findY);
            var marker = new BMapGL.Marker(point);  // 创建标注
            // alert("posID:"+posID);
            map.clearOverlays();                //清除其他覆盖物
            map.addOverlay(marker);              // 将标注添加到地图中
            // 创建信息窗口
            var opts = {
                width: 200,     // 信息窗口宽度
                height: 100,     // 信息窗口高度
                title: findName, // 信息窗口标题
            }

            map.setCenter(point);//更改地图中心点
            var infoWindow = new BMapGL.InfoWindow(html, opts);  // 创建信息窗口对象
            map.openInfoWindow(infoWindow, point); //开启信息窗口

            function imgLoad() {
                infoWindow.redraw();
            }
        }

        //设置坐标
        function createPoint(mapInfo) {
            console.log("createPoint: mapInfo=" + mapInfo);
            map.clearMap();                //清除其他覆盖物
            for (var i = 0; i < mapInfo.length; i++) {
                console.log("createPoint: mapInfo[" + i + "]=" + mapInfo[i]);
                console.log("createPoint : gh_x" + mapInfo[i].gh_x + "gh_y" + mapInfo[i].gh_x);
                //创建点标记实力
                if (numberInBetween(minLng, maxLng, mapInfo[i].gh_x) && numberInBetween(minLat, maxLat, mapInfo[i].gh_y)) {
                    var markers = [];
                    markers[i] = new AMap.Marker({
                        map: map,
                        position: [mapInfo[i].gh_x, mapInfo[i].gh_y],
                        offset: new AMap.Pixel(-13, -30)
                    });  // 创建标注
                    map.add(markers[i]);              // 将标注添加到地图中

                    //信息窗体信息
                    var content = [];
                    content.push("<div class=\"info-title\">" + mapInfo[i].gh_name + "</div><div class=\"info-content\">")
                    content.push("<img  src='../../assets/global/img/gh_pic/" + mapInfo[i].gh_img + "'  width='50px'>地址:" + mapInfo[i].gh_address);
                    content.push("电话：" + mapInfo[i].gh_phone);
                    content.push("<a  href =\"javascript:Page.onDetailRecord(" + mapInfo[i].gh_id + "," + mapInfo[i].owner_id + ")\">详细信息</a></div>");
                    // 创建信息窗口对象
                    var infoWindow = new AMap.AdvancedInfoWindow({
                        content: content.join(""),  //使用默认信息窗体框样式，显示信息内容,
                        asOrigin: false,
                        placeSearch: false,
                        offset: new AMap.Pixel(16, -45)
                    });
                    //监听事件:开启信息窗口
                    markers[i].on('click', markerClick);

                    function markerClick(e) {
                        //infoWindow.setContent(e.target.content);
                        infoWindow.open(map, e.target.getPosition());

                        //取消因为打开窗口造成页面自适应时窗口边界值的变化而调用logbound
                        relogbound.reload = false;
                        // 重新启动logbound监视的计时器
                        time11 = time11 - 1;
                        t = setInterval(changeItem, 1000);  // 1s调用一次

                        //循环计时器
                        function changeItem() {

                            if (time11 > 0) {
                                console.log("(" + time11 + "s)" + "关闭窗口,重新获取页面信息");
                                time11 = time11 - 1;
                            } else {
                                relogbound.reload = true;
                                console.log("正在重新获取页面信息..........");
                                clearInterval(t);
                                infoWindow.close();
                                time11 = time00;
                            }
                        }

                    }

                    function imgLoad() {
                        infoWindow.redraw();
                    }
                }
            }

        }

        var numberInBetween = function (startingNumber, endingNumber, givenNumber) {

            if (givenNumber > startingNumber && givenNumber < endingNumber) {
                console.log(givenNumber + "between" + startingNumber + " and " + endingNumber);
                return 1;
            }
            return 0;


        }
        /*-------------------------------------page函数-------------------------------------------*/

    var onqueryRecord =function () {
        $("#map_search_div").modal("show");
        getDaysBetween( $(".from-date").datepicker('getDate'),$(".to-date").datepicker('getDate'));
    }
    var onreset =function () {
        $("#map_search_div").reload;
        $("#map_search_result").hide();
        $("#map_search_result_2").hide();
        //进行初始getGHrecord全部查询
        relogbound.reload = true;
        onadvancedquery =false;
        logMapBounds();
    }

    var  onexamine = function(status,gh_id,examiner_id){
        var url = "../../guesthouse_center_servlet_action";
        var data ="";
        data.status=status;
        data.gh_id =gh_id;
        data.gh_examine_admin =examiner_id;
        data.action = "modify_gh_record";
        console.log(data);
        $.post(url, data, function (json) {
            if (json.result_code == 0) {
                // alert("get_owner_record成功");
                window.location.href="../guesthouse/guesthouse_list.jsp"

            }
        });
    }

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
        onDetailRecord:function (gh_id,owner_id) {
            console.log("onDetailRecord"+gh_id+"owner_id"+owner_id);
            initGhDetail(gh_id,owner_id);
            t = setInterval(function () {
                window.location.href="map_detail.jsp?gh_id="+gh_id+"&owner_id="+owner_id;
                clearInterval(t);

            }, 2000);  // 5s调用一次


        },
        onClickItem:function (x,y) {
            console.log("onClickItem:"+x+","+y);
            findPosition(x,y);

        },
        toggleScale: function (checkbox) {
            console.log("选中:toggleScale"+checkbox);
        if (checkbox.checked) {
            scale.show();
        } else {
            scale.hide();
        }
        },
        onqueryRecord:function () {
            onqueryRecord();
        },
        onreset:function () {
            onreset();

        },
        onexamine: function(status,gh_id,examiner_id){
            onexamine(gh_id,examiner_id);
        }
}
}();//Page
/*================================================================================*/
