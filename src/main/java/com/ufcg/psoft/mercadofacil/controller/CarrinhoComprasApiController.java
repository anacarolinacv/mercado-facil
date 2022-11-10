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


import com.ufcg.psoft.mercadofacil.model.CarrinhodeCompras;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.CarrinhodeComprasService;
import com.ufcg.psoft.mercadofacil.service.LoteService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroCarrinhodeCompras;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;
import com.ufcg.psoft.mercadofacil.util.ErroLote;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin
public class CarrinhoComprasApiController {

	@Autowired
	CarrinhodeComprasService carrinhoService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	LoteService loteService;
	
	@RequestMapping(value="/carrinho", method = RequestMethod.POST)
	public ResponseEntity<?> criarCarrinho(@RequestBody CarrinhodeCompras carrinho) {
		
		if (carrinhoService.criarCarrinho()) {
			return ErroCarrinhodeCompras.erroCarrinhoVazio();
		}
		carrinhoService.criarCarrinho();
		return new ResponseEntity<CarrinhodeCompras>(carrinho, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/carrinho", method = RequestMethod.PUT)
	public ResponseEntity<?> limparCarrinho(@RequestBody CarrinhodeCompras carrinho) {
		
		if (!carrinhoService.limparCarrinho()) {
			return ErroCarrinhodeCompras.erroCarrinhoVazio();
		}
		
		carrinhoService.limparCarrinho();
		return new ResponseEntity<CarrinhodeCompras>(carrinho, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/carrinho/{id}/{num_itens}", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarProdutoNoCarrinho(@PathVariable("num_itens") int numItens, @PathVariable("id_produto") long id) {
		
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);
		
		Produto produto = optionalProduto.get();
		
		if(numItens < 0) {
			return ErroProduto.erroQtdProdutoNaoAceita();
		}
		
		if(produto != null) {
			
			int itens = produto.getLote().getNumeroDeItens();
			
			if(itens <= 0) {
				return ErroLote.erroSemProdutosDisponiveis();
			}
			
			if (!(produto.isDisponivel())){
				
				return ErroProduto.erroProdutoNaoEnconrtrado(id);
			}
	
			if (!(carrinhoService.adicionarProduto(produto, itens))){
				return ErroCarrinhodeCompras.erroProdutoNaoCadastradoNoCarrinho(id);
			}
		} 
		carrinhoService.adicionarProduto(produto, numItens );
		
		return new ResponseEntity<>(produto, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/carrinho/{id}/{num_itens}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerProdutoDoCarrinho(@PathVariable("num_itens") int numItens, @PathVariable("id_produto") long id) {
		
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);
		
		if(numItens < 0) {
			return ErroProduto.erroQtdProdutoNaoAceita();
		}
		
		if(!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
		
		Produto produto = optionalProduto.get();

		
		if(produto != null) {
			
			if (carrinhoService.exibirProdutosDoCarrinho().isEmpty()) {
				return ErroCarrinhodeCompras.erroCarrinhoVazio();
			}
			
			if (!(produto.isDisponivel())){
				return ErroProduto.erroProdutoNaoEnconrtrado(id);
			}
			
			carrinhoService.removerProduto(produto, numItens);
			
		} else {
			
			return ErroCarrinhodeCompras.erroProdutoNaoCadastradoNoCarrinho(id);
		}
		
		return new ResponseEntity<>(produto, HttpStatus.NO_CONTENT);	
	}
	
	@RequestMapping(value = "/carrinho/produtos", method = RequestMethod.GET)
	public ResponseEntity<?> listarItensCarrinho() {
		
		
		if (carrinhoService.exibirProdutosDoCarrinho().isEmpty()) {
			return ErroCarrinhodeCompras.erroCarrinhoVazio();
		}
		
		String produtosCadastrados = carrinhoService.exibirProdutosDoCarrinho();
		
		return new ResponseEntity<>(produtosCadastrados, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/carrinho/descartar", method = RequestMethod.POST)
	public ResponseEntity<?> descartarCarrinho() {
		
		carrinhoService.descartarCarrinho();
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
