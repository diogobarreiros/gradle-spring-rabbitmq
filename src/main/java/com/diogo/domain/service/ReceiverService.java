package com.diogo.domain.service;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author dbarreiros
 * 
 * Responsável por registrar as mensagens recebidas.
 */
public class ReceiverService {

	private CountDownLatch latch = new CountDownLatch(1);

	/**
	 * Método para a recepção de mensagens
	 * @param message - mensagem enviada
	 */
	public void receiveMessage(String message) {
		System.out.println("Recebido <" + message + ">");
		latch.countDown();
	}

	/**
	 * CountDownLatch permite que um sinal seja enviado para que confirme a entrega da mensagem.
	 * @return CountDownLatch
	 */
	public CountDownLatch getLatch() {
		return latch;
	}

}
