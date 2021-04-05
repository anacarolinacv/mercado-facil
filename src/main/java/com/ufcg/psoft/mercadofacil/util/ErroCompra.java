package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCompra {
	
	static final String COMPRA_NAO_EXISTENTE = "Não há produtos relacionados no ID";
	
	public static ResponseEntity<CustomErrorType> erroCompraVazio() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCompra.COMPRA_NAO_EXISTENTE),
				HttpStatus.NO_CONTENT);
	}

}
