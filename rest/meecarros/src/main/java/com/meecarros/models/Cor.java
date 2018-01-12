package com.meecarros.models;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Cor {

	PRETO, BRANCO, VERDE;

	@JsonValue
	@Override
	public String toString() {
		return StringUtils.capitalize(super.toString().toLowerCase());
	}

}
