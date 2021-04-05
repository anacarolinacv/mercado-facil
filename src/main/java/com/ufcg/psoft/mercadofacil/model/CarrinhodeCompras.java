package com.ufcg.psoft.mercadofacil.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CarrinhodeCompras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private boolean isDisponivel;
	@OneToMany
	@JoinColumn(name="produto_id")
	private List<Venda> produtos;
	
	public CarrinhodeCompras () {
		this.produtos = new ArrayList<Venda>();
		this.isDisponivel = true;
		this.id = 0;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String exibirProdutosDoCarrinho() {
		
		String produtosFormatados = "";
		
		for (Venda produtoCarrinho : this.produtos) {
			produtosFormatados += produtoCarrinho.toString() + System.lineSeparator();
		}
		
		return produtosFormatados;
	}


	public boolean limparCarrinho() {
		
		this.produtos = new ArrayList<Venda>();
		
		return true;
	}
	
	
	public void descartarCarrinho() {
		setDisponivel(true);
		retornarAoLote();
	}
	
	public void retornarAoLote() {
		
		for(Venda produto : this.produtos) {
			if(!(produto.equals(null))) {
				produto.aumentarLote(produto.getProduto(), produto.getQuantidadeProdutoNoCarrinho());
			}
		}
	}
	
	public boolean isDisponivel() {
		return isDisponivel;
	}

	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

	public List<Venda> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Venda> produtos) {
		this.produtos = produtos;
	}
	
	public void removerProduto(Produto produto, int qtdProdutosDesejados) {

		Venda produtoCarrinho = procuraProduto(produto);
		Venda produtoCarrinhoPesquisar = null;
		
		
		if(this.isDisponivel) {
			
			if(!(this.produtos.isEmpty())) {
				
				int IndiceProduto = this.produtos.indexOf(produtoCarrinho);
				produtoCarrinhoPesquisar = this.produtos.get(IndiceProduto);
				produtoCarrinhoPesquisar.removerQtdProduto(qtdProdutosDesejados);
				
				if(produtoCarrinhoPesquisar.getQuantidadeProdutoNoCarrinho() == 0) {
					this.produtos.remove(IndiceProduto);
				}
				
				produtoCarrinhoPesquisar.aumentarLote(produtoCarrinhoPesquisar.getProduto(), qtdProdutosDesejados);
					
			}
		}
		
	}

	public boolean adicionarProduto(Produto produto, int qtdProdutosDesejados) {

		Venda produtoCarrinho = procuraProduto(produto);
		Venda produtoCarrinhoSalvo = null;
		boolean saida = false;
		
		if(this.isDisponivel) {
			setDisponivel(false);
		}
		
		if(produto.verificaLote(qtdProdutosDesejados)) {
			
			if(produtoCarrinho == null) {
				produtoCarrinhoSalvo = new Venda(produto, qtdProdutosDesejados);
				this.produtos.add(produtoCarrinhoSalvo);
			} else {
				int IndiceProduto = this.produtos.indexOf(produtoCarrinho);
				produtoCarrinhoSalvo = this.produtos.get(IndiceProduto);
				produtoCarrinhoSalvo.setQuantidadeProdutoNoCarrinho(produtoCarrinhoSalvo.getQuantidadeProdutoNoCarrinho()+ qtdProdutosDesejados);
				
			}
			saida = true;
		}
		
		produtoCarrinhoSalvo.reduzLote(produto, qtdProdutosDesejados);
		
		return saida;
	}
		
	private Venda procuraProduto(Produto produto) {
		
		for (Venda produtoCarrinho : this.produtos) {
			
			if(produto.equals(produtoCarrinho.getProduto())) {
				return produtoCarrinho;
			}
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
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
		CarrinhodeCompras other = (CarrinhodeCompras) obj;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}

}
