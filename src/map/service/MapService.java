package map.service;

import guesthouse.entitiy.Guesthouse;
import org.json.JSONException;
import org.json.JSONObject;
import db.Data;
import java.sql.SQLException;
import java.util.List;

public interface MapService {

    List<Guesthouse> getGHRecord(Data data, JSONObject json) throws SQLException, JSONException;
    List<Guesthouse> queryGHRecord(Data data, JSONObject json) throws SQLException, JSONException;

}
