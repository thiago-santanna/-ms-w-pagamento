package com.workerms.pagamentos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.workerms.pagamentos.data.vo.ProdutoVendaVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto_venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVenda implements Serializable {

	private static final long serialVersionUID = 6546787980984353347L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_produto", length = 10, nullable = false)
	private Long idProduto;

	@Column(name = "quantidade", length = 10, nullable = false)
	private Integer quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venda")
	private Venda venda;

	public static ProdutoVenda fromVendaVo(ProdutoVendaVo produtoVendaVo) {
		return new ModelMapper().map(produtoVendaVo, ProdutoVenda.class);
	}

}
