package com.app.model;

public class Point {
	private Integer month;
	private Integer points;

	public Point(int month, int points) {
		super();
		this.month = month;
		this.points = points;
	}
	
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Point [month=" + month + ", points=" + points + "]";
	}
	
}
