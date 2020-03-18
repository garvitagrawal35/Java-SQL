package com.employee;

import javax.sql.DataSource;

import com.employee.connections.SQLConnections;
import com.employees.impl.MySQLTemplate;
import com.pojo.Employee;

public class App {

	public static void main(String[] args) {
		SQLConnections sqlConnections = new SQLConnections();
		DataSource dataSource = sqlConnections.getDataSource();
		MySQLTemplate mySQLTemplate = new MySQLTemplate(dataSource);
		boolean isCreated = mySQLTemplate.createTableIfNotExist("employee", Employee.class);
		System.out.println(isCreated ? "Successfully Created table." : "Not able to create table this time.");
		boolean isInserted = mySQLTemplate.insert(new Employee("Aviral", "27", "USA"), "employee", Employee.class);
		System.out.println(isInserted ? "Successfully inserted into the table." : "Not able to insert into the table.");
	}
}
