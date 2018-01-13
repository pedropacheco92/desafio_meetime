package com.meecarros.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.meecarros.models.Person;
import com.meecarros.models.PersonDTO;
import com.meecarros.models.PersonsDTO;
import com.meecarros.utils.Parameters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {

	private RestTemplate restTemplate;

	@PostConstruct
	private void init() {
		this.restTemplate = new RestTemplate();
	}

	public List<Person> getAllProspects() {
		PersonsDTO persons = this.restTemplate.getForObject(Parameters.URL + "/persons?api_token=" + Parameters.TOKEN, PersonsDTO.class);

		return persons.getData();
	}

	public Person getPessoa(Long personId) {
		PersonDTO person = null;
		try {
			person = this.restTemplate.getForObject(Parameters.URL + "/persons/" + personId + "?api_token=" + Parameters.TOKEN, PersonDTO.class);
		} catch (RestClientException e) {
			log.info("Não foi encontrada pessoa com id: " + personId);
			return null;
		}

		return person.getData();
	}

}
