package com.employee.constant;

public class EmployeeConstants {

	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/garvit";
	public static final String EMPLOOYEE_INSERT = "insert into employee values('ABC','25','US');";
	public static final String EMPLOOYEE_INSERT_GENERICS = "insert into employee values(?,?,?);";
	public static final String GET_ALL_EMPLOYEES = "select * from employee";
}
