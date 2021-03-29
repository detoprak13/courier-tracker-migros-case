package com.migros.couriertracker.exception;

import com.migros.couriertracker.dto.ErrorCode;

public class NotFoundException extends MigrosException{
	public NotFoundException(String message) {
		super(message);
	}

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.COURIER_NOT_FOUND;
	}
}
