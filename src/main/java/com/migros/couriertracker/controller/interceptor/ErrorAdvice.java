package com.migros.couriertracker.controller.interceptor;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.migros.couriertracker.dto.BadRequestResponse;
import com.migros.couriertracker.exception.MigrosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

import static com.migros.couriertracker.dto.ErrorCode.*;

@RestControllerAdvice
public class ErrorAdvice {
	private final Logger log = LoggerFactory.getLogger(ErrorAdvice.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public BadRequestResponse handle(Exception e) {
		log.warn(e.getMessage(), e);
		return new BadRequestResponse(UNEXPECTED_ERROR, e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MigrosException.class)
	public BadRequestResponse handle(MigrosException e) {
		return new BadRequestResponse(e.getErrorCode(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public BadRequestResponse handleInvalidFormatException(HttpMessageNotReadableException e) {
		BadRequestResponse badRequestResponse = null;
		if (e.getCause() instanceof InvalidFormatException) {
			InvalidFormatException e1 = (InvalidFormatException) e.getCause();
			if (e1.getTargetType().equals(ZonedDateTime.class)) {
				badRequestResponse = new BadRequestResponse(INVALID_REQUEST, "invalid date");
			}
		}
		return badRequestResponse;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BadRequestResponse handleConverterErrors(MethodArgumentNotValidException e) {
		String message = getMessage(e);
		return new BadRequestResponse(INVALID_REQUEST, message);
	}

	private String getMessage(MethodArgumentNotValidException e) {
		FieldError error = e.getBindingResult().getFieldError();
		if (error != null) {
			return error.getDefaultMessage();
		}

		return e.getMessage();
	}
}
