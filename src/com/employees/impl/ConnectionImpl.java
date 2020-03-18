package com.employees.impl;

import static com.employee.constant.EmployeeConstants.EMPLOOYEE_INSERT;
import static com.employee.constant.EmployeeConstants.GET_ALL_EMPLOYEES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employees.Connections;
import com.pojo.Employee;

public class ConnectionImpl implements Connections {

	private Connection con;

	public ConnectionImpl(Connection con) {

		if (con == null) {
			System.out.println("Existing code as unable to make connections with SQL.");
			System.exit(0);
		} else {
			this.con = con;
		}
	}

	@Override
	public void createRecord() {

		try {
			PreparedStatement ps = con.prepareStatement(EMPLOOYEE_INSERT);
			// Statement stmt = con.createStatement();
			boolean bool = ps.execute();
			// ResultSet rs = stmt.executeQuery("insert into employee
			// values('ABC','25','US');");
			if (bool) {
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getRecord() {

		List<Employee> list = new ArrayList<Employee>();
		try {
			PreparedStatement ps = con.prepareStatement(GET_ALL_EMPLOYEES);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee(rs.getString(1), rs.getString(2), rs.getString(3));
				System.out.println(emp);
				list.add(emp);
			}
			System.out.println("My List Size is: " + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateEmployee() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTable() {

		
	}
}
