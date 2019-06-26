package com.rapl.ponto.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptEncoder.encode("123456"));
	}
}
