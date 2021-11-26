package com.app.exception;

public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 8737987284838703604L;
	
	private Integer code = 0;
    
	public CustomerNotFoundException (String message){
		super (message);
	}
	
	public CustomerNotFoundException (String message, Throwable cause){
		super (message, cause);
	}
	
	public CustomerNotFoundException (Integer code, String message, Throwable cause){
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
