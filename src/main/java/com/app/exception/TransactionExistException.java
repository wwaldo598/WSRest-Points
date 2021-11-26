package com.app.exception;

public class TransactionExistException extends RuntimeException{
	
	private static final long serialVersionUID = 5371033369914984590L;
	
	private Integer code = 0;
    
	public TransactionExistException (String message){
		super (message);
	}
	
	public TransactionExistException (String message, Throwable cause){
		super (message, cause);
	}
	
	public TransactionExistException (Integer code, String message, Throwable cause){
		super (message, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
