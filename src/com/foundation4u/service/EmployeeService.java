package com.foundation4u.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.foundation4u.model.Employee;

@Path("/")
public interface EmployeeService {

	@GET
	@Path("employee/{empId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Employee getEmployee(@PathParam("empId") int empId);
	
	@POST
	@Path("employee/{empId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Employee getEmployeeById(@PathParam("empId") int empId);
	
	public boolean addEmployee(Employee emp);
	
}
