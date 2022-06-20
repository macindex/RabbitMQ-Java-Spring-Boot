package com.microservico.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservico.estoquepreco.dto.EstoqueDTO;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueController {
	@Autowired
	
	private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
		return new ResponseEntity(HttpStatus.OK);
	}
}
