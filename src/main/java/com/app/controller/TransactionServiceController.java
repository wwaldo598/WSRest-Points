package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	   
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> 
	      updateTransaction(@PathVariable("id") String id, @RequestBody Transaction transaction) {	      
		   transactionService.updateTransaction(id, transaction); 
	      return new ResponseEntity<>("Transaction is updated successsfully", HttpStatus.OK);
	   }
	   
	   @RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		   transactionService.deleteTransaction(id);
	      return new ResponseEntity<>("Transaction is deleted successsfully", HttpStatus.OK);
	   }
	   
	   @RequestMapping(value = "/transaction", method = RequestMethod.POST)
	   public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
		   transactionService.createTransaction(transaction);
	      return new ResponseEntity<>("Transaction is created successfully", HttpStatus.CREATED);
	   }
	   
}
