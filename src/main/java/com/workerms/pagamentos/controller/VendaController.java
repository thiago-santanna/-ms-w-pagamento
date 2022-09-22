package com.workerms.pagamentos.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workerms.pagamentos.data.vo.VendaVo;
import com.workerms.pagamentos.services.VendaService;


@RestController
@RequestMapping("/venda")
public class VendaController {
	private final VendaService vendaService;
	private final PagedResourcesAssembler<VendaVo> assembler;
	
	@Autowired
	public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaVo> assembler) {
		this.vendaService = vendaService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public VendaVo findById(@PathVariable("id") Long id) {
		VendaVo produtoVo = vendaService.findById(id);
		produtoVo.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
		return produtoVo;
	}
	
	@GetMapping( produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		
		var sortDirection = "asc".equalsIgnoreCase(direction) ? Direction.ASC : Direction.DESC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataVenda"));
		
		Page<VendaVo> produtos = vendaService.findAll(pageable);
		
		produtos.stream().forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<VendaVo>> pagedModel = assembler.toModel(produtos);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping(
			produces = {"application/json","application/xml","application/x-yaml"}, 
			consumes = {"application/json","application/xml","application/x-yaml"}
			)
	public VendaVo create(@RequestBody VendaVo produtoVo) {
		VendaVo vo = vendaService.create(produtoVo);
		vo.add(linkTo(methodOn(VendaController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
}
