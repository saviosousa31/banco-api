package com.saviosousa.bancogestao.banco_api.transacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.saviosousa.bancogestao.banco_api.exceptions.ContaNaoEncontradaException;
import com.saviosousa.bancogestao.banco_api.exceptions.FormaDePagamentoInvalidaException;
import com.saviosousa.bancogestao.banco_api.exceptions.SaldoInsuficienteException;
import com.saviosousa.bancogestao.banco_api.exceptions.ValorInvalidoException;
import com.saviosousa.bancogestao.banco_api.models.ContaModel;
import com.saviosousa.bancogestao.banco_api.models.TransacaoModel;
import com.saviosousa.bancogestao.banco_api.services.ContaService;
import com.saviosousa.bancogestao.banco_api.services.TransacaoService;

public class TransacaoServiceTest {

	private TransacaoService transacaoService;
	private ContaService contaService;
	
	@BeforeEach
    void setUp() {
        contaService = new ContaService();
        transacaoService = new TransacaoService(contaService);
        ContaModel conta = new ContaModel(123, 100.0F);
        contaService.adicionarConta(conta);
    }

    /**
	 * Validação ao Realizar uma Transação
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testRealizarTransacao() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(123, 'P', 90.0F); // cria uma Transação válida
        
        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
		} catch (Exception e) {
			fail(); // caso ocorra algum erro, o teste falhou
		}
        /* ========== VERIFICAÇÕES ========== */
        assertEquals(contaService.buscarConta(transacao.getNumero_conta()).get().getSaldo(), 10.0F); // verifica se o saldo foi atualizado corretamente
    }

    /**
	 * Validação ao realizar uma Transação com saldo da Conta insuficiente #1
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testSaldoInsuficiente() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(123, 'P', 150.0F); // cria uma Transação com o valor acima do saldo da Conta

        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), SaldoInsuficienteException.MSG_SALDO_INSUFICIENTE); // verifica se lançou a exceção correta
		}
    }

    /**
	 * Validação ao realizar uma Transação com saldo da Conta insuficiente #2
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testSaldoInsuficiente2() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(123, 'C', 98.0F); // cria uma Transação com o (valor + taxa) acima do saldo da Conta

        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), SaldoInsuficienteException.MSG_SALDO_INSUFICIENTE); // verifica se lançou a exceção correta
		}
    }

    /**
	 * Validação ao realizar uma Transação com Valor da Transação inválido #1
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testValorInvalido() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(123, 'P', 0.0F); // cria uma Transação com o valor inválido

        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), ValorInvalidoException.MSG_VALOR_INVALIDO); // verifica se lançou a exceção correta
		}
    }

    /**
	 * Validação ao realizar uma Transação com Valor da Transação inválido #2
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testValorInvalido2() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(123, 'P', -1.5F); // cria uma Transação com o valor inválido

        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), ValorInvalidoException.MSG_VALOR_INVALIDO); // verifica se lançou a exceção correta
		}
    }

    /**
	 * Validação ao realizar uma Transação com a Forma de Pagamento inválida
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testFormaDePagamentoInvalida() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(123, 'X', 10.0F); // cria uma Transação com a Forma de Pagamento inválida

        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), FormaDePagamentoInvalidaException.MSG_FORMA_DE_PAGAMENTO_INVALIDA); // verifica se lançou a exceção correta
		}
    }

    /**
	 * Validação ao realizar uma Transação com uma Conta inexistente
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testContaInexistente() {
    	/* ========== MONTA O CENÁRIO ========== */
        TransacaoModel transacao = new TransacaoModel(999, 'D', 10.0F); // cria uma Transação com uma Conta inexistente

        /* ========== EXECUÇÃO ========== */
        try {
			transacaoService.realizarTransacao(transacao); // tenta executar a Transação criada
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), ContaNaoEncontradaException.MSG_CONTA_NAO_ENCONTRADA); // verifica se lançou a exceção correta
		}
    }
}
