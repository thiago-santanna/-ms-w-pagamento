package com.workerms.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workerms.pagamentos.entity.ProdutoVenda;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

}
