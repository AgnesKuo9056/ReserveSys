package cart.file;

import cart.dao.GuesthouseDao;
import cart.entitiy.Guesthouse;
import common.util.AuthorityUtil;
import common.util.MyExcelUtil;
import db.Data;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

//import  net.sf.json.JSONArray;
//import  net.sf.json.JSONException;
//import  net.sf.json.JSONObject;

//@WebServlet("/user_center_servlet")
public class GuesthouseServletAction extends HttpServlet {
    String module = "maintain/account";
    String sub = "file";

    public GuesthouseServletAction() {
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


            if (action.equals("get_gh_record")) {
                actionOk = true;

                try {
                    this.getGHRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }


            if (action.equals("add_gh_record")) {
                actionOk = true;

                try {
                    this.addGHRecord(request, response, json);
                } catch (JSONException var12) {
                    var12.printStackTrace();
                } catch (SQLException var13) {
                    var13.printStackTrace();
                }
            }

            if (action.equals("modify_gh_record")) {
                actionOk = true;

                try {
                    this.modifyGHRecord(request, response, json);
                } catch (Exception var11) {
                    var11.printStackTrace();
                }
            }

            if (action.equals("delete_gh_record")) {
                actionOk = true;

                try {
                    this.deleteGHRecord(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

            if (action.equals("query_gh_record")) {
                actionOk = true;

                try {
                    this.QueryGHRecord(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

//
//            if (action.equals("export_user_list")) {
//                actionOk = true;
//
//                try {
//                    this.Export_User_List(request, response, json);
//                } catch (Exception var10) {
//                    var10.printStackTrace();
//                }
//            }
//
//            if (action.equals("print_user_table")) {
//                actionOk = true;
//
//                try {
//                    this.Print_User_Table(request, response, json);
//                } catch (Exception var10) {
//                    var10.printStackTrace();
//                }
//            }

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
        GuesthouseDao dao = new GuesthouseDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        Data data = this.getPageParameters(request, response, json);
        AuthorityUtil.Author(user,json);
        //若is_store为true,则民宿管理列表指显示该商家下所有民宿
        data.getParam().put("owner_id",(json.has("is_store")?user.getUser_id():null));
        List<Guesthouse> GuestHouses = dao.getGHRecord(data, json);
        session.setAttribute("GuestHouses",GuestHouses);
        showDebug("/[getGHRecord]/GuestHouses为: "+GuestHouses);

    }

    private void modifyGHRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GuesthouseDao dao = new GuesthouseDao();
        Data data = this.getPageParameters(request, response, json);
        dao.modifyGHRecord(data, json);
    }

    private void    QueryGHRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GuesthouseDao dao = new GuesthouseDao();
        Data data = this.getPageParameters(request, response, json);
        dao.getGHRecord(data, json);
    }

    private void deleteGHRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GuesthouseDao dao = new GuesthouseDao();
        Data data = this.getPageParameters(request, response, json);
        dao.deleteGHRecord(data, json);
    }

    private void addGHRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GuesthouseDao dao = new GuesthouseDao();
        Data data = this.getPageParameters(request, response, json);
        dao.addGHRecord(data, json);
    }


//    private void  Export_User_List(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
//        MapDao dao = new MapDao();
//        Data data = this.getPageParameters(request, response, json);
//        dao.getUserRecord(data, json);
//        getExportUserto_excel(json, data);
//        getExportUserto_txt(json, data);
//    }
//
//    private void Print_User_Table(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
//        MapDao dao = new MapDao();
//        Data data = this.getPageParameters(request, response, json);
//        dao.getUserRecord(data, json);
//    }



    private void getExportUserto_txt(JSONObject json, Data data) throws JSONException {
        String jsonStr = json.toString();
        File jsonFile = new File("C:\\upload\\maintain\\User\\export_GuestHouses.txt");
        json.put("export_url","/upload/maintain/User/export_GuestHouses.txt");
        try {
            // 文件不存在就创建文件
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(jsonFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(jsonStr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void getExportUserto_excel(JSONObject json, Data data) throws JSONException, IOException {
        MyExcelUtil me = new MyExcelUtil("C:\\upload\\maintain\\User\\export_User.xls");
        json.put("export_url","/upload/maintain/User/export_GuestHouses.xls");

        json.put("file_path","C:\\upload\\maintain\\User\\export_GuestHouses.xls");
        me.exportData(data,json);
    }







}



