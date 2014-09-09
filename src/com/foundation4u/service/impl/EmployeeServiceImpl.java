package com.foundation4u.service.impl;

import com.foundation4u.dao.EmployeeDao;
import com.foundation4u.model.Employee;
import com.foundation4u.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao empDao;

	@Override
	public Employee getEmployee(int empId) {
		// dummy emp record
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("Tom");

		return emp;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		// dummy emp record
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("Victor");
		
		return emp;
	}

}
