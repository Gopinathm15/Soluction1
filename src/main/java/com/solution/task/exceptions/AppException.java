package com.solution.task.exceptions;

public class AppException extends RuntimeException {
	private static final long serialVersionUID = -1324181779916638644L;
	private String message;
	private String code;

	public AppException(String code) {
		this.code = code;
	}

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message, String code) {
		this.code = code;
		this.message = message;
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
