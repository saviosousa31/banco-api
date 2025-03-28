package com.saviosousa.bancogestao.banco_api.exceptions;

public class NumeroDeContaInvalidoException extends RuntimeException {
	public static String MSG_NUMERO_DE_CONTA_INVALIDO = "Número de Conta inválido!";
	
	public NumeroDeContaInvalidoException(String message) {
        super(message);
    }
}
