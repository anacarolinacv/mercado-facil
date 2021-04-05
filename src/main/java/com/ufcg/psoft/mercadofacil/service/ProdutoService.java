package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDoCarrinhoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface ProdutoService {

	public Optional<Produto> getProdutoById(long id);
	
	public List<Produto> getProdutoByCodigoBarra(String codigo);
	
	public void removerProdutoCadastrado(Produto produto);

	public void salvarProdutoCadastrado(Produto produto);

	public List<Produto> listarProdutos();
	
	public Produto criaProduto(ProdutoDoCarrinhoDTO produto);
	
	public Produto atualizaProduto(ProdutoDoCarrinhoDTO produtoDTO, Produto produto);
}
