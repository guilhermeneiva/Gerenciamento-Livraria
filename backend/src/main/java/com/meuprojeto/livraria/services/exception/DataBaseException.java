package com.meuprojeto.livraria.services.exception;

public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataBaseException(String mensagem) {
		super(mensagem);
	}
}
