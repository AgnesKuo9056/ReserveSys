package sue.service;

import db.Data;
import guesthouse.entitiy.Guesthouse;
import org.json.JSONException;
import org.json.JSONObject;
import sue.entity.Sue;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface SueService {

     void addSue(Data data, JSONObject json ) throws SQLException, InvocationTargetException, IllegalAccessException, JSONException;

     void deleteSue(Data data, JSONObject json ) throws SQLException, JSONException;


    void modifySue(Data data, JSONObject json) throws SQLException, JSONException, InvocationTargetException, IllegalAccessException;


     void getSue(Data data, JSONObject json) throws SQLException, JSONException, ParseException;


    List<Sue> listSue(Data data, JSONObject json) throws SQLException, JSONException, ParseException;


}
