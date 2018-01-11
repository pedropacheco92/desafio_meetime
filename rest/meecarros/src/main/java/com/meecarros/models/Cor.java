package com.meecarros.models;

import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonValue;

@AllArgsConstructor
public enum Cor {

	PRETO(1L),
	BRANCO(2L),
	VERDE(3L);

	private Long id;

	@JsonValue
	public Long getId() {
		return this.id;
	}

}
