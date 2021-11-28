package com.app.controller;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.PointWsApplication;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PointWsApplication.class)
@AutoConfigureMockMvc
public abstract class AbstractTest {
	   
	   protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
	              .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	      return objectMapper.writeValueAsString(obj);
	   }
	   
	   protected <T> T mapFromJson(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      
	      return objectMapper.readValue(json, clazz);
	   }
}
