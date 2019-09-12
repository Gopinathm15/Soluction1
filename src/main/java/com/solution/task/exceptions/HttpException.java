package com.solution.task.exceptions;


public class HttpException extends AppException {
	private static final long serialVersionUID = 6543102950937813082L;

	public HttpException() {
		super();
	}

	public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpException(String message, String code) {
		super(message, code);
	}

	public HttpException(String code) {
		super(code);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

}