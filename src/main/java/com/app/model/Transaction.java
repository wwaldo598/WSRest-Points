package com.app.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class Transaction {
	private String id;
	private String customer;
	private double purchaseAmmount;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate purchaseDate;
	
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
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param porchaseDate the porchaseDate to set
	 */
	public void setPorchaseDate(LocalDate porchaseDate) {
		this.purchaseDate = porchaseDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customer=" + customer + ", purchaseAmmount=" + purchaseAmmount
				+ ", purchaseDate=" + purchaseDate + "]";
	}
	
}
