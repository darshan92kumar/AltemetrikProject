package com.example.altemetrik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AltemetrikApplicationTests {
	
	// Authorized user
	public String body = "{\n" + 
			"    \"username\": \"darshan\",\n" + 
			"    \"password\": \"password\"\n" + 
			"}";
	// Unauthorized user
	public String badBody = "{\n" + 
			"    \"username\": \"unknown\",\n" + 
			"    \"password\": \"password\"\n" + 
			"}";
	
	@Autowired
    private MockMvc mvc;

    @Test
    public void shouldGenerateAuthToken() throws Exception {
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/authenticate")
				.accept(MediaType.APPLICATION_JSON).content(body)
				.contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());

    }
    
    @Test
    public void shouldNotGenerateAuthToken() throws Exception {
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/authenticate")
				.accept(MediaType.APPLICATION_JSON).content(badBody)
				.contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());

    }
    
    @Test
    public void invokeRestrictedAPI() throws Exception {
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/authenticate")
				.accept(MediaType.APPLICATION_JSON).content(body)
				.contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		
		String token = result.getResponse().getContentAsString().replace("{\"token\":\"", "").replace("\"}", "");
    	
		requestBuilder = MockMvcRequestBuilders
				.get("/hello")
				.header("Authorization", "Bearer " + token);
		
		result = mvc.perform(requestBuilder).andReturn();

		response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		assertTrue(response.getContentAsString().contains("Authenticated-User"));
		assertTrue(response.getContentAsString().contains("Welcome!"));
		
		
    	
    }
}
