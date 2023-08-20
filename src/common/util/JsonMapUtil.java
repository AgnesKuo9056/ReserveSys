package common.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonMapUtil {

    public static Map JsonToMap(JSONObject j) throws JSONException {
        Map map = new HashMap< >();
        Iterator iterator = j.keys();
        while (iterator.hasNext())
        {
            String key = (String) iterator.next();
            Object value = j.get(key);
            map.put(key, value);
        }
        return map;
    }
}
