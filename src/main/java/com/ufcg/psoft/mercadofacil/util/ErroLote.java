package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLote {

	static final String SEM_LOTES_CADASTRADOS = "Não há lotes cadastrados";
	
	static final String SEM_LOTE_DISPONÍVEL = "Não há quantidade de produtos disponível";
	
	public static ResponseEntity<CustomErrorType> erroSemLotesCadastrados() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLote.SEM_LOTES_CADASTRADOS),
				HttpStatus.NO_CONTENT);
	}
	
	public static ResponseEntity<CustomErrorType> erroSemProdutosDisponiveis() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLote.SEM_LOTE_DISPONÍVEL),
				HttpStatus.NO_CONTENT);
	}
	
}

