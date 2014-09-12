package com.foundation4u.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.foundation4u.dao.EmployeeDao;
import com.foundation4u.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{

	// temporary storage for emp data
	List<Employee> empList = new ArrayList<>();
	
	@Override
	public Employee fetchEmployee(int id) {
		for(Employee emp : empList){
			if(id == emp.getEmpId()){
				return emp;
			}
		}
		return null;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		empList.add(employee);
		return true;
	}

}
