package favorite.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.Data;
import db.Db;
import favorite.entitiy.Favorite;
import favorite.entitiy.FavoriteItem;
import guesthouse.entitiy.Guesthouse;
import guesthouse.entitiy.Roomtype;
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

public class FavoriteDao {
    public FavoriteDao(){

    }
    public void showDebug(String msg) {
        System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][favorite/dao/FavoriteDao]" + msg);

    }
    //统计该gh_id被收藏数量级
    public void CaculateFved(Data data, JSONObject json) throws JSONException, SQLException {
        int gh_id = data.getParam().has("gh_id") ? data.getParam().getInt("gh_id") : null;

        String sql = "SELECT COUNT(user_id) FROM favorite WHERE gh_id LIKE '% "+gh_id+" %' ";
        data.getParam().put("sql", sql);
        this.updateRecord(data, json);


    }

    public Map getFVRecord(Data data, JSONObject json) throws JSONException, SQLException {

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
    private Map  queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
//        boolean user_role_type = data.getParam().has("user_role_type") ? data.getParam().getBoolean("user_role_type"):false ;
//        boolean get_password = data.getParam().has("get_password") ? data.getParam().getBoolean("get_password"):false ;
        String resultMsg = "ok";
        int resultCode = 0;
        int guh_id_now=-1;
        int guh_id_bef=-1;
        List jsonList = new ArrayList();
        List jsonName = new ArrayList();
       // List<Guesthouse> ListGuesthouses = new ArrayList<>();
        Favorite favoritelinklist = new Favorite();
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
                FavoriteItem favoriteItem =new FavoriteItem();
                Map map = new HashMap();

                for(int i = 0; i < fieldCount; ++i) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));

                }

                BeanUtils.populate(favoriteItem,map);
                    //showDebug("BeanUtil转换guesthouse_now->" +guesthouse_now.toString());
                System.out.println("favoriteItem =>"+favoriteItem);
                favoritelinklist.addItem(favoritelinklist,favoriteItem,rs.getString("room_name"),rs.getInt("room_num"));
                System.out.println("addItem后 =>"+favoritelinklist);
                jsonList.add(favoriteItem);

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
        json.put("favoritelinklist", favoritelinklist.getFavorite());
        showDebug("favoritelinklist"+favoritelinklist.getFavorite());


        // json.put("aaFieldName", jsonName);
        json.put("result_msg", resultMsg);
        json.put("result_code", resultCode);
        return  favoritelinklist.getFavorite();
    }

    private String createGetRecordSql(Data data) throws JSONException {
        String sql = "SELECT A.gh_id,B.owner_id,B.gh_name,B.gh_address,B.gh_img,B.grade,C.room_name,C.room_num FROM (SELECT * FROM favorite ";
        String user_id = data.getParam().has("user_id") ? data.getParam().getString("user_id") : null;

        if (user_id != null  && !user_id.isEmpty()) {
            sql = sql + "  WHERE user_id = " + user_id+" ";
        }

        sql=sql+")AS A LEFT JOIN guesthouse_info AS B ON A.gh_id = B.gh_id LEFT JOIN guest_diiferroom_num AS C ON B.gh_id= C.gh_id";

        return sql;
    }
}
