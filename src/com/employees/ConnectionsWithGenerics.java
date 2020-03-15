package com.employees;

import java.util.List;

public interface ConnectionsWithGenerics {

	public <T> boolean createRecord(T t, Class<T> clazz);

	public <T> List<T> getRecord(T t, Class<T> clazz);

	public <T> boolean updateEmployee();
}
