package com.workerms.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workerms.pagamentos.entity.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
