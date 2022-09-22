package com.workerms.pagamentos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.workerms.pagamentos.data.vo.VendaVo;
import com.workerms.pagamentos.entity.ProdutoVenda;
import com.workerms.pagamentos.entity.Venda;
import com.workerms.pagamentos.exceptions.ResourceNotFoundException;
import com.workerms.pagamentos.repository.ProdutoVendaRepository;
import com.workerms.pagamentos.repository.VendaRepository;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;

	@Autowired
	public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}

	public VendaVo create(VendaVo vendaVo) {
		Venda venda = vendaRepository.save(Venda.fromVendaVo(vendaVo));
		
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		
		vendaVo.getProdutos().forEach(p -> {
			ProdutoVenda produtoVenda = ProdutoVenda.fromVendaVo(p);
			produtoVenda.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(produtoVenda));
		});
		
		venda.setProdutos(produtosSalvos);
		
		return VendaVo.fromVenda(venda);
	}

	public Page<VendaVo> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertVendaToVendaVo);
	}

	private VendaVo convertVendaToVendaVo(Venda venda) {
		return VendaVo.fromVenda(venda);
	}
	
	public VendaVo findById(Long id) {
		var venda = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto n'ao encontrado"));
		return VendaVo.fromVenda(venda);
	}	
}
