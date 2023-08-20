package sue.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.Data;
import db.Db;
import guesthouse.entitiy.GHOrder;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sue.entity.Sue;
import user.entitiy.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static common.util.BeanMapUtilByReflect.JavaBean2Map;

public class SueDao {

    public SueDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][Sue/dao/SueDao]" + msg);

    }

    public void addSue (Data data,JSONObject json , Sue sue) throws JSONException, SQLException {

        String sql =  "INSERT INTO sue (user_id, type, ad_note, ad_response, sue_create_date)\n" +
                "VALUES (?, ?, ?, ?, ?, ? ,? )";
        sql = sql + " values('" + sue.getSue_id() + "'";
        sql = sql + " ,'" + sue.getType() + "'";
        sql = sql + " ,'" + sue.getContent() + "'";
        sql = sql + " ,'" + sue.getAd_note() + "'";
        sql = sql + " ,'" + sue.getAd_response() + "'";
        sql = sql + " ,'STR_TO_DATE('" + sue.getSue_create_date()+"', '%Y-%m-%d') ";
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }

    public void deleteSue (Data data, JSONObject json) throws JSONException, SQLException {
        int order_id = data.getParam().has("order_id") ? data.getParam().getInt("order_id") : null;

        String sql = "DELETE FROM Sue  WHERE order_id = "+order_id ;
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }



    public void modifySue (Data data,JSONObject json , Sue sue) throws JSONException, SQLException {
        String  sue_id =sue.getSue_id();
        String  type =sue.getType();
        String   content  = sue.getContent()  ;
        String    ad_note  =sue.getAd_note() ;
        String    ad_response  = sue.getAd_response() ;
        String   deal_status  =sue.getDeal_status();

        String sql = "UPDATE Sue";

        if ( type != null && ! type.isEmpty()) {
            sql = sql + " set  type='"  +  type +"'";
        }
        if (content != null && !content.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,content='"  + content +"'";
            }else {
                sql = sql + " set content='"  + content +"'";
            }
        }
        if (ad_note != null && !ad_note.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,ad_note='"  + ad_note +"'";
            }else {
                sql = sql + " set ad_note='"  + ad_note +"'";
            }
        }
        if (ad_response != null && !ad_response.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,ad_response='"  + ad_response +"'";
            }else {
                sql = sql + " set ad_response='"  + ad_response +"'";
            }
        }

        if (deal_status!= null && !deal_status.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,deal_status='"  + deal_status+"'";
            }else {
                sql = sql + " set deal_status='"  + deal_status+"'";
            }
        }
        sql = sql + " where sue_id=" + sue_id;
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }

    public List<Sue> getSueById  (Data data, JSONObject json ) throws JSONException, SQLException, ParseException {

        String sql = this.createGetRecordSql(data);
        data.getParam().put("sql", sql);
        return this.queryRecord(data, json);
    }


    public List<Sue> listSues(Data data, JSONObject json) throws JSONException, SQLException, ParseException {

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

    private List<Sue> queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String resultMsg = "ok";
        int resultCode = 0;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
        //返回的封装bean数据
        List<Sue> ListSue = new ArrayList<>();

        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while (rs.next()) {

                Sue sue = new Sue();
                Map map = new HashMap();

                for (int i = 0; i < fieldCount; ++i) {

                    String columnName = rsmd.getColumnName(i + 1);
                    if (columnName.contains("create")) {
                        continue;
                    }
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }

                BeanUtils.populate(sue, map);
                LocalDate sue_create_date = rsTOlocalDate(rs,"sue_create_date");
                sue.setSue_create_date(sue_create_date);
                showDebug("BeanUtil转换comment->" + sue.toString());

                ListSue.add(sue);

            }
            for (int i = 0; i < ListSue.size(); i++) {
                showDebug(i + "为" + ListSue.get(i));
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, Object> tomap = JavaBean2Map(ListSue.get(i));

                jsonList.add(tomap);
            }
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String columnLabel = rsmd.getColumnLabel(i + 1);
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
        showDebug("jsonList" + jsonList);
        json.put("ListSue", ListSue);
        showDebug("ListSue" + ListSue);
        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);

        return ListSue;
    }

    private String createGetRecordSql(Data data) throws JSONException, ParseException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String status = data.getParam().has("status") ? data.getParam().getString("status") : null;
        String sql = "SELECT s.*,type.type_name FROM " ;

        if (user_id != null && !user_id.isEmpty() ) {

            sql = sql + "  (SELECT * FROM sue where user_id = " +user_id+ " ) ";

        }else {

            sql = sql + "  sue  ";

        }
        sql = sql +"AS s LEFT JOIN sue_type AS type ON s.type = type.type where deal_status ="+status;
        return sql;


    }

    private LocalDate rsTOlocalDate (ResultSet rs , String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        return  localDate;
    }
}
