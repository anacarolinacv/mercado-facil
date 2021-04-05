package com.ufcg.psoft.mercadofacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.service.CarrinhodeComprasService;
import com.ufcg.psoft.mercadofacil.service.ClienteService;
import com.ufcg.psoft.mercadofacil.service.ComprasService;
import com.ufcg.psoft.mercadofacil.util.ErroCarrinhodeCompras;
import com.ufcg.psoft.mercadofacil.util.ErroCliente;
import com.ufcg.psoft.mercadofacil.util.ErroCompra;
import com.ufcg.psoft.mercadofacil.util.ErroPagamento;
import com.ufcg.psoft.mercadofacil.model.Cliente;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ComprasApiController {
	
	@Autowired
	CarrinhodeComprasService carrinhoService;
	
	@Autowired
	ComprasService compraService;
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value = "/compras/{idCliente}/{id}/nova", method = RequestMethod.POST)
		public ResponseEntity<?> finalizarCompra(@RequestBody String tipoPagamento, @PathVariable Long id, @PathVariable Long idCliente) {

		
		if (carrinhoService.exibirProdutosDoCarrinho().isEmpty()) {
			return ErroCarrinhodeCompras.erroCarrinhoVazio();
		}
		
		if(this.clienteService.getClienteById(id) == null) {
			return ErroCliente.erroClienteNaoEncontrado(id);
		}
		
		if((tipoPagamento.toUpperCase() != "BOLETO") && (tipoPagamento.toUpperCase() != "CREDITO") && (tipoPagamento.toUpperCase() != "PICPAY" )) {
			return ErroPagamento.erroPagamentoNaoEncontrado(tipoPagamento);
		}
		
		Cliente cliente = this.clienteService.getClienteById(idCliente).get();
		compraService.finalizarCompra(tipoPagamento, cliente);
		compraService.adicionarCompra(tipoPagamento, cliente);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@RequestMapping(value = "/compras/{id}/{idCompra}", method = RequestMethod.GET)
		public ResponseEntity<?> exibirDescricaoCompra(@PathVariable("id") Long id, @PathVariable("idCompra") Long idCompra ) {
		
		if(this.clienteService.getClienteById(id) == null) {
			return ErroCliente.erroClienteNaoEncontrado(id);
		}
		if (compraService.exibirDescricaoCompra(idCompra) == null) {
			return ErroCompra.erroCompraVazio();
		}
		
		compraService.exibirDescricaoCompra(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	public ResponseEntity<?> exibirUltimasCompras() {
	
		if (compraService.exibirUltimasCompras() == null) {
			return ErroCompra.erroCompraVazio();
		}
		
		compraService.exibirUltimasCompras();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
