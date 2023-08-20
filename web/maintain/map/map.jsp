<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">
<meta charset="utf-8" />
<div id="detail_container" style="display:block; width:1000px ;height: 350px;">
    <div class='input-card'  style="display:inline-block;">
        <div class="input-item" >
            <input type="checkbox" onclick="javascript:Page.toggleScale(this)"/>比例尺
        </div>

    </div>
    <div class="myPageTop" style="display:inline-block;">
        <table>
            <tr>
                <td>
                    <label>请输入关键字：</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input id="shuru" />
                </td>
            </tr>
        </table>
    </div>
</div>