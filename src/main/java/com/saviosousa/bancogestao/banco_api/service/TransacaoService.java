package com.saviosousa.bancogestao.banco_api.service;

import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

	public String transacaoService(String name) {
		return "Olá de novo " + name;
	}
}
