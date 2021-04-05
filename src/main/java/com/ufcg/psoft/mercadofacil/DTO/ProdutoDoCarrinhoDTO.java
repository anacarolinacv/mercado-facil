package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class ProdutoDoCarrinhoDTO {

	private Produto produto;
	
	private int quantidade_produto;
	
	public ProdutoDoCarrinhoDTO() {
		
	}
	
	public ProdutoDoCarrinhoDTO(Produto produto, int quantidade_produto) {
		super();
		this.produto = produto;
		this.quantidade_produto = quantidade_produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}
}
