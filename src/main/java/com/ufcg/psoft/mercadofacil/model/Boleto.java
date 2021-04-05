package com.ufcg.psoft.mercadofacil.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Boleto implements MetodoPagamento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private LocalDate data;
	private Double valor;
	private String tipo;
	

	public Boleto(Double valor) {
		super();
		this.data = LocalDate.now();
		this.valor = valor;
		this.tipo = "BOLETO";
	}

	@Override
	public Double gerarAcrescimo(Double valorCompra) {
		setValor(valorCompra);
		return valorCompra;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
