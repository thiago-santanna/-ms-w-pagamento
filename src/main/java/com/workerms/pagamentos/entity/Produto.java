package com.workerms.pagamentos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.workerms.pagamentos.data.vo.ProdutoVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {
	private static final long serialVersionUID = 8774953817727299439L;

	@Id
	private Long id;

	@Column(name = "estoque", length = 10, nullable = false)
	private Integer estoque;

	public static Produto fromVendaVo(ProdutoVo produtoVo) {
		return new ModelMapper().map(produtoVo, Produto.class);
	}
}
