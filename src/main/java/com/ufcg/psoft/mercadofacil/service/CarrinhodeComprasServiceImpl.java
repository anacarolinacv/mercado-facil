package com.ufcg.psoft.mercadofacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.CarrinhodeCompras;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoDeComprasRepository;

@Service
public class CarrinhodeComprasServiceImpl implements CarrinhodeComprasService {
	
	@Autowired
	private CarrinhoDeComprasRepository carrinhoRepository;
	
	
	public boolean criarCarrinho() {
		CarrinhodeCompras carrinho = new CarrinhodeCompras();
		boolean saida = false;
		
		if(carrinhoRepository == null) {
			
			carrinhoRepository.save(carrinho);
			saida = true;
		}
		
		return saida;
	}

	@Override
	public boolean adicionarProduto(Produto produto, int qtdDesejadas) {
		boolean saida = false;
		if(carrinhoRepository != null) {
			
			CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
			saida = carrinho.adicionarProduto(produto, qtdDesejadas);
			carrinhoRepository.saveAndFlush(carrinho);
			
		}
		return saida;
	}

	@Override
	public void removerProduto(Produto produto, int qtdDesejadas) {
		CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
		
		
		if(carrinhoRepository != null) {
			carrinho.removerProduto(produto, qtdDesejadas);
		}
	
		carrinhoRepository.saveAndFlush(carrinho);
	}

	@Override
	public boolean limparCarrinho() {
		CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
		boolean saida = false;
		
		if(carrinhoRepository != null) {
			
			carrinho.limparCarrinho();
			carrinhoRepository.saveAndFlush(carrinho);
			saida = true;
		}
		
		return saida;
	}

	@Override
	public void descartarCarrinho() {
		if(carrinhoRepository != null) {
			
			CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
			carrinho.descartarCarrinho();
			carrinhoRepository.saveAndFlush(carrinho);
		}	
	}

	@Override
	public String exibirProdutosDoCarrinho() {
		String saida = "";
		if(carrinhoRepository != null) {
			
			CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
			saida = carrinho.exibirProdutosDoCarrinho();
			
		}	
		return saida;
	}

	

}
