package com.ufcg.psoft.mercadofacil.service;

import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Cliente;

public interface ClienteService {
	
	public boolean adicionaCliente(Cliente cliente);
	
	public boolean removerCliente(Long id); 
	
	public Optional<Cliente> getClienteById(Long id);
	
	public String listarCliente();
	
	public void editarCliente(Cliente cliente, String tipo);
	
}
