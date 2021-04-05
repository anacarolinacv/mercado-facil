package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class ErroCarrinhodeCompras {
	
	static final String CARRINHO_NAO_DISPONIVEL = "Carrinho não está disponível";
	
	static final String CARRINHO_VAZIO = "Não há produtos cadastrados no carrinho";
	
	static final String COMPRA_JA_FINALIZADA = "Compra já finalizada";
	
	static final String PRODUTO_NAO_CADASTRADO = "Produto não cadastrado no carrinho";
	
	
	
	
	public static ResponseEntity<CustomErrorType> erroCarrinhoIndisponivel() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCarrinhodeCompras.CARRINHO_NAO_DISPONIVEL),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroCarrinhoVazio() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCarrinhodeCompras.CARRINHO_VAZIO),
				HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<?> erroCompraFinalizada() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCarrinhodeCompras.COMPRA_JA_FINALIZADA),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<?> erroProdutoNaoCadastradoNoCarrinho(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCarrinhodeCompras.PRODUTO_NAO_CADASTRADO),
				HttpStatus.NOT_FOUND);
	}
	
	
	
}
