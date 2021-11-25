package com.app.util;

import java.time.LocalDate;
import java.util.Optional;

import com.app.enums.ErrorCodes;
import com.app.model.MessageCode;
import com.app.model.Transaction;

public class Util {

	private MessageCode messageCode = null;
	
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
			messageCode.setCode(ErrorCodes.ERR_PARAM_TRANSACTION_REQUIRED.getCode());
			messageCode.setDescription(ErrorCodes.ERR_PARAM_TRANSACTION_REQUIRED.getDescription());
		}else if (!isValidValue(Optional.ofNullable(transaction.getCustomer())) ||  transaction.getCustomer().trim().equals("")){
			messageCode.setCode(ErrorCodes.ERR_PARAM_CUSTOMER_REQUIRED.getCode());
			messageCode.setDescription(ErrorCodes.ERR_PARAM_CUSTOMER_REQUIRED.getDescription());
		}else if (!isValidValue(Optional.ofNullable(transaction.getPurchaseDate()))){
			messageCode.setCode(ErrorCodes.ERR_PARAM_PURCHASE_DATE_REQUIRED.getCode());
			messageCode.setDescription(ErrorCodes.ERR_PARAM_PURCHASE_DATE_REQUIRED.getDescription());
		}else if (transaction.getPurchaseAmmount()==0) {
			messageCode.setCode(ErrorCodes.ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED.getCode());
			messageCode.setDescription(ErrorCodes.ERR_PARAM_PURCHASE_AMMOUNT_REQUIRED.getDescription());
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
