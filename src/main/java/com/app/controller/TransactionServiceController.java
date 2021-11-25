package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.MessageCode;
import com.app.model.Transaction;
import com.app.service.TransactionService;


@RestController
@RequestMapping("/pointsAppWS")
public class TransactionServiceController {
	
	Logger LOGGER = LoggerFactory.getLogger(TransactionServiceController.class);
	
	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/transactions")
	public ResponseEntity<Object> getTransactions() {
		LOGGER.info ("Serving for GET - Method /transactions");
	    return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Object> 
	      updateTransaction(@PathVariable("id") String id, @RequestBody Transaction transaction) {
		  LOGGER.info  ("Serving for PUT - Method /transaction/{id}");
		  LOGGER.debug ("Transaction Id...:" + id);
		  LOGGER.debug ("Transaction body :" + transaction.toString());
		  
		  MessageCode messgCode = transactionService.updateTransaction(id, transaction); 
		  
		  LOGGER.debug ("Message-response :" + messgCode.toString());		  
	      return new ResponseEntity<>(messgCode, HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		   LOGGER.info  ("Serving for DELETE - Method /transaction/{id}");
		   LOGGER.debug ("Transaction Id...:" + id);

		   MessageCode messgCode = transactionService.deleteTransaction(id);

		   LOGGER.debug ("Message-response :" + messgCode.toString());	
	       return new ResponseEntity<>(messgCode, HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/transaction", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
		   LOGGER.info  ("Serving for POST - Method /transaction");
		   LOGGER.debug ("Transaction body...:" + transaction.toString());
		   
		   MessageCode messgCode = transactionService.createTransaction(transaction);
		   
		   LOGGER.debug ("Message-response :" + messgCode.toString());		   
		   return new ResponseEntity<>(messgCode, HttpStatus.CREATED);
	}
	   
	@RequestMapping(value = "/points/month/{customer}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPointsMonth(@PathVariable("customer") String customer) {	
		   LOGGER.info  ("Serving for GET - Method /points/month/{customer}");
		   LOGGER.debug ("Customer :" + customer);
		   return new ResponseEntity<>(transactionService.getPointsMonth(customer), HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/points/total/{customer}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPointsTotal(@PathVariable("customer") String customer) {
		   LOGGER.info  ("Serving for GET - Method /points/total/{customer}");
		   LOGGER.debug ("Customer :" + customer);
		   return new ResponseEntity<>(transactionService.getPointsTotal(customer), HttpStatus.OK);
	}
	
}
