package com.meecarros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meecarros.models.Prospect;
import com.meecarros.services.ProspectService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/prospects")
public class ProspectController {

	@Autowired
	private ProspectService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Prospect> getAllProspects() {
		return this.service.getAllProspects();
	}

}
