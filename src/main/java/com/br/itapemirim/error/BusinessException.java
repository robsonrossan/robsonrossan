package com.br.itapemirim.error;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, Exception ex) {
		super(message, ex);
	}
	
}
