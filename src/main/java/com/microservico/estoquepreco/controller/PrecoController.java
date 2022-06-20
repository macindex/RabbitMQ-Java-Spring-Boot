package com.microservico.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservico.estoquepreco.contantes.RabbitmqContantes;
import com.microservico.estoquepreco.dto.PrecoDTO;
import com.microservico.estoquepreco.service.RabbitmqService;

@RestController
@RequestMapping(value="/preco")
public class PrecoController {
	
	@Autowired
	private RabbitmqService rabbitmqService;
	
	@PutMapping
	private ResponseEntity alteraPreco(@RequestBody PrecoDTO precoDTO) {
		this.rabbitmqService.enviaMensagem(RabbitmqContantes.FILA_PRECO, precoDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
