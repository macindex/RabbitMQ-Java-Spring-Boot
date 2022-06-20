package com.microservico.estoquepreco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservico.estoquepreco.dto.PrecoDTO;

@RestController
@RequestMapping(value="/preco")
public class PrecoController {
	
	private ResponseEntity alteraPreco(@RequestBody PrecoDTO precoDTO) {
		return new ResponseEntity(HttpStatus.OK);
	}
}
