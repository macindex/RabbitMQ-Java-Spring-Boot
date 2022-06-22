package com.microservico.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.librabbitmq.librabbitmq.contantes.RabbitmqContantes;
//import com.librabbitmq.librabbitmq.dto.EstoqueDTO;
import com.librabbitmq.librabbitmq.contantes.RabbitmqContantes;
import com.librabbitmq.librabbitmq.dto.EstoqueDTO;
import com.microservico.estoquepreco.service.RabbitmqService;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {
	@Autowired
	private RabbitmqService rabbitmqService;

	@PutMapping
	private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
		this.rabbitmqService.enviaMensagem(RabbitmqContantes.FILA_ESTOQUE, estoqueDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
