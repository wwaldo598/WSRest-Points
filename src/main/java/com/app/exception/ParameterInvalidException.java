package com.app.exception;

public class ParameterInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 3258123196789164940L;
	
	private Integer code = 0;
    
	public ParameterInvalidException (String message){
		super (message);
	}
	
	public ParameterInvalidException (Integer code, String message){
		super (message);
		this.code = code;
	}
	
	public ParameterInvalidException (String message, Throwable cause){
		super (message, cause);
	}
	
	public ParameterInvalidException (Integer code, String message, Throwable cause){
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
