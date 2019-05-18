package com.fsoft.project.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.fsoft.project.utils.LogUtils;

public class DbHelper {
	private static Connection connection;
	private static String DRIVER;
	private static String USER;
	private static String PASSWORD;
	private static String URL;

	public static Connection getConnection() {
		configDatabase();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			LogUtils.getLogger(DbHelper.class.getName()).error(e.getMessage());
		} catch (SQLException e) {
			LogUtils.getLogger(DbHelper.class.getName()).error(e.getMessage());
		}
		return connection;
	}

	public static void configDatabase() {
		Properties properties = new Properties();
		try {
			properties.load(DbHelper.class.getResourceAsStream("/dbconfig.properties"));
			DRIVER = properties.getProperty("driver");
			USER = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
			URL = properties.getProperty("url");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection connection, PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection connection, CallableStatement cstm,
			ResultSet rs) {
		try {
			if (cstm != null)
				cstm.close();
			if (rs != null)
				rs.close();
			if (cstm != null)
				cstm.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
