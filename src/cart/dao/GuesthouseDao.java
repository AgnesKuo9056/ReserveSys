package cart.dao;

import cart.entitiy.Guesthouse;
import cart.entitiy.Roomtype;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.Data;
import db.Db;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static common.util.BeanMapUtilByReflect.JavaBean2Map;

public class GuesthouseDao {

    public GuesthouseDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][guesthouse/dao/MapDao]" + msg);

    }



    public void addGHRecord(Data data, JSONObject json) throws JSONException, SQLException {

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
        String examine_state = data.getParam().has("examine_state") ? data.getParam().getString("examine_state") : null;

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
            if (examine_state != null  && !examine_state.isEmpty()) {
                if(sql.indexOf("set")>-1){
                    sql = sql + " , examine_state = " + examine_state ;
                }else {
                    sql = sql + " set examine_state ="  + examine_state ;
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
//    }


    public List<Guesthouse> getGHRecord(Data data, JSONObject json) throws JSONException, SQLException {

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
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }

                //jsonList.add(map);
                guh_id_now=rs.getInt("gh_id");
                if (guh_id_bef != guh_id_now || guh_id_bef == -1) {

                    Guesthouse guesthouse_now = new Guesthouse();
                    BeanUtils.populate(guesthouse_now,map);
                    //showDebug("BeanUtil转换guesthouse_now->" +guesthouse_now.toString());

                    BeanUtils.populate(owner,map);
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
//                Map<String, Object> guesthouseMap = new Map<String, Object>() {
//                    @Override
//                    public int size() {
//                        return 0;
//                    }
//
//                    @Override
//                    public boolean isEmpty() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean containsKey(Object key) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean containsValue(Object value) {
//                        return false;
//                    }
//
//                    @Override
//                    public Object get(Object key) {
//                        return null;
//                    }
//
//                    @Override
//                    public Object put(String key, Object value) {
//                        return null;
//                    }
//
//                    @Override
//                    public Object remove(Object key) {
//                        return null;
//                    }
//
//                    @Override
//                    public void putAll(Map<? extends String, ?> m) {
//
//                    }
//
//                    @Override
//                    public void clear() {
//
//                    }
//
//                    @Override
//                    public Set<String> keySet() {
//                        return null;
//                    }
//
//                    @Override
//                    public Collection<Object> values() {
//                        return null;
//                    }
//
//                    @Override
//                    public Set<Entry<String, Object>> entrySet() {
//                        return null;
//                    }
//                };J
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
}
