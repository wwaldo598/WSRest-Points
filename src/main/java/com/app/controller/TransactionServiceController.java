package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.constant.MessageCodes;
import com.app.model.MessageCode;
import com.app.model.Transaction;
import com.app.service.TransactionService;


@RestController
public class TransactionServiceController {
		
	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/transactions")
	public ResponseEntity<Object> getTransactions() {
	      return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Object> 
	      updateTransaction(@PathVariable("id") String id, @RequestBody Transaction transaction) {	      
		  MessageCode messgCode = transactionService.updateTransaction(id, transaction); 
	      return new ResponseEntity<>(messgCode, HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		   MessageCode messgCode = transactionService.deleteTransaction(id);		   ;
	       return new ResponseEntity<>(messgCode, HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/transaction", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
		   MessageCode messgCode = transactionService.createTransaction(transaction);
		   return new ResponseEntity<>(messgCode, HttpStatus.CREATED);
	}
	   
	@RequestMapping(value = "/points/month/{customer}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPointsMonth(@PathVariable("customer") String customer) {		   
		   return new ResponseEntity<>(transactionService.getPointsMonth(customer), HttpStatus.OK);
	}
	   
	@RequestMapping(value = "/points/total/{customer}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPointsTotal(@PathVariable("customer") String customer) {		   
		   return new ResponseEntity<>(transactionService.getPointsTotal(customer), HttpStatus.OK);
	}
	
}
