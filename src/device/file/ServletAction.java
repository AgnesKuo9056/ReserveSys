//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package device.file;

import common.util.MyExcelUtil;
import db.Data;
import device.dao.DeviceDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

//@WebServlet("/device_file_servlet_action")
public class ServletAction extends HttpServlet {
    String module = "maintain/device";
    String sub = "file";

    public ServletAction() {
    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][" + this.module + "/" + this.sub + "/ServletAction]" + msg);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.processAction(request, response);
    }

    public void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            if (action.equals("get_device_record")) {
                actionOk = true;

                try {
                    this.getDeviceRecord(request, response, json);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }

            if (action.equals("add_device_record")) {
                actionOk = true;

                try {
                    this.addDeviceRecord(request, response, json);
                } catch (JSONException var12) {
                    var12.printStackTrace();
                } catch (Exception var13) {
                    var13.printStackTrace();
                }
            }

            if (action.equals("modify_device_record")) {
                actionOk = true;

                try {
                    this.modifyDeviceRecord(request, response, json);
                } catch (Exception var11) {
                    var11.printStackTrace();
                }
            }

            if (action.equals("delete_device_record")) {
                actionOk = true;

                try {
                    this.deleteDeviceRecord(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

            if (action.equals("query_device_record")) {
                actionOk = true;

                try {
                    this.QueryDeviceRecord(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

            if (action.equals("get_gps_status")) {
                actionOk = true;

                try {
                    this.Get_GPS_Status(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

            if (action.equals("export_device_list")) {
                actionOk = true;

                try {
                    this.Export_Device_List(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

            if (action.equals("print_table")) {
                actionOk = true;

                try {
                    this.Print_Table(request, response, json);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
            if (action.equals("get_gps_count_by_hour")) {
                actionOk = true;

                try {
                    this.GPSCountByHour(request, response, json);
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

    private void responseBack(HttpServletRequest request, HttpServletResponse response, JSONObject json) {
        response.setContentType("application/json; charset=UTF-8");

        try {
            response.getWriter().print(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    private void getDeviceRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.getDeviceRecord(data, json);
    }

    private void modifyDeviceRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.modifyDeviceRecord(data, json);
    }

    private void    QueryDeviceRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.queryDeviceRecord(data, json);
    }

    private void deleteDeviceRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.deleteDeviceRecord(data, json);
    }

    private void addDeviceRecord(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.addDeviceRecord(data, json);
    }

    private void Get_GPS_Status(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.getGpsStatus(data, json);
    }

    private void  Export_Device_List(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException, IOException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.getDeviceRecord(data, json);
        getEportdeviceto_file(json, data);
        getExportdeviceto_text(json, data);
        getExportdeviceto_excel(json, data);
        getExportdeviceto_pdf(json, data);
    }

    private void Print_Table(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException, SQLException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.getDeviceRecord(data, json);
    }

    private void GPSCountByHour(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException {
        DeviceDao dao = new DeviceDao();
        Data data = this.getPageParameters(request, response, json);
        dao.getGPSDeviceCountByHour(data, json);
    }

    private void getExportdeviceto_pdf(JSONObject json, Data data) throws JSONException {
        String jsonStr = json.toString();
        File jsonFile = new File("C:\\upload\\maintain\\device\\export_device.txt");
        json.put("export_url","/upload/maintain/device/export_device.txt");
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

    private void getExportdeviceto_excel(JSONObject json, Data data) throws JSONException, IOException {
        MyExcelUtil me = new MyExcelUtil("C:\\upload\\maintain\\device\\export_device.xls");
        json.put("export_url","/upload/maintain/device/export_device.xls");

        json.put("file_path","C:\\upload\\maintain\\device\\export_device.xls");
        me.exportData(data,json);
    }

    private void getExportdeviceto_text(JSONObject json, Data data) {

    }

    private void getEportdeviceto_file(JSONObject json, Data data) {

    }
}
