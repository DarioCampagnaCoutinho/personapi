package com.cam.personapi.service;

import com.cam.personapi.dto.request.PersonDTO;
import com.cam.personapi.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cam.personapi.dto.response.MessageResponseDto;
import com.cam.personapi.entity.Person;
import com.cam.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public MessageResponseDto createPerson(PersonDTO personDTO) {
		Person personToSaved = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(personToSaved);
		return MessageResponseDto.builder()
				.message("Created person with ID : " + savedPerson.getId())
				.build();
	}

}
