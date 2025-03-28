package com.saviosousa.bancogestao.banco_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saviosousa.bancogestao.banco_api.exceptions.ContaNaoEncontradaException;
import com.saviosousa.bancogestao.banco_api.exceptions.FormaDePagamentoInvalidaException;
import com.saviosousa.bancogestao.banco_api.exceptions.SaldoInsuficienteException;
import com.saviosousa.bancogestao.banco_api.exceptions.ValorInvalidoException;
import com.saviosousa.bancogestao.banco_api.models.ContaModel;
import com.saviosousa.bancogestao.banco_api.models.TransacaoModel;

@Service
public class TransacaoService {

    @Autowired
    private ContaService contaService;

    // calcula a taxa da transação
    public float calcularTaxa(char formaPagamento) {
        switch (formaPagamento) {
            case 'C':
                return 0.05F;
            case 'D':
                return 0.03F;
            default:
            	return 0F;
        }
    }

    // valida as regras e realiza a transação caso seja permitido
    public void realizarTransacao(TransacaoModel transacao) throws RuntimeException {
        Optional<ContaModel> conta = contaService.buscarConta(transacao.getNumero_conta());
        if (!conta.isPresent()) {
            throw new ContaNaoEncontradaException(ContaNaoEncontradaException.MSG_CONTA_NAO_ENCONTRADA);
        }

        float taxa = calcularTaxa(transacao.getForma_pagamento());

        ContaModel contaEspecifica = conta.get();
        if (transacao.getValor() <= 0) {
            throw new ValorInvalidoException(ValorInvalidoException.MSG_VALOR_INVALIDO);
        } else if ((transacao.getValor() + (transacao.getValor() * taxa)) > contaEspecifica.getSaldo()) {
            throw new SaldoInsuficienteException(SaldoInsuficienteException.MSG_SALDO_INSUFICIENTE);
        } else if (transacao.getForma_pagamento() != 'P' && transacao.getForma_pagamento() != 'C' && transacao.getForma_pagamento() != 'D') {
        	throw new FormaDePagamentoInvalidaException(FormaDePagamentoInvalidaException.MSG_FORMA_DE_PAGAMENTO_INVALIDA);
        }        
        
        float novoSaldo = contaEspecifica.getSaldo() - (transacao.getValor() + (transacao.getValor() * taxa));
        contaEspecifica.setSaldo(novoSaldo);
    }
}
