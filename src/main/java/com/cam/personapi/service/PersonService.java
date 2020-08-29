package com.cam.personapi.service;

import com.cam.personapi.dto.request.PersonDTO;
import com.cam.personapi.exception.PersonNotFoundException;
import com.cam.personapi.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cam.personapi.dto.response.MessageResponseDto;
import com.cam.personapi.entity.Person;
import com.cam.personapi.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public List<PersonDTO> findAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Optional<Person> optionalPerson = personRepository.findById(id);
		if(optionalPerson.isEmpty()){
			throw new PersonNotFoundException(id);
		}
		return personMapper.toDTO(optionalPerson.get());
	}
}
