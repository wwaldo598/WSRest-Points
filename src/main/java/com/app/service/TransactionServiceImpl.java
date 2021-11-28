package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.constant.Constants;
import com.app.constant.MessageCodes;
import com.app.enums.ErrorCodes;
import com.app.exception.CustomerNotFoundException;
import com.app.exception.ParameterInvalidException;
import com.app.exception.TransactionExistException;
import com.app.exception.TransactionNotFoundException;
import com.app.model.MessageCode;
import com.app.model.Point;
import com.app.model.PointsMonth;
import com.app.model.PointsTotal;
import com.app.model.Transaction;
import com.app.util.Util;

@Service
public class TransactionServiceImpl implements TransactionService{

	Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	private static Map<String, Transaction> transactionRepo = new HashMap<>();

	@Override
	public MessageCode createTransaction(Transaction transaction) {
		LOGGER.debug("Creating transaction");
		MessageCode messageCode = new MessageCode();

		try {
			Util util = new Util();		
			util.validateParameters(transaction);
	
			LOGGER.debug("Validating parameters - Response :" + util.getMessageCode().toString());
	
			if (util.getMessageCode().getCode() == MessageCodes.MESS_CODE_SUCCESSFULL){				
				LOGGER.debug("Validating if exist the transaction");	
				if (transactionRepo.containsKey(transaction.getId()))
					throw new TransactionExistException (ErrorCodes.ERR_TRANSACTION_ALREADY_EXIST.getDescription() + " : " + transaction.getId());
				else {
					transactionRepo.put(transaction.getId(), transaction);
					messageCode.setStatus (HttpStatus.CREATED.value());
					messageCode.setCode   (MessageCodes.MESS_CODE_SUCCESSFULL);
				    messageCode.setMessage(MessageCodes.MESS_TRANSACTION_CREATED);
				}
				
			}else 
				throw new ParameterInvalidException (util.getMessageCode().getCode(), util.getMessageCode().getMessage());
			
		}catch (TransactionExistException ex){
			throw ex;
		}catch (ParameterInvalidException ex){
			throw ex;
		}catch (Exception ex){	
			LOGGER.error(ex.getMessage(), ex);
		}
		return messageCode;
	}

	@Override
	public MessageCode updateTransaction(String id, Transaction transaction) {
		MessageCode messageCode = new MessageCode();
		LOGGER.info("Updating the transaction.");
		try {			
			LOGGER.debug("Validating if exist the transaction");			
			if (transactionRepo.containsKey(id)) {
				LOGGER.debug("Exist the transaction. Updating the transaction.");
				transactionRepo.remove(id);
				transaction.setId(id);
			    transactionRepo.put(id, transaction);
			    messageCode.setStatus (HttpStatus.OK.value());
			    messageCode.setCode   (MessageCodes.MESS_CODE_SUCCESSFULL);
			    messageCode.setMessage(MessageCodes.MESS_TRANSACTION_UPDATED);
			}else
				throw new TransactionNotFoundException (ErrorCodes.ERR_TRANSACTION_NOT_EXIST.getDescription() + " : " + id);
		}catch (TransactionNotFoundException ex) {
			throw ex;
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return messageCode;
	}

	@Override
	public MessageCode deleteTransaction(String id) {
		MessageCode messageCode = new MessageCode();
		LOGGER.info("Deleting the transaction.");
		try {
			LOGGER.debug("Validating if exist the transaction");
			if (transactionRepo.containsKey(id)) {
				transactionRepo.remove(id);
				LOGGER.debug("Exist the transaction. Deleting the transaction.");
				messageCode.setStatus(HttpStatus.OK.value());
			    messageCode.setCode(MessageCodes.MESS_CODE_SUCCESSFULL);
			    messageCode.setMessage(MessageCodes.MESS_TRANSACTION_DELETED);
			}else
				throw new TransactionNotFoundException (ErrorCodes.ERR_TRANSACTION_NOT_EXIST.getDescription() + " : " + id);
		
		}catch (TransactionNotFoundException ex) {
			throw ex;
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return messageCode;
	}

	@Override
	public Collection<Transaction> getTransactions() {
		LOGGER.info("Getting transactions");
		try {
			if (transactionRepo.isEmpty()) {
				LOGGER.debug("There're no transaction to process. The repository is empty.");
				throw new TransactionNotFoundException (ErrorCodes.ERR_NOT_TRANSACTIONS.getDescription());
			}else
				transactionRepo.values().forEach((p)->{LOGGER.debug(">" + p.toString());});		
		}catch (TransactionNotFoundException ex) {
			throw ex;
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}		
		return transactionRepo.values();
	}
	
	@Override
	public PointsMonth getPointsMonth(String customer) {	
		LOGGER.info("Getting points-month");
		List<Point> listPoints = new ArrayList<>();
		PointsMonth collPointsMonth = new PointsMonth();		
		try {
			long totalCustomer = transactionRepo.values().stream().filter((p)->p.getCustomer().equals(customer)).count();
			if (totalCustomer == 0){
				throw new CustomerNotFoundException (ErrorCodes.ERR_CUSTOMER_NOT_EXIST.getDescription() + " : " + customer);
			}else{
				transactionRepo.forEach((k,v)->{
					if (v.getCustomer().equals(customer))
						listPoints.add(new Point(Util.getMonth(v.getPurchaseDate()),
												 getEarnedPoints(v.getPurchaseAmmount())));
				});		
				
				Map<Integer, Integer> ponintsMonth =
						listPoints.stream().collect(Collectors.groupingBy(Point::getMonth, Collectors.summingInt(Point::getPoints)));
		
				collPointsMonth.setCustomer(customer);
				ponintsMonth.forEach((k,v)->collPointsMonth.addPoints(new Point(k,v)));
				
				LOGGER.debug("Points calculated " + collPointsMonth.toString());
			}
		}catch (CustomerNotFoundException ex) {
			throw ex;
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return collPointsMonth;
		}

	@Override
	public PointsTotal getPointsTotal(String customer) {
		LOGGER.info("Getting points-total");
		PointsTotal pointsTotal = new PointsTotal();
		try {
			
			long totalCustomer = transactionRepo.values().stream().filter((p)->p.getCustomer().equals(customer)).count();
			if (totalCustomer == 0){
				throw new CustomerNotFoundException (ErrorCodes.ERR_CUSTOMER_NOT_EXIST.getDescription() + " : " + customer);
			}else {
				int points = transactionRepo.values().stream()
											.filter((p)->p.getCustomer().equals(customer))
											.map((v)->getEarnedPoints(v.getPurchaseAmmount()))
											.reduce(0, Integer::sum);
				pointsTotal.setCustomer(customer);
				pointsTotal.setPoints(points);
				LOGGER.debug("Points calculated " + pointsTotal.toString()); 
			}
		}catch (CustomerNotFoundException ex) {
			throw ex;
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);		
		}
		return pointsTotal;
	}

	/**
	 * @return the total of earned points.
	 */
	private int getEarnedPoints(double purchaseAmmount){
		int earnedPoints = 0;
		if (purchaseAmmount > (Constants.C_AMMOUNT_POINTS_2 * 2)) 
			earnedPoints = (int) (Constants.C_AMMOUNT_POINTS_1 * Constants.C_EARNED_POINTS_1) +
						   		 (Constants.C_AMMOUNT_POINTS_2 * Constants.C_EARNED_POINTS_2);		
		else if (purchaseAmmount > Constants.C_AMMOUNT_POINTS_2 && purchaseAmmount <= (Constants.C_AMMOUNT_POINTS_2 * 2))
			earnedPoints = (int) ((Constants.C_AMMOUNT_POINTS_1 * Constants.C_EARNED_POINTS_1) + 
			          	   ((purchaseAmmount - Constants.C_AMMOUNT_POINTS_2 ) * Constants.C_EARNED_POINTS_2));
		else if (purchaseAmmount > Constants.C_AMMOUNT_POINTS_1 && purchaseAmmount <= Constants.C_AMMOUNT_POINTS_2)
			earnedPoints = (int) ((purchaseAmmount - Constants.C_AMMOUNT_POINTS_1 ) * Constants.C_EARNED_POINTS_1);
		return earnedPoints;
	}
	
}
