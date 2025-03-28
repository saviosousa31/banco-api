package com.saviosousa.bancogestao.banco_api.exceptions;

public class FormaDePagamentoInvalidaException extends RuntimeException {
	public FormaDePagamentoInvalidaException(String message) {
        super(message);
    }
}
