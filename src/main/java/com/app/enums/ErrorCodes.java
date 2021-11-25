package com.app.enums;

public enum ErrorCodes {
	
    ERR_PARAM_TRANSACTION_REQUIRED 		(-1, "The value of the parameter \"transaction\" is required."),
    ERR_PARAM_CUSTOMER_REQUIRED 		(-2, "The value of the parameter \"customer\" is required."),
    ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED (-3, "The value of the parameter \"purchaseAmmount\" is required."),
    ERR_PARAM_PURCHASE_DATE_REQUIRED 	(-4, "The value of the parameter \"purchaseDate\" is required."),
    ERR_PARAM_TRANSACTION_NOT_EXIST 	(-5, "The the entered transaction does not exist."),
    ERR_PARAM_TRANSACTION_ALREADY_EXIST (-6, "The the entered transaction already exist."),
	ERR_SERVER_INTERNAL 				(-99,"An error was generated processing the request.");  
	
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
