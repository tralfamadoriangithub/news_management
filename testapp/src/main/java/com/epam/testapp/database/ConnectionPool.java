package com.epam.testapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

	private static ConnectionProperties connectionProperties;
	private static ArrayBlockingQueue<Connection> freeConnections;
	private static ArrayBlockingQueue<Connection> buisyCoonnections;

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(
			ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public static void initialize() {

		System.out.println(connectionProperties);
		try {
			Class.forName(connectionProperties.getDriver());
			freeConnections = new ArrayBlockingQueue<Connection>(
					connectionProperties.getConnectionPoolSize());
			buisyCoonnections = new ArrayBlockingQueue<Connection>(
					connectionProperties.getConnectionPoolSize());

			while (freeConnections.remainingCapacity() > 0) {
				try {

					Connection conn = DriverManager.getConnection(
							connectionProperties.getUrl(),
							connectionProperties.getUser(),
							connectionProperties.getPassword());
					freeConnections.add(conn);

				} catch (SQLException e) {
					// TODO
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			buisyCoonnections.add(connection);
		} catch (InterruptedException e) {
			// TODO
		}
		return connection;
	}

	public void releaseConnection(Connection conn) {
		if (buisyCoonnections.contains(conn)) {
			freeConnections.add(conn);
			buisyCoonnections.remove(conn);
		} else {
			// TODO
		}
	}

	private void closeConnections(Collection<Connection> connectionArray) {
		connectionArray.forEach(conn -> {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		});
	}

	@Override
	protected void finalize() throws Throwable {
		closeConnections(freeConnections);
		closeConnections(buisyCoonnections);
		super.finalize();
	}
}
