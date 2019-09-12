package com.solution.task.response;

import java.util.Map;

import com.google.gson.Gson;
import com.solution.task.constant.ResponseStatus;

public class AppBaseResponse<T> {

	private String status;
	private T data;
	private ErrorMessage error;

	public AppBaseResponse() {
		status = ResponseStatus.SUCCESS.name();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ErrorMessage getError() {
		return error;
	}

	public void setError(ErrorMessage error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
