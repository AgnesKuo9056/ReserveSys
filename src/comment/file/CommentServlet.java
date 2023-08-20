package comment.file;

import comment.entity.Comment;
import comment.service.CommentService;
import comment.service.Impl.CommentServiceImpl;
import common.util.AuthorityUtil;
import common.util.VCodeUtil;
import db.Data;
import guesthouse.dao.GHOrderDao;
import guesthouse.entitiy.GHOrder;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.User;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;


public class CommentServlet extends HttpServlet {
    private CommentService commentService = new CommentServiceImpl();
    String module = "maintain/comment";
    String sub = "file";
    private boolean vCodetimeout =true;  // 后台产生的验证码
    private String vCode;  // 后台产生的验证码
    private String vCodeReceive;  // 接收到前端输入的验证码
    private String method;  // 要接收的方法
//    private PrintWriter out;  // 输出流


    public CommentServlet() {
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


            if (action.equals("get_cm_record")) {
                actionOk = true;

                try {
                    this.getCommentRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("owner_get_cm_record")) {
                actionOk = true;

                try {
                    this.owner_listComments(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("query_comment")) {
                actionOk = true;

                try {
                    this.queryCommentRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("modify_comment")) {
                actionOk = true;

                try {
                    this.modifCommentRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
            if (action.equals("verifyvcode")) {
                actionOk = true;

                try {
                    this.Vcode(request, response, json);
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

    private void getCommentRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, ParseException {
        
    }

    private void owner_listComments(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, ParseException {
        HttpSession session = request.getSession();
        Data data = this.getPageParameters(request, response, json);
        User user = (User) session.getAttribute("User");
        data.getParam().put("user_id",user.getUser_id());
        List<Comment> listComment = commentService.owner_listComments(data, json);
        session.setAttribute("comments",listComment);
    }

    private void queryCommentRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, ParseException {
        GHOrderDao dao = new GHOrderDao();
        Data data = this.getPageParameters(request, response, json);
        List<Comment> listComment = commentService.listComment(data, json);
        HttpSession session = request.getSession();
        session.setAttribute("comments",listComment);
    }
    private void modifCommentRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GHOrderDao dao = new GHOrderDao();
        Data data = this.getPageParameters(request, response, json);
        dao.modifyGHOrderRecord(data, json);
    }

    private void addCommentRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        GHOrderDao dao = new GHOrderDao();
        Data data = this.getPageParameters(request, response, json);
        dao.addGHOrderRecord(data, json);
    }

    private void Vcode(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        // 获取来自前端的参数
        GHOrderDao dao = new GHOrderDao();
        Data data = this.getPageParameters(request, response, json);
        vCodeReceive = data.getParam().has("vcode")?data.getParam().getString("vcode"):null;
        switch (data.getParam().getString("method")) {
            case "getVCode":
                mGetVCode();
                json.put("vCode",vCode);
                break;
            case "verify":
                json.put("verify_result", mVerify());
                break;
            case "reGetVCode":
                disablevcode();
                json.put("vCode",vCode);
                break;
            default:
                break;
        }

    }
    /***********************************************************************************************************************************/
    /*
     * 产生验证码，并发送邮件
     */
    private void mGetVCode() {

        vCode =  VCodeUtil.verifyCode(6);
        vCodetimeout=false;
        System.out.println("验证码为：" + vCode);

    }


    /*
     * 验证码验证
     */
    private boolean mVerify() {
        // TODO Auto-generated method stub
        if(!vCodetimeout&&vCode.equals(vCodeReceive)) {
            return  true;
        }
       return  false;
    }
    /*
     * timeout 原本验证码无效
     * */
    private  void disablevcode(){
        vCodetimeout=true;
        mGetVCode();
    }
}



