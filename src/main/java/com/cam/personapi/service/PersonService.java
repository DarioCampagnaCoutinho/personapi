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
		return createMessageResponse(savedPerson.getId(), "Created person with ID : ");
	}

	public List<PersonDTO> findAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);

		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException{
		verifyIfExists(id);
		personRepository.deleteById(id);
	}

	public MessageResponseDto updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		Person personToUpdate = personMapper.toModel(personDTO);
		Person updatePerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatePerson.getId(), "Updated person with ID : ");
	}

	private Person verifyIfExists(Long id)throws PersonNotFoundException{
		return personRepository.findById(id).orElseThrow(()->new PersonNotFoundException(id));
	}

	private MessageResponseDto createMessageResponse(Long id, String s) {
		return MessageResponseDto.builder()
				.message(s + id)
				.build();
	}
}
