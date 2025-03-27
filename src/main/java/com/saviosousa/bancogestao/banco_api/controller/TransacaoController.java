package com.saviosousa.bancogestao.banco_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

	@GetMapping
	public String transacao() {
		return "Em execução";
	}
}
