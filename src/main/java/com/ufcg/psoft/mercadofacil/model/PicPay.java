package com.ufcg.psoft.mercadofacil.model;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PicPay implements MetodoPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private LocalDate data;
	private Double valor;
	private String tipo;
	

	public PicPay(Double valor) {
		super();
		this.valor = valor;
		this.tipo = "PICPAY";
	}

	@Override
	public Double gerarAcrescimo(Double valorCompra) {
		setValor(valorCompra * 1.02); 
		return valorCompra * 1.02;
	}
	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return String.format("%s - DATA: %s - VALOR: %s", getTipo(), getData(), getValor());
	}
	
	
	
	

}
