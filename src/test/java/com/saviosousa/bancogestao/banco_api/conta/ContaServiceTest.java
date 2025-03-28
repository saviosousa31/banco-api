package com.saviosousa.bancogestao.banco_api.conta;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.saviosousa.bancogestao.banco_api.exceptions.ContaJaExistenteException;
import com.saviosousa.bancogestao.banco_api.exceptions.NumeroDeContaInvalidoException;
import com.saviosousa.bancogestao.banco_api.exceptions.SaldoDaContaInvalidoException;
import com.saviosousa.bancogestao.banco_api.models.ContaModel;
import com.saviosousa.bancogestao.banco_api.services.ContaService; 

public class ContaServiceTest {
	
	private ContaService contaService;

    @BeforeEach
    void setUp() {
        contaService = new ContaService();
    }

    /**
	 * Validação ao listar todas as Contas
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testListarContas() {
    	/* ========== MONTA O CENÁRIO ========== */
        ContaModel conta1 = new ContaModel(123, 100.0F);       
        ContaModel conta2 = new ContaModel(234, 500.0F);         

        /* ========== EXECUÇÃO ========== */
        contaService.adicionarConta(conta1); // adiciona a conta 1 com o Número de Conta = 123
        contaService.adicionarConta(conta2); // tenta adicionar a conta 2 que possui o mesmo Número de Conta = 123
        
        /* ========== VERIFICAÇÕES ========== */
        assertNotNull(contaService.listarContas()); // verifica se a lista de Contas não é NULL, ou seja, se foi inserido
        assertEquals(contaService.listarContas().size(), 2); // verifica se a lista contém 2 objetos
    }

    /**
	 * Validação ao buscar uma Conta específica
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testBuscarConta() {
    	/* ========== MONTA O CENÁRIO ========== */
        ContaModel conta1 = new ContaModel(123, 100.0F);  
        contaService.adicionarConta(conta1); // adiciona a conta 1 com o Número de Conta = 123       

        /* ========== EXECUÇÃO ========== */
        Optional<ContaModel> contaEncontrada = contaService.buscarConta(conta1.getNumero_conta()); // busca a conta com o Número de Conta = 123 
        
        /* ========== VERIFICAÇÕES ========== */
        assertNotNull(contaEncontrada); // verifica se contaEncontrada não é NULL, ou seja, se foi encontrado de fato
    }

    /**
	 * Validação ao adicionar uma Conta
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testAdicionarConta() {
    	/* ========== MONTA O CENÁRIO ========== */
        ContaModel conta1 = new ContaModel(123, 100.0F);         

        /* ========== EXECUÇÃO ========== */
        try {
			contaService.adicionarConta(conta1); // adiciona a conta 1 com o Número de Conta = 123
		} catch (Exception e) {
			fail(); // caso ocorra algum erro, o teste falhou
		}
        /* ========== VERIFICAÇÕES ========== */
        Optional<ContaModel> contaEncontrada = contaService.buscarConta(conta1.getNumero_conta()); // busca a conta com o Número de Conta = 123 
        assertNotNull(contaEncontrada); // verifica se a lista de Contas não é NULL, ou seja, se foi inserido
    }

    /**
	 * Validação ao criar uma Conta com o número da Conta já existente
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testCriarContaJaExistente() {
    	/* ========== MONTA O CENÁRIO ========== */
        ContaModel conta1 = new ContaModel(123, 100.0F);       
        ContaModel conta2 = new ContaModel(123, 500.0F);         
        contaService.adicionarConta(conta1); // adiciona a conta 1 com o Número de Conta = 123

        /* ========== EXECUÇÃO ========== */
        try {
        	contaService.adicionarConta(conta2); // tenta adicionar a conta 2 que possui o mesmo Número de Conta = 123
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), ContaJaExistenteException.MSG_CONTA_JA_EXISTENTE); // verifica se lançou a exceção correta
		}	
    }

    /**
	 * Validação ao criar uma Conta com o número da Conta inválido
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testCriarContaNumeroInvalido() {
    	/* ========== MONTA O CENÁRIO ========== */
        ContaModel conta1 = new ContaModel(0, 100.0F);         
        ContaModel conta2 = new ContaModel(-1, 100.0F);         

        /* ========== EXECUÇÃO ========== */
        try {
        	contaService.adicionarConta(conta1); // tenta adicionar a conta 1 com o Número de Conta = 0
        	fail();
        	contaService.adicionarConta(conta2); // tenta adicionar a conta 2 com o Número de Conta = -1
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), NumeroDeContaInvalidoException.MSG_NUMERO_DE_CONTA_INVALIDO); // verifica se lançou a exceção correta
		}	
    }

    /**
	 * Validação ao criar uma Conta com o saldo da Conta inválido
	 * @author Sávio Soares Sousa 
	 * @date 28/03/2025
	 */
    @Test
    void testCriarContaSaldoInvalido() {
    	/* ========== MONTA O CENÁRIO ========== */
        ContaModel conta1 = new ContaModel(123, -1.0F);         

        /* ========== EXECUÇÃO ========== */
        try {
        	contaService.adicionarConta(conta1); // tenta adicionar a conta 1 que possui o saldo = -1
			fail();
		} catch (Exception e) {
		/* ========== VERIFICAÇÕES ========== */
			assertEquals(e.getMessage(), SaldoDaContaInvalidoException.MSG_SALDO_DA_CONTA_INVALIDO); // verifica se lançou a exceção correta
		}	
    }

}
