package com.meecarros.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Car {

	private Long id;

	private String modelo;

	private Cor cor;

	private String ano;

	private Person person;

}
