package com.employee.connections;

import static com.employee.constant.EmployeeConstants.CONNECTION_URL;
import static com.employee.constant.EmployeeConstants.CREATE_DATABASE;
import static com.employee.constant.EmployeeConstants.DATABASE_NAME;
import static com.employee.constant.EmployeeConstants.JDBC_DRIVER;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SQLConnections {

	public DataSource getDataSource() {

		MysqlDataSource dataSource = new MysqlDataSource();
		Connection con = getConnection();
		try {
			DatabaseMetaData metaData = con.getMetaData();
			dataSource.setURL(metaData.getURL() + "/" + DATABASE_NAME);
			dataSource.setUser("root");
			dataSource.setPassword("root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	private Connection getConnection() {

		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(CONNECTION_URL, "root", "root");
			ResultSet rs = con.getMetaData().getCatalogs();
			boolean isSuccess = isDatabaseExist(rs);
			if (!isSuccess) {
				isSuccess = createDatabase(con);
				if (!isSuccess) {
					// try again to create database one more time.
					return getConnection();
				}
			}
			rs.close();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean createDatabase(Connection con) {

		boolean isSuccess = false;
		try {
			PreparedStatement ps = con.prepareStatement(CREATE_DATABASE);
			isSuccess = ps.execute();
			if (isSuccess) {
				System.out.println("Successfully created Database: " + DATABASE_NAME);
			} else {
				System.err.println("Unable to create Database: " + DATABASE_NAME);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	private boolean isDatabaseExist(ResultSet rs) {

		try {
			while (rs.next()) {
				String databaseName = rs.getString(1);
				if (databaseName.equals(DATABASE_NAME)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
