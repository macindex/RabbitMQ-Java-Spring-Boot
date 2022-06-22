package com.microservico.estoquepreco.connections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.librabbitmq.librabbitmq.contantes.RabbitmqContantes;

@Component
public class RabbitMQConnection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	// Fila duravel sim, exclusiva false, autodelete false
	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}
	// Fila duravel sim, exclusiva false, autodelete false	
	private DirectExchange trocaDireta() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	// Binding pede a fila, o destino desta fila, nome da exchange e nome do mapeamento que será feito
	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}
	
	@PostConstruct
	private void adiciona() {
	Queue filaEstoque =	this.fila(RabbitmqContantes.FILA_ESTOQUE);
	Queue filaPreco =	this.fila(RabbitmqContantes.FILA_PRECO);
	
	
	DirectExchange troca = this.trocaDireta();
	
	Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
	Binding ligacaoPreco = this.relacionamento(filaPreco, troca);

	// Criando as filas no RabbitMQ
	
	this.amqpAdmin.declareQueue(filaEstoque);
	this.amqpAdmin.declareQueue(filaPreco);
	
	this.amqpAdmin.declareExchange(troca);
	
	this.amqpAdmin.declareBinding(ligacaoEstoque);
	this.amqpAdmin.declareBinding(ligacaoPreco);
	}
}
