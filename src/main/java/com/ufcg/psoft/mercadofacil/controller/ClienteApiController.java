package com.ufcg.psoft.mercadofacil.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.service.ClienteService;
import com.ufcg.psoft.mercadofacil.util.ErroCliente;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClienteApiController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@RequestMapping(value = "/cliente/criar/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> salvarCliente(@PathVariable Long id, @RequestBody String nome, @RequestBody String email) {
		
		Optional<Cliente> optionalCliente = clienteService.getClienteById(id);
		
		if (!optionalCliente.isPresent()) {
			return ErroCliente.erroClienteNaoEncontrado(id);
		}
		
		if(nome == null | email == null) {
			return ErroCliente.erroAtributoIncorreto();
		}
		
		Cliente cliente = new Cliente(email, nome);
		this.clienteService.adicionaCliente(cliente);

		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/cliente/remover/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerCliente(@PathVariable Long id) {
		
		Optional<Cliente> optionalCliente = clienteService.getClienteById(id);
		
		if (!optionalCliente.isPresent()) {
			return ErroCliente.erroClienteNaoEncontrado(id);
		}
		
		this.clienteService.removerCliente(id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<?> listarClientes() {
		
		String clientes = clienteService.listarCliente();

		if (clientes.isEmpty()) {
			return ErroCliente.erroNenhumClienteCadastrado();
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cliente/editar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editarCliente(@PathVariable Long id, @RequestBody String novoTipo) {
		
		Optional<Cliente> optionalCliente = clienteService.getClienteById(id);
		
		if (!optionalCliente.isPresent()) {
			return ErroCliente.erroClienteNaoEncontrado(id);
		}
		Cliente clienteTeste = optionalCliente.get();
		
		if(clienteTeste.getTipo() == novoTipo) {
			return ErroCliente.erroEditarCliente(id);
		}
		
		this.clienteService.editarCliente(clienteTeste, novoTipo);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	

}
