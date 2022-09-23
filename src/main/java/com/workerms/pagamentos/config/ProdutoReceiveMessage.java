package com.workerms.pagamentos.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.workerms.pagamentos.data.vo.ProdutoVo;
import com.workerms.pagamentos.entity.Produto;
import com.workerms.pagamentos.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {
	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoVo produtoVo) {
		produtoRepository.save(Produto.fromVendaVo(produtoVo));
	}
}
