package com.saviosousa.bancogestao.banco_api.exceptions;

public class ValorInvalidoException extends RuntimeException {
	public static String MSG_VALOR_INVALIDO = "Valor inválido!";
	
	public ValorInvalidoException(String message) {
        super(message);
    }
}
