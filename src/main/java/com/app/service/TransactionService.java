package com.app.service;

import java.util.Collection;
import java.util.Map;

import com.app.model.Transaction;

public interface TransactionService {
	public abstract void createTransaction(Transaction transaction);
	public abstract void updateTransaction(String id, Transaction transaction);
	public abstract void deleteTransaction(String id);
	public abstract Collection<Transaction> getTransactions();
	
	public abstract Map<Integer, Long> getRewardedPointMonth(String customer);
	public abstract int getRewardedPointTotal(String customer);
}
