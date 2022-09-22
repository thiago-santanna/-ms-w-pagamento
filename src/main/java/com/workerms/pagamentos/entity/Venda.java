package com.workerms.pagamentos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.workerms.pagamentos.data.vo.VendaVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venda implements Serializable {
	private static final long serialVersionUID = 4486557245963967972L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "data_venda", nullable = false)
	private Date dataVenda;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
	private List<ProdutoVenda> produtos = new ArrayList<>();
	
	@Column(name = "valor_total", length = 10, nullable = false)
	private Double valorTotal;
	
	public static Venda fromVendaVo(VendaVo vendaVo) {
		return new ModelMapper().map(vendaVo, Venda.class);
	}

}
