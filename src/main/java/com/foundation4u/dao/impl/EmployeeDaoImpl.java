package com.foundation4u.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foundation4u.dao.EmployeeDao;
import com.foundation4u.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

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
