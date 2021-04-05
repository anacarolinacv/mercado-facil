package com.ufcg.psoft.mercadofacil.model;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private CarrinhodeCompras produtosCarrinho;
	private Double valorTotal;
	private LocalDateTime dataCriacao;
	@Embedded
	private MetodoPagamento pagamento;
	private boolean isFinalizado;
	
	@ManyToOne()
	private Cliente cliente;
	
	
	public Compra() {}

	public Compra(List<Venda> produtosCarrinho, String tipoPagamento, Cliente cliente) {
		this.produtosCarrinho = new CarrinhodeCompras();
		this.valorTotal = this.somaValorCompra(produtosCarrinho);
		this.dataCriacao = LocalDateTime.now();
		this.pagamento = new Boleto(this.valorTotal);
		this.isFinalizado = false;
		this.cliente = cliente;
		
	}

	public MetodoPagamento getPagamento() {
		return pagamento;
	}

	private MetodoPagamento salvarTipoPagamento(String tipoPagamento) {
		MetodoPagamento metodo = new Boleto(this.valorTotal);

		if (tipoPagamento.toUpperCase() == "PICPAY") {
			this.pagamento = new PicPay(this.valorTotal);
		} 
		
		if (tipoPagamento.toUpperCase() == "CREDITO") {
			this.pagamento = new CartaoDeCredito(this.valorTotal);
		} 
		
		return metodo;
	}

	public Long getId() {
		return id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double somaValorCompra(List<Venda> produtosCarrinho) {
		
		Double valorTotal = 0.0;

		for (Venda produtoCarrinho : produtosCarrinho) {
			valorTotal += produtoCarrinho.valorProdutos();
		}
		
		this.valorTotal = valorTotal;
		this.valorTotal = this.pagamento.gerarAcrescimo(this.valorTotal);
		
		aplicaDesconto();
		
		return valorTotal;
	}
	
	
	private void aplicaDesconto() {
		
		String tipo = cliente.getTipo();
		
		if(tipo == "ESPECIAL" && produtosCarrinho.getProdutos().size() > 10) {
			this.valorTotal = valorTotal * 0.9;
		}
		
		if(tipo == "PREMIUM" && produtosCarrinho.getProdutos().size() > 5) {
			this.valorTotal = valorTotal * 0.9;
		}
		
	}

	public CarrinhodeCompras getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public String exibirDescricaoCompra() {
		
		String produtosFormatados = "";
		
		produtosFormatados = this.produtosCarrinho.exibirProdutosDoCarrinho();
		
		String valorCompraFormatado = "VALOR DA COMPRA: " + String.valueOf(somaValorCompra(this.produtosCarrinho.getProdutos()).doubleValue());
		produtosFormatados += valorCompraFormatado + System.lineSeparator();
		
		return produtosFormatados;
	}

	public String exibirComprasComValorEFormaPagamento() {
		
		String produtosFormatados = "";
		
		produtosFormatados = this.produtosCarrinho.exibirProdutosDoCarrinho();
		
		String valorCompraFormatado = "VALOR DA COMPRA: " + String.valueOf(somaValorCompra(this.produtosCarrinho.getProdutos()).doubleValue());
		produtosFormatados += valorCompraFormatado + System.lineSeparator();
		produtosFormatados += "DATA: " + getDataCriacao() + System.lineSeparator();
		produtosFormatados += "PAGAMENTO: " + getPagamento().getTipo() + System.lineSeparator();
		
		return produtosFormatados;
	}
	
	public boolean finalizarCompra(String pagamento) {
		
		boolean saida = false;
			
		if(!(this.produtosCarrinho.getProdutos().isEmpty())) {
			setFinalizado(true);
			salvarTipoPagamento(pagamento);
			saida = true;
		}
		
		descartarCarrinho();
		return saida;
	}
	
	public void descartarCarrinho() {
			
		if(!(this.isFinalizado)) {
				
			this.produtosCarrinho.descartarCarrinho();
		}
		this.produtosCarrinho.setDisponivel(true);
		this.produtosCarrinho.limparCarrinho();
	}

	public boolean isFinalizado() {
		return isFinalizado;
	}

	public void setFinalizado(boolean isFinalizado) {
		this.isFinalizado = isFinalizado;
	}
	

}
