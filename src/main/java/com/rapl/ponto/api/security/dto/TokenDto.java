package com.rapl.ponto.api.security.dto;

//Esta classe faz parte do login da aplicação. Retornar o Token.

public class TokenDto {

	private String token; 
	
	public TokenDto() {
	}
	
	public TokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
