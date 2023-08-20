package favorite.service.Impl;

import db.Data;
import favorite.dao.FavoriteDao;
import favorite.service.FavoriteService;
import guesthouse.entitiy.Guesthouse;
import map.dao.MapDao;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDao();

    @Override
    public Map getFVRecord(Data data, JSONObject json) throws SQLException, JSONException {
        return favoriteDao.getFVRecord(data,json);
    }
}
