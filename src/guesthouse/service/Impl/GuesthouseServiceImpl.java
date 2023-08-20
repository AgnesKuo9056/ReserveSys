package guesthouse.service.Impl;

import db.Data;
import guesthouse.dao.GuesthouseDao;
import guesthouse.entitiy.Guesthouse;
import guesthouse.service.GuesthouseService;
import map.dao.MapDao;
import org.json.JSONException;
import org.json.JSONObject;
import user.entitiy.Img;

import java.sql.SQLException;
import java.util.List;

public class GuesthouseServiceImpl implements GuesthouseService {
    private GuesthouseDao guesthouseDao = new GuesthouseDao();

    @Override
    public void getGH_Count(Data data, JSONObject json) throws SQLException, JSONException {
        guesthouseDao.getGH_Count(data,json);
    }

    @Override
    public void getGH_rate(Data data, JSONObject json) throws SQLException, JSONException {
        guesthouseDao.getGH_rate(data, json);
    }

    @Override
    public void getGH_goodRate(Data data, JSONObject json) throws SQLException, JSONException {
        guesthouseDao.getGH_goodRate(data, json);
    }

    @Override
    public void getGHstatistic(Data data, JSONObject json) throws SQLException, JSONException{
        guesthouseDao.getGHstatistic(data,json);
    }
    @Override
    public void modifyGHRecord(Data data, JSONObject json) throws SQLException, JSONException {
        guesthouseDao.modifyGHRecord(data,json);
    }

    @Override
    public    List<Guesthouse> getGHRecord(Data data, JSONObject json) throws SQLException, JSONException {
       return guesthouseDao.getGHRecord(data,json);
    }

    @Override
    public void deleteGHRecord(Data data, JSONObject json) throws SQLException, JSONException {
        guesthouseDao.deleteGHRecord(data,json);
    }

    @Override
    public void addGHRoomRecord(Data data, JSONObject json, List<Img> imgs) throws SQLException, JSONException {
        guesthouseDao.addGHRoomRecord(data,json,imgs);
    }

    @Override
    public void addGHRecord(Data data, JSONObject json) throws SQLException, JSONException {
        guesthouseDao.addGHRecord(data,json);
    }
}
