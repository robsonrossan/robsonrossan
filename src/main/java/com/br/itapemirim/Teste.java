package com.br.itapemirim;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teste {
	
	public static void main(String[] args) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("123")); 
	}

}
