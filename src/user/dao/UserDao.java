package user.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import db.DbRemote;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.Data;
import db.Db;
import user.entitiy.Img;
import user.entitiy.Store;
import user.entitiy.User;

public class UserDao {

    public  UserDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][user/dao/UsserDao]" + msg);

    }
    public void setavatar(Data data, JSONObject json,User user) throws JSONException, SQLException {
        String sql ="UPDATE user_info SET avatar='"+user.getAvatar()+"' WHERE user_id="+user.getUser_id();
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);
    }

    public void addStore(Data data, JSONObject json, Store store) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        LocalDate currentDate = LocalDate.now();
        String sql ="insert into store (user_id,imgstring,apply_date,card_num)";
        sql = sql + " values('" + user_id + "'";
        sql = sql + " ,'" + store.getImgstring() + "'";
        sql = sql + " ,'" + currentDate + "'";
        sql = sql + " ,'" + store.getCard_num() + "')";
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);
    }

    public void login(Data data, JSONObject json) throws JSONException, SQLException {
        // 获取变量
        showDebug("进到login/dao李");
        String resultMsg = "ok";
        int resultCode = 0;
        List jsonList = new ArrayList();
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String password = data.getParam().has("password") ? data.getParam().getString("password") : null;
        String action = data.getParam().has("action") ? data.getParam().getString("action") : null;
        //数据库操作
        Db queryDb = new Db("reservesys");
        String sql = "SELECT * FROM user_login WHERE user_id ='" +user_id+"' and password='"+password+"'";
        showDebug("[login]" + sql);
        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            if(!rs.next()) {
                resultCode=10;      //如果发生错误就返回非0代码,代表登陆失败信息
                showDebug("[login] 登陆失败 没查到对应" + sql);
                resultMsg="登陆失败,请核对用户名及密码";
                queryDb.close();
                //返回数据开始

                json.put("action",action);
                json.put("result_msg", resultMsg);
                json.put("result_code", resultCode);
                return;
            }else {
                this.get_Login_User_Info(data, json);
            }
            rs.close();
        } catch (Exception var13) {
            var13.printStackTrace();
            this.showDebug("[queryRecord]查询数据库出现错误：" + sql);
            resultCode = 10;
            resultMsg = "查询数据库出现错误！" + var13.getMessage();
        }
        queryDb.close();
        //返回数据开始

        json.put("action",action);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);


    }


    public void addUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String password = data.getParam().has("password") ? data.getParam().getString("password") : null;
        String user_name = data.getParam().has("user_name") ? data.getParam().getString("user_name") : null;
        String user_role = data.getParam().has("user_role") ? data.getParam().getString("user_role") : null;
        String mail = data.getParam().has("mail") ? data.getParam().getString("mail") : null;
        String phone_number = data.getParam().has("phone_number") ? data.getParam().getString("phone_number") : null;
        if (user_id != null && user_name != null &&user_role != null) {
            String sql = "insert into user_info(user_id,user_name,user_role,mail,phone_number)";
            sql = sql + " values('" + user_id + "'";
            sql = sql + " ,'" + user_name + "'";
            sql = sql + " ,'" + user_role + "'";
            sql = sql + " ,'" + mail + "'";
            sql = sql + " ,'" + phone_number + "')";
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);
            String sql2 = "insert into user_login (user_id,password)";
            sql2 = sql2 + " values('" + user_id + "'";
            sql2 = sql2 + " ,'" + password + "')";
            data.getParam().put("sql", sql2);
            this.updateRecord(data, json);
        }

    }

    public void deleteUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        if (user_id != null) {
            String sql = "delete user_info,user_login from user_info,user_login  where user_info.user_id = user_login.user_id  AND user_info.user_id=" + data.getParam().getString("user_id");
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);
        }

    }
    public void get_store(Data data, JSONObject json) throws JSONException, SQLException {
        String status = data.getParam().has("status") ? data.getParam().getString("status") : null;
        String sql = "SELECT store.*,user_info.user_name ,user_info.mail FROM store LEFT JOIN user_info ON store.user_id=user_info.user_id  WHERE status = '"+status+"'";
        data.getParam().put("sql", sql);
        this.queryRecord(data, json);
    }

    public Store get_storebyid(Data data, JSONObject json) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String sql = "SELECT * FROM store  WHERE user_id = "+user_id;
        data.getParam().put("sql", sql);
        return this.queryRecord_store(data, json).get(0);
    }

    public void modifyStore(Data data, JSONObject json) throws JSONException, SQLException {
        String admin_id = data.getParam().has("admin_id") ? data.getParam().getString("admin_id") : null;
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;

        if (user_id != null  && !user_id.isEmpty()) {
            String sql = "update store";

            if (admin_id != null && !admin_id.isEmpty()) {
                sql = sql + " set status='"  + admin_id +"'";
            }else {
                sql = sql + " set status='2'";
            }

            sql = sql + " where user_id=" + user_id;
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);
        }

    }


    public void modifyUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String user_name = data.getParam().has("user_name") ? data.getParam().getString("user_name") : null;
        String user_role = data.getParam().has("user_role") ? data.getParam().getString("user_role") : null;
        String mail = data.getParam().has("mail") ? data.getParam().getString("mail") : null;
        String phone_number = data.getParam().has("phone_number") ? data.getParam().getString("phone_number") : null;

        if (user_id != null  && !user_id.isEmpty()) {
            String sql = "update user_info";
            if (user_name != null && !user_name.isEmpty()) {
                    sql = sql + " set user_name='"  + user_name +"'";
            }
            if (mail != null && !mail.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " ,mail='"  + mail +"'";
                }else {
                    sql = sql + " set mail='"  + mail +"'";
                }
            }
            if (phone_number != null && !phone_number.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " ,phone_number='"  + phone_number +"'";
                }else {
                    sql = sql + " set phone_number='"  + phone_number +"'";
                }
            }
            if (user_role != null  && !user_role.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , user_role = " + user_role ;
                }else {
                    sql = sql + " set user_role ="  + user_role ;
                }
            }
            sql = sql + " where user_id=" + user_id;
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);
        }

    }

//    public void queryUserRecord(Data data, JSONObject json) throws JSONException, SQLException {
//        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
//        if (user_id != null) {
//            String sql = "SELECT * FROM user_info WHERE user_id ='" +user_id+"'";
//            data.getParam().put("sql", sql);
//            this.queryRecord(data, json);
//        }
//
//    }

    public void get_Login_User_Info(Data data, JSONObject json) throws JSONException, SQLException {

        String sql = "SELECT o.user_id, o.user_name,o.mail , o.phone_number ,o.regist_time,o.user_role ,o.anony_name ,o.avatar, d.role_name ,c.password FROM (SELECT * FROM user_info WHERE user_id =  '" +data.getParam().getString("user_id")+"' )AS o LEFT JOIN user_role AS d ON  o.user_role =d.user_role LEFT JOIN user_login AS c ON o.user_id = c.user_id";
        showDebug("[getUser_Info]" + sql);
        data.getParam().put("sql", sql);
        this.queryRecord(data, json);//此时 aaData已经有该登陆用户的信息 ,其中aaData为数组对象
        this.getUser_role_type(data, json);  //带着user_id进入该函数查询 user_role表的全部数据,并调用 isadmin()加入json数据
    }
    public void getUserRecord(Data data, JSONObject json) throws JSONException, SQLException {

        String sql = this.createGetRecordSql(data);
        data.getParam().put("sql", sql);
        this.queryRecord(data, json);
    }
    public void Register(Data data, JSONObject json ,User user) throws JSONException, SQLException {
        String anony_name =(user.getAnony_name()!=null||!user.getAnony_name().isEmpty())?user.getAnony_name():null;
        String sql = "INSERT INTO user_info (user_id, anony_name ,user_name, phone_number) VALUES ("+user.getUser_id()+", '"+anony_name+"', '"+user.getUser_name()+"', '"+user.getPhone_number()+"')";
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);
        String sql2 ="INSERT INTO user_login (user_id, password) VALUES ("+user.getUser_id()+", '"+user.getPassword()+"')";
        data.getParam().put("sql", sql2);
        this.updateRecord(data, json);

    }

    public void getUserpsw(Data data, JSONObject json) throws JSONException, SQLException {
        String sql = "SELECT password FROM user_login WHERE user_id = "+data.getParam().getString("user_id");
        data.getParam().put("sql", sql);
        data.getParam().put("get_password", true);
        this.queryRecord(data, json);
    }
    public void modifyuserpsw(Data data, JSONObject json) throws JSONException, SQLException {
        String sql = "update user_login set password ='"+data.getParam().getString("password")+"' WHERE user_id = "+data.getParam().getString("user_id");
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);
    }

    //获取全部userrole种类 放进 role_option数组中 id 含有user_role代码 则查询对应的role_name
    public void getUser_role_type(Data data, JSONObject json) throws JSONException, SQLException {

        String sql = "SELECT * FROM user_role  ORDER BY user_role.user_role";
        data.getParam().put("sql", sql);
        data.getParam().put("user_role_type", true);//使USER_ROLE_TYPE存放在JSON中 ,参数为 role_option
        this.queryRecord(data, json);

    }
//    public void isadmin(Data data, JSONObject json) throws JSONException, SQLException {
//        String role_name = data.getParam().getString("role_name");
//        if ( role_name== "super_administrator"||role_name == "administrator") {
//            json.put("is_admin",true);
//        }
//        else {
//            json.put("is_admin",false);
//        }
//    }

//	<--=======================================================私有类=================================================================================-->

    private List<Store> queryRecord_store(Data data, JSONObject json) throws JSONException, SQLException {
        String resultMsg = "ok";
        int resultCode = 0;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
        List<Store> listSTORE = new LinkedList<>();
        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while(rs.next()) {
                Map map = new HashMap();
                Store store = new Store();
                for(int i = 0; i < fieldCount; ++i) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }
                jsonList.add(map);

                    BeanUtils.populate(store,map);
                    showDebug("user"+store.toString());
                listSTORE.add(store);

            }
            for(int i= 0;i<rsmd.getColumnCount();i++){
                String columnLabel =rsmd.getColumnLabel(i+1);
                jsonName.add(columnLabel);
                rs.close();
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            this.showDebug("[queryRecord]查询数据库出现错误：" + sql);
            resultCode = 10;
            resultMsg = "查询数据库出现错误！" + var13.getMessage();
        }

        queryDb.close();


            json.put("aaData", jsonList);
            showDebug("jsonList"+jsonList);
            json.put("listSTORE",listSTORE);
            showDebug("listSTORE"+listSTORE);


        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
        return  listSTORE;
    }

    private void updateRecord(Data data, JSONObject json) throws JSONException, SQLException {
        JSONObject param = data.getParam();
        int resultCode = 0;
        String resultMsg = "ok";
        Db updateDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[updateRecord]" + sql);
        try {
        updateDb.executeUpdate(sql);
        updateDb.close();

        } catch (Exception var13) {
        var13.printStackTrace();
        this.showDebug("[queryRecord]查询数据库出现错误：" + sql);
        resultCode = 10;
        resultMsg = "查询数据库出现错误！" + var13.getMessage();
    }
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
    }

    private void queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
        boolean user_role_type = data.getParam().has("user_role_type") ? data.getParam().getBoolean("user_role_type"):false ;
        boolean get_password = data.getParam().has("get_password") ? data.getParam().getBoolean("get_password"):false ;
        String resultMsg = "ok";
        int resultCode = 0;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
        List<User> listUser = new LinkedList<>();
        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while(rs.next()) {
                Map map = new HashMap();

                for(int i = 0; i < fieldCount; ++i) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }
                jsonList.add(map);
                if( user_role_type==false&&get_password==false){
                    User USER = new User();
//                    USER.setUser_id(rs.getInt("user_id"));
//                    showDebug("user_ID"+USER.getUser_id());
//                    USER.setUser_name(rs.getString("user_name"));
//                    USER.setMail(rs.getString("mail"));
//                    USER.setPhone_number(rs.getString("phone_number"));
//                    USER.setUser_role(rs.getInt("user_role"));
//                    USER.setRole_name(rs.getString("role_name"));
                    BeanUtils.populate(USER,map);
                    showDebug("user"+USER.toString());
                    listUser.add(USER);
                }
            }
            for(int i= 0;i<rsmd.getColumnCount();i++){
                String columnLabel =rsmd.getColumnLabel(i+1);
                jsonName.add(columnLabel);
                rs.close();
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            this.showDebug("[queryRecord]查询数据库出现错误：" + sql);
            resultCode = 10;
            resultMsg = "查询数据库出现错误！" + var13.getMessage();
        }

        queryDb.close();

        if(user_role_type==true){
            json.put("role_option", jsonList);
       } else {
            json.put("aaData", jsonList);
            showDebug("jsonList"+jsonList);
            json.put("UserList",listUser);
            showDebug("listUser"+listUser);
        }

        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
    }

    private String createGetRecordSql(Data data) throws JSONException {
        String sql = "SELECT  o.*, d.role_name ,c.password FROM (SELECT * FROM user_info ";
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String mapdetail = data.getParam().has("mapdetail") ? data.getParam().getString("mapdetail") : null;
        String user_name = data.getParam().has("user_name") ? data.getParam().getString("user_name") : null;
        String mail = data.getParam().has("mail") ? data.getParam().getString("mail") : null;
        String phone_number = data.getParam().has("phone_number") ? data.getParam().getString("phone_number") : null;
        String user_role = data.getParam().has("user_role") ? data.getParam().getString("user_role") : null;
        if (mapdetail != null  && !mapdetail.isEmpty()){
            sql = sql + " where CONVERT(user_id, CHAR) =" + user_id+" ";
        }else    if (user_id != null  && !user_id.isEmpty()) {
            sql = sql + " where CONVERT(user_id, CHAR) like  '%" + user_id+"%'";
        }
        if (user_name != null && !user_name.isEmpty()) {
           if(sql.indexOf("where")>-1){
               sql = sql + " AND user_name like '%"  + user_name +"%'";
           }else {
               sql = sql + " where user_name like '%"  + user_name +"%'";
           }
        }
        if (mail != null && !mail.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND mail like '%"  + mail +"%'";
            }else {
                sql = sql + " where mail like '%"  + mail +"%'";
            }
        }
        if (phone_number != null && !phone_number.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND phone_number like '%"  + phone_number +"%'";
            }else {
                sql = sql + " where phone_number like '%"  + phone_number +"%'";
            }
        }
        if (user_role != null  && !user_role.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND user_role = " + user_role ;
            }else {
                sql = sql + " where user_role ="  + user_role ;
            }
        }

        sql=sql+" ) AS o LEFT JOIN user_role AS d ON  o.user_role =d.user_role  LEFT JOIN user_login AS c ON o.user_id = c.user_id ";

        return sql;
    }
}
