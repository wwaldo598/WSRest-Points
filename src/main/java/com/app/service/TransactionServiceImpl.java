package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.model.Point;
import com.app.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{

	private static Map<String, Transaction> transactionRepo = new HashMap<>();
	
	@Override
	public void createTransaction(Transaction transaction) {
		transactionRepo.put(transaction.getId(), transaction);
	}

	@Override
	public void updateTransaction(String id, Transaction transaction) {
		transactionRepo.remove(id);
		transaction.setId(id);
	    transactionRepo.put(id, transaction);		
	}

	@Override
	public void deleteTransaction(String id) {
		transactionRepo.remove(id);
	}

	@Override
	public Collection<Transaction> getTransactions() {
		return transactionRepo.values();
	}
	
	@Override
	public Map<Integer, Long> getRewardedPointMonth(String customer) {		
		List<Point> listPoints = new ArrayList<>();
		transactionRepo.forEach((k,v)->{
			if (v.getCustomer().equals(customer))
				listPoints.add(new Point(v.getMonth(),
										 v.getEarnedPoints()));});		
		 Map<Integer, Long> ponintsByMonth =
				listPoints.stream().collect(Collectors.groupingBy(Point::getMonth, Collectors.summingLong(Point::getPoints)));
		 return ponintsByMonth;
		}

	@Override
	public int getRewardedPointTotal(String customer) {
		return 0;
	}

	
}
