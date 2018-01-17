package indi.yue.dtcp;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class OdpsDataSource implements DataSource {
	private static OdpsDataSource odpsDataSource = new OdpsDataSource();
	
//	private static String driverName = "com.aliyun.odps.jdbc.OdpsDriver";

	private String url = "http://service.odps.aliyun.com/api";
	private String dt_url="http://dt.odps.aliyun.com";
	private String access_id = null;
	private String access_key = null;
	private String project_name = null;
	private String charset = "utf-8";
	/*static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/

	public OdpsDataSource() {
		super();
	}

	public static OdpsDataSource getInstance() {
		if (odpsDataSource == null) {
			odpsDataSource = new OdpsDataSource();
		}
		return odpsDataSource;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public void setLogWriter(PrintWriter arg0) throws SQLException {

	}

	public void setLoginTimeout(int arg0) throws SQLException {

	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return null;
	}

	public Connection getConnection() throws SQLException {

		return this.getConnection(this.access_id, this.access_key);

	}

	public Connection getConnection(String access_id, String access_key) throws SQLException {
		if (access_id == null || "".equals(access_id)) {
			throw new SQLException("access_id is null");
		} else if (access_key == null || "".equals(access_key)) {
			throw new SQLException("access_key is null");
		} else if (project_name == null || "".equals(project_name)) {
			throw new SQLException("project_name is null");
		}

		Properties config = new Properties();
		config.put("access_id", access_id);
		config.put("access_key", access_key);
		config.put("project_name", this.project_name);
		config.put("charset", this.charset);

		return DriverManager.getConnection(this.url, config);
	}

	/*public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}*/

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccess_id() {
		return access_id;
	}

	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}

	public String getAccess_key() {
		return access_key;
	}

	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getDt_url() {
		return dt_url;
	}

	public void setDt_url(String dt_url) {
		this.dt_url = dt_url;
	}

}
