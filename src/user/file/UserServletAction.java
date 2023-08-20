package user.file;

import common.util.AuthorityUtil;
import common.util.FileUploadUtil;
import common.util.MyExcelUtil;
import db.Data;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import map.service.Impl.MapServiceImpl;
import map.service.MapService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.Img;
import user.entitiy.Store;
import user.entitiy.User;
import user.service.Impl.UseServiceImpl;
import user.service.UserService;

public class UserServletAction extends HttpServlet {
    private UserService userService = new UseServiceImpl();
    String module = "maintain/account";
    String sub = "file";


    public UserServletAction() {
    }

    public void showDebug(String msg) {
        PrintStream var10000 = System.out;
        SimpleDateFormat var10001 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        var10000.println("[" + var10001.format(new Date()) + "][" + this.module + "/" + this.sub + "/MapServletAction]" + msg);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            this.processAction(request, response);
        } catch (JSONException var4) {
            var4.printStackTrace();
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
            if (action.equals("login")) {
                actionOk = true;

                try {
                    this.Login(request, response, json);
                } catch (Exception var27) {
                    var27.printStackTrace();
                }
            }

            if (action.equals("get_user")) {
                actionOk = true;

                try {
                    this.getUser(request, response, json);
                } catch (Exception var26) {
                    var26.printStackTrace();
                }
            }

            if (action.equals("get_user_role_option")) {
                actionOk = true;

                try {
                    this.GetUserrole(request, response, json);
                } catch (Exception var25) {
                    var25.printStackTrace();
                }
            }

            if (action.equals("get_user_record")) {
                actionOk = true;

                try {
                    this.getUserRecord(request, response, json);
                } catch (Exception var24) {
                    var24.printStackTrace();
                }
            }

            if (action.equals("get_authority")) {
                actionOk = true;

                try {
                    this.Authority(request, response, json);
                } catch (Exception var23) {
                    var23.printStackTrace();
                }
            }

            if (action.equals("get_owner_record")) {
                actionOk = true;

                try {
                    this.getOwnerRecord(request, response, json);
                } catch (Exception var22) {
                    var22.printStackTrace();
                }
            }

            if (action.equals("add_user_record")) {
                actionOk = true;

                try {
                    this.addUserRecord(request, response, json);
                } catch (JSONException var20) {
                    var20.printStackTrace();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

            if (action.equals("get_user_password")) {
                actionOk = true;

                try {
                    this.getUserpsw(request, response, json);
                } catch (JSONException var18) {
                    var18.printStackTrace();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

            if (action.equals("modify_user_password")) {
                actionOk = true;

                try {
                    this.modifyuserpsw(request, response, json);
                } catch (JSONException var16) {
                    var16.printStackTrace();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

            if (action.equals("modify_user_record")) {
                actionOk = true;

                try {
                    this.modifyUserRecord(request, response, json);
                } catch (Exception var15) {
                    var15.printStackTrace();
                }
            }

            if (action.equals("log_out")) {
                actionOk = true;

                try {
                    this.logout(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }

            if (action.equals("delete_user_record")) {
                actionOk = true;

                try {
                    this.deleteUserRecord(request, response, json);
                } catch (Exception var13) {
                    var13.printStackTrace();
                }
            }

            if (action.equals("query_user_record")) {
                actionOk = true;

                try {
                    this.QueryUserRecord(request, response, json);
                } catch (Exception var12) {
                    var12.printStackTrace();
                }
            }

            if (action.equals("export_user_list")) {
                actionOk = true;

                try {
                    this.Export_User_List(request, response, json);
                } catch (Exception var11) {
                    var11.printStackTrace();
                }
            }

            if (action.equals("print_user_table")) {
                actionOk = true;

                try {
                    this.Print_User_Table(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
            if (action.equals("change_store_status")) {
                actionOk = true;

                try {
                    this.change_store_status(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
            if (action.equals("get_store_apply")) {
                actionOk = true;

                try {
                    this.get_store_apply(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }


            if (action.contains("upload")) {
                actionOk = true;
                if(action.equals("upload_avatar")){
                    try {
                        this.avatar_upload(request, response, json);

                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }else  if (action.equals("upload_store_apply") ){
                    try {
                        this.store_apply_upload(request, response, json);

                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }



            }
            if (action.equals("update_store")) {
                actionOk = true;

                try {
                    this.modifyStore(request, response, json);

                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

            if (action.equals("regist")) {
                actionOk = true;

                try {
                    this.regist(request, response, json);

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
        boolean isAjax = true;
        if (request.getHeader("x-requested-with") == null || request.getHeader("x-requested-with").equals("com.tencent.mm")) {
            isAjax = false;
        }

        if (isAjax) {
            response.setContentType("application/json; charset=UTF-8");

            try {
                response.getWriter().print(json);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }
        } else {
            String action = json.getString("action");
            String errorNo = "0";
            String errorMsg = "ok";
            String url = this.module + "/" + this.sub + "/result.jsp?action=" + action + "&result_code=" + errorNo + "&result_msg=" + errorMsg;
            if (json.has("redirect_url")) {
                url = json.getString("redirect_url");
            }

            try {
                response.sendRedirect(url);
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

    }

    private void avatar_upload(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        String file = getServletContext().getRealPath("/assets/global/img/avatar/");
        data.getParam().put("file", file);
        userService.upload_avatar(request,data,json);
        json.put("action","upload_file");
        json.put("redirect_url", "home/profile/account_setting.jsp");
    }

    private void store_apply_upload(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        String file = getServletContext().getRealPath( "/assets/global/img/store/");
        data.getParam().put("file", file);
        Store store=userService.upload_store_apply(request,data,json);
        if(json.getInt("result_code") == 0) {
            HttpSession session = request.getSession();
            session.setAttribute("Store", store);
        }
        json.put("action","upload_file");
        json.put("redirect_url", "home/profile/account_setting.jsp");
    }

    private void getUserRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        Data data = this.getPageParameters(request, response, json);
        userService.getUserRecord(data, json);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException {
        HttpSession session = request.getSession();
        session.removeAttribute("User");
        System.out.println("用户成功登出");
        json.put("redirect_url", "home/main/login.jsp");
        json.put("action","log_out");
    }
    private void regist(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        JSONObject reg = data.getParam();
        User user = new User(reg.getString("user_name"),reg.getString("anony_name"),reg.getString("password_strength"),reg.getString("phone_number"));
        userService.Register(data, json,user);
       //注册完后直接到index界面 ,自动登入
        HttpSession session = request.getSession();
        session.setAttribute("User",user);
        json.put("redirect_url", "maintain/map/map_list.jsp");

        json.put("action","regist");
    }


    private void modifyStore(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        userService.modifyStore(request,data, json);
    }

    private void getOwnerRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {

        Data data = this.getPageParameters(request, response, json);
        userService.getUserRecord(data, json);
        this.updataUserEntity(request, response, json);
    }

    private void modifyUserRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        userService.modifyUserRecord(data, json);
    }

    private void QueryUserRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        Data data = this.getPageParameters(request, response, json);
        userService.getUserRecord(data, json);
    }

    private void deleteUserRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        userService.deleteUserRecord(data, json);
    }

    private void addUserRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        userService.addUserRecord(data, json);
    }

    private void getUserpsw(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        data.getParam().put("user_id", user.getUser_id());
        userService.getUserpsw(data, json);
    }

    private void modifyuserpsw(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        Data data = this.getPageParameters(request, response, json);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        this.showDebug("user_id: " + user.getUser_id());
        data.getParam().put("user_id", user.getUser_id());
        userService.modifyuserpsw(data, json);
    }

    private void GetUserrole(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        Data data = this.getPageParameters(request, response, json);
        userService.getUser_role_type(data, json);
    }

    private void Export_User_List(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        Data data = this.getPageParameters(request, response, json);
        userService.getUserRecord(data, json);
        this.getExportUserto_excel(json, data);
        this.getExportUserto_txt(json, data);
    }

    private void Print_User_Table(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        Data data = this.getPageParameters(request, response, json);
        userService.getUserRecord(data, json);
    }

    private void get_store_apply(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException, ServletException {
        Data data = this.getPageParameters(request, response, json);
        userService.get_store_apply(data,json);
    }


    private void change_store_status(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        System.out.println("进入change_store_status");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        Store store = (Store) session.getAttribute("Store");
        System.out.println(store.getStatus());
        if(store.getStatus()!=3&&store.getStatus()!=0){
            //商家要切换为普通用户
            System.out.println("商家要切换为普通用户");
            store.setStatus(3);
            user.setUser_role(2);
            session.setAttribute("Store",store);
            session.setAttribute("User",user);
            System.out.println(user.toString());
            json.put("redirect_url", "maintain/map/map_list.jsp");
        }else  if (store.getStatus()==3){
            //商家要切换回商家状态
            System.out.println("商家要切换回商家状态");
            store.setStatus(1);
            user.setUser_role(1);
            session.setAttribute("Store",store);
            session.setAttribute("User",user);
            System.out.println(user.toString());
            json.put("redirect_url", "home/main/index.jsp?user_role="+user.getUser_role());
        }
        json.put("action","change_store_status");

        System.out.println( request.getRequestURI());
    }

    private void Login(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        Data data = this.getPageParameters(request, response, json);
        HttpSession session = request.getSession();
        JSONObject var10001 = data.getParam();
        this.showDebug("收到数据:" + var10001.getString("user_id"));
        var10001 = data.getParam();
        this.showDebug("收到数据:" + var10001.getString("password"));
        userService.login(data, json);
        if (json.getInt("result_code") == 0) {
            this.updataUserEntity(request, response, json);
            User user = (User)session.getAttribute("User");
            if(user.getUser_role()==1){
                userService.get_storebyid(request,data, json);
                json.put("redirect_url", "home/main/index.jsp?user_role="+user.getUser_role());
            }else if (user.getUser_role()==2){
                session.setAttribute("Store",null);
                System.out.print("进入map页面");
                json.put("redirect_url", "maintain/map/map_list.jsp");
            }else {
                session.setAttribute("Store",null);
                json.put("redirect_url", "home/main/index.jsp?user_role="+user.getUser_role());
            }

        } else {
            json.put("redirect_url", "home/main/login_error.jsp");
        }

    }
//<================================================================调用函数==========================================================================================>



    private void updataUserEntity(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException {
        String person = "User";
        User user = new User();
        JSONArray UserList = json.getJSONArray("UserList");
        this.showDebug("UserList" + UserList);

        for(int i = 0; i < UserList.length(); ++i) {
            user = (User)UserList.get(i);
            this.showDebug("UserList.get()" + user.getUser_id());
            if (request.getParameter("action").equals("get_owner_record") && request.getParameter("user_id").equals(String.valueOf(user.getUser_id()))) {
                this.showDebug("是owner噢");
                person = "Owner";
                break;
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute(person, user);
        if (!person.equals("Owner")) {
        }

        this.showDebug("/[updataUserEntity]/: " + person + "->" + user);
    }



    private void getExportUserto_txt(JSONObject json, Data data) throws JSONException {
        String jsonStr = json.toString();
        File jsonFile = new File("C:\\upload\\maintain\\User\\export_User.txt");
        json.put("export_url", "/upload/maintain/User/export_User.txt");

        try {
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(jsonFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(jsonStr);
            bw.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    public void getUser(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        Data data = new Data();
        JSONObject param = data.getParam();
        param.put("user_id", user.getUser_id());
        userService.getUserRecord(data, json);
        this.updataUserEntity(request, response, json);
        User userupdate = (User)session.getAttribute("User");
        this.UserEntityTojson(request, response, json, userupdate);

    }

    private void getExportUserto_excel(JSONObject json, Data data) throws JSONException, IOException {
        MyExcelUtil me = new MyExcelUtil("C:\\upload\\maintain\\User\\export_User.xls");
        json.put("export_url", "/upload/maintain/User/export_User.xls");
        json.put("file_path", "C:\\upload\\maintain\\User\\export_User.xls");
        me.exportData(data, json);
    }


    public void UserEntityTojson(HttpServletRequest request, HttpServletResponse response, JSONObject json, User user) throws JSONException, SQLException, IOException {
        List jsonList = new ArrayList();
        HashMap map = new HashMap();

        try {
            map.put("user_id", user.getUser_id());
            map.put("user_name", user.getUser_name());
            map.put("mail", user.getMail());
            map.put("phone_number", user.getPhone_number());
            map.put("user_role", user.getUser_role());
            map.put("role_name", user.getRole_name());
        } catch (Exception var8) {
            System.out.println("user=" + user.toString() + ".json转换成实体类出错");
            var8.printStackTrace();
        }

        jsonList.add(map);
        System.out.println("[UserEntityTojson]" + jsonList);
        json.put("result_code", 0);
        json.put("aaData", jsonList);
    }

    private void Authority(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        AuthorityUtil.Author(user, json);
    }
}
