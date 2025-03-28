package com.saviosousa.bancogestao.banco_api.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saviosousa.bancogestao.banco_api.exceptions.ContaJaExistenteException;
import com.saviosousa.bancogestao.banco_api.exceptions.ContaNaoEncontradaException;
import com.saviosousa.bancogestao.banco_api.exceptions.NumeroDeContaInvalidoException;
import com.saviosousa.bancogestao.banco_api.exceptions.SaldoDaContaInvalidoException;
import com.saviosousa.bancogestao.banco_api.models.ContaModel;
import com.saviosousa.bancogestao.banco_api.services.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;

	// lista todas as contas ou busca por número de conta se o parâmetro for
	// informado
	@GetMapping("")
	public ResponseEntity<?> listarOuBuscarConta(@RequestParam(required = false) Integer numero_conta) {
		if (numero_conta != null) {
			try {
				Optional<ContaModel> contaEspecifica = contaService.buscarConta(numero_conta);

				if (contaEspecifica.isPresent()) {
					// Retorna JSON da Conta específica com número da conta e saldo
					Map<String, Object> resposta = new HashMap<>();
					resposta.put("numero_conta", contaEspecifica.get().getNumero_conta());
					resposta.put("saldo", contaEspecifica.get().getSaldo());
					return ResponseEntity.ok(resposta);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(ContaNaoEncontradaException.MSG_CONTA_NAO_ENCONTRADA);
				}
			} catch (Exception e) {
				// captura qualquer exceção que não tenha sido tratada
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Erro inesperado: " + e.getMessage());
			}
		}
		return ResponseEntity.ok(contaService.listarContas()); // retorna todas as contas
	}

	// cria uma nova conta, validando caso ela já exista
	@PostMapping("")
	public ResponseEntity<?> criarConta(@RequestBody ContaModel body) {
		try {
			contaService.adicionarConta(body); // adiciona a conta na lista de Contas
			return ResponseEntity.status(HttpStatus.CREATED).body(body);
		} catch (NumeroDeContaInvalidoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (SaldoDaContaInvalidoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (ContaJaExistenteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			// captura qualquer outra exceção que não tenha sido tratada
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + e.getMessage());
		}
	}
}
