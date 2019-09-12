package com.solution.task.exceptions;

public class BadRequestHttpException extends HttpException {
	private static final long serialVersionUID = 1070136760341832975L;

	public BadRequestHttpException() {
		super();
	}

	public BadRequestHttpException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadRequestHttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestHttpException(String message, String code) {
		super(message, code);
	}

	public BadRequestHttpException(String code) {
		super(code);
	}

	public BadRequestHttpException(Throwable cause) {
		super(cause);
	}

}