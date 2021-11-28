package com.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.enums.ErrorCodes;
import com.app.model.MessageCode;
import com.app.model.Transaction;
import com.app.util.Util;


public class TransactionServiceControllerTest  extends AbstractTest {

	private static List<Transaction> transactionList = new ArrayList<Transaction>();
		
	@Autowired
	private MockMvc mvc;
	
	@BeforeAll
	static public void setUp() {
		transactionList.add(new Transaction("11", "Customer-A", 40,  Util.getLocalDateFromString("19-09-2021")));
		transactionList.add(new Transaction("12", "Customer-A", 75,  Util.getLocalDateFromString("20-10-2021")));
		transactionList.add(new Transaction("13", "Customer-A", 25,  Util.getLocalDateFromString("20-10-2021")));
		
		transactionList.add(new Transaction("15", "Customer-B", 130, Util.getLocalDateFromString("07-09-2021")));
		transactionList.add(new Transaction("16", "Customer-B", 113, Util.getLocalDateFromString("11-09-2021")));
		transactionList.add(new Transaction("17", "Customer-B", 28,  Util.getLocalDateFromString("12-10-2021")));	

		transactionList.add(new Transaction("18", "Customer-C", 50,  Util.getLocalDateFromString("20-08-2021")));
		transactionList.add(new Transaction("19", "Customer-C", 137, Util.getLocalDateFromString("27-10-2021")));
		transactionList.add(new Transaction("20", "Customer-C", 141, Util.getLocalDateFromString("19-11-2021")));
		transactionList.add(new Transaction("21", "Customer-C", 212, Util.getLocalDateFromString("27-11-2021")));
	}
	
	
	@Test
	@Order(value = 1)
	public void createTransactionCaseOk() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setId("1");
	   transaction.setCustomer("Customer-Z");	
	   transaction.setPorchaseDate(Util.getLocalDateFromString("12-12-2021"));
	   transaction.setPurchaseAmmount(120);
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.post(uri)
			   					.contentType(MediaType.APPLICATION_JSON_VALUE)
			   					.content(inputJson))
			   					.andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status); 
	}
	
	@Test
	@Order(value = 2)
	public void createTransactionCaseValidateIdParameter() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setCustomer("Customer-Z");	
	   transaction.setPorchaseDate(Util.getLocalDateFromString("12-12-2021"));
	   transaction.setPurchaseAmmount(10);
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.post(uri)
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(inputJson))
								.andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	   
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
	   assertEquals(messageCode.getCode(),ErrorCodes.ERR_PARAM_TRANSACTION_REQUIRED.getCode());
	   
	}
	
	@Test
	@Order(value = 3)
	public void createTransactionCaseValidateCustomerParameter() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setId("1");
	   transaction.setPorchaseDate(Util.getLocalDateFromString("12-12-2021"));
	   transaction.setPurchaseAmmount(10);
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.post(uri)
			   					.contentType(MediaType.APPLICATION_JSON_VALUE)
			   					.content(inputJson))
			   					.andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	   
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
	   assertEquals(messageCode.getCode(),ErrorCodes.ERR_PARAM_CUSTOMER_REQUIRED.getCode());	   
	}
	
	@Test
	@Order(value = 4)
	public void createTransactionCaseValidatePurchaseDateParameter() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setId("1");
	   transaction.setCustomer("Customer-Z");
	   transaction.setPurchaseAmmount(10);
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.post(uri)
			   					.contentType(MediaType.APPLICATION_JSON_VALUE)
			   					.content(inputJson))
			   					.andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	   
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
	   assertEquals(messageCode.getCode(),ErrorCodes.ERR_PARAM_PURCHASE_DATE_REQUIRED.getCode());
	}
	
	@Test
	@Order(value = 5)
	public void createTransactionCaseValidatePurchaseAmmountParameter() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setId("1");
	   transaction.setCustomer("Customer-Y");	
	   transaction.setPorchaseDate(Util.getLocalDateFromString("12-12-2021"));
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.post(uri)
			   					.contentType(MediaType.APPLICATION_JSON_VALUE)
			   					.content(inputJson))
			   					.andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	   
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
	   assertEquals(messageCode.getCode(),ErrorCodes.ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED.getCode());
	}
	
	@Test
	@Order(value = 6)
	public void createTransactionCaseIdExist() throws Exception {
	   String uri = "/pointsAppWS/transaction";
	   Transaction transaction = new Transaction();
	   transaction.setId("1");
	   transaction.setCustomer("Customer-Z");	
	   transaction.setPorchaseDate(Util.getLocalDateFromString("12-12-2021"));
	   transaction.setPurchaseAmmount(120);
	   
	   String inputJson = super.mapToJson(transaction);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.post(uri)
			   					.contentType(MediaType.APPLICATION_JSON_VALUE)
			   					.content(inputJson))
			   					.andReturn();	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	}
	
	
	@Test
	@Order(value = 7)
	public void getTransactionListCaseOk() throws Exception {
	   String uri = "/pointsAppWS/transactions";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
			   					.get(uri)
			   					.accept(MediaType.APPLICATION_JSON_VALUE))
			   					.andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   
	   String content = mvcResult.getResponse().getContentAsString();
	   Transaction [] transactiontList = super.mapFromJson(content, Transaction [].class);
	   assertTrue(transactiontList.length > 0);
	}
	
	 @Test
	 @Order(value = 8)
	 public void updateProductCaseOk() throws Exception {
	      String uri = "/pointsAppWS/transaction/1";
	      Transaction transaction = new Transaction();
		  transaction.setCustomer("Client-A");
		  transaction.setPorchaseDate(Util.getLocalDateFromString("12-12-2021"));
		  transaction.setPurchaseAmmount(150);
		   
	      String inputJson = super.mapToJson(transaction);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
	    		  				   .put(uri)
	    		  				   .contentType(MediaType.APPLICATION_JSON_VALUE)
	    		  				   .content(inputJson))
	    		  				   .andReturn();	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	   }	 
	   
	  @Test
	  @Order(value = 9)
	  public void deleteTransactionCaseNoExistId() throws Exception {
	      String uri = "/pointsAppWS/transaction/";
	      String idTransaction = "32";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + idTransaction)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);	   
	      
	      String content = mvcResult.getResponse().getContentAsString();
		  MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
		  assertEquals(messageCode.getCode(),ErrorCodes.ERR_TRANSACTION_NOT_EXIST.getCode());
		  
	   }
	  
	  @Test
	  @Order(value = 10)
	  public void deleteTransactionCaseExistId() throws Exception {
	      String uri = "/pointsAppWS/transaction/";
	      String idTransaction = "1";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + idTransaction)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);	     
	   }
	  
	    @Test
		@Order(value = 11)
		public void createTransactionCaseBatch() throws Exception {
		   String uri = "/pointsAppWS/transaction";   
		   for(Transaction item:transactionList){
			   String inputJson = super.mapToJson(item);
			   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
					   					.post(uri)
					   					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
					   					.andReturn();		   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(201, status);
		   }
		}		
		
	    @Test
		@Order(value = 12)
		public void getPointsMonthCustomerCaseOk() throws Exception {
		   String uri = "/pointsAppWS/points/month/";
		   String customer = "Customer-C";		   
		   
		   createTransactionCaseBatch();
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + customer)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		}
	    
	    @Test
		@Order(value = 13)
		public void getPointsMonthCustomerCaseNotExist() throws Exception {
		   String uri = "/pointsAppWS/points/month/";
		   String customer = "Customer-AC";
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + customer)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(400, status);		
		   
		   String content = mvcResult.getResponse().getContentAsString();
		   MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
		   assertEquals(messageCode.getCode(),ErrorCodes.ERR_CUSTOMER_NOT_EXIST.getCode());
		}
	    
	    @Test
		@Order(value = 14)
		public void getPointsTotalCustomerCaseOk() throws Exception {
		   String uri = "/pointsAppWS/points/total/";
		   String customer = "Customer-B";
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + customer)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);			   
		}
	    
	    @Test
		@Order(value = 15)
		public void getPointsTotalCustomerCaseNoExist() throws Exception {
		   String uri = "/pointsAppWS/points/total/";
		   String customer = "Customer-DB";
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + customer)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(400, status);		
		   
		   String content = mvcResult.getResponse().getContentAsString();
		   MessageCode messageCode = super.mapFromJson(content, MessageCode.class);
		   assertEquals(messageCode.getCode(),ErrorCodes.ERR_CUSTOMER_NOT_EXIST.getCode());
		}
}
