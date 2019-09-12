package com.solution.task.services;

import org.springframework.http.ResponseEntity;

public interface UserService {

	public ResponseEntity saveData(String appBaseRequest);

	public ResponseEntity findData(String id) throws Exception;

	public ResponseEntity deleteData(String id);
}
