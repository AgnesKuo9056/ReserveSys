package map.file;

import common.util.MyExcelUtil;
import db.Data;
import guesthouse.entitiy.Guesthouse;
import map.dao.MapDao;
import map.service.Impl.MapServiceImpl;
import map.service.MapService;
import org.json.JSONException;
import org.json.JSONObject;

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

//import  net.sf.json.JSONArray;
//import  net.sf.json.JSONException;
//import  net.sf.json.JSONObject;

//@WebServlet("/user_center_servlet")
public class MapServletAction extends HttpServlet {
    private MapService mapService = new MapServiceImpl();
    String module = "maintain/map";
    String sub = "file";

    public MapServletAction() {
    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][" + this.module + "/" + this.sub + "/MapServletAction]" + msg);
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


            if (action.equals("get_guesthouse_record")) {
                actionOk = true;

                try {
                    this.getGHRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }

            if (action.equals("advanced_query")) {
                actionOk = true;

                try {
                    this.QueryGHRecord(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
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

    private void getGHRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        List<Guesthouse> GuestHouses = mapService.getGHRecord(data, json);
        HttpSession session = request.getSession();
        session.setAttribute("GuestHouses",GuestHouses);
        showDebug("/[getGHRecord]/GuestHouses为: "+GuestHouses);
      //  showDebug("/[getGHRecord]/GuestHouses.get(0).getGh_id()为: "+GuestHouses.get(0).getGh_id());
    }


    private void    QueryGHRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException , ParseException {
        Data data = this.getPageParameters(request, response, json);
        List<Guesthouse> GuestHouses  = mapService.queryGHRecord(data, json);
        HttpSession session = request.getSession();
        session.setAttribute("GuestHouses",GuestHouses);
        showDebug("/[getGHRecord]/GuestHouses为: "+GuestHouses);
    }



}



