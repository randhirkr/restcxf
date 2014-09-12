package com.foundation4u.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class EmployeeServiceExceptionHandler implements ExceptionMapper<EmployeeServiceException>{

	@Override
	public Response toResponse(EmployeeServiceException exception) {
		return  Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}
