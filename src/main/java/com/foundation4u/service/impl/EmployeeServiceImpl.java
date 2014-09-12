package com.foundation4u.service.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.foundation4u.dao.EmployeeDao;
import com.foundation4u.exception.EmployeeServiceException;
import com.foundation4u.model.Employee;
import com.foundation4u.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao empDao;

	@Override
	public Response getEmployee(int empId) throws EmployeeServiceException {
		Employee emp = empDao.fetchEmployee(empId);
		if(emp == null){
			//return Response.ok("Employee Id: "+empId+" does not exist").build();
			//return Response.status(Status.NOT_FOUND).build();
			throw new EmployeeServiceException("Employee Id: "+empId+" does not exist");
		}
		return Response.ok(emp).build();
		
	}

	@Override
	public Response addEmployee(Employee emp) {
		boolean status = empDao.addEmployee(emp);
		if(status){
			return Response.ok("employee added successfully").build();
		}else{
			return Response.ok("error in adding employee").build();
		}
	}

	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}


}
