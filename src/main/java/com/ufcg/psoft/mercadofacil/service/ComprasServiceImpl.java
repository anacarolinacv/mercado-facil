package com.ufcg.psoft.mercadofacil.service;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.ufcg.psoft.mercadofacil.model.CarrinhodeCompras;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.MetodoPagamento;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoDeComprasRepository;
import com.ufcg.psoft.mercadofacil.repository.ClienteRepository;
import com.ufcg.psoft.mercadofacil.repository.ComprasRepository;

@Service
public class ComprasServiceImpl implements ComprasService{
	
	@Autowired
	private ComprasRepository comprasRepository;
	
	@Autowired
	private CarrinhoDeComprasRepository carrinhoRepository;

	@Override
	public void adicionarCompra(String pagamento, Cliente cliente) {
	
		
		CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
		Compra compra = new Compra(carrinho.getProdutos(), pagamento, cliente);
		comprasRepository.save(compra);
		
	}
	
	@Override
	public String exibirDescricaoCompra(Long id) {
		
		Compra compra = comprasRepository.getOne(id);
		String saida = "";
		
		if (carrinhoRepository != null ) {
			saida = compra.exibirDescricaoCompra();
		}
		
		return saida;
	}

	@Override
	public String exibirUltimasCompras() {
		List<Compra> compras = comprasRepository.findAll();
		String saida = "";
		
		for(Compra compra: compras) {
			saida += compra.getProdutosCarrinho().exibirProdutosDoCarrinho();
		}
		
		return saida;
	}

	@Override
	public void finalizarCompra(String tipoPagamento, Cliente cliente) {

		CarrinhodeCompras carrinho = carrinhoRepository.findAll().get(0);
		Compra compra = new Compra(carrinho.getProdutos(), tipoPagamento , cliente);
		compra.finalizarCompra(tipoPagamento);
		
	}
	
}
