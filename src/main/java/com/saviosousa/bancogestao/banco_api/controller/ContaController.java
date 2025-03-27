package com.saviosousa.bancogestao.banco_api.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saviosousa.bancogestao.banco_api.model.Conta;
import com.saviosousa.bancogestao.banco_api.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	private List<Conta> contas = new ArrayList<>();
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping("")
	public List<Conta> listarContas(){
		return contas;
	}
	
	// Buscar uma conta específica pelo número 
    @GetMapping("/{numeroConta}")
    public Conta buscarConta(@RequestParam int numero_conta) {
        Optional<Conta> contaEncontrada = contas.stream()
                .filter(conta -> conta.getNumero_conta() == (numero_conta))
                .findFirst();

        return contaEncontrada.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
	
	@PostMapping("")
	public String criarConta(@RequestBody Conta body) {
		
		contas.add(body);
		
		// Criando um formatador de moeda para o Brasil
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        
        // Formatando o saldo
        String saldoFormatado = formatoMoeda.format(body.getSaldo());

        return "Conta criada com sucesso!\nNúmero da conta: " + body.getNumero_conta() +
               "\nSaldo disponível: " + saldoFormatado;     
		
	}
}
