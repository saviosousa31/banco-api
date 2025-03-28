package com.saviosousa.bancogestao.banco_api.exceptions;

public class ContaNaoEncontradaException extends RuntimeException {
	public ContaNaoEncontradaException(String message) {
        super(message);
    }
}
