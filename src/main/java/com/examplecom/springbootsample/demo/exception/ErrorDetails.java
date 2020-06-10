package com.examplecom.springbootsample.demo.exception;

import java.sql.Date;

public class ErrorDetails {

	
	private Date timestamp;
	private String message;
	private String decsription;
	
	public ErrorDetails(Date timestamp, String message, String description) {
		super();
		this.timestamp=timestamp;
		this.message=message;
		this.decsription=description;
		
	}

	public Date getTimestamp() {
		return timestamp;
	}

	

	public String getMessage() {
		return message;
	}

	

	public String getDecsription() {
		return decsription;
	}

	
	
	
}
