package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;

public interface CarrinhodeComprasService {
	
	public boolean criarCarrinho();
	
	public boolean adicionarProduto(Produto produto, int qtdDesejadas);
	
    public void removerProduto(Produto produto, int qtdDesejadas);
    
    public String exibirProdutosDoCarrinho();
    
    public boolean limparCarrinho();
    
    public void descartarCarrinho();
    
   
    
}
