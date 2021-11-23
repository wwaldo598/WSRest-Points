package com.app.model;

public class MessageCode {

	private Integer code;
	private String  description;
		
	public MessageCode() {
		super();
		this.code = 0;
		this.description = "";
	}
	
	public MessageCode(Integer code, String description) {
		super();
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
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
