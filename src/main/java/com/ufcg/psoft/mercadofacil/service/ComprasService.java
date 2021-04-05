package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.MetodoPagamento;


public interface ComprasService {
	
	public String exibirDescricaoCompra(Long id);
	
	public String exibirUltimasCompras();

	public void adicionarCompra(String tipoPagamento, Cliente cliente);
	
	public void finalizarCompra(String tipoPagamento, Cliente cliente);	
	
	
	
}
