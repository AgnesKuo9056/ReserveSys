package favorite.service;

import db.Data;
import guesthouse.entitiy.Guesthouse;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FavoriteService {



    Map getFVRecord (Data data, JSONObject json) throws SQLException, JSONException;

}
