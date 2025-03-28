package com.saviosousa.bancogestao.banco_api.exceptions;

public class ContaJaExistenteException extends RuntimeException {
	public static String MSG_CONTA_JA_EXISTENTE = "Conta já existente!";
	
	public ContaJaExistenteException(String message) {
        super(message);
    }
}
