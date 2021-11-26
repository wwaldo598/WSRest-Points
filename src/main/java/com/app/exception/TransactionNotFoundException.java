package com.app.exception;

public class TransactionNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -9167934156795094131L;
	
	private Integer code = 0;
    
	public TransactionNotFoundException (String message){
		super (message);
	}
	
	public TransactionNotFoundException (String message, Throwable cause){
		super (message, cause);
	}
	
	public TransactionNotFoundException (Integer code, String message, Throwable cause){
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
