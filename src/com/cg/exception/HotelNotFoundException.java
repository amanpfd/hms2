package com.cg.exception;

public class HotelNotFoundException extends Exception{

	public HotelNotFoundException() {
		super();
	}

	public HotelNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public HotelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelNotFoundException(String message) {
		super(message);
	}

	public HotelNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
