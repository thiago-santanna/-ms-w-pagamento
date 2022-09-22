package com.workerms.pagamentos.data.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.workerms.pagamentos.entity.Venda;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({ "id", "dataVenda", "valorTotal", "produtos" })
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VendaVo extends RepresentationModel<VendaVo> implements Serializable {
	private static final long serialVersionUID = 6474262399810036997L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("dataVenda")
	private Date dataVenda;

	@JsonProperty("produtos")
	private List<ProdutoVendaVo> produtos = new ArrayList<>();

	@JsonProperty("valorTotal")
	private Double valorTotal;

	public static VendaVo fromVenda(Venda venda) {
		return new ModelMapper().map(venda, VendaVo.class);
	}
}
