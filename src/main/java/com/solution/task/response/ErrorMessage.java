package com.solution.task.response;

import com.google.gson.Gson;

public class ErrorMessage {

	private String message;
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
