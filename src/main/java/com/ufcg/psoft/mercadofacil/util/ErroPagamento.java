package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroPagamento {
	
static final String NAO_POSSUE_ESSE_TIPO = "O tipo de pagamento nao existe";
	
	
	public static ResponseEntity<CustomErrorType> erroPagamentoNaoEncontrado(String pagamento) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroPagamento.NAO_POSSUE_ESSE_TIPO, pagamento)),
				HttpStatus.CONFLICT);
	}

}
