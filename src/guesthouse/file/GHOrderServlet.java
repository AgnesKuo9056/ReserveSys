package guesthouse.file;

import common.util.AuthorityUtil;
import common.util.MyExcelUtil;
import db.Data;
import guesthouse.dao.GHOrderDao;
import guesthouse.dao.GuesthouseDao;
import guesthouse.entitiy.GHOrder;
import guesthouse.entitiy.Guesthouse;
import guesthouse.service.GHOrderService;
import guesthouse.service.Impl.GHOrderServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


public class GHOrderServlet extends HttpServlet {
    private GHOrderService ghOrderService  = new GHOrderServiceImpl();
    String module = "maintain/guesthouse";
    String sub = "file";

    public GHOrderServlet() {
    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][" + this.module + "/" + this.sub + "/SueServlet]" + msg);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            this.processAction(request, response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, JSONException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        boolean actionOk = false;
        boolean resultCode = false;
        String resultMsg = "ok";
        JSONObject json = new JSONObject();
        this.showDebug("processAction收到的action是：" + action);
        if (action == null) {
            resultMsg = "传递过来的action是NULL";
        } else {


            if (action.equals("get_gh_order_record")) {
                actionOk = true;

                try {
                    this.getGHOrderRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("get_order_count")) {
                actionOk = true;

                try {
                    this.getGHOrder_Count(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("get_order_statistic")) {
                actionOk = true;

                try {
                    this.getGHOrderstatistic(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();

                }
            }
            if (action.equals("modify_GHOrderRecord")) {
                actionOk = true;

                try {
                    this.modifyGHOrderRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("cancel_order_record")) {
                actionOk = true;

                try {
                    this.cancelGHOrderRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }

            this.responseBack(request, response, json);
        }

    }

    private Data getPageParameters(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException {
        Data data = new Data();
        HttpSession session = request.getSession();
        this.showDebug("[getPageParameters]-------------------------获取表单信息开始---------------------");
        JSONObject param = data.getParam();
        Enumeration requestNames = request.getParameterNames();

        Enumeration e = requestNames;

        while(e.hasMoreElements()) {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            this.showDebug("[getPageParameters]" + thisName + "=" + thisValue);
            this.showDebug(data.getParam().toString());
            param.put(thisName, thisValue);
        }

        String[] ids = request.getParameterValues("ids[]");
        if (ids != null) {
            param.put("ids[]", ids);
        }

        this.showDebug("[getPageParameters]----------------------------------------获取所有表单信息 完毕----------------------------------------");
        return data;
    }

    private void responseBack(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException {
        boolean isAjax=true;
        //判断是否为异步传输,若为同步,则需要重新定位页面
        if(request.getHeader("x-requested-with")==null||request.getHeader("x-requested-with").equals("com.tencent.mm")){isAjax=false;}
        if(isAjax){
            response.setContentType("application/json; charset=UTF-8");

            try {
                response.getWriter().print(json);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }else {
            String action =json.getString("action");
            String errorNo="0";
            String errorMsg="ok";
            String url = module+"/"+sub+"/result.jsp?action="+action+"&result_code="+errorNo+"&result_msg="+errorMsg;
            if(json.has("redirect_url")) url=json.getString("redirect_url");
            try{
                // request.getRequestDispatcher(url).forward(request,response);
                // request.getRequestDispatcher(url).forward(request,response);
                response.sendRedirect(url);
            }catch (IOException  E){
                E.printStackTrace();
            }
        }

    }

    private void getGHOrderstatistic(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        HttpSession session = request.getSession();
        Data data = this.getPageParameters(request, response, json);
        User user = (User) session.getAttribute("User");
        AuthorityUtil.Author(user,json);
        //若is_store为true or is_admin为true,则订单管理列表指显示该商家下所有民宿
        data.getParam().put("user_id",(json.has("is_store")?user.getUser_id():null));
        ghOrderService.getGHOrderstatistic(data, json);
    }
    private void getGHOrder_Count(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        HttpSession session = request.getSession();
        Data data = this.getPageParameters(request, response, json);
        User user = (User) session.getAttribute("User");
        AuthorityUtil.Author(user,json);
        //若is_store为true or is_admin为true,则订单管理列表指显示该商家下所有民宿
        data.getParam().put("user_id",(json.has("is_store")?user.getUser_id():null));
        ghOrderService.getGHOrder_Count(data, json);
    }

    private void getGHOrderRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, ParseException {

        HttpSession session = request.getSession();
        Data data = this.getPageParameters(request, response, json);
        User user = (User) session.getAttribute("User");
        AuthorityUtil.Author(user,json);
        //若is_store为true,则订单管理列表指显示该商家下所有民宿
        data.getParam().put("owner_id",(json.has("is_store")?user.getUser_id():null));
        //若is_store为true or is_admin为true,则订单管理列表指显示该商家下所有民宿
        data.getParam().put("user_id",(!(json.has("is_admin")||json.has("is_store"))?user.getUser_id():null));
        List<GHOrder> ghOrders = ghOrderService.getGHOrderRecord(data, json);
        session.setAttribute("Orders",ghOrders);
        showDebug("/[getGHOrderRecord]/Orders: "+ghOrders);
    }

    private void modifyGHOrderRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        ghOrderService.modifyGHOrderRecord(data, json);
    }


    private void cancelGHOrderRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        ghOrderService.cancelGHOrderRecord(data, json);
    }

    private void addGHOrderRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        ghOrderService.addGHOrderRecord(data, json);
    }


}



