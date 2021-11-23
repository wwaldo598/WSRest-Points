package com.app.tools;

import java.time.LocalDate;

public class Tools {

	/**
	 * @return the month of purchase-date of date-format "dd-mm-yyyy".
	 */
	public static int getMonth(LocalDate value){
		return value.getMonthValue();
	}
	
	
}
