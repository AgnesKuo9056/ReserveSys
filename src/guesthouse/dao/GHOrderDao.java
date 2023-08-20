package guesthouse.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.Data;
import db.Db;
import guesthouse.entitiy.GHOrder;
import guesthouse.entitiy.Guesthouse;
import guesthouse.entitiy.Roomtype;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static common.util.BeanMapUtilByReflect.JavaBean2Map;

public class GHOrderDao {
    public  GHOrderDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][guesthouse/dao/SueDao]" + msg);

    }



    public void addGHOrderRecord(Data data, JSONObject json) throws JSONException, SQLException {

        String owner_id = data.getParam().has("owner_id") ? data.getParam().getString("owner_id") : null;
        String gh_name = data.getParam().has("gh_name") ? data.getParam().getString("gh_name") : null;
        String gh_address = data.getParam().has("gh_address") ? data.getParam().getString("gh_address") : null;
        String gh_phone = data.getParam().has("gh_phone") ? data.getParam().getString("gh_phone") : null;
        String gh_x = data.getParam().has("gh_x") ? data.getParam().getString("gh_x") : null;
        String gh_y = data.getParam().has("gh_y") ? data.getParam().getString("gh_y") : null;

        String sql = "insert into guesthouse_info(owner_id,gh_name,gh_address,gh_phone,gh_x,gh_y)";
        sql = sql + " values('" + owner_id + "'";
        sql = sql + " ,'" + gh_name + "'";
        sql = sql + " ,'" + gh_address + "'";
        sql = sql + " ,'" + gh_phone + "'";
        sql = sql + " ,'" + gh_x + "'";
        sql = sql + " ,'" + gh_y + "')";
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);



    }

    public void cancelGHOrderRecord(Data data, JSONObject json) throws JSONException, SQLException {
        int order_id = data.getParam().has("order_id") ? data.getParam().getInt("order_id") : null;

        String sql = "UPDATE guesthouse_order SET guesthouse_order.status = 2 WHERE order_id = "+order_id ;
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }



    public void modifyGHOrderRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String order_id = data.getParam().has("order_id") ? data.getParam().getString("order_id") : null;
        String cus_name = data.getParam().has("cus_name") ? data.getParam().getString("cus_name") : null;
        String cus_phone_num = data.getParam().has("cus_phone_num") ? data.getParam().getString("cus_phone_num") : null;
        String total = data.getParam().has("total") ? data.getParam().getString("total") : null;

        if (order_id != null && !order_id.isEmpty() ) {
            String sql = "UPDATE guesthouse_order ";
            if (cus_name != null && !cus_name.isEmpty()) {
                sql = sql + " set cus_name='"  + cus_name +"'";
            }
            if (cus_phone_num != null && !cus_phone_num.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " ,cus_phone_num='"  + cus_phone_num +"'";
                }else {
                    sql = sql + " set cus_phone_num='"  + cus_phone_num +"'";
                }
            }
            if (total != null && !total.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " ,total='"  + total +"'";
                }else {
                    sql = sql + " set total='"  + total +"'";
                }
            }

            sql = sql + " where order_id=" + order_id;
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
    public void getGHOrder_Count(Data data, JSONObject json) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String sql="";
        if(user_id!=null&&!user_id.isEmpty()){
            sql = "SELECT COUNT(order_id) AS order_num  FROM guesthouse_order LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id=guest_diiferroom_num.room_id LEFT JOIN guesthouse_info on guesthouse_info.gh_id=guest_diiferroom_num.gh_id WHERE owner_id = "+user_id;

        }else {
            sql = "SELECT COUNT(order_id) AS order_num  FROM guesthouse_order \n" ;
        }

        data.getParam().put("sql", sql);
        this.querystatis(data, json);

    }

    public void getGHOrderstatistic(Data data, JSONObject json) throws JSONException, SQLException {
        String amgroupby = data.getParam().has("amgroupby") ? data.getParam().getString("amgroupby") : null;
        String amsearch = data.getParam().has("amsearch") ? data.getParam().getString("amsearch") : null;
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String selectedValue = data.getParam().has("selectedValue") ? data.getParam().getString("selectedValue") : null;

        String sql ="SELECT\n" ;
        if(amgroupby.equals("season")){
            sql=sql+    "   CASE \n" +
                    "    WHEN MONTH(check_in_date) BETWEEN 3 AND 5 THEN '0'\n" +
                    "    WHEN MONTH(check_in_date) BETWEEN 6 AND 8 THEN '1'\n" +
                    "    WHEN MONTH(check_in_date) BETWEEN 9 AND 11 THEN '2'\n" +
                    "\t\t ELSE '3'\n" +
                    "END as quarter,\n" ;
        }else if(amgroupby.equals("month")){
            sql=sql+    "    MONTH(check_in_date) AS month,\n" ;
        }else {
            sql=sql+    "    YEAR(check_in_date) AS year,\n" ;

        }
        sql= sql+"\t\tchoose_info.owner_id,  choose_info.gh_id, order_id, choose_info.gh_name,  choose_info.gh_address, count(order_id) as order_num  , SUM(total) as revenue FROM guesthouse_order\n" +
                "LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id = guest_diiferroom_num.room_id\n" +
                "LEFT JOIN (SELECT * FROM guesthouse_info\n" ;
        if(user_id!=null&&!user_id.isEmpty()){
            sql= sql+" where  guesthouse_info.owner_id = "+user_id;
        }
        if (amsearch != null && !amsearch.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql +   "AND (gh_name LIKE '%"+amsearch+"%' OR gh_address LIKE '%"+amsearch+"%')) " ;
            }else {
                sql = sql +   "where (gh_name LIKE '%"+amsearch+"%' OR gh_address LIKE '%"+amsearch+"%')) " ;
            }

        }
        sql= sql+ " ) AS choose_info ON guest_diiferroom_num.gh_id = choose_info.gh_id\n" ;

        if(amgroupby.equals("season")){
            sql=sql+  " WHERE YEAR(check_in_date) = " ;
            if(selectedValue!=null&&!selectedValue.isEmpty()){
                sql=sql+ selectedValue;
            }else {
                sql=sql+ "2023";
            }
            sql=sql+"  GROUP BY quarter" ;

        }else if(amgroupby.equals("month")){

            sql=sql+  " WHERE YEAR(check_in_date) = " ;
            if(selectedValue!=null&&!selectedValue.isEmpty()){
                sql=sql+ selectedValue;
            }else {
                sql=sql+ "2023";
            }
            sql=sql+"  GROUP BY month" ;

        }else {
            sql=sql+  "  GROUP BY year" ;

        }
        if(user_id!=null&&!user_id.isEmpty()){
            sql= sql+" ,owner_id\n" ;
        }

        sql= sql+ " HAVING gh_name IS NOT NULL ORDER BY owner_id";
        data.getParam().put("sql", sql);
        this.querystatis(data, json);
    }


    public List<GHOrder> getGHOrderRecord(Data data, JSONObject json) throws JSONException, SQLException, ParseException {

        String sql = this.createGetRecordSql(data);
        data.getParam().put("sql", sql);
        return this.queryRecord(data, json);
    }



//	<--=======================================================私有类=================================================================================-->



    private void updateRecord(Data data, JSONObject json) throws JSONException, SQLException {
        JSONObject param = data.getParam();
        int resultCode = 0;
        String resultMsg = "ok";
        Db updateDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[updateRecord]" + sql);
        updateDb.executeUpdate(sql);
        updateDb.close();
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
    }

    private void querystatis(Data data, JSONObject json) throws JSONException, SQLException {
        String resultMsg = "ok";
        int resultCode = 0;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while (rs.next()) {
                Map map = new HashMap();

                for (int i = 0; i < fieldCount; ++i) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));
                }

                jsonList.add(map);
            }

            rs.close();

            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String columnLabel = rsmd.getColumnLabel(i + 1);
                jsonName.add(columnLabel);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            this.showDebug("[queryRecord]查询数据库出现错误：" + sql);
            resultCode = 10;
            resultMsg = "查询数据库出现错误！" + var13.getMessage();
        }

        queryDb.close();
        json.put("aaData", jsonList);
        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
    }

    private List<GHOrder> queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
//        boolean user_role_type = data.getParam().has("user_role_type") ? data.getParam().getBoolean("user_role_type"):false ;
//        boolean get_password = data.getParam().has("get_password") ? data.getParam().getBoolean("get_password"):false ;
        String resultMsg = "ok";
        int resultCode = 0;
        int guh_id_now=-1;
        int guh_id_bef=-1;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
        // List<User> listUser = new LinkedList<>();
        List<GHOrder> ListGHOrder = new ArrayList<>();
        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while(rs.next()) {

                User owner = new User();
                Map map = new HashMap();

                for(int i = 0; i < fieldCount; ++i) {

                    String columnName = rsmd.getColumnName(i + 1);
                    if (columnName.contains("date")) {
                        continue;
                    }
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }
                GHOrder ghOrder = new GHOrder();
                BeanUtils.populate(ghOrder,map);

                /*将数据库中日期 单独提出来set进对象中*/
               LocalDate reserve_date = rsTOlocalDate(rs,"reserve_date");
                ghOrder.setReserve_date(reserve_date);
                LocalDate check_in_date = rsTOlocalDate(rs,"check_in_date");
                ghOrder.setCheck_in_date(reserve_date);
                LocalDate check_out_date = rsTOlocalDate(rs,"check_out_date");
                ghOrder.setCheck_out_date(reserve_date);

                showDebug("BeanUtil转换ghOrder->" +ghOrder.toString());

                BeanUtils.populate(owner,map);
                showDebug("owner->" +owner.toString());
                ghOrder.setOwner(owner);

                ListGHOrder.add(ghOrder);
            }
            for(int i=0;i<ListGHOrder.size();i++){
                showDebug(i+"为"+ListGHOrder.get(i));
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, Object> tomap = JavaBean2Map(ListGHOrder.get(i));
// 将user对象的属性映射到owner中
                Map<String, Object> ownerMap = JavaBean2Map(ListGHOrder.get(i).getOwner());
                //  Map<String, Object> ownerJson = objectMapper.convertValue(ownerMap, Map.class);
                tomap.put("owner", ownerMap);

                jsonList.add(tomap);
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
        json.put("ListGHOrder", ListGHOrder);
        showDebug("ListGHOrder"+ListGHOrder);


        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
        return  ListGHOrder;
    }

    private String createGetRecordSql(Data data) throws JSONException, ParseException {
        String end_date = data.getParam().has("end_date") ? data.getParam().getString("end_date") : null;
        String start_date = data.getParam().has("start_date") ? data.getParam().getString("start_date") : null;
        //List<Date> formatDATE = format_string2date(start_date,end_date);
        String status = data.getParam().has("status") ? data.getParam().getString("status") : null;
//        owner_id存在 代表此时为商家查看订单 user_id则为用户查看订单  均不存在则为管理员查看
        String owner_id = data.getParam().has("owner_id") ? data.getParam().getString("owner_id") : null;
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        //order_id 不为null代表 查询单个订单
        String order_id = data.getParam().has("order_id") ? data.getParam().getString("order_id") : null;

        // List<Date> formatDATE = format_string2date(start_date,end_date);

// o.order_id,o.cus_name ,o.cus_phone_num ,o.reserve_date , o.check_in_date, o.check_out_date , o.note , o.`status` , o.total
        String sql = "  SELECT o.* ,searchowner.user_name,searchowner.phone_number,gdr.gh_id,gdr.room_name,gdr.room_type_id\n" +
                "      FROM     (   SELECT  *\n" +
                "             FROM guesthouse_order\n" ;

       if(order_id != null && !order_id.isEmpty()){

           sql = sql +  "\t\twhere  order_id  = "+order_id+"\n" ;

       }else {
           if (status != null && !status.isEmpty()) {

               sql = sql +  "\t\twhere  guesthouse_order.status  = "+status+"\n" ;
           }

           //表示为 用户查看订单
           if (user_id != null && !user_id.isEmpty()) {
               if(sql.indexOf("where")>-1){
                   sql = sql +  "\t\tAND  user_id  = "+user_id+"\n" ;
               }else {
                   sql = sql +  "\t\twhere  user_id  = "+user_id+"\n" ;
               }

           }

           if (start_date != null && !start_date.isEmpty() && end_date != null && !end_date.isEmpty()) {

               if(sql.indexOf("where")>-1){
                   sql = sql + "  \t\tAND  (STR_TO_DATE('"+start_date+"', '%Y-%m-%d') BETWEEN check_in_date AND check_out_date   OR STR_TO_DATE('"+end_date+"', '%Y-%m-%d') BETWEEN check_in_date AND check_out_date )\n" ;

               }else {
                   sql = sql + "  \t\twhere  (STR_TO_DATE('"+start_date+"', '%Y-%m-%d') BETWEEN check_in_date AND check_out_date   OR STR_TO_DATE('"+end_date+"', '%Y-%m-%d') BETWEEN check_in_date AND check_out_date )\n" ;

               }
           }
       }

        sql =sql+  ") AS  o  LEFT JOIN guest_diiferroom_num AS gdr ON o.room_id = gdr.room_id\n" +
                "                LEFT JOIN guesthouse_info AS gi ON gdr.gh_id = gi.gh_id\n" +
                "                LEFT JOIN ";


/////表示此时为商家查看订单
        if (owner_id != null && !owner_id.isEmpty()) {

                sql = sql +  "(Select  user_id,user_name ,phone_number FROM user_info where user_id = "+owner_id+" ) " ;

        }else {
            sql = sql +  " user_info " ;
        }
         sql = sql +" AS searchowner ON searchowner.user_id = gi.owner_id  HAVING  searchowner.user_name IS NOT NULL";

        return sql;

    }
    private  List<Date> format_string2date(String start ,String end) throws ParseException {
        List<Date> format = new ArrayList<Date>();
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate_start = inputDateFormat.parse(start);
        format.add(inputDate_start);
        Date inputDate_end = inputDateFormat.parse(end);
        format.add(inputDate_end);
        return format;

    }
    private LocalDate rsTOlocalDate (ResultSet rs , String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        return  localDate;
    }

}
