package com.workerms.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workerms.pagamentos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
