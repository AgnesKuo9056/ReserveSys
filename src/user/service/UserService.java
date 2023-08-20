package user.service;

import org.json.JSONException;
import org.json.JSONObject;
import db.Data;
import user.entitiy.Store;
import user.entitiy.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public interface UserService  {



     void getUserRecord(Data data, JSONObject json) throws SQLException, IOException, JSONException;

     void Register(Data data, JSONObject json , User user) throws JSONException, SQLException ;

     void modifyUserRecord(Data data, JSONObject json)throws JSONException, SQLException ;

     void deleteUserRecord(Data data, JSONObject json) throws JSONException, SQLException ;

     void addUserRecord(Data data, JSONObject json)throws JSONException, SQLException ;

     void getUserpsw(Data data, JSONObject json) throws JSONException, SQLException ;

     void modifyStore(HttpServletRequest request,Data data, JSONObject json)throws JSONException, SQLException ;

     void modifyuserpsw(Data data, JSONObject json) throws JSONException, SQLException ;

     void getUser_role_type(Data data, JSONObject json) throws JSONException, SQLException ;

     void login( Data data, JSONObject json) throws JSONException, SQLException ;

      void get_storebyid(HttpServletRequest request,Data data, JSONObject json) throws JSONException, SQLException;

     void upload_avatar(HttpServletRequest request, Data data, JSONObject json) throws JSONException, SQLException;

     Store upload_store_apply(HttpServletRequest request, Data data, JSONObject json) throws JSONException, SQLException;

     void get_store_apply(Data data, JSONObject json) throws JSONException, SQLException ;

}
