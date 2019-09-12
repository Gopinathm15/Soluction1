package com.solution.task.exceptions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.solution.task.response.AppBaseResponse;
import com.solution.task.response.ErrorMessage;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(BadRequestHttpException.class)
	public ResponseEntity<?> handleHttpException(HttpServletRequest request, BadRequestHttpException e) {
		ResponseEntity<?> responseEntity = null;
		AppBaseResponse appBaseResponse = new AppBaseResponse();
		appBaseResponse.setStatus("FAILURE");
		ErrorMessage error = new ErrorMessage();
		error.setCode(e.getCode());
		error.setMessage(messageSource.getMessage(e.getCode(), null, getLocale(request.getHeader("Accept-Language"))));
		appBaseResponse.setError(error);
		responseEntity = new ResponseEntity<>(appBaseResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		ResponseEntity<?> responseEntity = null;
		AppBaseResponse appBaseResponse = new AppBaseResponse();
		appBaseResponse.setStatus("FAILURE");
		ErrorMessage error = new ErrorMessage();
		error.setCode("5000");
		error.setMessage(messageSource.getMessage("5000", null, getLocale(request.getHeader("Accept-Language"))));
		appBaseResponse.setError(error);
		responseEntity = new ResponseEntity<>(appBaseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}

	// Getting locale based on the language, if language not found default
	// locale it
	// will return.
	private Locale getLocale(String language) {
		Locale locale = null;
		if (language != null) {
			locale = new Locale(language);
		} else {
			locale = Locale.getDefault();
		}
		return locale;
	}
}
