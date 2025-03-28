package com.saviosousa.bancogestao.banco_api.exceptions;

public class FormaDePagamentoInvalidaException extends RuntimeException {
	public static String MSG_FORMA_DE_PAGAMENTO_INVALIDA = "Forma de pagamento inválida!";
	
	public FormaDePagamentoInvalidaException(String message) {
        super(message);
    }
}
