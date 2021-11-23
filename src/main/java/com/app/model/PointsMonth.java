package com.app.model;

import java.util.ArrayList;
import java.util.List;

public class PointsMonth {
	
	private String customer;
	private List<Point> points = new ArrayList<>();

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
	 * @return the points
	 */
	public List<Point> getPoints() {
		return points;
	}
	
	/**
	 * @param points the points to set
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
	/**
	 * @param Adding points
	 */
	public void addPoints(Point point) {
		this.points.add(point);
	}
	
}
