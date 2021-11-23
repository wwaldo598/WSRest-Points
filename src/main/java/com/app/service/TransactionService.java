package com.app.service;

import java.util.Collection;

import com.app.model.MessageCode;
import com.app.model.PointsMonth;
import com.app.model.PointsTotal;
import com.app.model.Transaction;

public interface TransactionService {
	public abstract MessageCode createTransaction(Transaction transaction);
	public abstract MessageCode updateTransaction(String id, Transaction transaction);
	public abstract MessageCode deleteTransaction(String id);
	public abstract Collection<Transaction> getTransactions();
	
	public abstract PointsMonth getPointsMonth(String customer);
	public abstract PointsTotal getPointsTotal(String customer);
}
