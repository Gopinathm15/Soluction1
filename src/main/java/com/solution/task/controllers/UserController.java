package com.solution.task.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solution.task.services.UserService;
import com.solution.task.utils.RequestUtils;

@RestController
@RequestMapping(value = "/user-service/")
public class UserController {

	@Autowired
	@Qualifier("userServiceImp")
	private UserService userService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseEntity addData(InputStream inputStreamRequest) throws IOException {
		String requestAsString = RequestUtils.convert(inputStreamRequest);
		return userService.saveData(requestAsString);
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public ResponseEntity getData(@RequestParam(name = "id") String id) throws Exception {
		return userService.findData(id);
	}

	@RequestMapping(value = "remove", method = RequestMethod.DELETE)
	public ResponseEntity removeData(@RequestParam(name = "id") String id) {
		return userService.deleteData(id);
	}
}
