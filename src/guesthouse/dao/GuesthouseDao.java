package guesthouse.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.util.BeanMapUtilByReflect;
import db.Data;
import db.Db;
import guesthouse.entitiy.Roomtype;
import org.apache.commons.beanutils.BeanUtils;
import user.entitiy.Img;
import user.entitiy.User;
import guesthouse.entitiy.Guesthouse;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static common.util.BeanMapUtilByReflect.JavaBean2Map;

public class GuesthouseDao {

    public GuesthouseDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][guesthouse/dao/MapDao]" + msg);

    }


    public void addGHRoomRecord(Data data, JSONObject json,List<Img> imgs) throws JSONException, SQLException {
        String room_name = data.getParam().has("fullname") ? data.getParam().getString("fullname") : null;
        String room_num = data.getParam().has("number") ? data.getParam().getString("number") : null;
        String cus_num = data.getParam().has("cus_num") ? data.getParam().getString("cus_num") : null;
        String price = data.getParam().has("price_number") ? data.getParam().getString("price_number") : null;
        String tags = data.getParam().has("tagsinput") ? data.getParam().getString("tagsinput") : null;
        String remarks_room = data.getParam().has("remarks_room") ? data.getParam().getString("remarks_room") : null;

         Guesthouse guesthouse = new Guesthouse("set_room");
         for(Img img : imgs){
             String sql = "insert into guest_diiferroom_num(gh_id,room_type_id,room_name,descript,room_pic,room_num,price,tags)";
             sql = sql + " values('" + guesthouse.getGh_id() + "'";
             sql = sql + " ,'" + cus_num + "'";
             sql = sql + " ,'" + room_name + "'";
             sql = sql + " ,'" + remarks_room + "'";
             sql = sql + " ,'" + img.getNewFileName() + "'";
             sql = sql + " ,'" + room_num + "'";
             sql = sql + " ,'" + price + "'";
             sql = sql + " ,'" + tags + "')";
             data.getParam().put("sql", sql);
             this.updateRecord(data, json);
         }
        json.put("gh_id",guesthouse.getGh_id());


    }
    public void addGHRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String gh_id = data.getParam().has("gh_id") ? data.getParam().getString("gh_id") : null;
        String owner_id = data.getParam().has("owner_id") ? data.getParam().getString("owner_id") : null;
        String gh_name = data.getParam().has("username") ? data.getParam().getString("username") : null;
        String gh_address = data.getParam().has("address") ? data.getParam().getString("address") : null;
        String gh_phone = data.getParam().has("phone") ? data.getParam().getString("phone") : null;
//        String gh_x = data.getParam().has("gh_x") ? data.getParam().getString("gh_x") : null;
//        String gh_y = data.getParam().has("gh_y") ? data.getParam().getString("gh_y") : null;
        String gh_intro = data.getParam().has("remarks_gh") ? data.getParam().getString("remarks_gh") : null;
        String gh_img = data.getParam().has("newFileName") ? data.getParam().getString("newFileName") : null;

            String sql = "insert into guesthouse_info(gh_id,owner_id,gh_name,gh_address,gh_phone,gh_intro,gh_img)";
            sql = sql + " values('" + gh_id + "'";
        sql = sql + " ,'" + owner_id + "'";
            sql = sql + " ,'" + gh_name + "'";
            sql = sql + " ,'" + gh_address + "'";
            sql = sql + " ,'" + gh_phone + "'";
            sql = sql + " ,'" + gh_intro + "'";
            sql = sql + " ,'" + gh_img + "')";
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);



    }

    public void deleteGHRecord(Data data, JSONObject json) throws JSONException, SQLException {
        int gh_id = data.getParam().has("gh_id") ? data.getParam().getInt("gh_id") : null;

            String sql = "delete from guesthouse_info,guest_diiferroom_num ,  where guesthouse_info.gh_id = guest_diiferroom_num.gh_id  AND guesthouse_info.gh_id=" + gh_id;
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);


    }



    public void modifyGHRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String gh_id = data.getParam().has("gh_id") ? data.getParam().getString("gh_id") : null;
        String owner_id = data.getParam().has("owner_id") ? data.getParam().getString("owner_id") : null;
        String gh_name = data.getParam().has("gh_name") ? data.getParam().getString("gh_name") : null;
        String gh_address = data.getParam().has("gh_address") ? data.getParam().getString("gh_address") : null;
        String gh_phone = data.getParam().has("gh_phone") ? data.getParam().getString("gh_phone") : null;
        String gh_x = data.getParam().has("gh_x") ? data.getParam().getString("gh_x") : null;
        String gh_y = data.getParam().has("gh_y") ? data.getParam().getString("gh_y") : null;
        String gh_img = data.getParam().has("gh_img") ? data.getParam().getString("gh_img") : null;
        String gh_examine_admin = data.getParam().has("gh_examine_admin") ? data.getParam().getString("gh_examine_admin") : null;
        String status = data.getParam().has("status") ? data.getParam().getString("status") : null;

        if (owner_id != null && !owner_id.isEmpty() ) {
            String sql = "update guesthouse_info";
            if (gh_name != null && !gh_name.isEmpty()) {
                    sql = sql + " set gh_name='"  + gh_name +"'";
            }
            if (gh_address != null && !gh_address.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " ,gh_address='"  + gh_address +"'";
                }else {
                    sql = sql + " set mail='"  + gh_address +"'";
                }
            }
            if (gh_phone != null && !gh_phone.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " ,gh_phone='"  + gh_phone +"'";
                }else {
                    sql = sql + " set phone_number='"  + gh_phone +"'";
                }
            }
            if (gh_x != null  && !gh_x.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , user_role = " + gh_x ;
                }else {
                    sql = sql + " set user_role ="  + gh_x ;
                }
            }
            if (gh_y != null  && !gh_y.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , gh_y = " + gh_y ;
                }else {
                    sql = sql + " set gh_y ="  + gh_y ;
                }
            }
            if (gh_img != null  && !gh_img.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , gh_img = " + gh_img ;
                }else {
                    sql = sql + " set gh_img ="  + gh_img ;
                }
            }
            if (gh_examine_admin != null  && !gh_examine_admin.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , gh_examine_admin = " + gh_examine_admin ;
                }else {
                    sql = sql + " set gh_examine_admin ="  + gh_examine_admin ;
                }
            }
            if (status != null  && !status.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , examine_state = " + status ;
                }else {
                    sql = sql + " set examine_state ="  + status ;
                }
            }
            sql = sql + " where gh_id=" + gh_id;
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
//    }egroupby /esearch

    public void getGH_Count(Data data, JSONObject json) throws JSONException, SQLException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String sql="";
        if(user_id!=null&&!user_id.isEmpty()){
            sql = "SELECT COUNT(gh_id) AS order_num  FROM  guesthouse_info WHERE owner_id = "+user_id;

        }else {
            sql = "SELECT COUNT(gh_id) AS order_num  FROM guesthouse_info \n" ;
        }

        data.getParam().put("sql", sql);
        this.querystatis(data, json);
    }


    public void getGH_rate(Data data, JSONObject json) throws JSONException, SQLException {
        int resultCode = 0;
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String sql="";

            sql = " SELECT \n" +
                    "  ROUND(IFNULL(status_count / total_count * 100, 0), 0) AS status_percentage\n" +
                    "FROM (\n" +
                    "  SELECT \n" +
                    "    COUNT(CASE WHEN guesthouse_order.status = 1 THEN 1 END) AS status_count,\n" +
                    "    COUNT(*) AS total_count\n" +
                    "  FROM guesthouse_order \n" +
                    "  LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id = guest_diiferroom_num.room_id \n" +
                    "  LEFT JOIN guesthouse_info ON guesthouse_info.gh_id = guest_diiferroom_num.gh_id \n" +
                    "  LEFT JOIN `comment` ON guesthouse_order.comment = comment.comment_id \n" ;
            if(user_id!=null&&!user_id.isEmpty()) {
                sql = sql + "  where owner_id = " + user_id + " ";
            }
                if(sql.indexOf("where")>-1){
                    sql = sql +    "AND guesthouse_order.status <> 0\n" ;
                }else {
                    sql = sql + "where guesthouse_order.status <> 0\n" ;
                }

            sql = sql + ") AS counts;";

        data.getParam().put("sql", sql);
        this.querystatis(data, json);
    }

    public void getGH_goodRate(Data data, JSONObject json) throws JSONException, SQLException {
        int resultCode = 0;
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String sql="";

            sql ="SELECT ROUND(IFNULL(qualified_count / total_count * 100, 0), 0) AS percentage\n" +
                    "FROM (\n" +
                    "  SELECT COUNT(*) AS qualified_count, \n" +
                    "         (SELECT COUNT(*) FROM guesthouse_order \n" +
                    "          LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id = guest_diiferroom_num.room_id \n" +
                    "          LEFT JOIN guesthouse_info  ON guesthouse_info.gh_id = guest_diiferroom_num.gh_id \n" +
                    "          LEFT JOIN `comment` ON guesthouse_order.comment = comment.comment_id \n" +
                    "          WHERE guesthouse_order.status = 1 " ;
        if(user_id!=null&&!user_id.isEmpty()) {
            sql = sql + "   AND owner_id = "+user_id ;
        }
        sql = sql + " ) AS total_count  FROM guesthouse_order \n" +
                    "  LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id = guest_diiferroom_num.room_id \n" +
                    "  LEFT JOIN guesthouse_info  ON guesthouse_info.gh_id = guest_diiferroom_num.gh_id \n" +
                    "  LEFT JOIN `comment` ON guesthouse_order.comment = comment.comment_id \n" +
                    "  WHERE guesthouse_order.status = 1 ";

            if(user_id!=null&&!user_id.isEmpty()) {
                sql = sql + "   AND owner_id = "+user_id ;
            }
            sql = sql + " AND comment.star >= 4.0    ) AS counts\n";



        data.getParam().put("sql", sql);
        this.querystatis(data, json);
    }


    public void getGHstatistic(Data data, JSONObject json) throws JSONException, SQLException {
        String egroupby = data.getParam().has("egroupby") ? data.getParam().getString("egroupby") : null;
        String esearch = data.getParam().has("esearch") ? data.getParam().getString("esearch") : null;
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String selectedValue = data.getParam().has("selectedValue") ? data.getParam().getString("selectedValue") : null;

        String sql ="SELECT\n" ;
        if(egroupby.equals("season")){
            sql=sql+    "   CASE \n" +
                    "    WHEN MONTH(check_in_date) BETWEEN 3 AND 5 THEN '0'\n" +
                    "    WHEN MONTH(check_in_date) BETWEEN 6 AND 8 THEN '1'\n" +
                    "    WHEN MONTH(check_in_date) BETWEEN 9 AND 11 THEN '2'\n" +
                    "\t\t ELSE '3'\n" +
                    "END as quarter,\n" ;
        }else if(egroupby.equals("month")){
            sql=sql+    "    MONTH(check_in_date) AS month,\n" ;
        }else {
            sql=sql+    "    YEAR(check_in_date) AS year,\n" ;

        }
        sql= sql+"\t\tchoose_info.owner_id,  choose_info.gh_id, order_id, choose_info.gh_name,  choose_info.gh_address, count(order_id) as order_num  , SUM( DATEDIFF(check_out_date, check_in_date) * rsv_num ) as total_days \n" +
                " FROM guesthouse_order " +
                "LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id = guest_diiferroom_num.room_id\n" +
                "LEFT JOIN (SELECT * FROM guesthouse_info\n" ;
        if(user_id!=null&&!user_id.isEmpty()){
            sql= sql+" where  guesthouse_info.owner_id = "+user_id;
        }
        if (esearch != null && !esearch.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql +   "AND (gh_name LIKE '%"+esearch+"%' OR gh_address LIKE '%"+esearch+"%')) " ;
            }else {
                sql = sql +   "where (gh_name LIKE '%"+esearch+"%' OR gh_address LIKE '%"+esearch+"%')) " ;
            }

        }
        sql= sql+ ") AS choose_info ON guest_diiferroom_num.gh_id = choose_info.gh_id\n" ;

        if(egroupby.equals("season")){
            sql=sql+  " WHERE YEAR(check_in_date) = " ;
            if(selectedValue!=null&&!selectedValue.isEmpty()){
                sql=sql+ selectedValue;
            }else {
                sql=sql+ "2023";
            }
            sql=sql+"  GROUP BY quarter" ;
        }else if(egroupby.equals("month")){
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
            sql= sql+" ,gh_id\n" ;
        }

        sql= sql+ " HAVING gh_name IS NOT NULL ORDER BY gh_id";
        data.getParam().put("sql", sql);
        this.querystatis(data, json);
    }

    public List<Guesthouse> getGHRecord(Data data, JSONObject json) throws JSONException, SQLException {

        String sql = this.createGetRecordSql(data);
        data.getParam().put("sql", sql);
        return this.queryRecord(data, json);
    }

    public  void Statistic(Data data , JSONObject json )  throws JSONException, SQLException {

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

    private List<Guesthouse>  queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
//        boolean user_role_type = data.getParam().has("user_role_type") ? data.getParam().getBoolean("user_role_type"):false ;
//        boolean get_password = data.getParam().has("get_password") ? data.getParam().getBoolean("get_password"):false ;
        String resultMsg = "ok";
        int resultCode = 0;
        int guh_id_now=-1;
        int guh_id_bef=-1;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
       // List<User> listUser = new LinkedList<>();
        List<Guesthouse> ListGuesthouses = new ArrayList<>();
        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            Guesthouse guesthouse_bef = new Guesthouse();


            while(rs.next()) {
                boolean chonfu =true;
                User owner = new User();
                Roomtype roomtype = new Roomtype();
                Map map = new HashMap();

                for(int i = 0; i < fieldCount; ++i) {
                    String columnName = rsmd.getColumnName(i + 1);
                    if (columnName.contains("time")) {
                        continue;
                    }
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));
                }

                //jsonList.add(map);
                guh_id_now=rs.getInt("gh_id");
                if (guh_id_bef != guh_id_now || guh_id_bef == -1) {

                    Guesthouse guesthouse_now = new Guesthouse();
                    BeanUtils.populate(guesthouse_now,map);
                    //showDebug("BeanUtil转换guesthouse_now->" +guesthouse_now.toString());

                    BeanUtils.populate(owner,map);
//                    LocalDate regist_time = rsTOlocalDate(rs,"regist_time");
//                    owner.setRegist_time(regist_time);
                    showDebug("owner->" +owner.toString());
                    guesthouse_bef=guesthouse_now;
                    chonfu =false;
                }

                BeanUtils.populate(roomtype,map);
               // showDebug("BeanUtil转换roomtype->" +roomtype.toString());
                //showDebug("guh_id_bef->" +guesthouse_bef.toString());
                guh_id_bef=guesthouse_bef.getGh_id();
                guesthouse_bef.addItem(roomtype);
                if(!chonfu) {

                    guesthouse_bef.setOwner(owner);
                    showDebug("新噌民宿->" +guesthouse_bef.toString());
                    ListGuesthouses.add(guesthouse_bef);
                }else {
                    showDebug("更新民宿->" +guesthouse_bef.toString());
                }

            }
            for(int i=0;i<ListGuesthouses.size();i++){
                showDebug(i+"为"+ListGuesthouses.get(i));
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, Object> tomap = JavaBean2Map(ListGuesthouses.get(i));
// 将user对象的属性映射到owner中
                Map<String, Object> ownerMap = JavaBean2Map(ListGuesthouses.get(i).getOwner());
              //  Map<String, Object> ownerJson = objectMapper.convertValue(ownerMap, Map.class);
                tomap.put("owner", ownerMap);

// 将roomtype对象的属性映射到guesthouse类型的roomtype列表中
                List<Map<String, Object>> roomtypeJsonList = new ArrayList<>();
                for (Roomtype roomType : ListGuesthouses.get(i).getRoomtype()) {
                    Map<String, Object> roomtypeMap = JavaBean2Map(roomType);
                    roomtypeJsonList.add(objectMapper.convertValue(roomtypeMap, Map.class));
                }
                tomap.put("roomtype", roomtypeJsonList);

               // String json = objectMapper.writeValueAsString(guesthouseMap);


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
        json.put("ListGuesthouses", ListGuesthouses);
            showDebug("ListGuesthouses"+ListGuesthouses);


        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
        return  ListGuesthouses;
    }

    private String createGetRecordSql(Data data) throws JSONException {
        String sql = "SELECT * FROM  (SELECT *  FROM  guesthouse_info ";
        String gh_id = data.getParam().has("gh_id") ? data.getParam().getString("gh_id") : null;
        String owner_id = data.getParam().has("owner_id") ? data.getParam().getString("owner_id") : null;
        String gh_name = data.getParam().has("gh_name") ? data.getParam().getString("gh_name") : null;
        String gh_address = data.getParam().has("gh_address") ? data.getParam().getString("gh_address") : null;
        String gh_phone = data.getParam().has("gh_phone") ? data.getParam().getString("gh_phone") : null;
        String status = data.getParam().has("status") ? data.getParam().getString("status") : null;

        if (gh_id != null  && !gh_id.isEmpty()) {
            sql = sql + " where CONVERT(gh_id, CHAR) like  '%" + gh_id+"%'";
        }
        if (owner_id != null && !owner_id.isEmpty()) {
           if(sql.indexOf("where")>-1){
               sql = sql + " AND  CONVERT(owner_id, CHAR) like '%"  + owner_id +"%'";
           }else {
               sql = sql + " where CONVERT(owner_id, CHAR)  like '%"  + owner_id +"%'";
           }
        }
        if (gh_name != null && !gh_name.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND gh_name like '%"  + gh_name +"%'";
            }else {
                sql = sql + " where gh_name like '%"  + gh_name +"%'";
            }
        }
        if (gh_address != null && !gh_address.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND gh_address like '%"  + gh_address +"%'";
            }else {
                sql = sql + " where gh_address like '%"  + gh_address +"%'";
            }
        }
        if(sql.indexOf("where")>-1){
            sql = sql + " AND  CONVERT(status, CHAR) ="  + status +"";
        }else {
            sql = sql + " where CONVERT(status, CHAR)  ="  + status +"";
        }
        sql=sql+" ) AS o LEFT JOIN guest_diiferroom_num AS d ON  o.gh_id =d.gh_id  LEFT JOIN room_type AS e ON d.room_type_id = e.room_type_id  LEFT JOIN user_info AS w ON  o.owner_id  = w.user_id ";

        return sql;
    }
    private LocalDate rsTOlocalDate (ResultSet rs , String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        return  localDate;
    }
}
