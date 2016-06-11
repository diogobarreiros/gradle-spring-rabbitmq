package com.diogo.domain.service;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 
 * @author dbarreiros
 *
 * Responsável por registrar o ouvinte e enviar uma mensagem.
 */
@Service
public class RabbitService {

	final static String queueName = "spring-rabbitmq-ejb";

	@Autowired
	AnnotationConfigApplicationContext context;
	
	//RabbitTemplate para enviar mensagens.
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-rabbitmq-ejb-topicexchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

    @Bean
    ReceiverService receiver() {
        return new ReceiverService();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(ReceiverService receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public void run() throws InterruptedException{
		System.out.println("Esperando cinco segundos...");
        Thread.sleep(5000);
        System.out.println("Enviando mensagem...");
        rabbitTemplate.convertAndSend(queueName, "Aplicação utilizando RabbitMQ!");
        receiver().getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
	}
}
