package com.ufcg.psoft.mercadofacil.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CompraDTO {
	
	private Long id;
	
	private Long usuarioId;
	
	private List<ProdutoDoCarrinhoDTO> produtos;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	public CompraDTO() {}
	
	public CompraDTO(List<ProdutoDoCarrinhoDTO> produtos, BigDecimal valorTotal,
			LocalDateTime dataCriacao) {
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
	}
	
	public CompraDTO(
			Long id,
			List<ProdutoDoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao,
			Long usuarioId) {
		this.id = id;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.usuarioId = usuarioId;
	}

	public List<ProdutoDoCarrinhoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDoCarrinhoDTO> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
