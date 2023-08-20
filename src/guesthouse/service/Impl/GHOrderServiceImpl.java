package guesthouse.service.Impl;

import db.Data;
import guesthouse.dao.GHOrderDao;
import guesthouse.entitiy.GHOrder;
import guesthouse.service.GHOrderService;
import guesthouse.service.GuesthouseService;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class GHOrderServiceImpl implements GHOrderService {

   private  GHOrderDao ghOrderDao = new GHOrderDao();


    @Override
    public List<GHOrder> getGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException, ParseException {
        return ghOrderDao.getGHOrderRecord(data,json);
    }

    @Override
    public void getGHOrder_Count(Data data, JSONObject json) throws SQLException, JSONException {
        ghOrderDao.getGHOrder_Count(data, json);
    }

    @Override
    public void getGHOrderstatistic(Data data, JSONObject json) throws SQLException, JSONException {
        ghOrderDao.getGHOrderstatistic(data,json);
    }

    @Override
    public void modifyGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException {
        ghOrderDao.modifyGHOrderRecord(data, json);
    }

    @Override
    public void cancelGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException {
        ghOrderDao.cancelGHOrderRecord(data, json);
    }

    @Override
    public void addGHOrderRecord(Data data, JSONObject json) throws SQLException, JSONException {
        ghOrderDao.addGHOrderRecord(data,json);
    }
}
