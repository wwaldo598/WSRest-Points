package com.app.model;

public class MessageCode {

	private Integer status;
	private Integer code;
	private String  message;
		
	public MessageCode() {
		super();
		this.status = 0;
		this.code = 0;
		this.message = "";
	}
	
	public MessageCode(Integer status, Integer code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
