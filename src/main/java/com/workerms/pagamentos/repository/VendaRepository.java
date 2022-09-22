package com.workerms.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workerms.pagamentos.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
