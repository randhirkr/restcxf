/**
 * 
 */
package com.foundation4u.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foundation4u.model.Employee;

/**
 * @author Randhir
 * 
 */
public class EmployeeServiceImplTest {

	DefaultHttpClient httpClient = null;
	String url = "http://localhost:8080/restcxf/empService/employee";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		httpClient = new DefaultHttpClient();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		httpClient.getConnectionManager().shutdown();
	}

	/**
	 * Test method for
	 * {@link com.foundation4u.service.impl.EmployeeServiceImpl#getEmployee(int)}
	 * .
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JAXBException
	 */
	@Test
	public void testGetEmployee() throws ClientProtocolException, IOException,
			JAXBException {
		HttpGet getRequest = new HttpGet(url + "/2000");

		getRequest.addHeader("accept", "application/xml");

		HttpResponse response = httpClient.execute(getRequest);

		int statusCode = response.getStatusLine().getStatusCode();
		assertEquals("fetching employee successful",200, statusCode);

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Employee employee = (Employee) jaxbUnmarshaller
				.unmarshal(new StringReader(apiOutput));

		System.out.println(employee.getEmpId());
		System.out.println(employee.getEmpName());
	}

	/**
	 * Test method for
	 * {@link com.foundation4u.service.impl.EmployeeServiceImpl#addEmployee(com.foundation4u.model.Employee)}
	 * .
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	public void testAddEmployee() throws JAXBException,
			ClientProtocolException, IOException {

		Employee emp = new Employee();
		emp.setEmpId(2000);
		emp.setEmpName("Tom");

		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(emp, writer);

		HttpPost postRequest = new HttpPost(url);

		postRequest.addHeader("content-type", "application/xml");

		StringEntity userEntity = new StringEntity(writer.getBuffer()
				.toString());
		postRequest.setEntity(userEntity);

		HttpResponse response = httpClient.execute(postRequest);

		int statusCode = response.getStatusLine().getStatusCode();
		
		assertEquals("employee got added",200, statusCode);
	}

}
