package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.constant.Constants;
import com.app.model.Point;
import com.app.model.PointsMonth;
import com.app.model.PointsTotal;
import com.app.model.Transaction;
import com.app.tools.Tools;

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
	public PointsMonth getPointsMonth(String customer) {		
		List<Point> listPoints = new ArrayList<>();
		transactionRepo.forEach((k,v)->{
			if (v.getCustomer().equals(customer))
				listPoints.add(new Point(Tools.getMonth(v.getPurchaseDate()),
										 getEarnedPoints(v.getPurchaseAmmount())));
		});		
		
		Map<Integer, Integer> ponintsMonth =
				listPoints.stream().collect(Collectors.groupingBy(Point::getMonth, Collectors.summingInt(Point::getPoints)));

		PointsMonth collPointsMonth = new PointsMonth();
		collPointsMonth.setCustomer(customer);
		ponintsMonth.forEach((k,v)->collPointsMonth.addPoints(new Point(k,v)));
		
		return collPointsMonth;
		}

	@Override
	public PointsTotal getPointsTotal(String customer) {
		PointsTotal pointsTotal = new PointsTotal();
		int points = transactionRepo.values().stream()
									.filter((p)->p.getCustomer().equals(customer))
									.map((v)->getEarnedPoints(v.getPurchaseAmmount()))
									.reduce(0, Integer::sum);
		pointsTotal.setCustomer(customer);
		pointsTotal.setPoints(points);
		return pointsTotal;
	}

	/**
	 * @return the total of earned points.
	 */
	private int getEarnedPoints(double purchaseAmmount){
		int earnedPoints = 0;
		if (purchaseAmmount > Constants.C_AMMOUNT_POINTS_2 && purchaseAmmount <= (Constants.C_AMMOUNT_POINTS_2 * 2))
			earnedPoints = (int) ((Constants.C_AMMOUNT_POINTS_1 * Constants.C_EARNED_POINTS_1) + 
			          	   ((purchaseAmmount - Constants.C_AMMOUNT_POINTS_2 ) * Constants.C_EARNED_POINTS_2));
		else if (purchaseAmmount > Constants.C_AMMOUNT_POINTS_1 && purchaseAmmount <= Constants.C_AMMOUNT_POINTS_1)
			earnedPoints = (int) ((purchaseAmmount - Constants.C_AMMOUNT_POINTS_1 ) * Constants.C_EARNED_POINTS_1);
		return earnedPoints;
	}
	
}
