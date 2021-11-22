package com.app.model;

import com.app.constant.Constants;

public class Transaction {
	private String id;
	private String customer;
	private double purchaseAmmount;
	private String purchaseDate;
	
	public Transaction() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the purchaseAmmount
	 */
	public double getPurchaseAmmount() {
		return purchaseAmmount;
	}
	/**
	 * @param purchaseAmmount the purchaseAmmount to set
	 */
	public void setPurchaseAmmount(double purchaseAmmount) {
		this.purchaseAmmount = purchaseAmmount;
	}
	/**
	 * @return the porchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param porchaseDate the porchaseDate to set
	 */
	public void setPorchaseDate(String porchaseDate) {
		this.purchaseDate = porchaseDate;
	}
	
	/**
	 * @return the month of purchase-date
	 */
	public int getMonth(){
		return 0;
	}
	
	/**
	 * @return the total of earned points.
	 */
	public int getEarnedPoints(){
		int earnedPoints = 0;
		if (this.purchaseAmmount > 0){
			int point = (int) (this.purchaseAmmount % Constants.C_AMMOUNT_POINTS_1);
			earnedPoints = point * Constants.C_EARNED_POINTS_1;
			
			point = (int) (this.purchaseAmmount % Constants.C_AMMOUNT_POINTS_2);
			earnedPoints = earnedPoints + point * Constants.C_EARNED_POINTS_2;			
		}
		return earnedPoints;
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customer=" + customer + ", purchaseAmmount=" + purchaseAmmount
				+ ", porchaseDate=" + purchaseDate + ", getId()=" + getId() + ", getCustomer()=" + getCustomer()
				+ ", getPurchaseAmmount()=" + getPurchaseAmmount() + ", getPurchaseDate()=" + getPurchaseDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
