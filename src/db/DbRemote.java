//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbRemote {

	int debugLevel = 0;
	private Connection connection;
	private Statement statement;
	String USER="gpy";
	String PASSWORD="gpy";

	public void showDebug(String msg) {
		System.out.println("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "][maintain.device/dao/DbRemote]" + msg);
	}

	public DbRemote(String dbName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException var4) {
			var4.printStackTrace();
		}

		this.showDebug("加载了JDBC驱动");


		try {
			String connStr = "jdbc:mysql://www.ylxteach.net:3366/" + dbName + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
			this.connection = DriverManager.getConnection(connStr,USER,PASSWORD);
			this.showDebug("准备statement connection语句是：" + connStr);
			this.statement = this.connection.createStatement();
			this.showDebug("已经连接上数据库！");
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}

	public ResultSet executeQuery(String sql) {
		ResultSet resultset = null;

		try {
			if (this.debugLevel > 0) {
				this.showDebug("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "]" + "Db executeQuery:" + sql);
			}

			resultset = this.statement.executeQuery(sql);
		} catch (SQLException var4) {
			var4.printStackTrace();
		}

		return resultset;
	}

	public void executeUpdate(String sql) {
		try {
			if (this.debugLevel > 0) {
				this.showDebug("[" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "]" + "Db executeUpdate:" + sql);
			}

			this.statement.executeUpdate(sql);
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}

	public void close() {
		try {
			this.statement.close();
			this.connection.close();
			this.showDebug("操作数据完成，关闭了数据库！");
		} catch (SQLException var2) {
			var2.printStackTrace();
		}

	}
}
