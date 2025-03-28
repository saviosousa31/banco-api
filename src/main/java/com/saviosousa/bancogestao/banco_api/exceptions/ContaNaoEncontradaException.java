package com.saviosousa.bancogestao.banco_api.exceptions;

public class ContaNaoEncontradaException extends RuntimeException {
	public static String MSG_CONTA_NAO_ENCONTRADA = "Conta não encontrada!";
	
	public ContaNaoEncontradaException(String message) {
        super(message);
    }
}
