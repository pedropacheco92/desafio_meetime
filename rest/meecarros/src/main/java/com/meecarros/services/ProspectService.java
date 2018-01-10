package com.meecarros.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meecarros.models.Person;
import com.meecarros.models.Prospect;

@Service
public class ProspectService {

	private static final String TOKEN = "7b89e5ee230957c0971499e1c502fc18e0e23c89";

	private static final String URL = "https://teste7.pipedrive.com/v1";

	@PostConstruct
	private void init() {
	}

	public List<Prospect> getAllProspects() {
		RestTemplate restTemplate = new RestTemplate();
		Person persons = restTemplate.getForObject(URL + "/persons?api_token=" + TOKEN, Person.class);

		return persons.getData();
	}

}
