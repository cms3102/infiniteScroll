package com.mvcvisitors.common.exception;

public class GBException extends RuntimeException {
	
	private static final String DEFAULT_CODE = "KMS-0001";
	private String code;

	public GBException() {
		this(DEFAULT_CODE, "Database Server Error!");				
	}
		
	public GBException(String message) {
		super(message);
	}

	public GBException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GBException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public GBException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
