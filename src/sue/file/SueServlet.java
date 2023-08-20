package sue.file;

import common.util.AuthorityUtil;
import common.util.VCodeUtil;
import db.Data;
import guesthouse.dao.GHOrderDao;
import guesthouse.entitiy.GHOrder;
import org.json.JSONException;
import org.json.JSONObject;
import sue.entity.Sue;
import sue.service.Impl.SueServiceImpl;
import sue.service.SueService;
import user.entitiy.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


public class SueServlet extends HttpServlet {
    private SueService sueService = new SueServiceImpl();
    String module = "maintain/sue";
    String sub = "file";
    private boolean vCodetimeout =true;  // 后台产生的验证码
    private String vCode;  // 后台产生的验证码
    private String vCodeReceive;  // 接收到前端输入的验证码
    private String method;  // 要接收的方法
//    private PrintWriter out;  // 输出流


    public SueServlet() {
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


            if (action.equals("get_sue_record")) {
                actionOk = true;

                try {
                    this.getSueRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("modify_sue_record")) {
                actionOk = true;

                try {
                    this.modifySueRecord(request, response, json);
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
        User user = (User)session.getAttribute("User");
        this.showDebug("[getPageParameters]-------------------------获取表单信息开始---------------------");
        JSONObject param = data.getParam();
        param.put("user_id",user.getUser_id());
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

    private void getSueRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, ParseException {

        HttpSession session = request.getSession();
        Data data = this.getPageParameters(request, response, json);
        User user = (User) session.getAttribute("User");
        AuthorityUtil.Author(user,json);
        //若is_store为true or is_admin为true,则订单管理列表指显示该商家下所有民宿
        data.getParam().put("user_id",(!(json.has("is_admin")||json.has("is_store"))?user.getUser_id():null));
        List<Sue> Sues = sueService.listSue(data, json);
        session.setAttribute("Sues",Sues);
        showDebug("/[getSueRecord]/Sues: "+Sues);
    }

    private void modifySueRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, InvocationTargetException, IllegalAccessException {

        Data data = this.getPageParameters(request, response, json);
        sueService.modifySue(data, json);
    }




    private void addSueRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GHOrderDao dao = new GHOrderDao();
        Data data = this.getPageParameters(request, response, json);
        dao.addGHOrderRecord(data, json);
    }


    /***********************************************************************************************************************************/

}



