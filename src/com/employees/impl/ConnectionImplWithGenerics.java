package com.employees.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.employee.constant.EmployeeConstants;
import com.employees.ConnectionsWithGenerics;
import com.pojo.Employee;

public class ConnectionImplWithGenerics implements ConnectionsWithGenerics {

	Connection con;

	public ConnectionImplWithGenerics(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public <T> boolean createRecord(T t, Class<T> clazz) {

		try {
			Map<String, Object> map = getFieldValues(t);
			for(Entry<String, Object> entry: map.entrySet()) {
				
			}
			PreparedStatement ps = con.prepareStatement(EmployeeConstants.EMPLOOYEE_INSERT_GENERICS);
			// ps.setString(1, t.);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T> List<T> getRecord(T t, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean updateEmployee() {
		// TODO Auto-generated method stub
		return false;
	}

	private <T> Map<String, Object> getFieldValues(T t) {

		Map<String, Object> map = new HashMap<>();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(t));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static void main(String[] args) {
		ConnectionImplWithGenerics ciwg = new ConnectionImplWithGenerics(null);
		Employee e = new Employee("Aviral", "27", "Hammond");
		ciwg.createRecord(e, Employee.class);
	}

}
