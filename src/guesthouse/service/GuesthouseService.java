package guesthouse.service;

import db.Data;
import guesthouse.entitiy.Guesthouse;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.Img;

import java.sql.SQLException;
import java.util.List;

public interface GuesthouseService {

    List<Guesthouse> getGHRecord(Data data, JSONObject json) throws SQLException, JSONException;

    void deleteGHRecord(Data data, JSONObject json) throws SQLException, JSONException;

    void addGHRecord(Data data, JSONObject json) throws SQLException, JSONException;

    void modifyGHRecord(Data data, JSONObject json) throws SQLException, JSONException;

    void getGHstatistic(Data data, JSONObject json) throws SQLException, JSONException;

    void getGH_Count(Data data, JSONObject json) throws SQLException, JSONException;

    void getGH_rate(Data data, JSONObject json) throws SQLException, JSONException;

    void getGH_goodRate(Data data, JSONObject json) throws SQLException, JSONException;

    void addGHRoomRecord(Data data, JSONObject json,List<Img> imgs) throws SQLException, JSONException;

}
