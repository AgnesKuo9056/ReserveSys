//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package device.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import db.Data;
import db.Db;
import db.DbRemote;
import org.json.JSONException;
import org.json.JSONObject;


public class DeviceDao {
	public DeviceDao() {
	}

	public void showDebug(String msg) {
		System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][dao/Db]" + msg);
	}

	public void addDeviceRecord(Data data, JSONObject json) throws JSONException, SQLException {
		String deviceId = data.getParam().has("device_id") ? data.getParam().getString("device_id") : null;
		String deviceName = data.getParam().has("device_name") ? data.getParam().getString("device_name") : null;
        String creator = data.getParam().has("creator") ? data.getParam().getString("creator") : null;
        String create_time = data.getParam().has("create_time") ? data.getParam().getString("create_time") : null;
		if (deviceId != null && deviceName != null) {
			String sql = "insert into device_file(device_id,device_name,creator,create_time)";
			sql = sql + " values('" + deviceId + "'";
			sql = sql + " ,'" + deviceName + "'";
            sql = sql + " ,'" + creator + "'";
            sql = sql + " ,'" + create_time + "')";
			data.getParam().put("sql", sql);
			this.updateRecord(data, json);
		}

	}

	public void deleteDeviceRecord(Data data, JSONObject json) throws JSONException, SQLException {
		String id = data.getParam().has("id") ? data.getParam().getString("id") : null;
		if (id != null) {
			String sql = "delete from device_file where id=" + data.getParam().getString("id");
			data.getParam().put("sql", sql);
			this.updateRecord(data, json);
		}

	}

	public void modifyDeviceRecord(Data data, JSONObject json) throws JSONException, SQLException {
		String id = data.getParam().has("id") ? data.getParam().getString("id") : null;
		String deviceId = data.getParam().has("device_id") ? data.getParam().getString("device_id") : null;
		String deviceName = data.getParam().has("device_name") ? data.getParam().getString("device_name") : null;
        String creator = data.getParam().has("creator") ? data.getParam().getString("creator") : null;
        String create_time = data.getParam().has("create_time") ? data.getParam().getString("create_time") : null;
		if (id != null) {
			String sql = "update device_file";
			sql = sql + " set device_id='" + deviceId + "'";
			sql = sql + " ,device_name='" + deviceName + "'";
			sql = sql + " ,creator='" + creator + "'";
			sql = sql + " ,create_time='" + create_time + "'";
			sql = sql + " where id=" + id;
			data.getParam().put("sql", sql);
			this.updateRecord(data, json);
		}

	}

    public void queryDeviceRecord(Data data, JSONObject json) throws JSONException, SQLException {
        String deviceId = data.getParam().has("device_id") ? data.getParam().getString("device_id") : null;
        if (deviceId != null) {
            String sql = "SELECT * FROM device_file WHERE device_id ='" +deviceId+"'";
            data.getParam().put("sql", sql);
			this.queryRecord(data, json);
        }

    }

	public void getDeviceRecord(Data data, JSONObject json) throws JSONException, SQLException {
		String sql = this.createGetRecordSql(data);
		data.getParam().put("sql", sql);
		this.queryRecord(data, json);
	}

	public  void getGpsStatus(Data data , JSONObject json )  throws JSONException, SQLException {
		// 获取变量
		String resultMsg = "ok";
		int resultCode = 0;
		List jsonList = new ArrayList();
		String timefrom = (new SimpleDateFormat("yyyy-MM-dd 00:00:00")).format(new Date());
		String timeto = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		int totalGpsActiveCount = 0;
		//数据库操作
		DbRemote queryDb = new DbRemote("ylxdb");
		String sql = "SELECT COUNT(*) AS TOTAL FROM gps_history WHERE GPSTime BETWEEN '" +timefrom+"' AND '"+timeto+"'";
		showDebug("[getGpsStatus]" + sql);
		try {
			ResultSet rs = queryDb.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int fieldCount = rsmd.getColumnCount();
			while(rs.next()) {
				totalGpsActiveCount = rs.getInt("total");
				showDebug("[totalGpsActiveCount]" + totalGpsActiveCount);
			}
			rs.close();
		} catch (Exception var13) {
			var13.printStackTrace();
			this.showDebug("[getGpsStatus]查询数据库出现错误：" + sql);
			resultCode = 10;
			resultMsg = "查询数据库出现错误！" + var13.getMessage();
		}
		queryDb.close();
		//返回数据开始
		json.put("aaData", jsonList);
		json.put("gps_active_number",totalGpsActiveCount);
		json.put("result_msg", resultMsg);
		json.put("result_code", resultCode);
	}

	public  void export_deviceList(Data data , JSONObject json )  throws JSONException, SQLException {

	}

//	<--=======================================================私有类=================================================================================-->

	private void updateRecord(Data data, JSONObject json) throws JSONException, SQLException {
		JSONObject param = data.getParam();
		int resultCode = 0;
		String resultMsg = "ok";
		Db updateDb = new Db("jquery");
		String sql = data.getParam().getString("sql");
		this.showDebug("[updateRecord]" + sql);
		updateDb.executeUpdate(sql);
		updateDb.close();
		json.put("result_msg", resultMsg);
		json.put("result_code", resultCode);
	}

	private void queryRecord(Data data, JSONObject json) throws JSONException, SQLException {
		String resultMsg = "ok";
		int resultCode = 0;
		List jsonList = new ArrayList();
		List jsonName = new ArrayList();
		//数据操作
		Db queryDb = new Db("jquery");
		String sql = data.getParam().getString("sql");
		this.showDebug("[queryRecord]构造的sql语句是:" + sql);

		try {
			ResultSet rs = queryDb.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int fieldCount = rsmd.getColumnCount();

			while(rs.next()) {
				Map map = new HashMap();

				for(int i = 0; i < fieldCount; ++i) {
					map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));
				}

				jsonList.add(map);
			}

			rs.close();

			for(int i= 0;i<rsmd.getColumnCount();i++){
				String columnLabel =rsmd.getColumnLabel(i+1);
				jsonName.add(columnLabel);
			}
		} catch (Exception var13) {
			var13.printStackTrace();
			this.showDebug("[queryRecord]查询数据库出现错误：" + sql);
			resultCode = 10;
			resultMsg = "查询数据库出现错误！" + var13.getMessage();
		}

		queryDb.close();
		json.put("aaData", jsonList);
		json.put("aaFieldName", jsonName);
		json.put("result_msg", resultMsg);
		json.put("result_code", resultCode);
	}

	private String createGetRecordSql(Data data) throws JSONException {
		String sql = "select * from device_file";
		String id = data.getParam().has("id") ? data.getParam().getString("id") : null;
		if (id != null) {
			sql = sql + " where id=" + id;
		}

		return sql;
	}


	public void getGPSDeviceCountByHour(Data data, JSONObject json) throws JSONException {
		// 获取变量
		String resultMsg = "ok";
		int resultCode = 0;
		List jsonList = new ArrayList();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		String timefrom = (new SimpleDateFormat("yyyy-MM-dd 00:00:00")).format(cal.getTime());
		String timeto = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cal.getTime());
		int totalGpsActiveCount = 0;
		//数据库操作
		DbRemote queryDb = new DbRemote("ylxdb");
		String sql = "SELECT DATE_FORMAT(GPSTime,\"%Y-%m-%d %H\") AS time_interval, COUNT(*) as total FROM gps_history ";
		sql = sql +"  WHERE GPSTime BETWEEN \"" +timefrom+"\" AND \""+timeto+"\"";
		sql = sql +	"GROUP BY DATE_FORMAT(GPSTime,\"%Y-%m-%d %H\") " ;
		showDebug("[getGPSDeviceCountByHour]构造的SQL语句是" + sql);
		try {
			ResultSet rs = queryDb.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int fieldCount = rsmd.getColumnCount();
			while(rs.next()) {
				HashMap map = new HashMap();
				map.put("time_interval",rs.getString("time_interval"));
				map.put("total",rs.getInt("total"));
				jsonList.add(map);
			}
			rs.close();
		} catch (Exception var13) {
			var13.printStackTrace();
			this.showDebug("[Dao/getGPSDeviceCountByHour]查询数据库出现错误：" + sql);
			resultCode = 10;
			resultMsg = "查询数据库出现错误！" + var13.getMessage();
		}
		queryDb.close();
		//返回数据开始
		json.put("aaData", jsonList);
		json.put("result_msg", resultMsg);
		json.put("result_code", resultCode);

	}
}
