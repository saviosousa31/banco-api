package com.saviosousa.bancogestao.banco_api.service;

import org.springframework.stereotype.Service;

@Service
public class ContaService {

	public String conta(String name) {
		return "Olá " + name;
	}
}
