package com.meecarros.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meecarros.models.Person;
import com.meecarros.models.Prospect;
import com.meecarros.utils.Parameters;

@Service
public class ProspectService {

	private RestTemplate restTemplate;

	@PostConstruct
	private void init() {
		this.restTemplate = new RestTemplate();
	}

	public List<Prospect> getAllProspects() {
		Person persons = this.restTemplate.getForObject(Parameters.URL + "/persons?api_token=" + Parameters.TOKEN, Person.class);

		return persons.getData();
	}

}
