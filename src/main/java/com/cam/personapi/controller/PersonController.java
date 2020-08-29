package com.cam.personapi.controller;

import com.cam.personapi.dto.request.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cam.personapi.dto.response.MessageResponseDto;
import com.cam.personapi.entity.Person;
import com.cam.personapi.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDto personCreate(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}

	@GetMapping
	public List<PersonDTO> listAll(){
		return personService.findAll();
	}
	
}
