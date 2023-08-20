<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />

<div class="modal fade draggable-modal" id="map_search_div" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title"><-- 高级查询 --></h4>
            </div>
<%--            <div style="margin-left: -15px">
                <div class="iq-search-bar">
                    <form id="search-form" class="searchbox" action="/map">
                        <input id ="gh_name" type="text" name="gh_name" class="text search-input" placeholder="搜寻民宿...">
                        <input id="btn-search" type="image" value="Submit" src="../../assets/global/img/map-search.jpg" name="map-search-img"
                               style="width: 28px;height: 28px; position: absolute;float: right;margin-top: 3px"> <!--必须用jpg格式-->
                    </form>
                </div>

            </div>--%>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="control-label col-md-3">预计入住-退房时间</label>
                            <div class="col-md-4">
                                <div class="input-group input-large date-picker input-daterange" data-date="2023-4-16" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control from_date" name="from_date">
                                    <span class="input-group-addon"> to </span>
                                    <input type="text" class="form-control to_date" name="to_date"> </div>
                                <!-- /input-group -->
                                <span class="help-block"> </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="multi-append" class="control-label">关键词搜索标签:(可多选)</label>
                            <div class="input-group select2-bootstrap-append">
                                <select id="multi-append" class="form-control select2" multiple>
                                    <option></option>
                                    <option value="靠走道">靠走道</option>
                                    <option value="经济">经济</option>
                                    <option value="none">none</option>
                                </select>
                                <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" data-select2-open="multi-append">
                                                    <span class="glyphicon glyphicon-search"></span>
                                                </button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">价格范围 : </label>
                            <div class="col-md-9">
                                <input id="range_3" type="text" value="" />
<%--                                <span class="help-block"> Set type to double and specify range, also showing grid and adding prefix "$" </span>--%>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label>共有  </label>
                            <div class="input-inline">
                                <select class="form-control input-inline input-xsmall " id="cust_num">
                                    <option selected="selected" value="1">默认(1人)</option>
                                    <option value="2"> 2 </option>
                                    <option value="3"> 3 </option>
                                    <option value="4"> 4 </option>
                                    <option value="5"> 5 </option>
                                    <option value="30">more</option>
                                </select><label>人 ; 需要  </label>
                                <select class="form-control input-inline input-xsmall" id="room_num">
                                    <option selected="selected" value="1">默认(一间)</option>
                                    <option value="2"> 2 </option>
                                    <option value="3"> 3 </option>
                                    <option value="4"> 4 </option>
                                    <option value="5"> 5 </option>
                                    <option value="30">more</option>
                                </select>
                            </div>
                            <label>间房</label>

                        </div>


                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button id="search_map_btn" name="search_map_btn" class="btn btn-info" >搜索相关民宿</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--<script>--%>

<%--        // $( "#datepicker" ).datepicker({--%>
<%--        //     changeMonth: true,--%>
<%--        //     changeYear: true--%>
<%--        // });--%>
<%--        var date = new  Date();--%>
<%--        var defaultFromDate = new Date();--%>
<%--        defaultFromDate.setMonth(date.getDate());--%>
<%--        var defaultToDate = new Date();--%>
<%--        defaultToDate.setMonth(date.getDate() + 1);--%>

<%--        function getDaysBetween(dateString1,dateString2) {--%>

<%--            var  startDate = Date.parse(dateString1);--%>
<%--            var  endDate = Date.parse(dateString2);--%>
<%--            if (startDate>endDate){--%>
<%--                return 0;--%>
<%--            }--%>
<%--            if (startDate==endDate){--%>
<%--                return 1;--%>
<%--            }--%>
<%--            var days=(endDate - startDate)/(1*24*60*60*1000);--%>

<%--            $(".help-block").val(days);--%>
<%--        }--%>

<%--                // $('.date-picker').datepicker({--%>
<%--                //     rtl: App.isRTL(),--%>
<%--                //     orientation: "left",--%>
<%--                //     autoclose: true--%>
<%--                // }--%>
<%--                // );--%>
<%--                $(".from_date").datepicker({--%>
<%--                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],// 设置控件的星期名称显示--%>
<%--                    firstDay: 1, //设置排在第一列的是星期几，星期天为0，星期一为1，以此类推。--%>
<%--                    monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],--%>
<%--                    monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],--%>
<%--                    format: 'yyyy-mm-dd',--%>
<%--                    autoclose: true,--%>
<%--                    date:defaultFromDate,--%>
<%--                    minDate: 0,--%>
<%--                    maxDate: moment().add('month',5),--%>
<%--                    rtl: App.isRTL(),--%>
<%--                    orientation: "left"--%>

<%--                }).on('changeDate', function (selected) {--%>
<%--                    var startDate = new Date(selected.date.valueOf());--%>
<%--                    $('.to_date').datepicker('setMinDate', startDate.getDate()+1);--%>
<%--                    getDaysBetween(startDate,$(".to_date").val());--%>
<%--                }).on('clearDate', function (selected) {--%>
<%--                    $('.to_date').datepicker('setMinDate', moment().add('days',1));--%>
<%--                });--%>

<%--                $(".to_date").datepicker({--%>
<%--                    format: 'yyyy-mm-dd',--%>
<%--                    autoclose: true,--%>
<%--                    rtl: App.isRTL(),--%>
<%--                    orientation: "left",--%>
<%--                    date:defaultToDate,--%>
<%--                    minDate: moment().add('days',1),--%>
<%--                    maxDate:  '+6M +10D' ,--%>

<%--                }).on('changeDate', function (selected) {--%>
<%--                    var endDate = new Date(selected.date.valueOf());--%>
<%--                    $('.from_date').datepicker('setMaxDate', endDate.getDate()-1);--%>
<%--                    getDaysBetween($(".from_date").val(),endDate)--%>
<%--                }).on('clearDate', function (selected) {--%>
<%--                    $('.from_date').datepicker('setMaxDate', moment().add('month',5));--%>
<%--                });--%>
<%--                //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal--%>


<%--            /* Workaround to restrict daterange past date select: http://stackoverflow.com/questions/11933173/how-to-restrict-the-selectable-date-ranges-in-bootstrap-datepicker */--%>



<%--</script>--%>
