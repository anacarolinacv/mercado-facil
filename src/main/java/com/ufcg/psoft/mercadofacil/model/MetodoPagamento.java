package com.ufcg.psoft.mercadofacil.model;

public interface MetodoPagamento {
	
	public Double gerarAcrescimo(Double valorCompra);
	public String getTipo();

}
