package com.employee;

import static com.employee.constant.EmployeeConstants.CONNECTION_URL;
import static com.employee.constant.EmployeeConstants.JDBC_DRIVER;

import java.sql.Connection;
import java.sql.DriverManager;

import com.employees.Connections;
import com.employees.impl.ConnectionImpl;

public class App {

	public static void main(String[] args) {
		Connections connections = new ConnectionImpl(getConnection());
		// connections.createRecord();
		connections.getRecord();
	}

	private static Connection getConnection() {

		try {
			Class.forName(JDBC_DRIVER);
			return DriverManager.getConnection(CONNECTION_URL, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
