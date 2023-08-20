package guesthouse.service;

import db.Data;
import guesthouse.entitiy.GHOrder;
import guesthouse.entitiy.Guesthouse;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface GHOrderService {

    List<GHOrder> getGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException, ParseException;

    void modifyGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException;


    void cancelGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException;

    void addGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException;

    void getGHOrderstatistic(Data data, JSONObject json) throws SQLException, JSONException;

    void getGHOrder_Count(Data data, JSONObject json) throws SQLException, JSONException;


}
