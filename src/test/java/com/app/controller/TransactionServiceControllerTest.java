package com.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.model.Transaction;


public class TransactionServiceControllerTest  extends AbstractTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void createTransaction() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setId("1");
	   transaction.setCustomer("ClientA");
	   
	   String dateTimePattern = "dd-MM-yyyy";
	   DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
	   LocalDate localDate = LocalDate.parse("12-12-2021", dateFormatter);
		
	   transaction.setPorchaseDate(localDate);
	   transaction.setPurchaseAmmount(120);
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);

	}
	
	@Test
	public void getTransactionList() throws Exception {
	   String uri = "/pointsAppWS/transactions";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   
	   String content = mvcResult.getResponse().getContentAsString();
	   Transaction [] transactiontList = super.mapFromJson(content, Transaction [].class);
	   assertTrue(transactiontList.length > 0);
	}
	
	 @Test
	 public void updateProduct() throws Exception {
	      String uri = "/pointsAppWS/transaction/1";
	      Transaction transaction = new Transaction();
	      transaction.setId("1");
		  transaction.setCustomer("ClientA");
		   
		  String dateTimePattern = "dd-MM-yyyy";
		  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
		  LocalDate localDate = LocalDate.parse("12-12-2021", dateFormatter);
			
		  transaction.setPorchaseDate(localDate);
		  transaction.setPurchaseAmmount(150);
		   
	      String inputJson = super.mapToJson(transaction);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);

	   }
	 
	  @Test
	  public void removeTransaction() throws Exception {
	      String uri = "/pointsAppWS/transaction/";
	      String idTransaction = "2";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + idTransaction)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(404, status);	     
	   }
	  
	  
	    @Test
		public void getPointsMonthCustomer() throws Exception {
		   String uri = "/pointsAppWS/points/month/";
		   String customer = "ClientA";
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + customer)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);

		}
	    
	    @Test
		public void getPointsTotalCustomer() throws Exception {
		   String uri = "/pointsAppWS/points/total/";
		   String customer = "ClientA";
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + customer)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
			   
		}
}
