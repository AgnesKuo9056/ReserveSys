package map.service.Impl;

import guesthouse.entitiy.Guesthouse;
import map.dao.MapDao;
import map.service.MapService;
import org.json.JSONException;
import org.json.JSONObject;
import db.Data;
import java.sql.SQLException;
import java.util.List;

public class MapServiceImpl implements MapService {
    private MapDao dao = new MapDao();

    @Override
    public List<Guesthouse> getGHRecord(Data data, JSONObject json) throws SQLException, JSONException {
        return dao.getGHRecord(data, json);
    }
    @Override
    public List<Guesthouse> queryGHRecord(Data data, JSONObject json) throws SQLException, JSONException {
        return dao.getGHRecord(data, json);
    }
}
