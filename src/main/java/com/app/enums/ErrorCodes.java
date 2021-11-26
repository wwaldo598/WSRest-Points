package com.app.enums;

public enum ErrorCodes {
	
    ERR_PARAM_TRANSACTION_REQUIRED 		(-1, "The value of the parameter 'id' is required"),
    ERR_PARAM_CUSTOMER_REQUIRED 		(-2, "The value of the parameter 'customer' is required"),
    ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED (-3, "The value of the parameter 'purchaseAmmount' is required"),
    ERR_PARAM_PURCHASE_DATE_REQUIRED 	(-4, "The value of the parameter 'purchaseDate' is required"),
    ERR_PARAM_INVALID_VALUE				(-4, "The value entered is a bad formatted JSON"),    
    ERR_CUSTOMER_NOT_EXIST 				(-5, "The required customer does not exist"),
    
    ERR_TRANSACTION_NOT_EXIST 			(-6, "The entered transaction does not exist"),
    ERR_TRANSACTION_ALREADY_EXIST 		(-7, "The entered transaction already exist"),

    ERR_SERVER_INTERNAL 				(-99,"An error was generated processing the request");  
	
	private Integer code;
	private String  description;
	
	ErrorCodes(int code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
}
