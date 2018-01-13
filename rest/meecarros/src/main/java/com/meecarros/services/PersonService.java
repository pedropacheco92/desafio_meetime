package com.meecarros.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.meecarros.models.Person;
import com.meecarros.models.PersonsDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {

	private RestTemplate restTemplate;

	public static final String URL = "https://teste7.pipedrive.com/v1";

	@PostConstruct
	private void init() {
		this.restTemplate = new RestTemplate();
	}

	public List<Person> getAllProspects(String token) {
		PersonsDTO persons = null;
		try {
			persons = this.restTemplate.getForObject(URL + "/persons?api_token=" + token, PersonsDTO.class);
		} catch (RestClientException e) {
			log.info("Não foi encontrado token: " + token);
			return null;
		}

		return persons.getData();
	}

}
