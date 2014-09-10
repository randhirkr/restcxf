package com.foundation4u.dao;

import com.foundation4u.model.Employee;

public interface EmployeeDao {

	public Employee fetchEmployee(int id);
	
	public boolean addEmployee(Employee employee);
	
}
