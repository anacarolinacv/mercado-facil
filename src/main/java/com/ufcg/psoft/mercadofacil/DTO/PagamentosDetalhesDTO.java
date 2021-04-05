package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.model.MetodoPagamento;


public class PagamentosDetalhesDTO {
	
	private MetodoPagamento metodoPagamento;
	
	public PagamentosDetalhesDTO() {}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
}
