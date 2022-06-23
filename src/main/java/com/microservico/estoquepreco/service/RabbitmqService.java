package com.microservico.estoquepreco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitmqService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// ObjectMapper - Jackson: Serializa objeto java em json deserializa objetos
	// json em objetos java
	@Autowired
	private ObjectMapper objectMapper;

	public void enviaMensagem(String nomeFila, Object mensagem) {
		try {
		String mensagemJson = this.objectMapper.writeValueAsString(mensagem);
		this.rabbitTemplate.convertAndSend(nomeFila, mensagemJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
