package com.saviosousa.bancogestao.banco_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saviosousa.bancogestao.banco_api.exceptions.ContaNaoEncontradaException;
import com.saviosousa.bancogestao.banco_api.exceptions.FormaDePagamentoInvalidaException;
import com.saviosousa.bancogestao.banco_api.exceptions.SaldoInsuficienteException;
import com.saviosousa.bancogestao.banco_api.exceptions.ValorInvalidoException;
import com.saviosousa.bancogestao.banco_api.models.TransacaoModel;
import com.saviosousa.bancogestao.banco_api.services.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	// realiza uma nova transação através do TransacaoService
	@PostMapping("")
	public ResponseEntity<?> realizarTransacao(@RequestBody TransacaoModel body) {
		try {
	        transacaoService.realizarTransacao(body);
        	return ResponseEntity.status(HttpStatus.CREATED).body(body);        	
	    } catch (ContaNaoEncontradaException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (SaldoInsuficienteException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (ValorInvalidoException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (FormaDePagamentoInvalidaException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        // captura qualquer outra exceção que não tenha sido tratada
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + e.getMessage());
	    }
	}
}
