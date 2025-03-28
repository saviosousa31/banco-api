package com.saviosousa.bancogestao.banco_api.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
	public static String MSG_SALDO_INSUFICIENTE = "Saldo insuficiente!!";
	
	public SaldoInsuficienteException(String message) {
        super(message);
    }
}
