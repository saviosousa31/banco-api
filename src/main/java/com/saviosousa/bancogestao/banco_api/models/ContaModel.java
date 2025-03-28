package com.saviosousa.bancogestao.banco_api.models;

public class ContaModel {
	private int numero_conta;
	private float saldo;
	
	public ContaModel() {
		
	}
	
	public ContaModel(int numero_conta, float saldo) {
		this.numero_conta = numero_conta;
		this.saldo = saldo;
	} 
	
	public int getNumero_conta() {
		return numero_conta;
	}
	public void setNumero_conta(int numero_conta) {
		this.numero_conta = numero_conta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
}
