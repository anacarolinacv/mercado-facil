package com.ufcg.psoft.mercadofacil.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Venda {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@JsonIgnore
	private Produto produto;
	private int quantidadeProdutoNoCarrinho;
	
	public Venda() {
		
	}
	public Venda(Produto produto, int quantidadeProdutoNoCarrinho) {
		super();
		this.produto = produto;
		this.quantidadeProdutoNoCarrinho = quantidadeProdutoNoCarrinho;
	}
	
	public Venda(Produto produto) {
		super();
		this.produto = produto;
		this.quantidadeProdutoNoCarrinho = 1;
	}
	
	
	public void reduzLote (Produto produto, int qtdRetirada) {
		
		if(this.produto.equals(produto)) {
			this.produto.reduzLote(qtdRetirada);
		}	
	}
	
	public boolean verificaLote(Produto produto, int qtdRetirada) {
		
		boolean saida = false;
		
		if(this.produto.equals(produto)) {
			
			saida = this.produto.verificaLote(qtdRetirada);	
		}
			
		return saida;
	}
	
	public void aumentarLote(Produto produto, int qtdRetornada) {
		if(this.produto.equals(produto)) {
			this.produto.aumentarLote(qtdRetornada);
		}
	}
	
	public Double valorProdutos() {
		return multiply();
	}
	
	private Double multiply () {
		Double valorPreco = this.produto.getPreco();
		
	   return  valorPreco * this.quantidadeProdutoNoCarrinho;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean removerQtdProduto(int qtdDesejada) {
		
		boolean saida = false;
		
		if(getQuantidadeProdutoNoCarrinho() >= qtdDesejada) {
			
			setQuantidadeProdutoNoCarrinho(getQuantidadeProdutoNoCarrinho() - qtdDesejada);
			saida = true;
		}
		
		
		return saida;
	}

	public Produto getProduto() {
		return produto;
	}
	

	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	public int getQuantidadeProdutoNoCarrinho() {
		return quantidadeProdutoNoCarrinho;
	}


	public void setQuantidadeProdutoNoCarrinho(int quantidadeProdutoNoCarrinho) {
		this.quantidadeProdutoNoCarrinho = quantidadeProdutoNoCarrinho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		String saida = this.produto.toString();
		saida += String.format("ITENS: %s unidades - ", getQuantidadeProdutoNoCarrinho());
		
		return saida;
	}
	
	
	
	
	

}
