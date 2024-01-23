package com.midterm.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conn;

	public static Connection createConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		final String SQL_URL = "jdbc:sqlserver://localhost:1433;databaseName=IndependentStudy;user=watcher;password=P@ssw0rd;"
				+ "encrypt=true;trustServerCertificate=true";
		conn = DriverManager.getConnection(SQL_URL);
//		System.out.println("Connection Status: " + !conn.isClosed()+ "\n");
		return conn;
	}

	public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null)
			conn.close();
//		System.out.println("Connection closed\n");
	}
}
