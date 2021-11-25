package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.constant.Constants;
import com.app.constant.MessageCodes;
import com.app.controller.TransactionServiceController;
import com.app.enums.ErrorCodes;
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
				if (transactionRepo.containsKey(transaction.getId())) {
					LOGGER.debug("Exist the transaction. Doesn't create the transaction.");
				    messageCode.setCode(ErrorCodes.ERR_PARAM_TRANSACTION_ALREADY_EXIST.getCode());
				    messageCode.setDescription(ErrorCodes.ERR_PARAM_TRANSACTION_ALREADY_EXIST.getDescription());
				}else {
					LOGGER.debug("Doesn't exist the transaction. Creating the transaction.");
					transactionRepo.put(transaction.getId(), transaction);
				    messageCode.setCode(MessageCodes.MESS_CODE_SUCCESSFULL);
				    messageCode.setDescription(MessageCodes.MESS_TRANSACTION_CREATED);
				}
				
			}else {
			    messageCode.setCode(util.getMessageCode().getCode());
			    messageCode.setDescription(util.getMessageCode().getDescription());
			}
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		    messageCode.setCode(ErrorCodes.ERR_SERVER_INTERNAL.getCode());
		    messageCode.setDescription(ErrorCodes.ERR_SERVER_INTERNAL.getDescription());
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
			    messageCode.setCode(MessageCodes.MESS_CODE_SUCCESSFULL);
			    messageCode.setDescription(MessageCodes.MESS_TRANSACTION_UPDATED);
			}else{
				LOGGER.debug("It doesn't exist the transaction");
			    messageCode.setCode(ErrorCodes.ERR_PARAM_TRANSACTION_NOT_EXIST.getCode());
			    messageCode.setDescription(ErrorCodes.ERR_PARAM_TRANSACTION_NOT_EXIST.getDescription());
			}			
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		    messageCode.setCode(ErrorCodes.ERR_SERVER_INTERNAL.getCode());
		    messageCode.setDescription(ErrorCodes.ERR_SERVER_INTERNAL.getDescription());
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
			    messageCode.setCode(MessageCodes.MESS_CODE_SUCCESSFULL);
			    messageCode.setDescription(MessageCodes.MESS_TRANSACTION_DELETED);
			}else{
				LOGGER.debug("It doesn't exist the transaction");
			    messageCode.setCode(ErrorCodes.ERR_PARAM_TRANSACTION_NOT_EXIST.getCode());
			    messageCode.setDescription(ErrorCodes.ERR_PARAM_TRANSACTION_NOT_EXIST.getDescription());
			}
		}catch(Exception ex) {
			LOGGER.error(ex.getMessage());
		    messageCode.setCode(ErrorCodes.ERR_SERVER_INTERNAL.getCode());
		    messageCode.setDescription(ErrorCodes.ERR_SERVER_INTERNAL.getDescription());
		}
		return messageCode;
	}

	@Override
	public Collection<Transaction> getTransactions() {
		LOGGER.info("Getting transactions presents");
		transactionRepo.values().forEach((p)->{LOGGER.debug(">" + p.toString());});
		return transactionRepo.values();
	}
	
	@Override
	public PointsMonth getPointsMonth(String customer) {	
		LOGGER.info("Getting points-month");
		List<Point> listPoints = new ArrayList<>();
		transactionRepo.forEach((k,v)->{
			if (v.getCustomer().equals(customer))
				listPoints.add(new Point(Util.getMonth(v.getPurchaseDate()),
										 getEarnedPoints(v.getPurchaseAmmount())));
		});		
		
		Map<Integer, Integer> ponintsMonth =
				listPoints.stream().collect(Collectors.groupingBy(Point::getMonth, Collectors.summingInt(Point::getPoints)));

		PointsMonth collPointsMonth = new PointsMonth();
		collPointsMonth.setCustomer(customer);
		ponintsMonth.forEach((k,v)->collPointsMonth.addPoints(new Point(k,v)));
		
		LOGGER.debug("Points calculated " + collPointsMonth.toString());
		
		return collPointsMonth;
		}

	@Override
	public PointsTotal getPointsTotal(String customer) {
		LOGGER.info("Getting points-total");
		PointsTotal pointsTotal = new PointsTotal();
		int points = transactionRepo.values().stream()
									.filter((p)->p.getCustomer().equals(customer))
									.map((v)->getEarnedPoints(v.getPurchaseAmmount()))
									.reduce(0, Integer::sum);
		pointsTotal.setCustomer(customer);
		pointsTotal.setPoints(points);
		LOGGER.debug("Points calculated " + pointsTotal.toString()); 
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
