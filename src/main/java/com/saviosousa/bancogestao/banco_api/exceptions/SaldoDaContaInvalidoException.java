package com.saviosousa.bancogestao.banco_api.exceptions;

public class SaldoDaContaInvalidoException extends RuntimeException {
	public static String MSG_SALDO_DA_CONTA_INVALIDO = "Saldo da Conta inválido!";
	
	public SaldoDaContaInvalidoException(String message) {
        super(message);
    }
}
