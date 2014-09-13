/**
 * 
 */
package com.foundation4u.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foundation4u.model.Employee;

/**
 * @author Randhir
 * 
 */
public class EmployeeServiceImplTest {

	private final static Logger log = LoggerFactory.getLogger(EmployeeServiceImplTest.class);
	DefaultHttpClient httpClient;
	Properties prop;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		prop = new Properties();
		loadPropertiesFile();
		httpClient = new DefaultHttpClient();
	}

	private void loadPropertiesFile() {
		InputStream input = null;
		try{
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			if (input == null) {
				log.error("error in loading properties file");
				return;
			}
			prop.load(input);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
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
		String url = prop.getProperty("url");
		HttpGet getRequest = new HttpGet(url + "/3000");
		log.info("url: "+url);
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
		emp.setEmpId(3000);
		emp.setEmpName("Victor");

		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(emp, writer);
		
		String url = prop.getProperty("url");
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
