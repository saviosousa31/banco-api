package com.saviosousa.bancogestao.banco_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.saviosousa.bancogestao.banco_api.exceptions.ContaJaExistenteException;
import com.saviosousa.bancogestao.banco_api.exceptions.NumeroDeContaInvalidoException;
import com.saviosousa.bancogestao.banco_api.exceptions.SaldoDaContaInvalidoException;
import com.saviosousa.bancogestao.banco_api.models.ContaModel;

@Service
public class ContaService {
	
	// define a lista de Contas 
	private List<ContaModel> contas = new ArrayList<>();

	// retorna todas as Contas
    public List<ContaModel> listarContas() {
        return contas;
    }

    // cria uma nova Conta
    public void adicionarConta(ContaModel conta) {
    	if (conta.getNumero_conta() <= 0) {
            throw new NumeroDeContaInvalidoException(NumeroDeContaInvalidoException.MSG_NUMERO_DE_CONTA_INVALIDO);
        } else if (conta.getSaldo() < 0) {
            throw new SaldoDaContaInvalidoException(SaldoDaContaInvalidoException.MSG_SALDO_DA_CONTA_INVALIDO);
        } else if (this.buscarConta(conta.getNumero_conta()).isPresent()) {
        	throw new ContaJaExistenteException(ContaJaExistenteException.MSG_CONTA_JA_EXISTENTE);
        } else
        	contas.add(conta);
    }

    // busca por uma Conta específica
    public Optional<ContaModel> buscarConta(int numero_conta) {
        return contas.stream()
                .filter(conta -> conta.getNumero_conta() == numero_conta)
                .findFirst();
    }
}
