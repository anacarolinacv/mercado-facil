package com.ufcg.psoft.mercadofacil.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.GenerationType;
import com.ufcg.psoft.mercadofacil.model.Produto;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	private Double preco;

	private String codigoBarra;

	private String fabricante;

	private String categoria;

	private boolean isDisponivel;
	
	@OneToOne(mappedBy = "produto")
	@JsonIgnore
    private Lote lote;
	
	private Produto() {    }

    public Produto(String nome, String codigoBarra, String fabricante,
            Double preco, String nomeCategoria) {

        this.nome = nome;
        this.preco = preco;
        this.codigoBarra = codigoBarra;
        this.fabricante = fabricante;
        this.categoria = nomeCategoria;
        this.isDisponivel = false;
    }
	
	public void reduzLote(int qtdRetirada) {
		
		this.lote.reduzLote(qtdRetirada);
	}
	
	public boolean verificaLote(int qtdRetirada) {
		return this.lote.verificaLote(qtdRetirada);
	}
	
	public void aumentarLote(int qtdAdicionada) {
		this.lote.aumentarLote(qtdAdicionada);
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}
	

	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void mudaFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void mudaCategoria(String categoria) {
		this.categoria = categoria;
	}
		
	public void tornaDisponivel() { 
		this.isDisponivel = true;
	}
	
	public boolean isDisponivel() {
		return this.isDisponivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produto other = (Produto) obj;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
    public String toString() {
        return String.format("NOME: %s - CÓDIGO: %s - ", getNome().toUpperCase(), getCodigoBarra());
    }
}