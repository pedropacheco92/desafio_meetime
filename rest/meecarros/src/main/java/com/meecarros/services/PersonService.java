package com.meecarros.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meecarros.models.PersonDTO;
import com.meecarros.models.Person;
import com.meecarros.utils.Parameters;

@Service
public class PersonService {

	private RestTemplate restTemplate;

	@PostConstruct
	private void init() {
		this.restTemplate = new RestTemplate();
	}

	public List<Person> getAllProspects() {
		PersonDTO persons = this.restTemplate.getForObject(Parameters.URL + "/persons?api_token=" + Parameters.TOKEN, PersonDTO.class);

		return persons.getData();
	}

}
