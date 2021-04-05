package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDoCarrinhoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> getProdutoById(long id) {
		return produtoRepository.findById(id);
	}
	
	public List<Produto> getProdutoByCodigoBarra(String codigo) {
		return produtoRepository.findByCodigoBarra(codigo);
	}
	
	public void removerProdutoCadastrado(Produto produto) {
		produtoRepository.delete(produto);
	}

	public void salvarProdutoCadastrado(Produto produto) {
		produtoRepository.save(produto);		
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto criaProduto(ProdutoDoCarrinhoDTO produtoDTO) {
		Produto produto = new Produto(produtoDTO.getProduto().getNome(), produtoDTO.getProduto().getFabricante(),
				produtoDTO.getProduto().getCodigoBarra(),produtoDTO.getProduto().getPreco(),
				produtoDTO.getProduto().getCategoria());
		
		produto.tornaDisponivel();
		return produto;
	}

	public Produto atualizaProduto(ProdutoDoCarrinhoDTO produtoDTO, Produto produto) {
		produto.setNome(produtoDTO.getProduto().getNome());
		produto.setPreco(produtoDTO.getProduto().getPreco());
		produto.setCodigoBarra(produtoDTO.getProduto().getCodigoBarra());
		produto.mudaFabricante(produtoDTO.getProduto().getFabricante());
		produto.mudaCategoria(produtoDTO.getProduto().getCategoria());
		
		return produto;
	}
}
