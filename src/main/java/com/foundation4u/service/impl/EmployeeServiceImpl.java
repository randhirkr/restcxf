package com.foundation4u.service.impl;

import java.util.Properties;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foundation4u.dao.EmployeeDao;
import com.foundation4u.exception.EmployeeServiceException;
import com.foundation4u.model.Employee;
import com.foundation4u.service.EmployeeService;
import com.foundation4u.util.EmployeeServiceHelper;

public class EmployeeServiceImpl implements EmployeeService {

	private final static Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private EmployeeDao empDao;

	@Override
	public Response getEmployee(HttpHeaders header, int empId) throws EmployeeServiceException {
		log.info("fetching employee--------");
		log.info("httpheaders: "+header.getRequestHeaders());
		
		Employee emp = empDao.fetchEmployee(empId);
		if(emp == null){
			//return Response.ok("Employee Id: "+empId+" does not exist").build();
			//return Response.status(Status.NOT_FOUND).build();
			throw new EmployeeServiceException("Employee Id: "+empId+" does not exist");
		}
		return Response.ok(emp).build();
		
	}

	@Override
	public Response addEmployee(HttpHeaders header, Employee emp) {
		log.info("adding employee----------------");
		log.info("httpheaders: "+header.getRequestHeaders());
		
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
