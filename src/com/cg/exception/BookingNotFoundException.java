package com.cg.exception;

public class BookingNotFoundException extends Exception{

	public BookingNotFoundException() {
		super();
	}

	public BookingNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookingNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingNotFoundException(String message) {
		super(message);
	}

	public BookingNotFoundException(Throwable cause) {
		super(cause);
	}
	

}
