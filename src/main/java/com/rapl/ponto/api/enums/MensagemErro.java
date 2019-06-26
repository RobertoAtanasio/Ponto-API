package com.rapl.ponto.api.enums;

public enum MensagemErro {

	EMP_NAO_ENCONTRADA("Empresa não encontrada para o CNPJ: ");
	
	String mensagem;
	
	MensagemErro(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
