package com.cam.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cam.personapi.dto.response.MessageResponseDto;
import com.cam.personapi.entity.Person;
import com.cam.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public MessageResponseDto createPerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return MessageResponseDto.builder()
				.message("Created person with ID : " + savedPerson.getId())
				.build();
	}

}
