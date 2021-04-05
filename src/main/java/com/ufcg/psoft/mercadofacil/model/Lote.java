package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
    private Produto produto;
    private int numeroDeItens;

    public Lote() {
        
    }

    public Lote(Produto produto, int numeroDeItens) {
        super();
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
    }

    public Lote(long id, Produto produto, int numeroDeItens) {
        this.id = id;
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
    }
    
    public void reduzLote(int qtdRetirada) {
    	
    	if(getNumeroDeItens() >= qtdRetirada) {
    		setNumeroDeItens(getNumeroDeItens() - qtdRetirada);
    	}
    }
    
	public void aumentarLote(int qtdAdicionar) {
	    this.numeroDeItens += qtdAdicionar;
	}
	
	public boolean verificaLote(int qtdRetirada) {
		boolean saida = false;
		
		if(this.numeroDeItens >= qtdRetirada) {
			saida = true;
			return saida;
		} else {
			return saida;
		}
	}
	
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", produto=" + produto.getId() +
                ", numeroDeItens=" + numeroDeItens + '\'' +
                '}';
    }
}
