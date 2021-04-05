package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCliente {
	
	static final String CLIENTE_NAO_CASDASTRADO = "Cliente com id %s não está cadastrado";
	static final String ATRIBUTO_VAZIO = "Atributo incorreto";
	static final String NENHUM_CLIENTE_CADASTRADO = "Não existe cliente cadastrado";
	static final String NAO_PODE_EDITAR_TIPO = "O tipo novo é o mesmo do antigo";
	
	
	public static ResponseEntity<CustomErrorType> erroClienteNaoEncontrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCliente.CLIENTE_NAO_CASDASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	public static ResponseEntity<CustomErrorType> erroAtributoIncorreto() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCliente.ATRIBUTO_VAZIO, "INCORRETO")),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroNenhumClienteCadastrado() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCliente.CLIENTE_NAO_CASDASTRADO, "VAZIO")),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroEditarCliente(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCliente.CLIENTE_NAO_CASDASTRADO, id)),
				HttpStatus.CONFLICT);
	}
}
