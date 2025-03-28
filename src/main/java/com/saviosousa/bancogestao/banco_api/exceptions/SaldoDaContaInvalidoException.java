package com.saviosousa.bancogestao.banco_api.exceptions;

public class SaldoDaContaInvalidoException extends RuntimeException {
	public SaldoDaContaInvalidoException(String message) {
        super(message);
    }
}
