package com.migros.couriertracker.exception;

import com.migros.couriertracker.dto.ErrorCode;

public abstract class MigrosException extends RuntimeException {
	public MigrosException() {
	}

	public MigrosException(String message) {
		super(message);
	}

	public MigrosException(Throwable cause) {
		super(cause);
	}

	public abstract ErrorCode getErrorCode();
}
