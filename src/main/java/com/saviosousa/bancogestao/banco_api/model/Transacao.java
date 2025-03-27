package com.saviosousa.bancogestao.banco_api.model;

public class Transacao {

	private int num_conta;
	private char forma_pagamento;
	
	public int getNum_conta() {
		return num_conta;
	}
	public void setNum_conta(int num_conta) {
		this.num_conta = num_conta;
	}
	public char getForma_pagamento() {
		return forma_pagamento;
	}
	public void setForma_pagamento(char forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	
}
