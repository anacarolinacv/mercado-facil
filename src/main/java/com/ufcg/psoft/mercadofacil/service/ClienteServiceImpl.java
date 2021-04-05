package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Cliente;import com.ufcg.psoft.mercadofacil.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	@Override
	public boolean adicionaCliente(Cliente cliente) {
		
		Long idCliente = cliente.getId();
		Cliente clienteTeste = this.clienteRepository.getOne(idCliente);
		
		if(clienteTeste == null){
			
			this.clienteRepository.save(cliente);
		} 

		return true;
	}
	
	
	@Override
	public boolean removerCliente(Long id) {
		Cliente cliente = this.clienteRepository.getOne(id);
		
		if(cliente != null){
			
			this.clienteRepository.delete(cliente);
		} 
		
		return true;
	}

	@Override
	public Optional<Cliente> getClienteById(Long id) {
		return this.clienteRepository.findById(id);
	}

	@Override
	public String listarCliente() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		String saida = "";
		for(Cliente cliente: clientes) {
			saida += cliente.toString() + System.lineSeparator();
		}
		return saida;
	}


	@Override
	public void editarCliente(Cliente cliente, String tipo) {
		
		Long idCliente = cliente.getId();
		Cliente clienteTeste = this.clienteRepository.getOne(idCliente);
		
		if(clienteTeste != null) {
			String tipoTeste = clienteTeste.getTipo();
			
			if(tipo != tipoTeste) {
				clienteTeste.setTipo(tipo);
			}		
		}
		
	}
	

}
