package com.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.app.enums.ErrorCodes;
import com.app.model.MessageCode;
import com.app.model.Transaction;

public class Util {

	private MessageCode messageCode = null;

	/**
	 * @param value The string value to turn into LocalDate
	 * @return The value turned into.
	 */
    public static LocalDate getLocalDateFromString (String value) {
	    String dateTimePattern = "dd-MM-yyyy";
	  	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
	  	LocalDate localDate = LocalDate.parse(value, dateFormatter);
	  	return localDate;
    } 
	
	/**
	 * @return the month of purchase-date of date-format "dd-mm-yyyy".
	 */
	public static int getMonth(LocalDate value){
		return value.getMonthValue();
	}
	
	/**
	 * @return Validating entered parameters. 
	 */
	public void validateParameters(Transaction transaction){
		messageCode = new MessageCode();
		if (!isValidValue(Optional.ofNullable(transaction.getId())) || transaction.getId().trim().equals("")){
			messageCode.setStatus(HttpStatus.BAD_REQUEST.value());
			messageCode.setCode(ErrorCodes.ERR_PARAM_TRANSACTION_REQUIRED.getCode());
			messageCode.setMessage(ErrorCodes.ERR_PARAM_TRANSACTION_REQUIRED.getDescription());
		}else if (!isValidValue(Optional.ofNullable(transaction.getCustomer())) ||  transaction.getCustomer().trim().equals("")){
			messageCode.setStatus(HttpStatus.BAD_REQUEST.value());
			messageCode.setCode(ErrorCodes.ERR_PARAM_CUSTOMER_REQUIRED.getCode());
			messageCode.setMessage(ErrorCodes.ERR_PARAM_CUSTOMER_REQUIRED.getDescription());
		}else if (!isValidValue(Optional.ofNullable(transaction.getPurchaseDate()))){
			messageCode.setStatus(HttpStatus.BAD_REQUEST.value());
			messageCode.setCode(ErrorCodes.ERR_PARAM_PURCHASE_DATE_REQUIRED.getCode());
			messageCode.setMessage(ErrorCodes.ERR_PARAM_PURCHASE_DATE_REQUIRED.getDescription());
		}else if (transaction.getPurchaseAmmount()==0) {
			messageCode.setStatus(HttpStatus.BAD_REQUEST.value());
			messageCode.setCode(ErrorCodes.ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED.getCode());
			messageCode.setMessage(ErrorCodes.ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED.getDescription());
		}
	}

	/**
	 * @return the messageCode
	 */
	public MessageCode getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(MessageCode messageCode) {
		this.messageCode = messageCode;
	}
	
	
	private boolean isValidValue(Optional<Object> value) {
		return value.isPresent();
	}
	
	
}
