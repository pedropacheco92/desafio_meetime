package com.meecarros.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meecarros.models.Person;
import com.meecarros.services.PersonService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/persons")
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	public Collection<Person> getAllProspects(@PathVariable String token) {
		return this.service.getAllProspects(token);
	}

}
