package com.saviosousa.bancogestao.banco_api.models;

public class TransacaoModel {

	private int numero_conta;
	private char forma_pagamento;
	private float valor;
	
	public TransacaoModel() {
		
	}
	
	public TransacaoModel(int num_conta, char forma_pagamento, float valor) {
		this.numero_conta = num_conta;
		this.forma_pagamento = forma_pagamento;
		this.valor = valor;
	}
	
	public int getNumero_conta() {
		return numero_conta;
	}
	public void setNumero_conta(int numero_conta) {
		this.numero_conta = numero_conta;
	}
	public char getForma_pagamento() {
		return forma_pagamento;
	}
	public void setForma_pagamento(char forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
