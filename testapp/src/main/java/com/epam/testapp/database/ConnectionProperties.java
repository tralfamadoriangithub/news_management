package com.epam.testapp.database;

public class ConnectionProperties {
	
	public ConnectionProperties(){}

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "testapp";
	private String password = "oracle";
	private int connectionPoolSize = 10;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	@Override
	public String toString() {
		return "ConnectionProperties [driver=" + driver + ", url=" + url
				+ ", user=" + user + ", password=" + password
				+ ", connectionPoolSize=" + connectionPoolSize + "]";
	}
	
	
}
