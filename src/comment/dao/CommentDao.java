package comment.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import comment.entity.Comment;
import db.Data;
import db.Db;
import guesthouse.entitiy.GHOrder;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
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

public class CommentDao {
    public CommentDao(){

    }

    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][Comment/dao/CommentDao]" + msg);

    }



    public void addComment  (Data data,JSONObject json, String order_id, Comment comment) throws JSONException, SQLException {

        String sql =  "INSERT INTO Sue (order_id, title, comment_content, cm_create_date,comment_state, name, star) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        sql = sql + " values('" + order_id + "'";
        sql = sql + " ,'" + comment.getTitle() + "'";
        sql = sql + " ,'" + comment.getComment_content() + "'";
        sql = sql + " ,'" + comment.getCm_create_date() + "'";
        sql = sql + " ,'" + comment.getComment_state() + "'";
        sql = sql + " ,'" + comment.getName() + "'";
        sql = sql + " ,'" + comment.getStar() + "')";
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }

    public void deleteComment  (Data data, JSONObject json) throws JSONException, SQLException {
        int order_id = data.getParam().has("order_id") ? data.getParam().getInt("order_id") : null;

        String sql = "DELETE FROM comment   WHERE order_id = "+order_id ;
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }



    public void modifyComment  (Data data,JSONObject json, String order_id, Comment comment) throws JSONException, SQLException {
        String  title =comment.getTitle();
        String   comment_content  =comment.getComment_content() ;
        String    comment_state  =comment.getComment_state();
        String    name  =comment.getName();
        String cm_create_date  =comment.getCm_create_date().toString();
        String   star  =comment.getStar();


        String sql = "UPDATE comment ";
        if (comment_content != null && !comment_content.isEmpty()) {
            sql = sql + " set comment_content='"  + comment_content +"'";
        }
        if (comment_state != null && !comment_state.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,comment_state='"  + comment_state +"'";
            }else {
                sql = sql + " set comment_state='"  + comment_state +"'";
            }
        }
        if (name != null && !name.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,name='"  + name +"'";
            }else {
                sql = sql + " set name='"  + name +"'";
            }
        }
        if (cm_create_date != null && !cm_create_date.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,cm_create_date = STR_TO_DATE('"+cm_create_date+"', '%Y-%m-%d') ";
            }else {
                sql = sql + " set cm_create_date = STR_TO_DATE('"+cm_create_date+"', '%Y-%m-%d') ";
            }
        }
        if (star != null && !star.isEmpty()) {
            if(sql.indexOf("set")>-1){
                sql = sql + " ,star='"  + star +"'";
            }else {
                sql = sql + " set star='"  + star +"'";
            }
        }
            sql = sql + " where order_id=" + order_id;
            data.getParam().put("sql", sql);
            this.updateRecord(data, json);


    }

    public Comment getCommentById  (Data data, JSONObject json ) throws JSONException, SQLException {
        String order_id = data.getParam().has("order_id") ? data.getParam().getString("order_id") : null;
        if (order_id != null) {
            String sql = "SELECT * FROM comment WHERE order_id ='" +order_id+"'";
            data.getParam().put("sql", sql);
            List<Comment> comments =this.queryRecord(data, json);
            return  comments.get(0);
        }else {
            json.put("error","没有传入id参数");
        }
        return  null;
    }



    public List<Comment> owner_listComments(Data data, JSONObject json) throws JSONException, SQLException, ParseException {
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;
        String search = data.getParam().has("search") ? data.getParam().getString("search") : null;

        String sql ="";
        sql= sql+"SELECT\n"+
                "   `comment`.*\n"+
                " FROM guesthouse_order \n"+
                "LEFT JOIN guest_diiferroom_num ON guesthouse_order.room_id = guest_diiferroom_num.room_id\n"+
                "LEFT JOIN guesthouse_info ON guesthouse_info.gh_id= guest_diiferroom_num.gh_id\n"+
                "LEFT JOIN comment ON `comment`.comment_id=guesthouse_order.`comment`\n"+
                "WHERE owner_id = "+user_id;
        if (search!=null&&!search.isEmpty()){
            sql= sql+" AND   (title LIKE '%"+search+"%' OR comment_content LIKE '%"+search+"%'  OR name LIKE '%"+search+"%')) " ;
        }
        data.getParam().put("sql", sql);
        return this.queryRecord(data, json);
    }


    public List<Comment> listComments(Data data, JSONObject json) throws JSONException, SQLException, ParseException {

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

    private List<Comment> queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String resultMsg = "ok";
        int resultCode = 0;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
        //返回的封装bean数据
        List<Comment> Listomment = new ArrayList<>();

        //数据操作
        Db queryDb = new Db("reservesys");
        String sql = data.getParam().getString("sql");
        this.showDebug("[queryRecord]构造的sql语句是:" + sql);

        try {
            ResultSet rs = queryDb.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while(rs.next()) {

                Comment comment = new Comment();
                Map map = new HashMap();

                for(int i = 0; i < fieldCount; ++i) {

                    String columnName = rsmd.getColumnName(i + 1);
                    if (columnName.contains("create")) {
                        continue;
                    }

                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }

                BeanUtils.populate(comment,map);
                LocalDate cm_create_date = rsTOlocalDate(rs,"cm_create_date");
                comment.setCm_create_date(cm_create_date);
//                /*将数据库中日期 单独提出来set进对象中*/
//               LocalDate cm_create_date = rsTOlocalDate(rs,"cm_create_date");
//                comment.s(cm_create_date);
//                LocalDate check_in_date = rsTOlocalDate(rs,"check_in_date");
//                ghOrder.setCheck_in_date(reserve_date);
//                LocalDate check_out_date = rsTOlocalDate(rs,"check_out_date");
//                ghOrder.setCheck_out_date(reserve_date);

                showDebug("BeanUtil转换comment->" +comment.toString());



                Listomment.add(comment);
            }
            for(int i=0;i<Listomment.size();i++){
                showDebug(i+"为"+Listomment.get(i));
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, Object> tomap = JavaBean2Map(Listomment.get(i));

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
        json.put("Listomment", Listomment);
        showDebug("Listomment"+Listomment);


        json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
        return  Listomment;
    }

    private String createGetRecordSql(Data data) throws JSONException, ParseException {
        String title = data.getParam().has("title") ? data.getParam().getString("title") : null;
        String name = data.getParam().has("name") ? data.getParam().getString("name") : null;
        String comment_content = data.getParam().has("comment_content") ? data.getParam().getString("comment_content") : null;
        String orderby = data.getParam().has("orderby") ? data.getParam().getString("orderby") : null;
        String sql = "  SELECT * FROM `comment` " ;
        if (title != null && !title.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND title like '%"  + title +"%'";
            }else {
                sql = sql + " where title like '%"  + title +"%'";
            }
        }
        if (name != null && !name.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND name like '%"  + name +"%'";
            }else {
                sql = sql + " where name like '%"  + name +"%'";
            }
        }
        if (comment_content != null && !comment_content.isEmpty()) {
            if(sql.indexOf("where")>-1){
                sql = sql + " AND comment_content like '%"  + comment_content +"%'";
            }else {
                sql = sql + " where comment_content like '%"  + comment_content +"%'";
            }
        }
        if(sql.indexOf("where")>-1){
            sql = sql + " AND comment_state = '1'";
        }else {
            sql = sql + "where comment_state = '1'";
        }
        if (orderby != null && !orderby.isEmpty()) {
            sql = sql + " ORDER BY "  + orderby;
        }


        return sql;

    }


    private LocalDate rsTOlocalDate (ResultSet rs , String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        return  localDate;
    }
//    @Select("Select comment_id,comment_content,comment_state,user_id,agency_name,comment_time from Comment natural join agency where user_ID=#{userID}")
//    public List<UserComment> getCommentByUserID(Integer userID);
//
//    @Delete("DELETE FROM COMMENT WHERE comment_id=#{commentID}")
//    public void deleteCommentBycommentID(Integer commentID);
//
//    /**
//     *普通用户查看所有的评论
//     */
//    @Select("select comment_time,comment_content from Comment where comment_state=2 and agency_id=#{agencyID} order by comment_time desc")
//    public List<Comment> getAgencyComment(Integer agencyID) throws Exception;
//
//
//    /*
//    * 用户增加自己的评论
//    * */
//    @Insert("insert into Comment(comment_id,comment_content,comment_state,comment_time,user_id,agency_id) " +
//            "values(#{commentID}, #{commentContent}, #{commentState}, #{commentTime}, #{userID}, #{agencyID})")


}
