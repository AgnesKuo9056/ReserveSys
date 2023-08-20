package map.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.Data;
import db.Db;
import common.util.BeanMapUtilByReflect;
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

public class MapDao {

    public MapDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][guesthouse/dao/MapDao]" + msg);

    }




    public  List<Guesthouse>  queryGHRecord(Data data, JSONObject json) throws JSONException, SQLException ,ParseException{

        String sql = this.createQueryRecordSql(data);
        data.getParam().put("sql", sql);
        return this.queryRecord(data, json);

    }


    public List<Guesthouse> getGHRecord(Data data, JSONObject json) throws JSONException, SQLException {

        String sql = this.createGetRecordSql(data);
        data.getParam().put("sql", sql);
        return this.queryRecord(data, json);
    }





//	<--=======================================================私有类=================================================================================-->



//    private void updateRecord(Data data, JSONObject json) throws JSONException, SQLException {
//        JSONObject param = data.getParam();
//        int resultCode = 0;
//        String resultMsg = "ok";
//        Db updateDb = new Db("reservesys");
//        String sql = data.getParam().getString("sql");
//        this.showDebug("[updateRecord]" + sql);
//        updateDb.executeUpdate(sql);
//        updateDb.close();
//        json.put("result_msg", resultMsg);
//        json.put("result_code", resultCode);
//    }

    private List<Guesthouse>  queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
//        boolean user_role_type = data.getParam().has("user_role_type") ? data.getParam().getBoolean("user_role_type"):false ;
//        boolean get_password = data.getParam().has("get_password") ? data.getParam().getBoolean("get_password"):false ;
        String resultMsg = "ok";
        int resultCode = 0;
        int guh_id_now=-1;
        int guh_id_bef=-1;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
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


       // json.put("aaFieldName", jsonName);
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
        String minLng = data.getParam().has("minLng") ? data.getParam().getString("minLng") : null;
        String maxLng = data.getParam().has("maxLng") ? data.getParam().getString("maxLng") : null;
        String minLat = data.getParam().has("minLat") ? data.getParam().getString("minLat") : null;
        String maxLat = data.getParam().has("maxLat") ? data.getParam().getString("maxLat") : null;
        String gh_phone = data.getParam().has("gh_phone") ? data.getParam().getString("gh_phone") : null;

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
        if (minLng != null && !minLng.isEmpty() && maxLng != null && !maxLng.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND gh_x BETWEEN convert('"  + minLng +"', decimal(10,6)) AND convert('"+maxLng+"', decimal(10,6))";
            }else {
                sql = sql + "  where gh_x BETWEEN convert('"  + minLng +"', decimal(10,6)) AND convert('"+maxLng+"', decimal(10,6))";
            }
        }
        if (minLat != null && !minLat.isEmpty()&&maxLng != null && !maxLng.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND gh_y BETWEEN convert('"  + minLat +"', decimal(10,6)) AND convert('"+maxLat+"', decimal(10,6))";
            }else {
                sql = sql + "  where gh_y BETWEEN convert('"  + minLat +"', decimal(10,6)) AND convert('"+maxLat+"', decimal(10,6))";
            }
        }

        sql=sql+" ) AS o LEFT JOIN guest_diiferroom_num AS d ON  o.gh_id =d.gh_id  LEFT JOIN room_type AS e ON d.room_type_id = e.room_type_id LEFT JOIN user_info AS w ON  o.owner_id  = w.user_id";

        return sql;
    }
    private String createQueryRecordSql(Data data) throws JSONException, ParseException {
            String minLng = data.getParam().has("minLng") ? data.getParam().getString("minLng") : null;
            String maxLng = data.getParam().has("maxLng") ? data.getParam().getString("maxLng") : null;
            String minLat = data.getParam().has("minLat") ? data.getParam().getString("minLat") : null;
            String maxLat = data.getParam().has("maxLat") ? data.getParam().getString("maxLat") : null;
            String cust_num = data.getParam().has("cust_num") ? data.getParam().getString("cust_num") : null;
            String price_min = data.getParam().has("price_min") ? data.getParam().getString("price_min") : null;
            String price_max = data.getParam().has("price_max") ? data.getParam().getString("price_max") : null;
            String tags = data.getParam().has("tags") ? data.getParam().getString("tags") : null;
            List<String> tag = new ArrayList();
            if (tags != null) {
                tag = Arrays.asList(tags.split(";"));
            }

            String start_date = data.getParam().has("start_date") ? data.getParam().getString("start_date") : null;
            String end_date = data.getParam().has("end_date") ? data.getParam().getString("end_date") : null;
            this.format_string2date(start_date, end_date);
            String sql = " SELECT h.*,info.*,(h.room_num-COALESCE(rsv_num,0))AS available_room From (SELECT * FROM guesthouse_info \n";
            if (minLng != null && !minLng.isEmpty() && maxLng != null && !maxLng.isEmpty()) {
                if (sql.indexOf("where") > -1) {
                    sql = sql + " AND gh_x BETWEEN convert('" + minLng + "', decimal(10,6)) AND convert('" + maxLng + "', decimal(10,6))";
                } else {
                    sql = sql + "  where gh_x BETWEEN convert('" + minLng + "', decimal(10,6)) AND convert('" + maxLng + "', decimal(10,6))";
                }
            }

            if (minLat != null && !minLat.isEmpty() && maxLng != null && !maxLng.isEmpty()) {
                if (sql.indexOf("where") > -1) {
                    sql = sql + " AND gh_y BETWEEN convert('" + minLat + "', decimal(10,6)) AND convert('" + maxLat + "', decimal(10,6))";
                } else {
                    sql = sql + "  where gh_y BETWEEN convert('" + minLat + "', decimal(10,6)) AND convert('" + maxLat + "', decimal(10,6))";
                }
            }

            String sql3 = "";
            sql3 = sql3 + " ) AS info LEFT JOIN   (SELECT *FROM guest_diiferroom_num \n";
            if (cust_num != null && !cust_num.isEmpty()) {
                sql3 = sql3 + "  where room_type_id<= " + cust_num + " \n";
            }

            if (price_min != null && !price_min.isEmpty() && price_max != null && !price_max.isEmpty()) {
                if (sql3.indexOf("where") > -1) {
                    sql3 = sql3 + "  AND  price BETWEEN " + price_min + " AND " + price_max + " ";
                } else {
                    sql3 = sql3 + "  where price BETWEEN " + price_min + " AND " + price_max + " ";
                }
            }

            if (tags != null && !tags.isEmpty()) {
                int i;
                if (sql3.indexOf("where") > -1) {
                    sql3 = sql3 + "  AND (tags LIKE '%" + (String)((List)tag).get(0) + "%' ";

                    for(i = 1; i < ((List)tag).size(); ++i) {
                        sql3 = sql3 + "  or tags LIKE '%" + (String)((List)tag).get(i) + "%' ";
                    }
                } else {
                    sql3 = sql3 + " where (tags LIKE '%" + (String)((List)tag).get(0) + "%' ";

                    for(i = 1; i < ((List)tag).size(); ++i) {
                        sql3 = sql3 + "  or tags LIKE '%" + (String)((List)tag).get(i) + "%' ";
                    }
                }

                sql3 = sql3 + " )";
            }

            sql = sql + sql3 + " )AS h  ON info.gh_id = h.gh_id  LEFT JOIN  ( SELECT o.room_id , SUM(rsv_num) as rsv_num FROM  guesthouse_order o";
            String sql2 = "";
            if (start_date != null && !start_date.isEmpty() && end_date != null && !end_date.isEmpty()) {
                sql2 = sql2 + "  where NOT (o.check_in_date >=  STR_TO_DATE('" + end_date + "', '%Y-%m-%d')  OR    o.check_out_date <= STR_TO_DATE('" + start_date + "', '%Y-%m-%d') ) ";
            }

            if (sql2.indexOf("where") > -1) {
                sql2 = sql2 + "  AND o.status <> 2 GROUP BY  o.room_id)  AS rsv ON h.room_id= rsv.room_id HAVING available_room > 0 ";
            } else {
                sql2 = sql2 + "  where o.status <> 2 GROUP BY  o.room_id)  AS rsv ON h.room_id= rsv.room_id HAVING available_room > 0";
            }

            sql = sql + sql2;
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
/**根据 日期 人数 价格 tag搜索
 *
 SELECT h.*,info.*,(h.room_num-COALESCE(rsv_num,0))AS available_room From
    (SELECT *FROM guest_diiferroom_num
        WHERE room_type_id<=2
             AND  price BETWEEN 50 AND 80
             AND tags LIKE "%靠走道%") AS h
 LEFT JOIN guesthouse_info AS info ON info.gh_id = h.gh_id
 LEFT JOIN
    ( SELECT o.room_id , SUM(rsv_num) as rsv_num FROM  guesthouse_order o
    WHERE NOT (o.check_in_date >=  STR_TO_DATE("2023-04-10", '%Y-%m-%d')
                OR
                o.check_out_date <= STR_TO_DATE("2023-04-07", '%Y-%m-%d') )
 AND o.status <> 2 GROUP BY  o.room_id)
 AS rsv ON h.room_id= rsv.room_id HAVING available_room > 0
 *
 *
 * */