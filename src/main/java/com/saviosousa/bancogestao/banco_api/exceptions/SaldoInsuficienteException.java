package com.saviosousa.bancogestao.banco_api.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException(String message) {
        super(message);
    }
}
