package comment.service;

import comment.entity.Comment;
import db.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface CommentService {

     void addComment(Data data, JSONObject json ) throws SQLException, InvocationTargetException, IllegalAccessException, JSONException;

     void deleteComment(Data data, JSONObject json ) throws SQLException, JSONException ;

     void modifyComment(Data data, JSONObject json) throws SQLException, JSONException, InvocationTargetException, IllegalAccessException ;

     public void getComment(Data data, JSONObject json) throws SQLException, JSONException;

     List<Comment> listComment(Data data, JSONObject json) throws SQLException, JSONException, ParseException;

     List<Comment> owner_listComments(Data data, JSONObject json) throws SQLException, JSONException, ParseException;

}
