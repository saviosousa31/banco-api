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

import com.saviosousa.bancogestao.banco_api.models.ContaModel;
import com.saviosousa.bancogestao.banco_api.services.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;	 

	// lista todas as contas ou busca por número de conta se o parâmetro for informado
    @GetMapping("")
    public ResponseEntity<?> listarOuBuscarConta(@RequestParam(required = false) Integer numero_conta) {
        if (numero_conta != null) {
        	
        	Optional<ContaModel> contaEspecifica = contaService.buscarConta(numero_conta);
        	
            if (contaEspecifica.isPresent()) {
                // Retorna JSON com número da conta e saldo
                Map<String, Object> resposta = new HashMap<>();
                resposta.put("numero_conta", contaEspecifica.get().getNumero_conta());
                resposta.put("saldo", contaEspecifica.get().getSaldo());
                return ResponseEntity.ok(resposta); // HTTP 200
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
            }
        }
        return ResponseEntity.ok(contaService.listarContas()); // retorna todas as contas
    }
	
    // cria uma nova conta, validando caso ela já exista
	@PostMapping("")
	public ResponseEntity<?> criarConta(@RequestBody ContaModel body) {
		if (contaService.buscarConta(body.getNumero_conta()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta já existente!");
        }
        else {
        	contaService.adicionarConta(body); // adiciona a conta na lista de Contas         	
        	return ResponseEntity.status(HttpStatus.CREATED).body(body);      
        }
	}
}
