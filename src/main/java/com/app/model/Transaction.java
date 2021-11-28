package com.app.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class Transaction {
	private String id;
	private String customer;
	private double purchaseAmount;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate purchaseDate;
	
	
	public Transaction(String id, String customer, double purchaseAmount, LocalDate purchaseDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.purchaseAmount = purchaseAmount;
		this.purchaseDate = purchaseDate;
	}

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
	 * @return the purchaseAmount
	 */
	public double getPurchaseAmount() {
		return purchaseAmount;
	}
	/**
	 * @param purchaseAmount the purchaseAmount to set
	 */
	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(purchaseAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(purchaseAmount) != Double.doubleToLongBits(other.purchaseAmount))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customer=" + customer + ", purchaseAmount=" + purchaseAmount
				+ ", purchaseDate=" + purchaseDate + "]";
	}
	
}
